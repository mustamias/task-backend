package com.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class AppUser {

	@Id  
	private String id;
	private String username;
	private String password;

		
	
}
