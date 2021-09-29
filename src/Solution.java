import DataStructure.ListzNode.ListNode;

import java.util.*;

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

    //数值的整数次方
    public static double myPow(double x, int n) {
        if (x == 0) return 0;
        long absolute = n;
        if (n < 0){
            x = 1D/x;
            absolute = -absolute;
        }
        double result = 1D;
        for (long pow = absolute;pow > 0;pow = pow >>=1){
            if ((pow & 1) ==1) result *=x;
            x *= x;
        }
        return result;
    }

    //打印从1到最大的n位数
    /*
     * 该题思考时需要考虑n位数是否超出表示范围,即大数的解决方式
     * 因此可以用数组表示数
     */
    public static int[] printNumbers(int n) {
        if (n <= 0) return new int[0];
        StringBuilder res = new StringBuilder();
        int[] list = new int[(int) (Math.pow(10,n)-1)];
        for (int i = 0; i < n; i++) {
            res.append('0');
        }
        String temp;
        int j = 0;
        while (!increment(res)){
            temp = "";
            boolean haveNumber = false;
            for (int i = 0; i < res.length(); i++) {
                if (res.charAt(i) == '0' && !haveNumber) continue;
                temp += res.charAt(i);
                haveNumber = true;
            }
            list[j++] = Integer.parseInt(temp);
        }
        return list;
    }

    //只有最高位进一时表示已到最大
    private static boolean increment(StringBuilder stringBuilder){
        boolean isOverFlow = false;
        for (int i = stringBuilder.length()-1; i >= 0; i--) {
            char num = (char)(stringBuilder.charAt(i)+1);
            //判断是否需要进位
            if (num > '9'){
                //判断是否是最高位
                stringBuilder.replace(i,i+1,"0");
                if (i == 0) {
                    isOverFlow = true;
                }
            }else {
                stringBuilder.replace(i,i+1,String.valueOf(num));
                break;
            }
        }
        return isOverFlow;
    }

    //删除链表节点
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) return head;
        ListNode chain = head;
        ListNode temp = null;
        while (chain.next != null){
            temp = chain.next;
            if (temp.val == val) {
                chain.next = temp.next;
                break;
            }
            chain = chain.next;
        }
        return head;
    }

    //正则表达式匹配
    /*
     * 使用动态规划
     */
    public static boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();

        boolean[][] matrix = new boolean[n+1][m+1];
        matrix[0][0] = true;

        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < m+1 ; j++){
                //分成空正则和非空正则两种
                if (j == 0) {
                    matrix[i][j] = i == 0;
                } else {
                    //非空正则分为两种情况 * 和 非*
                    if (p.charAt(j - 1) != '*') {
                        if (i > 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')) {
                            matrix[i][j] = matrix[i - 1][j - 1];
                        }
                    } else {
                        //碰到 * 了，分为看和不看两种情况
                        //不看
                        if (j >= 2) {
                            matrix[i][j] |= matrix[i][j - 2];
                        }
                        //看
                        if (i >= 1 && j >= 2 && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.')) {
                            matrix[i][j] |= matrix[i - 1][j];
                        }
                    }
                }
            }
        }

        return matrix[n][m];
    }

    //表示数字的字符串
    public static boolean isNumber(String s) {
        if (null == s) return false;
        s = s.trim();
        char[] chars = s.toCharArray();
        boolean findNumber = false;
        boolean findDot = false;
        boolean findE = false;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '-' || chars[i] == '+'){
                if (i != 0 && (chars[i-1] != 'e' && chars[i-1] != 'E')){
                    return false;
                }
            } else if (chars[i] >= '0' && chars[i] <= '9') {
                findNumber = true;
            } else if (chars[i] == '.'){
                if (findDot || findE){
                    return false;
                }
                findDot = true;
            }else if (chars[i] == 'e' || chars[i] == 'E'){
                if (findE || !findNumber){
                    return false;
                }
                findE = true;
                findNumber = false; //保证e后面也有数字
            }else{
                return false;
            }
        }
        return findNumber;
    }

    //奇数位于偶数前面
    /*
     * 第一个想法就是 类似冒泡，循环数组遇到一个偶数就将其移动到后面   time:O(n^2)
     * 相对较好的解法：使用双指针，当head指针指向偶数并且tail指向奇数两者交换，两葛指针同时移动
     * 当head指向奇数就++
     * 当tail指向偶数就—-
     */
    public static int[] exchange(int[] nums) {
        if (null == nums) return null;

        int length = nums.length;
        int head = 0;
        int tail = length - 1;

        while (head < tail) {
            if (nums[head] % 2 == 0 && nums[tail] % 2 != 0){
                int temp = nums[head];
                nums[head++] = nums[tail];
                nums[tail--] = temp;
            }
            if (nums[head] % 2 != 0) head++;
            if (nums[tail] % 2 == 0) tail--;
        }
        return nums;
    }

    /*
     * 反转链表
     */
    public ListNode reverseList(ListNode head) {
        if (null == head){
            return head;
        }
        ListNode originalHead = head;
        ListNode newHead = null;
        ListNode temp = null;
        while (originalHead != null){
            temp = new ListNode(originalHead.val);
            if (newHead == null){
                newHead = temp;
            }else {
                temp.next = newHead;
            }
            originalHead = originalHead.next;
            newHead = temp;
        }
        return newHead;
    }

    /*
     * 合并两个有序链表
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null){
            return null;
        }
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }

        ListNode head1 = l1;
        ListNode head2 = l2;
        ListNode newList = null;
        ListNode result = null;
        ListNode temp = newList;
        while (head1 != null || head2 != null){
            if (head1 != null && head2 != null){
                if (head1.val <= head2.val){
                    temp = new ListNode(head1.val);
                    head1 = head1.next;
                }
                else if (head2.val <= head1.val){
                    temp = new ListNode(head2.val);
                    head2 = head2.next;
                }
                if (newList == null){
                    newList = temp;
                    result = temp;
                }else {
                    newList.next = temp;
                }
                newList = temp;
                continue;
            }
            if (head1 == null) {
                newList.next = head2;
                break;
            }
            if (head2 == null) {
                newList.next = head1;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //斐波那契
//        System.out.println("斐波那契:"+fibLoop(48));
//        System.out.println("斐波那契:"+fibRecursion(48));
//        System.out.println("==============================");
//        System.out.println("青蛙跳台阶:"+numWays(48));
        //旋转数组的最小数字
//        System.out.println("==============================");
//        int[] numbers = {3,4,5,1,2};
//        int[] numbers = {1,1,1,0,1};
//        System.out.println(minArray(numbers));
        //数值的整数次方
//        System.out.println("==============================");
//        System.out.println(myPow(2D,-2147483648));
        //打印从1到最大的n位数
//        System.out.println("==============================");
//        System.out.println(Arrays.toString(printNumbers(2)));
        //删除链表节点
//        System.out.println("==============================");
//        String s = "aa";
//        String p = "a*";
//        System.out.println(isMatch(s,p));
        //表示数字的字符串
//        System.out.println("==============================");
//        String s = "-1.E-16";
//        System.out.println(isNumber(s));
        //调整数组顺序奇数位于偶数前面
//        System.out.println("==============================");
//        int[] nums = {1,2,3,4};
//        System.out.println(Arrays.toString(exchange(nums)));
        //合并链表
//        System.out.println("==============================");
//        ListNode head1 = new ListNode(1);
//        head1.next= new ListNode(2);
//        head1.next.next= new ListNode(4);
//        ListNode head2 = new ListNode(1);
//        head2.next= new ListNode(3);
//        head2.next.next= new ListNode(4);
//        ListNode merge = mergeTwoLists(head1,head2);
//        for (ListNode i = merge; i != null ; i = i.next) {
//            System.out.println(i.val);
//        }

        System.out.println('a'+0);
        StringBuilder builder = new StringBuilder();
        char e = 'A';
        builder.append( (e+=32));

        builder.append("sdd");
        System.out.println(new Object[]{new String[3]});
        System.out.println('A'+32);
    }


}
