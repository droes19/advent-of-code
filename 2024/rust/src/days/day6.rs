use std::fs;

pub fn day6() {
    let file_content = fs::read_to_string("../input/day6.input").expect("no");
    println!("");

    let rows: Vec<&str> = file_content.trim().split('\n').collect();

    let (mut board, y, x) = get_board_y_x(rows);

    board = run_game(board.clone(), y, x);

    let mut res = 0;
    for row in &board {
        for col in row {
            if *col == "X" {
                res += 1;
            }
        }
    }
    println!("{}", res);
}

fn get_board_y_x(rows: Vec<&str>) -> (Vec<Vec<&str>>, usize, usize) {
    let mut board: Vec<Vec<&str>> = Vec::new();
    let mut x = 0;
    let mut y = 0;

    let mut idy = 0;
    for row in rows {
        let cols: Vec<&str> = row.trim().split("").collect();
        board.push(cols.clone());

        let mut idx = 0;
        for col in cols {
            if col == "^" || col == "v" || col == "<" || col == ">" {
                x = idx;
                y = idy;
            }
            idx += 1;
        }

        idy += 1;
    }

    (board, y, x)
}

fn run_game(mut board: Vec<Vec<&str>>, mut y: usize, mut x: usize) -> Vec<Vec<&str>> {
    loop {
        match board[y][x] {
            "^" => {
                board[y][x] = "X";
                if y == 0 {
                    break;
                }
                (board, y, x) = get_top(board.clone(), y, x);
            }
            ">" => {
                board[y][x] = "X";
                if board[y][x + 1] == "" {
                    break;
                }
                (board, y, x) = get_right(board.clone(), y, x);
            }
            "v" => {
                board[y][x] = "X";
                if y == board.len() - 1 {
                    break;
                }
                (board, y, x) = get_down(board.clone(), y, x);
            }
            "<" => {
                board[y][x] = "X";
                if board[y][x - 1] == "" {
                    break;
                }
                (board, y, x) = get_left(board.clone(), y, x);
            }
            _ => break,
        }
    }
    board
}

fn get_top(
    mut board: Vec<Vec<&str>>,
    mut y: usize,
    mut x: usize,
) -> (Vec<Vec<&str>>, usize, usize) {
    if board[y - 1][x] == "#" {
        x += 1;
        board[y][x] = ">";
    } else {
        y -= 1;
        board[y][x] = "^";
    }
    (board, y, x)
}

fn get_right(
    mut board: Vec<Vec<&str>>,
    mut y: usize,
    mut x: usize,
) -> (Vec<Vec<&str>>, usize, usize) {
    if board[y][x + 1] == "#" {
        y += 1;
        board[y][x] = "v";
    } else {
        x += 1;
        board[y][x] = ">";
    }
    (board, y, x)
}

fn get_down(
    mut board: Vec<Vec<&str>>,
    mut y: usize,
    mut x: usize,
) -> (Vec<Vec<&str>>, usize, usize) {
    if board[y + 1][x] == "#" {
        x -= 1;
        board[y][x] = "<";
    } else {
        y += 1;
        board[y][x] = "v";
    }
    (board, y, x)
}

fn get_left(
    mut board: Vec<Vec<&str>>,
    mut y: usize,
    mut x: usize,
) -> (Vec<Vec<&str>>, usize, usize) {
    if board[y][x - 1] == "#" {
        y -= 1;
        board[y][x] = "^";
    } else {
        x -= 1;
        board[y][x] = "<";
    }
    (board, y, x)
}
