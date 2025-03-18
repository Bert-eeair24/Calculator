**Project Report: Arithmetic Expression Calculator**

**1. Introduction**
This project implements a simple yet functional arithmetic expression calculator using Java. The calculator supports basic arithmetic operations (+, -, *, /, %) as well as mathematical functions like power, square root, absolute value, and rounding. The user interacts with the calculator through a console interface, entering expressions that are evaluated in real-time. Additionally, a history feature allows users to review past calculations.

**2. Design Choices**
The calculator was designed with modularity in mind, breaking down functionality into separate methods to enhance readability and maintainability. The main components include:
- A loop to handle user input and manage the session.
- A history tracking system using a `List<String>`.
- Expression parsing and evaluation, handling both standard arithmetic operations and function calls.
- Error handling to prevent invalid calculations, such as division by zero.

**3. Algorithms and Data Structures**
- **Stack-Based Expression Evaluation:**
  - A `Stack<Double>` stores operands, and a `Stack<Character>` stores operators.
  - The algorithm processes the input string, pushes numbers onto the operand stack, and applies operations following precedence rules.
  - Parentheses are handled using a stack-based approach to ensure proper order of operations.

- **Function Parsing and Replacement:**
  - Recursive parsing identifies function calls (`power()`, `sqrt()`, `abs()`, `round()`).
  - Each function call is evaluated separately and replaced with its computed value.
  - The implementation includes a helper method `replaceFunction()` to extract function arguments and compute results.

**4. Challenges Encountered**
- **Parsing Complex Expressions:**
  - Handling nested functions required correctly identifying and evaluating inner expressions before outer ones.
- **Maintaining Operator Precedence:**
  - Ensuring correct application of operator precedence required careful implementation of the stack-based approach.
- **Handling Edge Cases:**
  - Invalid inputs such as division by zero, missing parentheses, and non-numeric values were addressed with exception handling.

**5. Improvements and Enhancements**
- **History Feature:**
  - Introduced a history tracking mechanism using an `ArrayList` to store past calculations.
- **Error Handling:**
  - Implemented robust error handling to provide informative messages for invalid input cases.
- **Expanded Functionality:**
  - Added additional mathematical functions (power, square root, absolute value, rounding).
- **Whitespace Handling:**
  - The program removes unnecessary spaces to allow for more flexible input formatting.

**6. File Usage**
- This implementation does not use files for input or output. Instead, all interactions occur through the console. However, future improvements could include:
  - Storing history in a file for persistence across sessions.
  - Reading expressions from a file and processing them in batch mode.

**7. Conclusion**
This calculator successfully evaluates arithmetic expressions and supports mathematical functions while maintaining a simple and user-friendly interface. 
The project demonstrates effective use of stacks, parsing techniques, and modular design principles. 
Future work could focus on improving the expression parser to support more advanced mathematical expressions and introducing file-based input/output functionality.

![image](https://github.com/user-attachments/assets/9b5ba1bb-8268-4a8b-9c44-d269fe014e9f)

