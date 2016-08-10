package testEdoardo;
/**
 * Class representing bike wheel
 * @author edoardoantonini722
 *
 */
public class Wheel implements java.io.Serializable
{
	private static final long serialVersionUID = 692699574190526525L;
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
	
	@Override
	public String toString() {
		return this.brand.toString()+" with profile "+this.profile+" and diameter "+this.diameter;
	}
}