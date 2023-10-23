public class Checkout {

    private Client[] queue;
    private int head = 0;
    private int tail = 0;
    private int maxSize;
    private int size = 0;
    private boolean open =false;


    Checkout(int k) {
        maxSize = k;
        queue = new Client[k];
    }

    boolean isEmpty() {
        return head == tail;
    }

    boolean isFull() {
        return ((tail+1) % maxSize) == head;
    }

    synchronized void add(Client k)
            throws QueueIsFullException {
        if(isFull()) throw new QueueIsFullException();
        queue[tail] = k;
        tail = (tail+1) % maxSize;
        size++;
    }

    Client get()
            throws QueueIsEmptyException {
        if(isEmpty()) throw new QueueIsEmptyException();
        Client result = queue[head];
        return result;
    }

    Client remove()
            throws QueueIsEmptyException {
        if(isEmpty()) throw new QueueIsEmptyException();
        Client result = queue[head];
        head = (head+1)% maxSize;
        size--;
        return result;
    }

    int getSize() {
        return size;
    }

    boolean getOpen() {
        return open;
    }

    void setOpen(boolean b) {
        open = b;
    }
}
