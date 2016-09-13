package club.lovety.file.common;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by 念梓  on 2016/9/13.
 * Email:sunmch@163.com
 * TEL:13913534135
 * author: 念梓
 * des:加载文件路径配置文件
 */
public class LoadFilePropertiesUtil {

    public static final String SRC_PATH_KEY = "srcKey";

    public static final String DES_PATH_KEY = "desKey";

    private static final String SRC_FILE_PATH_KEY = "src_file_path";

    private static final String DES_FILE_PATH_KEY = "des_file_path";

    private Map<String, String> propertiesMap = new HashMap<>();


    private static final Logger log = LoggerFactory.getLogger(LoadFilePropertiesUtil.class);

    public static LoadFilePropertiesUtil getInstance(String loadPath) throws  Exception {
        return new LoadFilePropertiesUtil(loadPath);
    }

    private LoadFilePropertiesUtil(String loadPath) throws  Exception {
        loadProperties(loadPath);
    }

    private void loadProperties(String loadPath) throws  Exception {
        Properties properties = new Properties();
        InputStream inputStream = LoadFilePropertiesUtil.class.getClassLoader().getResourceAsStream(loadPath);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("加载配置文件错误: ",e);
            throw new Exception("加载配置文件错误: " + e.getMessage());
        }
        anaProperties(properties);
    }

    /**
     * 解析配置文件
     *
     * @param properties
     */
    private void anaProperties(Properties properties) throws Exception {

        Object src_path_obj = properties.get(SRC_FILE_PATH_KEY);
        if (null == src_path_obj) {
            log.error("配置文件，找不到源文件 key:{}的属性",SRC_FILE_PATH_KEY);
            throw new Exception("配置文件中，找不到key: " + SRC_FILE_PATH_KEY + "的属性");
        }

        String src_path = src_path_obj.toString();
        if (StringUtils.isBlank(src_path)) {
            log.error("配置文件,key:{}的值为空",SRC_FILE_PATH_KEY);
            throw new Exception("配置文件中，key: " + SRC_FILE_PATH_KEY + "的值不能为空");
        }

        Object des_path_obj = properties.get(DES_FILE_PATH_KEY);
        if (null == des_path_obj) {
            log.error("配置文件中,找不到源key:{}的属性",DES_FILE_PATH_KEY);
            throw new Exception("配置文件中，找不到key: " + DES_FILE_PATH_KEY + "的属性");
        }

        String des_path = des_path_obj.toString();
        if (StringUtils.isBlank(des_path)) {
            log.error("配置文件中,key:{}的值不能为空",DES_FILE_PATH_KEY);
            throw new Exception("配置文件中，key: " + DES_FILE_PATH_KEY + "的值不能为空");
        }
        propertiesMap.put(SRC_PATH_KEY, src_path);
        propertiesMap.put(DES_PATH_KEY, des_path);
    }

    public String getFilePath(String filePathKey) {
        return propertiesMap.get(filePathKey);
    }

}
