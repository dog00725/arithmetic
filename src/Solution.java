import java.util.HashMap;
import java.util.Map;

public class Solution {

    private static Map<Integer,Integer> help = new HashMap();

    //斐波那契数列
    /*
     * 递归和循环均可实现
     * 递归存在大量重复计算，记录已得到值以减少重复计算
     * 循环自下而上的方式
     * time O(n)
     */
    public static int fibLoop(int n) {
        if (n == 1 || n == 0){
            return n;
        }
        int pre = 1;
        int ppre = 0;
        int fib = 0;
        for (int i = 2; i <= n; i++) {
            fib = (pre + ppre)%(1000000007);
            ppre = pre;
            pre = fib;
        }
        return fib;
    }

    public static int fibRecursion(int n){
        //可以记录得到过的值减少计算
//        return n <= 1 ? n : (fibRecursion(n-2) + fibRecursion(n-1))%(1000000007);
        if (n <= 1){
            help.put(n,n);
            return n;
        }
        int pre = help.get(n-1) == null?fibRecursion(n-1):help.get(n-1);
        int ppre = help.get(n-2) == null?fibRecursion(n-2):help.get(n-2);
        return (pre+ppre)%(1000000007);
    }

    //青蛙跳台阶
    /*
     * 可转化成斐波那契数列，只是初始值不同
     * 斐波那契数列 f(0)=0 f(1)=1
     * 青蛙跳台阶 f(0)=1 f(1)=1
     */
    public static int numWays(int n) {
        if (n == 1 || n == 0){
            return 1;
        }
        int pre = 1;
        int ppre = 1;
        int fib = 0;
        for (int i = 2; i <= n; i++) {
            fib = (pre + ppre)%(1000000007);
            ppre = pre;
            pre = fib;
        }
        return fib;
    }

    /*
     * 旋转数组的最小数字
     * 有序数组查找优先考虑二分查找，使用二分查找可以将原本线性的时间复杂度降低为对数级 log n
     * 本地要注意特殊情况，例如{1,1,1,0,1} 或 {1,0,1,1,1} 此种情况下无法判断中间索引是位于左排序数组还是有排序数组，此时可以减少数组数量（减少完旋转点仍旧位于i，j 之间）
     */
    public static int minArray(int[] number){
        int i = 0;
        int j = number.length-1;
        while (i < j){
            int mid = (i+j)/2;
            if (number[mid] < number[j]) j = mid;
            else if (number[mid] > number[j]) i = mid+1;
            else j--;
        }
        return number[i];
    }

    public static void main(String[] args) {
        //斐波那契
        System.out.println("斐波那契:"+fibLoop(48));
//        System.out.println("斐波那契:"+fibRecursion(48));
//        System.out.println("==============================");
//        System.out.println("青蛙跳台阶:"+numWays(48));
        System.out.println("==============================");
//        int[] numbers = {3,4,5,1,2};
        int[] numbers = {1,1,1,0,1};
        System.out.println(minArray(numbers));
    }
}
