package ijiaban.foodtherapy.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class CommonDao { 
	/**
	 * ��ȡjson�ַ���
	 * @param path
	 *            ��ַ
	 * @return json
	 * @throws Exception
	 *             �������Ӵ���
	 */
	public static String getDataFromServer(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setAllowUserInteraction(true);
		conn.setAllowUserInteraction(true);
		conn.setConnectTimeout(5 * 1000);// �������ӳ�ʱ
		conn.setRequestMethod("GET");// ��get��ʽ��������
		if (conn.getResponseCode() != 200)
			throw new RuntimeException("����urlʧ��");
		InputStream is = conn.getInputStream();// �õ����緵�ص�������
		String result = readData(is, "utf-8");
		conn.disconnect();
		return result;
	} 
	/**
	 * ��������������ȡ�ַ���
	 * @param inSream ����������
	 * @param charsetName ���ݵı����ʽ
	 * @return �ַ���
	 * @throws IOException IO�쳣
	 */
	public static String readData(InputStream inSream, String charsetName) throws IOException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = inSream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();
		outStream.close();
		inSream.close();
		return new String(data, charsetName);
	}  
}
