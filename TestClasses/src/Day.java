import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class Day {
	public List<Lesson> lessons = new ArrayList<Lesson>();
	public int weekday;
	public String weekdayText;
	
	public Day() {		
	}
	
	public Day (JSONObject jo) {
		JSONArray ja = jo.getJSONArray("lessons");
		for (int i = 0; i < ja.length(); i++) {
			lessons.add(new Lesson(ja.getJSONObject(i)));
		}
		weekday = jo.getInt("weekday");
		weekdayText = jo.getString("weekdayText");
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public int getWeekday() {
		return weekday;
	}

	public String getWeekdayText() {
		return weekdayText;
	}

	public String toString() {
		String result = "lessons: \n";
		for (Lesson less: lessons) {
			result += less.toString() + "\n";
		}
		result += "weekday: " + weekday + "\n"
				+ "weekday text: " + weekdayText + "\n";
		return result;
	}

}
