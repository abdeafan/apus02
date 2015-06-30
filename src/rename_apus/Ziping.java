package rename_apus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Ziping {

    public boolean ziping(String mfile) {
        String withoutExten = mfile.substring(0, mfile.length() - 4);
        try {
            ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(withoutExten + ".zip"));
            FileInputStream fis = new FileInputStream(mfile);
            ZipEntry entry1 = new ZipEntry(mfile);
            zout.putNextEntry(entry1);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
            zout.close();
            fis.close();
            File mf = new File(mfile);
            mf.delete();
        } catch (Exception e) {
            System.out.println("---EE--- Zip module error: " + e.getMessage());
            e.printStackTrace();
            new First().mylog("---EE--- Zip module error: " + e.getMessage());
            return false;
        }
        System.out.println("zipping " + withoutExten + ".zip" + " success");
        new First().mylog("zipping " + withoutExten + ".zip" + " success");
        return true;
    }

}
