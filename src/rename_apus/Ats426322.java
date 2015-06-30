package rename_apus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Ats426322 {
    Config_read cr = new Config_read();
    FtpList4263222 flSrc = new FtpList4263222();
    FtpList4263222 flDst = new FtpList4263222();
    FtpGet fg = new FtpGet();
    FtpPut fp = new FtpPut();
    Ziping zp = new Ziping();
    int num = 0;
    String date_and_time_Src, mDate, newname = null, prefix = null, newnamezip = null;
    First f = new First();
    String ftpserverS = cr.config_read(f.conf_file, "FtpServerSource");
    String ftpserverD = cr.config_read(f.conf_file, "FtpServerDest");
    String userS = cr.config_read(f.conf_file, "UserSource");
    String passwordS = cr.config_read(f.conf_file, "PasswordSource");
    String userD = cr.config_read(f.conf_file, "UserDest");
    String passwordD = cr.config_read(f.conf_file, "PasswordDest");
    long curTime = System.currentTimeMillis();
    long mlastTime = curTime;
    List<String> list_Src = new ArrayList<>();
    List<String> list_Dst = new ArrayList<>();

    
    public boolean ats426322() {
        list_Src = flSrc.ftpList(ftpserverS, userS, passwordS);
        list_Dst = flDst.ftpList(ftpserverD, userD, passwordD);

        if (list_Dst.isEmpty()) {
            GetAll();
            return true;
        }

        for (int k = 0; k < Integer.valueOf(cr.config_read(f.conf_file, "HowMuchDays")); k++) {
            mDate = new SimpleDateFormat("yyyyMMdd").format(mlastTime);

            for (String name_Src : list_Src) {
                if (name_Src.length() < 22) continue;
                if (!(name_Src.substring(22, 25).equals("ama"))) continue;
                date_and_time_Src = name_Src.substring(7, 17);
                if (date_and_time_Src.contains(mDate.substring(2))) {
                    if (!(list_Dst.contains("4263222-si2k-" + date_and_time_Src + ".zip"))) GetRenameZipPut(name_Src);
                    list_Dst.add("4263222-si2k-" + date_and_time_Src + ".zip");
                }
            }

            mlastTime = mlastTime - 86400000;
            System.out.println("files on day: " + mDate);
        }
        System.out.println("Making files 4263222: " + num);
        f.mylog("Making files 4263222: " + num);
        return false;
    }


    public boolean GetRenameZipPut(String name_Src) {
        if (!fg.FTPget(ftpserverS, "", name_Src)) return false;
        if (!renameApus(name_Src)) return false;
        if (!zp.ziping(newname)) return false;
        fp.FTPput(ftpserverD, newnamezip, "/");
        return true;
    }

    public boolean renameApus(String filename) {
        try {
            prefix = filename.substring(7, 17);
            newname = "4263222-si2k-" + prefix + ".ama";
            newnamezip = "4263222-si2k-" + prefix + ".zip";
            File file = new File(filename);
            if (!file.renameTo(new File(newname))) return false;
            num++;
        } catch (Exception e) {
            System.out.println("---EE--- module 4263222 error: " + e.getMessage());
            e.printStackTrace();
            f.mylog("---EE--- module 4263222 error: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean GetAll() {
        for (int k = 0; k < Integer.valueOf(cr.config_read(f.conf_file, "HowMuchDays")); k++) {
            mDate = new SimpleDateFormat("yyyyMMdd").format(mlastTime);

            for (String name_Src : list_Src) {
                if (name_Src.length() < 22) continue;
                if (!(name_Src.substring(22, 25).equals("ama"))) continue;
                if (!(name_Src.contains(mDate))) continue;
                GetRenameZipPut(name_Src);
            }

            mlastTime = mlastTime - 86400000;
        }
        return true;
    }
}