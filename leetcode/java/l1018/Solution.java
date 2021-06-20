package leetcode.l1018;

import com.sun.codemodel.internal.JDocComment;
import com.sun.codemodel.internal.JDocCommentable;
import com.sun.org.apache.xpath.internal.operations.Bool;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import sun.java2d.pipe.SolidTextRenderer;

public class Solution {

  public List<Boolean> prefixesDivBy5(int[] nums) {
    int res = 0;
    List<Boolean> list = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      res = ((res << 1) + nums[i]) % 5;
      list.add(res == 0);
    }
    return list;
  }


  public static void main(String[] args) {

  }
}
