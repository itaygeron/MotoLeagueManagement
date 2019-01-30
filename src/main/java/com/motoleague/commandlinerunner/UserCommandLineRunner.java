package com.motoleague.commandlinerunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.motoleague.repository.UserRepository;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

	private final UserRepository repository;
	
	@Autowired
	public UserCommandLineRunner(UserRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void run(String... args) throws Exception {
		repository.findAll().forEach(System.out::println);
	}

}
