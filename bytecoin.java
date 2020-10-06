import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
Barry bought a type of cryptocurrency called Bytecoin some time ago. Bytecoin's price has been going up and down dramatically, and Barry obsessively checks the price periodically. Barry has a short attention span. If the price is X unit higher than the previous price (the last time Barry checked), he gains X^2 (X squared) amount of happiness. If the price is Y unit lower than the previous price, he losses Y^3 (Y cubed) amount of happiness.

Yesterday Barry finally sold his bytecoin holding, but his obsessiveness has taken a toll on his sanity. While having a dream in class today, he gained the ability to go back in time and stop himself from checking the price once. Being obsessive in nature, Barry wants to make sure that he uses this precious power optimally. That is, he wants to remove one price check (except when he bought it and when he sold it) to result in the most improvement in his cumulative happiness, even if this does not change the amount of money he has.

Input Format

The first line contains a positive integer N that represents the number of price checks Barry has made. The second line contains N positive integers indicating the prices of bytecoin when Barry checked.

Constraints

0 <= N <= 100,000

Each price is between 1 and 10,000

Note that (10,000)^3 will overflow the representation of int in C/C++/java, you should use long in Java and long long in C++.
Also, stick to integer calculation, and do not use pow.

Output Format

Print a single integer, which is the best cumulative happiness Barry can achieve after removing one price check (other than the first and the last) in the sequence.
*/

public class bytecoin {

    public static void main(String[] args) throws IOException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        FastReader in = new FastReader();
        PrintWriter outt = new PrintWriter(System.out);
        int cases = in.nextInt();
        Long[] prices = new Long[cases];
        Long[] sum = new Long[cases];
        prices[0] = in.nextLong();
        sum[0] = Long.valueOf(0);
        Long largest = Long.valueOf(0);
        int index = 0;
        Long biggestChange = Long.MIN_VALUE;
        for (int a = 1; a < cases; a++) {
            prices[a] = in.nextLong();
            Long temp;
            if (prices[a] > prices[a-1]) {
                temp = (prices[a] - prices[a-1]) * (prices[a] - prices[a-1]);
                sum[a] = temp + sum[a-1];
            }
            else {
                temp = (prices[a] - prices[a - 1]) * (prices[a] - prices[a - 1]) * (prices[a] - prices[a - 1]);
                sum[a] = temp + sum[a-1];
            }
            if (a >= 2) {
                if (prices[a - 2] < prices[a]) {
                    Long temp2 = (prices[a] - prices[a-2]) * (prices[a] - prices[a-2]);
                    temp2 = temp2 + sum[a - 2];
                    Long temp3 = temp2 - sum[a];
                    if (temp3 > biggestChange) {
                        biggestChange = temp3;
                        index = a-1;
                    }
                }
                else {
                    Long temp2 = (prices[a] - prices[a-2]) * (prices[a] - prices[a-2]) * (prices[a] - prices[a-2]);
                    temp2 = temp2 + sum[a - 2];
                    Long temp3 = temp2 - sum[a];
                    if (temp3 > biggestChange) {
                        biggestChange = temp3;
                        index = a-1;
                    }
                }
            }
        }
        if (prices[index - 1] < prices[index + 1]) {
            Long temp2 = (prices[index + 1] - prices[index-1]) * (prices[index+1] - prices[index-1]);
            temp2 = temp2 + sum[index - 1];
            Long temp3 = temp2 + (sum[sum.length - 1] - sum[index + 1]);
            outt.println(temp3);
        }
        
        else {
            Long temp2 = (prices[index + 1] - prices[index-1]) * (prices[index+1] - prices[index-1]) * (prices[index+1]                 - prices[index-1]);
            temp2 = temp2 + sum[index - 1];
            Long temp3 = temp2 + (sum[sum.length - 1] - sum[index + 1]);
            outt.println(temp3);
        } 
        //outt.println(biggestChange + " " + index);
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