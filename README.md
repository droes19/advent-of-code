# Advent of Code Solutions

This repository contains my solutions for [Advent of Code](https://adventofcode.com/) challenges, implemented in multiple programming languages.

## Repository Structure

```
├── 2015/
│   ├── java/           # Java solutions for 2015
│   └── input/          # Input files for 2015
├── 2024/
│   ├── java/           # Java solutions for 2024
│   ├── go/             # Go solutions for 2024
│   ├── rust/           # Rust solutions for 2024
│   └── input/          # Input files for 2024
└── .gitignore
```

## Languages Used

- **Java** - Primary language for most solutions
- **Go** - Alternative implementations for some 2024 challenges
- **Rust** - Performance-focused implementations for specific challenges

## Completed Solutions

### 2015
- Day 1: Not Quite Lisp (Parts 1 & 2)
- Day 2: I Was Told There Would Be No Math (Parts 1 & 2)
- Day 3: Perfectly Spherical Houses in a Vacuum (Parts 1 & 2)
- Day 4: The Ideal Stocking Stuffer
- Day 5: Doesn't He Have Intern-Elves For This? (Parts 1 & 2)
- Day 6: Probably a Fire Hazard (Parts 1 & 2)
- Day 7: Some Assembly Required (Parts 1 & 2)
- Day 8: Matchsticks (Parts 1 & 2)

### 2024
- Day 1: Historian Hysteria (Parts 1 & 2)
- Day 2: Red-Nosed Reports (Parts 1 & 2)
- Day 3: Mull It Over (Parts 1 & 2)
- Day 4: Ceres Search (Parts 1 & 2)
- Day 5: Print Queue (Parts 1 & 2) - Go implementation
- Day 6: Guard Gallivant (Parts 1 & 2) - Java, Go, and Rust implementations
- Day 7: Bridge Repair (Parts 1 & 2)
- Day 8: Resonant Collinearity (Parts 1 & 2)

## Running the Solutions

### Java Solutions

Navigate to the appropriate year's java directory:

```bash
cd 2024/java  # or 2015/java
javac dayX.java
java dayX
```

**Note**: Update the file path in the source code to use test input:
- Change `../input/dayX.input` to `../input/dayX.test` for sample data

### Go Solutions

Navigate to the Go directory:

```bash
cd 2024/go
go run dayX.go
```

### Rust Solutions

Navigate to the Rust directory:

```bash
cd 2024/rust
cargo run dayX    # or dayX_2 for part 2
```

## Input Files

- `*.input` - Actual puzzle input (not tracked in git)
- `*.test` - Sample input provided in problem descriptions

To use your own input:
1. Download your input from the Advent of Code website
2. Save it as `dayX.input` in the appropriate input directory
3. Ensure the file path in the solution matches your input file

## Key Features

### Performance Optimizations
- **Day 6 Rust Implementation**: Optimized for performance-critical pathfinding
- **BigInteger Usage**: Java solutions use BigInteger for large number calculations (Day 7)
- **Efficient Data Structures**: HashMap and HashSet implementations for fast lookups

### Algorithm Highlights
- **Bitwise Operations**: Day 7 (2015) implements a circuit simulator with bitwise logic
- **MD5 Hashing**: Day 4 (2015) uses MD5 for proof-of-work style calculations
- **Grid Traversal**: Multiple days feature 2D grid navigation and pathfinding
- **String Processing**: Advanced regex and string manipulation techniques

## Development Notes

### Code Organization
- Each solution is self-contained with its own main method
- Input parsing is consistent across solutions
- Error handling includes proper file exception management

### Testing
- Test files are provided for verification
- Solutions can be easily switched between test and actual input
- Debug output available in most implementations

## Contributing

Feel free to:
- Suggest optimizations
- Add solutions in other languages
- Improve code documentation
- Report bugs or issues

## License

This project is open source. Feel free to use these solutions for learning purposes.

---

**Note**: This repository is for educational purposes. Please don't use these solutions to cheat on current Advent of Code challenges. The fun is in solving them yourself! 🎄
