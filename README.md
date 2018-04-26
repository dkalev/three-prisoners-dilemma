# three-prisoners-dilemma

Design process

I started with very simple agent that starts by cooperation and then cooperates only if both his opponents did in the previous round. The idea behind that was that the only state which gives higher payoff than mutual cooperation U(CCC) is U(DCC). However, both opponents want to deviate from U(DCC), therefore assuming the opponents are rational players, trying to achieve highest utility, they will try to maintain U(CCC) for most of the time. After that I played tournaments with different set of matches to determine where the other players performed better and where the agent can be improved. 

Agent design

The agent always starts by cooperating and continues to cooperate until the 3th round if at least one of the opponents is cooperating. After that until the 10th round it defects if at least one of the opponents has been defecting since the beginning, else acts as tolerant player. After the 10th round in the match, if one of the opponents is acting randomly, the player looks for similar patterns in the history and if it finds a match, acts accordingly. Otherwise defects. If both opponents played the same action the previous round, the player does the same. If none of the scenarios is matched, it defects by default. As the number of rounds is fixed, the player defects in the last round in order to maximize its utility.

Additional notes

The strategy for the first 10 rounds, proved quite necessary when playing against rational players that start by defecting, such as Suspicious Tit for Tat. In tournaments with such players, they rarely won, however they drastically reduced the performance of my player as well, leaving the best performing player to be TolerantPlayer. This happened, because TolerantPlayer cooperated with them, despite the defect in first round and his total utility was much higher.


Results

The following histograms show the number of wins per player (only players which have won a tournament are shown) out of 1000 tournaments. Note: there are different players able to win a tournament, mostly because of RandomPlayer and FreakyPlayer, which add randomness to the matches and skew the stats.

![](images/results_no_frd.PNG?raw=true)

The performance of the player against players who defect in the first round, cannot be improved without loss in performance when those players are not present. However, they rarely win and therefore it is unlikely that many of them will be present in the tournament.

![](images/results_frd.PNG?raw=true)