package actionboard;

public class Semaphore {
	private int permits;

	public Semaphore(int num) {
		this.permits = num;
	}

	public synchronized void acquire(Player player) throws InterruptedException {
		ThreadViz.addMessage("proovib j�rgmisele ruudule liikuda");

		while ((player.getN() == "wizard"
				&& player.getLocation().countPlayers() && player.getLocation()
				.getName() == "B1")
				|| (player.getN() == "wizard"
						&& player.getLocation().countPlayers() && player
						.getLocation().getName() == "A2")) {
			ThreadViz.addMessage("liiga palju koletisi, j��b ootele");
			this.wait();
		}

		while (permits <= 0) {
			if(player.getN()=="monster"){
				if(player.getMood()=="Good"){
					player.setMood("Neutral");
					player.moodCount++;
				} else {
					player.setMood("Angry");
					player.moodCount++;
				}
			}
			ThreadViz.addMessage("j�rgmisel ruudul pole kohti, j��b ootele");
			this.wait();
		}

		permits--;
		ThreadViz.addMessage("liikus j�rgmisele ruudule. Vabu kohti on veel "
				+ permits);

	}

	public synchronized void release(Player player) {
		permits++;

		if (player.getN() == "monster") {
			this.notify();
		}

		ThreadViz.addMessage("liikus ruudult �ra");
		this.notify();
	}

}
