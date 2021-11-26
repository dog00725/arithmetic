package PracticeQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        if (null == nums || nums.length < 3){
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        int length = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            //排序后，第一个数大于0则后面两数相加不可能等于0
            if (nums[i] > 0){
                break;
            }
            //去重
            int l = i+1;
            int r = length - 1;
            while (l < r){
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    result.add(list);
                    //下面去重
                    while (l < r && nums[l] == nums[l+1]){
                        l++;
                    }
                    while (l < r && nums[r] == nums[r-1]){
                        r--;
                    }
                    l++;
                    r--;
                }else if (sum < 0){
                    l++;
                }else if(sum > 0){
                    r--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        int[] arrays = {-0,0,0};
        List<List<Integer>> list = threeSum.threeSum(arrays);
        for (List<Integer> integers : list) {
            integers.stream().forEach(System.out::print);
            System.out.println();
        }
    }
}
