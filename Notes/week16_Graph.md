# More graph

## The Minimum Spaning Tree 
In an connected undirected graph: the spanning tree $T$ is a tree, which connects all the verteces in the graph, with cost equals the sum of weights of edges.

Given a connected undirected weighted graph $(G,W)$ with $G = (V,E)$. The goal of the minimum spanning tree (MST) problem is to find the spanning tree with the smallest cost.

The MST might not be unique.

1. Find the smallest edge in the graph.
2. Repeat:
2.1 Find the smallest extension edge with the smallest weight.
2.2

## The Strongly Connected Component
1. Build inverted graph of the original graph.
2. perform DFS on the inverted graph and record the out-stack order.
3. Reverse the out-stack order.
4. Perform BFS/DFS on the original graph int the reversed out-stack order. The points you can reach are a strongly connected component set.