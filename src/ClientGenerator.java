import java.util.Random;

public class ClientGenerator implements Runnable {

    int whichClient;
    Client[] inStore;
    Thread[] ClientsThreads;

    ClientGenerator() {
        whichClient = 0;
        inStore = Supermarket.store.inStore;
        ClientsThreads = new Thread[inStore.length];
    }

    @Override
    public void run() {
        try {
            while (Supermarket.store.generateNewClients) generateNewClients();
        } catch(InterruptedException exc) {
            handleException(exc);
        }
    }

    private void generateNewClients() throws InterruptedException {
        if(!Supermarket.storeIsFull) {
            waitForNewClient();
            addClientAndStartHisThread();
            updateVariables();
            Supermarket.ui.updateSwing();
        } else waitForFreeSpaceInStore();
    }

    private void waitForNewClient() throws InterruptedException {
        var r = new Random();
        double waitingTimeForNewClient = r.nextDouble(Supermarket.store.MAX_TIME_FOR_NEW_CLIENT);
        Thread.sleep((long) (waitingTimeForNewClient * 1000 * (1/Supermarket.SPEED)));
    }

    private void addClientAndStartHisThread() {
        int indexOfClientInStore = whichClient % inStore.length;
        inStore[indexOfClientInStore] = new Client("Klient nr " + whichClient);
        ClientsThreads[indexOfClientInStore] = new Thread(inStore[indexOfClientInStore]);
        ClientsThreads[indexOfClientInStore].start();
    }

    private void updateVariables() {
        whichClient++;
        Supermarket.store.clientsInStore++;
        Supermarket.store.clientsInStoreAndAtCheckouts++;
    }

    private void waitForFreeSpaceInStore() {
        try {
            Thread.sleep(300);
        } catch(InterruptedException exc) {
            handleException(exc);
        }
    }

    private void handleException(Exception exc) {
        System.out.println("Wątek generatora klientów został przerwany.");
        System.out.println(exc);
    }
}