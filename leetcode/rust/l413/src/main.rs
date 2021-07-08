struct Solution;

impl Solution {
    pub fn number_of_arithmetic_slices(nums: Vec<i32>) -> i32 {
        let mut dp = 0;
        let mut sum = 0;
        for i in 2..nums.len() {
            if nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2] {
                dp = 1 + dp;
                sum += dp;
            } else {
                dp = 0;
            }
        }
        sum
    }
}

fn main() {
    assert_eq!(Solution::number_of_arithmetic_slices(vec![1, 2, 3, 4]), 3)
}
