/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BalliesPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Sely
 */
public class JPPlayGround extends javax.swing.JPanel {

    /**
     * Creates new form JPPlayGround
     */
    static boolean Playing=false;
    static int FAILED=0;
    static int SUCCESS=1;
    static int stageNo=0;
    static int rowCount = 20;
    static int colCount = 30;
    static ArrayList objects = new ArrayList();
    ArrayList enemies = new ArrayList<BalliesEnemy>();
    ArrayList coins = new ArrayList<BalliesCoin>();
    ArrayList floors = new ArrayList<BalliesFloor>();
    JTable jt;
    Ballie ballie;
    int coinCount = 0;
    ArrayList savedGames = new ArrayList<String>();
    ArrayList savedGamesKey = new ArrayList<String>();
        
    
    public JPPlayGround() {
        initComponents();
        initialisePlayGround();
    }
    
    public void setStage(int stage){
    	stageNo=stage;
    }
    
    public int getStage(){
    	return stageNo;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        this.setBackground(Color.black);

    }
    
    private void stopApplication(){
        System.exit(0);
    }
    
    private void startGame(){    	
        if(stageNo==0){
            initialiseStage0();
        }else if(stageNo==1){            
            initialiseStage1();            
        }else if(stageNo==2){
            initialiseStage2();
        }else if(stageNo==3){
            initialiseStage3();
        }else if(stageNo==4){
            initialiseStage4();
        }else if(stageNo==5){
            initialiseStage5();
        }else if(stageNo==6){
            initialiseStage6();
        }
        this.repaint();
        Playing=true;
                
    }
    
    private void endGame(int state){
        int returnV=0;
        
        if(state==SUCCESS){
            //If success Next Game or End Game
            if(stageNo==6){
            	JOptionPane.showMessageDialog(this, "Finished all Stages !!, Your Score is "+ coinCount*10 +" !!!", "Congrats", JOptionPane.INFORMATION_MESSAGE);
            	coinCount=0;
            	returnV=JOptionPane.showConfirmDialog(this, "Do you want to Restart Game ?", "Restart?", JOptionPane.YES_NO_OPTION);
                if(returnV==1){
                    stopApplication();
                }else{
                    //Restart Game 
                    clearStage();
                    stageNo=0;
                    startGame();
                }
            }else{
                returnV=JOptionPane.showConfirmDialog(this, "Do you want to Try Next Stage ?", "Success", JOptionPane.YES_NO_OPTION);
                if(returnV==1){
                	JOptionPane.showMessageDialog(this, "Your Score is "+ coinCount*10 +" !!!", "Congrats", JOptionPane.INFORMATION_MESSAGE);
                    stopApplication();
                }else{
                    //Next Game 
                    clearStage();
                    stageNo++;
                    startGame();
                }
            }
            
                        
            
        }else{
            //If failure Try Again or End Game
        	JOptionPane.showMessageDialog(this, "Your Score is "+ coinCount*10 +" !!!", "Congrats", JOptionPane.INFORMATION_MESSAGE);
        	coinCount=0;
            if(JOptionPane.showConfirmDialog(this, "Do you want to Try again ?", "Failed", JOptionPane.YES_NO_OPTION)==1){
                stopApplication();
            }else{
                //Try Again
                clearStage();
                startGame();
            }
            
        }
    }
    
    private void clearStage(){
        //Clears all the objects
        Playing=false;
        enemies.clear();
        coins.clear();
        floors.clear();
    }
    
    private void initialiseStage0(){
    	enemies.clear();
        coins.clear();
        floors.clear();
    	//objects.clear();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                try {
                    Image image;
                    switch(i*colCount+j){
                        //Wall
                        case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10: case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19: case 20: case 21: case 22: case 23: case 24: case 25: case 26: case 27: case 28: case 29: case 30: case 31: case 32: case 33: case 34: case 35: case 36: case 37: case 38: case 39: case 40: case 41: case 42: case 43: case 44: case 45: case 46: case 47: case 48: case 49: case 50: case 51: case 52: case 53: case 54: case 55: case 56: case 57: case 58: case 59: case 60: case 61: case 62: case 63: case 64: case 65: case 66: case 67: case 68: case 69: case 70: case 71: case 72: case 73: case 74: case 75: case 76: case 77: case 78: case 79: case 80: case 81: case 82: case 83: case 84: case 85: case 86: case 87: case 88: case 89: case 90: case 91: case 92: case 93: case 94: case 95: case 96: case 97: case 98: case 99: case 100: case 109: case 110: case 118: case 119: case 120: case 121: case 122: case 123: case 124: case 125: case 126: case 127: case 128: case 129: case 130: case 134: case 135: case 139: case 140: case 148: case 149: case 150: case 151: case 164: case 165: case 169: case 170: case 178: case 179: case 180: case 181: case 194: case 195: case 199: case 200: case 208: case 209: case 210: case 211: case 224: case 225: case 229: case 230: case 236: case 237: case 238: case 239: case 240: case 241: case 254: case 255: case 266: case 267: case 268: case 269: case 270: case 271: case 272: case 275: case 276: case 277: case 278: case 279: case 280: case 281: case 282: case 283: case 284: case 285: case 286: case 296: case 297: case 298: case 299: case 300: case 301: case 302: case 326: case 327: case 328: case 329: case 330: case 331: case 332: case 335: case 336: case 337: case 338: case 339: case 340: case 341: case 342: case 343: case 344: case 345: case 346: case 358: case 359: case 360: case 361: case 388: case 389: case 390: case 391: case 418: case 419: case 420: case 421: case 448: case 449: case 450: case 451: case 478: case 479: case 480: case 481: case 508: case 509: case 510: case 511: case 538: case 539: case 540: case 541: case 568: case 569: case 570: case 571: case 572: case 573: case 574: case 575: case 576: case 577: case 578: case 579: case 580: case 581: case 582: case 583: case 584: case 585: case 586: case 587: case 588: case 589: case 590: case 591: case 592: case 593: case 594: case 595: case 596: case 597: case 598: case 599:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesWall.png"));
                                BalliesWall bw = new BalliesWall(i, j, image);
                                //objects.add(bw);
                                objects.set(i*colCount+j, bw);
                                break;
                        //Enemy Horizontal
                        case 111: case 177: case 303: 
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesEnemy.png"));
                                BalliesEnemy bhe = new BalliesEnemy(i, j, BalliesEnemy.HORIZONTAL, i, j, image);
                                //objects.add(bhe);
                                objects.set(i*colCount+j, bhe);
                                enemies.add(bhe);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bhef = new BalliesFloor(i, j, image);
                                floors.add(bhef);
                                break;
                        //Enemy Vertical
                        case 372: case 547: case 549:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesEnemy.png"));
                                BalliesEnemy bve = new BalliesEnemy(i, j, BalliesEnemy.VERTICAL, i, j, image);
                                //objects.add(bve);
                                objects.set(i*colCount+j, bve);
                                enemies.add(bve);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bvef = new BalliesFloor(i, j, image);
                                floors.add(bvef);
                                break;
                        //Floor
                        case 101: case 102: case 103: case 104: case 105: case 106: case 107: case 108: case 112: case 113: case 114: case 115: case 116: case 117: case 131: case 132: case 133: case 136: case 137: case 138: case 152: case 153: case 154: case 155: case 156: case 157: case 158: case 159: case 160: case 161: case 162: case 163: case 166: case 167: case 168: case 171: case 172: case 173: case 174: case 175: case 176: case 182: case 183: case 184: case 185: case 186: case 187: case 188: case 189: case 190: case 191: case 192: case 193: case 196: case 197: case 198: case 201: case 202: case 203: case 204: case 205: case 206: case 207: case 212: case 213: case 214: case 215: case 216: case 217: case 218: case 219: case 220: case 221: case 222: case 223: case 226: case 227: case 228: case 231: case 232: case 233: case 234: case 235: case 242: case 243: case 244: case 245: case 246: case 247: case 248: case 249: case 250: case 251: case 252: case 253: case 256: case 257: case 258: case 259: case 260: case 261: case 262: case 263: case 264: case 265: case 273: case 274: case 287: case 288: case 289: case 290: case 291: case 292: case 293: case 294: case 295: case 304: case 317: case 318: case 319: case 320: case 321: case 322: case 323: case 324: case 325: case 333: case 334: case 347: case 348: case 349: case 350: case 351: case 352: case 353: case 354: case 355: case 356: case 357: case 362: case 363: case 364: case 367: case 369: case 370: case 375: case 376: case 377: case 378: case 379: case 380: case 381: case 382: case 383: case 384: case 385: case 386: case 387: case 392: case 393: case 394: case 397: case 399: case 401: case 402: case 405: case 406: case 407: case 408: case 409: case 410: case 413: case 414: case 415: case 416: case 417: case 422: case 423: case 424: case 430: case 432: case 436: case 437: case 438: case 439: case 440: case 443: case 444: case 445: case 446: case 447: /*case 452:*/ case 453: case 454: case 461: case 462: case 466: case 467: case 468: case 469: case 470: case 473: case 474: case 475: case 476: case 477: case 482: case 483: case 484: case 490: case 492: case 496: case 497: case 498: case 499: case 500: case 501: case 502: case 503: case 504: case 505: case 506: case 507: case 512: case 513: case 514: case 517: case 519: case 521: case 522: case 525: case 526: case 527: case 528: case 529: case 530: case 531: case 532: case 533: case 534: case 535: case 536: /*case 537:*/ case 542: case 543: case 544: case 550: case 552: case 555: case 556: case 557: case 558: case 559: case 560: case 561: case 562: case 563: case 564: case 565: case 566: case 567:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bf = new BalliesFloor(i, j, image);
                                //objects.add(bf);
                                objects.set(i*colCount+j, bf);
                                floors.add(bf);
                                break;
                        //Coin
                        case 141: case 142: case 143: case 144: case 145: case 146: case 147: case 305: case 306: case 307: case 308: case 309: case 310: case 311: case 312: case 313: case 314: case 315: case 316: case 365: case 366: case 368: case 371: case 373: case 374: case 395: case 396: case 398: case 400: case 403: case 404: case 411: case 412: case 425: case 426: case 427: case 428: case 429: case 431: case 433: case 434: case 435: case 441: case 442: case 455: case 456: case 457: case 458: case 459: case 460: case 463: case 464: case 465: case 471: case 472: case 485: case 486: case 487: case 488: case 489: case 491: case 493: case 494: case 495: case 515: case 516: case 518: case 520: case 523: case 524: case 545: case 546: case 548: case 551: case 553: case 554: 
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesCoin.png"));
                                BalliesCoin bc = new BalliesCoin(i, j, image);
                                //objects.add(bc);
                                objects.set(i*colCount+j, bc);
                                coins.add(bc);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bcf = new BalliesFloor(i, j, image);
                                floors.add(bcf);
                                break;
                        //Door    
                        case 537:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesDoor.png"));
                                BalliesDoor bd = new BalliesDoor(i, j, image);
                                //objects.add(bd);
                                objects.set(i*colCount+j, bd);
                                break;
                        //Ballie
                        case 452:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\Ballie.png"));
                                ballie = new Ballie(i, j, image);
                                //objects.add(ballie);
                                objects.set(i*colCount+j, ballie);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor blf = new BalliesFloor(i, j, image);
                                floors.add(blf);
                                break;
                        default:
                    }
                   
                } catch (IOException ex) {
                    Logger.getLogger(JPPlayGround.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }        
    }
        
