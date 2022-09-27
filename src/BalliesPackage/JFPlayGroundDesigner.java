/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BalliesPackage;

import static BalliesPackage.JPPlayGround.objects;
import static BalliesPackage.JPPlayGround.rowCount;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Sely
 */
public class JFPlayGroundDesigner extends javax.swing.JPanel {

    /**
     * Creates new form JFPlayGroundDesigner
     */
    static int rowCount = 20;
    static int colCount = 30;
    static ArrayList rowData = new ArrayList();

    JScrollPane jp = new JScrollPane();

    public JFPlayGroundDesigner() {
        initialise();
    }

    private void initialise() {

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                try {
                    Image image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                    BalliesFloor bf = new BalliesFloor(i, j, image);
                    rowData.add(bf);
                } catch (IOException ex) {
                    Logger.getLogger(JPPlayGround.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        this.setLayout(new BorderLayout());
        TableModel tm = new AbstractTableModel() {

            public int getRowCount() {
                return rowCount;
            }

            public int getColumnCount() {
                return colCount;
            }

            public Object getValueAt(int row, int col) {
                return rowData.get(row * colCount + col);
            }

            public boolean isCellEditable(int row, int col) {
                return false;
            }

            public void setValueAt(Object value, int row, int col) {
                rowData.set(row * colCount + col, value);
                fireTableCellUpdated(row, col);
            }

            @Override
            public Class getColumnClass(int c) {
                return ImageIcon.class;
            }
        };

        JTable jt = new JTable(tm);

        jt.setRowMargin(0);
        jt.getColumnModel().setColumnMargin(0);
        jt.setRowHeight(30);

        for (int i = 0; i < colCount; i++) {
            jt.getColumnModel().getColumn(i).setWidth(30);
        }

        jt.setBounds(0, 0, colCount * 30, rowCount * 30);
        jp.add(jt);

        jt.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 86) {//Vertical Enemy
                    JTable jt = (JTable) e.getSource();
                    try {
                        Image image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesEnemy.png"));
                        BalliesEnemy be = new BalliesEnemy(jt.getSelectedRow(), jt.getSelectedColumn(), BalliesEnemy.VERTICAL, jt.getSelectedRow(), jt.getSelectedColumn(), image);
                        jt.setValueAt(be, jt.getSelectedRow(), jt.getSelectedColumn());
                    } catch (IOException ex) {
                        Logger.getLogger(JFPlayGroundDesigner.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (e.getKeyCode() == 72) {//Horizontal Enemy
                    JTable jt = (JTable) e.getSource();
                    try {
                        Image image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesEnemy.png"));
                        BalliesEnemy be = new BalliesEnemy(jt.getSelectedRow(), jt.getSelectedColumn(), BalliesEnemy.HORIZONTAL, jt.getSelectedRow(), jt.getSelectedColumn(), image);
                        jt.setValueAt(be, jt.getSelectedRow(), jt.getSelectedColumn());
                    } catch (IOException ex) {
                        Logger.getLogger(JFPlayGroundDesigner.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (e.getKeyCode() == 67) {//Coin
                    JTable jt = (JTable) e.getSource();
                    try {
                        Image image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesCoin.png"));
                        BalliesCoin bc = new BalliesCoin(jt.getSelectedRow(), jt.getSelectedColumn(), image);
                        jt.setValueAt(bc, jt.getSelectedRow(), jt.getSelectedColumn());
                    } catch (IOException ex) {
                        Logger.getLogger(JFPlayGroundDesigner.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else if (e.getKeyCode() == 87) {//Wall
                    JTable jt = (JTable) e.getSource();
                    try {
                        Image image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesWall.png"));
                        BalliesWall bw = new BalliesWall(jt.getSelectedRow(), jt.getSelectedColumn(), image);
                        jt.setValueAt(bw, jt.getSelectedRow(), jt.getSelectedColumn());
                    } catch (IOException ex) {
                        Logger.getLogger(JFPlayGroundDesigner.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                } else if (e.getKeyCode() == 70) {//Floor
                    JTable jt = (JTable) e.getSource();
                    try {
                        Image image = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\BalliesFloor.png"));
                        BalliesFloor bf = new BalliesFloor(jt.getSelectedRow(), jt.getSelectedColumn(), image);
                        jt.setValueAt(bf, jt.getSelectedRow(), jt.getSelectedColumn());
                    } catch (IOException ex) {
                        Logger.getLogger(JFPlayGroundDesigner.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (e.getKeyCode() == 83) {//Save
                    
                    FileInputStream fis = null;
                    try {
                        String enemyV = "";
                        String enemyH = "";
                        String coin = "";
                        String wall = "";
                        String floor = "";
                        for (int i = 0; i < rowCount; i++) {
                            for (int j = 0; j < colCount; j++) {
                                JTable jt = (JTable) e.getSource();
                                if (jt.getValueAt(i, j).getClass() == BalliesEnemy.class) {
                                    BalliesEnemy be= (BalliesEnemy) jt.getValueAt(i, j);
                                    if(be.getDirection()==BalliesEnemy.HORIZONTAL){
                                        enemyH = enemyH + "case " + ((i * colCount + j)) + ": ";
                                    }else{
                                        enemyV = enemyV + "case " + ((i * colCount + j)) + ": ";
                                    }                                                                            
                                } else if (jt.getValueAt(i, j).getClass() == BalliesCoin.class) {
                                    coin = coin + "case " + ((i * colCount + j)) + ": ";
                                } else if (jt.getValueAt(i, j).getClass() == BalliesWall.class) {
                                    wall = wall + "case " + ((i * colCount + j)) + ": ";
                                } else if (jt.getValueAt(i, j).getClass() == BalliesFloor.class) {
                                    floor = floor + "case " + ((i * colCount + j)) + ": ";
                                }
                            }
                        }
                        Properties prop = new Properties();
                        File f = new File("design.txt");
                        if (!f.exists()) {
                            try {
                                f.createNewFile();
                            } catch (IOException ex) {
                                System.out.println(ex.toString());

                            }
                        }
                        fis = new FileInputStream(f);
                        try {
                            prop.load(fis);
                        } catch (IOException ex) {
                            Logger.getLogger(JFPlayGroundDesigner.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        prop.setProperty("enemy horizontal", enemyH);
                        prop.setProperty("enemy vertical", enemyV);
                        prop.setProperty("coin", coin);
                        prop.setProperty("wall", wall);
                        prop.setProperty("floor", floor);
                        FileOutputStream fos = new FileOutputStream(new File("design.txt"));
                        try {
                            prop.store(fos, "Data For Designing Stages in Ballie's Adventure");
                        } catch (IOException ex) {
                            Logger.getLogger(JFPlayGroundDesigner.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(JFPlayGroundDesigner.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            fis.close();
                        } catch (IOException ex) {
                            Logger.getLogger(JFPlayGroundDesigner.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                    
                
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });        

        JButton jb = new JButton("Get Code");
        jb.setBounds(0, 0, 100, 30);
        jb.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("clicked");
            }

        });

        this.add(jp);
        this.repaint();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        JFrame jf = new JFrame("Ballie's Adventure Design");

        JFPlayGroundDesigner jpg = new JFPlayGroundDesigner();

        jf.setContentPane(jpg);

        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(700, 600);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
