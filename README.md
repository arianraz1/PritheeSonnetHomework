# Sonnet Guessing Game

A console-based word guessing game based on a given sonnet.
The sonnet is hard-coded to match the project specification; however,
the core logic stands independently.

## Running the program
Run the `Main` class. The program prints a sonnet up to a random word, masks that word, 
and prompts the user to guess the missing word.
The game ends after either three correct or three incorrect guesses.

## Project structure
- `Main` — handles game loop, logic, and console output
- `SonnetPlayer` — encapsulates word parsing, masking, and validation logic
- `SonnetPlayerTest` — unit tests for SonnetPlayer's core logic
- `docs/architecture.png` — high-level architectural design diagram
