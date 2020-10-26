package br.com.fiap.Brain_Tech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BrainTechApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrainTechApplication.class, args);
	}

}
