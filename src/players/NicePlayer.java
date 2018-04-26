package players;

public class NicePlayer extends Player {
	//NicePlayer always cooperates
	public int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
		return 0;
	}
}