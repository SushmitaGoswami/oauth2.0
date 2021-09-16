package com.sushmita.github.webclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SpringWebApplication {

	@Bean
	WebClient webClient(
			ReactiveClientRegistrationRepository clientRegistrations,
			ServerOAuth2AuthorizedClientRepository authorizedClients) {

		ServerOAuth2AuthorizedClientExchangeFilterFunction oauth =
				new ServerOAuth2AuthorizedClientExchangeFilterFunction(
						clientRegistrations,
						authorizedClients);

		oauth.setDefaultOAuth2AuthorizedClient(true);

		return WebClient.builder()
				.filter(oauth)
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringWebApplication.class, args);
	}

}
