import org.json.JSONObject;


public class Auditory {
	public long auditID;
	public String auditName;
	//public String alias;
	//public String auditAddress;
	
	public Auditory() {		
	}
	
	public Auditory(JSONObject jo) {
		auditID = jo.getLong("auditory_id");
		auditName = jo.getString("auditory_name");
	//	alias = jo.getString("alias");
	//	auditAddress = jo.getString("auditory_address");
	}
	
	public long getAuditID() {
		return auditID;
	}

	public String getAuditName() {
		return auditName;
	}
	/*public String isAlias() {
		return alias;
	}
	public String getAuditAddress() {
		return auditAddress;
	}*/
	
	public String toString() {
		return new String("auditory id: " + auditID 
				+ "\n auditory name: " + auditName
				//+ "\n alias: " + alias
				/*+ "\n auditory address: " + auditAddress*/ + "\n");
	}
	
	public boolean isEquals(Auditory a) {
		if (this.auditName.equals(a.auditName)) {
			return true;
		}
		return false;
	}
	
}
