# BFS and DFS

## The Breadth First Search

Solving the Single Source Shortest Path (SSSP).
Starting from only one node, what is the shortest path from this node to any other node in the graph.

Using the breadth first search, colouring the nodes with white, red and yellow.

At the beginning, we colour all the nodes white.

If the node is in the queue, colour it yellow.

If it is dequeued, colour it red.

**Time Complexity**
We need to enqueue and dequeue all the nodes in the graph, so the time complexity is $O(|V|)$.

For each node, we need to check each out path, thus the complexity is the total out-degree of all nodes, that is the total number of edges $O(|E|)$

So the time complexity is $O(|V| + |E|)$

## The Depth First Search

Cyclic graph : a graph (directed or undirected) with a cycle.
Directed Acyclic Graph (DAG) : a directed graph with no cycle.

1. Colour all nodes white and create a empty DFS tree $T$, an empty stack $S$.
2. Pick an arbitrary vector $v$, push $v$ into $S$ and colour it yellow. 
3. Make $v$ as the root of $T$.
4. Repeat the following until S is empty.
4.1 Get the node at the top of the stack $t$.
4.2 Does $t$ have a white neighbour?
4.2.1 If yes, push the white neighbour into $S$, colour it yellow, make it the child of $t$ in $T$.
4.2.2 If no, pop $t$ from stack and colour it red.
5. If there are still white nodes, repeat from a arbitrary white node and create a new tree $T$.

Edge Classification:
1. Forward Edge: If in $G$ there is $a \rightarrow b$, $a$ is a proper ancestor of $b$ in $T$
2. Backward Edge: If in $G$ there is $a \rightarrow b$, $a$ is a proper descendent of $b$ in $T$
3. Cross Edge: If neither of the above applies.

Augmented DFS:
: add a counter, each time we push or pop a vertices, add the counter, and thus define a discovery time and a deletion time for each node. $I(u)$ : get the time interval of $u$.

$u$ is a proper ancestor of $t$ if $I(t) \in I(u)$ .

$u$ is a proper descendent of $t$ if $I(u) \in I(t)$ .

$u$ and $v$ are not connected if $I(u) \cap I(t) = \emptyset$.

Cycle Therom:
: $G$ contains a cycle if and only if there is a backward edge in $T$.