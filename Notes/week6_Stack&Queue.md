# Stack and Queue

## Stack

Stack:
: A stack is a sequence of data in which items can be added or removed only at the top.
Items: Last In First Out (LIFO)
Two main operations: push and pop

We can implement a stack using array or linked list.

If we implement stack with array, we need:
- maxsize: n
- top: -1
- the Array: S[n]

```Java
push(item){
    if(top == n - 1){
        return no push;
    }
    top++;
    S[top] = item;
}

pop(){
    if(pop == -1){
        return no pop;
    }else{
        top--;
        //remember that you are only excessing the value with top
        //so the next time you use top++, there will be a new value
    }
}
```

#### What Problems can stack solve
**finding whether brackets are balanced**
pop the bracket if the right bracket matches the left one

**Writing a calculator**
We are familiar to infix expresion: operand, operator, operand
We need prefix expression: operator followed by two operand.
and profix expression: two operand followed by operator.

e.g.:
: 5 * ((9 + 3)*(4 * 2) + 7) is infix expression
    5 9 3 + 4 2 * * 7 + * is the profix expression

Calculating (for binary operand):
```Java
if is operand{
    push operand into stack
}else{
    pop two top element
    apply operator
    push result back into stack
}
```


## Queue
Queue:
: Is a sequence which items and added at the rear and removed from the front.

**Two basic operations:**
**enQueue**
```Java
enqueue(item){
    if(rear < maxsize){
        s[rear] = item
        rear++
    }else{
        no enqeue;
    }
}
```
**deQueue**
```Java
dequeue(){
    if(front < maxsize){
        front++;
    }else{
        no dequeue;
    }
}
```
This can cause a small bug because it will cause overflow in unwanted places.

We can solve this by taking the mod of rear and front. (Ring queue/Circular queue)
But the question now rises is that how do we know that the queue is empty or full?
We reserve an empty space in the ring queue, when (rear + 1) % n = front, we say the queue is full, when front == rear, we say the queue is empty.