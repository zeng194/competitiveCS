import java.io.*;
import java.util.*;

public class encryptionByShifting {
    /*
    Suppose you wnat to encrypt a top secret message like “BoilerUp”. You could use a simple substitution cipher,
    where each letter in the alphabet is replaced with a different letter. However, since some letters of the alphabet appear more frequently than others, 
    these ciphers are easily broken by frequency analysis. A somewhat stronger cipher (but still insecure against modern cryptoanalysis) is the autokey cipher, which will have difference ocurrences of the same letter encrypted to diffreent letters in the ciphertext.

    To encrypt a message, you first select a secret word – say “PURDUE” – and prepend it to the front of the message. 
    This longer string is truncated to the length of the message and called the key, and the 𝑛𝑡ℎ letter of the key is used to encrypt the 𝑛𝑡ℎ letter of the original message. 
    This encryption is done by treating each letter in the key as a cyclic shift value for the corresponding letter in the message, where ‘A’ indicates a shift of 0, ‘B’ a shift of 1, and so on. Using “PURDUE” as the secret word, we would encrypt our message as follows:

    BOILERUP (message) PURDUEBO (key) QIZOYVVD (ciphertext)

    Input Format

    Input consists of two lines. The first contains the ciphertext and the second contains the secret word (which may be longer than the ciphertext). Both lines contain only uppercase alphabetic letters.

    Constraints

    Both the ciphertext and the secret word are between 1 and 500 letters long.

    Output Format

    Display the original message that generated the given ciphertext using the given secret word.
*/
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        String cipher = s.nextLine();
        String key = s.nextLine();
        String edit = "";
        char c;
        if (key.length() >= cipher.length()) {
            for (int a = 0; a < cipher.length(); a++) {
                if ((int)cipher.charAt(a) >= (int)key.charAt(a)) {
                    c = (char)((int)cipher.charAt(a) - (int)key.charAt(a) + 65);
                }
                else {
                    c = (char)(91 - ((int)key.charAt(a) - (int)cipher.charAt(a)));
                }
                edit = edit + c;
            }
        } 
        else {
            int counter = 0;
            int messageCounter = 0;
            while (counter < cipher.length()) {
                if (counter < key.length()) {
                    if ((int)cipher.charAt(counter) >= (int)key.charAt(counter)) {
                        c = (char)((int)cipher.charAt(counter) - (int)key.charAt(counter) + 65);
                    }
                    else {
                        c = (char)(91 - ((int)key.charAt(counter) - (int)cipher.charAt(counter)));
                    }
                    edit = edit + c;
                    counter++;
                }
                else {
                    if ((int)cipher.charAt(counter) >= (int)edit.charAt(messageCounter)) {
                        c = (char)((int)cipher.charAt(counter) - (int)edit.charAt(messageCounter) + 65);
                    }
                    else {
                        c = (char)(91 - ((int)edit.charAt(messageCounter) - (int)cipher.charAt(counter)));
                    }
                    counter++;
                    messageCounter++;
                    edit = edit + c;
                }
            }
        }
        System.out.println(edit);
    }
}