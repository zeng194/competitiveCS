import java.io.*;
import java.util.*;

/*
You just started your journey in the exciting field of data mining and machine learning. The first algorithm you just learned is the k nearest neighbors (kNN) classifier. To "train" such a classifer, you need a number of training instances. To predict the class of a given instance, the kNN classifier finds the k training instances that are the cloest to the instance, and then use the majority class of these instances to predict the class of the given instance.

You are asked to implement a 3-NN binary classifier. Each training instance has a single numerical feature, and a class label that is either -1 or 1. The distance between two instances are defined to be the absolute value of the difference between their feature values. Given the feature value of a test instance, your classifier finds 3 closest training instances. If 2 or more of the 3 instances have label 1, the classifier predicts 1. Otherwise, the classfier predicts -1.

It is guaranteed that there are no two training instances that have exactly the same feature value. Since two instances may have the same distance to the target instance, it is possible that two training instances are tied as the third closest instances, in which case you should consider the 4 closest neighbors. If they happen to have two 1, and two -1, then the output class should be 0.

Input Format

The first line has two numbers N, the number of training instances, and Q, the number of queries.

Lines 2 to N+1 each contains one pair (F,C), where F is the feature value, and C is the class.

Line N+2 contains Q numbers, each F gives the feature of one query instance.

Constraints

3 <= N <= 10^4
1 <= Q <= 10^4
-10^18 <= F <= 10^18
C in {-1, 1}
Output Format

The output consists of one line of Q numbers, separated by spaces. Each number is the class of one instance, in the order that they are given. Each number should be in {-1,0,1}.
*/
public class threeNearestNeighbors {    
    public static void main(String[] args) throws IOException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        //FastReader in = new FastReader();
        FastReader in = new FastReader();
        PrintWriter outt = new PrintWriter(System.out);
        int cases = in.nextInt();
        int queries = in.nextInt();
        Long add;
        int clas = 0;
        HashMap<Long, Integer> map = new HashMap<>();
        Long[] f = new Long[queries];
        Long[] caseList = new Long[cases];
        for (int b = 0; b < cases; b++) {
            add = in.nextLong();
            clas = in.nextInt();
            caseList[b] = add;
            map.put(add, clas);
            //System.out.println(map.get(add));
        }
        for (int a = 0; a < queries; a++) {
            f[a] = in.nextLong();
        }
        Long[] sortedCases = bubbleSort(caseList);
        Long key = Long.MAX_VALUE;
        Long diff;
        Long smalldiff = Long.MAX_VALUE;
        int index = -1;
        Long[] keys = new Long[4];
        Long[] diffs = new Long[4];
        
