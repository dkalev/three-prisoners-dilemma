# three-prisoners-dilemma


## Problem

Two Prisoners Dilemma

Two members of a criminal gang are arrested and imprisoned. Each prisoner is in solitary confinement with no means of communicating with the other. The prosecutors lack sufficient evidence to convict the pair on the principal charge. They hope to get both sentenced to a year in prison on a lesser charge. Simultaneously, the prosecutors offer each prisoner a bargain. Each prisoner is given the opportunity either to: betray the other by testifying that the other committed the crime, or to cooperate with the other by remaining silent. The offer is:

*If A and B each betray the other, each of them serves 2 years in prison
*If A betrays B but B remains silent, A will be set free and B will serve 3 years in prison (and vice versa)
*If A and B both remain silent, both of them will only serve 1 year in prison (on the lesser charge)

Repeated Prisoners Dilemma

In a repeated game, a given game (often thought of in normal form) is played multiple times (possibly
infinitely many times) by the same set of players. A strategy in a repeated game specifies what action the agent should take in each stage of the game,
given all the actions taken by all players in the past.

## Solution

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