package project;

public class Student 
{
	private String studentId;
	private String lastName;
	private String firstName;
	private String testWorkName;
	private String studentMark;
	private String testWorkMaxMark;
	
	public Student(String studentId, String lastName, String firstName, String testWorkName, String studentMark, String testWorkMaxMark)
	{
		this.studentId = studentId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.testWorkName = testWorkName;
		this.studentMark = studentMark;
		this.testWorkMaxMark = testWorkMaxMark;
	}
}
