package players;
import java.util.Arrays;

public class DaPlayer extends TolerantPlayer {
		private int opp1Rand = 0;
		private int opp2Rand = 0; 
		private boolean peaceOffered = false;
		
			public int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			
			
			//start by cooperating
			if(n==0) return 0;
			
			if(n < 3 && (oppHistory1[n-1] == 0 || oppHistory2[n-1] == 0)) return 0;

			if(n==99) return 1;
			
			//update randomness of each opponent
			if(n > 1 && oppHistory1[n-1] != oppHistory1[n-2])
				opp1Rand += 1;
			if(n > 1 && oppHistory2[n-1] != oppHistory2[n-2])
				opp2Rand += 1;
			
			//in case of evil player -> massive improvement
			if(n < 10) {
				//if somebody is playing nasty
				if(n > 1 && ((opp1Rand == 0 && oppHistory1[n-1] == 1) || (opp2Rand == 0 && oppHistory2[n-1] == 1))) {
					return 1;
				}
				//else be tolerant
				return super.selectAction(n, myHistory, oppHistory1, oppHistory2);
			}
			
			//handle random opponents
			if(n >= 10 && (opp1Rand/n > 0.01 || opp2Rand > 0.01)){
				return findBestMove(n, myHistory, oppHistory1, oppHistory2);
			}
			
			//if everybody is doing the same, do it as well
			if(oppHistory1[n-1] == oppHistory2[n-1])
				return oppHistory1[n-1];
			
			//defect by default
			return 1;
		}
		
		
		//look if the current scenario(last 5 rounds) has happened in the past
		private String findMatch(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			
			String myHistoryStr = Arrays.toString(myHistory);
			String oppHistory1Str = Arrays.toString(oppHistory1);
			String oppHistory2Str = Arrays.toString(oppHistory2);
			String opp1lastMoves = oppHistory1Str.substring(n - 6, n - 1);
			String opp2lastMoves = oppHistory2Str.substring(n - 6, n - 1);
			if (n > 5) {
				//find if the current scenario has happened before and return the outcome
				for(int i = 0; i < n - 6; i++) {
					if(oppHistory1Str.substring(i, i+5).equals(opp1lastMoves) && oppHistory2Str.substring(i, i+5).equals(opp2lastMoves)) {
						return ""+ myHistoryStr.charAt(i+4) + oppHistory1Str.charAt(i+4) + oppHistory2Str.charAt(i+4)+ myHistoryStr.charAt(i+5) + oppHistory1Str.charAt(i+5) + oppHistory2Str.charAt(i+5) + myHistoryStr.charAt(i+4) + oppHistory1Str.charAt(i+6) + oppHistory2Str.charAt(i+6);
					}
				}
			}
			
			return "";
		}
		
		private int findBestMove(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
			String scenario = findMatch(n , myHistory, oppHistory1, oppHistory2);
			
			//if a coalition has been achieved even with only one opponent, return 0
			if(scenario.equals("000000000") || scenario.equals("010010010") || scenario.equals("001001001")) {
				return 0;
			//if one of the opponents offered peace and it was unsuccessful, try accepting but only once
			}else if(peaceOffered == false && 
					(scenario.equals("101101111") 
							|| scenario.equals("110110111") 
							|| scenario.equals("010110111")
							|| scenario.equals("010110111"))) {
				peaceOffered = true;
				return 0;
			}
			return 1;
		}
}

