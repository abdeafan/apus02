package rename_apus;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;

/**
 * Created by ReutskyVV on 24.11.2014.
 */
public class Config_create {

    public boolean config_create(String conf_file) {
        Properties prop = new Properties();
        try {
            prop.setProperty("FtpServerDest", "127.0.0.1");
            prop.setProperty("FtpServerSource", "127.0.0.1");
            prop.setProperty("AtsMode", "426226");
            prop.setProperty("UserDest", "solo");
            prop.setProperty("PasswordDest", "123456789qQ");
            prop.setProperty("UserSource", "solo");
            prop.setProperty("PasswordSource", "123456789qQ");
            prop.setProperty("HowMuchDays", "7");
            prop.setProperty("Offline", "true");
            prop.store(new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(conf_file), "UTF-8")), null);
            return true;
        } catch (IOException ex) {
            System.out.println("---EE--- create config file error: " + ex.getMessage());
            ex.printStackTrace();
            new First().mylog("---EE--- create config file error: " + ex.getMessage());
            return false;
        }
    }

}
