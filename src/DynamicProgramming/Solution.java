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


    public static void main(String[] args) {
        //剪绳子-动态规划
        System.out.println(cuttingRope_dynamic(10));
        System.out.println(cuttingRope_greedy(10));
    }
}
