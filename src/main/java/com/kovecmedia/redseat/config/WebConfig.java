package com.kovecmedia.redseat.config;

import java.awt.image.BufferedImage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan("com.kovecmedia.redseat")
public class WebConfig implements WebMvcConfigurer {

	  @Bean
	  public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
	      return new BufferedImageHttpMessageConverter();
	  }
	  
	  
}