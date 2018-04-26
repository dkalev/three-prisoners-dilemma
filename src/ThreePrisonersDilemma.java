import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import players.DaEvilPlayer;
import players.DaPlayer;
import players.FreakyPlayer;
import players.NastyPlayer;
import players.NicePlayer;
import players.Player;
import players.RandomPlayer;
import players.SuspiciousT4TPlayer;
import players.T4TPlayer;
import players.TolerantPlayer;
public class ThreePrisonersDilemma {

	/*
	 This Java program models the two-player Prisoner's Dilemma game.
	 We use the integer "0" to represent cooperation, and "1" to represent
	 defection.

	 Recall that in the 2-players dilemma, U(DC) > U(CC) > U(DD) > U(CD), where
	 we give the payoff for the first player in the list. We want the three-player game
	 to resemble the 2-player game whenever one player's response is fixed, and we
	 also want symmetry, so U(CCD) = U(CDC) etc. This gives the unique ordering

	 U(DCC) > U(CCC) > U(DDC) > U(CDC) > U(DDD) > U(CDD)

	 The payoffs for player 1 are given by the following matrix: */

	static int[][][] payoff = {
		{{6,3},  //payoffs when first and second players cooperate
		 {3,0}}, //payoffs when first player coops, second defects
		{{8,5},  //payoffs when first player defects, second coops
	     {5,2}}};//payoffs when first and second players defect

	/*
	 So payoff[i][j][k] represents the payoff to player 1 when the first
	 player's action is i, the second player's action is j, and the
	 third player's action is k.

	 In this simulation, triples of players will play each other repeatedly in a
	 'match'. A match consists of about 100 rounds, and your score from that match
	 is the average of the payoffs from each round of that match. For each round, your
	 strategy is given a list of the previous plays (so you can remember what your
	 opponent did) and must compute the next action.  */
	
	/* In our tournament, each pair of strategies will play one match against each other.
	 This procedure simulates a single match and returns the scores. */
	float[] scoresOfMatch(Player A, Player B, Player C, int rounds) {
		int[] HistoryA = new int[0], HistoryB = new int[0], HistoryC = new int[0];
		float ScoreA = 0, ScoreB = 0, ScoreC = 0;
		for (int i=0; i<rounds; i++) {
			int PlayA = A.selectAction(i, HistoryA, HistoryB, HistoryC);
			int PlayB = B.selectAction(i, HistoryB, HistoryC, HistoryA);
			int PlayC = C.selectAction(i, HistoryC, HistoryA, HistoryB);
			ScoreA = ScoreA + payoff[PlayA][PlayB][PlayC];
			ScoreB = ScoreB + payoff[PlayB][PlayC][PlayA];
			ScoreC = ScoreC + payoff[PlayC][PlayA][PlayB];
			HistoryA = extendIntArray(HistoryA, PlayA);
			HistoryB = extendIntArray(HistoryB, PlayB);
			HistoryC = extendIntArray(HistoryC, PlayC);
		}
		float[] result = {ScoreA/rounds, ScoreB/rounds, ScoreC/rounds};
		return result;
	}

//	This is a helper function needed by scoresOfMatch.
	int[] extendIntArray(int[] arr, int next) {
		int[] result = new int[arr.length+1];
		for (int i=0; i<arr.length; i++) {
			result[i] = arr[i];
		}
		result[result.length-1] = next;
		return result;
	}

	/* The procedure makePlayer is used to reset each of the Players
	 (strategies) in between matches. When you add your own strategy,
	 you will need to add a new entry to makePlayer, and change numPlayers.*/

	int numPlayers = 9;
	Player makePlayer(int which) {
		switch (which) {
		case 0: return new NicePlayer();
		case 1: return new NastyPlayer();
		case 2: return new RandomPlayer();
		case 3: return new TolerantPlayer();
		case 4: return new FreakyPlayer();
		case 5: return new T4TPlayer();
		case 6: return new DaPlayer();
		case 7: return new SuspiciousT4TPlayer();
		case 8: return new DaEvilPlayer();
		}
		throw new RuntimeException("Bad argument passed to makePlayer");
	}

	/* Finally, the remaining code actually runs the tournament. */

	static boolean verbose = false; // set verbose = false if you get too much text output

	public static void main (String[] args) {
		if(args.length == 1)
			verbose = Boolean.valueOf(args[0]);

		ThreePrisonersDilemma instance = new ThreePrisonersDilemma();
		String winner;
		Map<String, Integer> map = new HashMap<String, Integer>();

		for(int i = 0; i < 1000; i++){
			if(i % 1000 == 0)
				System.out.println(i/1000);
			winner = instance.runTournament();
			Integer count = map.get(winner);
			map.put(winner, count != null ? count+1 : 1);
		}
		Entry<String, Integer> max = null;

		for (Entry<String, Integer> e : map.entrySet()) {
			System.out.println(e.getKey() + "," + e.getValue());
			if (max == null || e.getValue() > max.getValue())
				max = e;
		}

		System.out.println(max.getKey());
	}



	public String runTournament() {
		float[] totalScore = new float[numPlayers];

		// This loop plays each triple of players against each other.
		// Note that we include duplicates: two copies of your strategy will play once
		// against each other strategy, and three copies of your strategy will play once.

		for (int i=0; i<numPlayers; i++) for (int j=i; j<numPlayers; j++) for (int k=j; k<numPlayers; k++) {

			Player A = makePlayer(i); // Create a fresh copy of each player
			Player B = makePlayer(j);
			Player C = makePlayer(k);
			int rounds = 100;
			float[] matchResults = scoresOfMatch(A, B, C, rounds); // Run match
			totalScore[i] = totalScore[i] + matchResults[0];
			totalScore[j] = totalScore[j] + matchResults[1];
			totalScore[k] = totalScore[k] + matchResults[2];
			if (verbose)
				System.out.println(A.name() + " scored " + matchResults[0] +
						" points, " + B.name() + " scored " + matchResults[1] +
						" points, and " + C.name() + " scored " + matchResults[2] + " points.");
		}
		
		int[] sortedOrder = new int[numPlayers];

		// This loop sorts the players by their score.
		for (int i=0; i<numPlayers; i++) {
			int j=i-1;
			for (; j>=0; j--) {
				if (totalScore[i] > totalScore[sortedOrder[j]])
					sortedOrder[j+1] = sortedOrder[j];
				else break;
			}
			sortedOrder[j+1] = i;
		}

		// Finally, print out the sorted results.
		if (verbose) {
		System.out.println();
		System.out.println("Tournament Results");
		for (int i=0; i<numPlayers; i++)
			System.out.println(makePlayer(sortedOrder[i]).name() + ": "
				+ totalScore[sortedOrder[i]] + " points.");
		}
		return makePlayer(sortedOrder[0]).name();
	} // end of runTournament()

} // end of class PrisonersDilemma
