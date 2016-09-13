package club.lovety.file.servlet;

import club.lovety.file.common.Constant;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * cn.oceansoft.file.servlet
 * Created by smc
 * date on 2016/3/15.
 * Email:sunmch@163.com
 */
@WebServlet(name="file", urlPatterns="/servlet/file")
public class FileServlet  extends HttpServlet {

    private static final Logger log  = LoggerFactory.getLogger(FileServlet.class);

    /**
     * 请求是get方式
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        String fileDir = request.getParameter("dir");
        String fileName = request.getParameter("fileName");
        log.debug("转译前的fileName:{}",fileName);
        response.setHeader("Content-Disposition","attachment; filename="+new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+"");
        //获取响应报文输出流对象
        ServletOutputStream out =response.getOutputStream();
        FileUtils.copyFile(new File(Constant.FTP_FILE_PATH+fileDir+"/"+fileName),out);
        out.flush();
        out.close();
    }
    /**
     * 请求是post方式
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

    }
}
