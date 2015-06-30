package rename_apus;

import java.io.File;
import java.text.SimpleDateFormat;


public class Ats426226 {
    Config_read cr = new Config_read();
    FtpGet fg = new FtpGet();
    FtpPut fp = new FtpPut();
    int num = 0, i = 0, x = 0;
    String mf, newname = null, newnamecut = null, prefix = null, filenameByData = null;
    First f = new First();
    String ftpserverS = cr.config_read(f.conf_file, "FtpServerSource");
    String ftpserverD = cr.config_read(f.conf_file, "FtpServerDest");
    long curTime = System.currentTimeMillis();
    long mlastTime = curTime;
    String userId = cr.config_read(f.conf_file, "UserDest");
    String password = cr.config_read(f.conf_file, "PasswordDest");

    public void ats426226() {
        for (int k = 0; k < Integer.valueOf(cr.config_read(f.conf_file, "HowMuchDays")); k++) {
            String mDate = new SimpleDateFormat("yyMMdd").format(mlastTime);
            for (int x = 0; x < 5; x++) {
                filenameByData = mDate + "0" + x + ".cal";
                String path = "ELSIS\\BRBLAP\\Poll\\CALLS0" + x + "\\";
                if (!Boolean.valueOf(cr.config_read(f.conf_file, "Offline"))) {
                    boolean a = fg.FTPget(ftpserverS, path, filenameByData);
                    if (a) {
                        renameApus(filenameByData);
                        new Ziping().ziping(newname);
                        new File(newnamecut + ".txt").delete();
                        if (!new FtpList().ftpList(ftpserverD, "42622_6", newnamecut + ".zip", userId, password)) {
                            if (!Boolean.valueOf(cr.config_read(f.conf_file, "Offline"))) {
                                fp.FTPput(ftpserverD, newnamecut + ".zip", "42622_6");
                            }
                        }
                    }
                }
            }
            mlastTime = mlastTime - 86400000;
        }
        System.out.println("Making files 426226: " + num);
        f.mylog("Makibg files 426226: " + num);
    }


    public void renameApus(String filename) {
        try {
            mf = filename.substring(filename.length() - 6, filename.length());
            prefix = filename.substring(filename.length() - 6, 8);
            newname = "426226--khab-" + filename.substring(0, 6) + "00" + prefix + ".txt";
            newnamecut = "426226--khab-" + filename.substring(0, 6) + "00" + prefix;
            File file = new File(filename);
            file.renameTo(new File(newname));
            num++;
        } catch (Exception e) {
            System.out.println("---EE--- module 426226 error: " + e.getMessage());
            e.printStackTrace();
            f.mylog("---EE--- module 426226 error: " + e.getMessage());
        }
    }
}
