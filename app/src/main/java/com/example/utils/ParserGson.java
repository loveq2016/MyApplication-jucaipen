package com.example.utils;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ParserGson {
	public static String createJsonString(Object value) {
		Gson gson = new Gson();
		String str = gson.toJson(value);
		return str;
	}

	public static List<Object> getList(String json) {
		Gson gson = new Gson();
		List<Object> objects = gson.fromJson(json,
				new TypeToken<List<Object>>() {
				}.getType());
		return objects;

	}
}
