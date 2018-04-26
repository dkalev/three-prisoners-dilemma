package players;

public class RandomPlayer extends Player {
	//RandomPlayer randomly picks his action each time
	public int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
		if (Math.random() < 0.5)
			return 0;  //cooperates half the time
		else
			return 1;  //defects half the time
	}
}