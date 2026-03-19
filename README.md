# CSC220 Foundations of Computer Science: Expression Compiler

## Project Summary
This project is a Java-based compiler  designed to process mathematical expressions through the core phases of compilation. It simulates how modern compilers break down instructions by implementing lexical analysis, recursive descent parsing, and abstract syntax tree generation.

The system takes a string input (e.g., `(3+2)*5-1`), validates its syntax against a context-free grammar, and builds a hierarchical tree to evaluate the final result while respecting operator precedence.

## Features
* **Lexical Analysis:** Converts raw strings into structured tokens like Numbers, Operators, and Parentheses.
* **Recursive Descent Parsing:** Validates input based on a defined mathematical grammar ($E \rightarrow T$, $T \rightarrow F$, etc.).
* **AST Generation:** Constructs an Expression Tree to represent the operation hierarchy.
* **Support of negative numbers:** Capable of handling negative numbers (e.g., `-3`).
* **Error Handling**

## Constraints
This program is meant to run on integers only for memory. Divisions that result in non integer solutions get recorded as the floor function.


## Run program
   ```bash 
   java Main
   ```

## Example Test Cases

### Valid

#### 1. First scenario
``` bash
3 + 4 * 2
```
(Result: 11)

#### 2. With parenthese:
``` bash
(1+2)*(3+4)
```
(Result: 21)

#### 3. Nested:
``` bash
((3))
```
(Result: 3)

### Invalid
 
``` bash
3+*5
```
or
``` bash
3+(4-)
```

## Trace output
### Every input prints a trace:
1. List of tokens extracted from the input
2. Assessment of valid syntax
3. Expression tree visualization
4. Final result evaluated numerically
