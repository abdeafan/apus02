package rename_apus;


import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPFile;

import java.util.ArrayList;
import java.util.List;


public class FtpList4263222 {

    First f = new First();
    List<String> listSrc = new ArrayList<>();

    public List<String> ftpList(String ftpserver, String userId, String password) {

        FTPClient ftp = new FTPClient();
        ftp.setPassive(false);

        try {
            ftp.connect(ftpserver);
            if (!ftp.isConnected()) return listSrc;

            ftp.setType(FTPClient.TYPE_BINARY);

            ftp.login(userId, password);
            if (!ftp.isAuthenticated()) return listSrc;

            FTPFile[] listNames = ftp.list();
            for (FTPFile list : listNames) {
                if (list.getType() == 0) listSrc.add(list.getName());
            }

            ftp.disconnect(true);

            System.out.println("FtpList " + ftpserver + " success");

        } catch (Exception ex) {
            System.out.println("---EE--- FTPlist4263222 module error: " + ex.getMessage());
            ex.printStackTrace();
            f.mylog("---EE--- FTPlist4263222 module error: " + ex.getMessage());
            return listSrc;
        }

        return listSrc;
    }
}
