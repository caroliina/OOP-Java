
public class Bathroom {

	private Semaphore sem = new Semaphore(1);
	
	public void enter() throws InterruptedException {
		sem.acquire();
	}
	
	public void leave() {
		sem.release();
	}

}
