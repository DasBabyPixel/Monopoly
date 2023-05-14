public class Player {
    int money;
    int pos;
    int num_trains;
    int num_utilities;

    public Player(int money, int pos, int num_trains, int num_utilities) {
        this.money = money;
        this.pos = pos;
        this.num_trains = num_trains;
        this.num_utilities = num_utilities;
    }

    void makeMove(int ran, int i, Street[] street) {
        move(ran, i, street);
    }

    void move(int ran, int i, Street[] street) {
        System.out.println("Du hast eine " + ran + " gewuerfelt!");
        System.out.println("Die Stats davor:");
        printProperties();
        this.pos = this.pos + ran;
        if (this.pos > 39){
            this.pos = this.pos - 40;
            System.out.println("Spieler ist über Los gegangen und hat 200 erhalten");
            money = money + 200;
        }

        /*if(i== 0) {
            switch (pos) {
                case 0:
                    GUI.player1.setBounds(520, 875, 50, 50); //Bewegt spieler1 nach jedem zug
                    break;
                case 1:
                    GUI.player1.setBounds(520, 790, 50, 50);
                    break;
                case 2:
                    GUI.player1.setBounds(520, 730, 50, 50);
                    break;
                case 3:
                    GUI.player1.setBounds(520, 675, 50, 50);
                    break;
                case 4:
                    GUI.player1.setBounds(520, 610, 50, 50);
                    break;
                case 5:
                    GUI.player1.setBounds(520,550,50,50);
                    break;
                case 6:
                    GUI.player1.setBounds(520,490,50,50);
                    break;
                case 7:
                    GUI.player1.setBounds(520,425,50,50);
                    break;
                case 8:
                    GUI.player1.setBounds(520, 365, 50, 50);
                    break;
                case 9:
                    GUI.player1.setBounds(520, 310, 50, 50);
                    break;
                case 10:
                    GUI.player1.setBounds(500, 200, 50, 50);
                    break;
            }
        }*/
        int[] a = testBoardCoords();
        GUI.player1.setBounds(a[0], a[1],50,50);

        //testBoardCoords();
        switch (street[pos].name) {
            case "Ereignisfeld":
                //Ereigniskarte ziehen
                break;
            case "Gemeinschaftsfeld":
                //Gemeinschaftskarte ziehen
                break;
            case "Frei Parken":
                //Steuergelder einkassieren
                break;
            case "Einkommenssteuer":
                money = money - 200;
                break;
            case "Zusatzsteuer":
                money = money - 100;
                break;

            default:
                if (street[pos].available) {
                    if (street[pos].cost <= money) {
                        GUI.sell.addActionListener(e -> buy(street, pos));
                    }
                    else {
                        //versteigern
                    }
                } else if (street[pos].owner != null) {
                    street[pos].payrent(i);
                }
        }
        GUI.posln.setText("Du stehst auf " + street[pos].name);
        System.out.println("Du stehst auf " + street[pos].name);
        System.out.println("Die Stats danach:");
        printProperties();
        System.out.println();
    }
    public void printProperties() {
        System.out.println(money + " " + pos);
    }
    void buy(Street[] street, int pos){
        street[pos].owner = this;
        this.money = this.money - street[pos].cost;
    }

    public int[] testBoardCoords(){
        final int startx = 500;
        final int starty = 810; //hier startcoords eingeben y
        final int distance = 60; //Entfernung zws Feldern
        final int distanceSquareRectangle = 85; //Entfernung Ecke zu Feld
        int movex = 0; //Änderung der Koordinaten
        int movey = 0;
        if (pos < 10){
            movex = startx;
            movey = starty - distanceSquareRectangle - (pos - 1) * distance;
            return new int[]{movex, movey};
        } else if (pos < 20){
            movex = startx + distanceSquareRectangle + (pos - 11) * distance;
            movey = starty - 2 * distanceSquareRectangle - 8 * distance;
            return new int[]{movex, movey};
        } else if (pos < 30){
            movex = startx + 2 * distanceSquareRectangle + 8 * distance;
            movey = starty - distanceSquareRectangle - (29 - pos) * distance;
            return new int[]{movex, movey};
        } else if (pos < 40) {
            movex = startx + distanceSquareRectangle + (39 - pos) * distance;
            movey = starty;
            return new int[]{movex, movey};
        }
        int[] arr = {movex, movey};
        return arr;
    }
}

