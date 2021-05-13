/*
 * @lc app=leetcode.cn id=420 lang=rust
 *
 * [420] 强密码检验器
 */

//! 这个题太没意思了，不会做了
//!
//! 解题思路：
// 主要先考虑如果去消除连续字符，nn 代表步数，ss 代表连续的个数，最后的目标都是小于 33。

// 删除 效率最低 s-n*1<3
// 插入 效率其次 s-n*2<3
// 替换 效率最高 s-n*3<3
// 举例 aaaaa 五连字符，要正确的话如果只删除要 33 步， 如果插入的话要 22 步，如果替换只需要替换中间的 aa 一步就可以完成。

// 接下来 分情况讨论

// 长度 <6 ，步数=缺失类型和缺失长度取大者。
// 长度 (6,20)，这时候我们不需要低效的插入和删除来处理连续字符，直接替换步数就等于处理连续字和缺失类型取大者。
// 比较负载的是 >20，我们需要知道优先级，一样优先处理连续数组。
// 优先处理缺失类型，用替换的方式来处理，这时候要替换的连续组的连续数 %3==2 -> 连续数%3==1 -> 连续数%3==0，然后处理多余字符，删除的优先级是连续组的连续数 %3==0 -> 连续数%3==1 -> 连续数%3==2。

// 作者：chu-yun-xi-yi
// 链接：https://leetcode-cn.com/problems/strong-password-checker/solution/zhi-xing-1ms-ji-bai-100-javajie-ti-si-lu-by-chu-yu/
// 来源：力扣（LeetCode）
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

struct Solution;

// @lc code=start
impl Solution {
    pub fn strong_password_checker(password: String) -> i32 {
        let pswlen = password.len();
        let mut uppercase_figure = false;
        let mut lowercase_figure = false;
        let mut number = false;
        let mut repeated = 0;

        let mut mu = std::char::MAX;
        let mut count = 0;
        for cur in password.chars() {
            uppercase_figure = cur.is_uppercase();
            lowercase_figure = cur.is_lowercase();
            number = cur.is_numeric();
            if cur == mu {
                count += 1;
            } else {
                repeated = repeated + count % 3;
                count = 0;
            }
        }

        let mut hubu = 0;
        1
    }
}
// @lc code=end

fn main() {
    println!("Hello, world!");
}
