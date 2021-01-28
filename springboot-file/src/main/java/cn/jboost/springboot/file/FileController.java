package cn.jboost.springboot.file;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author ronwxy
 * @Date 2021/1/27 15:33
 * @Version 1.0
 */
@RestController
@RequestMapping("file")
public class FileController {

    @PostMapping(value = "upload")
    public Map upload(@RequestParam MultipartFile file) throws IOException {
        String filePath = FileUtil.fileSave("D:\\upload\\", "file", file);
        Map<String,Object> map = new HashMap<>();
        map.put("filePath",filePath);
        return map;
    }

    @CrossOrigin
    @GetMapping("download")
    public void download(@RequestParam("path") String path, HttpServletRequest req, HttpServletResponse res) {
        String absolutPath = "D:\\upload\\"+ path;
        // 重要，需要设置此值，否则下载后打开文件会提示文件需要修复
        File file = new File(absolutPath);
        if (file.exists()) {
            byte[] buffer = new byte[1024];
            OutputStream os;
            try (FileInputStream fis= new FileInputStream(file);
                 BufferedInputStream bis = new BufferedInputStream(fis)) {
                os = res.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1){
                    os.write(buffer,0,i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
