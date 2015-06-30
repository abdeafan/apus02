package rename_apus;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;

public class First {

    public String conf_file = "config.cfg";

    public void mylog(String text) {
        long curTime = System.currentTimeMillis();
        String curStringDate = new SimpleDateFormat("dd.MM.yyyy kk:mm:ss").format(curTime);
        try {
            PrintStream printStream = new PrintStream(new FileOutputStream("log.log", true), true);
            printStream.println(curStringDate + ": " + text);
            printStream.close();
        } catch (Exception e) {
            System.out.println("---EE--- log file error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
