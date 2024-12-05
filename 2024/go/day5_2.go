package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var rules [][]string

func main() {
	file, err := os.Open("../input/day5.input")
	if err != nil {
		fmt.Println("Error opening file:", err)
		return
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)

	var puzz []string
	isRule := true
	for scanner.Scan() {
		t := strings.TrimSpace(scanner.Text())
		if isRule {
			if t == "" {
				isRule = false
			} else {
				rule := strings.Split(t, "|")
				rules = append(rules, rule)
			}
		} else {
			puzz = append(puzz, t)
		}
	}

	res := 0
	for _, str := range puzz {
		sa := strings.Split(str, ",")
		ln := len(sa)

		v := process(sa, ln)
		if !v && process2(sa, ln) {
			idx := ln / 2
			r, _ := strconv.Atoi(sa[idx])
			res += r
		}
	}
	fmt.Println(res)

	if err := scanner.Err(); err != nil {
		fmt.Println("Error reading file:", err)

	}
}

func isValid(s1 string, s2 string) bool {
	for _, rule := range rules {
		if s1 == rule[0] && s2 == rule[1] {
			return true
		}

	}
	return false
}

func process(sa []string, ln int) bool {
	v := true
	for idx, s := range sa {
		valid := false
		if idx < ln-1 {
			valid = isValid(s, sa[idx+1])
		} else {
			valid = true
		}
		if !valid {
			v = false
			break
		}
	}
	return v
}

func process2(sa []string, ln int) bool {

	idx := 0
	for {
		if idx == ln-1 {
			if process(sa, ln) {
				return true
			} else {
				idx = 0
			}
		}
		s1 := sa[idx]
		s2 := sa[idx+1]
		v := isValid(s1, s2)

		if !v {
			sa[idx] = s2
			sa[idx+1] = s1
		} else {
			idx++
		}
	}
	return false
}
