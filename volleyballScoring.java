import java.io.*;
import java.util.*;

public class volleyballScoring {
    /*
    In “traditional” volleyball scoring (sometimes called “side-out scoring”, used in the Olympics before the 2000 games when they changed to “rally scoring”), 
    a team can only score a point when they are serving. If a team serves and wins, they add a point to their score. If a team serves and does not get a point, 
    there is no change to the score, and the serve goes to the other team (who then has a chance to score points). These rules mean it is possible to deduce the 
    score of a game based on the knowledge of who won the various serves, assuming that team A always start serving.

    Input Format

    The first line will be a positive number, N, indicating the number of input cases. There will then follow N lines. 
    Each line will start with a positive number S (<= 100), indicating the number of serves in the test case. There will then follow S values. 
    Each value will either be the letter A (indicating Team A won the point) or the letter B (indicating Team B won the point). Team A always starts out serving. 
    The game will go to 15 points, but a team has to win by at least two points ahead to win the game, so it is possible for scores to be higher than 15.

    Constraints

    S<=100

    Output Format

    For each input case, output a line: Match m: The score is x-y. Where m is a number denoting the match (starting from 1), 
    and x and y are Team A’s and Team B’s points, respectively, in the current game. It is possible for a match to end (because a team has reached 15 points or more and is ahead by at least two points). 
    If this happens, disregard any remaining data in the test case, and output the line: Match m: Team A/B has won the match with a score of x-y. (You’ll say which team of A and B has won, of course)
    */
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int team = 0;
        int serves = 0;
        int teamA = 0;
        int teamB = 0;
        boolean gameOver = false;
        
        Scanner s = new Scanner(System.in);
        int matches = s.nextInt();
        for (int a = 0; a < matches; a++) {
            System.out.print("Match " + (a + 1) + ": ");
            teamA = 0;
            teamB = 0;
            team = 0;
            gameOver = false;
            serves = s.nextInt();
            for (int b = 0; b < serves; b++) {
                char c = s.next().charAt(0);
                if (gameOver == false) {
                    if (c == 'A') {
                        if (team == 0) {
                            teamA++;
                        }
                        else {
                            team = 0;
                        }
                    }
                    else if (c == 'B') {
                        if (team == 1) {
                            teamB++;
                        }
                        else {
                            team = 1;
                        }
                    }
                    if ((teamA >= teamB + 2) && teamA >= 15) {
                        gameOver = true;
                        System.out.println("Team A has won the match with a score of " + teamA + "-" + teamB + ".");
                    }
                    if ((teamB >= teamA + 2) && teamB >= 15) {
                        gameOver = true;
                        System.out.println("Team B has won the match with a score of " + teamA + "-" + teamB + ".");
                    }
                }
            }
            if (gameOver == false) {
                System.out.println("The score is " + teamA + "-" + teamB + ".");
            }            
        }
    }
}