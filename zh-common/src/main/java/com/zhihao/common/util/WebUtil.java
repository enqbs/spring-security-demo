package com.zhihao.common.util;

import com.zhihao.common.constant.Constants;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtil {

    public static void renderString(HttpServletResponse response, int httpStatus, String str) {
        try {
            response.setStatus(httpStatus);
            response.setContentType(Constants.CONTENT_TYPE);
            response.setCharacterEncoding(Constants.CHAR_ENCODE);
            response.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
