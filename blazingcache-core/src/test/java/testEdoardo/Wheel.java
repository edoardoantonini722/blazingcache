package testEdoardo;

public class Wheel implements java.io.Serializable
{
	private double diameter;
	private Boolean lenticular;
	private Profile profile;
	private Brand brand;
	
	public Wheel() {}

	
	public double getDiameter() {
		return diameter;
	}
	
	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}
	
	public Boolean isLenticular() {
		return lenticular;
	}
	
	public void setLenticulararity(Boolean lenticular) {
		this.lenticular = lenticular;
	}
	
	public Profile getProfile() {
		return profile;
	}
	
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
}