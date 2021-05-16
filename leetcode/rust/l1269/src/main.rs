struct Solution;

impl Solution {
    pub fn num_ways(steps: i32, arr_len: i32) -> i32 {
        let MODULO = 1000000007;
        let max_column = (arr_len - 1).min(steps);

        let mut dp = vec![vec![0 as u64; (max_column + 1) as usize]; (steps + 1) as usize];

        dp[0][0] = 1 as u64;

        for i in 1..(steps + 1) {
            for j in 0..(max_column + 1) {
                dp[i as usize][j as usize] = dp[i as usize - 1][j as usize];
                if j - 1 >= 0 {
                    dp[i as usize][j as usize] =
                        (dp[i as usize][j as usize] + dp[i as usize - 1][j as usize - 1]) % MODULO;
                }

                if j + 1 <= max_column {
                    dp[i as usize][j as usize] =
                        (dp[i as usize][j as usize] + dp[i as usize - 1][j as usize + 1]) % MODULO;
                }
            }
        }

        dp[steps as usize][0] as i32
    }
}

fn main() {
    Solution::num_ways(2, 4);
}