        for (int c = 0; c < queries; c++) {
            smalldiff = Long.MAX_VALUE;
            for (int d = 0; d < cases; d++) {
                diff = Math.abs(sortedCases[d] - f[c]);
                if (diff < smalldiff) {
                    smalldiff = diff;
                    key = sortedCases[d];
                    index = d;
                }
            }
            diffs[0] = smalldiff;
            keys[0] = key;
            Long[] diffcompare = new Long[4];
            Long[] keycompare = new Long[4];
    
            if (index - 2 >= 0 && index + 2 < cases) {
                diffcompare[0] = Math.abs(sortedCases[index + 1] - f[c]);
                keycompare[0] = sortedCases[index + 1];
                diffcompare[1] = Math.abs(sortedCases[index + 2] - f[c]);
                keycompare[1] = sortedCases[index + 2];
                diffcompare[2] = Math.abs(sortedCases[index - 1] - f[c]);
                keycompare[2] = sortedCases[index - 1];
                diffcompare[3] = Math.abs(sortedCases[index - 2] - f[c]);
                keycompare[3] = sortedCases[index - 2];
                Long[][] dub = doubleBubble(diffcompare, keycompare);
                diffcompare = dub[0];
                keycompare = dub[1];
                diffs[1] = diffcompare[0];
                diffs[2] = diffcompare[1];
                diffs[3] = diffcompare[2];
                keys[1] = keycompare[0];
                keys[2] = keycompare[1];
                keys[3] = keycompare[2];
            }
            else if (index - 1 >=0 && index + 3 < cases) {
                diffcompare[0] = Math.abs(sortedCases[index + 1] - f[c]);
                keycompare[0] = sortedCases[index + 1];
                diffcompare[1] = Math.abs(sortedCases[index + 2] - f[c]);
                keycompare[1] = sortedCases[index + 2];
                diffcompare[2] = Math.abs(sortedCases[index - 1] - f[c]);
                keycompare[2] = sortedCases[index - 1];
                diffcompare[3] = Math.abs(sortedCases[index + 3] - f[c]);
                keycompare[3] = sortedCases[index + 3];
                Long[][] dub = doubleBubble(diffcompare, keycompare);
                diffcompare = dub[0];
                keycompare = dub[1];
                diffs[1] = diffcompare[0];
                diffs[2] = diffcompare[1];
                diffs[3] = diffcompare[2];
                keys[1] = keycompare[0];
                keys[2] = keycompare[1];
                keys[3] = keycompare[2];
            
            }
            else if (index - 2 < 0 && index + 4 < cases) {
                diffcompare[0] = Math.abs(sortedCases[index + 1] - f[c]);
                keycompare[0] = sortedCases[index + 1];
                diffcompare[1] = Math.abs(sortedCases[index + 2] - f[c]);
                keycompare[1] = sortedCases[index + 2];
                diffcompare[2] = Math.abs(sortedCases[index + 3] - f[c]);
                keycompare[2] = sortedCases[index + 3];
                diffcompare[3] = Math.abs(sortedCases[index + 4] - f[c]);
                keycompare[3] = sortedCases[index + 4];
                Long[][] dub = doubleBubble(diffcompare, keycompare);
                diffcompare = dub[0];
                keycompare = dub[1];
                diffs[1] = diffcompare[0];
                diffs[2] = diffcompare[1];
                diffs[3] = diffcompare[2];
                keys[1] = keycompare[0];
                keys[2] = keycompare[1];
                keys[3] = keycompare[2];
            }
            else if (index - 3 >= 0 && index + 1 < cases && index + 2 >= cases) {
                diffcompare[0] = Math.abs(sortedCases[index + 1] - f[c]);
                keycompare[0] = sortedCases[index + 1];
                diffcompare[1] = Math.abs(sortedCases[index - 3] - f[c]);
                keycompare[1] = sortedCases[index - 3];
                diffcompare[2] = Math.abs(sortedCases[index - 1] - f[c]);
                keycompare[2] = sortedCases[index - 1];
                diffcompare[3] = Math.abs(sortedCases[index - 2] - f[c]);
                keycompare[3] = sortedCases[index - 2];
                Long[][] dub = doubleBubble(diffcompare, keycompare);
                diffcompare = dub[0];
                keycompare = dub[1];
                diffs[1] = diffcompare[0];
                diffs[2] = diffcompare[1];
                diffs[3] = diffcompare[2];
                keys[1] = keycompare[0];
                keys[2] = keycompare[1];
                keys[3] = keycompare[2];
            }
            else if (index - 4 >= 0 && index + 2 >= cases) {
                diffcompare[0] = Math.abs(sortedCases[index -4] - f[c]);
                keycompare[0] = sortedCases[index -4];
                diffcompare[1] = Math.abs(sortedCases[index -3] - f[c]);
                keycompare[1] = sortedCases[index -3];
                diffcompare[2] = Math.abs(sortedCases[index - 1] - f[c]);
                keycompare[2] = sortedCases[index - 1];
                diffcompare[3] = Math.abs(sortedCases[index - 2] - f[c]);
                keycompare[3] = sortedCases[index - 2];
                Long[][] dub = doubleBubble(diffcompare, keycompare);
                diffcompare = dub[0];
                keycompare = dub[1];
                diffs[1] = diffcompare[0];
                diffs[2] = diffcompare[1];
                diffs[3] = diffcompare[2];
                keys[1] = keycompare[0];
                keys[2] = keycompare[1];
                keys[3] = keycompare[2];
            }
            //System.out.println(diffs[0] + " " + diffs[1] + " " + diffs[2] + " " + diffs[3]);
            //System.out.println(keys[0] + " " + keys[1] + " " + keys[2] + " " + keys[3]);
            int one = 0;
            int negative = 0;
            boolean tie = false;
            
            if (diffs[2].equals(diffs[3])) {
                tie = true;
            }
            for (int i = 0; i < 4; i++) {
                if (i == 3 && tie == false) {
                    break;
                }
                if (map.get(keys[i]) == 1) {
                    one++;
                }
                else {
                    negative++;
                }
                //System.out.print(diffs[i] + " ");
            }
            //System.out.println();
            
            if (one > negative) {
                System.out.print("1 ");
            }
            else if (negative > one) {
                System.out.print("-1 ");
            }
            else {
                System.out.print("0 ");
            } 
        }
        outt.close();
    }
        
    
    static Long[] bubbleSort(Long arr[]) 
    { 
        int n = arr.length; 
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++){ 
                if (arr[j] > arr[j+1]) 
                { 
                    // swap arr[j+1] and arr[i] 
                    Long temp = arr[j]; 
                    arr[j] = arr[j+1]; 
                    arr[j+1] = temp; 
                } 
            }
        }
        return arr;
    } 
    
    static Long[][] doubleBubble(Long arr[], Long arr2[]) 
    { 
        int n = arr.length; 
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++){ 
                if (arr[j] > arr[j+1]) 
                { 
                    // swap arr[j+1] and arr[i] 
                    Long temp = arr[j]; 
                    arr[j] = arr[j+1]; 
                    arr[j+1] = temp; 
                    
                    Long temp2 = arr2[j];
                    arr2[j] = arr2[j + 1];
                    arr2[j + 1] = temp2;
                } 
            }
        }
        Long[][] dub = {arr, arr2};
        return dub;
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
















