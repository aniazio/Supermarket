public class ObslugaKasy implements Runnable {

    String nazwaWatku;
    int numerKasy;


    ObslugaKasy(String nazwa, int i) {
        nazwaWatku = nazwa;
        numerKasy = i;
    }

    @Override
    public void run() {

        Klient k;


        Kasa naszaKasa = Supermarket.sklepik.kasy[numerKasy];


        while (naszaKasa.getOtwarta() || !naszaKasa.isEmpty()) {
            try {
                try {
                    obsluga();
                } catch (QueueIsEmptyException exc) {
                    while (naszaKasa.isEmpty() && naszaKasa.getOtwarta()) Thread.sleep(300);
                }
            } catch(InterruptedException exc) {
                System.out.println("Przerwany wątek obsługi kasy.");
            }

        }
    }


    synchronized void obsluga()
    throws QueueIsEmptyException{

        Klient obslugiwany;
        Kasa naszaKasa = Supermarket.sklepik.kasy[numerKasy];

        try {
                obslugiwany = naszaKasa.show();
                Thread.sleep((long) (obslugiwany.czasPrzyKasie * 1000 * (1/Supermarket.predkosc)));
                obslugiwany = naszaKasa.remove();
                Supermarket.sklepik.ileWCałymSklepie--;

                Supermarket.ob.updateSwing();

            } catch (InterruptedException exc) {
            System.out.println("Wątek kasy " + numerKasy + " został przerwany.");
        }
    }

}
