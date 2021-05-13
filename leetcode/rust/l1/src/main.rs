struct Solution;

impl Solution {
    pub fn two_sum1(nums: Vec<i32>, target: i32) -> Vec<i32> {
        let mut m = std::collections::HashMap::with_capacity(nums.len());

        for (cur, &itr) in nums.iter().enumerate() {
            if m.contains_key(&(target - itr)) {
                return vec![
                    if let Some(&x) = m.get(&(target - itr)) {
                        x
                    } else {
                        0
                    },
                    cur as i32,
                ];
            }

            m.insert(itr, cur as i32);
        }
        vec![]
    }
}

fn main() {
    println!("Hello, world!");
}
