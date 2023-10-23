public class Store {

    final int numberOfCheckouts;
    final int numberOfCientsForChechout;

    final double MAX_TIME_IN_STORE;
    final double MAX_TIME_FOR_NEW_CLIENT;

    Checkout[] checkouts;
    Client[] inStore;
    CheckoutService[] service;

    int clientsInStore = 0;
    int clientsInStoreAndAtCheckouts = 0;

    boolean open;
    boolean generateNewClients;

    Store(int noOfCheckouts, int noOfClientsForCheckout, double timeInStore, double timeForNewClient) {
        numberOfCheckouts = noOfCheckouts;
        numberOfCientsForChechout = noOfClientsForCheckout;

        MAX_TIME_IN_STORE = timeInStore;
        MAX_TIME_FOR_NEW_CLIENT = timeForNewClient;

        checkouts = new Checkout[numberOfCheckouts];
        inStore = new Client[numberOfCheckouts * numberOfCientsForChechout];
        service = new CheckoutService[numberOfCheckouts];

        for(int i = 0; i < numberOfCheckouts; i++) {
            checkouts[i] = new Checkout(numberOfCientsForChechout);
            service[i] = new CheckoutService("Kasa nr " + (i+1), i);
        }
    }

    Store() {
        this(4,6,6,2);
    }

    Store(int m, int k) {
        this(m,k,6,2);
    }
}
