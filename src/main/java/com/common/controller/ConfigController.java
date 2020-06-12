package com.common.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.xml.configuration.*;


@RestController
@PropertySource("classpath:application.properties")
public class ConfigController {
    ConfigurationSetting configurationSetting = new ConfigurationSetting();
    ConfigurationSetting configuration = new ConfigurationSetting();
    //add to application context as bean
    @Value("${dbConfigFile}")
    String configFileName;
    @GetMapping("/config")
    public void config() {
        try {
            configurationSetting.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/configByJar")
    public void configByJar() {
        /*try {
            Configuration.
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @GetMapping("/config2")
    public void config2() {
        String filePath = configFileName;
        System.out.println("filePath : " + filePath);
        try {
            ClassPathResource configFile = new ClassPathResource(filePath);
            File file = configFile.getFile();
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
