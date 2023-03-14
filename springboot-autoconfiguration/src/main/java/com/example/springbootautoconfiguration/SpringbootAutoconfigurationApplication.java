package com.example.springbootautoconfiguration;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootAutoconfigurationApplication {

	@Bean
	ApplicationRunner run(ConditionEvaluationReport report) {
		return args -> {
			report.getConditionAndOutcomesBySource().entrySet().stream()
					.filter(co -> co.getValue().isFullMatch())
					.filter(co -> !co.getKey().contains("Jmx"))
					.forEach(co -> {
						System.out.println(co.getKey());
						co.getValue().forEach(c -> {
							System.out.println("\t" + c.getOutcome());
						});
						System.out.println();
					});
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAutoconfigurationApplication.class, args);
	}

}
