import java.io.*;
import java.util.*;

/*
You may have heard about Hilbert's hotel, which has an infinite number of rooms. Even when all rooms are occupied by guests, it can still accommodate infinitely many new groups, each having an infinite number of guests.

Dilbert is building a new hotel and wants to ensure that it has sufficient number of rooms for the next one million years. Even though Dilbert has not learned the technique to build a hotel with an infinite number of rooms, he does possess a crystal ball, which tells him on which day a group would arrive, how many rooms the group will need, and how many days they will stay.

Your are asked to write a program to help Dilbert decide how many rooms he needs in his hotel.

Input Format

The first line has one positive integer N, which is the number of groups. Each of the next N lines has three positive integers, A_i (the number of rooms needed by the group), B_i (the day on which the group arrives), and C_i (the number of nights the group stays).

Constraints

1 <= N <= 50,000 1 <= A_i <= 50,000 1 <= B_i <= 365,250,000 1 <= C_i <= 10^6

Output Format

One number R, which is the minimum number of rooms that is sufficient to accommodate the guests.
*/

public class dilbertsHotel {

    public static void main(String[] args) throws IOException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        FastReader in = new FastReader();
        PrintWriter outt = new PrintWriter(System.out);
        
        int groups = in.nextInt();
        int[][] rooms = new int[groups][3];
        
        rooms[0][1] = in.nextInt();
        rooms[0][0] = in.nextInt();
        rooms[0][2] = rooms[0][0] + in.nextInt();
        
        
        int[] sum = new int[groups];
        int largest = 0;
        for (int a = 1; a < groups; a++) {
            int temp1 = in.nextInt();
            int temp2 = in.nextInt();
            rooms[a][0] = temp2;
            rooms[a][1] = temp1;
            rooms[a][2] = rooms[a][0] + in.nextInt();
            
        }
        mergeSort(rooms, groups);
        int[] arrl = new int[groups];
        int[] exit = new int[groups];
        for (int b = 0; b < groups; b++) {
            arrl[b] = rooms[b][0];
            exit[b] = rooms[b][2];
        }
        
        
        
        int guests_in = rooms[0][1], max_guests = 0;
        int i = 1, j = 0;
        while (i < groups && j < groups) { 
            if (arrl[i] < exit[j]) { 
                guests_in = guests_in + rooms[i][1]; 
                if (guests_in > max_guests) { 
                    max_guests = guests_in; 
                } 
                i++;
            } 
            else {
                guests_in = guests_in - rooms[j][1]; 
                j++; 
            } 
        } 
        outt.println(max_guests);
        
        
        
        
        
        
        outt.close();
    }
    
    
    
    
    
    
    
    
    
    
    public static void mergeSort(int[][] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[][] l = new int[mid][3];
        int[][] r = new int[n - mid][3];
        
        for (int i = 0; i < mid; i++) {
            l[i][0] = a[i][0];
            l[i][1] = a[i][1];
            l[i][2] = a[i][2];
            
        }
        for (int i = mid; i < n; i++) {
            r[i - mid][0] = a[i][0];
            r[i-mid][1] = a[i][1];
            r[i-mid][2] = a[i][2];
          
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);
        

        merge(a, l, r, mid, n - mid);
        
    }
    public static void merge(
        int[][] a, int[][] l, int[][] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i][0] <= r[j][0]) {
                a[k][1] = l[i][1];
                a[k][2] = l[i][2];
                a[k++][0] = l[i++][0];
                
            }
            else {
                 a[k][1] = r[j][1];
                a[k][2] = r[j][2];
                a[k++][0] = r[j++][0];
               
            }
        }
        while (i < left) {
            a[k][1] = l[i][1];
            a[k][2] = l[i][2];
            a[k++][0] = l[i++][0];
             
        }
        while (j < right) {
            a[k][1] = r[j][1];
            a[k][2] = r[j][2];
            a[k++][0] = r[j++][0];
            
        }
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