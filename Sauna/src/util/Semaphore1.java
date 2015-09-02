package util;

import java.util.ArrayList;

import sauna.FemaleBather;
import sauna.MaleBather;


public class Semaphore1 {
	private int permits;
	ArrayList<String> bathers = new ArrayList<String>();
	
	public Semaphore1(int num) {
		this.permits=num;
	}
	
	public synchronized void acquire(FemaleBather fb) {	
		
		try {
			ThreadViz.addMessage("vaatan, kas saunas kohti on?");

			while ((permits <= 0) || bathers.contains("mb")) {
				ThreadViz.addMessage("saunas kohti ei ole, jŠŠn ootele.");
				this.wait();
			}
		} catch (InterruptedException e) {} 
	
		bathers.add("fb");
		ThreadViz.addMessage("sain omale saunas koha.");
		permits--;
	}
	
	public synchronized void acquire(MaleBather mb) {	
		
		try {
			ThreadViz.addMessage("vaatan, kas saunas kohti on?");

			while ((permits <= 0) || bathers.contains("fb")) {
				ThreadViz.addMessage("saunas kohti ei ole, jŠŠn ootele.");
				this.wait();
			}
		} catch (InterruptedException e) {} 
	
		bathers.add("mb");
		ThreadViz.addMessage("sain omale saunas koha.");
		permits--;
	}
	
	public synchronized void release(FemaleBather fb) {
		bathers.remove("fb");
		permits++;
		
		ThreadViz.addMessage("vabastan saunas koha.");
		this.notify();
	}
	
	public synchronized void release(MaleBather mb) {
		bathers.remove("mb");
		permits++;
		
		ThreadViz.addMessage("vabastan saunas koha.");
		this.notify();
	}
}
