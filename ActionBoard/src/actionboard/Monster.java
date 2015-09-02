package actionboard;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Monster extends Player {

	String name = "monster";
	Player opponent;
	Player buddy;
	Tile start;

	public synchronized String getN() {
		return name;
	}

	String content;

	public void writeToLog() {
		try {

			File file = new File(
					"/Users/caroliinalaantee/Documents/EclipseWorkspace/ActionBoard/action_log.txt");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fileWritter = new FileWriter(file.getName(), true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(content);
			bufferWritter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Monster(Tile location) {
		super(location);
		start = location;
		setName(name);
		ThreadViz.addThread(this);
		start();
	}

	@Override
	public void run() {

		while (true) {

			try {

				location = location.getNeighbours().get(randn());

				location.enter(this);

				/*
				 * Panen monsterile meme idee
				 */
				this.setIdea("Meme");

				if (location.contains("wizard") == "yes") {
					if (this.getMood() == "Angry") {
						this.setMood("Neutral");
						this.moodCount++;
					} else {
						this.setMood("Good");
						this.moodCount++;
					}
				}

				if (location.getName() == "B2" || location.getName() == "A1") {
					if (this.getMood() == "Good") {
						ThreadViz.addMessage("La la la... "
								+ location.getName() + " (" + this.getIdea()
								+ ")");
					} else {
						ThreadViz.addMessage("Zzz... " + location.getName()
								+ " (" + this.getIdea() + ")");
					}

					/*
					 * Ideede vahetamine
					 */
					if (location.getPlayers().size() > 1) {
						for (int i = 0; i < location.getPlayers().size(); i++) {
							if (location.getPlayers().get(i) != this) {
								buddy = location.getPlayers().get(i);
							}
						}

						String myIdea = this.getIdea();
						if (buddy.getIdea() == "Meme" || myIdea == "Meme") {
							this.setIdea("Meme");
							buddy.setIdea("Meme");
						}
						if (buddy.getIdea() != "NoIdea") {
							this.setIdea(buddy.getIdea());
							if (this.getIdea() == "GoodIdea") {
								this.setMood("Good");
							}
						}
						if (myIdea != "NoIdea") {
							buddy.setIdea(myIdea);
							if (buddy.getIdea() == "GoodIdea") {
								buddy.setMood("Good");
							}
						}
					}
					sleep(rand());
					location.leave(this);

				} else {

					if (location.getName() == "B1"
							|| location.getName() == "A2") {

						if (location.getPlayers().size() > 1) {
							ThreadViz.addMessage("Norime tüli... "
									+ location.getName());

							for (int i = 0; i < location.getPlayers().size(); i++) {
								if (location.getPlayers().get(i).getN() != "monster") {
									opponent = location.getPlayers().get(i);
								}
							}

							if ((opponent != null
									&& this.level >= opponent.level && opponent
									.getMood() != "Good")
									|| (opponent != null && this.getMood() == "Angry")) {
								ThreadViz.addMessage(this.getName()
										+ " on tugevam kui "
										+ opponent.getName());
								content = this.getName() + " on tugevam kui "
										+ opponent.getName() + "\n";
								this.setWins(opponent.getWins() + 1);
								opponent.setLosses(opponent.getLosses() + 1);
								this.level++;
								/*
								 * Level väheneb
								 */
								opponent.level--;
								if (opponent.level <= 0) {
									opponent.setLocation(start);
								}
								writeToLog();
								sleep(rand());
								location.leave(this);

							} else if (opponent != null) {
								ThreadViz.addMessage(opponent.getName()
										+ " on tugevam kui " + this.getName());
								content = opponent.getName()
										+ " on tugevam kui " + this.getName()
										+ "\n";
								this.setLosses(opponent.getLosses() + 1);
								opponent.setWins(opponent.getWins() + 1);
								opponent.level++;
								/*
								 * this.level--; if (opponent.level <= 0) {
								 * opponent.setLocation(start); }
								 */
								writeToLog();
								sleep(rand());
								location.leave(this);

							}
						} else {
							if (this.getMood() == "Good") {
								ThreadViz.addMessage("La la la... "
										+ location.getName() + " ("
										+ this.getIdea() + ")");
							} else {
								ThreadViz.addMessage("Just standing... "
										+ location.getName() + " ("
										+ this.getIdea() + ")");
							}
							sleep(rand());
							location.leave(this);
						}
					}
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
