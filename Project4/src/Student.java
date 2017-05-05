import java.text.*;

public class Student {
	private String name;
	private String major;
	private int totalCredits;
	private int qualityPoints;
	
	public Student(String name, String major) {
		totalCredits = 0;
		qualityPoints = 0;
		this.name = name;
		this.major = major;
	}
	
	public void courseCompleted(char grade, int credits) {
		totalCredits += credits;
		
		if (grade == 'A') {
			qualityPoints += (4*credits);
		}
		else if(grade == 'B') {
			qualityPoints += (3*credits);
		}
		else if(grade == 'C') {
			qualityPoints += (2*credits);
		}
		else if(grade == 'D') {
			qualityPoints += (1*credits);
		}
		else if(grade == 'F') {
			qualityPoints += (0*credits);
		}
	}
	
	public String toString() {
		DecimalFormat numberFormat = new DecimalFormat("#.0");
		if(totalCredits != 0) {
			return "Name: " + name + ", Major: " + major + ", GPA: " + (numberFormat.format((double)qualityPoints/totalCredits));
		}
		else {
			return "Name: " + name + ", Major: " + major + ", GPA: 4.0";
		}
	}
}
