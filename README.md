# java-minesweeper
A terminal-based Minesweeper game in Java featuring ANSI color styling and dynamic difficulty levels.
# java-minesweeper

A terminal-based Minesweeper game in Java featuring ANSI color styling and dynamic difficulty levels. This project focuses on 2D array manipulation, coordinate-based logic, and terminal UI formatting.

## Features
- Dynamic Difficulty Levels:
    - Easy: 8x10 grid with 10 mines.
    - Medium: 14x18 grid with 40 mines.
    - Hard: 20x24 grid with 99 mines.
- ANSI Color UI: Implementation of terminal escape codes for mines (Red), hidden cells (Cyan), and neighbor counts (Yellow).
- 8-Neighbor Detection: Algorithmic logic to calculate adjacent mines for specific coordinates.
- Symmetrical Grid Rendering: Mathematical formatting to ensure consistent cell width across different grid sizes.

## Technologies
- Language: Java
- Libraries: Scanner API, Random API
- Formatting: ANSI Escape Sequences

## Roadmap and Current Status
The project is currently in the initial development phase. The core game loop and visualization are functional.

Planned Enhancements:
- Implementation of the Flood-Fill algorithm for recursive clearing of empty cells.
- Input validation to handle out-of-bounds coordinate exceptions.
- Refinement of win/loss state transitions and game-over logic.

## Execution
To run the game locally, follow these steps:

1. Compile the source file:
   ```bash
   javac MineSweeper.java