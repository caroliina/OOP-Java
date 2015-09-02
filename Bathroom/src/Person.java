
public class Person extends Thread{
	
		//private Bathroom room;
		private Showers room;

		public Person(String name, Showers room) {
			this.room = room;
			setName(name);
			ThreadViz.addThread(this);
			start();
			}
		
		@Override
		public void run() {
			
				while(true) {
					
					try {
					room.enter();
					ThreadViz.addMessage("Pesen...");
					sleep(2000);
					room.leave();
					
					//Puhkab
					sleep(1000);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			
		}
		
		}

