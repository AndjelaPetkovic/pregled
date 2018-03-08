package pregled;

public abstract class Pacijent implements Comparable<Pacijent>{
	
	private double mass, height;
	private int age;	
	private DailyActivity dailyActivity;
	
	public Pacijent(double mass, double height, int age, DailyActivity dailyActivity) {
		
		this.mass = mass;
		this.height = height;
		this.age = age;
		this.dailyActivity = dailyActivity;
	}

	public double getMass() {
		return mass;
	}

	public double getHeight() {
		return height;
	}

	public int getAge() {
		return age;
	}

	public DailyActivity getDailyActivity() {
		return dailyActivity;
	}
	
	@Override
	public String toString() {
		return "[" + sex() + "] masa: " + mass + "kg, visina: " + height + "cm, starost: " + age + "god\n"
				+ "nivo aktivnost: " + dailyActivity
				+ ", preporuceni dnevni unos kCal: " + recommendedDailyCalorieIntake()
				+ "\n---------------------------------------\n";
	}

	public abstract String sex();
	
	public abstract double recommendedDailyCalorieIntake();

	@Override
	public int compareTo(Pacijent p) {
		int rez = Integer.compare(this.age, p.age);
		if (rez == 0) {
			return Double.compare(this.recommendedDailyCalorieIntake(), p.recommendedDailyCalorieIntake());
		}
		return rez;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((dailyActivity == null) ? 0 : dailyActivity.hashCode());
		long temp;
		temp = Double.doubleToLongBits(height);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(mass);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pacijent))
			return false;
		Pacijent other = (Pacijent) obj;
		if (age != other.age)
			return false;
		if (dailyActivity != other.dailyActivity)
			return false;
		if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
			return false;
		if (Double.doubleToLongBits(mass) != Double.doubleToLongBits(other.mass))
			return false;
		return true;
	}
	
	
	
	

}
