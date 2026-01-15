# java-minesweeper

A terminal-based Minesweeper game in Java. This project focuses on recursive algorithms, robust input validation, and tracking player statistics.

## Technical Features
- Recursive Area Clearing: Opens adjacent safe cells using a flood-fill algorithm.
- Robust Input Validation: Custom integer validator prevents crashes from non-numeric or invalid inputs.
- Move Tracking: Tracks and displays the total number of moves made during a session.
- Flagging System: Toggleable markers (▲) to track suspected mine locations.
- ANSI Color UI: Color-coded display with row and column indices for precise navigation.

## Execution
To compile and run the game, use these commands in your terminal:

javac MineSweeper.java
java MineSweeper

## Controls
- A: Reveal cell (Safe places)
- B: Toggle mine flag (Mark/Unmark)
- C: Restart game (Return to main menu)
- D: Close application
