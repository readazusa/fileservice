package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * test
 * Created by smc
 * date on 2016/3/15.
 * Email:sunmch@163.com
 */
public class OneTest {
    private static final String LISTENER_FILE_DIR ="G:"+File.separator +"file"+File.separator+"listener/";
    public static void main(String[] args) {
        File file = new File("E:"+ File.separator+"files"+File.separator+"DSC04002.JPG");

        try {
            FileInputStream inputStream = new FileInputStream(file);
            File outFile = new File(LISTENER_FILE_DIR+"好的_2014-12-21.JPG");

            FileOutputStream outputStream = new FileOutputStream(outFile);
            int a = 0 ;
            while((a = inputStream.read()) != -1){
                outputStream.write(a);
                System.out.println("======sasdasd==========");
            }
            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
