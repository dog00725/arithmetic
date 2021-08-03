
import sort.*;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Character[] a = {'E','A','S','Y','Q','U','E','S','T','I','O','N'};
        Character[] b = Arrays.copyOf(a,a.length);
        Character[] c = Arrays.copyOf(a,a.length);

        Merge.sort(b);
        System.out.println(Arrays.toString(b));
        MergeBU.sort(c);
        System.out.println(Arrays.toString(c));
        System.out.println(Integer.toBinaryString(5));
        System.out.println(Integer.toBinaryString(-5));
        System.out.println(Integer.toBinaryString((~5)+1));
        System.out.println(Integer.toBinaryString(~(-5-1)));
        System.out.println("aE,a".toLowerCase());
    }
}
