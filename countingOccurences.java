import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
You are given an array of N numbers, each a number between 1 and 10.

You need to answer Q queries, each specified by a, b, c, and asking how many times the number c appears between position a and position b, inclusive.

Since the largest test case is very large, you will need to use efficient input and output mechanism in your chosen language.

Input Format

The first line of input contains N and Q.

Each of lines 2 to N+1 contains an integer x that is between 1 and 10, where line i gives the (i-1)-th number.

Each of the next Q lines contains three numbers a, b, c.

Constraints

1 ≤ N ≤ 500,000

1 ≤ Q ≤ 500,000

1 ≤ a ≤ b ≤ N

1 ≤ c ≤ 10

Output Format

Output Q lines, each containing the answer to the Q-th query.
*/

public class countingOccurences {

    public static void main(String[] args) throws IOException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        FastReader in = new FastReader();
        PrintWriter outt = new PrintWriter(System.out);
        int cases = in.nextInt();
        int queries = in.nextInt();
        
        //int[] list = new int[cases];
        int[] values = new int[cases];
        int[][] list = new int[cases][10];
        int[] counts = {0,0,0,0,0,0,0,0,0,0};
        for (int a = 0; a < cases; a++) {
            int num = in.nextInt();
            values[a] = num;
            counts[num - 1]++;
            list[a][0] = counts[0];
            list[a][1] = counts[1];
            list[a][2] = counts[2];
            list[a][3] = counts[3];
            list[a][4] = counts[4];
            list[a][5] = counts[5];
            list[a][6] = counts[6];
            list[a][7] = counts[7];
            list[a][8] = counts[8];
            list[a][9] = counts[9];
        }
        for (int b = 0; b < queries; b++) {
            int start = in.nextInt();
            int end = in.nextInt();
            int num = in.nextInt();
            /*
            System.out.println("num = " + num );
            for (int c = 0; c < 10; c++) {
                System.out.print(list[start - 1][c] + " ");
            }
            System.out.println();
            for (int d = 0; d < 10; d++) {
                System.out.print(list[end - 1][d] + " ");
            }
            System.out.println(); */
            if (values[start - 1] == num) {
                outt.println(list[end - 1][num-1] - (list[start - 1][num-1] - 1));
            }
            else {
                outt.println(list[end - 1][num-1] - list[start - 1][num-1]);
            }
        }   
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