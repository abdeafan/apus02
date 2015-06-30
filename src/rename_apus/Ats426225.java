package rename_apus;

import java.io.File;
import java.text.SimpleDateFormat;

public class Ats426225 {
    Config_read cr = new Config_read();
    FtpList fl = new FtpList();
    FtpGet fg = new FtpGet();
    FtpPut fp = new FtpPut();
    int num = 0, i = 0, x = 0;
    String mDate, newname = null, newnamecut = null, prefix = null, filenameByData = null;
    First f = new First();
    String ftpserverS = cr.config_read(f.conf_file, "FtpServerSource");
    String ftpserverD = cr.config_read(f.conf_file, "FtpServerDest");
    String userd = cr.config_read(f.conf_file, "UserDest");
    String password = cr.config_read(f.conf_file, "PasswordDest");
    String userS = cr.config_read(f.conf_file, "UserSource");
    String passwordS = cr.config_read(f.conf_file, "PasswordSource");
    String fileS;
    long curTime = System.currentTimeMillis();
    long mlastTime = curTime;

    public void ats426225() {
        for (int k = 0; k < Integer.valueOf(cr.config_read(f.conf_file, "HowMuchDays")); k++) {
            mDate = new SimpleDateFormat("yyMMdd").format(mlastTime);
            for (int x = 0; x < 9; x++) {
                filenameByData = "426225--VBAX_20" + mDate + "_" + x + ".log";
                String file = "426225--VBAX-" + mDate + "000" + x + ".zip";
                String path = "/home/oracle/j2ee/home/cdrs/";
                if (!Boolean.valueOf(cr.config_read(f.conf_file, "Offline"))) {
                    if (fl.ftpList(ftpserverS, path, filenameByData, userS, passwordS) != false) {
                        boolean a = fg.FTPget(ftpserverS, path, filenameByData);
                        if (a) {
                            renameApus(filenameByData);
                            new Ziping().ziping(newname);
                            if (!new FtpList().ftpList(ftpserverD, "sip", file, userd, password)) {
                                if (!Boolean.valueOf(cr.config_read(f.conf_file, "Offline"))) {
                                    fp.FTPput(ftpserverD, newnamecut + ".zip", "sip");
                                }
                            }
                        }
                    }
                }
            }
            mlastTime = mlastTime - 86400000;
        }
        System.out.println("Making files 426225: " + num);
        f.mylog("Makibg files 426225: " + num);
    }


    public void renameApus(String filename) {
        try {
            prefix = filename.substring(22, 23);
            newname = "426225--vbax-" + mDate + "000" + prefix + ".log";
            newnamecut = "426225--vbax-" + mDate + "000" + prefix;
            File file = new File(filename);
            file.renameTo(new File(newname));
            num++;
        } catch (Exception e) {
            System.out.println("---EE--- module 426225 error: " + e.getMessage());
            e.printStackTrace();
            f.mylog("---EE--- module 426225 error: " + e.getMessage());
        }
    }
}
