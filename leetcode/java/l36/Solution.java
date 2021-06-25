package leetcode.l36;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Solution {

  /**
   * 判断输入的9X9的二维数组中的数字，是否满足数独的三条规则
   *
   * @param board
   * @return
   */
  public boolean isValidSudoku(char[][] board) {
    //定义一个记录行数据的hash 、  定义一个记录列数据的hash、  再定义一个记录9个3X3的小宫格中的所有数据
    HashMap<Integer, ArrayList<Character>> XMap = new HashMap<>();
    HashMap<Integer, ArrayList<Character>> YMap = new HashMap<>();
    int xLen = 9;
    int yLen = 9;
    ArrayList<Character>[][] minMatrix = new ArrayList[3][3];
    boolean flag = true;
    // 小矩阵初始化
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        minMatrix[i][j] = new ArrayList<Character>();
      }
    }
    for (int i = 0; i < board.length; i++) {
      ArrayList<Character> xArrayList = XMap.getOrDefault(i, new ArrayList<>());
      for (int j = 0; j < board[0].length; j++) {
        if (board[i][j] == '.') {
          continue;
        }
        //行hash记录
        xArrayList.add(board[i][j]);
        //更新列hash记录
        ArrayList<Character> yArrayList = YMap.getOrDefault(j, new ArrayList<>());
        yArrayList.add(board[i][j]);
        YMap.put(j, yArrayList);
        //记录3X3的小方格矩阵
        int minx = getMinMatrixIndex(i);
        int miny = getMinMatrixIndex(j);
        minMatrix[minx][miny].add(board[i][j]);
      }
      // 判断行索引是否存在重复字符
      if (!judgeDobule(xArrayList)) {
        return false;
      }
    }
    // 判断列索引是否有重复字符
    for (int j = 0; j < 9; j++) {
      if (!judgeDobule(YMap.get(j))) {
        flag = false;
        return flag;
      }
    }
    // 判断3X3小矩阵是否满足
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (!judgeDobule(minMatrix[i][j])) {
          return false;
        }
      }
    }
    return flag;

  }

  public int getMinMatrixIndex(int i) {
    int minx = -1;
    if (i >= 0 && i <= 2) {
      minx = 0;
    } else if (i >= 3 && i <= 5) {
      minx = 1;
    } else {
      minx = 2;
    }
    return minx;
  }

  public boolean judgeDobule(ArrayList<Character> list) {
    //都为 ".", 默认满足数独
    if(list==null){
      return true;
    }
    int len = list.size();
    HashSet<Character> set = new HashSet<>();
    for (int i = 0; i < len; i++) {
      set.add(list.get(i));
    }
    if (set.size() == len) {
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    new Solution().isValidSudoku(new char[9][9]);
  }
}
