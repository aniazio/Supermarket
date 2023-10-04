public class KierownikKas implements Runnable {

    @Override
    public void run() {

        Kasa[] kas = Supermarket.sklepik.kasy;
        ObslugaKasy[] obKa = Supermarket.sklepik.obsluga;
        int ileOtwartych = 2;
        Thread[] mt = new Thread[obKa.length];

        for(int i=0; i< obKa.length; i++) mt[i] = new Thread(obKa[i]);

        kas[0].setOtwarta(true);
        mt[0].start();
        kas[1].setOtwarta(true);
        mt[1].start();

        Supermarket.ob.updateSwing();


        while(Supermarket.sklepik.otwarty) {

            int wSklepie = Supermarket.sklepik.ileWCałymSklepie;

            if(wSklepie>(ileOtwartych* Supermarket.sklepik.K) && ileOtwartych< Supermarket.sklepik.M) {
                System.out.println("Kasa nr " + (ileOtwartych + 1) + " zostaje otwarta.");

                Supermarket.ob.updateSwing();

                    boolean czyJeszczeOtwarta = !kas[ileOtwartych].isEmpty();
                    kas[ileOtwartych].setOtwarta(true);
                    if (!czyJeszczeOtwarta) {
                        mt[ileOtwartych] = new Thread(obKa[ileOtwartych]);
                        mt[ileOtwartych].start();
                    }
                    ileOtwartych++;

            }

            if(wSklepie<((ileOtwartych-1)* Supermarket.sklepik.K) && ileOtwartych>2) {
                System.out.println("Kasa nr " + ileOtwartych + " zostanie zamknięta.");
                kas[ileOtwartych-1].setOtwarta(false);
                ileOtwartych--;
                Supermarket.ob.updateSwing();
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException exc) {
                System.out.println("Wątek kierownika sklepu został przerwany. ");
            }


        }
        for(int i=0; i< obKa.length; i++) kas[i].setOtwarta(false);


    }
}
