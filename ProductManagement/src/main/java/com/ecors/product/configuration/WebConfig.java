package com.ecors.product.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	@Autowired
	private WebIntercepter webIntercepter;

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(webIntercepter);

	}

}
