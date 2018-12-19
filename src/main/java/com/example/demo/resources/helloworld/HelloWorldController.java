package com.example.demo.resources.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value="/hello-world", method=RequestMethod.GET)
	public String testRequest(@RequestParam String param) {
		return "hello world";
	}
	
//	@RequestMapping(value="/hello-world-international", method=RequestMethod.GET)
//	public String testRequestInternational(@RequestHeader (name="Accept-Language", required = false) Locale locale) {
//		if(locale == null) {
//			System.out.println("*&^%$#@#$%^&*");
//		}
//		System.out.println("******************"+locale.getCountry());
//		return messageSource.getMessage("good.morning.message", null,locale);
//	}
	
	@RequestMapping(value="/hello-world-bean", method=RequestMethod.GET)
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("hello world");
	}
	
	@RequestMapping(value="/hello-world/path-variable/{name}", method=RequestMethod.GET)
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		System.out.println(name);
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
	

}
