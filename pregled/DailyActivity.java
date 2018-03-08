package pregled;

public enum DailyActivity {
	
	S, M, A, VA; 
	
	public String toString() {
		
		switch(this) {
		case S:
			return "sedeci";
		case M:
			return "umereno aktivan";
		case A:
			return "aktivan";
		case VA:
			return "veoma aktivan";
		default:
			return "";
		}
	}
	

}
