package sort;

public class MergeBU {

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {

        int N = a.length;
        aux = new Comparable[N];

        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }

    /**
     * 归并
     *
     * @param a   归并的数组
     * @param hi  归并开始位置
     * @param mid 中间值
     * @param lo  归并结束位置
     */
    public static void merge(Comparable[] a, int hi, int mid, int lo) {

        int i = hi, j = mid + 1;  //左右两边的起始下标

        //复制数组
        for (int k = 0; k <= lo; ++k) {
            aux[k] = a[k];
        }

        //归并
        for (int k = hi; k <= lo; ++k) {
            //左边元素取完，取右边元素
            if (i > mid) a[k] = aux[j++];
            //右边元素取完，取左边元素
            if (j > lo) a[k] = aux[i++];
                //左边元素比较小去右边元素
            else if (aux[i].compareTo(aux[j]) <= 0) a[k] = aux[i++];
                //右边元素比较小，取右边元素
            else a[k] = aux[j++];
        }
    }
}
