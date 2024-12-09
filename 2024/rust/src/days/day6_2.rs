use std::fs;

//slower then java, need optimizing
pub fn day6_2() {
    //let file_content = fs::read_to_string("../input/day6.test").expect("no");
    let file_content = fs::read_to_string("../input/day6.input").expect("no");
    println!("");

    let rows: Vec<&str> = file_content.trim().split('\n').collect();

    let (first_board, first_y, first_x, first_step) = get_board_y_x(&rows);

    let mut success_list: Vec<String> = Vec::new();
    let mut failed_list: Vec<String> = Vec::new();

    let mut counter = 0;
    'run: loop {
        let (board, curr_y_x, set_break) = set_block(
            &first_board,
            first_y,
            first_x,
            &first_step,
            &success_list,
            &failed_list,
        );

        if set_break {
            break 'run;
        }

        let success = run_game(&board, first_y, first_x, &first_step);
        if success {
            success_list.push(curr_y_x);
        } else {
            failed_list.push(curr_y_x);
        }

        //println!("{:?}", success_list);
        //println!("{:?}", failed_list);
        println!("counter = {}", counter);
        counter += 1;
    }
    println!("{}", success_list.len());
}
//
fn get_board_y_x(rows: &Vec<&str>) -> (Vec<Vec<String>>, usize, usize, String) {
    let mut board: Vec<Vec<String>> = Vec::new();
    let mut x = 0;
    let mut y = 0;
    let mut step = String::from("");

    let mut idy = 0;
    for row in rows {
        let cols: Vec<String> = row.trim().chars().map(|c| c.to_string()).collect();
        board.push(cols.clone());

        let mut idx = 0;
        if step == "" {
            for col in cols {
                if col == "^" || col == "v" || col == "<" || col == ">" {
                    x = idx;
                    y = idy;
                    step = col;
                }
                idx += 1;
            }
        }

        idy += 1;
    }

    (board, y, x, step)
}
fn set_block(
    first_board: &Vec<Vec<String>>,
    first_y: usize,
    first_x: usize,
    first_step: &String,
    success_list: &Vec<String>,
    failed_list: &Vec<String>,
) -> (Vec<Vec<String>>, String, bool) {
    let mut board = first_board.to_vec();
    let mut y = first_y;
    let mut x = first_x;
    let mut step = first_step.clone();
    let mut curr_y_x = String::from("");
    let mut set_break = true;
    loop {
        match &*step {
            "^" => {
                if y == 0 {
                    break;
                } else if board[y - 1][x] == "#" {
                    x += 1;
                    step = ">".to_string();
                } else {
                    y -= 1;
                }
            }
            ">" => {
                if x == board[0].len() - 1 {
                    break;
                } else if board[y][x + 1] == "#" {
                    y += 1;
                    step = "v".to_string();
                } else {
                    x += 1;
                }
            }
            "v" => {
                if y == board.len() - 1 {
                    break;
                } else if board[y + 1][x] == "#" {
                    x -= 1;
                    step = "<".to_string();
                } else {
                    y += 1;
                }
            }
            "<" => {
                if x == 0 {
                    break;
                } else if board[y][x - 1] == "#" {
                    y -= 1;
                    step = "^".to_string();
                } else {
                    x -= 1;
                }
            }
            _ => break,
        }
        curr_y_x = format!("{}_{}", y, x);
        if !(success_list.contains(&curr_y_x) || failed_list.contains(&curr_y_x)) {
            board[y][x] = "O".to_string();
            set_break = false;
            break;
        }
    }
    (board.to_vec(), curr_y_x, set_break)
}

fn run_game(board: &Vec<Vec<String>>, y: usize, x: usize, step: &String) -> bool {
    let mut board = board.to_vec();
    let mut y = y;
    let mut x = x;
    let mut step = step.clone();
    let mut turn: Vec<String> = Vec::new();
    let mut success = false;
    loop {
        match &*step {
            "^" => {
                if y == 0 {
                    break;
                } else if board[y - 1][x] == "#" || board[y - 1][x] == "O" {
                    board[y][x] = "+".to_string();
                    let t = format!("{}_{}{}", y - 1, x, step);
                    if turn.contains(&t) {
                        success = true;
                        break;
                    } else {
                        turn.push(t);
                    }
                    step = ">".to_string();
                } else {
                    if board[y][x] == "-" || board[y - 1][x] == "#" || board[y - 1][x] == "O" {
                        board[y][x] = "+".to_string();
                    } else {
                        board[y][x] = "|".to_string();
                    }
                    y -= 1;
                }
            }
            ">" => {
                if x == board[0].len() - 1 {
                    break;
                } else if board[y][x + 1] == "#" || board[y][x + 1] == "O" {
                    board[y][x] = "+".to_string();
                    let t = format!("{}_{}{}", y, x + 1, step);
                    if turn.contains(&t) {
                        success = true;
                        break;
                    } else {
                        turn.push(t);
                    }
                    step = "v".to_string();
                } else {
                    if board[y][x] == "|" || board[y][x + 1] == "#" || board[y][x + 1] == "O" {
                        board[y][x] = "+".to_string();
                    } else {
                        board[y][x] = "-".to_string();
                    }
                    x += 1;
                }
            }
            "v" => {
                if y == board.len() - 1 {
                    break;
                } else if board[y + 1][x] == "#" || board[y + 1][x] == "O" {
                    board[y][x] = "+".to_string();
                    let t = format!("{}_{}{}", y + 1, x, step);
                    if turn.contains(&t) {
                        success = true;
                        break;
                    } else {
                        turn.push(t);
                    }
                    step = "<".to_string();
                } else {
                    if board[y][x] == "-" || board[y + 1][x] == "#" || board[y + 1][x] == "O" {
                        board[y][x] = "+".to_string();
                    } else {
                        board[y][x] = "|".to_string();
                    }
                    y += 1;
                }
            }
            "<" => {
                if x == 0 {
                    break;
                } else if board[y][x - 1] == "#" || board[y][x - 1] == "O" {
                    board[y][x] = "+".to_string();
                    let t = format!("{}_{}{}", y, x - 1, step);
                    if turn.contains(&t) {
                        success = true;
                        break;
                    } else {
                        turn.push(t);
                    }
                    step = "^".to_string();
                } else {
                    if board[y][x] == "|" || board[y][x - 1] == "#" || board[y][x - 1] == "O" {
                        board[y][x] = "+".to_string();
                    } else {
                        board[y][x] = "-".to_string();
                    }
                    x -= 1;
                }
            }
            _ => break,
        }
    }
    success
}
