package naverTrend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Program {
	private static String clientId;
	private static String clientSecret;
	private static String apiURL;
	private static String body;
	private static HttpURLConnection con;
	private static JSONObject json;
	private static JSONArray jarray;
	private static JSONParser jsonParser;
	private static InputData data;
	
	public static void main(String[] args) throws Exception {
		makeJsonFile();
//		parseLocalJson();
		init();
		connect();
		response();
	}

	@SuppressWarnings("unchecked")
	private static void makeJsonFile() {
		JSONObject obj = new JSONObject();
		JSONObject date = new JSONObject();
		JSONArray jarray = new JSONArray();

		
		data = new InputData();
		
		//mode - put the date info predefined.
		data.init(true);
		
		date.put("startDate", data.getStartDate());
		date.put("endDate", data.getEndDate());
		date.put("timeUnit", data.getTimeUnit());
		obj.putAll(date);
		
		for(int i = 0; i < data.getCnt(); i++) {
			JSONObject keyword = new JSONObject();
			JSONArray tempArray = new JSONArray();
			
			keyword.put("groupName", data.getGroupName(i));
			for(int j = 0; j < data.getKeyCnt(i); j++) {
				tempArray.add(data.getKeyWords(i, j));
			}
			if(data.getKeyCnt(i) == 0)
				tempArray.add(data.getGroupName(i));
				
			keyword.put("keywords", tempArray);
			jarray.add(keyword);
		}
		
		obj.put("keywordGroups", jarray);
		System.out.println(obj);
		body = obj.toString(); //fault toJsonString
	}

	private static void parseLocalJson() {
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader("src/test.json"));
			JSONObject jobj = (JSONObject) obj;
			
			System.out.println(jobj.get("startDate"));
			System.out.println(jobj.get("endDate"));
			System.out.println(jobj.get("timeUnit"));
			
			JSONArray keywordGroup = (JSONArray) jobj.get("keywordGroups");
			for(int i = 0; i < keywordGroup.size(); i++) {
				JSONObject result = (JSONObject) keywordGroup.get(i);
				System.out.println(result.get("groupName"));
				
				JSONArray keywords = (JSONArray) result.get("keywords");
				for(int j = 0; j < keywords.size(); j++) {
					System.out.println(keywords.get(j));
				}
			}
			
			body = jobj.toString(); //fault toJsonString
			System.out.println(body);
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	private static void response() throws Exception {
		int responseCode = con.getResponseCode();
		BufferedReader br;
		if (responseCode == 200) { // 정상 호출
			br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		} else { // 에러 발생
			br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
		}

		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = br.readLine()) != null) {
			response.append(inputLine);
		}
		br.close();
		
		System.out.println(response.toString());
		BufferedWriter writer = new BufferedWriter(new FileWriter("src/result.json"));
		writer.write(response.toString());
		writer.close();
	}

	private static void connect() throws Exception {
		URL url = new URL(apiURL);

		con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("X-Naver-Client-Id", clientId);
		con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		con.setRequestProperty("Content-Type", "application/json");
		con.setDoOutput(true);

		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		byte[] bytes = new byte[1000];
		bytes = body.getBytes();
		wr.write(bytes);
		wr.flush();
		wr.close();

	}

	private static void init() {
		clientId = "qcb_heUiOwEgQEvETpk8";// 애플리케이션 클라이언트 아이디값";
		clientSecret = "HopZWjJ6Qf";// 애플리케이션 클라이언트 시크릿값";

		apiURL = "https://openapi.naver.com/v1/datalab/search";

	}
}
