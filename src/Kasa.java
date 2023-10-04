public class Kasa {

    private int k;      //k odpowiada K w sklepiku

    private Klient[] kolejka;
    private int head = 0;
    private int tail = 0;
    private int maxSize;
    private int size = 0;
    private boolean otwarta=false;


    Kasa(int kk) {
        k=kk+1;
        maxSize = k;
        kolejka = new Klient[k];
    }

    boolean isEmpty() {
        return head == tail;
    }

    boolean isFull() {
        return ((tail+1) % maxSize) == head;
    }

    synchronized void add(Klient k)
    throws QueueIsFullException {
        if(isFull()) throw new QueueIsFullException();
        kolejka[tail] = k;
        tail = (tail+1) % maxSize;
        size++;
    }

    Klient show()
            throws QueueIsEmptyException {
        if(isEmpty()) throw new QueueIsEmptyException();
        Klient k = kolejka[head];
        return k;
    }

    Klient remove()
            throws QueueIsEmptyException {
        if(isEmpty()) throw new QueueIsEmptyException();
        Klient k = kolejka[head];
        head = (head+1)% maxSize;
        size--;
        return k;
    }

    int getSize() {
        return size;
    }

    boolean getOtwarta() {
        return otwarta;
    }

    void setOtwarta(boolean b) {
        otwarta = b;
    }

}
