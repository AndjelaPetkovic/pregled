package pregled;

public class Zena extends Pacijent {

	public Zena(double mass, double height, int age, DailyActivity dailyActivity) {
		super(mass, height, age, dailyActivity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String sex() {
		
		return "pacijentkinja";
	}

	@Override
	public double recommendedDailyCalorieIntake() {
		
		return 354 - 6.91 * getAge() + 1.2 * (9.36 * getMass() + 726 * getHeight()/100);
	}

}
