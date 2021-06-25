package leetcode.l10;

public class Solution {

  public boolean isMatch(String s, String p) {
    int m = s.length();
    int n = p.length();
    boolean f[][] = new boolean[m + 1][n + 1];
    f[0][0] = true;//f[0][0]代表s和p均为空字符串，f[1][1]代表s和p的第一个字符（即在s和p中下标为0的字符）
    for (int i = 0; i <= m; ++i) {
      for (int j = 1; j <= n; ++j) {
        if (p.charAt(j - 1) == '*') {//p的第j个字符为*
          if (matches(s, p, i, j - 1)) {//匹配s的第i个字符和p的第j-1个字符
            f[i][j] = f[i - 1][j] || f[i][j - 2];//p中*前面的字符在s中出现多次或者在s中只出现1次
          } else {
            f[i][j] = f[i][j - 2];//p中*前面的在s中字符出现0次
          }
        } else {//p的第j个字符不为*
          if (matches(s, p, i, j)) {//匹配s的第i个字符和p的第j个字符
            f[i][j] = f[i - 1][j - 1];//匹配成功，状态转移；匹配不成功，默认是false
          }
        }
      }
    }
    return f[m][n];
  }

  private boolean matches(String s, String p, int i, int j) {//注意在字符串中的下标变换
    if (i == 0) {
      return false;
    }
    if (p.charAt(j - 1) == '.') {
      return true;
    }
    return s.charAt(i - 1) == p.charAt(j - 1);
  }

}
