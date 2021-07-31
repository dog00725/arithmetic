package ElementaryAlgorithm.String;

import java.util.Arrays;

public class Solution {
    //------反转字符穿-----
    /*
     * 1.可以使用双指针
     */
    public static void reverseString(char[] s) {
        int left = 0;
        int right = s.length-1;
        while (left < right){
            char temp = s[left];
            s[left++] = s[right];
            s[right--] = temp;
        }
    }

    //------整数反转-----
    /*
     * 1. 直接想法将其转为字符数组然后反转，利用NumberFormatException进行判断是否超过范围
     * 2. 利用取余乘10进行反转，在利用除10判断是否和前一个值相等进行判断是否溢出
     */
    public static int reverse(int x) {
        char[] s = String.valueOf(x).toCharArray();
        int left = 0;
        int right = s.length-1;
        while (left < right){
            if (s[left] == '-'){
                left++;
                continue;
            }
            char temp = s[left];
            s[left++] = s[right];
            s[right--] = temp;
        }
        try {
            return Integer.valueOf(new StringBuilder().append(s).toString());
        }catch (NumberFormatException e){
            return 0;
        }
    }

    public static void main(String[] args) {
        //------反转字符穿-----
//        char[] s = "hello".toCharArray();
//        reverseString(s);
//        System.out.println(Arrays.toString(s));

        //------整数反转-----
//        System.out.println(reverse(1534236469));
        int i = -2147483648;
        System.out.println();
    }
}
