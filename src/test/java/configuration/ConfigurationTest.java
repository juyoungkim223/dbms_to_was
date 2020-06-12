package configuration;

import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ConfigurationTest {
    String s = "  dbCon.properties";

    @Test
    public void getFileName() {
        String s1 = "jdbcTemplate.datasource.ref";
        int first = s1.indexOf('.');
        int second = s1.indexOf('.', first + 1);

        assertEquals(s1.substring(0, first), "jdbcTemplate");
        assertEquals( s1.substring(first + 1, second ), "datasource");
    }

    @Test
    public void getFileName2() {
        String s1 = "jdbcTemplate.id";
        int first = s1.indexOf('.');
        int second = s1.indexOf('.', first + 1);
        System.out.println(second);
        assertEquals(s1.substring(0, first), "jdbcTemplate");
        assertEquals( s1.substring(first + 1), "id");

    }

    @Test
    public void writeFile() throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        File newFile = resourceLoader.getResource("classpath:applicationContext.xml").getFile();
        FileWriter fw2 = new FileWriter(newFile, false);
        fw2.write("test");
        fw2.close();

    }
}
