# Introduction

## Goals

#### Fundamental data structures
* ways of imposing order on a collection of information (How to implement sorting algorithms into softwares)
* Lists, stacks, queues, trees, heap, graphs.

#### Algorithms
* algorithms related to data structures
* learn how to compare data structures and algorithms

#### Final Goals
* think more intelligently about problems.
* to be a scientist rather than a monkey coder.

## Prerequisites
* try to use terminal to write the programs. (compile source file by Command or Makefile)
* understand simple proving methods

Textbook chapters after each class. **Introduction to Algorithms** and **Data Structures and Algorithms Analysis in C++**

Solve as many problems as possible [here](http://acm.hdu.edu.cn).

## Solving Real World Problems
Abstract real world problems and procedures into things machines can understand.

#### Example: Sorting
* sort a set of cards
* sort the student list according to their scores.

## Algorithms

***Algorithms***: a well defined sequence of steps for solving a computational problem.
* It produces the correct output.
* It uses basic steps or defined operations.
* It should be finished in finite time.

*e.g.* The card sorting
* Start with empty hand and all cards on table (input: cards)
* Pick the smallest card
* Insert the card into the hand (output: sorted cards)

we need to let the computer know what are the inputs and outputs.
we need to translate a human algorithm into machine-understandable algorithm.
pseudo-codes are algorithms every machine understands.

algorithm + data structure = program

## Data Structure

A set of itmes(`S`) + A search key for the item(`x`)
* `search(S,x)`
* `insert(S,x)`
* `delete(S,x)`

***Data Structure***: A way of organizing data for efficient usage. Are the building blocks for designing algorithms.

Through data structure, we can reduce the time need to search a certain thing, because we previously classified it. (*e.g.* R trees)

## Algorithms Design Techniques

*Incremental technique*:
* Build a solution into a larger solution (solve small problems, then solve the big problems) (*e.g.* solve `i-1` first then `i`)

*Recursive technique*:
* Reduce the problem into smaller subproblems. (divide and conquer)


