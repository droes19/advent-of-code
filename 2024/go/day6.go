package main

import (
	"bufio"
	"fmt"
	"os"
	"slices"
	"strconv"

	// "strconv"
	"strings"
)

var firstY, firstX, firstStep = 0, 0, "^"

var firstBoard [][]string
var lastBoard [][]string
var lastY, lastX, lastStep = 0, 0, ""

func main() {
	file, err := os.Open("../input/day6.test")
	if err != nil {
		fmt.Println("Error opening file:", err)
		return
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)

	var board [][]string

	idy := 0
	for scanner.Scan() {
		t := strings.Split(strings.TrimSpace(scanner.Text()), "")
		fmt.Println(t)
		board = append(board, t)
		firstBoard = append(firstBoard, t)
		for idx, col := range t {
			// fmt.Printf("%d, %s\n", idx, col)
			if col == "^" || col == "v" || col == ">" || col == "<" {
				firstY = idy
				firstX = idx
				firstStep = col
			}
		}
		idy++
	}

	run(board, firstY, firstX, firstStep)
	res := 0
	// for _, str := range puzz {
	// 	sa := strings.Split(str, ",")
	// 	ln := len(sa)
	// 	v := process(sa, ln)
	// 	if v {
	// 		idx := ln / 2
	// 		r, _ := strconv.Atoi(sa[idx])
	// 		res += r
	// 	}
	// }
	fmt.Println(res)

	if err := scanner.Err(); err != nil {
		fmt.Println("Error reading file:", err)

	}
}

func run(board [][]string, y int, x int, step string) {
	// looping := false
	// var currLoop string
	var successList []string
	var failedList []string

	loopY := y
	loopX := x
	// first:
	b := make([][]string, len(board))
	for {
		// copy(board[:], firstBoard[:])
		for idy, y := range firstBoard {
			board[idy] = y
		}
		if step == "^" {
			loopY -= 1
			cr := strconv.Itoa(loopY) + "_" + strconv.Itoa(loopX)
			if !slices.Contains(successList, cr) && !slices.Contains(failedList, cr) {
				board[loopY][loopX] = "O"

				fmt.Println("board = ")
				for _, d := range firstBoard {
					fmt.Println(d)
				}
			}

		} else if step == ">" {
		} else if step == "v" {
		} else if step == "<" {
		}
	}
	// outer:
	//
	//	for {
	//		switch step {
	//		case "^":
	//			fmt.Println("top")
	//			y -= 1
	//			if board[y][x] == "#" {
	//				y += 1
	//				board[y][x] = "+"
	//				step = ">"
	//			} else {
	//				if !(firstStep == board[y][x]) {
	//					board[y][x] = "|"
	//				}
	//			}
	//		case ">":
	//			fmt.Println("right")
	//			x += 1
	//			if board[y][x] == "#" {
	//				x -= 1
	//				board[y][x] = "+"
	//				step = "v"
	//			} else {
	//				if !(firstStep == board[y][x]) && !("|" == board[y][x]) && !("+" == board[y][x]) {
	//					board[y][x] = "-"
	//				} else {
	//					if !(firstStep == board[y][x]) {
	//						board[y][x] = "+"
	//					}
	//					fmt.Printf("y = %d, x = %d, looping = %s\n", y, x, looping)
	//					cr := strconv.Itoa(y) + "_" + strconv.Itoa(x)
	//
	//					if !looping && !slices.Contains(save, cr) {
	//						lastStep = step
	//						lastY = y
	//						lastX = x
	//
	//						currLoop = cr
	//						board[y][x+1] = "O"
	//						looping = true
	//						step = "v"
	//
	//					} else if looping && cr == currLoop {
	//						save = append(save, cr)
	//						looping = false
	//						currLoop = ""
	//					}
	//				}
	//
	//			}
	//		case "v":
	//			fmt.Println("bottom")
	//			y += 1
	//			fmt.Println(y)
	//			fmt.Println(len(board))
	//			if y == len(board) {
	//				if looping {
	//					step = lastStep
	//					y = lastY
	//					x = lastX
	//					looping = false
	//
	//				} else {
	//					fmt.Println("break")
	//					break outer
	//				}
	//			} else if board[y][x] == "#" {
	//				y -= 1
	//				board[y][x] = "+"
	//				step = "<"
	//			} else {
	//				if !(firstStep == board[y][x]) && !("-" == board[y][x]) && !("+" == board[y][x]) {
	//					board[y][x] = "|"
	//				} else {
	//					if !(firstStep == board[y][x]) {
	//						board[y][x] = "+"
	//					}
	//					fmt.Printf("y = %d, x = %d, looping = %s\n", y, x, looping)
	//					cr := strconv.Itoa(y) + "_" + strconv.Itoa(x)
	//
	//					if !looping && !slices.Contains(save, cr) {
	//						lastStep = step
	//						lastY = y
	//						lastX = x
	//
	//						currLoop = cr
	//						board[y+1][x] = "O"
	//						looping = true
	//						step = "<"
	//					} else if looping && cr == currLoop {
	//						fmt.Println("sini bottom dong")
	//						save = append(save, cr)
	//						looping = false
	//						currLoop = ""
	//					}
	//				}
	//			}
	//		case "<":
	//			fmt.Println("left")
	//			x -= 1
	//			if board[y][x] == "#" {
	//				x += 1
	//				board[y][x] = "+"
	//				step = "^"
	//			} else {
	//				if !(firstStep == board[y][x]) {
	//					board[y][x] = "-"
	//				} else {
	//					fmt.Printf("y = %d, x = %d, looping = %s\n", y, x, looping)
	//					cr := strconv.Itoa(y) + "_" + strconv.Itoa(x)
	//
	//					if !looping && !slices.Contains(save, cr) {
	//						lastStep = step
	//						lastY = y
	//						lastX = x
	//
	//						currLoop = cr
	//						board[y][x-1] = "O"
	//						looping = true
	//						step = "^"
	//						fmt.Printf("lastStep = %s\n", lastStep)
	//
	//					} else if looping && cr == currLoop {
	//						fmt.Println("sini left dong")
	//						save = append(save, cr)
	//						looping = false
	//						currLoop = ""
	//					}
	//				}
	//			}
	//		}
	//
	//		fmt.Println("board = ")
	//		for _, d := range board {
	//			fmt.Println(d)
	//		}
	//		fmt.Println(save)
	//	}
}
