package PracticeQuestions;

public class CountServers {
    public static int countServers(int[][] grid) {
        //暴力解法，计算出每行每列的服务器数量
        int row = grid.length;
        int col = grid[0].length;

        int[] rowArray = new int[row];
        int[] colArray = new int[col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1){
                    rowArray[i] += 1;
                    colArray[j] += 1;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1 && (rowArray[i] > 1 || colArray[j] >1)){
                    ans+=1;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = {{1,0},{0,1}};
        System.out.println(countServers(grid));
    }
}
