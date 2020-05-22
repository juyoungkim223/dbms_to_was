package com.common.vo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@Data
@PropertySource("classpath:application.properties")
public class User {
    @Value("${dbConfigFile}")
    String configFileName;

    private String user;
    private String password;

}
