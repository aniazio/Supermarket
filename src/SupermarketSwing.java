import javax.swing.*;
import java.awt.*;


public class SupermarketSwing {

    JLabel naSklepie;
    JLabel labKasy[];
    JLabel godzina;

    SupermarketSwing() {

        JFrame jfrm = new JFrame("Supermarket");
        jfrm.setLayout(new GridLayout(Supermarket.sklepik.M+2, 1));
        jfrm.setSize(400, (Supermarket.sklepik.M+2)*50);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setVisible(true);


        godzina = new JLabel("8:00");
        godzina.setHorizontalAlignment(JLabel.CENTER);
        jfrm.add(godzina);


        naSklepie = new JLabel("Liczba klientów na sklepie: 0.");
        naSklepie.setHorizontalAlignment(JLabel.CENTER);
        labKasy = new JLabel[Supermarket.sklepik.M];
        jfrm.add(naSklepie);

        for(int i = 0; i< Supermarket.sklepik.M; i++) {
            labKasy[i] = new JLabel("Kasa nr " + (i+1) + ": zamknięta.");
            labKasy[i].setHorizontalAlignment(JLabel.CENTER);
            jfrm.add(labKasy[i]);
        }

    }


    void updateSwing() {

        naSklepie.setText("Liczba klientów na sklepie: " + Supermarket.sklepik.ileNaSklepie);
        for(int i = 0; i< Supermarket.sklepik.M; i++) {
            String str = "Kasa nr " + (i+1) + ": ";
            if(!(Supermarket.sklepik.kasy[i].getOtwarta() ||
            !Supermarket.sklepik.kasy[i].isEmpty())) str += "zamknięta.";
            else {
                if(Supermarket.sklepik.kasy[i].getSize() == 1 ) str += "1 klient oczekujący w kolejce.";
                else str += (Supermarket.sklepik.kasy[i].getSize() + " klientów oczekujących w kolejce.");

                if(!(Supermarket.sklepik.kasy[i].getOtwarta())) {
                    str = str.substring(0,str.length()-1);
                    str += " (zamykana).";
                }
            }
            labKasy[i].setText(str);
        }

    }

    void updateGodzina(int h, int mm) {



        if(mm==0) godzina.setText(h + ":00");
        else godzina.setText(h + ":" + mm);



        //wyświetlanie na kosnoli, do celów analizy całego dnia

        if(mm==0) System.out.println(h + ":00");
        else System.out.println(h + ":" + mm);


        System.out.println("Liczba klientów na sklepie: " + Supermarket.sklepik.ileNaSklepie);
        for(int i = 0; i< Supermarket.sklepik.M; i++) {
            System.out.print("Kasa nr " + (i+1) + ": ");
            if(!Supermarket.sklepik.kasy[i].getOtwarta() &&
                    Supermarket.sklepik.kasy[i].isEmpty()) System.out.println("zamknięta.");
            else if(Supermarket.sklepik.kasy[i].getSize() == 1 ) System.out.println("1 klient oczekujący w kolejce.");
            else System.out.println(Supermarket.sklepik.kasy[i].getSize() + " klientów oczekujących w kolejce.");
        }



    }


}
