package eu.tsys.sensor;

import java.util.TimerTask;

import eu.tsys.util.Vector;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.addon.AccelHTSensor;
import lejos.util.DebugMessages;

public class Sampler extends TimerTask
{
	private AccelHTSensor accelSensor;
	private Vector rotation;
	private SampleReceiver receiver;
	
	private Vector rotationOffset;
	private TouchSensor touchSensor;
	
	private int[] acceleration = new int[3];

	DebugMessages mesgs = new DebugMessages();	
	
	public Sampler(SensorPort accelPort, SensorPort touchPort, SampleReceiver receiver)
	{
		this.accelSensor = new AccelHTSensor(accelPort);
		this.touchSensor = new TouchSensor(touchPort);
		this.rotation = new Vector();
		this.rotationOffset = new Vector(0d, 0d, 0d);
		this.receiver = receiver;
	}
	
	@Override
	public void run()
	{
		this.accelSensor.getAllAccel(this.acceleration, 0);
		this.rotation = Vector.fromArray(this.acceleration, 0);
		if(this.touchSensor.isPressed())
			this.rotationOffset = this.rotation.clone().add(new Vector(-205d, 0d, 0d));
		this.rotation.sub(this.rotationOffset);
		this.receiver.on_sample(this.rotation);
	}
	
	public interface SampleReceiver
	{
		public void on_sample(Vector rotation);
	}
}
