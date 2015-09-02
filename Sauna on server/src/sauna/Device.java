package sauna;

public abstract class Device {

	String status = "OFF";

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public abstract int getTemp();

	public abstract void setTemp(int temp);

	public abstract int getMaxTemp();

	public abstract void setMaxTemp(int maxTemp);

	public abstract int getMinTemp();

	public abstract void setMinTemp(int minTemp);

	public abstract int getPower();

	public abstract void setPower(int power);

	public abstract int getTime();

	public abstract void setTime(int time);
	
	public abstract int getLimit();

	public abstract void setLimit(int limit);

}
