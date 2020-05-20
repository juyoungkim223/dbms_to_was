package configuration;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ConfigurationTest {
    String s = "dbCon.properties";
    @Test
    public void getFileName() {
        assertEquals("properties", s.substring(s.indexOf('.') + 1));
    }
}
