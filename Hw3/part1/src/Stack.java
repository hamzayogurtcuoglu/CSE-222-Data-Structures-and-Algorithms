import java.util.*;

public class Stack<T>{
    private Node top;

    // Im created linklist style in order to make STACK

    public T peek(){
        if (empty()) throw new NoSuchElementException("NO SUCH A ELEMENT");
        return top.value;
    }

    public T pop() {
        T value=null;
        if(!empty()){
            value=top.value;
            top=top.next;

        }
        return value;
    }

    public boolean empty()
    {
        return top == null;
    }

    public void push(T item){
        Node current=new Node(item);
        if(empty())
            top=current;
        else{
            current.next=top;
            top=current;
        }
    }
    public class Node {
        T value;
        Node next;

        public Node(T value){
            this.value=value;
            next=null;
        }
    }
}