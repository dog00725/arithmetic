package sort;

import java.util.Arrays;

/**
 * 计数排序
 * 利用元素出现的次数进行排序
 */

public class CountSort {

    public static void sort(int[] integers,int range){
        int[] temp = new int[integers.length];
        int[] count = new int[range+1];

        for (int integer : integers) {
            count[integer]++;
        }
        for (int i = 1; i < range+1; i++) {
            count[i] += count[i-1];
        }

        for (int i = 0; i < integers.length; i++) {
            temp[--count[integers[i]]] = integers[i];
        }

        for (int i = 0; i < temp.length; i++) {
            integers[i] = temp[i];
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
        int[] arrays = {5,6,3,1,7,9,24,2,4};
        System.out.println("排序前:");
        System.out.println(Arrays.toString(arrays));
        sort(arrays,24);
        System.out.println("排序后:");
        System.out.println(Arrays.toString(arrays));
    }
}
