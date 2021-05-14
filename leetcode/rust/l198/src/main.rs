struct Solution;

impl Solution {
    pub fn rob(nums: Vec<i32>) -> i32 {
        if nums.is_empty() {
            return 0;
        }
        if nums.len() == 1 {
            return nums[0];
        }
        if nums.len() == 2 {
            return nums[0].max(nums[1]);
        }

        let mut v = vec![nums[0], nums[0].max(nums[1])];
        let mut ret = v[0].max(v[1]);

        for i in 2..nums.len() {
            v.push(v[i - 1].max(v[i - 2] + nums[i]));
            ret = v[i].max(ret);
        }

        ret
    }

    pub fn rob1(nums: Vec<i32>) -> i32 {
        if nums.is_empty() {
            return 0;
        }
        if nums.len() == 1 {
            return nums[0];
        }
        if nums.len() == 2 {
            return nums[0].max(nums[1]);
        }
        let mut nums = nums;

        nums[1] = nums[0].max(nums[1]);
        let mut ret = nums[1];

        for i in 2..nums.len() {
            nums[i] = nums[i - 1].max(nums[i - 2] + nums[i]);
            ret = nums[i].max(ret);
        }

        ret
    }
}

fn main() {
    assert_eq!(Solution::rob(vec![1, 2, 3, 1]), 4);
}
