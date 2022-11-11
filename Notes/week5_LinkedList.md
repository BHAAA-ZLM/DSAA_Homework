# Linked Lists

#### Array

*Until now:* We store elements inside an array. 

Advantages of the Array:
1. Random Access: means we can access through `A[i] = x`. Which means, with `A[0]`, we can easily calculate the position of `A[i]`
2. Constant time: Takes a constant time to gain access to an element.

Disadvantages of the Array:
1. Have a specific array size. 
2. Resizing the array is possible but is expensive. (need to copy to a new array)
3. Delete or insert takes $O(n)$ time. Have higher memory cost as well.

#### Linked list
Linked List node:
: the combination of value and a link pointing to the next node. The position of the element is stored inside the previous node.

Head node:
: the first node.

Tail node: 
: the last node with link nil (pointing to nothing).

In array, elements are stored consecutively (one by one) in memory, but the linked list is not.

Disadvantages:
1. No random access, have to go down from head.
2. Need additional memory to store the link.
3. Not compact in term of memory (distributed randomly in the memory).

Advantages:
1. Grow without limit.
2. Easy to delete and insert.

Operators:

The pointer:
: p.a is the values. p.next is the link. 
Suppose `p` point to node a.
We want `q` to point to a: q <- p
We want `q` to point to b (a.next): q <- p.next
We want `p` to point to b (a.next): p <- p.next
Change the link of a node: p.next <- q

#### More about Linked List

**Inserting an item at position i**
```Java
InsertNode(A, node q, i){
    a = 0; node p = A.head;
    while(i - 1 > a){
        p = p.next;
        a = a + 1;
    }
    q.next = p.next;
    p.next = q;
    return A;
}
```

**Deleting an item at position i**
```Java
DeleteNode(A, i){
    a = 0; node p = A.head;
    while(i - 1 > a){
        p = p.next;
        a++;
    }
    p.next = p.next.next;
    return A;
}
```

**Finding the item at position i**
```Java
FindNode(A, i){
    a = 0, Node p = A.head;
    while(i - 1 > a){
        p = p.next;
        a++;
    }
    return p.next;
}
```

**Finding the item with value i**
```Java
FindValue(A, i){
    node p = A.head;
    while(p.value != i){
        p = p.next
    }
    if(p == null){
        return -1;
    }
    return p;
}
```

**Updating node with value x to y in A**
```Java
update(A, x, y){
    while(p != null){
        if(p.value = x){
            p.value = y;
        }
        p = p.next;
    }
    return A;
}