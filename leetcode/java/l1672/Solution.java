package leetcode.l1672;

import java.util.Arrays;

public class Solution {
  public int maximumWealth(int[][] accounts) {
    return Arrays.stream(accounts).map(x-> Arrays.stream(x).sum()).max(Integer::compareTo).get();
  }
}
