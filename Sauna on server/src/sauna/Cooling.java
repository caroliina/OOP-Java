package sauna;

public class Cooling extends Thread {
	int minTemp;
	int maxTemp;
	int temp;
	Device heater;
	Ventilation vent;
	private volatile boolean isRunning = true;

	public Cooling(int minTemp, int maxTemp, int temp, Device heater,
			Ventilation vent) {

		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		this.temp = temp;
		this.heater = heater;
		this.vent = vent;

		this.start();

	}

	public void run() {
		while (isRunning) {
			try {

				if ((heater.getTemp() - 20) >= minTemp) {
					heater.setTemp(heater.getTemp() - heater.getMinTemp());
					System.out.println(heater.getTemp());
					if (vent.getState().equals("ON")) {
						Thread.sleep((heater.getTime()) / 2);
					} else {
						Thread.sleep(heater.getTime());
					}

				}

			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

		}
	}

	public void kill() {
		isRunning = false;
	}
}
