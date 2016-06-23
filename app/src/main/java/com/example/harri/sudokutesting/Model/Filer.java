package com.example.harri.sudokutesting.Model;

/**
 * Created by harri on 23/06/2016.
 */
public class Filer {

    GameImpl theGame;

    public Filer(GameImpl model) {
        this.theGame = model;
    }

    public void setMap_4_1(){
        theGame.setMaxValue(16);
        theGame.setSingleValue(2, theGame.getCellByCoord(2, 0));
        theGame.setSingleValue(3, theGame.getCellByCoord(1, 1));
        theGame.setSingleValue(4, theGame.getCellByCoord(3, 1));
        theGame.setSingleValue(3, theGame.getCellByCoord(0, 2));
        theGame.setSingleValue(4, theGame.getCellByCoord(2, 2));
        theGame.setSingleValue(1, theGame.getCellByCoord(1, 3));
        theGame.finaliseInitialPuzzle();
    }

    public void setMap_4_2(){
        theGame.setMaxValue(16);
        theGame.setSingleValue(3, theGame.getCellByCoord(0, 0));
        theGame.setSingleValue(4, theGame.getCellByCoord(1, 0));
        theGame.setSingleValue(1, theGame.getCellByCoord(2, 0));
        theGame.setSingleValue(2, theGame.getCellByCoord(1, 1));
        theGame.setSingleValue(2, theGame.getCellByCoord(2, 2));
        theGame.setSingleValue(1, theGame.getCellByCoord(1, 3));
        theGame.setSingleValue(4, theGame.getCellByCoord(2, 3));
        theGame.setSingleValue(3, theGame.getCellByCoord(3, 3));
        theGame.finaliseInitialPuzzle();
    }

    public void setMap_4_3(){
        theGame.setMaxValue(16);
        theGame.setSingleValue(4, theGame.getCellByCoord(2, 0));
        theGame.setSingleValue(4, theGame.getCellByCoord(0, 1));
        theGame.setSingleValue(3, theGame.getCellByCoord(2, 1));
        theGame.setSingleValue(4, theGame.getCellByCoord(1, 2));
        theGame.setSingleValue(3, theGame.getCellByCoord(3, 2));
        theGame.setSingleValue(1, theGame.getCellByCoord(1, 3));
        theGame.finaliseInitialPuzzle();
    }

    public void setMap(int theMap) {
        switch(theMap) {
            case 1:
                this.setMap_4_1();
                break;
            case 2:
                this.setMap_4_2();
                break;
            case 3:
                this.setMap_4_3();
                break;
            default:
                break;
        }
    }




}
