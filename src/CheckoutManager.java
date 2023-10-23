public class CheckoutManager implements Runnable {

    Checkout[] checkouts;
    CheckoutService[] service;
    int howManyOpenCheckouts;
    Thread[] checkoutsThreads;

    CheckoutManager() {
        checkouts = Supermarket.store.checkouts;
        service = Supermarket.store.service;
        howManyOpenCheckouts = 2;
        checkoutsThreads = new Thread[service.length];

        for(int i=0; i< service.length; i++) checkoutsThreads[i] = new Thread(service[i]);
    }

    @Override
    public void run() {
        openFirstCheckouts();
        Supermarket.ui.updateSwing();

        while(Supermarket.store.open) {
            openCheckoutsIfNecessary();
            closeCheckoutsIfNecessary();
            waitMoment();
        }
        closeAllCheckouts();
    }

    private void openFirstCheckouts() {
        checkouts[0].setOpen(true);
        checkoutsThreads[0].start();
        checkouts[1].setOpen(true);
        checkoutsThreads[1].start();
    }

    private void openCheckoutsIfNecessary() {
        int inWholeStore = Supermarket.store.clientsInStoreAndAtCheckouts;

        if(inWholeStore > (howManyOpenCheckouts * Supermarket.store.numberOfCientsForChechout) &&
                howManyOpenCheckouts < Supermarket.store.numberOfCheckouts) {
            openChceckout(howManyOpenCheckouts);
            startThreadIfNecessary(howManyOpenCheckouts);
            howManyOpenCheckouts++;
        }
    }

    private void openChceckout(int number) {
        System.out.println("Kasa nr " + (number + 1) + " zostaje otwarta.");
        checkouts[number].setOpen(true);
        Supermarket.ui.updateSwing();
    }

    private void startThreadIfNecessary(int number) {
        boolean isStillOpen = !checkouts[number].isEmpty();
        if (!isStillOpen) {
            checkoutsThreads[number] = new Thread(service[number]);
            checkoutsThreads[number].start();
        }
    }

    private void closeCheckoutsIfNecessary() {
        int inWholeStore = Supermarket.store.clientsInStoreAndAtCheckouts;

        if(inWholeStore<((howManyOpenCheckouts-1)* Supermarket.store.numberOfCientsForChechout) &&
                howManyOpenCheckouts>2) {
            howManyOpenCheckouts--;
            closeCheckout(howManyOpenCheckouts);
        }
    }

    private void closeCheckout(int number) {
        System.out.println("Kasa nr " + (number+1) + " zostanie zamknięta.");
        checkouts[number].setOpen(false);
        Supermarket.ui.updateSwing();
    }

    private void waitMoment() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException exc) {
            System.out.println("Wątek kierownika sklepu został przerwany. ");
        }
    }

    private void closeAllCheckouts() {
        for(int i=0; i< service.length; i++) checkouts[i].setOpen(false);
    }
}