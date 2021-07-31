package ElementaryAlgorithm.Array;

import java.util.*;

public class Solution {

    //-------------删除排序数组中的重复项------------------
    public static int removeDuplicates(int[] nums) {
        if (nums.length < 2) {
            return 1;
        }
        int front = 0;
        int back = 1;
        int count = 1;
        while (back < nums.length) {
            if (nums[front] != nums[back]) {
                count++;
                nums[count - 1] = nums[back];
            }
            front = back++;
        }
        return count;
    }

    public static int removeDuplicates_LeetCode(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[left] != nums[right]) {
                nums[++left] = nums[right];
            }
        }
        return ++left;
    }

    //-------------卖股票的最佳时机 Ⅱ---------------------
    //双指针适合只进行一次交易找出最大利润
    public static int maxProfit(int[] prices) {
        int buy = 0;
        int maxProfit = 0;
        for (int seal = 0; seal < prices.length; seal++) {
            if (prices[seal] - prices[buy] < 0) {
                buy = seal;
                continue;
            }
            maxProfit = Math.max(maxProfit, prices[seal] - prices[buy]);
        }
        return maxProfit;
    }

    /*
     * 可进行多次交易时，可用动态规划
     * 当天交易完成后有两种状态，持有股票和没有持有股票
     * 持有股票：1.今天刚买进股票，则最大利润为前一天没有持有股票的最大利润减去今天买进价
     *         2.今天没有交易，最大利润为前一天持有股票的最大利润
     * 没有股票：1.今天刚卖出股票，则最大利润为前一天持有股票的利润加上今天的价格
     *         2.今天没有交易，则最大利润为前一天没有股票的最大利润
     */
    public static int multiple_maxProfit(int[] prices) {
        int[][] profit = new int[prices.length][2];
        profit[0][1] = -prices[0];
        profit[0][0] = 0;
        for (int i = 1; i < prices.length; i++) {
            profit[i][0] = Math.max(profit[i - 1][0], profit[i - 1][1] + prices[i]);
            profit[i][1] = Math.max(profit[i - 1][1], profit[i - 1][0] - prices[i]);
        }
        return Math.max(profit[prices.length - 1][1], profit[prices.length - 1][0]);
    }

    //对以上代码进行优化
    //优化思路，当前状态只参照前一个状态，再之前的状态并不影响
    public static int improve_Multiple_maxProfit(int[] prices) {
        int hold = -prices[0];
        int noHold = 0;
        for (int i = 1; i < prices.length; i++) {
            noHold = Math.max(noHold, hold + prices[i]);
            hold = Math.max(hold, noHold - prices[i]);
        }
        return Math.max(hold, noHold);
    }

    //贪心算法
    /*
     *可将股票的价格的涨跌趋势用折线图表示
     * 其中所有上涨阶段的总利润就是我们所求的最大利润
     */
    public static int greedy_Multiple_maxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int index = 0;
        int total = 0;
        while (index < prices.length) {
            //找到开始上涨的最低点
            while (index < prices.length - 1 && prices[index] >= prices[index + 1])
                index++;
            int min = prices[index];
            //找到上涨的最高点
            while (index < prices.length - 1 && prices[index] <= prices[index + 1])
                index++;
            total += prices[index++] - min;
        }
        return total;
    }

    //贪心算法优化 space:O(1) time:O(n)
    public static int improve_Greedy_Multiple_maxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int total = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            total += Math.max(0, prices[i + 1] - prices[i]);
        }
        return total;
    }


    //-------------旋转数组--------------------------------
    /*
     * 1.临时数组 space：O(n) time：O(n)
     * 2.冒泡移动 space:O(1) time:O(k*n)
     * 3.整个数组分成两部分（分而治之） space:O(1) time:O(n)
     */
    //临时数组 space：O(n) time：O(n)
    public static void rotate_Temporary_Array(int[] nums, int k) {
        if (k > nums.length) {
            k = k % nums.length;
        }
        int[] temporary = new int[nums.length];
        for (int i = 0; i < k; i++) {
            temporary[i] = nums[nums.length - k + i];
        }
        int j = k;
        for (int i = 0; i < nums.length - k; i++) {
            temporary[j++] = nums[i];
        }
        System.arraycopy(temporary, 0, nums, 0, nums.length);
    }

    //冒泡移动 space:O(1) time:O(k*n)
    public static void rotate_Bubble(int[] nums, int k) {
        if (k > nums.length) {
            k = k % nums.length;
        }
        for (int i = 0; i < k; i++) {
            for (int j = nums.length - 1; j > 0; j--) {
                int temp = nums[j];
                nums[j] = nums[j - 1];
                nums[j - 1] = temp;
            }
        }
    }

    //整个数组分成两部分（分而治之） space:O(1) time:O(n)
    public static void rotate(int[] nums, int k) {
        if (k > nums.length) {
            k = k % nums.length;
        }
        reversal(nums, 0, nums.length - k - 1);
        reversal(nums, nums.length - k, nums.length - 1);
        reversal(nums, 0, nums.length - 1);
    }

    private static void reversal(int[] target, int left, int right) {
        while (left < right) {
            int temp = target[left];
            target[left++] = target[right];
            target[right--] = temp;
        }
    }

    //--------------------存在重复元素-----------------------
    /*
     * 1.先对数组进行排序，再相邻进行判断
     * 2.使用set进行判断
     */
    public static boolean containsDuplicate(int[] nums) {
        Merge.sort(nums, 0, nums.length - 1);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsDuplicate_set(int[] nums) {
        Set<Integer> set = new HashSet<>();
        boolean result = false;
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

    //--------------------只出现一次的数字-----------------------
    /*
     * 出现一次可以使用亦或运算：A^A^X = x space:O(1) time:O(n)
     */
    public static int singleNumber(int[] nums) {
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            num ^= nums[i];
        }
        return num;
    }

    //--------------------两个数组的交集 Ⅱ-----------------------
    /*
     * 1.可对两个数组进行排序，后比较
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        int h = 0;
        while (i < nums1.length && j < nums2.length) {
            while (i < nums1.length && nums1[i] < nums2[j]) {
                i++;
            }
            if (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
                result.add(nums1[i++]);
            }
            j++;
        }
        int[] cv = new int[result.size()];
        for (int k = 0; k < cv.length; k++) {
            cv[k] = result.get(k);
        }
        return cv;
    }

    //--------------------    数组加一   -----------------------
    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] >= 0 && digits[i] < 9) {
                digits[i] += 1;
                return digits;
            }
            digits[i] = 0;
        }
        int[] extend = new int[digits.length + 1];
        extend[0] = 1;
        return extend;
    }

    //--------------------    移动零   -----------------------
    /*
     * 1. 移动元素
     * 2，直接调换元素
     */
    public static void moveZeroes(int[] nums) {
        if (null == nums || nums.length < 2) {
            return;
        }

        int j = 1;
        for (int i = 0; i < nums.length - 1 && j < nums.length; i++) {
            if (nums[i] != 0 && nums[j] != 0) {
                j += 2;
                i++;
                continue;
            }
            if (nums[i] == 0 && nums[j] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j++] = temp;
                continue;
            }
            if (nums[i] != 0 && nums[j] != 0) {
                j++;
                continue;
            }
            while (j < nums.length && nums[i] == 0 && nums[j] == 0) {
                j++;
                if (j < nums.length && nums[j] != 0) {
                    int h = j - 1;
                    for (int k = j; k >= i + 1; k--) {
                        int te = nums[k];
                        nums[k] = nums[h];
                        nums[h--] = te;
                    }
                    break;
                }
            }
        }
    }

    public static void moveZeroes2(int[] nums) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == 0) {
                i++;
            } else if (i != 0) {
                nums[j - i] = nums[j];
                nums[j] = 0;
            }
        }
    }

    //--------------------    两数之和   -----------------------
    /*
     * 利用HasMap
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
                break;
            }
            map.put(nums[i], i);
        }
        return result;
    }

    //--------------------    有效数独   -----------------------\
    /*
     * 1.使用二维数组存储信息
     * 2.利用位运算
     */
    public static boolean isValidSudoku(char[][] board) {
        int length = board[0].length;

        int[][] rowContain = new int[length][length];
        int[][] colContain = new int[length][length];
        int[][] boxContain = new int[length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if ('.' == (board[i][j])) {
                    continue;
                }
                int num = board[i][j] - '0';
                int box = (j / 3) + (i / 3) * 3;

                if (rowContain[i][num - 1] != 0 || colContain[j][num - 1] != 0 || boxContain[box][num - 1] != 0) {
                    return false;
                }
                rowContain[i][num - 1] = colContain[j][num - 1] = boxContain[box][num - 1] = 1;
            }
        }
        return true;
    }

    //--------------------    旋转图像  -----------------------\
    public static void rotate(int[][] matrix) {
        int length = matrix.length;
        //只需要循环前半段
        for (int i = 0; i < length / 2; i++) {
            //最后一个已经和第一个替换了，不要重复替换
            for (int j = i; j < length - 1 - i; j++) {
                int temp = matrix[length - 1 - j][i];
                int row = i;
                int col = j;
                for (int k = 0; k < 4; k++) {
                    int t = matrix[row][col];
                    matrix[row][col] = temp;
                    temp = t;
                    int r = row;
                    row = col;
                    col = length - r - 1;
                }
            }
        }
    }

    /*
     * 改进：四个交换的数的坐标其实已知变换，可去除第三个for循环
     */
    public static void rotate2(int[][] matrix) {
        int length = matrix.length;
        for (int i = 0; i < length / 2; i++){
            for (int j = i; j < length - i - 1; j++) {
                int temp = matrix[i][j];
                int m = length - j - 1;
                int n = length - i - 1;
                matrix[i][j] = matrix[m][i];
                matrix[m][i] = matrix[n][m];
                matrix[n][m] = matrix[j][n];
                matrix[j][n] = temp;
            }
        }
    }


    public static void main(String[] args) {
        //-------------删除排序数组中的重复项------------------
//        int[] nums ={0,0,1,1,1,2,2,3,3,4};
//        System.out.println(removeDuplicates_LeetCode(nums));

        //-------------卖股票的最佳时机 Ⅱ---------------------
//        int[] prices = {7,1,5,3,6,4};
//        System.out.println(improve_Greedy_Multiple_maxProfit(prices));

        //-------------旋转数组--------------------------------
//        int[] nums = {-1,-100,3,99};
//        rotate(nums,2);

        //--------------------存在重复元素-----------------------
//        int[] nums = {4,6,1,5,3,2,6};
//        System.out.println(containsDuplicate(nums));

        //--------------------只出现一次的数字-----------------------
//        int[] nums = {4,6,4,3,3,2,6};
//        System.out.println(singleNumber(nums));

        //--------------------两个数组的交集 Ⅱ-----------------------
//        int[] nums1 = {7,2,2,4,7,0,3,4,5};
//        int[] nums2 = {3,9,8,6,1,9};
//        System.out.println(Arrays.toString(intersect(nums1,nums2)));

        //--------------------数组加一   -----------------------
//        int[] digits = {9,9,9};
//        System.out.println(Arrays.toString(plusOne(digits)));

        //--------------------    移动零   -----------------------
//        int[] nums = {0,1,0,5,3,12};
//        moveZeroes(nums);
//        System.out.println(Arrays.toString(nums));

        //--------------------    两数之和   -----------------------
//        int[] nums = {3,4,5};
//        System.out.println(Arrays.toString(twoSum(nums,8)));

        //--------------------    有效数独   -----------------------
//        char[][] board = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
//        System.out.println(isValidSudoku(board));


        int[][] arr = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("--------------------------");
        rotate2(arr);
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
