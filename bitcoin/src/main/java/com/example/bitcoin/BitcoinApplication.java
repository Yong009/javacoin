package com.example.bitcoin;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan
@SpringBootApplication
@EnableScheduling
public class BitcoinApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(BitcoinApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(BitcoinApplication.class);
	}
}


