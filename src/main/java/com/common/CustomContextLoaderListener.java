package com.common;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CustomContextLoaderListener extends ContextLoaderListener {
    @Autowired
    ApplicationContext context;

    public CustomContextLoaderListener() {
    }

    public CustomContextLoaderListener(WebApplicationContext context) {
        super(context);
    }


    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            //<bean class="com.common.vo.User"/>
            System.out.println("custom ctxloaderlistener");
            WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
            //WebApplicationContext appCxt= WebApplicationContextUtils.getWebApplicationContext(ctx);
            System.out.println(ctx);
            /*File file = new File("WEB-INF/applicationContext.xml");

            //System.out.println(factory.getDisplayName());
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }*/
            super.contextInitialized(event);

        }
        catch(Exception e) {
            Exception exceptionToLog = (e instanceof BeanCreationException) ? null : e;
            e.printStackTrace();
            contextDestroyed(event);
        }
    }
}
