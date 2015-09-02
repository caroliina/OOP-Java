
public class Showers{

	Semaphore shCount;
	
	public Showers(int count){
	shCount = new Semaphore(count);
	}
	
	public void enter() throws InterruptedException {
		shCount.acquire();
	}
	
	public void leave() {
		shCount.release();
	}
}
