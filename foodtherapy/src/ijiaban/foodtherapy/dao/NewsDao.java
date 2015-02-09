package ijiaban.foodtherapy.dao;

import ijiaban.foodtherapy.beans.Menu;
import ijiaban.foodtherapy.beans.NewDetail;
import ijiaban.foodtherapy.beans.NewsList;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class NewsDao {
	/**
	 * ��ȡ��Ѷ�б�
	 * @param url   http://api.yi18.net/news/list?page=1&limit=10&type=id&id=5
	 * @return
	 */
	public static List<NewsList> getnews(String url){
		Gson gson=new Gson();
		List<NewsList> news=new ArrayList<NewsList>();
	String json;
	try {
		json = CommonDao.getDataFromServer(url);
		JSONObject jb=new JSONObject(json);
	    JSONArray ja=jb.getJSONArray("yi18");
		String jas=ja.toString();
		news=gson.fromJson(jas, new TypeToken<List<NewsList>>(){}.getType());
	} catch (Exception e) {
		e.printStackTrace();
	}
		return news;
		
	}
	/**
	 * ��ȡid�б�
	 * @param pageno
	 * @return
	 */
	public static List<Integer> getids(int pageno){
	String	url="http://api.yi18.net/news/list?limit=20&type=id&&id=5&page="+pageno;
		List<NewsList> news;
		news=getnews(url);
		List<Integer> ids=new ArrayList<Integer>();
		for(int i=0;i<news.size();i++){
			NewsList new1=news.get(i);
		   long	id=new1.getId();
		    ids.add((int) id);
			
		}
		
		return ids;
		
	}
	public static List<NewDetail> getnewsDetail(List<Integer >ids) throws Exception{
		Gson gson=new Gson();
		List<NewDetail> news=new ArrayList<NewDetail>();
		for(int i=0;i<ids.size();i++){
			String	url="http://api.yi18.net/news/show?id="+ids.get(i);
			String json;
			json = CommonDao.getDataFromServer(url);
			JSONObject jb=new JSONObject(json);
			JSONObject jb2=jb.getJSONObject("yi18");
			json=jb2.toString();
			NewDetail detail=new NewDetail();
			detail=gson.fromJson(json, NewDetail.class);
			news.add(detail);
		}
		return news;
	}
public static NewDetail getnewdetail(long id) throws Exception{
	Gson gson=new Gson();
	String url="http://api.yi18.net/news/show?id="+id;
	String json;
	json = CommonDao.getDataFromServer(url);
	JSONObject jb=new JSONObject(json);
	JSONObject jb2=jb.getJSONObject("yi18");
	json=jb2.toString();
	NewDetail detail=new NewDetail();
	detail=gson.fromJson(json, NewDetail.class);
	return detail;
	
}
}
