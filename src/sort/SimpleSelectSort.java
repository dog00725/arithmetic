package sort;

/**
 * 简单选择排序
 * 在序列中找到最小（大）元素，放到序列的起始位置作为已排序序列
 * 再从剩余未排序元素中继续寻找最小（大）元素，放到已排序序列的末尾。
 */

public class SimpleSelectSort {

    public static void sort(Comparable[] comparables){
        for (int i = 0; i < comparables.length-1; i++) {
            int min = i;
            for (int j = i; j < comparables.length; j++) {
                if (less(comparables[j],comparables[min])){
                    min = j;
                }
            }
            exch(comparables,i,min);
        }
    }

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
