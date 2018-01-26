Building
########
The code can be compiled, unit tests run, and packaged into a .JAR file using Maven.

Additions to the specification
##############################
Accepts 3 optional command line arguments
  --ansi - use colored output on ANSI compatible terminals
  --big - play on a 15x12 board
  --tiny - play on a 5x5 board


Assumptions
###########

- With no mention of what to do after a round, the program exits after a win
- The code requires no external libraries to run, but JUnit is required for tests
- It is written to show maintainable, extensible code rather than how I would implement it for a one-off or well-scoped project. So it has:
-- Interfaces with only one implementation
-- Methods that could be inlined, but exist for legibility
- The Java language level was kept low deliberately, avoiding lambdas, newer APIs etc.