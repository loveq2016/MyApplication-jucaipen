package com.example.utils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class KeepNetwork {

	public static byte[] networkConnection(String path,
			Map<String, String> parames) {
		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(path);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		if (parames != null && !parames.isEmpty()) {
			for (Entry<String, String> entry : parames.entrySet()) {
				list.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
		}

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
			HttpResponse httpResponse = client.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				byte bs[] = EntityUtils.toByteArray(httpResponse.getEntity());
				return bs;
			} else {
				httpPost.abort();
			}
		} catch (Exception e) {
			client.getConnectionManager().shutdown();// �ͷ�����
			e.printStackTrace();
		}

		return null;
	}


	public static byte[] networkGet(String path, Map<String, String> parames) {
		try {
			HttpClient client = new DefaultHttpClient();
			StringBuffer buff = new StringBuffer(path);
			if (parames != null && parames.size() > 0) {
				buff.append("?");
				for (Entry<String, String> enty : parames.entrySet()) {
					buff.append(enty.getKey());
					buff.append("=");
					buff.append(URLEncoder.encode(enty.getValue(), "UTF-8"));
					buff.append("&");
				}
				buff.deleteCharAt(buff.length() - 1);
			}
			HttpGet Get = new HttpGet(buff.toString());
			HttpResponse httpResponse = client.execute(Get);

			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				byte bs[] = EntityUtils.toByteArray(httpResponse.getEntity());
				return bs;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
