package write;

import java.util.Map;

import org.json.simple.JSONObject;

public class SendObj {
	
	Map<String,Object> data;
	
	public SendObj(Map<String,Object> data) {
		this.data=data;
	}
	
	public String toString() {
		
		JSONObject obj=new JSONObject();    
		obj.putAll(data);  
		return obj.toJSONString();
	}
	

}
