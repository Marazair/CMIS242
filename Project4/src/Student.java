
public class Student {
	private String name;
	private String major;
	private int totalCredits;
	private int qualityPoints;
	
	public Student(String name, String major) {
		this.name = name;
		this.major = major;
	}
	
	public void courseCompleted(int grade, int credits) {
		totalCredits += credits;
		qualityPoints += (grade * credits);
	}
	
	public String toString() {
		return "Name: " + name + ", Major: " + major + ", GPA: " + (totalCredits/qualityPoints);
	}
}
