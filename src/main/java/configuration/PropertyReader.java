package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

//Bill Pugh Solution for singleton pattern
public class PropertyReader implements Reader {
    private ResourceLoader resourceLoader = new DefaultResourceLoader();
    private static Properties properties = new Properties();
    private List<BeanInfo> list = new ArrayList<>();
    private Map<String, List<BeanInfo>> map = new HashMap<>();

    private PropertyReader() {
        try {
            InputStream inputStream = resourceLoader.getResource("bean.properties").getInputStream();
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //to hold PropertyReader object
    private static class Holder {
        private static final PropertyReader INSTANCE = new PropertyReader();
    }

    public static PropertyReader getInstance() {
        return Holder.INSTANCE;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public Set<String> getAllPropertyNames() {
        return properties.stringPropertyNames();
    }

    public boolean containsKey(String key) {
        return properties.containsKey(key);
    }

    public Map<String, List<BeanInfo>> getAllPropertyMap() {
        for(Object o : properties.keySet()) {
           String s = o.toString();
           BeanInfo beanInfo = resolveBeanInfo(s);
           if(!map.containsKey(beanInfo.getBeanName())) {
               map.put(beanInfo.getBeanName(), new ArrayList<>());
           }
           List<BeanInfo> beanInfos = map.get(beanInfo.getBeanName());
           beanInfos.add(beanInfo);
           map.put(beanInfo.getBeanName(), beanInfos);
        }
        map.forEach((k,v) -> Collections.sort(map.get(k), new BeanInfoComparator()));
        map.forEach((k,v) -> {
            for(BeanInfo s : map.get(k)) {
                System.out.println(k + " : " + s.toString());
            }
        });
        return map;
    }

    public BeanInfo resolveBeanInfo(String s1) {
        BeanInfo beanInfo = new BeanInfo();
        int first = s1.indexOf('.');
        beanInfo.setBeanName(s1.substring(0, first));

        int second = s1.indexOf('.', first + 1);
        if(second < 0) beanInfo.setType(BeanInfo.BType.valueOf(s1.substring(first + 1).toUpperCase()));
        else {
            beanInfo.setType(BeanInfo.BType.valueOf(s1.substring(first + 1, second).toUpperCase()));
            beanInfo.setPropertyName(s1.substring(second + 1));
        }
        return beanInfo;
    }

}
