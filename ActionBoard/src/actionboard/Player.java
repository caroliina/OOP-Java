package actionboard;

import java.util.Random;

public abstract class Player extends Thread {

	Tile location;
	int level = 1;
	int wins = 0;
	int losses = 0;

	String mood = "Neutral";
	int moodCount = 0;
	
	String idea = "NoIdea";

	public String getIdea() {
		return idea;
	}

	public void setIdea(String idea) {
		this.idea = idea;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	public synchronized Tile getLocation() {
		return location;
	}

	public void setLocation(Tile location) {
		this.location = location;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public Player(Tile location) {
		this.location = location;
	}

	public abstract String getN();

	/*
	 * Genereerib random aja
	 */
	public int rand() {
		int rn;
		Random randomnum = new Random();
		rn = randomnum.nextInt(4000);
		return rn;
	}

	/*
	 * Genereerib random mänguvälja
	 */
	public int randn() {
		int rnn;
		Random randomn = new Random();
		rnn = randomn.nextInt(1);
		return rnn;
	}

	@Override
	public abstract void run();

}
