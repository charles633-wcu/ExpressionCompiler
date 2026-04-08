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








CSC220 Foundations of Computer Science  
Mini Expression Compiler

Project Summary

This project is a Java-based mini compiler that processes mathematical expressions through the core phases of compilation. It simulates how modern compilers analyze and evaluate expressions using:

• Lexical Analysis  
• Recursive Descent Parsing  
• Abstract Syntax Tree (AST) Construction  
• Expression Evaluation  

The system takes a string input (e.g., (3+2)*5-1), validates its syntax using a context-free grammar, constructs an AST to represent operator precedence, and evaluates the final result.

---------------------------------------

Features

Lexical Analysis  
Converts raw input strings into structured tokens such as:
- Numbers
- Operators (+, -, *, /)
- Parentheses

Recursive Descent Parsing  
Validates input using a mathematical grammar structure:

Expression → Term  
Term → Factor  
Factor → Number | (Expression)

AST Generation  
Builds an Expression Tree that preserves operator precedence and hierarchy.

Unary Minus Support  
Handles negative numbers (e.g., -3 + 5).

Error Handling  
Detects:
- Invalid syntax (e.g., 3+*5)
- Mismatched parentheses
- Incomplete expressions

---------------------------------------

Constraints

- Operates on integers only.
- Division results are evaluated using integer division (floor behavior).

---------------------------------------

How to Run

Compile all Java files, then run:

java Main

---------------------------------------

Example Test Cases

Valid Expressions

1. 3 + 4 * 2  
Result: 11  

2. (1+2)*(3+4)  
Result: 21  

3. ((3))  
Result: 3  

Invalid Expressions

3+*5  
3+(4-)

---------------------------------------

Program Output

For every input, the program prints:

• List of extracted tokens  
• Syntax validation result  
• Expression tree representation  
• Final evaluated result  
