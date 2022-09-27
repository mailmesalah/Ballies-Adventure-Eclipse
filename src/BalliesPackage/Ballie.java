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
public class Ballie extends ImageIcon{    
    int row;
    int col;

    public Ballie( int row, int col,Image image) {
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

    public void setCol(int col) {
        this.col = col;
    }
    
    
    
}
