#String

| primitive data structure | non-primitive data structure(linear) | non-primitive data structure (non-linear)|
|:---|---|---:|
|Integer|array|tree/heap|
|Float|linked-list|graph
|Double|stack|
|String|queue|

None primitive data structure can store primitive data structures.

String is the only primitive data structure we will learn in DSAA.

String:
: string is a sequence of characters over an alphabet.

Alphabet:
: a collection of characters.
e.g. Binary {0,1}, string = '01001011101'
DNA{A,C,G,T}, string = 'ACAACTCGGGA'
English{a...z,A...Z}, string = 'Hello world'

String applications:
1. word processing
2. web search
3. virus scanning

#### String operators
- append
- assign
- insert
- erase 
- replace 
- swap 
- find

Substring:
: e.g. substrings number for "SUSTechCS203" is 79 ($\frac{(12+1)\cdot 12}{2} + 1$)


#### String search
search string or search pattern

Given a pattern string P, and a string text T, find the first position of P in T.
T: assusustechcs  P: sustech

**brutal**
```Java
for(i <- 0 to n - m - 1){
    for(j <- 0 to m - 1){
        if(T[i + j] != P[j]){
            break;
        }
    }
    if(j = m - 1){
        pattern p occurred at position i;
    }
}
```

