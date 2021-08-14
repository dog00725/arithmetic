package sort;

/**
 * 插入排序
 * 从第一个元素开始，该元素可以看作已经排好序；
 * 取出下一个元素，从这个元素从后往前开始扫描，如果该元素大于新元素，将该元素移到下一位置；
 */

public class InsertSort {

    public static void sort(Comparable[] comparables){
        for (int i = 0; i < comparables.length; i++) {
            for (int j = 0; j < i; j++) {
                if (less(comparables[i],comparables[j])){
                    exch(comparables,i,j);
                }
            }
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
