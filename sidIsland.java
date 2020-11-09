import java.io.*;
import java.util.*;
import java.lang.Math;

/*
Some time ago in a galaxy not too far away, a civilization was devastated by blight. Sid led one group of settlers out of the galaxy and into the arcs and circles of the Egg Nebula. Inside the nebula, the settlers found a beautiful planet, which has many islands. Knowing that other groups will arrive soon, they want to select an island on which they can build their first city that will lay the foundation for a great civilization.

They've created a map of the planet in the form of an M-by-N rectangular grid. Each water cell is represented by 0, and each land cell a digit from 1 to 9, representing land cells with 9 different kinds of resources. Sid wants to find an island that has all 9 different types of resources.

An island is formed by land cells that are connected through edges. Find the size of the biggest island that includes all 9 types of resources.

Input Format

The first line has two positive integers M and N. Each of the next M lines contains N digits. These M lines give the map.

Constraints

1 <= M <= 60, 1 <= N <= 60

Output Format

A single integer, which is the size of the biggest island that includes all 9 kinds of land cells. If no island includes all 9 kinds of cells, output -1.
*/
public class sidIsland {

    public static void main(String[] args) throws IOException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] grid = new int[m][n];
        //ArrayList<Integer> areas = new ArrayList<Integer>();
        for (int a = 0; a < m; a++) {
            String s = in.nextLine();
            for (int b = 0; b < n; b++) {
                grid[a][b] = Character.getNumericValue(s.charAt(b));
                //out.println(grid[a][b]);
            }
        }
        int greatest = -1;
        for (int a = 0; a < m; a++) {
            for (int b = 0; b < n; b++) {
                if (grid[a][b] != 0) {
                    HashMap temp = new HashMap();
                    int area = dfs(grid, a, b, temp);
                    
                    if (temp.containsKey(1) == true && temp.containsKey(2) == true && temp.containsKey(3) == true && temp.containsKey(4) == true && temp.containsKey(5) == true && temp.containsKey(6) == true && temp.containsKey(7) == true && temp.containsKey(8) == true && temp.containsKey(9) == true) {
                        if (area > greatest) {
                            greatest = area;
                        }
                        //out.print("hi");
                    }
                }
            }
        }
        out.print(greatest);
        out.close();
    }
    public static int dfs(int[][] grid, int a, int b, HashMap s) {
        //returns area
        if (a < 0 || b < 0) {
            return 0;
        }
        //split into two ifs to make syntax  easier
        else if (a >= grid.length || b >= grid[0].length || grid[a][b] == 0) {
            return 0;
        }
        else {
            //s.add(grid[a][b]);
            if (s.containsKey(grid[a][b]) == false) {
                s.put(grid[a][b], 1);
            }
            grid[a][b] = 0;
            return 1 + dfs(grid, a + 1, b, s) + dfs(grid, a, b + 1, s) + dfs(grid, a - 1, b, s) + dfs(grid, a, b -1, s);
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