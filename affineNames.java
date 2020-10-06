import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
You are given a sequence of N names, each has a first name, middle name, and last name. A pair of names A and B are said to be affine if and only if the three-letter initial of the name A are the first three letters of at least one of the first, middle, or last name in name B, and vice versa.

For example, the names "Alice Nancy Ianto" and "Isabella Anika Nate" are a pair of affine names, because the three-letter initial of the "Alice Nancy Ianto" is ANI, which starts the middle name Anika, and the initial of "Isabella Anika Nate" is IAN, which starts the lastname Ianto.

Compute the number of pairs of affine names.

Input Format

The first line gives N, which is the number of names.

Each of the next N lines contains three strings, given one person's first, middle, and last names.

Constraints

1 <= N <= 10^5

Each first, middle, or last name has at least 3 characters, and at most 20 characters. No two person will have exactly the same name, i.e., first, middle, and last, are all the same. It is guaranteed that the answer will be no more than 2*10^5.

Output Format

The output contains a single integer, the number of pairs of affine names. A name may be affine with itself, and this is not considered a pair.
*/

public class affineNames {

    public static void main(String[] args) throws IOException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        FastReader in = new FastReader();
        PrintWriter outt = new PrintWriter(System.out);
        int cases = in.nextInt();
        HashMap<String, HashSet<String>> words = new HashMap<String, HashSet<String>>();
        int pairs = 0;
        for (int a = 0; a < cases; a++) {
            String first = in.next().toLowerCase(), middle = in.next().toLowerCase(), last = in.next().toLowerCase();
            String full = first + middle + last;
            String key = "" + first.charAt(0) + middle.charAt(0) + last.charAt(0);
            String newfirst = key + first.substring(0,3);
            String newmiddle = key + middle.substring(0,3);
            String newlast = key + last.substring(0,3);
            String newfirst2 = first.substring(0,3) + key;
            String newmiddle2 = middle.substring(0,3) + key;
            String newlast2 = last.substring(0,3) + key;
            
            if (words.get(newfirst) != null) {
                pairs = pairs + words.get(newfirst).size();
            }
            if (!newmiddle.equals(newfirst)) {
                if (words.get(newmiddle) != null) {
                    pairs = pairs + words.get(newmiddle).size();
                }
            }
            if (!newmiddle.equals(newlast) && !newfirst.equals(newlast)) {
                if (words.get(newlast) != null) {
                    pairs = pairs + words.get(newlast).size();
                }
            }
            if (words.get(newfirst2) == null) {
                words.put(newfirst2, new HashSet<>());
            }
            words.get(newfirst2).add(full);
            if (words.get(newmiddle2) == null) {
                words.put(newmiddle2, new HashSet<>());
            }
            words.get(newmiddle2).add(full);
            if (words.get(newlast2) == null) {
                words.put(newlast2, new HashSet<>());
            }
            words.get(newlast2).add(full);
        }
        outt.println(pairs);
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