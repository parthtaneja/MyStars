package Models;
import java.time.LocalTime;
public class Course {
	private String courseCode;
	private  String school;
	private int indexNumber;
	private int vacancy;
	private String day;
	private String venue;
	private LocalTime startTime;
	private LocalTime endTime;
	// ADDD NEW THINGS
	
	public Course(String courseCode, String school, int indexNumber, int vacancy, String day, String Venue, int Start, int End) {
		this.courseCode = courseCode;
		this.school = school;
		this.indexNumber = indexNumber;
		this.vacancy = vacancy;
		this.day = day;
		this.venue = Venue;
		this.startTime = LocalTime.of(Start, 0, 0);
		this.endTime = LocalTime.of(End, 0, 0);
	}

	public String getVenue() {return venue;}
	public String getday() {return  day;}
	public LocalTime getStartTime() {return startTime;}
	public LocalTime getEndTime() {return endTime;}
	public String getCourseCode() {
		return courseCode;
	}

	public String getSchool() {
		return school;
	}

	public int getIndexNumber() {
		return indexNumber;
	}

	public int getVacancy() {
		return vacancy;
	}
	
	public void changeIndexNumber(int newIndexNumber)
	{
		this.indexNumber = newIndexNumber;
	}

	@Override
	public String toString() {
		return "Models.Course Code: " + courseCode + "\nSchool: " + school + "\nIndex Number: " + indexNumber + "\nVacancy: "
				+ vacancy ;
	}

}
