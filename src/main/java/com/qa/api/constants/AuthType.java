package com.qa.api.constants;


//enum is collection of multiple constants
//An enum is a special "class" that represents a group of constants (unchangeable variables, like final variables).
//To create an enum, use the enum keyword (instead of class or interface), and separate the constants with a comma. 
//Note that they should be in uppercase letters:


public enum AuthType {

	BEARER_TOKEN,
	OAUTH2,
	BASIC_AUTH,
	API_KEY,
	NO_AUTH
	
}
