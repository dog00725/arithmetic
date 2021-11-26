package sort;

/**
 * 快速排序
 * 随机选择一个基准数，大于等于放右边，小于等于放左边
 * 左右两边按照上面步骤循环
 */

public class QuickSort {

    public static void sort(Comparable[] comparables){
        sort(comparables,0,comparables.length-1);
    }

    private static void sort(Comparable[] comparables,int start, int end){
//        if (start >= end) return;
//        int partition = partition(comparables,start,end);
//        sort(comparables,start,partition-1);
//        sort(comparables,partition+1,end);

        //三路切分，解决重复元素问题（荷兰国旗问题）
        if (start >= end) return;
        Comparable v = comparables[start];
        int lt = start;
        int i = start+1;
        int gt = end;
        while (i <= gt){
            int cmpare = comparables[i].compareTo(v);
            if (cmpare < 0) exch(comparables,lt++,i++);
            else if (cmpare > 0) exch(comparables,i,gt--);
            else i++;
        }
        sort(comparables,start,lt-1);
        sort(comparables,gt+1,end);

    }
    

    private static int partition(Comparable[] comparables, int start, int end){
        //双指针
        //直接选取 arr[L]作为pivot(中心点)
//        Comparable key = comparables[start];
//        int pivot = start;
//        for (int i = start + 1; i <= end; i++) {
//            if (less(comparables[i],key))
//                exch(comparables, i, ++pivot);
//        }
        //exch(comparables, pivot, start); // 将arr[L]放到pivot位置(中间) --> 完全了按照arr[L]划分数组的目的
        //return pivot;


        //以上方式对于重复元素较多时，划分会不均匀，下方方法可以优化该问题左右两边指针，等于时会进行交换，但是并不是最优
        int left = start;
        int right = end+1;
        Comparable v = comparables[start];
        while (true){
            while (less(comparables[++left], v)){
                if (left == end) break;
            }
            while (less(v,comparables[--right])){
                if (right == start) break;
            }
            if (left >= right) break;
            exch(comparables,left,right);
        }
        exch(comparables,start,right);
        return right;
    }

    /*
     * 下面的三个方法是工具方法
     * less 比较大小
     * exch 交换元素
     * show 打印
     */
    private static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void show(Comparable[] a){
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] arrays = {5,6,3,1,7,9,24,2,4};
        System.out.println("排序前:");
        show(arrays);
        sort(arrays);
        System.out.println("排序后:");
        show(arrays);
    }
}
