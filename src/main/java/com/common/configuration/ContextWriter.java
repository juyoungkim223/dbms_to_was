package com.common.configuration;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import java.io.*;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.currentThread;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ContextWriter implements ConfigEventListener {
    static ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Override
    public void writeEvent() throws IOException, InterruptedException {
        System.out.println("Performing callback after synchronous Task");
        generate();
    }
    //deploy cannot create new file, only do update existing file
    public void generate() throws IOException, InterruptedException {
        resourceLoader.getResource("applicationContext.xml").getInputStream();
        String initialConfig = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n" +
                "       http://www.springframework.org/schema/beans/spring-beans.xsd\">\n" +
                "    <bean class=\"org.springframework.beans.factory.config.PropertyPlaceholderConfigurer\">\n" +
                "        <property name=\"locations\" value=\"classpath:bean.properties\"/>\n" +
                "    </bean>\n" +
                "    <bean class=\"org.springframework.beans.factory.config.PropertyPlaceholderConfigurer\">\n" +
                "        <property name=\"locations\" value=\"classpath:db.properties\"/>\n" +
                "    </bean>\n" +
                "    <bean id=\"dbcp2\" class=\"org.apache.commons.dbcp2.BasicDataSource\" >\n" +
                "        <property name=\"driverClassName\" value=\"com.mysql.cj.jdbc.Driver\"/>\n" +
                "        <property name=\"url\" value=\"jdbc:mysql://125.133.210.234:3306/calendar?serverTimezone=Asia/Seoul\"/>\n" +
                "        <property name=\"username\" value=\"jy\"/>\n" +
                "        <property name=\"password\" value=\"852467\"/>\n" +
                "        <property name=\"initialSize\" value=\"3\"/>\n" +
                "    </bean>\n" +
                "        <!--p:maxActive=\"${db.maxActive}\"\n" +
                "          p:maxIdle=\"${db.maxIdle}\"\n" +
                "          p:maxWait=\"${db.maxWait}\"\n" +
                "    </bean>\n" +
                "    <bean id=\"jdbcTemplate\" class=\"org.springframework.jdbc.core.JdbcTemplate\">\n" +
                "        <property name=\"dataSource\" ref=\"dbcp2\" />\n" +
                "    </bean>-->";
        File newFile = resourceLoader.getResource("classpath:applicationContext.xml").getFile();
        writeBaseConfig(newFile, initialConfig);
        //writeAppendConfig(newFile);
    }

    public void writeBaseConfig(File file, String initialConfig) throws IOException {
        FileWriter fileWriter = new FileWriter(file, false);
        Writer writer = new BufferedWriter(fileWriter);
        writer.write(writeAppendConfig(initialConfig));
        writer.close();
        System.out.println("gen currentThread().getId() : " + currentThread().getId());
    }

    public String writeAppendConfig(String str) {
        return getStringByPropertiesMap(str);
    }

    public String getStringByPropertiesMap(String res) {
        Map<String, List<BeanInfo>> map = PropertyReader.getInstance().getAllPropertyMap();

        StringBuilder sb = new StringBuilder();
        sb.append(res);
        map.forEach((k,v) -> {
            for(BeanInfo bi : map.get(k)) {
                appendBean(sb, bi);
            }
            sb.append("</bean>\n");
        });
        sb.append("</beans>");
        return sb.toString();
    }

    public StringBuilder appendBean(StringBuilder sb, BeanInfo bi) {
        switch (bi.getType().name()) {
            case "ID" :
                sb.append("<bean id=\"")
                        .append(bi.getBeanName())
                        .append("\"");
                break;
            case "CLASS" :
                sb.append(" class=\"")
                        .append(bi.getPropertyValue())
                        .append("\">\n");
                break;
            case "VALUE" :
                sb.append("<property name=\"")
                        .append(bi.getPropertyName())
                        .append("\"")
                        .append(" value=\"")
                        .append(bi.getPropertyValue())
                        .append("\"/>\n");
                break;
            case "REF" :
                sb.append("<property name=\"")
                        .append(bi.getPropertyName())
                        .append("\"")
                        .append(" ref=\"")
                        .append(bi.getPropertyValue())
                        .append("\"/>\n");
                break;
        }
        return sb;
    }
}