struct UF(Vec<i32>);
impl UF {
    fn with_capacity(len: i32) -> Self {
        Self((0..len).collect())
    }
    fn u(&mut self, mut x: i32, mut y: i32) -> bool {
        x = self.f(x);
        y = self.f(y);
        (self.0)[x as usize] = y;
        x != y
    }
    fn f(&mut self, x: i32) -> i32 {
        if x != (self.0)[x as usize] {
            (self.0)[x as usize] = self.f((self.0)[x as usize]);
            (self.0)[x as usize]
        } else {
            x
        }
    }
}
impl Solution {
    pub fn find_circle_num(is_connected: Vec<Vec<i32>>) -> i32 {
        let mut ans = is_connected.len() as i32;
        let mut uf = UF::with_capacity(ans);
        for (i, x) in (0..).zip(is_connected.into_iter()) {
            for (j, b) in (0..).zip(x.into_iter()).skip(i + 1) {
                if (b == 1) && uf.u(i as i32, j as i32) {
                    ans -= 1
                };
            }
        }
        ans
    }
}

fn main() {
    println!("Hello, world!");
}
