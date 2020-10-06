import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
You are given N strings of length K. Each string consists of lower-case letters.

Some of these strings are anagrams of each other. Two strings are anagrams of each other if one can rearrange characters in one string to obtain the other string.

We say that strings that are anagrams of each other are in the same group. Output information of the group with the most strings.

Input Format

The first line is given two numbers: N and K.

Each of the next N lines contains a string of lower-case letters.

Constraints

1 <= N <= 10,000

1 <= K <= 100

It is guaranteed that there is a unique group with the largest size.

Output Format

The first line of the output is an integer, which is size of the largest group. Each of the next lines include a string in the group, in alphbetical order.
*/

public class anagramCounting {

    public static void main(String[] args) throws IOException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        FastReader in = new FastReader();
        PrintWriter outt = new PrintWriter(System.out);
        int cases = in.nextInt();
        int length = in.nextInt();
        HashMap<String, List<String>> words = new HashMap<String, List<String>>();
        ArrayList<String> keys = new ArrayList<String>();
        for (int a = 0; a < cases; a++) {
            String temp = in.nextLine();
            char tempChar[] = temp.toCharArray(); 
            Arrays.sort(tempChar); 
            String key = new String(tempChar);
            if (words.get(key) == null) {
                //System.out.println("hi");
                List<String> values = new ArrayList<String>();
                values.add(temp);
                words.put(key, values);
                keys.add(key);
            }
            else {
                List<String> values = words.get(key);
                values.add(temp);
                words.put(key, values);
            }
        }
        int largest = 0;
        int index = 0;
        for (int b = 0; b < words.size(); b++) {
            //System.out.println(words.get(keys.get(b)).size());
            if (words.get(keys.get(b)).size() > largest) {
                largest = words.get(keys.get(b)).size();
                index = b;
            }
        }
        outt.println(largest);
        Object[] ss = words.get(keys.get(index)).toArray();
        Arrays.sort(ss);
        for (int c = 0; c < largest; c++) {
            outt.println(ss[c]);
            //outt.println(words.get(keys.get(index)).get(c));
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