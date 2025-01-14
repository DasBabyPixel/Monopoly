import javax.swing.*;

public class Player {
    int money;
    int pos;
    int num_trains;
    int num_utilities;
    int cooldown;// in Klasse Main als cooldown oder so denn benutzen

    public Player(int money, int pos, int num_trains, int num_utilities, int cooldown) {
        this.money = money;
        this.pos = pos;
        this.num_trains = num_trains;
        this.num_utilities = num_utilities;
        this.cooldown = cooldown;
    }

    public void makeMove ( int ran, int i, Street[] street){
        GUI.playerstats.append("Geld:" + money + "\n");
        this.pos += ran;

        passedLOS();//sind jz Methoden damit es aufgeräumter ist
        IconMove(i);

        GUI.playerstats.append("Du stehst auf " + street[pos].name + "\n");

        switch (street[pos].name) {
            case "Ereignisfeld":
                //Ereigniskarte ziehen
                Ereigniskarten karte = Kartenstapel.karte_ziehen();
                System.out.println("Ereigniskarte: " + karte.name);
                System.out.println(karte.textausgabe);
                this.money = this.money + karte.geldzahlung;
                if (karte.vorruecken) {
                    if (this.pos > karte.moveToPos) {
                        System.out.println("Du bekommst DM 200, weil du ueber Los gegangen bist");
                        this.money = this.money + 200;
                    }
                    this.pos = karte.moveToPos;
                }
                if (karte.gehe_ins_gefaengnis) {
                    gehe_ins_gefaengnis(i);
                }
                break;
            case "Gemeinschaftsfeld":
                //Gemeinschaftskarte ziehen
                break;
            case "Frei_Parken":
                this.money += Board.Moneypool;
                Board.Moneypool = 0;
                //Steuergelder einkassieren
                break;
            case "Einkommenssteuer":
                this.money -= 200;
                Board.Moneypool += 200;//jz funktioniert Frei Parken auch
                break;
            case "Zusatzsteuer":
                money -= 100;
                Board.Moneypool += 100;
                break;
            case "Geh ins Gefaengnis":
                gehe_ins_gefaengnis(i);

            default:
                if (street[pos].owner == null) {
                    if (street[pos].cost <= money) {
                        int decision = JOptionPane.showConfirmDialog(GUI.frame, "Willst du's kaufen? Kosten:" + street[pos].cost, "Grundstueck kaufen", JOptionPane.YES_NO_OPTION);
                        if (decision == 0) {
                            buy(street, pos);
                        }
                    }
                } else {
                    if (street[pos].name.equals("Bahnhof")) {
                        double tsrent = 25 * Math.pow(2, street[pos].owner.num_trains - 1);
                        int tsRent = (int) tsrent;
                        GUI.playerstats.append("Du kannst des nd kaufen, musst " + tsRent + " Miete zahlen\n");
                        Main.players.get(i).money -= tsRent;
                        street[pos].owner.money += tsRent;
                        break;
                    } else if (street[pos].name.equals("Elektrizitaetswerk") | street[pos].name.equals("Wasserwerk")) {
                        int utilityrent = ran * (street[pos].owner.num_utilities * 4);
                        GUI.playerstats.append("Du kannst des nd kaufen, musst " + utilityrent + " Miete zahlen\n");
                        Main.players.get(i).money = Main.players.get(i).money - utilityrent;
                        street[pos].owner.money = street[pos].owner.money + utilityrent;
                        break;
                    }
                    street[pos].payrent(i);
                }
        }
            /*if(cooldown > 0){
                cooldown--;
            }*/
        printMoney();
        streets_ausgeben();
    }

    public void printMoney () {
        GUI.playerstats.append("Jetzt hast du " + money + " Geld\n");
    }
    void buy (Street[]street,int pos){
        street[pos].owner = this;
        if (street[pos].name.equals("Bahnhof")) {
            this.num_trains = this.num_trains + 1;
        }
        if (street[pos].name.equals("Elektrizitaetswerk") || street[pos].name.equals("Wasserwerk")) {
            this.num_utilities = this.num_utilities + 1;
        }
        this.money = this.money - street[pos].cost;
    }

    public void streets_ausgeben () {
        GUI.leftStats.setText(null);
        GUI.leftStats.append("Der Spieler " + (Main.playerturn + 1) + " hat\n");
        for (int j = 0; j < 40; j++) {
            if (Board.street[j].owner == this) {
                GUI.leftStats.append(Board.street[j].name + "\n");
            }
        }
    }

    public int[] BoardCoords ( int playernum){
        int x = 538;
        int y = 893; //hier startcoords eingeben
        final int distance = 67; //Entfernung zws Feldern
        final int distanceSquareRectangle = 88; //Entfernung Ecke zu Feld
        if (pos < 10) {
            x = x - (playernum * 12);
            y = y - distanceSquareRectangle - (pos - 1) * distance;
            return new int[]{x, y};
        } else if (pos < 20) {
            x = 525 + distanceSquareRectangle + ((pos - 11) * distance);
            y = y - 2 * distanceSquareRectangle - 8 * distance - (playernum * 12);
            return new int[]{x, y};
        } else if (pos < 30) {
            x = x + 2 * distanceSquareRectangle + 8 * distance - (playernum * 12);
            y = y - distanceSquareRectangle - (29 - pos) * distance;
            return new int[]{x, y};
        } else if (pos < 40) {
            x = 525 + distanceSquareRectangle + (39 - pos) * distance;
            y = y + (playernum * 12);
            return new int[]{x, y};
        }
        return new int[]{0, 0};
    }

    public void passedLOS () {
        if (this.pos > 39) {
            this.pos = this.pos - 40;
            System.out.println("Spieler ist über Los gegangen und hat 200 erhalten");
            money = money + 200;
        }
    }
    public void IconMove ( int i){
        int[] a = BoardCoords(i);
        switch (i) {
            case 0 -> GUI.player1.setBounds(a[0], a[1], 50, 50);
            case 1 -> GUI.player2.setBounds(a[0], a[1], 50, 50);
            case 2 -> GUI.player3.setBounds(a[0], a[1], 50, 50);
            case 3 -> GUI.player4.setBounds(a[0], a[1], 50, 50);
        }
    }
    public void gehe_ins_gefaengnis(int i) {
        switch (i) {
            case 0 -> {
                GUI.player1.setBounds(535, 195, 50, 50);
                this.pos = 10;
                cooldown = 3;
            }
            case 1 -> {
                GUI.player2.setBounds(535, 195, 50, 50);
                this.pos = 10;
                cooldown = 3;
            }
            case 2 -> {
                GUI.player3.setBounds(535, 195, 50, 50);
                this.pos = 10;
                cooldown = 3;
            }
            case 3 -> {
                GUI.player4.setBounds(535, 195, 50, 50);
                this.pos = 10;
                cooldown = 3;
            }
        }
    }
}


