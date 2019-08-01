package cn.jboost.springboot.simpleauth.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse implements Serializable {

    private static int SUCCESS_CODE = 0;
    private static int FAILED_CODE = 1;
    private static String SUCCESS_MESSAGE = "操作成功！";

    private int code;
    private String message;
    private Object data;

    public static ApiResponse success(Object data) {
        return new ApiResponse(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static ApiResponse failed(String errorMessage) {
        return new ApiResponse(FAILED_CODE, errorMessage, null);
    }

}
