package eu.tsys;

import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import eu.tsys.control.Leveler;

public class Main {

	public static void main(String[] args)
	{
		Leveler leveler = new Leveler(SensorPort.S1, SensorPort.S4);
		while(true)
			Button.waitForAnyPress();
	}

}
