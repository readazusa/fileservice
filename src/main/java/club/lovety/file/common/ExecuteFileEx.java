package club.lovety.file.common;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.*;

/**
 * Created by 念梓  on 2016/9/13.
 * Email:sunmch@163.com
 * TEL:13913534135
 * author: 念梓
 * des:监听文件处理类
 */
public class ExecuteFileEx implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(ExecuteFileEx.class);

    private String srcPath;

    private String desPath;

    public ExecuteFileEx(String srcPath, String desPath) {
        this.desPath = desPath;
        this.srcPath = srcPath;
    }

    @Override
    public void run() {
        log.info("开始文件监听--------{} 文件", srcPath);
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path dir = Paths.get(srcPath);
            dir.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_CREATE);   //只读取已经创建完成的文件
            while (true) {
                WatchKey key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    log.info("[{}]发送了[{}]事件", event.context(), event.kind());
                    WatchEvent.Kind<?> kind = event.kind();
                    log.info("鉴定类型: {}", kind);
                    if (kind == StandardWatchEventKinds.OVERFLOW) {
                        continue;
                    }
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path fileKind = ev.context();
                    File file = fileKind.toFile();
                    try {
                        String fileName = file.getName();
                        if (fileName.indexOf("topwalktmp") < 0) {   //由于网闸的ftp传输会生成.topwalktmp的临时文件，所以此文件不读取
                            int indexLast = fileName.lastIndexOf("_") + 1;
                            String dateDir = fileName.substring(indexLast);
                            log.info("dataDi文件:{}", dateDir);
                            int index = dateDir.lastIndexOf(".");
                            log.info("获取日期的长度: {}", index);
                            log.info("父文件夹目录:{}", desPath + dateDir.substring(0, index));
                            File parentFile = new File(desPath + dateDir.substring(0, index));
                            if (!parentFile.exists()) {
                                parentFile.mkdirs();
                            }
                            File srcFile = new File(srcPath + fileName);
                            if (srcFile.renameTo(srcFile)) {
                                FileUtils.copyFile(new File(srcPath + fileName), new File(parentFile, fileName));
                                if (srcFile.delete()) {
                                    log.info("删除源文件: {} 成功", fileName);
                                }
                            }
                        } else {
                            log.info("读取中间文件: {}", fileName);
                        }
                    } catch (Exception e) {
                        log.error("文件:{}同步失败: 失败原因:{}", file.getName(), e.getMessage());
                    }
                }
                if (!key.reset()) {
                    break;
                }
            }
        } catch (Exception ex) {
            log.error("监听文件路径:{} 失败", srcPath);
            log.error("监听文件失败,错误信息 ", ex);
        }
    }
}
