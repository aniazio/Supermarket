import javax.swing.*;
import java.awt.*;


public class SupermarketSwing {

    JLabel inStoreLabel;
    JLabel checkoutsLabels[];
    JLabel time;

    SupermarketSwing() {
        JFrame jfrm = new JFrame("Supermarket");
        jfrm.setLayout(new GridLayout(Supermarket.store.numberOfCheckouts +2, 1));
        jfrm.setSize(400, (Supermarket.store.numberOfCheckouts +2)*50);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setVisible(true);
        jfrm.setLocationRelativeTo(null);

        time = new JLabel("8:00");
        time.setHorizontalAlignment(JLabel.CENTER);
        jfrm.add(time);

        inStoreLabel = new JLabel("Liczba klientów na sklepie: 0.");
        inStoreLabel.setHorizontalAlignment(JLabel.CENTER);
        checkoutsLabels = new JLabel[Supermarket.store.numberOfCheckouts];
        jfrm.add(inStoreLabel);

        for(int i = 0; i< Supermarket.store.numberOfCheckouts; i++) {
            checkoutsLabels[i] = new JLabel("Kasa nr " + (i+1) + ": zamknięta.");
            checkoutsLabels[i].setHorizontalAlignment(JLabel.CENTER);
            jfrm.add(checkoutsLabels[i]);
        }
    }

    void updateSwing() {
        inStoreLabel.setText("Liczba klientów na sklepie: " + Supermarket.store.clientsInStore);
        for(int i = 0; i< Supermarket.store.numberOfCheckouts; i++) {
            setTextOfLabel(i);
        }
    }

    private void setTextOfLabel(int i) {
        String str = "Kasa nr " + (i+1) + ": ";
        if(!(Supermarket.store.checkouts[i].getOpen() ||
                !Supermarket.store.checkouts[i].isEmpty())) str += "zamknięta.";
        else {
            if(Supermarket.store.checkouts[i].getSize() == 1 ) str += "1 klient oczekujący w kolejce.";
            else str += (Supermarket.store.checkouts[i].getSize() + " klientów oczekujących w kolejce.");

            if(!(Supermarket.store.checkouts[i].getOpen())) {
                str = str.substring(0,str.length()-1);
                str += " (zamykana).";
            }
        }
        checkoutsLabels[i].setText(str);
    }

    void updateTimeAndPrintStoreState(int hour, int min) {
        if(min==0) time.setText(hour + ":00");
        else time.setText(hour + ":" + min);

        //Program prints a history of a day to the console for purposes of analysis
        printStoreState(hour, min);
    }

    private void printStoreState(int hour, int min) {
        if(min==0) System.out.println(hour + ":00");
        else System.out.println(hour + ":" + min);

        System.out.println("Liczba klientów na sklepie: " + Supermarket.store.clientsInStore);
        for(int i = 0; i< Supermarket.store.numberOfCheckouts; i++) {
            System.out.print("Kasa nr " + (i+1) + ": ");
            if(!Supermarket.store.checkouts[i].getOpen() &&
                    Supermarket.store.checkouts[i].isEmpty()) System.out.println("zamknięta.");
            else if(Supermarket.store.checkouts[i].getSize() == 1 ) System.out.println("1 klient oczekujący w kolejce.");
            else System.out.println(Supermarket.store.checkouts[i].getSize() + " klientów oczekujących w kolejce.");
        }
    }
}
