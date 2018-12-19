package com.example.demo.resources.contentfiltering;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class ContentFilteringRestController {

	@RequestMapping(value="/filtered", method=RequestMethod.GET)
	public SomeData staticFiltering() {
		return new SomeData("field1","field2","filed3");
	}
	
	@RequestMapping(value="/dynamicFilter", method=RequestMethod.GET)
	public MappingJacksonValue dynamicFiltering() {
		SomeOtherData otherData = new SomeOtherData("one", "two", "three");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("two");
		
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		MappingJacksonValue jacksonValue = new MappingJacksonValue(otherData);
		jacksonValue.setFilters(filterProvider);
		
		return jacksonValue;
	}
	
	
}
