import java.io.*;
import java.util.*;

/*
The casino game craps is played by rolling two dices. A player can place bets before any roll. The outcome for that bet follows the following rules:

If the roll comes out 7 or 11, the player wins the bet
If the roll comes out 2, 3, or 12, the player losses the bet
If the roll comes out any other number P (i.e., P in {4,5,6,8,9,10}), whether the player wins or losses the bet depending on whether P or 7 comes up first in future rolls. If P is rolled a second time before a 7 is rolled, the player wins. Otherwise, the player loses.
One strategy to play craps is to place a new bet on every die roll. It's possible to have several active bets (whose winning status waits for future rolls) at the same time.

Suppose for this problem that all bets are made in the amount of 1 dollar. So, each win adds 1 dollar to the player's bankroll, and each loss subtracts 1 dollar from the player's bankroll. Given a sequence of rolls using this strategy, determine the change to the player's bankroll for the sequence. You can assume that the player never runs out of money.

Input Format

Each input file contains between 1 and 100 sequences, one per line. Each line contain a sequence of integers in the range 2-12 (denoting the number rolled in each turn), followed by the number 0. The last roll in each sequence (i.e., the number just before 0) is always a 7, which will determine the outcomes of all active bets.

Constraints

Each test instance (i.e., each line) contains at most 10^5 numbers.

Output Format

For each sequence ,i, (starting from 1), output one of the following as appropriate:

Sequence i: Win of $X.
Sequence i: Loss of $X.
Sequence i: Break even.
*/
public class ohCraps {

    public static void main(String[] args) throws IOException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int winnings = 0;
        int counter = 0;
        boolean active = false;
        ArrayList<Integer> g = new ArrayList<Integer>();
        List collect = new ArrayList();
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader b = new BufferedReader(in);
        int roll = -1;
        String line = "";
        while ((line = b.readLine()) != null) {
            String[] splat = line.split(" ");
            for (int z = 0; z < splat.length; z++) {
                roll = Integer.parseInt(splat[z]);
                if (roll == 0) {
                    counter++;
                    if (winnings > 0) {
                        System.out.println("Sequence " + counter + ": Win of $" + winnings + ".");
                        winnings = 0;
                    }
                    else if (winnings < 0) {
                        System.out.println("Sequence " + counter + ": Loss of $" + winnings + ".");
                        winnings = 0;
                    }
                    else {
                        System.out.println("Sequence " + counter + ": Break even.");
                    }
                }
                else if (roll == 7 || roll == 11) {
                    winnings++;
                    if (roll == 7 && active == true) {
                        active = false;
                        winnings = winnings - g.size();
                        g = new ArrayList<Integer>();
                    }
                }
                else if (roll == 2 || roll == 3 || roll == 12) {
                    winnings--;
                }
                else {
                    active = true;
                    for (int a = 0; a < g.size(); a++) {
                        if (g.get(a) == roll) {
                            winnings++;
                            g.remove(a);
                            break;
                        }
                    }
                    g.add(roll);
                }
            }
        }
        b.close();
    }
}