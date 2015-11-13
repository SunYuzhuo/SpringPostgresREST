package com.confer.sun.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.confer.sun.repository")
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.confer.sun.entity"})
public class RepositoryConfig {
}
