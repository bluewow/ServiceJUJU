package data;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataGetter {

	public static void main(String[] args) {
		System.out.println("TEST - Naver Trend Data Get");
		String clientId = "qcb_heUiOwEgQEvETpk8";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "HopZWjJ6Qf";// 애플리케이션 클라이언트 시크릿값";
		try {
			String apiURL = "https://openapi.naver.com/v1/datalab/search";
			String body = "{\"startDate\":\"2017-01-01\","
					+ "\"endDate\":\"2017-04-30\","
					+ "\"timeUnit\":\"month\","
					+ "\"keywordGroups\":[{\"groupName\":\"한글\","
					+ "\"keywords\":[\"한글\",\"korean\"]},"
					+ "{\"groupName\":\"영어\","
					+ "\"keywords\":[\"영어\","
					+ "\"english\"]}],"
					+ "\"device\":\"pc\","
					+ "\"ages\":[\"1\","
					+ "\"2\"],"
					+ "\"gender\":\"f\"}";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			con.setRequestProperty("Content-Type", "application/json");

			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			byte[] temp = new byte[1000];
			temp = body.getBytes();
			wr.write(temp);
//			wr.writeBytes(body);
			wr.flush();
			wr.close();

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
			System.out.println("OK");
			System.out.println(response.toString());

		} catch (Exception e) {
			System.out.println("ERROR");
			System.out.println(e);
		}
	}
}
