package leetcode.l43;

/**
 * 字符串相乘
 */
public class Solution {

  /**
   * 两个字符串数字相乘，不允许直接转换成数字进行相乘
   * @param num1
   * @param num2
   * @return
   */
  public String multiply(String num1, String num2) {
    if ("0".equals(num1)||"0".equals(num2)){
      return "0";
    }
    int m = num1.length();
    int n = num2.length();
    //  m位的数 和 n位的数 相乘， 结果的位数最大为m+n
    int[] ansArray = new int[m+n];
    for (int i = m-1; i>=0;i--){
      int x = num1.charAt(i)-'0';
      for(int j=n-1;j>=0;j--){
        int y = num2.charAt(j)-'0';
        ansArray[i+j+1] += x*y;  // 将每位上两个数据进行乘积累加，然后将进位给到前面的元素
      }
    }

    for (int i = m+n-1; i>0; i--) {
      ansArray[i-1] += ansArray[i]/10;
      ansArray[i] = ansArray[i]%10;
    }
    // 判断结果数组中的最高位是0还是非零,如果为0，就从索引为1处开始，如果非零，索引就从0处开始
    int startIndex = (ansArray[0]==0)?1:0;
    StringBuffer sb = new StringBuffer();
    for (int i = startIndex;i<m+n;i++){
      sb.append(ansArray[i]);
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println(new Solution().multiply("2", "3"));
  }
}
