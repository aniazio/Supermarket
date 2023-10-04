import javax.swing.*;
import java.util.concurrent.CountDownLatch;

public class Supermarket {

    static SupermarketSwing ob;
    static Sklep sklepik = new Sklep(4,5,5,0.9);
    static boolean przepełnienie = false;
    static final double predkosc = 0.5;


    public static void main(String args[]) {

        sklepik.otwarty = true;
        sklepik.nowiKlienci = true;
        int godzinaOtwarcia = 8;
        int godzinaZamkniecia = 16;
        int godzina = godzinaOtwarcia;
        int minuta = 0;
        CountDownLatch latch = new CountDownLatch(1);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ob = new SupermarketSwing();
                latch.countDown();
            }
        });
        try {
            latch.await();
        } catch (InterruptedException exc) {
            System.out.println("Przerwane oczekiwanie na otwarcie aplikacji.");
        }

        var kk = new KierownikKas();
        Thread mt1 = new Thread(kk);
        mt1.start();

        var gk = new GeneratorKlientow();
        Thread mt2 = new Thread(gk);
        mt2.start();


        try {
            while (godzina < godzinaZamkniecia || sklepik.ileWCałymSklepie!=0) {

                Thread.sleep((long) (1000 * (1/Supermarket.predkosc)));
                minuta += 10;
                if(minuta == 60) {
                    minuta = 0;
                    godzina++;
                }
                Supermarket.ob.updateGodzina(godzina, minuta);

                if(przepełnienie == true && sklepik.ileWCałymSklepie < sklepik.K* sklepik.M) {
                        przepełnienie = false;
                    }

                    if(godzina>=godzinaZamkniecia-1) sklepik.nowiKlienci = false;

                if(sklepik.ileWCałymSklepie >= sklepik.K* sklepik.M) {
                    przepełnienie = true;
                }


            }
        } catch(InterruptedException exc) {
            System.out.println("Przerwany wątek wyświetlania.");
        }
        sklepik.otwarty = false;




    }
}
