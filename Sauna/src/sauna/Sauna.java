package sauna;

import util.Semaphore;
import util.Semaphore1;

public class Sauna {
	private Semaphore1 sem = new Semaphore1(2);
	
	public void enter(FemaleBather fb) {
		sem.acquire(fb);
	}
	
	public void enter(MaleBather mb) {
		sem.acquire(mb);
	}
	
	public void leave(FemaleBather fb) {
		sem.release(fb);
	}
	
	public void leave(MaleBather mb) {
		sem.release(mb);
	}
}
