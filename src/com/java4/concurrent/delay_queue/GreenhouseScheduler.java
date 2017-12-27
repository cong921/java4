package com.java4.concurrent.delay_queue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
public class GreenhouseScheduler {
	private volatile boolean light = false;
	private volatile boolean water = false;
	private String thermostat = "Day";
	public String getThermostat() {
		return thermostat;
	}
	public synchronized void setThermostat(String thermostat) {
		this.thermostat = thermostat;
	}
	ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(10);
	public synchronized void shedule(Runnable event, long delay) {
		scheduled.schedule(event, delay, TimeUnit.MILLISECONDS);
	}
	public void repeat(Runnable event,long initialDelay,long period) {
		scheduled.scheduleAtFixedRate(event, initialDelay,period, TimeUnit.MILLISECONDS);
	}
	class LightOn implements Runnable {
		public void run() {
			// Put hardware control code here to
			// physically turn on the light
			System.out.println("Turning on lights");
			light = true;
		}
	}
	class LightOff implements Runnable {
		public void run() {
			// Put hardware control code here to
			// physically turn off the light
			System.out.println("Turning off lights");
			light = false;
		}
	}
	class WaterOn implements Runnable {
		public void run() {
			// Put hardware control code here to
			System.out.println("Turning greenhouse water on");
			water = true;
		}
	}
	class WaterOff implements Runnable {
		public void run() {
			// Put hardware control code here to
			System.out.println("Truning greenhouse water off");
			water = false;
		}
	}
	class ThermostatNight implements Runnable {
		public void run() {
			// Put hardware control code here to
			System.out.println("Thermostat to night setting");
			setThermostat("Night");
		}
	}
	class ThermostatDay implements Runnable {
		public void run() {
			// Put hardware control code here to
			setThermostat("Day");
		}
	}
	class Bell implements Runnable {
		public void run() {
			// Put hardware control code here to
			System.out.println("Bing!");
		}
	}
	class Terminate implements Runnable {
		public void run() {
			System.out.println("Terminating");
			scheduled.shutdownNow();
			// Must start a separate task to do this job,
			// since the scheduler has been shut down;
			new Thread() {
				public void run() {
					for (DataPoint dataPoint : data) {
						System.out.println(dataPoint);
					}
				}
			}.start();
		}
	}
	static class DataPoint {
		final Calendar time;
		final float temperature;
		final float humidity;

		public DataPoint(Calendar time, float temperature, float humidity) {
			super();
			this.time = time;
			this.temperature = temperature;
			this.humidity = humidity;
		}

		@Override
		public String toString() {
			return time.getTime() + String.format(" temperature: %1$.1f humidity:%2$.2f", temperature, humidity);
		}
	}
	private Calendar lastTime = Calendar.getInstance();
	{
		// Adjust date to the half hour
		lastTime.set(Calendar.MINUTE, 30);
		lastTime.set(Calendar.SECOND, 00);
	}
	private float lastTemp = 65.0f;
	private int tempDirection = +1;
	private float lastHumidity = 50.0f;
	private int humidityDirection = +1;
	private Random rand = new Random(47);
	List<DataPoint> data = Collections.synchronizedList(new ArrayList<DataPoint>());
	class CollectionData implements Runnable {
		public void run() {
			System.out.println("Collection data");
			synchronized (GreenhouseScheduler.this) {
				//Pretend the interval is longer than it is:
				lastTime.set(Calendar.MINUTE,lastTime.get(Calendar.MINUTE)+30);
				//One in 5 chances of reversing the direction:
				if(rand.nextInt(5)==4)
					tempDirection=-tempDirection;
				//Store previous value:
				lastTemp = lastTemp*tempDirection*(1.0f+rand.nextFloat());
				if(rand.nextInt(5)==4)
					humidityDirection=-humidityDirection;
				lastHumidity=lastHumidity+humidityDirection*rand.nextFloat();
				//Calendar must be cloned,otherwise all
				//DataPoints hold references to the same lastTime.
				//For a basic object like Calendar.clone() is OK.
				data.add(new DataPoint((Calendar) lastTime.clone(), lastTemp, lastHumidity));
			}
		}
	}
	public static void main(String[] args) {
		GreenhouseScheduler gh = new GreenhouseScheduler();
		gh.shedule(gh.new Terminate(), 5000);
		//Former "Restart" class not necessary;
		gh.repeat(gh.new Bell(), 0, 1000);
		gh.repeat(gh.new ThermostatNight(), 0, 2000);
		gh.repeat(gh.new LightOn(), 0, 200);
		gh.repeat(gh.new LightOff(), 0, 400);
		gh.repeat(gh.new WaterOn(), 0, 600);
		gh.repeat(gh.new WaterOff(), 0, 800);
		gh.repeat(gh.new ThermostatDay(), 0, 1400);
		gh.repeat(gh.new CollectionData(), 500, 500);
	}
}
