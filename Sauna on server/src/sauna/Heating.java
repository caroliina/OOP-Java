package sauna;

public class Heating extends Thread {
	int minTemp;
	int maxTemp;
	int temp;
	Device heater;
	Ventilation vent;
	boolean stop = false;
	private volatile boolean isRunning = true;
	private OutboundMessages out;
	private Cooling cooling;

	public Heating(int minTemp, int maxTemp, int temp, Device heater,
			Ventilation vent, OutboundMessages out, Cooling cooling) {

		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		this.temp = temp;
		this.heater = heater;
		this.vent = vent;
		this.out=out;
		this.cooling=cooling;

		this.start();

	}

	public void run() {
		while (isRunning) {
			try {

				if ((heater.getTemp() + 20) <= heater.getMaxTemp()) {
					heater.setTemp(heater.getTemp() + heater.getMinTemp());
					System.out.println(heater.getTemp());

					if (vent.getState().equals("ON")) {
						Thread.sleep((heater.getPower()) * 2);
					} else {
						Thread.sleep(heater.getPower());
					}

				} else if (heater.getTemp() == heater.getMaxTemp()) {
					Thread.sleep(heater.getLimit());
					out.addMessage("Keris lülitub välja!!!");
					heater.setStatus("OFF");
					vent.setState("ON");
					isRunning = false;

					cooling = new Cooling(heater.getMinTemp(),
							heater.getMaxTemp(), heater.getTemp(), heater,
							vent);
					//cooling.start();

				}

			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

		}
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public void kill() {
		isRunning = false;
	}

}
