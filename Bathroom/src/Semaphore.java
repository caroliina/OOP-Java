
public class Semaphore {
	private int permits;
	
	public Semaphore(int num) {
		this.permits = num;
	}
	
	public synchronized void acquire() throws InterruptedException {
			ThreadViz.addMessage("proovib vannituppa p��seda");

			while (permits <= 0) {
				ThreadViz.addMessage("vannitoas ei ole vabu kohti, j��b ootele");
				this.wait();
			}
	
		permits--;
		ThreadViz.addMessage("sai vannitoas koha. Vabu kohti on veel " + permits);
		
	}
	
	public synchronized void release() {
		permits++;
		
		ThreadViz.addMessage("vabastab vannitoas koha");
		this.notify();
	}
}
