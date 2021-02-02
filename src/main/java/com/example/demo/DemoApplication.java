package com.example.demo;

import com.example.demo.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
	 	ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
		String[] names = run.getBeanDefinitionNames();
		System.out.println(run.containsBean("user1"));
		System.out.println(run.containsBean("user2"));
		System.out.println(run.getBean("userP", User.class).getName());
	}
}
