package com.example.demo.resources.contentfiltering;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter(value="SomeBeanFilter")
public class SomeOtherData {
	private String one;
	private String two;
	private String three;
	public SomeOtherData(String one, String two, String three) {
		super();
		this.one = one;
		this.two = two;
		this.three = three;
	}
	public String getOne() {
		return one;
	}
	public void setOne(String one) {
		this.one = one;
	}
	public String getTwo() {
		return two;
	}
	public void setTwo(String two) {
		this.two = two;
	}
	public String getThree() {
		return three;
	}
	public void setThree(String three) {
		this.three = three;
	}	
}
