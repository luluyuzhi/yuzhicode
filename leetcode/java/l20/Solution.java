package leetcode.l20;

import java.util.HashMap;
import java.util.LinkedList;

public class Solution {

  /**
   * 有效的括号
   * @param s
   * @return
   */
  public boolean isValid(String s) {
    boolean flag = true;
    if(s==null || s.length()==0){
      return flag;
    }
    if(s.length()%2 != 0) return false;
    HashMap<Character, Character> map = new HashMap<>();
    map.put('(',')');
    map.put('[',']');
    map.put('{','}');
    char[] chars = s.toCharArray();
    LinkedList<Character> stack = new LinkedList<>();
    for(int i=0;i<chars.length;i++){
      if(stack.size()>0){
        char peek = stack.peek();
        if(map.getOrDefault(peek, 'a')==chars[i]){
          stack.pop();
        }else{
          stack.push(chars[i]);
        }
      }else{
        stack.push(chars[i]);
      }
    }
    if(stack.size()!=0){
      flag = false;
    }
    return flag;
  }

}
