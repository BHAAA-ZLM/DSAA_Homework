# Algorithm Analysis - Time Complexity 

RAM computational model: 
if we count all the CPU processes
Brutal search: $f(n) = 4n +1$
binary search: $g(n) = 9 \log_2{(n) }+ 11 $
From our math knowledge, we will know that $f(n) > g(n)$
But on a CPU, some processes takes longer time. (division: 21 CPU cycle, comparison: 1 CPU cycle, so $3n<5n$ might not be true) 
So in computer science, we always ignore the constants in time complexity equations, we only compare the $n$.

### Theoritical Computer Science

#### Asymptotic Analyisis 

Big-O notation:
: Let $f(n) , g(n)$ be two functions of n. We say $ f(n)$ grows asymptotically no faster than $g(n)$, if there is a constant $c_1 > 0$ such that $$f(n) \le c_1 \cdot g(n)$$ holds for all $n \ge c_2$. We can say $$f(n) = O(g(n))$$

e.g. proving $10000\log_2{(n)} = O(n)$

>$\because c1 = 10000$
$ \therefore f(n) \le c_1 \cdot g(n)$ for $n > 0$

we can also prove that $log_a(n) = O(log_b(n))$

asymtotically: 
: Approaching a given value or condition, as a variable or an expression containing a variable approaches a limit, usually infinity.

**Usually Met Big-O notations**:
$O(1)$ : comparison of two numbers
$O(\log(n))$: binary search algorithm
$O(n)$ : for loop, have to be $O(n)$ complexity
$O(n^2)$
$O(n^3)$ : for matrix multiplication
$O(2^n)$
$O(n!)$ : the largest possible Big-O notation


Big-$\Omega$:
: we say $f(n)$ grows asymptotically no slower than $g(n)$ if there is a constant $c_1 > 0$ such that $$ f(n) \ge c_1 \cdot g(n)$$ holds for all $n \ge c_2$ we say $f(n) = \Omega(g(n))$

Big-$\Theta$:
: if $f(n) = O(g(n)), f(n) = \Omega(g(n))$, we say that $f(n) = \Theta (g(n))$

# Sorting Algorithm

The Sorting Problem:

Input:
: an array A[1,...,n] with n integers.

Output:
: a sorted array A (in ascending or disending order)

Different Sorting Algorithms: 
1. bubble sort
2. selection sort 
3. quick sort 
4. heap sort 
5. shell sort 
6. insertion sort
7. merge sort 

Comparison-based sorting (what we focus on)
1. O(n^2): bubble, selection, insertion.
2. O(nlogn): merge sort.
3. quick sort (worst case $O(n^2)$ theoretical case $O(n\log{n})$)

**Selection Sort**: pick the smallest(or largest) from the array.
```
1. for(int i = 0; i < array.length - 1; i++){       n-1         O(n)
2.      k = i;                                       n-1         O(n)
3.      for(int j = i + 1, j < array.length; j++){   n(n-1)/2    O(n^2)
4.          if(A[k] > A[j]){                          n(n-1)/2    O(n^2)
5.              k = j;                               n(n-1)/2    O(n^2)
            }
        }
6.      swap A[i] and A[k];                           n-1         O(n)
}
```
time complexity: $f(n) = \frac{3}{2}n^2 + \frac{3}{2}n - 3$, $O(n^2)$

**Insertion Sort**: 
```
1. for integer i <- n to 1
2.  for integer j <- i to 1 with j > 2
3.      if A[j-1] > A[j] then 
4.          swap A[j-1], A[j]
5.      else break;
```
time complexity: $O(n^2)$

**Bubble Sort**:
```
1. for integer i = 1 to n-1
2.      for integer j = 2 to n 
3.          if A[j-1] > A[j] then
4.              swap A[j-1], A[j]
```
time complexity: $O(n^2)$

Other Sorting: radix sorting, bucket sorting.


