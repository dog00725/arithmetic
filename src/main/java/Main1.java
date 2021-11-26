import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        List<List> value = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            List<int[]> temp = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                int[] a = new int[3];
                a[0] = scanner.nextInt();
                a[2] = scanner.nextInt();
                a[1] = scanner.nextInt();
                temp.add(a);
            }
            value.add(temp);
        }
    }


}
