import java.io.*;
import java.util.*;
import java.lang.Math;

/*
Given a sorted list of integers and two bounds a,b: find the indices for the range of numbers ni in the list such that a <= ni <= b.

If the range would have size 0, output -1 -1 instead.

Your program must have O(log n) runtime.

Input Format

The first line contains a single integer M, the number of integers in the sorted list.

The second line contains two integers a and b.

The third line contains M space-separated integers in increasing order.

Constraints

1 <= M <= 10^7
a < b
a, b, and all array entries are guaranteed to fit in a 32-bit signed integer.

Output Format

Output two numbers : the index of the first and last element in the range, respectively
*/

public class firstToLast {

    public static void main(String[] args) throws IOException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int left = in.nextInt();
        int right = in.nextInt();
        int[] array = new int[n];
        array[0] = in.nextInt();
        int first = -1;
        int second = -1;
        boolean f = true;
        for (int a = 1; a < n; a++) {
            array[a] = in.nextInt();
            if (array[a] > right && array[a-1] < left) {
                f = false;
                break;
            }
        }
        if (f == true) {
            first = search(array,left,array.length,1);
            second = search(array,right,array.length,2);
        }
        out.println(first + " " + second);
        out.close();
    }
    private static int search(int[] array, int key, int size, int which) {
        int first = 0;
        int last = size-1;
        int mid =(first + last)/2;
        int least = Integer.MAX_VALUE;
        int index = -1;
        while(first <= last){
            if (array[mid] < key){  
                if (which == 2) {
                    if (least >= key - array[mid]) {
                        least = key - array[mid];
                        index = mid;
                    }
                }
                first = mid + 1;
            }
            else if (array[mid] > key){
                if (which == 1) {
                    if (least >= array[mid] - key) {
                        least = array[mid] - key;
                        index = mid;
                    }
                }
                last = mid - 1;
            }
            else{  
                if (which == 1) {
                    for (int a = mid; a >= 0; a--) {
                        if (array[a] != key) {
                            return (a+1);
                        }
                    }
                }
                else {
                    for (int a = mid; a < size; a++) {
                        if (array[a] != key) {
                            return (a-1);
                        }
                    }
                }
                return mid; 
            }  
            mid = (first + last)/2;  
        }  
        return index;
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