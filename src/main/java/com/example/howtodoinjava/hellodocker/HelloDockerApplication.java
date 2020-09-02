package com.example.howtodoinjava.hellodocker;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;

@SpringBootApplication
public class HelloDockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloDockerApplication.class, args);
	}
}

@RestController
class HelloDockerRestController {
	
	@RequestMapping("/hello/{name}")
	public String helloDocker(@PathVariable(value = "name") String name) {
		String response = "Hello " + name + " Response received on : " + new Date();
		System.out.println(response);
		return response;

	}
	
	@RequestMapping("/hello/actions")
	public String githubActions() {
		String response = "GitHub Actions Tested  and verified: " + new Date();
		System.out.println(response);
		return response;

	}
	
	
	@RequestMapping("/hello/secrets")
	public String helloSecret() {
		String kvUri = "https://keyvaultpoc-avery.vault.azure.net";

		SecretClient secretClient = new SecretClientBuilder()
		    .vaultUrl(kvUri)
		    .credential(new DefaultAzureCredentialBuilder().build())
		    .buildClient();
		return secretClient.getSecret("name").getValue();

	}
}
