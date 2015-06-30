package rename_apus;

public class Mmain {

    public static void main(String[] args) {
        Is_config_file icf = new Is_config_file();
        Config_read cr = new Config_read();
        Config_create cc = new Config_create();
        First f = new First();

        String conf_file = "config.cfg";
        if (!icf.is_config_file(conf_file)) {
            cc.config_create(conf_file);
        }
        switch (cr.config_read(conf_file, "AtsMode")) {
            case "426226":
                new Ats426226().ats426226();
                break;
            case "426225":
                new Ats426225().ats426225();
                break;
            case "426223":
                new Ats426223().ats426223();
                break;
            case "426322":
                new Ats426322().ats426322();
                break;
            default:
                System.out.println("No ATS is processed. Check config file.");
                f.mylog("No ATS is processed. Check config file.");
        }
    }

}
