package ElementaryAlgorithm.String;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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

    //------字符串中的第一个唯一字符-------
    // 貌似只有两次遍历的解法，除非使用Java的api
    public static int firstUniqChar(String s) {
        Map<Character,Integer> letter = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (letter.containsKey(s.charAt(i))){
                letter.put(s.charAt(i),-1);
                continue;
            }
            letter.put(s.charAt(i),i);
        }
        int index = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> characterIntegerEntry : letter.entrySet()) {
            if (characterIntegerEntry.getValue() != -1 && characterIntegerEntry.getValue() < index){
                index = characterIntegerEntry.getValue();
            }
        }
        return index == Integer.MAX_VALUE ? -1:index;
    }

    //------有效的字母异位词------
    /*
     * 1.第一想法：可以相对两个字符串排序，再对比
     * 2.看到存在相同找不同想到亦或，但是会有一些情况无法解决 ----- “aa”和“bb”；单纯使用运算貌似无法解决这种问题
     * 3，记录字符串的字符频数，第一个字符串的字符频数减去第二个字符串的，最后判断是否等于0.暴力需要三次，优化后i需要两次遍历
     * 4.下方采用的方法只需遍历一次,记录新出现的字符。
     */
    public static boolean isAnagram(String s, String t) {
        //以下解法存在漏解的情况
        if (s.length() != t.length())
            return false;
//        int result = 0;
//        for (int i = 0; i < s.length(); i++) {
//           result ^= (s.charAt(i) - 'a');
//           result ^= (t.charAt(i) - 'a');
//        }
//
//        return result == 0?true:false;
        int[] map = new int[26];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (++map[s.charAt(i)-'a'] == 1){
                count++;
            }
            if (--map[t.charAt(i)-'a'] == 0){
                count--;
            }
        }
        return count == 0 ?true:false;
    }

    //------验证回文字符串------
    public static boolean isPalindrome(String s) {
        if (s.length() == 0)
        return true;
        int left = 0, right = s.length() - 1;
        while (left < right) {
            //因为题中说了，只考虑字母和数字，所以不是字母和数字的先过滤掉
            while (left < right && !Character.isLetterOrDigit(s.charAt(left)))
                left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right)))
                right--;
            //然后把两个字符变为小写，在判断是否一样，如果不一样，直接返回false
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)))
                return false;
            left++;
            right--;
        }
        return true;
    }

    //------字符串转换整数 (atoi)------
    public static int myAtoi(String s) {
        boolean flag = false;
        boolean get = false;
        int sum = 0;
        int sign = 1;
        for (int i = 0; i < s.length(); i++) {
            if (get==false&&' ' == s.charAt(i)) continue;
            if (get==false&&'-' == s.charAt(i) && flag != true) {flag = true;sign = -1;continue;}
            if (get==false&&'+' == s.charAt(i) && flag != true) {flag = true;sign = 1;continue;}
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                get = true;
                int number = s.charAt(i) - '0';
                int temp = sum;
                sum = 10*sum + number;
                if (sum/10 != temp){

                    break;
                }
                continue;
            }
            break;
        }
        if (sign == -1){
            return -1*sum <= Integer.MIN_VALUE? Integer.MIN_VALUE:-1 * sum;
        }
        return sum >= Integer.MAX_VALUE? Integer.MAX_VALUE:(int) sum;
    }

    //-----替换空格-----
    public static String replaceSpace(String s) {
        if (null == s || s.length() == 0){
            return s;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = s.length()-1; i >= 0; i--) {
            if (s.charAt(i) != ' '){
                stringBuilder.append(s.charAt(i));
                continue;
            }
            stringBuilder.append("02%");
        }

        stringBuilder.reverse();
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        //------反转字符穿-----
//        char[] s = "hello".toCharArray();
//        reverseString(s);
//        System.out.println(Arrays.toString(s));

        //------整数反转-----
//        System.out.println(reverse(1534236469));
//        int i = -2147483648;
//        System.out.println();

        //------字符串中的第一个唯一字符-------
//        String letters = "aabb";
//        System.out.println(firstUniqChar(letters));

        //------有效的字母异位词------
//        System.out.println(isAnagram("ll","ee"));

        //------验证回文字符串------
//        System.out.println(isPalindrome("0P"));

        //字符串转整数
//        System.out.println(myAtoi("9223372036854775808"));

        //-----替换空格-----
        System.out.println(replaceSpace("we are happy"));

    }
}
