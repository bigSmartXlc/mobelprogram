package com.hbtcsrzzx.utils;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * web工具类
 */
public class WebUtils {

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes reqAttr = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
		if (reqAttr != null) {
			return reqAttr.getRequest();
		}
		return null;
	}
	
	public static String getBody(InputStream input) throws IOException {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = input.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}
		return out.toString();
	}
	
	/*public static HttpServletResponse getResponse() {
		ServletRequestAttributes reqAttr = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
		if (reqAttr != null) {
			return reqAttr.getResponse();
		}
		return null;
	}*/
}
