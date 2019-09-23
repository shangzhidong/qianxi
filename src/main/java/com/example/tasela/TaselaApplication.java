package com.example.tasela;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication
@MapperScan(basePackages = {"com.example.*.dao.mapper,com.example.*.dao.queryMapper"})
public class TaselaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaselaApplication.class, args);
	}

}
