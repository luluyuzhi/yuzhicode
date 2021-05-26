// 线段树
struct NumArray {
    data: Vec<i32>,
    nums: Vec<i32>,
}

impl NumArray {
    fn new(nums: Vec<i32>) -> Self {
        let n = nums.len();
        let data = vec![0; n * 4];
        let mut na = NumArray { nums, data };
        na.build_tree(0, 0, n - 1);
        na
    }

    fn build_tree(&mut self, node: usize, lo: usize, hi: usize) {
        if lo == hi {
            self.data[node] = self.nums[lo];
        } else {
            let mid = lo + (hi - lo) / 2;
            let left_child = node * 2 + 1;
            let right_child = node * 2 + 2;
            self.build_tree(left_child, lo, mid);
            self.build_tree(right_child, mid + 1, hi);
            self.data[node] = self.data[left_child] + self.data[right_child];
        }
    }

    fn query_tree(&mut self, node: usize, lo: usize, hi: usize, l: usize, r: usize) -> i32 {
        if hi < l || lo > r {
            0
        } else if hi <= r && lo >= l {
            self.data[node]
        } else if lo == hi {
            self.data[node]
        } else {
            let mid = lo + (hi - lo) / 2;
            let left_child = node * 2 + 1;
            let right_child = node * 2 + 2;
            let left_val = self.query_tree(left_child, lo, mid, l, r);
            let right_val = self.query_tree(right_child, mid + 1, hi, l, r);
            left_val + right_val
        }
    }

    fn update_tree(&mut self, node: usize, index: usize, lo: usize, hi: usize) {
        if lo == hi {
            self.data[node] = self.nums[index];
        } else {
            let mid = lo + (hi - lo) / 2;
            let left_child = node * 2 + 1;
            let right_child = node * 2 + 2;
            if index <= mid {
                self.update_tree(left_child, index, lo, mid);
            } else {
                self.update_tree(right_child, index, mid + 1, hi);
            }
            self.data[node] = self.data[left_child] + self.data[right_child];
        }
    }

    pub fn update(&mut self, index: i32, val: i32) {
        self.nums[index as usize] = val;
        self.update_tree(0, index as usize, 0, self.nums.len() - 1);
    }

    pub fn sum_range(&mut self, left: i32, right: i32) -> i32 {
        self.query_tree(0, 0, self.nums.len() - 1, left as usize, right as usize)
    }
}

fn main() {
    println!("Hello, world!");
}
