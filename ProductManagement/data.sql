insert into product (`productid`, `product_description`, `product_name`,`active`,`product_img_url`,`price`,`discounted_price`,`discount`,`highlights`,`delivery_fee_discounted`) values(1, 'Drill machine description', 'Bosch drill machine 500W',true,'http://www.pngall.com/wp-content/uploads/4/Drill-Machine-PNG-Free-Image.png',3000,2500,15,'{
	"Type": "Pistol Grip Drill",
	"Chuck Size": "10 mm",
	"Rotation" : "Reverse Rotation",
	"Power Source": "Corded",
	"Usage Type": "Home & Professional"
}',true);

insert into product (`productid`, `product_description`, `product_name`,`active`,`product_img_url`,`price`,`discounted_price`,`discount`,`highlights`,`delivery_fee_discounted`) values(2, 'Heavy duty Drill machine description', 'Heavy duty drill machine 500W',true,'http://www.pngall.com/wp-content/uploads/4/Heavy-Duty-Drill-Machine-PNG-Image.png',5000,3500,20,'{
	"Type": "Pistol Grip Drill",
	"Chuck Size": "10 mm",
	"Rotation" : "Reverse Rotation",
	"Power Source": "Corded",
	"Usage Type": "Home & Professional"
}',true);

insert into product_images(`id`,`image`,`type`,`productid`) values(1,'http://www.pngall.com/wp-content/uploads/4/Drill-Machine-PNG-Free-Image.png','MEDIUM',1);
insert into product_images(`id`,`image`,`type`,`productid`) values(2,'http://www.pngall.com/wp-content/uploads/4/Heavy-Duty-Drill-Machine-PNG-Image.png','MEDIUM',1);
insert into product_images(`id`,`image`,`type`,`productid`) values(3,'http://www.pngall.com/wp-content/uploads/4/Drill-Machine-PNG-Free-Image.png','MEDIUM',1);

insert into category (`category_id`,`category_name`) values (1,'Power tools');

insert into category (`category_id`,`category_name`) values (2,'Eletronics');

insert into sub_category (`sub_category_id`, `sub_category_name`, `category_id`,`active`,`sub_category_img_url`,`offer_message`) values (1,'Drill machinw',1,true,'http://www.pngall.com/wp-content/uploads/4/Drill-Machine-PNG-Free-Image.png','Min 60% off');

insert into sub_category (`sub_category_id`, `sub_category_name`, `category_id`,`active`,`sub_category_img_url`,`offer_message`) values (2,'Mobile',2,true,'https://www.searchpng.com/wp-content/uploads/2019/02/HUAWEI-P20-715x856.png','Min 40% off');

insert into sub_category (`sub_category_id`, `sub_category_name`, `category_id`,`active`,`sub_category_img_url`,`offer_message`) values (3,'Video games',2,true,'https://upload.wikimedia.org/wikipedia/commons/8/8c/PS4-Console-wDS4.png','Play with joy');

insert into sub_category (`sub_category_id`, `sub_category_name`, `category_id`,`active`,`sub_category_img_url`,`offer_message`) values (4,'Cloths',2,true,'http://www.pngall.com/wp-content/uploads/2018/04/Clothing-PNG-Picture.png','60%-90% off');

insert into sub_category (`sub_category_id`, `sub_category_name`, `category_id`,`active`,`sub_category_img_url`,`offer_message`) values (5,'Jeans',2,true,'http://www.pngall.com/wp-content/uploads/2016/04/Jeans-PNG-Pic.png','40%-60% off');

INSERT INTO offer (`offer_id`,`active`, `active_from`, `offer_description`, `offer_name`) VALUES (1,true, now(), 'Deal of the day', 'Deal of the day');

INSERT INTO offer (`offer_id`,`active`, `active_from`, `offer_description`, `offer_name`) VALUES (2,true, now(), 'Deals On Large Appliances', 'Deals On Large Appliances');

INSERT INTO offer_sub_category (`offer_sub_category_id`,`offer_id`,`sub_category_id`) VALUES (1,1,1);

INSERT INTO offer_sub_category (`offer_sub_category_id`,`offer_id`,`sub_category_id`) VALUES (2,1,2);

INSERT INTO offer_sub_category (`offer_sub_category_id`,`offer_id`,`sub_category_id`) VALUES (3,2,2);

INSERT INTO offer_sub_category (`offer_sub_category_id`,`offer_id`,`sub_category_id`) VALUES (4,1,3);

INSERT INTO offer_sub_category (`offer_sub_category_id`,`offer_id`,`sub_category_id`) VALUES (5,1,4);

INSERT INTO offer_sub_category (`offer_sub_category_id`,`offer_id`,`sub_category_id`) VALUES (6,1,5);

INSERT INTO product_sub_category (`id`,`productid`,`sub_category_id`) VALUES (1,1,1);
INSERT INTO product_sub_category (`id`,`productid`,`sub_category_id`) VALUES (2,2,1);