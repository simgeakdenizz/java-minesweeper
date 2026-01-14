# java-minesweeper

A robust, terminal-based Minesweeper game implemented in Java. This project demonstrates advanced concepts like recursive flood-fill algorithms, defensive programming, and ANSI-styled terminal UI.

## Features 
- **Recursive Clearing:** Automated flood-fill logic that opens all adjacent empty cells instantly.
- **Robust Input Validation:** Custom `getSafeInt` logic to prevent crashes from non-numeric or negative inputs.
- **Flagging System:** Toggleable markers (◙) to track suspected mine locations.
- **Dynamic Difficulty:** Choose from Easy, Medium, Hard, or fully Customizable grid dimensions.
- **ANSI Terminal UI:** Symmetrical grid rendering with color-coded mines, flags, and coordinates.

## Execution
To compile and run the game, use these commands:

javac MineSweeper.java
java MineSweeper



## Controls
- **A:** Reveal cell (Safe places)
- **B:** Toggle mine flag (Mark/Unmark)
- **C:** Restart game (Return to main menu)
- **D:** Close application
