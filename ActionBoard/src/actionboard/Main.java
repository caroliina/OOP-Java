package actionboard;

import java.io.File;

public class Main {

	public static void main(String[] args) {

		System.out.println(new File(".").getAbsolutePath());

		Board board = new Board();

		Player warrior = new Warrior(board.getStart());
		Player wizard = new Wizard(board.getStart());
		Player monster = new Monster(board.getStart());
		// Player monsterr = new Monster(board.getStart());
		// Player monsterrr = new Monster(board.getStart());

		ThreadViz.showUI();

	}

}
