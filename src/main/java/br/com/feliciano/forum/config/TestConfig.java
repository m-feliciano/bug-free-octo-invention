package br.com.feliciano.forum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.feliciano.forum.services.DbService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DbService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String profile;

	@Bean
	public boolean instantiateDatabase() {
		if (!"create".equals(profile)) {
			return false;
		}
		dbService.instantiateDatabase();
		return true;
	}

}
