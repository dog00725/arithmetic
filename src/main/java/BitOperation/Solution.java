package BitOperation;

/**
 * 位运算
 */

public class Solution {
    //二进制中1的个数
    //循环次数为int类型的长度 32
    public static int hammingWeight_first(int n) {
        int bit = 1;
        int count = 0;
        while (true){
            int temp = n & bit;
            if (bit == 0) break;
            if (temp == bit) count++;
            bit = bit<<1;
        }
        return count;
    }
    //利用n-1后n二进制中最右边的1会变为0同时之后的为变1，再与n做与运算可以将最右边的1变为0
    //循环次数为1的个数
    public static int hammingWeight_second(int n) {
        int count = 0;
        while (n != 0){
            count++;
            n = (n-1)& n;
        }
        return count;
    }
    public static void main(String[] args) {
        //二进制中1的个数
        System.out.println(hammingWeight_first(128));
        System.out.println(hammingWeight_second(128));
    }
}
