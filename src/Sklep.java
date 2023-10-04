public class Sklep {

    final int M;        //liczba kas
    final int K;        //ile klientów max. odpowiada za jedną kasę


    final double MAX_W_SKLEPIE;    //ile sekund maksymalnie klient spędza w sklepie
    final double MAX_NOWY_KLIENT;  //ile sekund maksymalnie czeka się na nowego klienta

    Kasa[] kasy;
    Klient[] naSklepie;
    ObslugaKasy[] obsluga;

    int ileNaSklepie = 0;
    int ileWCałymSklepie = 0;

    boolean otwarty;
    boolean nowiKlienci;

    Sklep(int m, int k, double wsklepie, double nowy) {
        M = m;
        K = k;

        MAX_W_SKLEPIE = wsklepie;
        MAX_NOWY_KLIENT = nowy;

        kasy = new Kasa[M];
        naSklepie = new Klient[M*K];
        obsluga = new ObslugaKasy[M];

        for(int i=0; i<M; i++) {kasy[i] = new Kasa(K); obsluga[i] = new ObslugaKasy("Kasa nr " + (i+1), i);}


    }

    Sklep() {
        this(4,6,6,2);
    }

    Sklep(int m, int k) {
        this(m,k,6,2);
    }

}
