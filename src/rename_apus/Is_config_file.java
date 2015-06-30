package rename_apus;

import java.io.File;

/**
 * Created by ReutskyVV on 24.11.2014.
 */
public class Is_config_file {
    public boolean is_config_file(String conf_file) {
        File file = new File(conf_file);
        return file.exists();
    }

}
