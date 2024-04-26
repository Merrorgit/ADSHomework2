public class MyStack<T> {
    private MyLinkedList<T> list = new MyLinkedList<>();

    public boolean isEmpty(){
        return list.size() != 0;
    }

    public int size(){
        return list.size();
    }

    public T peek(){
        if(list.size() == 0){
            throw new IndexOutOfBoundsException();
        }
        return list.getLast();
    }

    public void push(T item){
        list.addLast(item);
    }

    public void pop(){
        if(list.size() == 0){
            throw new NegativeArraySizeException();
        }
        list.removeLast();
    }
}
