package sort;

/**
 * 自顶向下递归归并
 * 比较次数 NlgN
 */
public class Merge {

    private static Comparable[] aux;   //归并排序所需的辅助数组

    /**
     * 归并
     * @param a   归并的数组
     * @param left  归并开始位置
     * @param mid  中间值
     * @param right  归并结束位置
     */
    public static void merge(Comparable[] a,int left,int mid,int right){

        int i = left, j = mid+1;  //左右两边的起始下标

        //复制数组
        for(int k=0;k<=right;++k){
            aux[k] = a[k];
        }

        //归并
        for (int k=left;k<=right;++k){
            //左边元素取完，取右边元素
            if (i > mid) a[k] = aux[j++];
            //右边元素取完，取左边元素
            if (j > right) a[k] = aux[i++];
            //左边元素比较小取左边元素
            else if (aux[i].compareTo(aux[j]) <= 0) a[k] = aux[i++];
            //右边元素比较小，取右边元素
            else a[k] = aux[j++];
        }
    }

    //自顶向下的归并排序
    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];  //一次性分配空间
        sort(a,0,a.length-1);
    }

    public static void sort(Comparable[] a,int lo, int hi){
        if (lo >= hi) return;
        int mid = (lo+hi)/2;
        sort(a,lo,mid);       //左半边排序
        sort(a,mid+1,hi); //右半边排序
        merge(a,lo,mid,hi);  //归并当前数组
    }
}


