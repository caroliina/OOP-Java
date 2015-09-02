package sauna;

import util.ThreadViz;


public class MaleBather extends Thread{
	private Sauna sauna;

	public MaleBather(String name, Sauna sauna) {
		this.sauna = sauna;
		setName(name);
		ThreadViz.addThread(this);
		start();
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				sauna.enter(this);
				ThreadViz.addMessage("Viskan leili, higistan, vihtlen...");
				sleep(2000);
				sauna.leave(this);
				
				ThreadViz.addMessage("Jahtun maha...");
				sleep(2000);
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
