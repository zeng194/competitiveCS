public class longestSub {
    /*
    Given a string s, find the length of the longest substring without repeating characters.
    */

    //faster than 99.85% of submissions, less than 96.49% of submissions for memory use
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int a = 0;
        int b = 1;
        int longest = 1;
        int len = 1;
        int[] letters = new int[127];
        letters[s.charAt(0)]++;
        while (a < s.length() && b < s.length()) {
            if (letters[(int)s.charAt(b)] != 0) {
                while (letters[(int)s.charAt(b)] != 0) {
                    letters[(int)s.charAt(a)]--;
                    a++;
                    len--;
                }
            }       
            letters[(int)s.charAt(b)]++;
            len++;
            b++;
            if (len > longest) {
                longest = len;
                
            }
        }
        return longest;
    }
}