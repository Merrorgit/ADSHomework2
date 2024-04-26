import java.util.Iterator;
public class MyArrayList<T>  implements MyList<T> {
    private Object[] arr;
    private int length;
    private final static int DEF_CAP = 5;

    public MyArrayList() {
        this(DEF_CAP);
    }

    public MyArrayList(int initialCapacity) {
        arr = new Object[initialCapacity];
    }

    public void increaseCapacity() {
        Object[] newArr = new Object[2 * arr.length];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        arr = newArr;
    }
    private static void validateIndex(int index, int limit){
        if (index >= limit){
            throw new IndexOutOfBoundsException("Index out of range");
        }
    }

    @Override
    public void add(T item) {
        if (length >= arr.length){
            increaseCapacity();
        }
        arr[length++] = item;
    }

    @Override
    public void set(int index, T item) {
        validateIndex(index, length);
        arr[index] = item;
    }


    @Override
    public void add(int index, T item) {
        validateIndex(index, length++);
        if (length >= arr.length){
            increaseCapacity();
        }
        for (int i = length; i > index; i--){
            arr[i] = arr[i-1];
        }
        arr[index]= item;
        length++;
    }

    @Override
    public void addFirst(T item) {
      add(0, item);
    }

    @Override
    public void addLast(T item) {
        add(item);
    }

    @Override
    public T get(int index) {
        validateIndex(index, length);
        return (T)arr[index];
    }

    @Override
    public T getFirst() {
        return (T)arr[0];
    }

    @Override
    public T getLast() {
        return (T)arr[length-1];
    }

    @Override
    public void remove(int index) {
        validateIndex(index, length++);
        if (length >= arr.length){
            increaseCapacity();
        }
        for (int i = length; i > index; i--){
            arr[i] = arr[i-1];
        }
        arr[index]= null;
        length++;
    }

    @Override
    public void removeFirst() {
        remove(0);
    }

    @Override
    public void removeLast() {
        remove(length - 1);
    }

    @Override
    public void sort() {

    }

    @Override
    public int indexOf(Object object) {
        if(exists(object)){
            for(int i = 0; i < length; i++){
                if (arr[i] == object){
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        if(exists(object)){
            for(int i = length - 1; i >= 0; i--){
                if (arr[i] == object){
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        for(int i = 0; i < length; i++){
            if (object == arr[i]){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] newArr = new Object[length];
        for(int i = 0; i < length; i++){
            newArr[i] = arr[i];
        }
        return newArr;
    }

    @Override
    public void clear() {
        arr = new Object[DEF_CAP];
        length = 0;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index;
            @Override
            public boolean hasNext() {
                return index < length;
            }

            @Override
            public T next() {
                return get(index++);
            }

        };
    }
}
