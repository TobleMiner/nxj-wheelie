package eu.tsys.util;

public class Vector implements Cloneable
{
	public static Vector fromArray(int[] data, int offset)
	{
		return new Vector(data[offset],
				data[offset + 1],
				data[offset + 2]);
	}
	
	private double x;
	private double y;
	private double z;
	
	public Vector()
	{
		
	}
	
	public Vector(Vector vec)
	{
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
	}
	
	public Vector(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector normalize()
	{
		double length = this.length();
		this.x /= length;
		this.y /= length;
		this.z /= length;
		return this;
	}
	
	public Vector multiply(double factor)
	{
		this.x *= factor;
		this.y *= factor;
		this.z *= factor;
		return this;
	}
	
	public Vector divide(double divider)
	{
		this.x /= divider;
		this.y /= divider;
		this.z /= divider;
		return this;
	}
	
	public Vector add(Vector vec)
	{
		this.x += vec.x;
		this.y += vec.y;
		this.z += vec.z;
		return this;
	}
	
	public Vector sub(Vector vec)
	{
		this.x -= vec.x;
		this.y -= vec.y;
		this.z -= vec.z;
		return this;
	}
	
	public double length()
	{
		return Math.sqrt(Math.pow(this.x, 2) +
				Math.pow(this.y, 2) +
				Math.pow(this.z, 2));
	}
	
	public double getX()
	{
		return this.x;
	}
	
	public double getY()
	{
		return this.y;
	}
	
	public double getZ()
	{
		return this.z;
	}
	
	@Override
	public Vector clone()
	{
		return new Vector(this);
	}
}
