package players;
/*
 * True Peace Maker (hybrid of Tit For Tat and Tit For Two Tats with Random Co-operation) 
 * - Co-operate unless opponent defects twice in a row, then defect once, but sometimes make
 *  peace by co-operating in lieu of defecting.* 
 */

class TruePeaceMakerPlayer extends Player{
	public int selectAction(int n, int[] myHistory, int[] oppHistory1, int[] oppHistory2) {
		if(n > 1) {
			if((oppHistory1[n-1] == 1 && oppHistory1[n-2] == 1) || (oppHistory2[n-1] == 1 && oppHistory2[n-2] == 1)) {
				if (Math.random() < 0.8)
					return 1;
				else
					return 0;
			}
		}
		return 0;
	}
}