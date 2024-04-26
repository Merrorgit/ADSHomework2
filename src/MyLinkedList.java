import java.util.Iterator;

public class MyLinkedList <T> implements MyList<T> {
    public void validateIndex(int index, int limit){
        if (index >= limit){
            throw new IndexOutOfBoundsException("Index out of range!");
        }
    }
    private static class MyNode<T>{
        T data;
        MyNode<T> next;
        MyNode<T> prev;
        MyNode(T newData){
            this.data = newData;
        }
    }
    MyNode<T> head, tail;
    int length = 0;

    @Override
    public void add(T item) {
        MyNode<T> newNode = new MyNode<>(item);
        if(head == null){
            head = tail = newNode;
        }
        else{
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        length++;

    }

    @Override
    public void set(int index, T item) {
        validateIndex(index, length - 1);
        MyNode<T> currentNode = head;
        for(int i = 0; i < index; i++){
            currentNode = currentNode.next;
        }
        currentNode.data = item;

    }

    @Override
    public void add(int index, T item) {
        validateIndex(index, length);
        MyNode<T> newNode = new MyNode<>(item);
        MyNode<T> currentNode = head;
        if(index == 0){
            addFirst(item);
            return;
        }
        else if (index == length){
            addLast(item);
            return;
        }
        else{
            for(int i = 0; i < index; i++){
                currentNode = currentNode.next;
            }
            currentNode.prev.next = newNode;
            newNode.prev = currentNode.prev;
            currentNode.prev = newNode;
            newNode.next = currentNode;
            length++;
        }

    }

    @Override
    public void addFirst(T item) {
        MyNode<T> newNode = new MyNode<>(item);
        head.prev = newNode;
        newNode.next = head;
        head = newNode;
        length++;
    }

    @Override
    public void addLast(T item) {
        add(item);
        length++;
    }

    @Override
    public T get(int index) {
        validateIndex(index, length - 1);
        MyNode<T> currentNode;
        if(index <= length / 2){
            currentNode = head;
            for(int i = 0; i < index; i++){
                currentNode = currentNode.next;
            }
        }
        else{
            currentNode = tail;
            for(int i = length - 1; i > index; i--){
                currentNode = currentNode.prev;
            }
        }
        return currentNode.data;
    }

    @Override
    public T getFirst() {
        return head.data;
    }

    @Override
    public T getLast() {
        return tail.data;
    }

    @Override
    public void remove(int Index) {
        validateIndex(Index, length - 1);
        if(Index == 0){
            removeFirst();
        }
        else if(Index == length - 1){
            removeLast();
        }
        else{
            MyNode<T> currentNode = head;
            validateIndex(Index, length - 1);
            for(int i = 0; i < Index - 1; i++){
                currentNode = currentNode.next;
            }
            currentNode.next = currentNode.next.next;
            currentNode.next.prev = currentNode;
        }
        length--;
    }

    @Override
    public void removeFirst() {
        if(head == null){
            throw new NegativeArraySizeException();
        }
        else if(head.next != null){
            head = head.next;
            head.prev = null;
        }
        else{
            head = tail;
            head.prev = null;
        }
        length--;

    }

    @Override
    public void removeLast() {
        if(head == null){
            throw new NegativeArraySizeException();
        }
        else if(tail.prev == null && tail != head){
            head = tail;
        }
        else if(tail == head){
            head = tail = null;
        }
        else{
            tail = tail.prev;
            tail.next = null;
        }
        length--;

    }

    @Override
    public void sort() {
        if (head == null || head.next == null) {
            return;
        }

        for (int i = 0; i < length - 1; i++) {
            MyNode<T> current = head;
            for (int j = 0; j < length - 1; j++) {
                Comparable<T> currentComparable = (Comparable<T>) current.data;
                if (currentComparable.compareTo(current.next.data) > 0) {
                    T temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                }
                current = current.next;
            }
        }
    }

    @Override
    public int indexOf(Object object) {
        if(exists(object)){
            MyNode<T> currentNode = head;
            for(int i = 0; i < length; i++){
                if (currentNode.data == object){
                    return i;
                }
                currentNode = currentNode.next;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        if(exists(object)){
            MyNode<T> currentNode = tail;
            for(int i = length - 1; i >= 0; i--){
                if (currentNode.data == object){
                    return i;
                }
                currentNode = currentNode.prev;
            }
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        MyNode<T> currentNode = head;
        while(currentNode != null){
            if (currentNode.data == object){
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[length];
        MyNode<T> currentNode = head;
        for (int i = 0; i < length; i++){
            arr[i] = currentNode;
            currentNode = currentNode.next;
        }
        return arr;
    }

    @Override
    public void clear() {
        head = tail = null;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

        private class MyIterator implements Iterator<T>{
            MyNode<T> current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        }
    }
