package l5;

/**
 * 最长回文
 */
public class Solution {
    // 1. 暴力解法, 略
    public String longestPalindrome1(String s) {
        return "";
    }

    // 2. 中心扩散发： 从中间向两端进行扩散
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int start = 0;
        int currentLen = 1;
        for (int i = 0; i < s.length(); i++) {
            //s的长度为奇数时，向外扩散
            int len1 = expandAroundCenter(s, i, i);
            //s的长度为偶数时，向外扩散
            int len2 = expandAroundCenter(s, i, i + 1);
            // 选择回文长度最大的那个长度
            int len = Math.max(len1, len2);
            //如果当前len的长度大于之前的回文长度 end-start
            if (len > currentLen) {
                currentLen = len;
                // 此处需要-1再除以2， 可以动手画一下
                start = i - (currentLen - 1) / 2;
            }
        }
        return s.substring(start, start + currentLen);
    }

    //从left和right向外扩散，找到最长的回文串的长度
    public int expandAroundCenter(String s, int left, int right) {
        // 从给定的lef 和right向外扩散，两侧的字符应该相等
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    // 动态规划方法： 设dp[i][j]表示字符串s的第i到第j个字母组成的串是否是回文：
    //              dp[i][j]=true/false
    // 转移方程：    dp[i][j] = dp[i+1][j-2] ^ (Si==Sj)   一个串是回文，那么它去掉两端的字符还是子串
    // 边界值：     子串长度<=3,且 Si==Sj 时，dp[i][j]=true

    public String longestPalindrome3(String s) {
        int len = s.length();
        if (len<2)
        {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        boolean[][] dp = new boolean[len][len];
        //初始化
        for(int i=0;i<len;i++)
        {
            dp[i][i]=true;
        }

        char[] charArray = s.toCharArray();
        //枚举子串长度
        for(int L=2;L<=len;L++)
        {
            //上界i，下界i+L-1
            for (int i = 0; i < len; i++) {
                //下界j
                int j = i+L-1;
                //如果越界，退出此次循环
                if (j>=len)
                {
                    break;
                }
                if (charArray[i]!=charArray[j])
                {
                    dp[i][j]=false;
                }else
                {
                    // 如果长度是3个，2个，1个的时候，直接置为true；此处为边界值赋值
                    if (j-i<3)
                    {
                        dp[i][j]=true;
                    }else
                    {
                        // 如果长度超过3个，那么就等于 子串取掉前后字符的标志
                        dp[i][j] = dp[i+1][j-1];
                    }
                }
                // 每次找到最大的回文长度，并记录
                if (dp[i][j]&&j-i+1>maxLen)
                {
                    maxLen=j-i+1;
                    begin=i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }



    public static void main(String[] args) {

    }
}
