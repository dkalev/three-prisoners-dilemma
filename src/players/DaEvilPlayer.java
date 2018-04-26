package players;
public class DaEvilPlayer extends Player {
	private double opp1Def = 0;
	private double opp2Def = 0;

	public int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
		if(n==0) return 1;
		// Calculate probability for Def/Coop (Opponent 1)
		opp1Def += oppHistory1[n - 1];

		// Calculate probability for Def/Coop (Opponent 2)
		opp2Def += oppHistory2[n - 1];
		
		//def if random
		if(n > 10 && (Math.abs(opp1Def - n/2) < 4 || Math.abs(opp2Def - n/2) < 4)){
			return 1;
		}
		
		//if everybody is doing the same, do it as well
		if(oppHistory1[n-1] == oppHistory2[n-1])
			return oppHistory1[n-1];
		//defect by default
		return 1;
	}
}