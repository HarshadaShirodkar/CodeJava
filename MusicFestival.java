import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MusicFestival {

	public static void main(String[] args) throws Exception {
		   JSONParser jsonPaser = new JSONParser();
        try {
        	//This code for getting the json from the url but commented below section *********
		
		/*
			 * String inline= ""; URL url = new URL("URL"); HttpURLConnection conn =
			 * (HttpURLConnection)url.openConnection(); conn.setRequestMethod("GET");
			 * conn.connect(); int responsecode = conn.getResponseCode();
			 * 
			 * 
			 * if(responsecode == 200) { Scanner sc = new Scanner(url.openStream());
			 * 
			 * while(sc.hasNext()) { inline+=sc.nextLine(); } System.out.println(inline);
			 * sc.close(); } JSONObject jobj = (JSONObject)jsonPaser.parse(inline);
			 */
		
		
        	if(new File("src/FestivalData.json").length()> 0 
        			&& new File("src/FestivalData.json").exists()) {        	
        		
        		Object obj = jsonPaser.parse(new FileReader("src/FestivalData.json"));
        	if(obj != null && !obj.equals(null)) {
        	
        	
        	 JSONObject jsonObject= (JSONObject) obj;
        	 JSONArray musicFestArr=  (JSONArray) jsonObject.get("musicFestival");
        	
			  List<JSONObject> list = new ArrayList<JSONObject>(); 
			  HashMap<Integer, String> sortedMap = new HashMap<Integer, String>();
			  if(!musicFestArr.isEmpty()) {
			  
			  
			  for (int i = 0; i < musicFestArr.size(); i++) {
				  JSONObject JSONObject = (org.json.simple.JSONObject) musicFestArr.get(i);
				  String recordLabelname = (String) JSONObject.get("recordLabelname");
				  sortedMap.put(0, recordLabelname+"\n");
				  
				  List<JSONObject> str = (List<org.json.simple.JSONObject>) JSONObject.get("bands");
				  
				  if(!str.isEmpty()) {
				  for (JSONObject s: str) {
					  if(s.get("name") != null) {
					  String name = (String) s.get("name");
					  	sortedMap.put(1, name+"\n");
					  	
					  }
					  if(s.get("bandName") != null) {
					  List<String> str1=  (List<String>) s.get("bandName");
					  Collections.sort(str1);
					  
					  for(int k=0; k< str1.size(); k++) {
						  
						  if(str1.get(k) != null) {
							  sortedMap.put(k+2, new String());
							  
							  
						  }
						  sortedMap.put(k+2, str1.get(k)+"\n");;
						  
					  }
					  List<String> targetList = new ArrayList<>(sortedMap.values());
					  System.out.println(targetList);
					  }
				  }
				  }
				}
			  }
        	}
        }
        	}
            catch (FileNotFoundException e) {
            	e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }catch (ParseException e) {
	            e.printStackTrace();
	        }
	}
	
	
	
	
}
