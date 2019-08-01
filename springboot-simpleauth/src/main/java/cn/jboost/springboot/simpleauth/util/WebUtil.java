package cn.jboost.springboot.simpleauth.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

public class WebUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public static void outputJsonString(ApiResponse data,
                                        HttpServletResponse response) {
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setHeader("Pragma", "no-cache");
        OutputStream out;
        try {
            out = response.getOutputStream();
            IOUtils.write(mapper.writeValueAsString(data), out, "UTF-8");
            out.flush();
            out.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}