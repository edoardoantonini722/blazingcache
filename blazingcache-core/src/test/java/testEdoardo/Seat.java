package testEdoardo;

public class Seat implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2162542740034799195L;
	private Brand brand;
	private double lenght;
	
	public Seat() {}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public double getLenght() {
		return lenght;
	}

	public void setLenght(double lenght) {
		this.lenght = lenght;
	}
	
	@Override
	public String toString() {
		return this.brand.toString()+" with lenght "+this.lenght;
	}
	
	
	
}