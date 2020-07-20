package com.common;

import com.common.configuration.*;
import org.springframework.web.context.ContextLoaderListener;
import javax.servlet.ServletContextEvent;
import java.util.List;

public class CustomContextLoaderListener extends ContextLoaderListener {
    ContextGenerator propertyGenerator = new ContextGenerator();
    ConfigEventListener configEventListener = new ContextWriter();

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            propertyGenerator.registerConfigEventListener(configEventListener);
            propertyGenerator.generate();
            //System.out.println("Ctx : " + currentThread().getId());
            Thread.sleep(5000);
            System.out.println("call super contextinit");
            super.contextInitialized(event);
        } catch(Exception e) {
            e.printStackTrace();
            contextDestroyed(event);
        }
    }
}