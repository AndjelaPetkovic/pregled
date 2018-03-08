package pregled;

public class Muskarac extends Pacijent {

	public Muskarac(double mass, double height, int age, DailyActivity dailyActivity) {
		super(mass, height, age, dailyActivity);
		
	}

	@Override
	public String sex() {		
		return "pacijent";
	}
	
/*
	@Override
	public String toString() {
		return "[" + sex() + "] " + super.toString();
	}*/

	@Override
	public double recommendedDailyCalorieIntake() {
		return 662-9.53*getAge()+1.4*(15.91*getMass()+539.6*getHeight()/100);
	}
	

}
