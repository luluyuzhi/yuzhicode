package leetcode.l67;

public class Solution {

  public String addBinary(String a, String b) {
    // 两个二进制相加， 最终和的位数做多为 max(a.length, b.length) + 1
    char[] A = a.toCharArray();
    char[] B = b.toCharArray();
    char[] res = new char[Math.max(A.length, B.length) + 1];
    // 先把两个二进制待求和的两个数，转换成位数一样的数，高位不足时补0
    char[] A2 = new char[res.length];
    char[] B2 = new char[res.length];
    int gapA = res.length - A.length;
    int gapB = res.length - B.length;
    for (int i = 0; i < res.length; i++) {
      if (i < gapA) {
        A2[i] = '0';
      } else {
        A2[i] = A[i - gapA];
      }
      if (i < gapB) {
        B2[i] = '0';
      } else {
        B2[i] = B[i - gapB];
      }
    }
    boolean flag = false;
    for (int i = res.length - 1; i >= 0; i--) {
      if (A2[i] == '0' && B2[i] == '0' && flag == false) {
        res[i] = '0';
        flag = false;
      }
      if (A2[i] == '0' && B2[i] == '0' && flag == true) {
        res[i] = '1';
        flag = false;
      }
      if ((A2[i] == '1' && B2[i] == '0' && flag == false)
          || A2[i] == '0' && B2[i] == '1' && flag == false) {
        res[i] = '1';
        flag = false;
      }
      if ((A2[i] == '1' && B2[i] == '0' && flag == true)
          || A2[i] == '0' && B2[i] == '1' && flag == true) {
        res[i] = '0';
        flag = true;
      }
      if (A2[i] == '1' && B2[i] == '1' && flag == true) {
        res[i] = '1';
        flag = true;
      }
      if (A2[i] == '1' && B2[i] == '1' && flag == false) {
        res[i] = '0';
        flag = true;
      }
    }
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < res.length; i++) {
      if (i == 0 && res[i] == '0') {
        continue;
      }
      sb.append(res[i]);
    }
    return sb.toString();
  }

  public String addBinary2(String a, String b) {
    StringBuffer sb = new StringBuffer();
    int n = Math.max(a.length(), b.length());
    int carry = 0;
    for(int i=0;i<n;i++){
      carry += i<a.length()?a.charAt(a.length()-i-1)-'0':0;
      carry += i<b.length()?b.charAt(b.length()-i-1)-'0':0;
      sb.append((char)(carry%2+'0'));
      carry = carry/2;
    }
    if (carry>0){
      sb.append('1');
    }
    return (sb.reverse()).toString();

  }

  public static void main(String[] args) {
    System.out.println(new Solution().addBinary("1101", "0110"));
  }
}
