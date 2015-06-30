package rename_apus;


import it.sauronsoftware.ftp4j.FTPClient;

import java.io.File;
/**
class MyTransferListener implements it.sauronsoftware.ftp4j.FTPDataTransferListener {

    public void started() {
        System.out.println("Transfer started");
    }

    public void transferred(int length) {
        System.out.println("Transfered " + length + " bytes");
    }

    public void completed() {
        System.out.println("Transfer completed");
    }

    public void aborted() {
        System.out.println("Transfer aborted");
    }

    public void failed() {
        System.out.println("Transfer failed");
    }
}
**/

public class FtpGet {

    public boolean FTPget(String ftpserver, String path, String mfile) {
        Config_read cr = new Config_read();
        First f = new First();

        String serverAddress = ftpserver;
        String userId = cr.config_read(f.conf_file, "UserSource");
        String password = cr.config_read(f.conf_file, "PasswordSource");

        FTPClient ftp = new FTPClient();
        ftp.setPassive(false);

        try {
            ftp.connect(serverAddress);

            ftp.setType(FTPClient.TYPE_BINARY);

            ftp.login(userId, password);

            File output;
            output = new File(mfile);
            ftp.download(mfile, output);//, new MyTransferListener());

            ftp.disconnect(true);

            System.out.println("ftpget " + mfile + " success");
            f.mylog("ftpget " + mfile + " success");

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return true;
    }
}
