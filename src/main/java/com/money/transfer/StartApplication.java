package com.money.transfer;

import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.money.transfer.model.Account;
import com.money.transfer.service.AccountService;

@SpringBootApplication
public class StartApplication {
	private static final Logger logger = LoggerFactory.getLogger(StartApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(AccountService accountService) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Account>> typeReference = new TypeReference<List<Account>>() {
			};

			logger.info("Loading resources (" + "/accounts-mock.json" + ")");
			InputStream inputstream = TypeReference.class.getResourceAsStream("/accounts-mock.json");
			List<Account> accounts = mapper.readValue(inputstream, typeReference);
			accountService.createAccount(accounts);
			logger.info("resources loaded succesfully");
			logger.info("System, is ready to make a transfer through ...!!");
		};

	}
}
