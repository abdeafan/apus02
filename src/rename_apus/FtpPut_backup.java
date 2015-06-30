package rename_apus;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;


public class FtpPut_backup {

    public boolean FTPput(String ftpserver, String fileToFTP, String mPath) {
        First f = new First();
        Config_read cr = new Config_read();
        try {
            String serverAddress = ftpserver;
            String userId = cr.config_read(f.conf_file, "UserDest");
            String password = cr.config_read(f.conf_file, "PasswordDest");
            FTPClient ftp = new FTPClient();
            ftp.connect(serverAddress);
            ftp.enterLocalPassiveMode();
            if (!ftp.login(userId, password)) {
                ftp.disconnect();
                return false;
            }
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            FileInputStream input = new FileInputStream(fileToFTP);
            ftp.storeFile(mPath + "/" + fileToFTP, input);
            input.close();
            ftp.logout();
            ftp.disconnect();
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