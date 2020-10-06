import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
In a shopping center, there are n stores in a row. Obviously, it is not very profitable to have lots of stores close together that sell the same thing. Specifically, let's call a range of stores [l,r] bad if the number of unique products sold by stores in this range is less than or equal to k. Can you find the longest bad range, so that we can renovate?

Input Format

The first line contains two integers n and k, the number of stores, and the maximum amount of unique products in a range for it to be considered bad.

The next line contains a string of length n. The i-th character in the string denotes the product sold by the -th store. All products are lowercase English letters.

Constraints

1 <= n <= 2 * 10^5
1 <= k <= 26

Output Format

Output a single integer: the length of the longest bad range of stores.
*/

public class shoppingCenter {

    public static void main(String[] args) throws IOException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        FastReader in = new FastReader();
        PrintWriter outt = new PrintWriter(System.out);
        int n = in.nextInt();
        int k = in.nextInt();
        String s = in.nextLine();
        int[] alphabet = new int[26];
        int i = 0;
        int j = 1;
        int uniques = 1;
        int length = 1;
        int max = 1;
        alphabet[(int)s.charAt(0) - 97]++;
        while (i < n && j < n) {
            //System.out.println(length);
            if (uniques > k) {
                //increment i until uniques is <= k
                //decrement length
                //remove letters of i
                while (uniques > k) {
                    if (alphabet[(int)s.charAt(i) - 97] == 1) {
                        uniques--;
                    }
                    alphabet[(int)s.charAt(i)-97]--;
                    i++;
                    length--;
                }
            }
            else {
                if (alphabet[(int)s.charAt(j)-97] == 0) {
                    uniques++;
                }
                alphabet[(int)s.charAt(j) - 97]++;
                length++;
                j++;
                if (length > max && uniques <= k) {
                    max = length;
                }
            }
        }
        /*
        while (uniques > k) {
                    if (alphabet[(int)s.charAt(i) - 97] == 1) {
                        uniques--;
                    }
                    alphabet[(int)s.charAt(i)-97]--;
                    i++;
                    length--;
                } */
        outt.println(max);
        outt.close();
    }
    
    
    static class FastReader { 
        final private int BUFFER_SIZE = 1 << 16; 
        private int MAX_LINE_SIZE = 1 << 16; 
        private DataInputStream din; 
        private byte[] buffer, lineBuf; 
        private int bufferPointer, bytesRead; 
  
        public FastReader() { 
            din = new DataInputStream(System.in); 
            buffer = new byte[BUFFER_SIZE]; 
            lineBuf = new byte[MAX_LINE_SIZE];
            bufferPointer = bytesRead = 0; 
        } 
        public FastReader(String file_name) throws IOException { 
            din = new DataInputStream(new FileInputStream(file_name)); 
            buffer = new byte[BUFFER_SIZE];  
            bufferPointer = bytesRead = 0; 
        } 
        public boolean hasNext() throws IOException { 
            byte c; 
            while ((c = read()) != -1) { 
                if (c > ' ') {        // Find first byte bigger than ' '
                    bufferPointer--;
                    return true;
                }
            }
            return false;
        }
        // return the next line that contains at least one character > ' '
        public String nextLine() throws IOException { 
            int ctr = 0;
            byte c; 
            boolean empty = true;
            while ((c = read()) != -1) { 
                if (c == '\r')        continue;     // ignore '\r'
                if (c == '\n') {
                    if (empty)  { ctr = 0;   continue;  } // read only spaces etc. until \n
                    else        break;
                }
                if (ctr == MAX_LINE_SIZE) {
                    MAX_LINE_SIZE *= 2;
                    lineBuf = Arrays.copyOf(lineBuf, MAX_LINE_SIZE);
                }
                lineBuf[ctr++] = c; 
                if (c > ' ')  empty = false;
            } 
            return new String(lineBuf, 0, ctr); 
        }
        public String next() throws IOException { 
            int ctr = 0;
            byte c = read(); 
            while (c <= ' ')      c = read(); 
            while (c > ' ') {
                if (ctr == MAX_LINE_SIZE) {
                    MAX_LINE_SIZE *= 2;
                    lineBuf = Arrays.copyOf(lineBuf, MAX_LINE_SIZE);
                }
                lineBuf[ctr++] = c;
                c = read();
            }
            return new String(lineBuf, 0, ctr);
        }
        public int nextInt() throws IOException { 
            int ret = 0; 
            byte c = read(); 
            while (c <= ' ')   c = read(); 
            boolean neg = (c == '-'); 
            if (neg)           c = read(); 
            do { 
                ret = ret * 10 + c - '0'; 
            } while ((c = read()) >= '0' && c <= '9'); 
  
            if (neg)           return -ret; 
            return ret; 
        } 
        public long nextLong() throws IOException { 
            long ret = 0; 
            byte c = read(); 
            while (c <= ' ')    c = read(); 
            boolean neg = (c == '-'); 
            if (neg)            c = read(); 
            do { 
                ret = ret * 10 + c - '0'; 
            } while ((c = read()) >= '0' && c <= '9'); 
            if (neg)            return -ret; 
            return ret; 
        } 
        public double nextDouble() throws IOException { 
            double ret = 0, div = 1; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg)      c = read(); 
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
            if (c == '.') { 
                while ((c = read()) >= '0' && c <= '9') { 
                    ret += (c - '0') / (div *= 10); 
                } 
            } 
            if (neg)     return -ret; 
            return ret; 
        } 
        private void fillBuffer() throws IOException { 
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); 
        } 
        private byte read() throws IOException { 
            if (bufferPointer == bytesRead)     fillBuffer(); 
            if (bytesRead <= 0)  return -1;  // No data
            return buffer[bufferPointer++]; 
        } 
        public void close() throws IOException { 
            if (din == null)        return; 
            din.close(); 
        } 
    } 
}