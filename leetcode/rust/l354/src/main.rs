struct Solution;
use std::cmp::Ordering;
impl Solution {
    pub fn max_envelopes(envelopes: Vec<Vec<i32>>) -> i32 {
        if envelopes.is_empty() {
            return 0;
        }
        let mut envelopes = envelopes;
        let n = envelopes.len();

        envelopes.sort_by(|e1, e2| {
            if e1[0] < e2[0] || (e1[0] == e2[0] && e1[1] > e2[1]) {
                Ordering::Less
            } else {
                Ordering::Greater
            }
        });
        let mut f = vec![envelopes[0][1]];
        for i in 1..n {
            let num = envelopes[i][1];
            if num > *f.last().unwrap() {
                f.push(num);
            } else {
                let it = f.binary_search(&num);
                if let Err(it) = it {
                    f[it] = num;
                }
            }
        }
        f.len() as i32
    }
}

fn main() {
    Solution::max_envelopes(vec![
        vec![1, 2],
        vec![2, 3],
        vec![3, 4],
        vec![3, 5],
        vec![4, 5],
        vec![5, 5],
        vec![5, 6],
        vec![6, 7],
        vec![7, 8],
    ]);
}
