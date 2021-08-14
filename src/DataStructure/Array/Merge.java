package DataStructure.Array;

public class Merge {

    public static void sort(int[] nums, int begin, int end){
        if (begin < end){
            int mid = (end+begin)/2;
            sort(nums,begin,mid);
            sort(nums,mid+1,end);
            merge(nums,begin,mid,end);
        }

    }

    public static void merge(int[] nums, int begin, int mid, int end){
        int i = begin;
        int j = mid+1;
        while (i < j && j <= end){
            while (i < j && nums[i] <= nums[j]){
                i++;
            }
            int old_j = j;
            while (j <= end && nums[j] < nums[i]){
                j++;
            }
            reverse(nums,i,old_j-1,j-1);
            i += (j - old_j);
        }
    }


    private static void reverse(int[] nums, int begin, int mid, int end){
        swap(nums,begin,mid);
        swap(nums,mid+1,end);
        swap(nums,begin,end);
    }

    private static void swap(int[] nums, int begin , int end){
        while(begin < end){
            int temp = nums[begin];
            nums[begin++] = nums[end];
            nums[end--] = temp;
        }
    }
}
