# CLI RPN Calculator

Welcome to _CLI RPN Calculator_, a command-line reverse polish notation (RPN) calculator.

## Description

The utility works in an interactive way, printing response after each input from the user.
Any input line is combined with all the lines typed previously, and the result is evaluated to some real number. If the evaluation is successful, the calculation result is printed to the console, otherwise the error message is printed and the whole last line is discarded.   

Each input line is expected to be one of the following:

* list of integer or real numbers or arithmetic operations (+, -, *, /) separated by spaces; 
* **u** command, which means _Undo_;
* **q** command, which is used for exit.

## Usage

Clone the source code using [git](https://git-scm.com) and cd into the retrieved directory:

```sh
git clone https://github.com/boyaryn/calculator.git
cd calculator
```

Assuming Java 8+ and maven are installed, build the jar:

```sh
mvn package
```

Run the calculator:

```sh
java -cp target/calculator-1.0-SNAPSHOT.jar ua.in.boyaryn.App
```

## Intent

The goal of developing this utility is to demonstrate what the author's coding style may look like. Since the author was encouraged to take creative liberty with development, the additional requirement to provide Undo functionality was added based on what he lacked most in basic calculator implementations.

In real life, it would probably be better to design an application created for internal use by a small group of people and temporarily in a simpler way.

Conversely, the first version of a tool expected to be used outside the team or for a long time should probably require gathering more requirements and be developed in a more extensible way, with more tests and documentation. If a high load is expected, other data structures (that a Stack instance cloned after every input with a valid expression, for example) and paradigms (than one-threaded and non-distributed application) should be used.  

## Links

The latest code can be found in [GitHub](https://github.com/boyaryn/calculator). The requirements used for development are in *instructions.md* in this repository and [here](https://gist.github.com/joedean/078a62b9ec03b38dfc519b3a5f168b07).
