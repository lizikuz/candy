import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class Lesson {
	public long lessonID;
	public int parity = -1;
	public List<Auditory> auditories = new ArrayList<Auditory>();
	public List<Teacher> teachers = new ArrayList<Teacher>();
	
	public String timeStart; 
	public String timeEnd;
	public List<String> dates = new ArrayList<String>();
	public String dateStart;
	public String dateEnd;
	//public long lastUpdated;
	public int type;
	public String typeName;
	public String subject;

	
	public Lesson() {		
	}
	
	public Lesson (JSONObject jo) {
		lessonID = jo.getLong("lesson_id");
		parity = jo.getInt("parity");
		JSONArray audit_arr = jo.getJSONArray("auditories");
		for (int i = 0; i < audit_arr.length(); i++) {			
			auditories.add(new Auditory(audit_arr.getJSONObject(i)));
		}
		JSONArray teach_arr = jo.getJSONArray("teachers");
		for (int i = 0; i < teach_arr.length(); i++) {			
			teachers.add(new Teacher(teach_arr.getJSONObject(i)));
		}
		timeStart = jo.getString("time_start");
		timeEnd = jo.getString("time_end");
		if (parity < 0) {
			JSONArray dates_arr = jo.getJSONArray("dates");
			for (int i = 0; i < dates_arr.length(); i++) {
				dates.add(dates_arr.getString(i));
			}
			if(dates == null || dates.size() == 0) {
				dateStart = jo.getString("date_start");
				dateEnd = jo.getString("dateEnd");
			}
		}
		type = jo.getInt("type");
		typeName = jo.getString("typeName");
		subject = jo.getString("subject");
		
	}

	

	public long getLessonID() {
		return lessonID;
	}

	public int getParityy() {
		return parity;
	}

	public List<Auditory> getAuditories() {
		return auditories;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public List<String> getDates() {
		return dates;
	}

	/*public long getLastUpdated() {
		return lastUpdated;
	}*/

	public int getType() {
		return type;
	}

	public String getTypeName() {
		return typeName;
	}

	public String getSubject() {
		return subject;
	}
	
	public String toString() {
		String result = "lesson id: " + lessonID 
				+ "\n parity: " + parity
				+ "\n auditories: \n";
		for (Auditory aud: auditories) {
			result += aud.toString();
		}
		for (Teacher teach: teachers) {
			result += teach.toString();
		}
		result += "start time: " + timeStart + "\n"
				+ "end time: " + timeEnd  + "\n"
				+ "dates: " + "\n";
		for (String data: dates) {
		result += data  + "\n";
		}
		result += "type: " + type + "\n"
				+ "typeName: " + typeName + "\n"
				+ "subject: " + subject + "\n";
				
		return result;
	}
	
	public boolean isEqual(Lesson l) {
		boolean teachersEq = false;
		Comparator<Teacher> teach_comp = new Comparator<Teacher>() {
			@Override
			public int compare(Teacher t1, Teacher t2) {
				return  (int) (t1.teacherID-t2.teacherID);
			}
		};
		Collections.sort(teachers, teach_comp);
		Collections.sort(l.teachers, teach_comp);
		if (this.teachers.size() == l.teachers.size()) {
			for (int i = 0; i < this.teachers.size(); i++) {
				if(teachers.get(i).isEquals(l.teachers.get(i))) {
					teachersEq = true;
				}
			}
		}
		
		boolean auditEq = false;
		Comparator<Auditory> audit_comp = new Comparator<Auditory>() {
			@Override
			public int compare(Auditory a1, Auditory a2 ) {
				return  (int) (a1.auditID-a2.auditID);
			}
		};
		Collections.sort(auditories, audit_comp);
		Collections.sort(l.auditories, audit_comp);
		if (this.auditories.size() == l.auditories.size()) {
			for (int i = 0; i < this.auditories.size(); i++) {
				if(auditories.get(i).isEquals(l.auditories.get(i))) {
					auditEq = true;
				}
			}
		}
		
		boolean datesEq = false;
		Collections.sort(dates);
		Collections.sort(l.dates);
		if (dates.equals(l.dates)) {
			datesEq = true;
		}
		
		
		if (this.subject.equals(l.subject)
				&& this.type == l.type
				&& this.timeStart.equals(l.timeStart)
				&& this.timeEnd.equals(l.timeEnd)
				&& teachersEq
				&& auditEq
				&& ((parity==l.parity) 
						|| (datesEq) 
						|| (dateStart.equals(l.dateStart)&&dateEnd.equals(l.dateEnd)))) 
		{
			return true;
		}		
		return false;
		
	}
	
}
