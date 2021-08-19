package backtracking;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * 回溯算法
 */

public class Solution {
    //矩阵中的路径
    /*
     * 使用回溯算法，对于已访问过的位置进行记录
     * 记录的方式：1.可以修改原数组的情况，访问过后可以将其求改为‘ ’ --- 存在缺陷前面访问过后，之后相邻的路径无法访问，board被污染
     *           2.使用额外的辅助空间进行标记,
     */
    public static boolean exist(char[][] board, String word) {

            Deque<int[]> stack = new ArrayDeque();
            int row = board.length;
            int col = board[0].length;
            out:
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == word.charAt(0)){
                        if (dfs(board,word,i,j,0)) return true;
                        break out;
                    }
                }

            }
            return false;
    }
    private static boolean dfs(char[][] board,String word,int i,int j,int index){
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word.charAt(index)) return false;
        if (index == word.length()-1) return true;
        char temp = board[i][j];
        board[i][j] = '.';
        boolean res = dfs(board, word, i + 1, j, index + 1) || dfs(board, word, i - 1, j, index + 1) ||
                dfs(board, word, i, j + 1, index + 1) || dfs(board, word, i, j - 1, index + 1);
        board[i][j] = temp;
        return res;
    }

    /*
     * 机器人的运动范围
     */
    public static int movingCount(int m, int n, int k) {
        byte[][] path = new byte[m][n];
        return dfs(0,0,k,m,n,path);
    }

    //需要记录已走路径不然递归下去会进入闭环导致栈溢出
    //此处当时想用set进行记录已走过的位置，但是存储元素的类型是数组是引用类型，这回导致set进行存在的判断有误
//    private static int dfs(int x, int y, int k, int row, int col, Set<int[]> path){
    private static int dfs(int x, int y, int k, int row, int col, byte[][] path){
        if (x >= row || y >= col || x < 0 || y < 0 || path[x][y] == 1) return 0;
        if (((x%10) + (x/10) + (y%10) + (y/10)) > k ) return 0;
        path[x][y] = 1;
        if (x == row-1 && y == col-1) return 1;
        return 1 + dfs(x+1,y,k,row,col,path) + dfs(x-1,y,k,row,col,path)
                + dfs(x,y+1,k,row,col,path) + dfs(x,y-1,k,row,col,path);
    }

    public static void main(String[] args) {
        //矩阵中的路径
//        char[][] board = {{'A','A'}};
//        String word = "AAA";
//        System.out.println(exist(board,word));

        //机器人的运动范围
        System.out.println(movingCount(3,2,17));
    }

}
