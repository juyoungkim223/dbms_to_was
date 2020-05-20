package com.common.controller;

import configuration.ConfigurationSetting;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@PropertySource("classpath:application.properties")
public class ConfigController {
    @Value("${dbConnectionFile}")
    String configFileName;
    ConfigurationSetting configurationSetting = new ConfigurationSetting();

    @GetMapping("/config")
    public void config() {
        try {
            configurationSetting.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/config2")
    public void config2() {
        String filePath = configFileName;
        System.out.println("filePath : " + filePath);
    }

}
