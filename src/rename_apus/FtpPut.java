package rename_apus;

import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPClient;

import java.io.File;
import java.io.FileInputStream;


public class FtpPut {

    public boolean FTPput(String ftpserver, String fileToFTP, String mPath) {
        First f = new First();
        Config_read cr = new Config_read();
        String serverAddress = ftpserver;
        String userId = cr.config_read(f.conf_file, "UserDest");
        String password = cr.config_read(f.conf_file, "PasswordDest");

        FTPClient ftp = new FTPClient();
        ftp.setPassive(false);

        try {
            ftp.connect(serverAddress);
            if (!ftp.isConnected()) return false;
            ftp.setType(FTPClient.TYPE_BINARY);
            ftp.login(userId, password);
            if (!ftp.isAuthenticated()) {
                ftp.disconnect(true);
                return false;
            }

            //FileInputStream input = new FileInputStream(fileToFTP);
            //ftp.storeFile(mPath + "/" + fileToFTP, input);
            File input = new File(fileToFTP);
            ftp.upload(input);
            //input.close();
            ftp.disconnect(true);
        } catch (Exception ex) {
            System.out.println("---EE--- FTPput module error: " + ex.getMessage());
            ex.printStackTrace();
            f.mylog("---EE--- FTPput module error: " + ex.getMessage());
            return false;
        }
        System.out.println("ftpput " + fileToFTP + " success");
        System.out.println(" ");
        f.mylog("ftpput " + fileToFTP + " success");
        f.mylog(" ");
        return true;
    }


}