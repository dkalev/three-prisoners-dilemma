package players;

public class FreakyPlayer extends Player {
	//FreakyPlayer determines, at the start of the match,
	//either to always be nice or always be nasty.
	//Note that this class has a non-trivial constructor.
	int action;
	public FreakyPlayer() {
		if (Math.random() < 0.5)
			action = 0;  //cooperates half the time
		else
			action = 1;  //defects half the time
	}

	public int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
		return action;
	}
}