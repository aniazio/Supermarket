import java.util.Random;

public class GeneratorKlientow implements Runnable {


    @Override
    public void run() {

        int ktoryKlient = 0;
        int kk;
        var r = new Random();
        Klient[] naSklep = Supermarket.sklepik.naSklepie;
        Thread[] mt = new Thread[naSklep.length];

        try {
            while (Supermarket.sklepik.nowiKlienci) {
                if(!Supermarket.przepełnienie) {

                    double nowy = r.nextDouble(Supermarket.sklepik.MAX_NOWY_KLIENT);

                    Thread.sleep((long) (nowy * 1000 * (1/Supermarket.predkosc)));

                    kk = ktoryKlient % naSklep.length;

                    naSklep[kk] = new Klient("Klient nr " + ktoryKlient);
                    mt[kk] = new Thread(naSklep[kk]);
                    mt[kk].start();

                    ktoryKlient++;
                    Supermarket.sklepik.ileNaSklepie++;
                    Supermarket.sklepik.ileWCałymSklepie++;

                    Supermarket.ob.updateSwing();
                } else {
                    try {
                        Thread.sleep(300);
                    } catch(InterruptedException exc) {
                        System.out.println("Wątek generatora klientów został przerwany.");
                    }
                }

            }
        } catch(InterruptedException exc) {
            System.out.println("Wątek generatora klientów został przerwany.");
        }


    }
}
