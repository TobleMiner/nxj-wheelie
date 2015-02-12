package eu.tsys.control;

import java.util.Timer;

import lejos.nxt.SensorPort;
import eu.tsys.display.Display;
import eu.tsys.sensor.Sampler;
import eu.tsys.sensor.Sampler.SampleReceiver;
import eu.tsys.util.Vector;

public class Leveler implements SampleReceiver
{
	private Sampler sampler;
	private Timer timer;
	private Display display;
	
	private int interval = 100;
	
	public Leveler(SensorPort accelPort, SensorPort touchPort)
	{
		this.sampler = new Sampler(accelPort, touchPort, this);
		this.timer = new Timer();
		this.display = new Display();
		this.timer.schedule(this.sampler, this.interval, this.interval);
	}

	@Override
	public void on_sample(Vector rotation)
	{
		this.display.updateIndicator(rotation);
		this.display.updateRotationInfo(rotation);
		this.display.writeBuffer();
	}
}
