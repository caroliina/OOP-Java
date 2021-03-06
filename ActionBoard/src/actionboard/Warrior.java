package actionboard;

public class Warrior extends Player {

	String name = "warrior";
	Player buddy;

	public Warrior(Tile location) {
		super(location);

		setName(name);
		ThreadViz.addThread(this);
		start();
	}

	@Override
	public void run() {

		while (true) {

			try {

				location = location.getNeighbours().get(this.randn());

				location.enter(this);

				if (location.getName() == "B2" || location.getName() == "A1") {
					ThreadViz.addMessage("Practising... " + location.getName()
							+ " (" + this.getIdea() + ")");
					this.setMood("Good");
					this.moodCount++;

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
					this.level++;
					location.leave(this);
				} else {
					ThreadViz.addMessage("Just standing... "
							+ location.getName() + " (" + this.getIdea() + ")");
					sleep(rand());
					location.leave(this);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public synchronized String getN() {
		return name;
	}

}
