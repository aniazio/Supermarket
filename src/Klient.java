import java.util.Random;

public class Klient implements Runnable {

    double czasPrzyKasie;
    String nazwa;

    Klient(String n) {
        nazwa = n;
    }

    @Override
    public void run() {

        var r = new Random();
        double wSklepie = r.nextDouble(Supermarket.sklepik.MAX_W_SKLEPIE);
        czasPrzyKasie = r.nextDouble(0.5, 0.7)*wSklepie;


        try {
            Thread.sleep((long) (wSklepie * 1000 * (1/Supermarket.predkosc)));
            doKasy();
        } catch(InterruptedException exc) {
            System.out.println("Przerwano wątek klienta " + this.toString());
        }

        Supermarket.sklepik.ileNaSklepie--;

    }

    synchronized public void doKasy() {

        int k = 0;

        for(int i = 0; i< Supermarket.sklepik.M; i++) {
            if(Supermarket.sklepik.kasy[i].getOtwarta()) {
                if(Supermarket.sklepik.kasy[i].getSize() < Supermarket.sklepik.kasy[k].getSize()) k = i;
            }
        }

        try {
            Supermarket.sklepik.kasy[k].add(this);

            Supermarket.ob.updateSwing();

        } catch(QueueIsFullException exc) {
            System.out.println("Wszystkie otwarte kasy są pełne.");
        }

    }
}
