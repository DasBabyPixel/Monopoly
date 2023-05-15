import java.util.Random;
public class Kartenstapel {
    public static Ereigniskarten[] ereigniskarten;
    private int ran;
    public Kartenstapel(){
        ereigniskarten = new Ereigniskarten[10];
        ereigniskarten[0] = new Ereigniskarten("Kreuzworträtselwettbewerb","Du hast in einem Kreuzworträtselwettbewerb gewonnen. Ziehe DM 100 ein",100,0,false,0,false);
        ereigniskarten[1] = new Ereigniskarten("Miete und Anleihezinsen","Miete und Anleihezinsen werden fällig. Die Bank zahlt Dir DM 150",150,0,false,0,false);
        ereigniskarten[2] = new Ereigniskarten("Bankzahlung","Die Bank zahlt dir eine Dividende von DM 50",50,0,false,0,false);
        ereigniskarten[3] = new Ereigniskarten("Seestraße","Rücke vor bis zur Seestraße. Wenn du über Los kommst, ziehe DM 200 ein",0,0,true,11,false);
        ereigniskarten[4] = new Ereigniskarten("Los","Rücke bis auf Los vor",0,0,true,0,false);
        ereigniskarten[5] = new Ereigniskarten("Schlossallee","Rücke vor bis zu Schlossallee",0,0,true,39,false);
        ereigniskarten[6] = new Ereigniskarten("Opernplatz","Rücke vor bis zum Opernplatz. Wenn Du über Los kommst, ziehe DM 200 ein",0,0,true,24,false);
        ereigniskarten[7] = new Ereigniskarten("Betrunken im Dienst","Betrunken im Dienst. Strafe DM 200",-200,200,false,0,false);
        ereigniskarten[8] = new Ereigniskarten("Strafzahlung","Strafe für zu schnelles Fahren DM 150",-150,150,false,0,false);
        ereigniskarten[9] = new Ereigniskarten("Gefängnis","Gehe in das Gefängnis. Begib Dich direkt dorthin. Gehe nicht über Los. Ziehe nicht DM 200 ein",0,0,false,0,true);
    }
    /*public Ereigniskarten karte_ziehen{
        ran = rand.nextInt(9);
        return ereigniskarten[ran];
    }*/
}
