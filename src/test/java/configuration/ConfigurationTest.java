package configuration;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ConfigurationTest {
    String s = "  dbCon.properties";

    @Test
    public void getFileName() {
        for(char c : s.toCharArray()) {
            if(c == ' ') s = s.replaceFirst(" ", "");
            else break;
        }
        assertEquals(s, "dbCon.properties");
    }
}
