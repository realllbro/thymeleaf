package org.brolab.thymeleaf.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Address {

	private String postCode;
	
	private String location;

}
