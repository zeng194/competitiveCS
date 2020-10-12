import java.io.*;
import java.util.*;
import java.lang.Math;

/*
There are n drawers, numbered from 1 to n, top to bottom.

There are m objects to process. The i-th object belongs in drawer ai. In order to place an object in drawer ai, you must first close all drawers above it, and then open the drawer ai. Initially, all drawers are closed. You must process all objects in the order listed.

After processing each object, output the number of open drawers at that time.

Input Format

The first line contains two integers, n and m --- the number of drawers, and the number of objects, respectively.

The next line contains m integers a1 ... am, where ai is the drawer in which object i belongs.

Constraints

1 <= n <= 10^9
1 <= m <= 2 * 10^5
1 <= ai <= n

Output Format

In one line, output m integers, where the i-th integer is the number of drawers open after putting away the first i objects.
*/

public class drawers {

    public static void main(String[] args) throws IOException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        Stack s = new Stack();
        int drawers = in.nextInt();
        int cases = in.nextInt();
        int num = 1;
        for (int a = 0; a < cases; a++) {
            int val = in.nextInt();
            if (a == 0) {
                out.print(num + " ");
                s.push(val);
            }
            else {
                while ((int)s.peek() < val) {
                    num--;
                    s.pop();
                    if (s.empty() == true) {
                        break;
                    }
                }
                if (s.search(val) == -1) {
                    num++;
                    s.push(val);
                }
                out.print(num + " ");
            }
        }
        out.close();
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