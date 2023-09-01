package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class JsonParseUtil {

	public static HttpServletResponse response;
	
//	public static ResponseUtil ofResponse(HttpServletResponse response) {
//		ResponseUtil.response = response;
//	}
	
	
	public static Map<String, Object> toMap(InputStream inputStream) {
		Gson gson = new Gson();
		StringBuilder jsonData = new StringBuilder("");
		BufferedReader brfferedReader = null;
		if(inputStream == null) {
			return null;
		}
		brfferedReader =
				new BufferedReader(new InputStreamReader(inputStream));
		while(true) {
			try {
				String data = brfferedReader.readLine();
				if(data == null) {
					break;
				}
				jsonData.append(data);
			} catch (Exception e) {
				return null;
			}
		}
		return gson.fromJson(jsonData.toString(), Map.class);
	}
	
	public static String toJson(Object object) {
		Gson gson = new Gson();	
		return gson.toJson(object);
	}
	

	
}
