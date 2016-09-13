package club.lovety.file.controller;

import club.lovety.file.common.Constant;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * cn.oceansoft.file.controller
 * Created by smc
 * date on 2016/3/15.
 * Email:sunmch@163.com
 */
@Controller
@RequestMapping("file")
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);
    @RequestMapping("{fileDir}/{fileName}")
    @ResponseBody
    public void view(@PathVariable("fileDir") String fileDir,@PathVariable("fileName") String fileName,HttpServletRequest request, HttpServletResponse response)  throws IOException {
        response.setCharacterEncoding("UTF-8");
        log.debug("转译前的fileName:{}",fileName);
        response.setHeader("Content-Disposition","attachment; filename="+new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+"");
        //获取响应报文输出流对象
        ServletOutputStream out =response.getOutputStream();
        FileUtils.copyFile(new File(Constant.FTP_FILE_PATH + fileDir + "/" + fileName), out);
        out.flush();
        out.close();

    }

}
