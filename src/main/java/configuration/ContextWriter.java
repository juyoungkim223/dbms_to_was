package configuration;

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
                "        <property name=\"driverClassName\" value=\"${mysqlDriver}\"/>\n" +
                "        <property name=\"url\" value=\"${mysqlServerConnectionUrl}\"/>\n" +
                "        <property name=\"username\" value=\"${mysqlServerConnectionUsername}\"/>\n" +
                "        <property name=\"password\" value=\"${mysqlServerConnectionPassword}\"/>\n" +
                "        <property name=\"initialSize\" value=\"3\"/>\n" +
                "        <!--p:maxActive=\"${db.maxActive}\"\n" +
                "          p:maxIdle=\"${db.maxIdle}\"\n" +
                "          p:maxWait=\"${db.maxWait}\"-->\n" +
                "    </bean>\n" +
                /*"  <bean id=\"jdbcTemplate\" class=\"org.springframework.jdbc.core.JdbcTemplate\">\n" +
                "        <property name=\"dataSource\" ref=\"dbcp2\" />\n" +
                "    </bean>\n" +*/
                "\n" +
                "    <bean id=\"cofigurationSetting\" class=\"configuration.ConfigurationSetting\"/>\n" +
                "\n" +
                "    <bean class=\"configuration.ConfigurationSetting\"/>\n" +
                "</beans>";
        File newFile = resourceLoader.getResource("classpath:applicationContext.xml").getFile();
        writeBaseConfig(newFile, initialConfig);
        writeAppendConfig(newFile);
    }

    public void writeAppendConfig(File file) throws IOException {
        InputStream inputStream = resourceLoader.getResource("applicationContext.xml").getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream, UTF_8);
        String res = FileCopyUtils.copyToString(reader);
        res = res.replace("</beans>", "");
        res += getStringByPropertiesMap();
    }

    public void writeBaseConfig(File file, String source) throws IOException {
        FileWriter fileWriter = new FileWriter(file, false);
        Writer writer = new BufferedWriter(fileWriter);
        writer.write(source);
        writer.close();
        System.out.println("gen currentThread().getId() : " + currentThread().getId());
    }

    public String getStringByPropertiesMap() {
        Map<String, List<BeanInfo>> map = PropertyReader.getInstance().getAllPropertyMap();
        StringBuilder sb = new StringBuilder();
        map.forEach((k,v) -> {
            for(BeanInfo bi : map.get(k)) {
                appendBean(sb, bi);
            }
            sb.append("</bean>");
        });
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

                break;
            case "VALUE" :
                break;
            case "REF" :
                break;
        }
        return sb;
    }
}