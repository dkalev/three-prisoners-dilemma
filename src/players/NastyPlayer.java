package players;

public class NastyPlayer extends Player {
	//NastyPlayer always defects
	public int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
		return 1;
	}
}