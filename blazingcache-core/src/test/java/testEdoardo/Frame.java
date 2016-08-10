package testEdoardo;
/**
 * Class representing bike frame.
 * @author edoardoantonini722
 *
 */
public class Frame implements java.io.Serializable
{
	private static final long serialVersionUID = 2729405158038986117L;
	private Brand brand;
	private double weight;
	
	public Frame() {}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return this.brand.toString() + " with weight "+this.weight;
	}
	
	
}