package configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

@org.springframework.context.annotation.Configuration
@PropertySource("classpath:application.properties")
public class ConfigurationSetting implements Configuration {
    @Value("${dbConfigFile}")
    String configFileName;

    @Override
    public ConfigFileType getFile() throws IOException {
        String filePath = configFileName;
        System.out.println("filePath : " + filePath);
        //TODO class path
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
        return ConfigFileType.Properties;
    }

    @Override
    public String getKey(String key) {
        return null;
    }

    @Override
    public Set<String> getKeys() {
        return null;
    }
}
