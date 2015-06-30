package rename_apus;


import org.apache.commons.net.ftp.FTPClient;


public class FtpList {
    String s1;
    public boolean ftpList(String ftpserver, String path, String file, String userId, String password) {

        First f = new First();
        try {
            String serverAddress = ftpserver;
            FTPClient ftp = new FTPClient();
            ftp.connect(serverAddress);
            ftp.enterLocalActiveMode();
            //ftp.enterLocalPassiveMode();
            if (!ftp.login(userId, password)) {
                ftp.logout();
                return false;
            }
            String[] listNames = ftp.listNames(path);
            ftp.logout();
            ftp.disconnect();
            for (String s : listNames) {
                if (!s.equalsIgnoreCase(".")) {
                    if (!s.equalsIgnoreCase("..")) {
                        s1 = s.substring(s.length() - 27, s.length());
                        if (s1.equalsIgnoreCase(file)) return true;
                    }
                }
            }
            return false;
        } catch (Exception ex) {
            System.out.println("---EE--- FTPlist module error: " + ex.getMessage());
            ex.printStackTrace();
            f.mylog("---EE--- FTPlist module error: " + ex.getMessage());
            f.mylog("---EE--- FTPlist module error: " + s1);
            return false;
        }

    }

}
