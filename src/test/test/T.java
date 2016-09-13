package test;

import java.io.File;

/**
 * test
 * Created by smc
 * date on 2016/3/16.
 * Email:sunmch@163.com
 */
public class T {
    public static void main(String[] args) {
        String str = "2014.jpg";
        int index = str.lastIndexOf("_");
        String s = str.substring(index+1);
        System.out.println(s);
//        if (fileNames.length > 1) {
//            String dateDir = fileNames[1];
//            int index = dateDir.lastIndexOf(".");
//            String s = dateDir.substring(0, index);
//            File parentFile = new File("/asd/"+ dateDir.substring(0,index));
////            File parentFile = new File(path + dateDir.substring(0, index));
//            System.out.println(s);
//        }
    }
}
