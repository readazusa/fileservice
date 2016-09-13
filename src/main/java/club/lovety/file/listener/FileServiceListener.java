package club.lovety.file.listener;

import club.lovety.file.common.Constant;
import club.lovety.file.common.ExecuteFileEx;
import club.lovety.file.common.LoadFilePropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * cn.oceansoft.file.listener
 * Created by smc
 * date on 2016/3/15.
 * Email:sunmch@163.com
 */
public class FileServiceListener implements ServletContextListener {

    private static final Logger log = LoggerFactory.getLogger(FileServiceListener.class);

    private static final String LISTENER_FILE_DIR = Constant.FTP_FILE_PATH;

    private String srcPath;

    private String desPath;


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        try {
            LoadFilePropertiesUtil loadFileProperties = LoadFilePropertiesUtil.getInstance("load_file.properties");
            String srcPath = loadFileProperties.getFilePath(LoadFilePropertiesUtil.SRC_PATH_KEY);
            String desPath = loadFileProperties.getFilePath(LoadFilePropertiesUtil.DES_PATH_KEY);
            new Thread(new ExecuteFileEx(srcPath,desPath)).start();
        } catch (Exception e) {
            log.error("读取配置文件错误：");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.debug("文件取消监听");
    }


}
