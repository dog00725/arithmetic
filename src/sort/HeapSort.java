package sort;

/**
 * 堆排序
 */

public class HeapSort {
    public static void sort(Comparable[] comparables){
        if (null == comparables || comparables.length <= 1) return;
        //构建初始堆-两种方式上浮或下沉
        //上浮方式构建最大堆
//        for (int i = 0; i < comparables.length; i++) {
//            siftUp(comparables,i);
//        }
        /* 
         * 下称的方式初始最大堆
         * 从第一个非叶子节点开始下称
         */
        for (int i = (comparables.length -1)/2; i >= 0 ; i--) {
            siftDown(comparables,i, comparables.length);
        }
        //交换根节点和最后未交换过的元素
        int index = comparables.length -1;
        exch(comparables,0,index);
        //调整堆使其恢复成最大堆--使用下沉的方式
        while (index > 0){
            siftDown(comparables,0,index);
            exch(comparables,0,--index);
        }
    }

    /*
     * 上浮
     * 从最后一个非叶子节点进行调整
     * 最后一个非叶子节点 为 length/2 -1
     */
    private static void siftUp(Comparable[] comparables, int i){
        while (less(comparables[(i-1)/2], comparables[i])){
            exch(comparables,(i-1)/2,i);
            i = (i-1)/2;
        }
    }

    /*
     * 下沉
     * 将根节点的数和最后一个进行调换
     * 然后调整堆
     */
    private static void siftDown(Comparable[] comparables,int i,int index){
        int sub = 2 * i + 1;
        while (sub < index){
            //找出当前节点和它子节点中最大的节点的索引
            int maxIndex = sub+1 < index && less(comparables[sub],comparables[sub+1]) ? sub+1 : sub; //找出子节点中最大的
            maxIndex = less(comparables[maxIndex], comparables[i]) ? i : maxIndex; //比较最大的子节点和当前节点
            if (maxIndex == i) break;
            exch(comparables,i,maxIndex);
            //继续下沉
            i = maxIndex;
            sub = i * 2 +1;
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
