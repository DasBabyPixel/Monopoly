public class Street{
    final public String name;
    final int cost;
    int rent;
    Player owner;
    final public String colour;
    //int num_houses;

    public Street(String name, int cost, int rent, Player owner, String colour) {
        this.name = name;
        this.cost = cost;
        this.rent = rent;
        this.owner = owner;
        this.colour = colour;
    }

    public int getRent() {
        return rent;
    }

    void payrent(int i){
        GUI.playerstats.append("Du kannst des nd kaufen, musst " + this.getRent() + " Miete zahlen\n");
        Main.players.get(i).money = Main.players.get(i).money - this.getRent();
        owner.money = owner.money + this.getRent();
    }

    public int UtilityRent(){
        if(Board.street[Main.playerturn].owner != null){
            if(Board.street[Main.playerturn].owner.num_utilities == 1){

            }
        }
        return 0;
    }
}
