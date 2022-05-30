package com.berg.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Value
@ConstructorBinding
@ConfigurationProperties(prefix = "db")
public class DatabaseProperties {

    String username;
    String password;
    String driver;
    String jdbc;
    String hosts;
}
