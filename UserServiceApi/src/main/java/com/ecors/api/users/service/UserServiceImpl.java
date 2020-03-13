package com.ecors.api.users.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ecors.api.users.DTO.AddressDTO;
import com.ecors.api.users.DTO.UserContext;
import com.ecors.api.users.DTO.UserDTO;
import com.ecors.api.users.entity.Address;
import com.ecors.api.users.entity.Orders;
import com.ecors.api.users.entity.OrderDeliveryStatus;
import com.ecors.api.users.entity.User;
import com.ecors.api.users.entity.UserOrders;
import com.ecors.api.users.entity.UserRole;
import com.ecors.api.users.enums.MailType;
import com.ecors.api.users.enums.OrderStatus;
import com.ecors.api.users.enums.UserType;
import com.ecors.api.users.repository.OrderRepository;
import com.ecors.api.users.repository.UserRepository;
import com.ecors.api.users.service.client.MailServiceClient;
import com.ecors.api.users.service.client.ProductManagementClient;
import com.ecors.api.users.ui.request.SendMailRequest;
import com.ecors.core.exception.NotFoundException;
import com.ecors.core.ui.response.GenericResponse;
import com.ecors.core.utility.ModelMapperUtils;

@Service
public class UserServiceImpl implements UserService {
	
	public static final boolean YES=true;
	

	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private MailServiceClient mailServiceClient;

	@Autowired
	private AddressService addressService;

