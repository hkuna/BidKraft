package remove.classes.from.the.pkg;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.util.Log;
 
 public class JsonClass {
	static String tag ="json class"; 
	//ArrayList<String> requestor_id,category_id , description , date_needed, location , zipcode ,selected_offer;
	public static  ArrayList<RequestorItems> requestdatastruct;

	
	public static boolean jsonParseRequest(String response)
	{
		 requestdatastruct = new ArrayList<RequestorItems>();
		Log.d(tag,"request json string"+response);
		
		try {
			JSONObject jObject = new JSONObject(response);
			 JSONArray reqarray = jObject.getJSONArray("results");
			 
			 for(int i=0; i<reqarray.length(); i++)
			 {
				 
				 JSONObject loopobj  = reqarray.getJSONObject(i);
				// loopobj.get("requestor");
				// requestdatastruct.add(i, new RequestorItems(loopobj.getString("requester"), loopobj.getString("category"), loopobj.getString("description"), loopobj.getString("needed"), loopobj.getString("location"), loopobj.getString("zipcode")));
				/* Log.d("tag","json object data " +requestdatastruct.get(i));
				 final RequestorItems o = requestdatastruct.get(i);
				 Log.d(tag," json category" +o.requestor_id());
				Log.d(tag," json category" +o.category_id());
				Log.d(tag," json category" +o.description());
				Log.d(tag," json category" +o.date_needed());
				Log.d(tag," json category" +o.location());
				Log.d(tag," json category" +o.zipcode());
				Log.d(tag," json category" +o.selected_offer());*/
			 }
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		
		return true;
		
		
	}
	
	
	 
}

