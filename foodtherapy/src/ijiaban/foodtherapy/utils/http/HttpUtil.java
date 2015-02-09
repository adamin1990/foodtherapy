package ijiaban.foodtherapy.utils.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
/**
 * ���繤����
 */
public class HttpUtil {
	
	private static RequestCache requestCache = null;
	
	/**
	 * ����Ƿ�����������
	 * @param context   ������ 
	 * @return
	 */
	public static boolean checkConnection(Context context) {
		@SuppressWarnings("static-access")
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo != null) {
			return networkInfo.isAvailable();
		}
		return false;
	}
	/**
	 * ����Ƿ�����������
	 * @param context ������
	 * @return
	 */
	public static boolean isWifi(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null && activeNetInfo.getTypeName().equals("WIFI")) {
			return true;
		}
		return false;
	}
	/**
	 * ��ȡ��ַ����
	 * @param url ��ַ
	 * @return  ת�����ַ���
	 * @throws IOException
	 */
	public static String getStringFromUrl(String url) throws IOException {
		
		String data = null;
		boolean iscache = false;
		if(url.contains("http://api.yi18.net/cook/cookclass")||url.contains("http://api.yi18.net/food/bar")||url.contains("http://api.yi18.net/food/menu")){ 
			iscache = true;
			if(requestCache != null){
				data = requestCache.get(url);
				if(data != null){ 
					return data;
				}
			}
		}
		HttpGet get = new HttpGet(url);
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(get); 
		HttpEntity entity = response.getEntity();
		data = EntityUtils.toString(entity, "UTF-8");
		if(requestCache != null&&iscache){
			requestCache.put(url, data);
		}
		return data;
	}
	public static void setRequestCache(RequestCache requestCache) {
		HttpUtil.requestCache = requestCache;
	}  
}
