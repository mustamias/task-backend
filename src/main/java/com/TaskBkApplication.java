package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.model.AppUser;
import com.service.IUserService;


@SpringBootApplication
public class TaskBkApplication  implements CommandLineRunner{
	
	
   @Autowired
   private IUserService userService;

	public static void main(String[] args) {
		SpringApplication.run(TaskBkApplication.class, args);
       

	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		AppUser user = userService.findUserByUserName("mus");
		
		if (user == null)
				userService.saveUser(new AppUser(null, "mus", "123") );
		
	}

}
