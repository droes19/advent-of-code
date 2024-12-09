use days::day6_2;

use crate::days::day6;
//use crate::day1;
use std::env;

pub mod days;
fn main() {
    for argument in env::args() {
        let arg = argument.as_str();
        match arg {
            "day6" => {
                print!("{argument} = ");
                day6::day6();
            }
            "day6_2" => {
                print!("{argument} = ");
                day6_2::day6_2();
            }
            _ => println!("{arg}"),
        }
    }
}
