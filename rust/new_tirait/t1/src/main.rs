#[derive(Debug)]
struct Person {
    name: String,
    age: u8,
}

fn main() {
    let person = Person {
        name: String::from("Alice"),
        age: 20,
    };

    // `name` is moved out of person, but `age` is referenced.
    let Person { name:cc, ref age } = person;
    println!("{} {}", cc, age);
}       