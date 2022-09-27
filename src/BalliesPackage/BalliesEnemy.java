/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BalliesPackage;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Sely
 */
public class BalliesEnemy extends ImageIcon{
    static final int HORIZONTAL=0;
    static final int VERTICAL=1;
    
    int row;
    int col;
    int direction;
    int lastRow;
    int lastCol;

    public BalliesEnemy(int row, int col, int direction, int lastRow, int lastCol, Image image) {
        super(image);
        this.row = row;
        this.col = col;
        this.direction = direction;
        this.lastRow = lastRow;
        this.lastCol = lastCol;
    }

    BalliesEnemy(int row, int col, Image image) {
        super(image);
        this.row = row;
        this.col = col;
    }


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getLastRow() {
        return lastRow;
    }

    public void setLastRow(int lastRow) {
        this.lastRow = lastRow;
    }

    public int getLastCol() {
        return lastCol;
    }

    public void setLastCol(int lastCol) {
        this.lastCol = lastCol;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

}