    private void initialiseStage1(){
    	enemies.clear();
        coins.clear();
        floors.clear();
        //objects.clear();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                try {
                    Image image;
                    switch(i*colCount+j){
                        //Wall
                        case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10: case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19: case 20: case 21: case 22: case 23: case 24: case 25: case 26: case 27: case 28: case 29: case 30: case 31: case 32: case 33: case 34: case 35: case 36: case 37: case 52: case 53: case 54: case 55: case 56: case 57: case 58: case 59: case 60: case 61: case 62: case 63: case 64: case 65: case 66: case 67: case 82: case 83: case 84: case 85: case 86: case 87: case 88: case 89: case 90: case 91: case 92: case 93: case 94: case 95: case 96: case 97: case 112: case 113: case 114: case 115: case 116: case 117: case 118: case 119: case 120: case 121: case 122: case 123: case 124: case 125: case 126: case 127: case 142: case 143: case 144: case 145: case 146: case 147: case 148: case 149: case 150: case 151: case 152: case 153: case 154: case 155: case 156: case 157: case 172: case 173: case 174: case 175: case 176: case 177: case 178: case 179: case 180: case 181: case 182: case 183: case 184: case 185: case 186: case 187: case 188: case 189: case 190: case 191: case 192: case 197: case 198: case 199: case 200: case 201: case 202: case 203: case 204: case 205: case 206: case 207: case 208: case 209: case 210: case 239: case 240: case 269: case 270: case 299: case 300: case 329: case 330: case 359: case 360: case 389: case 390: case 391: case 392: case 393: case 394: case 395: case 396: case 397: case 398: case 399: case 400: case 401: case 402: case 407: case 408: case 409: case 410: case 411: case 412: case 413: case 414: case 415: case 416: case 417: case 418: case 419: case 420: case 421: case 422: case 423: case 424: case 425: case 426: case 427: case 442: case 443: case 444: case 445: case 446: case 447: case 448: case 449: case 450: case 451: case 452: case 453: case 454: case 455: case 456: case 457: case 472: case 473: case 474: case 475: case 476: case 477: case 478: case 479: case 480: case 481: case 482: case 483: case 484: case 485: case 486: case 487: case 502: case 503: case 504: case 505: case 506: case 507: case 508: case 509: case 510: case 511: case 512: case 513: case 514: case 515: case 516: case 517: case 532: case 533: case 534: case 535: case 536: case 537: case 538: case 539: case 540: case 541: case 542: case 543: case 544: case 545: case 546: case 547: case 562: case 563: case 564: case 565: case 566: case 567: case 568: case 569: case 570: case 571: case 572: case 573: case 574: case 575: case 576: case 577: case 578: case 579: case 580: case 581: case 582: case 583: case 584: case 585: case 586: case 587: case 588: case 589: case 590: case 591: case 592: case 593: case 594: case 595: case 596: case 597: case 598: case 599:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesWall.png"));
                                BalliesWall bw = new BalliesWall(i, j, image);
                                //objects.add(bw);
                                objects.set(i*colCount+j, bw);
                                break;
                        //Enemy Horizontal
                        /*
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesEnemy.png"));
                                BalliesEnemy bhe = new BalliesEnemy(i, j, BalliesEnemy.HORIZONTAL, i, j, image);
                                objects.add(bhe);
                                enemies.add(bhe);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bhef = new BalliesFloor(i, j, image);
                                floors.add(bhef);
                                break;*/
                        //Enemy Vertical
                        case 162: case 167: case 432: case 437: 
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesEnemy.png"));
                                BalliesEnemy bve = new BalliesEnemy(i, j, BalliesEnemy.VERTICAL, i, j, image);
                                //objects.add(bve);
                                objects.set(i*colCount+j, bve);
                                enemies.add(bve);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bvef = new BalliesFloor(i, j, image);
                                floors.add(bvef);
                                break;
                        //Floor
                        case 39: case 41: case 42: case 43: case 44: case 45: case 46: case 47: case 48: case 50: /*case 68:*/ case 70: case 72: case 73: case 74: case 75: case 76: case 77: case 79: case 81: case 99: case 101: case 102: case 103: case 104: case 105: case 106: case 107: case 108: case 110: case 128: case 130: case 132: case 133: case 134: case 135: case 136: case 137: case 139: case 141: case 159: case 161: case 163: case 164: case 165: case 166: case 168: case 170: case 193: case 194: case 195: case 196: case 211: case 212: case 213: case 214: case 215: case 216: case 217: case 218: case 219: case 220: case 221: case 222: case 223: case 224: case 225: case 226: case 227: case 228: case 229: case 230: case 231: case 232: case 233: case 234: case 235: case 236: case 237: case 238: case 241: case 242: case 243: case 244: case 246: case 247: case 248: case 249: case 250: case 251: case 253: case 254: case 255: case 256: case 258: case 259: case 260: case 261: case 262: case 264: case 265: case 266: case 267: case 268: case 271: case 272: case 273: case 274: case 275: case 276: case 277: case 278: case 279: case 280: case 281: case 282: case 283: case 284: case 285: case 286: case 287: case 288: case 289: case 290: case 291: case 292: case 293: case 294: case 295: case 296: case 297: case 298: case 301: case 302: case 303: case 304: case 305: case 306: case 307: case 308: case 309: case 310: case 311: case 312: case 313: case 314: case 315: case 316: case 317: case 318: case 319: case 320: case 321: case 322: case 323: case 324: case 325: case 326: case 327: case 328: case 331: case 332: case 333: case 334: case 336: case 337: case 338: case 339: case 340: case 341: case 343: case 344: case 345: case 346: case 348: case 349: case 350: case 351: case 352: case 354: case 355: case 356: case 357: case 358: case 361: case 362: case 363: case 364: case 365: case 366: case 367: case 368: case 369: case 370: case 371: case 372: case 373: case 374: case 375: case 376: case 377: case 378: case 379: case 380: case 381: case 382: case 383: case 384: case 385: case 386: case 387: case 388: case 403: case 404: case 405: case 406: case 428: case 430: case 433: case 434: case 435: case 436: case 439: case 441: case 459: case 461: case 462: case 463: case 464: case 465: case 466: case 467: case 468: case 470: case 488: case 490: case 492: case 493: case 494: case 495: case 496: case 497: case 499: /*case 501:*/ case 519: case 521: case 522: case 523: case 524: case 525: case 526: case 527: case 528: case 530: case 548: case 550: case 552: case 553: case 554: case 555: case 556: case 557: case 559: case 561:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bf = new BalliesFloor(i, j, image);
                                //objects.add(bf);
                                objects.set(i*colCount+j, bf);
                                floors.add(bf);
                                break;
                        //Coin
                        case 38: case 40: case 49: case 51: case 69: case 71: case 78: case 80: case 98: case 100: case 109: case 111: case 129: case 131: case 138: case 140: case 158: case 160: case 169: case 171: case 245: case 252: case 257: case 263: case 335: case 342: case 347: case 353: case 429: case 431: case 438: case 440: case 458: case 460: case 469: case 471: case 489: case 491: case 498: case 500: case 518: case 520: case 529: case 531: case 549: case 551: case 558: case 560:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesCoin.png"));
                                BalliesCoin bc = new BalliesCoin(i, j, image);
                                //objects.add(bc);
                                objects.set(i*colCount+j, bc);
                                coins.add(bc);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bcf = new BalliesFloor(i, j, image);
                                floors.add(bcf);
                                break;
                        //Door    
                        case 501:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesDoor.png"));
                                BalliesDoor bd = new BalliesDoor(i, j, image);
                                //objects.add(bd);
                                objects.set(i*colCount+j, bd);
                                break;
                        //Ballie
                        case 68:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\Ballie.png"));
                                ballie = new Ballie(i, j, image);
                                //objects.add(ballie);
                                objects.set(i*colCount+j, ballie);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor blf = new BalliesFloor(i, j, image);
                                floors.add(blf);
                                break;
                        default:
                    }
                   
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                    Logger.getLogger(JPPlayGround.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }     
    }
    