	@Autowired
	private ProductManagementClient productManagementClient;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;

	}

	@Value("${default.password}")
	private String defaultPassword;

	/**
	 * Create user details in DB , first checks if user is exists or not
	 * 
	 */

	@Override
	public UserDTO createUser(UserDTO userDto) {
		User userEntity = getUserByUsername(userDto.getUsername());
		ModelMapperUtils.map(userDto, userEntity);
		userEntity.setPassword((bCryptPasswordEncoder.encode(userDto.getPassword())));
		userRepository.save(userEntity);
		SendMailRequest mailRequest = new SendMailRequest();
		mailRequest.setToAddress(userDto.getUsername());
		mailRequest.setMailType(MailType.SIGNUP);
		mailServiceClient.sendMail(mailRequest);
		return ModelMapperUtils.map(userEntity, UserDTO.class);
	}

	private User getUserByUsername(String username) {
		Optional<User> userEntityOptional = userRepository.findUserByUsername(username);
		if (userEntityOptional.isPresent()) {
			return userEntityOptional.get();
		}
		throw new NotFoundException("User");
	}

	@Override
	@Transactional
	public UserContext loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userEntity = userRepository.findUserByUsername(username);
		String password = null;
		if (userEntity.isPresent()) {
			User user = userEntity.get();

			if (user.getOTP() != null && !user.getOTP().isEmpty()) {
				password = user.getOTP();
			} else {
				password = user.getPassword();
			}
			List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
			Set<UserRole> roles = user.getUserRoles();
			list.add(new SimpleGrantedAuthority("ROLE_" + roles.stream().findFirst().get().getType().name()));

			return new UserContext(userEntity.get().getUsername(), password, true, true, true, true, list,
					user.getUserId());
		}
		return null;

	}

	@Override
	public UserDTO getUserByEmailID(String email) {
		Optional<User> userEntity = userRepository.findUserByUsername(email);
		if (userEntity.isPresent()) {
			return ModelMapperUtils.map(userEntity.get(), UserDTO.class);
		}
		throw new NotFoundException("User");
	}

	@Override
	public UserDTO getUserByUserId(String userID) {
		UserDTO userDTO = ModelMapperUtils.map(getUser(userID), UserDTO.class);
		userDTO.setAddress(ModelMapperUtils.map(addressService.getAllByUserId(getUser(userID)), AddressDTO.class));
		return userDTO;
	}

	/**
	 * Creates user in database using provided emailID only.
	 * 
	 * @param emailid
	 * @return User's basic data userId,OTP,emailID as username
	 */
	public UserDTO createBasicUser(String emailid, String otp) {
		String encodedOTP = bCryptPasswordEncoder.encode(otp);
		Optional<User> userEntity = userRepository.findUserByUsername(emailid);
		if (userEntity.isPresent()) {
			userEntity.get().setOTP(encodedOTP);
			User user = userRepository.save(userEntity.get());
			return ModelMapperUtils.map(userRepository.save(user), UserDTO.class);
		}
		User user = new User();
		user.setUsername(emailid);
		UserRole role = new UserRole();
		role.setType(UserType.USER);
		role.setUser(user);
		user.setOTP(encodedOTP);
		Set<UserRole> roles = new HashSet<>();
		roles.add(role);
		user.setUserId(UUID.randomUUID().toString());
		user.setUserRoles(roles);
		return ModelMapperUtils.map(userRepository.save(user), UserDTO.class);
	}

	public User getUser(String userId) {
		Optional<User> userEntity = userRepository.findUserByUserId(userId);
		if (userEntity.isPresent()) {
			return userEntity.get();
		}
		throw new NotFoundException("User");
	}

	public User getUserFromUUID(String userId) {
		Optional<User> userEntity = userRepository.findUserByUserId(userId);
		if (userEntity.isPresent()) {
			return userEntity.get();
		}
		throw new NotFoundException("User");
	}
	
	@Override
	public List<AddressDTO> getAddressesByUser(String userId) {
		return addressService.getAllByUserId(getUser(userId));
	}

	@Override
	public String createOrder(List<Integer> order, String userID) {

		// Retrieve user and delivery address
		User user = authenticationService.getUserFromUUID(userID);
		// Get product details
		ResponseEntity<GenericResponse<List<com.ecors.core.dto.ProductDTO>>> productList = productManagementClient
				.getProductsByIDs(order);
		// Save order
		return orderRepository.save(saveOrderEntity(productList.getBody().getData().getResult(), user,
				getDeliveryAddress(user.getAddresses()))).getOrderId().toString();

	}

	private Address getDeliveryAddress(Set<Address> addresses) {
		Assert.notEmpty(addresses, "Please save atleast one address");
		List<Address> deliveryAddress = addresses.stream().filter(addres -> addres.isDeliveryAddress() == YES)
				.collect(Collectors.toList());
		Assert.notEmpty(deliveryAddress, "Delivery address not available");
		return deliveryAddress.stream().findFirst().get();
	}

	private Orders saveOrderEntity(List<com.ecors.core.dto.ProductDTO> productList, User user, Address address) {
		Assert.notEmpty(productList, "Product(s) not found");
		Orders order = new Orders();
		order.setAddress(address);
		order.setUser(user);
		Set<UserOrders> userOrders = new HashSet<>();
		productList.forEach(product -> {
			userOrders.add(saveUserOrders(product.getProductID(), product.isDeliveryFeeDiscounted(),
					product.getDeliveryDate(), order));
		});

		order.setUserOrder(userOrders);
		return order;
	}

	private UserOrders saveUserOrders(Integer productId, boolean deliveryFeeDiscounted, LocalDateTime deliveryDate,
			Orders orders) {
		UserOrders userOrders = new UserOrders();
		userOrders.setDeliveryFeeDiscounted(deliveryFeeDiscounted);
		userOrders.setProductId(productId.toString());
		userOrders.setDeliveryDate(deliveryDate);
		Set<OrderDeliveryStatus> orderDeliveryStatus = new HashSet<>();
		orderDeliveryStatus.add(saveOrderDeliveryStatus(userOrders));
		userOrders.setOrderDeliveryStatus(orderDeliveryStatus);
		userOrders.setOrder(orders);
		return userOrders;
	}

	private OrderDeliveryStatus saveOrderDeliveryStatus(UserOrders userOrders) {
		OrderDeliveryStatus orderDeliveryStatus = new OrderDeliveryStatus();
		orderDeliveryStatus.setDeliveryStatus(OrderStatus.CREATED);
		orderDeliveryStatus.setDate(LocalDateTime.now());
		orderDeliveryStatus.setUserOrders(userOrders);
		return orderDeliveryStatus;
	}

}
