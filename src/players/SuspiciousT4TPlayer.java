package players;

public class SuspiciousT4TPlayer extends Player {
	//Picks a random opponent at each play,
	//and uses the 'tit-for-tat' strategy against them
	public int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
		if (n==0) return 1; //cooperate by default
		if (Math.random() < 0.5)
			return oppHistory1[n-1];
		else
			return oppHistory2[n-1];
	}
}