import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
	
	public static void main(String[] arg) {
		
		
		String strPath = new String("C:/Users/Марина/workspace/files/classes.json");
		String strURL = new String(" http://raspisaniye-vuzov.ru/api/v1/groups/111914.json");		
		download(strURL, strPath);
		List<Day> days = parser(strPath);
		List<String> result = compare(days);
		if (result.size() == 0) {
			System.err.println("There is no simular lessons!");
		} else {
			for (String res : result) {
				System.out.println(res);
			}
		}
		
	}
	
	
	public static void download(String strURL, String strPath) {
		System.out.println("Download json file from " + strURL + "...");
		int buffSize = 100;
		try {
            URL connection = new URL(strURL);
            HttpURLConnection urlconn;
            urlconn = (HttpURLConnection) connection.openConnection();
            urlconn.setRequestMethod("GET");
            urlconn.connect();
            InputStream in = null;
            in = urlconn.getInputStream();
            OutputStream writer = new FileOutputStream(strPath);
            byte buffer[] = new byte[buffSize];
            int c = in.read(buffer);
            while (c > 0) {
                writer.write(buffer, 0, c);
                c = in.read(buffer);
            }
            writer.flush();
            writer.close();
            in.close();
            System.out.println("Success!");
        } catch (IOException e) {
        	System.err.println("Error #0: " + e.getMessage());
        }
	}
	
	@SuppressWarnings("resource")
	public static List<Day> parser(String str) {
		System.out.println("Parsing file " + str + "...");
		List<Day> days = new ArrayList<Day>();
		File file;
		BufferedReader br;		
		try {
			file = new File(str);
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String json = br.readLine();
			JSONObject obj = new JSONObject(json);
			/*if (!obj.getString("success").equals("true")) {
				return null;
			}*/
			JSONObject jdata = obj.getJSONObject("data");
			JSONArray jdays = jdata.getJSONArray("days");			
			for (int i = 0; i < jdays.length(); i++) {
				days.add(new Day(jdays.getJSONObject(i)));
			}
			System.out.println("Success!");
		} catch (IOException e) {
			System.err.println("Error #1: " + e.getMessage());
		}
		return days;
	}
	
	public static List<String> compare(List<Day> days) {
		List<String> equalLessons = new ArrayList<String>();
		for (int i = 0; i < days.size(); i++) {
			Day day = days.get(i);
			Collections.sort(days.get(i).lessons, new Comparator<Lesson>() {
				@Override
				public int compare(Lesson l1, Lesson l2) {
					return (int) (l1.lessonID - l2.lessonID);
				}
			});
			for (int j = 0; j < days.get(i).lessons.size()-1; j++) {
				Lesson l1 = day.lessons.get(j);
				Lesson l2 = day.lessons.get(j+1);
				if (l1.isEqual(l2)) {
					equalLessons.add("Lesson #" + l1.lessonID + " is equals to lesson #" + l2.lessonID);
				}
			}
		}	
		return equalLessons;		
	}
}
