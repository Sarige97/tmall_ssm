package com.sarige.tmall.util;

import javax.servlet.http.HttpSession;

public class FileUtil {

    public static String getRealPath(HttpSession session, String relativePath) {
        return session.getServletContext().getRealPath(relativePath);
    }

}
