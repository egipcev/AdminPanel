import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Helper {
    public static Map<String,String> getProperties() {
        Map<String, String> inpudData = new HashMap<>();
        try (InputStream input = Helper.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            inpudData.put("customer", prop.getProperty("customer"));
            inpudData.put("dashboard_name", prop.getProperty("dashboard_name"));
            inpudData.put("display_title", prop.getProperty("display_title"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return inpudData;
    }
}
