package rename_apus;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by ReutskyVV on 24.11.2014.
 */
public class Config_read {

    public String config_read(String conf_file, String key) {
        Properties prop = new Properties();
        try {
            prop.load(new InputStreamReader(new FileInputStream(conf_file),
                    "UTF-8"));
            return prop.getProperty(key);
        } catch (IOException ex) {
            System.out.println("---EE--- read config file error: " + ex.getMessage());
            ex.printStackTrace();
            new First().mylog("---EE--- read config file error: " + ex.getMessage());
            return "";
        }
    }

}
