package sort;

public class BubbleSort{

    public static void sort(Comparable[] comparables) {
        for (int i = 0; i < comparables.length - 1; i++) {
            boolean isExchange = false;
            for (int j = 0; j < comparables.length - i - 1; j++) {
                if (less(comparables[j + 1], comparables[j])) {
                    exch(comparables, j, j + 1);
                    isExchange = true;
                }
            }
            if (!isExchange) break;
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
