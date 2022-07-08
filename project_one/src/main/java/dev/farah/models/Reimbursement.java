package dev.farah.models;

public class Reimbursement {

	private int id;
	private String FormDate;
	private String FormTime;
	private Float  Rate;
	private Float Cost;
	private String Grade;
	private String Program;
	
	public Reimbursement() {}
	
	public Reimbursement(int id, String formDate, String formTime, Float rate, Float cost, String grade, String program,
			String justification, String status) {
		super();
		this.id = id;
		FormDate = formDate;
		FormTime = formTime;
		Rate = rate;
		Cost = cost;
		Grade = grade;
		Program = program;
		Justification = justification;
		Status = status;
	}
	public Reimbursement(int int1, String string, String string2, boolean boolean1) {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFormDate() {
		return FormDate;
	}
	public void setFormDate(String formDate) {
		FormDate = formDate;
	}
	public String getFormTime() {
		return FormTime;
	}
	public void setFormTime(String formTime) {
		FormTime = formTime;
	}
	public Float getRate() {
		return Rate;
	}
	public void setRate(Float rate) {
		Rate = rate;
	}
	public Float getCost() {
		return Cost;
	}
	public void setCost(Float cost) {
		Cost = cost;
	}
	public String getGrade() {
		return Grade;
	}
	public void setGrade(String grade) {
		Grade = grade;
	}
	public String getProgram() {
		return Program;
	}
	public void setProgram(String program) {
		Program = program;
	}
	public String getJustification() {
		return Justification;
	}
	public void setJustification(String justification) {
		Justification = justification;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	private String Justification;
	private String Status;
	
}
