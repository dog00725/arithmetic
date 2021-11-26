package sort;

/**
 * 把数组分成几块，每一块进行一个插入排序；
 * 而分块的依据在于增量的选择分好块之后，从gap开始到n，每一组和它前面的元素（自己组内的）进行插入排序；
 */

public class ShellSort {


    public static void sort(Comparable[] comparables){

        int length = comparables.length;
        int step = 1;
        while (step < length/3) step = 3* step + 1;
        while (step > 0){
            for (int i = 0; i < comparables.length; i+=step) {
                for (int j = 0; j < i; j+= step) {
                    if (less(comparables[i],comparables[j])){
                        exch(comparables,j,i);
                    }
                }
            }
            step /= 3;
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
