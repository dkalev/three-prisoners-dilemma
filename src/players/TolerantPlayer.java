package players;

public class TolerantPlayer extends Player {
	//TolerantPlayer looks at his opponents' histories, and only defects
	//if at least half of the other players' actions have been defects
	public int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
		int opponentCoop = 0;
		int opponentDefect = 0;
		for (int i=0; i<n; i++) {
			if (oppHistory1[i] == 0)
				opponentCoop = opponentCoop + 1;
			else
				opponentDefect = opponentDefect + 1;
		}
		for (int i=0; i<n; i++) {
			if (oppHistory2[i] == 0)
				opponentCoop = opponentCoop + 1;
			else
				opponentDefect = opponentDefect + 1;
		}
		if (opponentDefect > opponentCoop)
			return 1;
		else
			return 0;
	}
}