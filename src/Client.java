import java.util.Random;

public class Client implements Runnable {

    double timeAtCheckout;
    String name;

    Client(String n) {
        name = n;
    }

    @Override
    public void run() {
        var r = new Random();
        double timeInStore = r.nextDouble(Supermarket.store.MAX_TIME_IN_STORE);
        timeAtCheckout = r.nextDouble(0.3, 0.4)*timeInStore;

        try {
            Thread.sleep((long) (timeInStore * 1000 * (1/Supermarket.SPEED)));
            goToCheckout();
        } catch(InterruptedException exc) {
            handleException(exc);
        }
        Supermarket.store.clientsInStore--;
    }

    private void handleException(Exception exc) {
        System.out.println("Przerwano wątek klienta " + this.name);
        System.out.println(exc);
    }

    synchronized public void goToCheckout() {
        int bestCheckout = 0;
        for(int i = 0; i< Supermarket.store.numberOfCheckouts; i++) {
            if(Supermarket.store.checkouts[i].getOpen()) {
                if(Supermarket.store.checkouts[i].getSize() < Supermarket.store.checkouts[bestCheckout].getSize())
                    bestCheckout = i;
            }
        }

        try {
            Supermarket.store.checkouts[bestCheckout].add(this);
            Supermarket.ui.updateSwing();
        } catch(QueueIsFullException exc) {
            System.out.println("Wszystkie otwarte kasy są pełne.");
        }
    }
}
