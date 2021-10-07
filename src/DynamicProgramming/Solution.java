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

    
    /**
         * 斐波那契数列
     */
    int[] num;
    public int fib(int n) {
	   if(n == 0) {
		   return 0;
	   }
	   num = new int[n+1];
	   return partition(n);
    }
    
    private int partition(int n) {
    	if (n == 1 || n == 2) {
    		return 1;
    	}
    	if (num[n] != 0) {
    		return num[n];
    	}
    	num[n] = partition(n-1)+partition(n-2);
    	return num[n];
    }

    
    /**
         * 打家劫舍
     */
    public int rob(int[] nums) {
    	if(nums == null || nums.length == 0) {
    		return 0;
    	}
    	if(nums.length == 1) {
    		return nums[0];
    	}
    	if(nums.length == 2) {
    		return Math.max(nums[0], nums[1]);
    	}
    	int[] value = new int[nums.length+1];
    	value[0] = 0;
    	value[1] = nums[0];
    	for(int i=2;i<=nums.length;i++) {
    		value[i] = Math.max(value[i-2]+nums[i-1], value[i-1]);
    	}
    	return value[nums.length];
    }
    
	/**
	 * 打家劫舍Ⅱ  --- 打家劫舍类似后续遍历
	 * 房屋头尾相接，将其拆成两个分成以下两种情况
	 * 第一家偷
	 * 第一家不偷
	 */
	public int rob2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}
		if (nums.length == 2) {
			return Math.max(nums[0], nums[1]);
		}
		int length = nums.length;
		int end = nums[length-1];
		
//		第一家偷
		int[] value = new int[length + 1];
		value[0] = 0;
		value[1] = nums[0];
		nums[length-1] = 0;
		for (int i = 2; i <= length; i++) {
			value[i] = Math.max(value[i - 2] + nums[i - 1], value[i - 1]);
		}
		
		//第一家不偷
		int[] value2 = new int[length+1];
		nums[length-1] = end;
		for (int i = 2; i <= length; i++) {
			value2[i] = Math.max(value2[i - 2] + nums[i - 1], value2[i - 1]);
		}
		return Math.max(value[length],value2[length]);
	}
	
//	跳跃游戏，别想太多就直接挨着跳吧 --- 层次遍历
	/**
	 * 最远距离的左边位置都是可以到达的 
	 */
	public static boolean canJump(int[] nums) {
		int maxLength = 0;
		for(int i=0;i<=maxLength;i++) {
			int temp = i + nums[i];
			maxLength = Math.max(maxLength, temp);
			if (maxLength >= nums.length-1) {
				return true;
			}
		}
		return false;
	}
	
    public static int jump(int[] nums) {
        int maxLength = 0;
        int ans = 0;
        int end = 0;
        for(int i=0;i<=end && end < nums.length-1;i++) {
        	maxLength = Math.max(nums[i]+i, maxLength);
        	if(i == end) {
        		ans++;
        		end = maxLength;
        	}
        }
        return ans;
    }
    
    
      
    public static void main(String[] args) {
        //剪绳子-动态规划
//        System.out.println(cuttingRope_dynamic(10));
//        System.out.println(cuttingRope_greedy(10));
    	//跳跃游戏
    	int[] nums = {2,3,1,1,4};
    	System.out.println(jump(nums));	
    }
}
