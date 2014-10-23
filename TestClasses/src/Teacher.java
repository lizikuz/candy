import org.json.JSONObject;


public class Teacher {
	public long teacherID;
	public String teacherName;
	
	public Teacher() {		
	}
	
	public Teacher(JSONObject jo) {
		teacherID = jo.getLong("teacher_id");
		teacherName = jo.getString("teacher_name");
	}
	
	public long getTeacherID() {
		return teacherID;
	}
	
	public String getTeacherName() {
		return teacherName;
	}
		
	public String toString() {
		return new String("teacher id: " + teacherID + "\n teacher name: " + teacherName + "\n");
	}
	
	public boolean isEquals(Teacher t) {
		if (this.teacherName.equals(t.teacherName)) {
			return true;
		}
		return false;
	}
}
