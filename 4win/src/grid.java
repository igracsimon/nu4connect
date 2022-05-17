public class grid {

    private String[/*column*/][/*row*/] mGrid;
    //TODO: when creating the GUIgrid make sure you render it correctly, mGrid[0][0] is the lowest stone
    public grid(int pWidth, int pHeight){

        mGrid = new String[pWidth][pHeight];

        for(int i = 0; i<= pWidth-1; i++){
            for(int j = 0; j<= pHeight-1; j++) {
                mGrid[i][j] = "0";
            }
        }




    }

    public boolean fillPC(String pChar, int pColumn){ //returns filled = true if a Char got placed in a Column
        boolean filled = false;

                if(checkFullCol(pColumn) == false){
                    for(int i = 0; i <= mGrid[pColumn].length-1; i++){
                        if(mGrid[pColumn][i] == "0"){
                            mGrid[pColumn][i] = pChar;
                            filled = true;
                            return filled;
                        }
                    }
                }

        return filled;
    }

    public boolean checkFullCol(int pColumn){
        boolean isFull = false;

        if(mGrid[pColumn][mGrid[0].length-1] != "0"){  //[0] is lowest spot in column
            isFull = true;
        }

        return isFull;
    } //returns isFull = true if a Column is full

    public String[][] getGrid(){

        return mGrid;
    }

    public String getVisGrid(){

        String visGrid = "";
        for(int c = mGrid[0].length-1; c >= 0; c--){
            for(int r = mGrid.length-1; r >= 0; r--){
                visGrid = visGrid + "[" + mGrid[r][c] + "] ";
            }
            visGrid = visGrid + "\n";
        }

        return visGrid;
    }


}
