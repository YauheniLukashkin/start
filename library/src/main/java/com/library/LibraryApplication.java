package com.library;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan
public class LibraryApplication {
	@Bean
	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
		return hemf.getSessionFactory();
	}
		
	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}
}