    private void initialiseStage2(){
    	enemies.clear();
        coins.clear();
        floors.clear();
    	//objects.clear();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                try {
                    Image image;
                    switch(i*colCount+j){
                        //Wall
                        case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10: case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19: case 20: case 21: case 22: case 23: case 24: case 25: case 26: case 27: case 28: case 29: case 30: case 37: case 48: case 59: case 60: case 67: case 78: case 89: case 90: case 97: case 104: case 119: case 120: case 127: case 134: case 149: case 150: case 157: case 160: case 161: case 162: case 163: case 164: case 165: case 166: case 167: case 168: case 169: case 170: case 179: case 180: case 187: case 200: case 203: case 204: case 205: case 206: case 207: case 208: case 209: case 210: case 217: case 230: case 239: case 240: case 247: case 260: case 269: case 270: case 271: case 272: case 275: case 276: case 277: case 278: case 279: case 280: case 281: case 282: case 283: case 284: case 285: case 286: case 290: case 299: case 300: case 306: case 316: case 320: case 321: case 322: case 323: case 324: case 325: case 326: case 329: case 330: case 336: case 346: case 350: case 359: case 360: case 366: case 376: case 380: case 389: case 390: case 396: case 406: case 410: case 419: case 420: case 421: case 422: case 425: case 426: case 427: case 428: case 429: case 432: case 436: case 440: case 443: case 444: case 445: case 446: case 447: case 448: case 449: case 450: case 459: case 462: case 466: case 470: case 479: case 480: case 489: case 492: case 500: case 509: case 510: case 515: case 522: case 530: case 539: case 540: case 545: case 552: case 560: case 569: case 570: case 571: case 572: case 573: case 574: case 575: case 576: case 577: case 578: case 579: case 580: case 581: case 582: case 583: case 584: case 585: case 586: case 587: case 588: case 589: case 590: case 591: case 592: case 593: case 594: case 595: case 596: case 597: case 598: case 599:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesWall.png"));
                                BalliesWall bw = new BalliesWall(i, j, image);
                                //objects.add(bw);
                                objects.set(i*colCount+j, bw);
                                break;
                        //Enemy Horizontal
                        case 133: case 301: case 335: case 361: case 375: case 377: case 395: case 411: case 465:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesEnemy.png"));
                                BalliesEnemy bhe = new BalliesEnemy(i, j, BalliesEnemy.HORIZONTAL, i, j, image);
                                //objects.add(bhe);
                                objects.set(i*colCount+j, bhe);
                                enemies.add(bhe);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bhef = new BalliesFloor(i, j, image);
                                floors.add(bhef);
                                break;
                        //Enemy Vertical
                        case 47: case 55: case 139: case 174: case 176: case 191: case 195: case 234: case 309: case 456: case 548: 
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesEnemy.png"));
                                BalliesEnemy bve = new BalliesEnemy(i, j, BalliesEnemy.VERTICAL, i, j, image);
                                //objects.add(bve);
                                objects.set(i*colCount+j, bve);
                                enemies.add(bve);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bvef = new BalliesFloor(i, j, image);
                                floors.add(bvef);
                                break;
                        //Floor
                        case 38: case 39: case 40: case 41: case 42: case 43: case 44: case 45: case 46: case 49: case 50: case 51: case 52: case 53: case 54: case 56: case 68: case 73: case 74: case 75: case 76: case 77: case 79: case 80: case 81: case 82: case 83: case 84: case 85: case 86: case 98: case 99: case 100: case 101: case 102: case 103: case 105: case 106: case 108: case 110: case 111: case 112: case 113: case 114: case 115: case 116: case 128: case 129: case 130: case 131: case 132: case 135: case 136: case 137: case 138: case 140: case 141: case 142: case 143: case 144: case 145: case 146: case 158: case 159: case 171: case 172: case 173: case 175: case 188: case 189: case 190: case 192: case 193: case 194: case 196: case 197: case 198: case 199: case 201: case 202: case 218: case 219: case 220: case 221: case 222: case 224: case 225: case 226: case 227: case 229: case 231: case 232: case 233: case 235: case 236: case 237: case 238: case 248: case 249: case 250: case 251: case 252: case 253: case 254: case 255: case 256: case 257: case 258: case 259: case 261: case 262: case 263: case 264: case 265: case 266: case 268: case 273: case 274: case 287: case 289: case 291: case 292: case 293: case 294: case 295: case 296: case 297: case 298: case 302: case 303: case 304: case 305: case 307: case 308: case 310: case 311: case 312: case 313: case 314: case 315: case 317: case 318: case 319: case 327: case 328: case 331: case 332: case 333: case 334: case 339: case 340: case 341: case 342: case 343: case 344: case 345: case 347: case 349: case 351: case 352: case 353: case 354: case 355: case 356: case 357: case 358: case 362: case 363: case 364: case 365: case 369: case 370: case 371: case 372: case 373: case 374: case 378: case 379: case 381: case 382: case 383: case 384: case 385: case 386: case 388: case 391: case 392: case 393: case 394: case 397: case 398: case 399: case 400: case 401: case 402: case 403: case 404: case 405: case 407: case 409: case 412: case 413: case 414: case 415: case 416: case 417: case 418: case 423: case 424: case 431: case 433: case 434: case 435: case 437: case 438: case 439: case 441: case 442: case 451: case 452: case 453: case 454: case 455: case 457: case 458: case 460: case 463: case 464: case 467: case 468: case 469: case 471: case 472: case 473: case 474: case 475: case 476: case 477: case 478: case 481: /*case 482:*/ case 483: case 484: case 485: case 486: case 488: case 491: case 493: case 495: case 496: case 497: case 499: case 501: case 503: case 505: case 507: /*case 508:*/ case 511: case 512: case 513: case 514: case 516: case 518: case 519: case 520: case 523: case 524: case 525: case 527: case 528: case 529: case 531: case 533: case 535: case 537: case 538: case 541: case 542: case 543: case 544: case 546: case 547: case 549: case 551: case 553: case 555: case 556: case 557: case 559: case 561: case 562: case 563: case 564: case 565: case 566: case 567: case 568:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bf = new BalliesFloor(i, j, image);
                                //objects.add(bf);
                                objects.set(i*colCount+j, bf);
                                floors.add(bf);
                                break;
                        //Coin
                        case 31: case 32: case 33: case 34: case 35: case 36: case 57: case 58: case 61: case 62: case 63: case 64: case 65: case 66: case 69: case 70: case 71: case 72: case 87: case 88: case 91: case 92: case 93: case 94: case 95: case 96: case 107: case 109: case 117: case 118: case 121: case 122: case 123: case 124: case 125: case 126: case 147: case 148: case 151: case 152: case 153: case 154: case 155: case 156: case 177: case 178: case 181: case 182: case 183: case 184: case 185: case 186: case 211: case 212: case 213: case 214: case 215: case 216: case 223: case 228: case 241: case 242: case 243: case 244: case 245: case 246: case 267: case 288: case 337: case 338: case 348: case 367: case 368: case 387: case 408: case 430: case 461: case 487: case 490: case 494: case 498: case 502: case 504: case 506: case 517: case 521: case 526: case 532: case 534: case 536: case 550: case 554: case 558:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesCoin.png"));
                                BalliesCoin bc = new BalliesCoin(i, j, image);
                                //objects.add(bc);
                                objects.set(i*colCount+j, bc);
                                coins.add(bc);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bcf = new BalliesFloor(i, j, image);
                                floors.add(bcf);
                                break;
                        //Door    
                        case 508:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesDoor.png"));
                                BalliesDoor bd = new BalliesDoor(i, j, image);
                                //objects.add(bd);
                                objects.set(i*colCount+j, bd);
                                break;
                        //Ballie
                        case 482:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\Ballie.png"));
                                ballie = new Ballie(i, j, image);
                                //objects.add(ballie);
                                objects.set(i*colCount+j, ballie);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor blf = new BalliesFloor(i, j, image);
                                floors.add(blf);
                                break;
                        default:
                    }
                   
                } catch (IOException ex) {
                    Logger.getLogger(JPPlayGround.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }        
    }
    
