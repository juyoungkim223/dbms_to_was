package com.common;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.ContextLoaderListener;
import javax.servlet.ServletContextEvent;
import java.io.*;
import static java.nio.charset.StandardCharsets.UTF_8;

public class CustomContextLoaderListener extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            //<bean class="com.common.vo.User"/>
            System.out.println("custom ctxloaderlistener");
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            InputStream inputStream = resourceLoader.getResource("applicationContext.xml").getInputStream();
            StringBuilder sb = new StringBuilder();
            Reader reader = new InputStreamReader(inputStream, UTF_8);
            String res = FileCopyUtils.copyToString(reader);
            sb.append(res);
            System.out.println(res);
            
            String str = sb.toString();
            System.out.println(str.lastIndexOf('>'));
            str = str.replace("</beans>", "");
            str += "<bean class=\"com.common.vo.User\"/>\n</beans>";
            System.out.println(str);
            super.contextInitialized(event);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch(Exception e) {
            Exception exceptionToLog = (e instanceof BeanCreationException) ? null : e;
            e.printStackTrace();
            contextDestroyed(event);
        }
    }

    public void addBean() {

    }
}
