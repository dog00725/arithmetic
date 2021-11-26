package DynamicProgramming;

/**
 * 动态规划
 */

public class Solution {

    //剪绳子 - 动态规划算法或贪婪算法
    /*
     * 动态规划：f（n）为长度n的绳子划分后最优解，f（n） = max（f（n-j）* f（j））
     * 贪婪算法：可以利用数学公式推导，推导结果可得到尽可能将绳子以长度 33 等分为多段时，乘积最大
     */
    public static int cuttingRope_dynamic(int n) {
        if (n < 2) return 0;
        if (n == 2) return 1;
        if (n == 3) return 2;
        int[] optimal = new int[n+1];
        // TODO: 2021/8/25  为什么 f（1）=1，f（2）=2，f（3）=3
        optimal[1] = 1;
        optimal[2] = 2;
        optimal[3] = 3;
        for (int i = 4; i <= n; i++) {
            int temp = Integer.MIN_VALUE;
            for (int j = 1; j <= i/2; j++) {
                temp = Math.max(optimal[j] * optimal[i - j], temp);
            }
            optimal[i] = temp;
        }
        return optimal[n];
    }

    public static int cuttingRope_greedy(int n){
        if (n < 2) return 0;
        if (n == 2) return 1;
        if (n == 3) return 2;

        int timesOf3 = n/3;
        if (n - 3*timesOf3 == 1){
            timesOf3 -= 1;
        }
        int timesOf2 = (n - 3* timesOf3) /2;
        return (int)(Math.pow(3,timesOf3)*Math.pow(2,timesOf2));
    }

    //两字符串最长公共子序列
    public static int longestCommonSubsequence(String text1, String text2){
        if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0){
            return 0;
        }

        int length1 = text1.length();
        int length2 = text2.length();

        int[][] dp = new int[length1+1][length2+1];
        for (int i=1;i<=length1;i++){
            for (int j=1;j<=length2;j++){
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] +1;
                }else{
                    dp[i][j] =Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[length1][length2];
    }

    //编辑距离
    public static int minDistance(String word1, String word2) {
        if (word1 == null || word1.length() == 0){
            return word2.length();
        }
        if (word2 == null || word2.length() == 0){
            return word1.length();
        }

        int length1 = word1.length();
        int length2 = word2.length();

        int[][] dp = new int[length1+1][length2+1];
        for(int i=0;i<=length1;i++){
            dp[i][0] = i;
        }
        for(int i=0;i<=length2;i++){
            dp[0][i] = i;
        }
        for(int i=1;i<=length1;i++){
            for(int j=1;j<=length2;j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    int min = Integer.MAX_VALUE;
                    //delete
                    min = Math.min(min,dp[i-1][j]+1);
                    //insert
                    min = Math.min(min,dp[i][j-1]+1);
                    //replace
                    min = Math.min(min,dp[i-1][j-1]+1);
                    dp[i][j] = min;
                }
            }
        }
        return dp[length1][length2];
    }

    //最长回文子序列
    public static int longestPalindrome(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        if (s.length() == 1){
            return 1;
        }
        int length = s.length();

        int[][] dp = new int[length][length];

        for(int h=0;h<length;h++){
            dp[h][h] = 1;
        }

        for(int i= length-2;i>=0;i--){
            for(int j=i+1;j<length;j++){
                if (s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1] +2;
                }else{
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }

        return dp[0][length-1];
    }

    public static void main(String[] args) {
        //剪绳子-动态规划
//        System.out.println(cuttingRope_dynamic(10));
//        System.out.println(cuttingRope_greedy(10));
        //两字符串最长公共子序列
//        String text1 = "abcde";
//        String text2 = "ace";
//        System.out.println(longestCommonSubsequence(text1,text2));
        //编辑距离
//        String word1 = "horse";
//        String word2 = "ros";
//        System.out.println(minDistance(word1,word2));
        //最长回文字序列
        String s = "bbbab";
        System.out.println(longestPalindrome(s));
    }
}
