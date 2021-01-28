package cn.jboost.springboot.file;


import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

public class FileUtil extends cn.hutool.core.io.FileUtil {
    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);
    private static final int GB = 1073741824;
    private static final int MB = 1048576;
    private static final int KB = 1024;
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    public FileUtil() {
    }

    public static File toFile(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        String prefix = "." + getExtensionName(fileName);
        File file = null;

        try {
            file = File.createTempFile(IdUtil.simpleUUID(), prefix);
            multipartFile.transferTo(file);
        } catch (IOException var5) {
            log.error("fail to transfer multipartFile[{}] to oss", fileName, var5);
        }

        return file;
    }

    public static void deleteFile(File... files) {
        File[] var1 = files;
        int var2 = files.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            File file = var1[var3];
            if (file.exists()) {
                file.delete();
            }
        }

    }

    public static String getExtensionName(String filename) {
        if (filename != null && filename.length() > 0) {
            int dot = filename.lastIndexOf(46);
            if (dot > -1 && dot < filename.length() - 1) {
                return filename.substring(dot + 1);
            }
        }

        return filename;
    }

    public static String getFileNameNoEx(String filename) {
        if (filename != null && filename.length() > 0) {
            int dot = filename.lastIndexOf(46);
            if (dot > -1 && dot < filename.length()) {
                return filename.substring(0, dot);
            }
        }

        return filename;
    }

    public static String getSize(int size) {
        String resultSize = "";
        if (size / 1073741824 >= 1) {
            resultSize = DF.format((double) ((float) size / 1.07374182E9F)) + "GB   ";
        } else if (size / 1048576 >= 1) {
            resultSize = DF.format((double) ((float) size / 1048576.0F)) + "MB   ";
        } else if (size / 1024 >= 1) {
            resultSize = DF.format((double) ((float) size / 1024.0F)) + "KB   ";
        } else {
            resultSize = size + "B   ";
        }

        return resultSize;
    }

    /**
     * 获取项目根路径
     *
     * @return
     */
    public static String getResourceBasePath() {
        // 获取跟目录
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            // nothing to do
        }
        if (path == null || !path.exists()) {
            path = new File("");
        }
        String pathStr = path.getAbsolutePath();
        // 如果是在eclipse中运行，则和target同级目录,如果是jar部署到服务器，则默认和jar包同级
        pathStr = pathStr.replace("\\target\\classes", "");
        return pathStr;
    }

    /**
     * 保证拷贝的文件的目录一定要存在
     *
     * @param filePath
     *            文件路径
     */
    public static void ensureDirectory(String filePath) {
        if (StrUtil.isBlank(filePath)) {
            return;
        }
        //将符号“\\”和“\”替换成“/”,有时候便于统一的处理路径的分隔符,避免同一个路径出现两个或三种不同的分隔符
        filePath = filePath.replace("\\", "/").replace("\\\\", "/");
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 上传文件保存
     * @param rootPath
     * @param bizPath
     * @param mf
     * @return
     * @throws IOException
     */
    public static String fileSave(String rootPath, String bizPath, MultipartFile mf) throws IOException {
        log.info("--------文件正在保存---------" );
        String ctxPath = rootPath;
        File file = new File(ctxPath + File.separator + bizPath);
        if (!file.exists()) {
            file.mkdirs();// 创建文件根目录
        }
        String orgName = mf.getOriginalFilename();// 获取文件名
        String filePrefixName = orgName.substring(0, orgName.lastIndexOf(".")) + "_" + System.currentTimeMillis();
        String fileName =  filePrefixName + orgName.substring(orgName.indexOf("."));
        String savePath = file.getPath() + File.separator + fileName;
        File savefile = new File(savePath);
        FileCopyUtils.copy(mf.getBytes(), savefile);
        String dbpath = bizPath + File.separator + fileName;
        if (dbpath.contains("\\")) {
            dbpath = dbpath.replace("\\", "/");
        }
        log.info("--------文件成功保存至" + dbpath);
        return dbpath;
    }

    /**
     * 删除指定的文件
     *
     * @param strFileName 指定绝对路径的文件名
     * @return 如果删除成功true否则false
     */
    public static boolean delete(String strFileName) {
        File fileDelete = new File(strFileName);
        if (!fileDelete.exists() || !fileDelete.isFile()) {
            log.info("错误: " + strFileName + "不存在!");
            return false;
        }
        log.info("--------成功删除文件---------" + strFileName);
        return fileDelete.delete();
    }

}
