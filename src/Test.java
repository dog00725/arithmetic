
import sort.*;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
//        Character[] a = {'E','A','S','Y','Q','U','E','S','T','I','O','N'};
//        Character[] b = Arrays.copyOf(a,a.length);
//        Character[] c = Arrays.copyOf(a,a.length);
//
//        Merge.sort(b);
//        System.out.println(Arrays.toString(b));
//        MergeBU.sort(c);
//        System.out.println(Arrays.toString(c));
//        System.out.println(Integer.toBinaryString(5));
//        System.out.println(Integer.toBinaryString(-5));
//        System.out.println(Integer.toBinaryString((~5)+1));
//        System.out.println(Integer.toBinaryString(~(-5-1)));
//        System.out.println("aE,a".toLowerCase());

//        int[] t = {1,2,3,4};
//        System.out.println(t[2]-1);
//        System.out.println(Arrays.toString(t));

        Integer[] arrays = {5,6,3,1,7,9,24,2,4};
        System.out.println("排序前:");
        show(arrays);
        sort(arrays);
        System.out.println("排序后:");
        show(arrays);
    }

    public static void sort(Comparable[] comparables){
        if (null == comparables || comparables.length == 0){
            return;
        }
        int length = comparables.length;
        for(int i=0;i < comparables.length-1;i++){
            for(int j=0;j<comparables.length-1-i;j++){
                if(less(comparables[j+1],comparables[j])){
                    exch(comparables,j,j+1);
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
}
