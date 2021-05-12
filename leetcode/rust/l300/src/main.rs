struct Solution;

impl Solution {
    pub fn length_of_lis(nums: Vec<i32>) -> i32 {
        if nums.len() == 0 {
            return 0;
        }

        let mut len = 1;

        let mut dp = Vec::with_capacity(nums.len()+1);
        dp.resize(nums.len()+1, 0);

        dp[len] = nums[0];

        for i in 1..nums.len() {
            if nums[i] > dp[len] {
                len += 1;
                dp[len] = nums[i];
            } else {
                let mut l = 1;
                let mut r = len;
                let mut pos = 0;
                while l <= r {
                    let mid = (l + r) >> 1;
                    if dp[mid] < nums[i] {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                dp[pos + 1] = nums[i];
            }
        }
        len as i32
    }
}

fn main() {
    Solution::length_of_lis(vec![10,9,2,5,3,7,101,18]);
}
