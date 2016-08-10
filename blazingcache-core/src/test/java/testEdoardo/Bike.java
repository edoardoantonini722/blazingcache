package testEdoardo;
/**
 * Simple bike class
 * @author edoardoantonini722
 *
 */
public class Bike implements java.io.Serializable
{
	private static final long serialVersionUID = 7257429130826014400L;
	private Frame frame;
	private Wheel frontWheel;
	private Wheel rearWheel;
	private Seat seat;
	
	public Bike(){}
	
	public Frame getFrame()
	{
		return this.frame;
	}
	
	public Wheel getFrontWheel()
	{
		return this.frontWheel;
	}
	
	public Wheel getRearWheel()
	{
		return this.rearWheel;
	}
	
	public Seat getSeat()
	{
		return this.seat;
	}
	
	
	public void setFrame(Frame frame)
	{
		this.frame=frame;
	}
	
	public void setFrontWheel(Wheel frontWheel)
	{
		this.frontWheel=frontWheel;
	}
	
	public void setRealWheel(Wheel rearWheel)
	{
		this.rearWheel=rearWheel;
	}
	
	public void setSeat(Seat seat)
	{
		this.seat=seat;
	}
	
	@Override
	public String toString() {
		return "Bike with seat "+seat.toString()+
				" and frame "+frame.toString()+
				" and front wheel "+frontWheel.toString()+
				" and rear wheel "+rearWheel.toString();
	}
	
	
}