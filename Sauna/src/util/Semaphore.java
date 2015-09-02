package util;

import java.util.ArrayList;
import sauna.FemaleBather;
import sauna.MaleBather;

public class Semaphore {
	private int permits;
	private String gender = null;
	private int fcount=0;
	private int mcount=0;
	//private String semaphoreName;
	
	private ArrayList<String> bathers = new ArrayList<String>();
	
	public Semaphore(int num) {
		//this.semaphoreName = name;
		this.permits=num;
		
	}
	
	
	
	public synchronized void acquire(FemaleBather fb) {	
		
		ThreadViz.addMessage("vaatan, kas saunas kohti on?");
		
		if(gender=="fb"){
			try {

				while (permits <= 0) {
					ThreadViz.addMessage("saunas kohti ei ole, jŠŠn ootele.");
					this.wait();
				}
			} catch (InterruptedException e) {} 
		
			
			ThreadViz.addMessage("sain omale saunas koha.");
			permits--;
		} else if(gender=="mb"){
			try {
				ThreadViz.addMessage("saunas kohti ei ole, jŠŠn ootele.");
				this.wait();
			} catch (InterruptedException e) {}
		} else {
			gender="fb";
			try {

				while (permits <= 0) {
					ThreadViz.addMessage("saunas kohti ei ole, jŠŠn ootele.");
					this.wait();
				}
			} catch (InterruptedException e) {} 
		
			
			ThreadViz.addMessage("sain omale saunas koha.");
			permits--;
		}
		
	}
	
	public synchronized void acquire(MaleBather mb) {	
		
		ThreadViz.addMessage("vaatan, kas saunas kohti on?");
		
		if(gender=="mb"){
			try {

				while (permits <= 0) {
					ThreadViz.addMessage("saunas kohti ei ole, jŠŠn ootele.");
					this.wait();
				}
			} catch (InterruptedException e) {} 
		
			ThreadViz.addMessage("sain omale saunas koha.");
			permits--;
			
		} else if(gender=="fb"){
			try {
				ThreadViz.addMessage("saunas kohti ei ole, jŠŠn ootele.");
				this.wait();
			} catch (InterruptedException e) {}
		} else {
			gender="mb";
			try {

				while (permits <= 0) {
					ThreadViz.addMessage("saunas kohti ei ole, jŠŠn ootele.");
					this.wait();
				}
			} catch (InterruptedException e) {} 
		
			
			ThreadViz.addMessage("sain omale saunas koha.");
			permits--;
		}
	}
	
	public synchronized void release(FemaleBather fb) {
		permits++;
		fcount++;
		if(fcount==2){
			gender=null;
			fcount=0;
		}
		ThreadViz.addMessage("vabastan saunas koha.");
		this.notify();
	}
	
	public synchronized void release(MaleBather mb) {
		permits++;
		mcount++;
		if(mcount==2){
			gender=null;
			mcount=0;
		}
		ThreadViz.addMessage("vabastan saunas koha.");
		this.notify();
	}
}