    private void initialiseStage3(){
    	enemies.clear();
        coins.clear();
        floors.clear();
    	//objects.clear();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                try {
                    Image image;
                    switch(i*colCount+j){
                        //Wall
                        case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10: case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19: case 20: case 21: case 22: case 23: case 24: case 25: case 26: case 27: case 28: case 29: case 30: case 36: case 48: case 59: case 60: case 66: case 78: case 89: case 90: case 96: case 101: case 108: case 113: case 119: case 120: case 126: case 131: case 138: case 143: case 149: case 150: case 156: case 161: case 168: case 173: case 179: case 180: case 186: case 191: case 198: case 203: case 209: case 210: case 216: case 221: case 228: case 233: case 239: case 240: case 246: case 251: case 258: case 263: case 269: case 270: case 276: case 281: case 288: case 293: case 299: case 300: case 306: case 311: case 318: case 323: case 329: case 330: case 336: case 341: case 348: case 353: case 359: case 360: case 366: case 371: case 378: case 383: case 389: case 390: case 396: case 401: case 408: case 413: case 419: case 420: case 426: case 431: case 438: case 439: case 440: case 441: case 442: case 443: case 449: case 450: case 456: case 461: case 479: case 480: case 491: case 509: case 510: case 521: case 539: case 540: case 551: case 569: case 570: case 571: case 572: case 573: case 574: case 575: case 576: case 577: case 578: case 579: case 580: case 581: case 582: case 583: case 584: case 585: case 586: case 587: case 588: case 589: case 590: case 591: case 592: case 593: case 594: case 595: case 596: case 597: case 598: case 599:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesWall.png"));
                                BalliesWall bw = new BalliesWall(i, j, image);
                                //objects.add(bw);
                                objects.set(i*colCount+j, bw);
                                break;
                        //Enemy Horizontal
                        case 88: case 102: case 148: case 151: case 167: case 169: case 181: case 190: case 204: case 220: case 222: case 238: case 245: case 262: case 287: case 298: case 301: case 307: case 319: case 335: case 342: case 365: case 407: case 414: case 427: case 451: case 457:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesEnemy.png"));
                                BalliesEnemy bhe = new BalliesEnemy(i, j, BalliesEnemy.HORIZONTAL, i, j, image);
                                //objects.add(bhe);
                                objects.set(i*colCount+j, bhe);
                                enemies.add(bhe);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bhef = new BalliesFloor(i, j, image);
                                floors.add(bhef);
                                break;
                        //Enemy Vertical
                        case 469: case 472:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesEnemy.png"));
                                BalliesEnemy bve = new BalliesEnemy(i, j, BalliesEnemy.VERTICAL, i, j, image);
                                //objects.add(bve);
                                objects.set(i*colCount+j, bve);
                                enemies.add(bve);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bvef = new BalliesFloor(i, j, image);
                                floors.add(bvef);
                                break;
                        //Floor
                        case 31: case 32: case 33: case 34: case 35: case 37: case 38: case 39: case 40: case 41: case 42: case 43: case 44: case 45: case 46: case 47: case 49: case 50: case 51: case 52: case 53: case 54: case 55: case 56: case 57: case 58: case 61: case 62: case 63: case 64: case 65: case 67: case 76: case 77: case 79: case 91: case 92: case 93: case 94: case 95: case 97: case 98: case 99: case 100: case 103: case 104: case 105: case 106: case 107: case 109: case 110: case 111: case 112: case 114: case 115: case 116: case 117: case 118: case 121: case 122: case 123: case 124: case 125: case 127: case 128: case 129: case 130: case 132: case 133: case 134: case 135: case 136: case 137: case 139: case 140: case 141: case 142: case 144: case 145: case 147: case 153: case 154: case 155: case 157: case 158: case 159: case 160: case 162: case 172: case 174: case 175: case 176: case 177: case 178: case 183: case 184: case 185: case 187: case 189: case 192: case 193: case 194: case 195: case 196: case 197: case 199: case 200: case 201: case 202: case 205: case 207: case 208: case 211: case 212: case 213: case 214: case 215: case 217: case 219: case 223: case 224: case 225: case 226: case 227: case 229: case 230: case 231: case 232: case 234: case 235: case 236: case 237: case 241: case 242: case 243: case 247: case 248: case 249: case 250: case 252: case 253: case 254: case 255: case 256: case 257: case 259: case 264: case 265: case 266: case 267: case 268: case 271: case 272: case 273: case 275: case 277: case 278: case 280: case 282: case 289: case 290: case 291: case 292: case 294: case 295: case 297: case 303: case 304: case 305: case 308: case 310: case 312: case 313: case 314: case 315: case 316: case 317: case 322: case 324: case 325: case 326: case 327: case 328: case 331: case 333: case 334: case 337: case 338: case 340: case 343: case 344: case 345: case 346: case 347: case 349: case 350: case 351: case 352: case 354: case 355: case 356: case 357: case 358: case 361: case 363: case 364: case 367: case 368: case 370: case 372: case 373: case 374: case 375: case 376: case 377: case 379: case 380: /*case 381:*/ case 382: case 384: case 385: case 386: case 387: case 388: case 391: case 392: case 393: case 394: case 395: case 397: case 398: case 399: case 400: case 402: case 409: case 410: case 411: case 412: case 415: case 416: case 417: case 418: case 421: case 422: case 423: case 425: case 429: case 430: case 432: case 433: case 434: case 435: case 436: case 437: case 444: case 445: case 446: case 447: case 448: case 452: case 453: case 455: case 459: case 460: case 462: case 463: case 464: case 465: case 466: case 467: case 468: case 470: case 471: case 473: case 474: case 475: case 476: case 477: case 478: case 481: /*case 482:*/ case 483: case 485: case 486: case 487: case 488: case 489: case 490: case 492: case 493: case 494: case 495: case 496: case 497: case 498: case 499: case 500: case 501: case 502: case 503: case 504: case 505: case 506: case 507: case 508: case 511: case 512: case 513: case 514: case 515: case 517: case 518: case 519: case 520: case 522: case 523: case 524: case 525: case 526: case 527: case 528: case 533: case 534: case 535: case 536: case 537: case 538: case 541: case 542: case 543: case 544: case 545: case 546: case 547: case 548: case 549: case 550: case 552: case 553: case 554: case 555: case 556: case 557: case 558: case 559: case 560: case 561: case 562: case 563: case 564: case 565: case 566: case 567: case 568:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bf = new BalliesFloor(i, j, image);
                                //objects.add(bf);
                                objects.set(i*colCount+j, bf);
                                floors.add(bf);
                                break;
                        //Coin
                        case 68: case 69: case 70: case 71: case 72: case 73: case 74: case 75: case 80: case 81: case 82: case 83: case 84: case 85: case 86: case 87: case 146: case 152: case 163: case 164: case 165: case 166: case 170: case 171: case 182: case 188: case 206: case 218: case 244: case 260: case 261: case 274: case 279: case 283: case 284: case 285: case 286: case 296: case 302: case 309: case 320: case 321: case 332: case 339: case 362: case 369: case 403: case 404: case 405: case 406: case 424: case 428: case 454: case 458: case 484: case 516: case 529: case 530: case 531: case 532:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesCoin.png"));
                                BalliesCoin bc = new BalliesCoin(i, j, image);
                                //objects.add(bc);
                                objects.set(i*colCount+j, bc);
                                coins.add(bc);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bcf = new BalliesFloor(i, j, image);
                                floors.add(bcf);
                                break;
                        //Door    
                        case 381: 
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesDoor.png"));
                                BalliesDoor bd = new BalliesDoor(i, j, image);
                                //objects.add(bd);
                                objects.set(i*colCount+j, bd);
                                break;
                        //Ballie
                        case 482:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\Ballie.png"));
                                ballie = new Ballie(i, j, image);
                                objects.add(ballie);
                                objects.set(i*colCount+j, ballie);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor blf = new BalliesFloor(i, j, image);
                                floors.add(blf);
                                break;
                        default:
                    }
                   
                } catch (IOException ex) {
                    Logger.getLogger(JPPlayGround.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
     
    private void initialiseStage4(){
    	enemies.clear();
        coins.clear();
        floors.clear();
    	//objects.clear();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                try {
                    Image image;
                    switch(i*colCount+j){
                        //Wall
                        case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10: case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19: case 20: case 21: case 22: case 23: case 24: case 25: case 26: case 27: case 28: case 29: case 30: case 59: case 60: case 89: case 90: case 119: case 120: case 149: case 150: case 160: case 169: case 179: case 180: case 190: case 199: case 209: case 210: case 220: case 229: case 239: case 240: case 269: case 270: case 275: case 294: case 299: case 300: case 305: case 324: case 329: case 330: case 335: case 354: case 359: case 360: case 365: case 384: case 389: case 390: case 395: case 414: case 419: case 420: case 425: case 444: case 449: case 450: case 455: case 456: case 457: case 458: case 459: case 460: case 461: case 462: case 463: case 464: case 465: case 466: case 467: case 468: case 469: case 470: case 471: case 472: case 473: case 474: case 479: case 480: case 509: case 510: case 539: case 540: case 569: case 570: case 571: case 572: case 573: case 574: case 575: case 576: case 577: case 578: case 579: case 580: case 581: case 582: case 583: case 584: case 585: case 586: case 587: case 588: case 589: case 590: case 591: case 592: case 593: case 594: case 595: case 596: case 597: case 598: case 599:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesWall.png"));
                                BalliesWall bw = new BalliesWall(i, j, image);
                                //objects.add(bw);
                                objects.set(i*colCount+j, bw);
                                break;
                        //Enemy Horizontal
                        case 159: case 170: case 191: case 198: case 219: case 230: case 274: case 295: case 334: case 355: case 391: case 418: case 451: case 478: case 486: case 488: case 501: case 503: case 550: case 559:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesEnemy.png"));
                                BalliesEnemy bhe = new BalliesEnemy(i, j, BalliesEnemy.HORIZONTAL, i, j, image);
                                //objects.add(bhe);
                                objects.set(i*colCount+j, bhe);
                                enemies.add(bhe);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bhef = new BalliesFloor(i, j, image);
                                floors.add(bhef);
                                break;
                        //Enemy Vertical
                        case 44: case 45: case 433: case 436: 
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesEnemy.png"));
                                BalliesEnemy bve = new BalliesEnemy(i, j, BalliesEnemy.VERTICAL, i, j, image);
                                //objects.add(bve);
                                objects.set(i*colCount+j, bve);
                                enemies.add(bve);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bvef = new BalliesFloor(i, j, image);
                                floors.add(bvef);
                                break;
                        //Floor
                        /*case 31:*/ case 32: case 33: case 34: case 35: case 36: case 37: case 38: case 39: case 40: case 41: case 42: case 43: case 46: case 47: case 48: case 49: case 50: case 51: case 52: case 53: case 54: case 55: case 56: case 57: case 58: case 61: case 62: case 63: case 64: case 65: case 66: case 67: case 68: case 69: case 70: case 71: case 72: case 73: case 74: case 75: case 76: case 77: case 78: case 79: case 80: case 81: case 82: case 83: case 84: case 85: case 86: case 87: case 88: case 91: case 92: case 93: case 96: case 97: case 98: case 99: case 100: case 101: case 102: case 103: case 104: case 105: case 106: case 107: case 108: case 109: case 110: case 111: case 112: case 113: case 116: case 117: case 118: case 121: case 122: case 124: case 125: case 127: case 128: case 129: case 130: case 131: case 132: case 133: case 134: case 135: case 136: case 137: case 138: case 139: case 140: case 141: case 142: case 144: case 145: case 147: case 148: case 151: case 152: case 157: case 158: case 161: case 162: case 163: case 164: case 165: case 166: case 167: case 168: case 171: case 172: case 177: case 178: case 181: case 182: case 183: case 184: case 185: case 186: case 187: case 188: case 189: case 192: case 193: case 194: case 195: case 196: case 197: case 200: case 201: case 202: case 203: case 204: case 205: case 206: case 207: case 208: case 211: case 212: case 213: case 214: case 215: case 216: case 217: case 218: case 221: case 222: case 223: case 224: case 225: case 226: case 227: case 228: case 231: case 232: case 233: case 234: case 235: case 236: case 237: case 238: case 241: case 242: case 243: case 244: case 245: case 246: case 247: case 248: case 249: case 250: case 251: case 252: case 253: case 254: case 255: case 256: case 257: case 258: case 259: case 260: case 261: case 262: case 263: case 264: case 265: case 266: case 267: case 268: case 271: case 272: case 273: case 276: case 277: case 278: case 279: case 280: case 281: case 282: case 283: case 284: case 285: case 286: case 287: case 288: case 289: case 290: case 291: case 292: case 293: case 296: case 297: case 298: case 301: case 302: case 304: case 306: case 307: case 310: case 311: case 312: case 313: case 314: case 315: case 316: case 317: case 318: case 319: case 322: case 323: case 325: case 327: case 328: case 331: case 333: case 336: case 338: case 339: case 341: case 342: case 343: case 344: case 345: case 346: case 347: case 348: case 350: case 351: case 353: case 356: case 358: case 361: case 362: case 364: case 366: case 371: case 372: case 373: case 374: case 375: case 376: case 377: case 378: case 383: case 385: case 387: case 388: case 393: case 394: case 396: case 397: case 398: case 399: case 400: case 401: case 402: case 403: case 404: case 405: case 406: case 407: case 408: case 409: case 410: case 411: case 412: case 413: case 415: case 416: case 421: case 422: case 424: case 426: case 427: case 428: case 429: case 430: case 431: case 432: case 434: case 435: case 437: case 438: case 439: case 440: case 441: case 442: case 443: case 445: case 447: case 448: case 453: case 454: case 475: case 476: case 481: case 482: case 484: case 485: case 487: case 489: case 490: case 491: case 492: case 493: case 494: case 495: case 496: case 497: case 498: case 499: case 500: case 502: case 504: case 505: case 507: case 508: case 511: case 513: case 515: case 517: case 519: case 521: case 523: case 524: case 525: case 526: case 528: case 530: case 532: case 534: case 536: case 538: case 541: case 542: case 543: case 544: case 545: case 546: case 547: case 548: case 549: case 551: case 552: case 553: case 554: case 555: case 556: case 557: case 558: case 560: case 561: case 562: case 563: case 564: case 565: case 566: case 567: /*case 568:*/
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bf = new BalliesFloor(i, j, image);
                                //objects.add(bf);
                                objects.set(i*colCount+j, bf);
                                floors.add(bf);
                                break;
                        //Coin
                        case 94: case 95: case 114: case 115: case 123: case 126: case 143: case 146: case 153: case 154: case 155: case 156: case 173: case 174: case 175: case 176: case 303: case 308: case 309: case 320: case 321: case 326: case 332: case 337: case 340: case 349: case 352: case 357: case 363: case 367: case 368: case 369: case 370: case 379: case 380: case 381: case 382: case 386: case 392: case 417: case 423: case 446: case 452: case 477: case 483: case 506: case 512: case 514: case 516: case 518: case 520: case 522: case 527: case 529: case 531: case 533: case 535: case 537:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesCoin.png"));
                                BalliesCoin bc = new BalliesCoin(i, j, image);
                                //objects.add(bc);
                                objects.set(i*colCount+j, bc);
                                coins.add(bc);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bcf = new BalliesFloor(i, j, image);
                                floors.add(bcf);
                                break;
                        //Door    
                        case 568:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesDoor.png"));
                                BalliesDoor bd = new BalliesDoor(i, j, image);
                                //objects.add(bd);
                                objects.set(i*colCount+j, bd);
                                break;
                        //Ballie
                        case 31:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\Ballie.png"));
                                ballie = new Ballie(i, j, image);
                                //objects.add(ballie);
                                objects.set(i*colCount+j, ballie);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor blf = new BalliesFloor(i, j, image);
                                floors.add(blf);
                                break;
                        default:
                    }
                   
                } catch (IOException ex) {
                    Logger.getLogger(JPPlayGround.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private void initialiseStage5(){
    	enemies.clear();
        coins.clear();
        floors.clear();
    	//objects.clear();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                try {
                    Image image;
                    switch(i*colCount+j){
                        //Wall
                        case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10: case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19: case 20: case 21: case 22: case 23: case 24: case 25: case 26: case 27: case 28: case 29: case 30: case 59: case 60: case 89: case 90: case 119: case 120: case 149: case 150: case 166: case 167: case 168: case 169: case 170: case 171: case 172: case 173: case 174: case 175: case 176: case 177: case 178: case 179: case 180: case 181: case 182: case 183: case 184: case 185: case 186: case 187: case 188: case 189: case 190: case 191: case 209: case 210: case 239: case 240: case 269: case 270: case 286: case 287: case 288: case 289: case 290: case 291: case 292: case 293: case 294: case 295: case 296: case 297: case 298: case 299: case 300: case 301: case 302: case 303: case 304: case 305: case 306: case 307: case 308: case 309: case 310: case 311: case 329: case 330: case 359: case 360: case 389: case 390: case 406: case 407: case 408: case 409: case 410: case 411: case 412: case 413: case 414: case 415: case 416: case 417: case 418: case 419: case 420: case 449: case 450: case 479: case 480: case 481: case 482: case 483: case 484: case 485: case 486: case 487: case 488: case 489: case 490: case 491: case 509: case 510: case 539: case 540: case 569: case 570: case 571: case 572: case 573: case 574: case 575: case 576: case 577: case 578: case 579: case 580: case 581: case 582: case 583: case 584: case 585: case 586: case 587: case 588: case 589: case 590: case 591: case 592: case 593: case 594: case 595: case 596: case 597: case 598: case 599:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesWall.png"));
                                BalliesWall bw = new BalliesWall(i, j, image);
                                //objects.add(bw);
                                objects.set(i*colCount+j, bw);
                                break;
                        //Enemy Horizontal
                        /*case 159: case 170: case 191: case 198: case 219: case 230: case 274: case 295: case 334: case 355: case 391: case 418: case 451: case 478: case 486: case 488: case 501: case 503: case 550: case 559:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesEnemy.png"));
                                BalliesEnemy bhe = new BalliesEnemy(i, j, BalliesEnemy.HORIZONTAL, i, j, image);
                                objects.add(bhe);
                                enemies.add(bhe);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bhef = new BalliesFloor(i, j, image);
                                floors.add(bhef);
                                break;*/
                        //Enemy Vertical
                        case 36: case 38: case 40: case 136: case 138: case 140: case 142: case 157: case 159: case 161: case 199: case 203: case 215: case 216: case 221: case 257: case 261: case 278: case 279: case 316: case 318: case 320: case 322: case 324: case 337: case 341: case 436: case 440: case 444: case 459: case 558: case 562:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesEnemy.png"));
                                BalliesEnemy bve = new BalliesEnemy(i, j, BalliesEnemy.VERTICAL, i, j, image);
                                //objects.add(bve);
                                objects.set(i*colCount+j, bve);
                                enemies.add(bve);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bvef = new BalliesFloor(i, j, image);
                                floors.add(bvef);
                                break;
                        //Floor
                        /*case 31:*/ case 32: case 34: case 35: case 37: case 39: case 41: case 42: case 43: case 44: case 45: case 46: case 47: case 48: case 49: case 50: case 51: case 52: case 53: case 54: case 61: case 62: case 64: case 65: case 66: case 67: case 68: case 69: case 70: case 71: case 72: case 74: case 75: case 76: case 77: case 78: case 79: case 80: case 81: case 82: case 83: case 84: case 86: case 87: case 96: case 97: case 98: case 99: case 100: case 101: case 102: case 103: case 104: case 105: case 106: case 107: case 108: case 109: case 110: case 111: case 112: case 113: case 114: case 116: case 117: case 121: case 122: case 124: case 125: case 126: case 127: case 128: case 129: case 130: case 131: case 132: case 133: case 135: case 137: case 139: case 141: case 143: case 144: case 151: case 152: case 154: case 155: case 156: case 158: case 160: case 162: case 163: case 164: case 165: case 192: case 194: case 195: case 196: case 197: case 198: case 200: case 201: case 202: case 204: case 214: case 217: case 218: case 219: case 220: case 222: case 223: case 224: case 225: case 226: case 227: case 228: case 229: case 230: case 231: case 232: case 233: case 234: case 244: case 245: case 246: case 247: case 248: case 249: case 250: case 251: case 252: case 253: case 255: case 256: case 258: case 259: case 260: case 262: case 263: case 264: case 274: case 275: case 276: case 277: case 280: case 281: case 282: case 283: case 284: case 285: case 312: case 314: case 315: case 317: case 319: case 321: case 323: case 325: case 331: case 332: case 333: case 334: case 335: case 336: case 338: case 339: case 340: case 342: case 343: case 344: case 345: case 346: case 347: case 348: case 349: case 350: case 351: case 352: case 353: case 354: case 355: case 361: case 365: case 366: case 367: case 368: case 369: case 370: case 371: case 372: case 373: case 375: case 376: case 377: case 378: case 379: case 380: case 381: case 382: case 383: case 384: case 385: case 391: case 395: case 396: case 397: case 398: case 399: case 400: case 401: case 402: case 403: case 404: case 405: case 421: case 425: case 426: case 427: case 428: case 429: case 430: case 431: case 432: case 434: case 435: case 437: case 438: case 439: case 441: case 442: case 443: case 445: case 446: case 448: case 451: case 452: case 453: case 454: case 455: case 456: case 457: case 458: case 460: case 461: case 462: case 463: case 464: case 465: case 466: case 467: case 468: case 469: case 470: case 471: case 472: case 473: case 474: case 475: case 476: case 478: case 492: case 493: case 495: case 496: case 497: case 498: case 499: case 500: case 501: case 502: case 503: case 504: case 505: case 506: case 508: /*case 511:*/ case 512: case 513: case 514: case 515: case 516: case 517: case 518: case 519: case 520: case 521: case 522: case 523: case 524: case 525: case 526: case 527: case 528: case 529: case 530: case 531: case 532: case 533: case 534: case 535: case 536: case 538: case 541: case 542: case 543: case 544: case 545: case 546: case 547: case 548: case 549: case 550: case 551: case 552: case 553: case 554: case 555: case 556: case 557: case 559: case 560: case 561: case 563: case 564: case 565: case 566: case 568:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bf = new BalliesFloor(i, j, image);
                                //objects.add(bf);
                                objects.set(i*colCount+j, bf);
                                floors.add(bf);
                                break;
                        //Coin
                        case 33: case 55: case 56: case 57: case 58: case 63: case 73: case 85: case 88: case 91: case 92: case 93: case 94: case 95: case 115: case 118: case 123: case 134: case 145: case 146: case 147: case 148: case 153: case 193: case 205: case 206: case 207: case 208: case 211: case 212: case 213: case 235: case 236: case 237: case 238: case 241: case 242: case 243: case 254: case 265: case 266: case 267: case 268: case 271: case 272: case 273: case 313: case 326: case 327: case 328: case 356: case 357: case 358: case 362: case 363: case 364: case 374: case 386: case 387: case 388: case 392: case 393: case 394: case 422: case 423: case 424: case 433: case 447: case 477: case 494: case 507: case 537: case 567:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesCoin.png"));
                                BalliesCoin bc = new BalliesCoin(i, j, image);
                                //objects.add(bc);
                                objects.set(i*colCount+j, bc);
                                coins.add(bc);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bcf = new BalliesFloor(i, j, image);
                                floors.add(bcf);
                                break;
                        //Door    
                        case 511:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesDoor.png"));
                                BalliesDoor bd = new BalliesDoor(i, j, image);
                                //objects.add(bd);
                                objects.set(i*colCount+j, bd);
                                break;
                        //Ballie
                        case 31:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\Ballie.png"));
                                ballie = new Ballie(i, j, image);
                                //objects.add(ballie);
                                objects.set(i*colCount+j, ballie);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor blf = new BalliesFloor(i, j, image);
                                floors.add(blf);
                                break;
                        default:
                    }
                   
                } catch (IOException ex) {
                    Logger.getLogger(JPPlayGround.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }    
    
    private void initialiseStage6(){
    	enemies.clear();
        coins.clear();
        floors.clear();
    	//objects.clear();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                try {
                    Image image;
                    switch(i*colCount+j){
                        //Wall
                        case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10: case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19: case 20: case 21: case 22: case 23: case 24: case 25: case 26: case 27: case 28: case 29: case 30: case 37: case 59: case 60: case 67: case 89: case 90: case 97: case 109: case 119: case 120: case 127: case 139: case 149: case 150: case 157: case 169: case 179: case 180: case 187: case 199: case 209: case 210: case 229: case 239: case 240: case 259: case 269: case 270: case 271: case 272: case 273: case 274: case 275: case 276: case 277: case 278: case 279: case 280: case 281: case 287: case 288: case 289: case 290: case 291: case 292: case 293: case 294: case 295: case 296: case 297: case 298: case 299: case 300: case 309: case 329: case 330: case 339: case 359: case 360: case 369: case 379: case 389: case 390: case 399: case 409: case 419: case 420: case 429: case 439: case 449: case 450: case 459: case 469: case 479: case 480: case 489: case 499: case 509: case 510: case 529: case 539: case 540: case 559: case 569: case 570: case 571: case 572: case 573: case 574: case 575: case 576: case 577: case 578: case 579: case 580: case 581: case 582: case 583: case 584: case 585: case 586: case 587: case 588: case 589: case 590: case 591: case 592: case 593: case 594: case 595: case 596: case 597: case 598: case 599:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesWall.png"));
                                BalliesWall bw = new BalliesWall(i, j, image);
                                //objects.add(bw);
                                objects.set(i*colCount+j, bw);
                                break;
                        //Enemy Horizontal
                        case 286: case 380: case 400: case 468: case 478:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesEnemy.png"));
                                BalliesEnemy bhe = new BalliesEnemy(i, j, BalliesEnemy.HORIZONTAL, i, j, image);
                                //objects.add(bhe);
                                objects.set(i*colCount+j, bhe);
                                enemies.add(bhe);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bhef = new BalliesFloor(i, j, image);
                                floors.add(bhef);
                                break;
                        //Enemy Vertical
                        case 38: case 50: case 246: case 249: case 258: case 307: case 310: case 318: case 548: case 560:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesEnemy.png"));
                                BalliesEnemy bve = new BalliesEnemy(i, j, BalliesEnemy.VERTICAL, i, j, image);
                                //objects.add(bve);
                                objects.set(i*colCount+j, bve);
                                enemies.add(bve);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bvef = new BalliesFloor(i, j, image);
                                floors.add(bvef);
                                break;
                        //Floor
                        /*case 31:*/ case 32: case 33: case 34: case 35: case 36: case 39: case 40: case 41: case 42: case 43: case 44: case 45: case 46: case 47: case 48: case 49: case 51: case 52: case 53: case 54: case 55: case 56: case 57: case 58: case 61: case 62: case 63: case 65: case 66: case 68: case 69: case 70: case 71: case 72: case 76: case 77: case 78: case 79: case 80: case 81: case 82: case 83: case 87: case 88: case 91: case 92: case 93: case 95: case 96: case 98: case 99: case 100: case 101: case 102: case 103: case 104: case 106: case 107: case 108: case 110: case 111: case 112: case 113: case 114: case 115: case 117: case 118: case 121: case 122: case 123: case 125: case 126: case 128: case 129: case 130: case 131: case 132: case 136: case 137: case 138: case 140: case 141: case 142: case 143: case 147: case 148: case 151: case 152: case 153: case 155: case 156: case 158: case 159: case 160: case 161: case 162: case 164: case 165: case 166: case 167: case 168: case 170: case 171: case 172: case 173: case 174: case 175: case 177: case 178: case 181: case 182: case 183: case 185: case 186: case 188: case 189: case 190: case 191: case 192: case 196: case 197: case 198: case 200: case 201: case 202: case 203: case 207: case 208: case 211: case 212: case 213: case 214: case 215: case 216: case 217: case 218: case 219: case 220: case 221: case 222: case 223: case 224: case 225: case 226: case 227: case 228: case 230: case 231: case 232: case 233: case 234: case 235: case 236: case 237: case 238: case 241: case 242: case 243: case 244: case 245: case 247: case 248: case 250: case 251: case 252: case 253: case 254: case 255: case 256: case 257: case 260: case 261: case 262: case 263: case 264: case 265: case 266: case 267: case 268: case 282: case 283: case 284: case 285: case 301: case 302: case 303: case 304: case 305: case 306: case 308: case 311: case 312: case 313: case 314: case 315: case 316: case 317: case 319: case 320: case 321: case 322: case 323: case 324: case 325: case 326: case 327: case 328: case 331: case 332: case 333: case 334: case 335: case 336: case 337: case 338: case 340: case 341: case 342: case 343: case 344: case 345: case 346: case 347: case 348: case 349: case 350: case 351: case 352: case 353: case 354: case 355: case 356: case 357: case 358: case 361: case 362: case 363: case 365: case 366: case 367: case 368: case 370: case 371: case 372: case 376: case 377: case 378: case 381: case 382: case 383: case 387: case 388: case 391: case 392: case 395: case 396: case 397: case 398: case 401: case 402: case 404: case 405: case 406: case 407: case 408: case 410: case 411: case 412: case 413: case 415: case 416: case 417: case 418: case 421: case 423: case 425: case 426: case 427: case 428: case 430: case 431: case 432: case 436: case 437: case 438: case 440: case 441: case 442: case 443: case 447: case 448: case 451: case 456: case 457: case 458: case 460: case 461: case 462: case 463: case 464: case 466: case 467: case 470: case 471: case 472: case 473: case 475: case 477: case 481: case 482: case 483: case 485: case 486: case 487: case 488: case 490: case 491: case 492: case 496: case 497: case 498: case 500: case 501: case 502: case 503: case 507: case 508: case 511: case 512: case 513: case 514: case 515: case 516: case 517: case 518: case 519: case 520: case 521: case 522: case 523: case 524: case 525: case 526: case 527: case 528: case 530: case 531: case 532: case 533: case 534: case 535: case 536: case 537: case 538: case 541: case 542: case 543: case 544: case 545: case 546: case 547: case 549: case 550: case 551: case 552: case 553: case 554: case 555: case 556: case 557: case 558: case 561: case 562: case 563: case 564: case 565: case 566: case 567: /*case 568:*/
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bf = new BalliesFloor(i, j, image);
                                //objects.add(bf);
                                objects.set(i*colCount+j, bf);
                                floors.add(bf);
                                break;
                        //Coin
                        case 64: case 73: case 74: case 75: case 84: case 85: case 86: case 94: case 105: case 116: case 124: case 133: case 134: case 135: case 144: case 145: case 146: case 154: case 163: case 176: case 184: case 193: case 194: case 195: case 204: case 205: case 206: case 364: case 373: case 374: case 375: case 384: case 385: case 386: case 393: case 394: case 403: case 414: case 422: case 424: case 433: case 434: case 435: case 444: case 445: case 446: case 452: case 453: case 454: case 455: case 465: case 474: case 476: case 484: case 493: case 494: case 495: case 504: case 505: case 506:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesCoin.png"));
                                BalliesCoin bc = new BalliesCoin(i, j, image);
                                //objects.add(bc);
                                objects.set(i*colCount+j, bc);
                                coins.add(bc);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor bcf = new BalliesFloor(i, j, image);
                                floors.add(bcf);
                                break;
                        //Door    
                        case 568:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesDoor.png"));
                                BalliesDoor bd = new BalliesDoor(i, j, image);
                                //objects.add(bd);
                                objects.set(i*colCount+j, bd);
                                break;
                        //Ballie
                        case 31:
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\Ballie.png"));
                                ballie = new Ballie(i, j, image);
                                //objects.add(ballie);
                                objects.set(i*colCount+j, ballie);
                                
                                image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                                BalliesFloor blf = new BalliesFloor(i, j, image);
                                floors.add(blf);
                                break;
                        default:
                    }
                   
                } catch (IOException ex) {
                    Logger.getLogger(JPPlayGround.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void animate() {

        class RunBack extends Thread {

            RunBack() {
                setDaemon(true);
                start();
            }

            public void run() {
                while (true) {
                	if(Playing){

                		try {
                        	Thread.sleep(200);
                    	} catch (InterruptedException e) {
                    	}
                        
                    	try{
                    	
                    
                    	//Repaints Floor
                    	for (Iterator it = floors.iterator(); it.hasNext();) {
                        	BalliesFloor obj = (BalliesFloor) it.next();
                        	jt.setValueAt(obj, obj.getRow(), obj.getCol());
                    	}
                    
                    	//Repaints Coin
                    	for (Iterator it = coins.iterator(); it.hasNext();) {
                        	BalliesCoin obj = (BalliesCoin) it.next();
                        	jt.setValueAt(obj, obj.getRow(), obj.getCol());
                    	}
                    
                    	boolean enemyColide=false;
                    	//Repaints Enemy after move
                    	for (Iterator it = enemies.iterator(); it.hasNext();) {
                        	BalliesEnemy obj = (BalliesEnemy) it.next();
                        	if(moveEnemy(obj)){
                        		enemyColide=true;                        	
                            	break;                             
                        	}
                        
                    	}   
                    
                                       
                    	if(enemyColide && Playing){
                    		Playing=false;
                    		endGame(FAILED);
                    	}else{
                    		//Repaints Ballie
                        	jt.setValueAt(ballie, ballie.getRow(), ballie.getCol());
                    	}
                    
                    	}catch(Exception e){System.out.println("Thread "+e+" "+objects.size()+" "+enemies.size());}
                    
                	}else{
                		System.out.println("Game Paused for new Stage!!!");	
                	}
                	
                }                
            }
        }
        RunBack runBack = new RunBack();

    }

	private void initialisePlayGround() {

		
		  for (int i = 0; i < rowCount; i++) { 
			  for (int j = 0; j < colCount;j++) { 
				  try { 
					  Image image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
					  objects.add(new ImageIcon(image)); 
					  } 
				  catch (IOException ex) {Logger.getLogger(JPPlayGround.class.getName()).log(Level.SEVERE,null, ex); } 
			  } 
		  }
		stageNo=0;
		startGame();
		animate();

		TableModel tm = new AbstractTableModel() {

			@Override
			public int getRowCount() {
				return rowCount;
			}

			@Override
			public int getColumnCount() {
				return colCount;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				return objects.get(rowIndex * colCount + columnIndex);
			}

			@Override
			public Class getColumnClass(int c) {
				return ImageIcon.class;
			}

			public void setValueAt(Object value, int row, int col) {
				objects.set(row * colCount + col, value);
				fireTableCellUpdated(row, col);
			}
		};

		jt = new JTable(tm);
		this.add(jt);
		this.setDoubleBuffered(true);
				

		//jt.setFocusable(false);
		
		
		jt.setRowMargin(0);
		jt.getColumnModel().setColumnMargin(0);
		jt.setRowHeight(30);
	
		
		for (int i = 0; i < colCount; i++) {
			jt.getColumnModel().getColumn(i).setWidth(30);
		}

		jt.setBounds(0, 0, colCount * 30, rowCount * 30);

		jt.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				try{
				if (Playing) {
					
					if (e.getKeyCode() == 37) {// Left
						Object obj = jt.getValueAt(ballie.getRow(),
								ballie.getCol() - 1);
						if (BalliesWall.class.isInstance(obj)) {
							// Dont move on Wall
						} else if (BalliesEnemy.class.isInstance(obj)) {
							// Die here
							ballie.setCol(ballie.getCol() - 1);
							// /////////////////////////////////
							Playing=false;
							endGame(FAILED);
							// /////////////////////////////////
						} else if (BalliesCoin.class.isInstance(obj)) {
							try {
								// Remove Coin replace with floor and move
								BalliesCoin bc = (BalliesCoin) obj;
								coins.remove(bc);

								Image image = ImageIO.read(new File(System
										.getProperty("user.dir")
										+ "\\Images\\BalliesFloor.png"));
								BalliesFloor bf = new BalliesFloor(bc.getRow(),
										bc.getCol(), image);
								jt.setValueAt(bf, bc.getRow(), bc.getCol());

								ballie.setCol(ballie.getCol() - 1);
								
								coinCount++;

							} catch (IOException ex) {
								Logger.getLogger(JPPlayGround.class.getName())
										.log(Level.SEVERE, null, ex);
							}

						} else if (BalliesDoor.class.isInstance(obj)) {
							// Game Ends No Movement clear to next stage
							// ///////////////////////////////////////
							Playing=false;
							endGame(SUCCESS);
							// ///////////////////////////////////////
						} else if (BalliesFloor.class.isInstance(obj)) {
							// Move on
							ballie.setCol(ballie.getCol() - 1);
						}
					} else if (e.getKeyCode() == 38) {// Up
						Object obj = jt.getValueAt(ballie.getRow() - 1,
								ballie.getCol());
						if (BalliesWall.class.isInstance(obj)) {
							// Dont move on Wall
						} else if (BalliesEnemy.class.isInstance(obj)) {
							// Die here
							ballie.setRow(ballie.getRow() - 1);
							// /////////////////////////////////
							Playing=false;
							endGame(FAILED);
							// /////////////////////////////////
						} else if (BalliesCoin.class.isInstance(obj)) {
							try {
								// Remove Coin replace with floor and move
								BalliesCoin bc = (BalliesCoin) obj;
								coins.remove(bc);

								Image image = ImageIO.read(new File(System
										.getProperty("user.dir")
										+ "\\Images\\BalliesFloor.png"));
								BalliesFloor bf = new BalliesFloor(bc.getRow(),
										bc.getCol(), image);
								jt.setValueAt(bf, bc.getRow(), bc.getCol());

								ballie.setRow(ballie.getRow() - 1);
								
								coinCount++;

							} catch (IOException ex) {
								Logger.getLogger(JPPlayGround.class.getName())
										.log(Level.SEVERE, null, ex);
							}

						} else if (BalliesDoor.class.isInstance(obj)) {
							// Game Ends No Movement clear to next stage
							// ///////////////////////////////////////
							Playing=false;
							endGame(SUCCESS);
							// ///////////////////////////////////////
						} else if (BalliesFloor.class.isInstance(obj)) {
							// Move on
							ballie.setRow(ballie.getRow() - 1);
						}
					} else if (e.getKeyCode() == 39) {// Right
						Object obj = jt.getValueAt(ballie.getRow(),
								ballie.getCol() + 1);
						if (BalliesWall.class.isInstance(obj)) {
							// Dont move on Wall
						} else if (BalliesEnemy.class.isInstance(obj)) {
							// Die here
							ballie.setCol(ballie.getCol() + 1);
							// /////////////////////////////////
							Playing=false;
							endGame(FAILED);
							// /////////////////////////////////
						} else if (BalliesCoin.class.isInstance(obj)) {
							try {
								// Remove Coin replace with floor and move
								BalliesCoin bc = (BalliesCoin) obj;
								coins.remove(bc);

								Image image = ImageIO.read(new File(System
										.getProperty("user.dir")
										+ "\\Images\\BalliesFloor.png"));
								BalliesFloor bf = new BalliesFloor(bc.getRow(),
										bc.getCol(), image);
								jt.setValueAt(bf, bc.getRow(), bc.getCol());

								ballie.setCol(ballie.getCol() + 1);
								
								coinCount++;

							} catch (IOException ex) {
								Logger.getLogger(JPPlayGround.class.getName())
										.log(Level.SEVERE, null, ex);
							}

						} else if (BalliesDoor.class.isInstance(obj)) {
							// Game Ends No Movement clear to next stage
							// ///////////////////////////////////////
							Playing=false;
							endGame(SUCCESS);
							// ///////////////////////////////////////
						} else if (BalliesFloor.class.isInstance(obj)) {
							// Move on
							ballie.setCol(ballie.getCol() + 1);
						}

					} else if (e.getKeyCode() == 40) {// Down
						Object obj = jt.getValueAt(ballie.getRow() + 1,
								ballie.getCol());
						if (BalliesWall.class.isInstance(obj)) {
							// Dont move on Wall
						} else if (BalliesEnemy.class.isInstance(obj)) {
							// Die here
							ballie.setRow(ballie.getRow() + 1);
							// /////////////////////////////////
							Playing=false;
							endGame(FAILED);
							// /////////////////////////////////
						} else if (BalliesCoin.class.isInstance(obj)) {
							try {
								// Remove Coin replace with floor and move
								BalliesCoin bc = (BalliesCoin) obj;
								coins.remove(bc);

								Image image = ImageIO.read(new File(System
										.getProperty("user.dir")
										+ "\\Images\\BalliesFloor.png"));
								BalliesFloor bf = new BalliesFloor(bc.getRow(),
										bc.getCol(), image);
								jt.setValueAt(bf, bc.getRow(), bc.getCol());

								ballie.setRow(ballie.getRow() + 1);
								
								coinCount++;

							} catch (IOException ex) {
								Logger.getLogger(JPPlayGround.class.getName())
										.log(Level.SEVERE, null, ex);
							}

						} else if (BalliesDoor.class.isInstance(obj)) {
							// Game Ends No Movement clear to next stage
							// ///////////////////////////////////////
							Playing=false;
							endGame(SUCCESS);
							// ///////////////////////////////////////
						} else if (BalliesFloor.class.isInstance(obj)) {
							// Move on
							ballie.setRow(ballie.getRow() + 1);
						}
					} else if (e.getKeyCode() == 76) {// L for Load
						Playing=false;
						JFrame jf = new JFrame("Load Game");
						JPanel jp = new JPanel();
						
						loadGame();
						int i=1;
						for (Object  str : savedGames) {
							StringTokenizer stk = new StringTokenizer(str+"", ":");
							String sCoinCount= stk.nextToken();
							String sLevel= stk.nextToken();
							JButton jb = new JButton(i+". "+getGameLevelAndCoinCount(i-1));
							jp.add(jb);
							jb.addKeyListener(new KeyAdapter(){@Override
							public void keyPressed(KeyEvent ke) {
								// TODO Auto-generated method stub
								if(ke.getKeyCode()==127){
									JButton jjb= (JButton) ke.getSource();
									
									String ss=jjb.getText();
									JPanel j=(JPanel) jjb.getParent();
									j.remove(jjb);
									j.repaint();
									removeGame(Integer.parseInt(new StringTokenizer(ss, ".").nextToken())-1);									
								}else if(ke.getKeyCode()==10){
									JButton jjb= (JButton) ke.getSource();
									String ss=jjb.getText();
									JPanel j=(JPanel) jjb.getParent();
									//JFrame jfr =(JFrame) j.getParent();
									//jfr.dispose();
									
									selectGame(Integer.parseInt(new StringTokenizer(ss, ".").nextToken())-1);
								}
							}
								
							});
							
							i++;
						}
						jf.setContentPane(jp);
						jf.setSize(700, 600);
						jf.setVisible(true);
						
					} else if (e.getKeyCode() == 83) {// S for Save						
						saveGame();			
						JOptionPane.showMessageDialog(null, "Successfully Saved !!!", "Saved!", JOptionPane.INFORMATION_MESSAGE);
					} else {
						
					}
						
				}
					
				}catch(Exception exx){System.out.println("keyPress"+exx+" "+objects.size()+" "+ enemies.size() );}

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});

		//jt.setDefaultRenderer(ImageIcon.class,new BorderLessTableCellRenderer());
		jt.setDefaultRenderer(ImageIcon.class, 
			    new ProxyCellRenderer(jt.getDefaultRenderer(ImageIcon.class)));
				
	}
	
	private String getGameLevelAndCoinCount(int index){
		int i=0;		
		String st=(String) savedGames.get(index);		
		StringTokenizer stk = new StringTokenizer(st, ";");
		String returnV="";
		
		while(stk.hasMoreElements()){
			String el=(String) stk.nextElement();
			StringTokenizer sz= new StringTokenizer(el,":");
			i=Integer.parseInt(sz.nextToken());
			String classN=sz.nextToken();
			
			if(!(classN.equals("BalliesPackage.Ballie") || classN.equals("BalliesPackage.BalliesDoor") || classN.equals("BalliesPackage.BalliesCoin") || classN.equals("BalliesPackage.BalliesEnemyH") || classN.equals("BalliesPackage.BalliesWall") || classN.equals("BalliesPackage.BalliesFloor") || classN.equals("BalliesPackage.BalliesEnemyV"))){
				//Count Count and stage No
				coinCount=i;
				stageNo=Integer.parseInt(classN);
				returnV="Level "+Integer.parseInt(classN)+" Coin "+i*10;
				break;
			}
						
		}		
		return returnV;
	}
	
	private void selectGame(int index){
		int i=0;		
		String st=(String) savedGames.get(index);		
		StringTokenizer stk = new StringTokenizer(st, ";");
		
		enemies.clear();
        coins.clear();
        floors.clear();    	
        
		while(stk.hasMoreElements()){
			String el=(String) stk.nextElement();
			StringTokenizer sz= new StringTokenizer(el,":");
			i=Integer.parseInt(sz.nextToken());
			String classN=sz.nextToken();
			
			if(classN.equals("BalliesPackage.BalliesWall")){
				Image image;
				try {
					image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesWall.png"));
					BalliesWall bw = new BalliesWall(i/colCount, i%colCount, image);                
	                objects.set(i, bw);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                                
			}else if(classN.equals("BalliesPackage.BalliesFloor")){
				Image image;
				try {
					image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                    BalliesFloor bf = new BalliesFloor(i/colCount, i%colCount, image);                   
                    objects.set(i, bf);
                    floors.add(bf);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(classN.equals("BalliesPackage.BalliesEnemyV")){
				Image image;
				try {
					image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesEnemy.png"));
                    BalliesEnemy bve = new BalliesEnemy(i/colCount, i%colCount, BalliesEnemy.VERTICAL, i/colCount, i%colCount, image);
                    objects.set(i, bve);
                    enemies.add(bve);
                    
                    image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                    BalliesFloor bvef = new BalliesFloor(i/colCount, i%colCount, image);
                    floors.add(bvef);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(classN.equals("BalliesPackage.BalliesEnemyH")){
				Image image;
				try {
					image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesEnemy.png"));
                    BalliesEnemy bhe = new BalliesEnemy(i/colCount, i%colCount, BalliesEnemy.HORIZONTAL, i/colCount, i%colCount, image);
                    objects.set(i, bhe);
                    enemies.add(bhe);
                    
                    image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                    BalliesFloor bhef = new BalliesFloor(i/colCount, i%colCount, image);
                    floors.add(bhef);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(classN.equals("BalliesPackage.BalliesCoin")){
				Image image;
				try {
					image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesCoin.png"));
                    BalliesCoin bc = new BalliesCoin(i/colCount, i%colCount, image);
                    objects.set(i, bc);
                    coins.add(bc);
                    
                    image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                    BalliesFloor bcf = new BalliesFloor(i/colCount, i%colCount, image);
                    floors.add(bcf);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(classN.equals("BalliesPackage.BalliesDoor")){
				Image image;
				try {
					image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesDoor.png"));
                    BalliesDoor bd = new BalliesDoor(i/colCount, i%colCount, image);
                    objects.set(i, bd);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(classN.equals("BalliesPackage.Ballie")){
				Image image;
				try {
					image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\Ballie.png"));
                    ballie = new Ballie(i/colCount, i%colCount, image);
                    objects.set(i, ballie);
                    
                    image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                    BalliesFloor blf = new BalliesFloor(i/colCount, i%colCount, image);
                    floors.add(blf);                    
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				//Count Count and stage No
				coinCount=i;
				stageNo=Integer.parseInt(classN);
			}
						
		}
		//startGame();
		this.repaint();
        Playing=true;
		
	}
	
	private void removeGame(int index){		
		FileInputStream fis = null;
        try {
            
            Properties prop = new Properties();
            File f = new File("BalliesAdventure.properties");
            if (!f.exists()) {
                try {
                    f.createNewFile();
                } catch (IOException ex) {
                    System.out.println(ex.toString());                    
                }
            }
            fis = new FileInputStream(f);
            prop.load(fis);
            
            
            prop.remove(savedGamesKey.get(index));
            savedGamesKey.remove(index);
            savedGames.remove(index);
            FileOutputStream fos = new FileOutputStream(new File("BalliesAdventure.properties"));
            prop.store(fos, "BalliesAdventure.properties");
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
            }
        }
	}
	
	private void saveGame(){
		FileInputStream fis = null;
        try {
            
            Properties prop = new Properties();
            File f = new File("BalliesAdventure.properties");
            if (!f.exists()) {
                try {
                    f.createNewFile();
                } catch (IOException ex) {
                    System.out.println(ex.toString());                    
                }
            }
            fis = new FileInputStream(f);
            prop.load(fis);
            
            String sValue="";
            String classN="";
            for(int i=0;i<objects.size();i++){
            	if(objects.get(i).getClass().getName().equals("BalliesPackage.BalliesEnemy")){
            		BalliesEnemy be=(BalliesEnemy) objects.get(i);
            		classN=(be.getDirection()==BalliesEnemy.HORIZONTAL)?"BalliesPackage.BalliesEnemyH":"BalliesPackage.BalliesEnemyV";
            	}else{
            		classN=objects.get(i).getClass().getName();
            	}
            	sValue+=i+":"+classN+";";
            }
            sValue+=coinCount+":"+stageNo+";";
            String milli =System.currentTimeMillis()+"";
            prop.setProperty(milli, sValue);
            savedGames.add(sValue);
            savedGamesKey.add(milli);
            FileOutputStream fos = new FileOutputStream(new File("BalliesAdventure.properties"));
            prop.store(fos, "BalliesAdventure.properties");
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
            }
        }
	}
	
	private void loadGame(){
		FileInputStream fis = null;
        try {
            
            Properties prop = new Properties();
            File f = new File("BalliesAdventure.properties");
            if (!f.exists()) {
                try {
                    f.createNewFile();
                } catch (IOException ex) {
                    System.out.println(ex.toString());                   
                }
            }
            fis = new FileInputStream(f);
            prop.load(fis);

            Set<String> keys=prop.stringPropertyNames();
            savedGames.clear();
            savedGamesKey.clear();
            for (String str : keys) {
            	savedGames.add(prop.getProperty(str));
            	savedGamesKey.add(str);
			}
            
            
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
	}
	
    private boolean moveEnemy(BalliesEnemy be){
        
    	/*
        Ballie was caught by Enemy
        */
        if(be.getRow()==ballie.getRow() && be.getCol()==ballie.getCol()){             
            return true;
        }
        
        if(be.getDirection()==BalliesEnemy.HORIZONTAL){            
            
            if(be.getLastCol()<be.getCol()){//Move right if there is no Wall or Door
            
                be.setLastCol(be.getCol());                
                //Since the border is covered with walls, expecting the enemy not to reach border
                Object obj=jt.getValueAt(be.getRow(), be.getCol()+1);
                if(BalliesWall.class.isInstance(obj)||BalliesDoor.class.isInstance(obj)){                    
                    be.setCol(be.getCol()-1);
                    jt.setValueAt(be, be.getRow(), be.getCol());
                }else{                    
                    be.setCol(be.getCol()+1);
                    jt.setValueAt(be, be.getRow(), be.getCol());
                }
                
            }else if(be.getLastCol()>be.getCol()){//Move left if there is no Wall or Door
                be.setLastCol(be.getCol());
            
                //Since the border is covered with walls, expecting the enemy not to reach border
                Object obj=jt.getValueAt(be.getRow(), be.getCol()-1);
                if(BalliesWall.class.isInstance(obj)||BalliesDoor.class.isInstance(obj)){
                    be.setCol(be.getCol()+1);
                    jt.setValueAt(be, be.getRow(), be.getCol());
                }else{
                    be.setCol(be.getCol()-1);
                    jt.setValueAt(be, be.getRow(), be.getCol());
                }
                
            }else{//Starting
            
                be.setLastCol(be.getCol());
                
                //Since the border is covered with walls, expecting the enemy not to reach border
                Object obj=jt.getValueAt(be.getRow(), be.getCol()+1);
                if(BalliesWall.class.isInstance(obj)||BalliesDoor.class.isInstance(obj)){
                    be.setCol(be.getCol()-1);
                    jt.setValueAt(be, be.getRow(), be.getCol());
                }else{
                    be.setCol(be.getCol()+1);
                    jt.setValueAt(be, be.getRow(), be.getCol());
                }
            }
                        
        }else{
            if(be.getLastRow()<be.getRow()){//Move down if there is no Wall or Door
            
                be.setLastRow(be.getRow());                
                //Since the border is covered with walls, expecting the enemy not to reach border
                Object obj=jt.getValueAt(be.getRow()+1, be.getCol());
                if(BalliesWall.class.isInstance(obj)||BalliesDoor.class.isInstance(obj)){                    
                    be.setRow(be.getRow()-1);
                    jt.setValueAt(be, be.getRow(), be.getCol());
                }else{                    
                    be.setRow(be.getRow()+1);
                    jt.setValueAt(be, be.getRow(), be.getCol());
                }
                
            }else if(be.getLastRow()>be.getRow()){//Move up if there is no Wall or Door
                be.setLastRow(be.getRow());
            
                //Since the border is covered with walls, expecting the enemy not to reach border
                Object obj=jt.getValueAt(be.getRow()-1, be.getCol());
                if(BalliesWall.class.isInstance(obj)||BalliesDoor.class.isInstance(obj)){
                    be.setRow(be.getRow()+1);
                    jt.setValueAt(be, be.getRow(), be.getCol());
                }else{
                    be.setRow(be.getRow()-1);
                    jt.setValueAt(be, be.getRow(), be.getCol());
                }
                
            }else{//Starting
            
                be.setLastRow(be.getRow());
                
                //Since the border is covered with walls, expecting the enemy not to reach border
                Object obj=jt.getValueAt(be.getRow()+1, be.getCol());
                if(BalliesWall.class.isInstance(obj)||BalliesDoor.class.isInstance(obj)){
                    be.setRow(be.getRow()-1);
                    jt.setValueAt(be, be.getRow(), be.getCol());
                }else{
                    be.setRow(be.getRow()+1);
                    jt.setValueAt(be, be.getRow(), be.getCol());
                }
            }
        }
         
         /*
         Ballie was caught by Enemy
         */
         if(be.getRow()==ballie.getRow() && be.getCol()==ballie.getCol()){             
             return true;
         }else{
             return false;
         }
         
    
    }
    
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 824, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    public static void main(String arg[]) {
        JFrame jf = new JFrame("Ballie's Adventure");

        JPPlayGround jpg = new JPPlayGround();

        jf.setContentPane(jpg);

        //jf.pack();
        jf.setSize(906, 628);
        jf.setVisible(true);
        jf.setResizable(false);         
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
