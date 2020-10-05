import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class knightAndKing {
    /*
    Given a board with M rows and N columns, where each cell contains a number, you want to move a piece from the top row to the bottom row.

    You can select which cell on the top row for the piece to start, and the piece can move like a knight or a king in chess, but must move downwards in each step. 
    The piece must reach the bottom row. Compute the maximum achievable sum of the numbers on all the cells that the piece lands on.

    Input Format

    The first line of the input contains two numbers M and N. The next M lines each contains N numbers a[i][j]. The first line gives the number on the top row.

    Constraints

    1 <= M, N <= 1000 -10^6 <= a[i][j] <= 10^6

    Output Format

    Output a single number, the maximum sum that can be achieved.
    */
    public static void main(String[] args) throws IOException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        FastReader in = new FastReader();
        PrintWriter outt = new PrintWriter(System.out);
        int cases = in.nextInt();
        int length = in.nextInt();
        int[][] sums = new int[cases][length];
        int[][] vals = new int[cases][length];
        for (int a = 0; a < cases; a++) {
            for (int b = 0; b < length; b++) {
                vals[a][b] = in.nextInt();
                if (a == 0) {
                    sums[0][b] = vals[0][b];
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int a = 1; a < cases; a++) {
            for (int b = 0; b < length; b++) {
                max = Integer.MIN_VALUE;
                if (sums[a - 1][b] + vals[a][b] > max) { //directly above
                    max = sums[a - 1][b] + vals[a][b];
                }
                if (b > 0) { //diagonal left
                    if (sums[a - 1][b - 1] + vals[a][b] > max) {
                        max = sums[a - 1][b - 1] + vals[a][b];
                    }
                }
                if (b < length - 1) { // diagonal right
                    if (sums[a - 1][b + 1] + vals[a][b] > max) {
                        max = sums[a - 1][b + 1] + vals[a][b];
                    }
                }
                if (a >= 2 && b > 0) { //top left horse
                    if (sums[a - 2][b - 1] + vals[a][b] > max) {
                        max = sums[a - 2][b - 1] + vals[a][b];
                    }
                }
                 if (a >= 2 && b < length - 1) { //top right horse
                    if (sums[a - 2][b + 1] + vals[a][b] > max) {
                        max = sums[a - 2][b + 1] + vals[a][b];
                    }
                }
                if (b > 1) { //wide left horse
                    if (sums[a - 1][b - 2] + vals[a][b] > max) {
                        max = sums[a - 1][b - 2] + vals[a][b];
                    }
                }
                if (b < length - 2) { //wide right horse
                    if (sums[a - 1][b + 2] + vals[a][b] > max) {
                        max = sums[a - 1][b + 2] + vals[a][b];
                    }
                }
                sums[a][b] = max;
            }
        }
        max = Integer.MIN_VALUE;
        for (int a = 0; a < length; a++) {
            if (max < sums[cases-1][a]) {
                max = sums[cases-1][a];
            }
        }
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