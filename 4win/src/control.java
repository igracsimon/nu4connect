public class control {

    private grid uGrid;
    private int playerAtTurn;
    private Player[] players;
    private int amtPlayers;
    private boolean gameEnd;
    private int winP;
    //TODO: change visibility of methods from public to private if necessary

    public control(int pWidth, int pHeight){

        initGrid(pWidth, pHeight);

        playerAtTurn = 0;
        amtPlayers = 0;
        players = new Player[2];
    }

    public boolean getStatus(){
        return gameEnd;
    }

    public void initGrid(int pWidth, int pHeight){

        uGrid = new grid(pWidth, pHeight);

    }

    public boolean placeStone(int pColumn){

        if(gameEnd == true){

            return true; //TODO: might be bullshit
        } else {
            boolean ret;
            int nuV;
            if (playerAtTurn == 0) {
                ret = uGrid.fillPC(players[playerAtTurn].getpChar(), pColumn);
                nuV = 1;
            } else {
                ret = uGrid.fillPC(players[playerAtTurn].getpChar(), pColumn);
                nuV = 0;
            }

            for (int i = 0; i < uGrid.getGrid().length; i++) {
                for (int j = 0; j < uGrid.getGrid()[0].length; j++) {
                    if (checkW(i, j) == true) {
                        gameEnd = true;
                        winP = Integer.parseInt(uGrid.getGrid()[i][j]);
                        return true;
                    }
                }
            }


            if (ret == true) {
                playerAtTurn = nuV;
            }
            return ret;

        }



    }

    public void crePlayer(Player pPlayer){
        if(amtPlayers <= 1) {
            players[amtPlayers] = pPlayer;
            amtPlayers++;
        }
    }

    public void crePlayer(String pName){

        if(amtPlayers <= 1) {
            System.out.println(amtPlayers + " " + players.length);
            players[amtPlayers] = new Player();
            players[amtPlayers].setName(pName);
            players[amtPlayers].setpChar(String.valueOf(amtPlayers));
            amtPlayers++;
        }
    }

    public boolean checkFullGird(){
        boolean full = true;

        for(int i = 0; i<= uGrid.getGrid().length-1; i++){

            if(uGrid.checkFullCol(i) == false){
                full = true;
                break;
            }
        }
        return full;
    }

    public void switchPlayer(){
        if(playerAtTurn == 0){
            playerAtTurn = 1;
        } else {
            playerAtTurn =0;
        }
    }

    public boolean checkW(int pColumn, int pRow){
        boolean won = false;
            if(uGrid.getGrid()[pColumn][pRow] != "0"){
                int check = cIFB(checkHorW(pColumn, pRow)) + cIFB(checkDiaL(pColumn, pRow)) + cIFB(checkDiaR(pColumn, pRow)) + cIFB(checkVerW(pColumn, pRow));
                    if(check>0){
                        won = true;
                    }
            }


        return won;

    }

    public String[][] getGrid(){
        return uGrid.getGrid();
    }

    public String getpChar(int pP){
        return players[pP].getpChar();
    }

    public String getCurrentPlayerName(){
        return players[playerAtTurn].getName();
    }

    public String getWinningPlayerName(){
        return players[winP].getName();
    }

    private int cIFB(boolean pValue){ //returns 1 if pValue is true
        int ret = 0;
        if (pValue == true){
            ret = 1;
        }
        return ret;
    }

    private boolean checkHorW(int pColumn, int pRow){ //TESTED + row and Column (x) must be x-1
        boolean hWon = false;
        int countSameChar = 0;

        if(uGrid.getGrid()[pColumn][pRow] != "0") {
            if (pColumn <= uGrid.getGrid().length - 4) {

                for (int i = pColumn; i <= pColumn + 3; i++) {

                    if (uGrid.getGrid()[i][pRow] == uGrid.getGrid()[pColumn][pRow]) {
                        countSameChar++;
                    } else {
                        break;
                    }

                }

                if (countSameChar == 4) {
                    hWon = true;
                }

            }
        }

        return hWon;
    }

    private boolean checkVerW(int pColumn, int pRow){ //Tested
        boolean vWon = false;
        int countSameChar = 0;

        if(uGrid.getGrid()[pColumn][pRow] != "0") {
            if (pRow <= uGrid.getGrid()[0].length - 4) {

                for (int i = pRow; i <= pRow + 3; i++) {

                    if (uGrid.getGrid()[pColumn][i] == uGrid.getGrid()[pColumn][pRow]) {
                        countSameChar++;
                    } else {
                        break;
                    }

                }

                if (countSameChar == 4) {
                    vWon = true;
                }
            }
        }

        return vWon;
    }


    private boolean checkDiaR(int pColumn, int pRow){ //Tested
        boolean diaRW = false;
        int countSameChar = 0;

        if(uGrid.getGrid()[pColumn][pRow] != "0") {

            if (pRow <= uGrid.getGrid()[0].length - 4 && pColumn <= uGrid.getGrid().length - 4) {
                for (int r = 0; r <= 3; r++) {

                    if (uGrid.getGrid()[pColumn + r][pRow + r] == uGrid.getGrid()[pColumn][pRow]) {
                        countSameChar++;
                    } else {
                        break;

                    }

                }
                if (countSameChar == 4) {
                    diaRW = true;
                }

            }
        }


        return diaRW;
    }

    private boolean checkDiaL(int pColumn, int pRow) { //Tested
        boolean diaLW = false;
        int countSameChar = 0;

        if (uGrid.getGrid()[pColumn][pRow] != "0") {
            if (pRow <= uGrid.getGrid()[0].length-4 && pColumn >= 3) {
                for (int r = 0; r <= 3; r++) {


                    if (uGrid.getGrid()[pColumn - r][pRow + r] == uGrid.getGrid()[pColumn][pRow]) {
                        countSameChar++;
                    } else {
                        break;
                    }


                }

                if (countSameChar == 4) {
                    diaLW = true;
                }
            }
        }


            return diaLW;
        }


    public void printVisGrid(){
        System.out.println(uGrid.getVisGrid());
    }

}
