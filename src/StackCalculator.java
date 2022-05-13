import java.util.*;
import java.util.function.Consumer;

public class StackCalculator<T> implements Iterable<T>, Iterator<T> {
    private Node stackElement = null;
    private int size = 0;
    private Node iteration;

    private class Node<T>{
        T data;
        Node last;

        public Node(T data, Node last){
            this.data = data;
            this.last = last;
        }
        public Node getLast(){
            return last;
        }

        public T getData(){
            return data;
        }
    }
    public StackCalculator(){

    }

    public Iterator<T> iterator() {
        return this;
    }

    public T next() {
        if (iteration == null)
            throw new NoSuchElementException();

        T lastIterable = (T) iteration.getData();
        iteration = iteration.getLast();
        return lastIterable;
    }

    public boolean hasNext() {
        if (iteration != null){
            return true;
        }
        return false;
    }

    public void push(T element){
        if(!element.equals(null)){
            Node oldElement = stackElement;
            stackElement = new Node<>(element, stackElement);
        } else {
            stackElement = new Node<>(element, null);
        }
        iteration = stackElement;
        size++;
    }

    public T pop(){
        size--;
        if(size > 1){
            Node forReturn = stackElement;
            stackElement = stackElement.getLast();
            return (T) forReturn.getData();
        } else {
            iteration = null;
            return null;
        }
    }

    public T peek(){
        return (T) stackElement.getData();
    }

    public int getSize(){
        return size;
    }

}


