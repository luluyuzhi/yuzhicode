struct Solution;

impl Solution {
    pub fn min_path_sum(grid: Vec<Vec<i32>>) -> i32 {
        let col = grid.len();
        let row = grid[0].len();

        let mut dp = vec![vec![0; row]; col];

        for i in 0..col {
            for j in 0..row {
                if i == 0 && j == 0 {
                    dp[i][j] = grid[i][j];
                } else if i == 0 {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if j == 0 {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    dp[i][j] = dp[i - 1][j].min(dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        dp[col - 1][row - 1]
    }
}

fn main() {
    assert_eq!(
        Solution::min_path_sum(vec![vec![1, 3, 1], vec![1, 5, 1], vec![4, 2, 1]]),
        7
    );
}
