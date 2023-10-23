public class CheckoutService implements Runnable {

    String threadName;
    int checkoutNumber;

    CheckoutService(String name, int number) {
        threadName = name;
        checkoutNumber = number;
    }

    @Override
    public void run() {
        Checkout thisCheckout = Supermarket.store.checkouts[checkoutNumber];

        while (thisCheckout.getOpen() || !thisCheckout.isEmpty()) {
            try {
                try {
                    service();
                } catch (QueueIsEmptyException exc) {
                    while (thisCheckout.isEmpty() && thisCheckout.getOpen()) Thread.sleep(300);
                }
            } catch(InterruptedException exc) {
                handleException(exc);
            }
        }
    }

    synchronized void service()
            throws QueueIsEmptyException {
        Client servicedClient;
        Checkout thisCheckout = Supermarket.store.checkouts[checkoutNumber];
        try {
            servicedClient = thisCheckout.get();
            Thread.sleep((long) (servicedClient.timeAtCheckout * 1000 * (1/Supermarket.SPEED)));
            thisCheckout.remove();
            Supermarket.store.clientsInStoreAndAtCheckouts--;
            Supermarket.ui.updateSwing();
        } catch (InterruptedException exc) {
            handleException(exc);
        }
    }

    private void handleException(Exception exc) {
        System.out.println("Wątek kasy " + checkoutNumber + " został przerwany.");
        System.out.println(exc);
    }
}
