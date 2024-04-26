public class MyQueue <T>{
    private MyLinkedList<T> list = new MyLinkedList<>();

    public boolean isEmpty(){
        return list.size() != 0;
    }

    public int size(){
        return list.size();
    }

    public T peek(){
        return list.getFirst();
    }

    public void enqueue(T item){
        list.addLast(item);
    }

    public void dequeue(){
        list.removeFirst();
    }
}
