package testEdoardo;

public class Bike implements java.io.Serializable
{
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
	
	public Wheel getRealWheel()
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
	
	
}