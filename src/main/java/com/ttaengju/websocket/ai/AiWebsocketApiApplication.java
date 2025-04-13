package com.ttaengju.websocket.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class AiWebsocketApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiWebsocketApiApplication.class, args);
	}

}
