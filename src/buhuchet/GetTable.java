package buhuchet;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.lang.*;
import javax.swing.text.*;

public class GetTable {
    Buhuchet       signin;
    ParamsTables   zpt;
    JTable         table;
    TableModel     dataModel;
    JTextComponent[][] rows;
    int            mBottom,mLeft,print;
    String         dbn;
    SetCells       setCells;
    KeyF8          keyF8;

    public GetTable(Buhuchet signinx,ParamsTables z,int printx) {
        signin=signinx; zpt=z; dbn=zpt.dbName; print=printx; signin.printxyz=printx;
        setCells=new SetCells(signin,zpt); rows=setCells.getCells(); if (rows==null) return;
        String s=dbn.substring(0,2);
        if (s.equals("tx")&&signin.edit>0) {
            SetSQL xyz=new SetSQL(signin,zpt,rows);
            if (dbn.equals("tx0")) rows=xyz.setRows(); else {rows=xyz.setRows(dbn); setCells.otchets(rows);}
        }
        keyF8=new KeyF8(signin,zpt,rows);
        dataModel=new AbstractTableModel() {
            public int getColumnCount() {return zpt.colLength;}
            public int getRowCount() {return rows.length;}
            public Object getValueAt(int row,int col) {return rows[row][col];}
            public Class getColumnClass(int col) {return getValueAt(0, col).getClass();}
            public boolean isCellEditable(int row,int col) {

if (signin.cregion>0&&signin.region[0][0].equals("0000")) return true;

                String[] xtxzpt=zpt.dbName.split("_");
                if (xtxzpt[0].equals("users")&&col<2) return false;
                if (xtxzpt[0].equals("users")&&col>=2) return true;
                if (dbn.equals("podpis")||dbn.equals("podpis2")) return true;
                if (print==1) return false;
                if (signin.zadach==1) {
                    String s="";
                    if (signin.edit>1) {
                        s=zpt.dbName.substring(0,3);
                        if (s.equals("xtx")) {if (row>zpt.rowEdit&&col>zpt.colEdit) return true; else return false;}
                    }
                    if (signin.region[0][0].equals(signin.region[0][signin.cregion])&&signin.uID<3) return false;
                    s=zpt.dbName.substring(0,3);
                    if (s.equals("xt6")&&col==4) return false;
                    if (s.equals("xt6")&&col==5) return false;
                    if (s.equals("xt2")&&row==0&&setCells.ident==1) return false;
                    if (s.equals("xt2")&&row==1&&setCells.ident==1) return false;
                    if (s.equals("xt2")) if (row==2||row==6||row==12) return false;
                    if (s.equals("xt2")) if (row==41) return false;
                    if (s.equals("xt2")) if (row==9) return false;
                    if (s.equals("xt2")) if (row==48||row==49) return false;
                    if (s.equals("xt2")) if (row==25) return false;
                    if (s.equals("xt3")&&row==0) return false;
                    if (s.equals("xt9")&&col>3) {if (row==16||row==17) return false;}
            //        return true;
                    if (col<zpt.colEdit+1) return false; else return true;
                }
                String s="";
                if (signin.edit>1) {
                    s=zpt.dbName.substring(0,3);
                    if (s.equals("xtx")) {if (row>zpt.rowEdit&&col>zpt.colEdit) return true; else return false;}
                }
                s=zpt.dbName.substring(0,2);
                if (signin.edit!=0) {
                    if (s.equals("t6")&&col==4) return false;
                    if (s.equals("t6")&&col==5) return false;
                    if (s.equals("t2")&&row==0&&setCells.ident==1) return false;
                    if (s.equals("t2")&&row==1&&setCells.ident==1) return false;
                    if (s.equals("t2")) if (row==2||row==6||row==12) return false;
                    if (s.equals("t2")) if (row==41) return false;
                    if (s.equals("t2")) if (row==9) return false;
                    if (s.equals("t2")) if (row==48||row==49) return false;
                    if (s.equals("t2")) if (row==25) return false;
                    if (s.equals("t3")&&row==0) return false;
                    if (s.equals("t9")&&col>3) {if (row==16||row==17) return false;}
                }
                boolean b1=(row>zpt.rowEdit&&col>zpt.colEdit&&signin.uID==3); boolean b2=(signin.edit<2&&col>0);
                if (signin.edit==1) return b1; else return b2;
            }
            public void setValueAt(Object value,int row,int col) {
                String[] xtxzpt=zpt.dbName.split("_");
                if (dbn.equals("podpis")||dbn.equals("podpis2")||xtxzpt[0].equals("users")) rows[row][col]=(JTextArea)value;
                else {if (col>zpt.colEdit) rows[row][col]=(JTextField)value; else rows[row][col]=(JTextArea)value;}
                if (signin.zadach==1) rows=setCells.setCells(row,col);
                if (signin.edit>1) rows=setCells.otchets(row,col);
                else {if (signin.zadach!=1) rows=setCells.setCells(row,col);}
                TableModelEvent e=new TableModelEvent(dataModel); table.tableChanged(e); setRowSizes();
            }
        };
        table=new JTable(dataModel); table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setIntercellSpacing(new Dimension(0,0));
        Header header=new Header(signin,zpt,table,dbn);
        TableColumnModel tcm=table.getColumnModel();
        String[] xtxzpt=dbn.split("_");
        if (dbn.equals("podpis")||dbn.equals("podpis2")||xtxzpt[0].equals("users")) {
            for (int i=0; i<zpt.colLength; i++) {
                TableColumn xz=tcm.getColumn(i);
                xz.setCellRenderer(new JTextAreaRenderer()); xz.setCellEditor(new JTextAreaEditor());
            }
            table.setShowGrid(false);
        } else {
            for (int i=0; i<zpt.colEdit+1; i++) {
                TableColumn xz=tcm.getColumn(i);
                xz.setCellRenderer(new JTextAreaRenderer()); xz.setCellEditor(new JTextAreaEditor());
            }
            for (int i=zpt.colEdit+1; i<zpt.colLength; i++) {
                TableColumn xz=tcm.getColumn(i);
                xz.setCellEditor(new JTextAreaEditor2()); xz.setCellRenderer(new JTextAreaRenderer2());
            }
        }
        for (int i=0; i<zpt.colLength; i++) table.getColumnModel().getColumn(i).setPreferredWidth(zpt.columnSizes[i]);
        table.setColumnSelectionAllowed(true); table.setRowSelectionAllowed(true);
        table.requestFocus(true);
        table.setRowHeight(16); makeRowSizes(); setRowSizes();
        if (print==0&&!dbn.equals("podpis")&&!dbn.equals("podpis2")) {
            table.addColumnSelectionInterval(zpt.cursorX,zpt.cursorX);
            table.addRowSelectionInterval(zpt.cursorY,zpt.cursorY);
        }
        if (signin.edit==0) {
            JTableHeader jth=table.getTableHeader();
            jth.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent arg0) {}
                public void mouseEntered(MouseEvent arg0) {}
                public void mouseExited(MouseEvent arg0) {}
                public void mousePressed(MouseEvent arg0) {}
                public void mouseReleased(MouseEvent arg0) {
                    for (int i=0; i<zpt.columnSizes.length; i++) {
                        TableColumn p=table.getColumnModel().getColumn(i); zpt.columnSizes[i]=p.getPreferredWidth();
                    }
                    signin.sql.setHeader(zpt);
                }
            });
        }
        if (signin.edit>0||dbn.equals("podpis")||dbn.equals("podpis2")) {
            TableColumnModel tcmx=table.getColumnModel(); TableColumn p=tcmx.getColumn(0); tcmx.removeColumn(p);
            if (print==1) table.setBorder(BorderFactory.createMatteBorder(0,1,0,0,Color.BLACK));
        }
    }
    String getErrors(int m) {return keyF8.getErrors(m);}
    void setRowSizes() {for (int row=0; row<rows.length; row++) table.setRowHeight(row,zpt.rowSizes[row]);}
    void makeRowSizes() {
        for (int row=0; row<rows.length; row++) {
            int max=16; if (zpt.tx==1) max=20;
            for (int i=0; i<zpt.colEdit; i++) {
                Dimension xy=rows[row][i].getPreferredSize(); double z=xy.getHeight(); int w=(int)Math.floor(z)+1;
                if (max<w) max=w;
            }
            zpt.rowSizes[row]=max;
        }
    }
    void font(JTextComponent cJTA,int row,int col) {
        if (zpt.tx==0) {
            boolean b0=dbn.equals("t1")&&col==2;
            if (signin.edit>0) b0=dbn.equals("t1")&&col==1;
            boolean b011=dbn.equals("t11")&&col==2;
            if (signin.edit>0) b011=dbn.equals("t11")&&col==1;
            boolean b012=dbn.equals("t12")&&col==3;
            if (signin.edit>0) b012=dbn.equals("t12")&&col==2;
            boolean b02=dbn.equals("t2")&&col==3;
            if (signin.edit>0) b02=dbn.equals("t2")&&col==2;
            boolean b03=dbn.equals("t3")&&col==4;
            if (signin.edit>0) b03=dbn.equals("t3")&&col==3;
            boolean b04=dbn.equals("t4")&&col==3;
            if (signin.edit>0) b04=dbn.equals("t4")&&col==2;
            boolean b07=dbn.equals("t7")&&col==2;
            if (signin.edit>0) b07=dbn.equals("t7")&&col==1;
            boolean b08=dbn.equals("t8")&&col==3;
            if (signin.edit>0) b08=dbn.equals("t8")&&col==2;
            boolean b09=dbn.equals("t9")&&col==2;
            if (signin.edit>0) b09=dbn.equals("t9")&&col==1;
            boolean b010=dbn.equals("t10")&&col==3;
            if (signin.edit>0) b010=dbn.equals("t10")&&col==2;
            boolean b05=dbn.equals("t5")&&col==3;
            if (signin.edit>0) b05=dbn.equals("t5")&&col==2;
            boolean b06=dbn.equals("t6")&&col==3;
            if (signin.edit>0) b06=dbn.equals("t6")&&col==2;
            boolean b1=dbn.equals("t1")&&row==44;
            boolean b2=dbn.equals("t3")&&(row==0||(row==4&&col>0));
            boolean b3=dbn.equals("t4")&&row==12;
            boolean b4=dbn.equals("t2")&&((row==2||row==12||row==13||row==25||(row==18&&col>0)||(row==20&&col>0)||(row==22&&col>0)||(row==24&&col>0)||(row==25&&col>0))||(row==37||row==39||row==41||row==48));
            boolean b5=dbn.equals("t8")&&(row==0||row==10);
            boolean b6=dbn.equals("t9")&&row==11; boolean b7=dbn.equals("t12")&&row==0;
            if (b06||b05||b010||b09||b08||b07||b04||b02||b03||b012||b011||b0||b1||b2||b3||b4||b5||b6||b7) {
                cJTA.setBackground(new Color(238,238,238)); //cJTA.setFont(new Font("Verdana",Font.BOLD,10));
            }
            if (col>=zpt.colEdit) cJTA.setFont(new Font("Times New Roman",Font.PLAIN,14));
            else cJTA.setFont(new Font("Times New Roman",Font.PLAIN,12));
        } else {
            if (row==rows.length-1) {
                cJTA.setBackground(new Color(238,238,238)); cJTA.setFont(new Font("Times New Roman",Font.BOLD,12));
            } else cJTA.setFont(new Font("Times New Roman",Font.PLAIN,12));
        }
    }
    class JTextAreaRenderer2 extends JLabel implements TableCellRenderer {
        public JTextAreaRenderer2() {}
        public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,
                                                       int row,int column) {
            JTextField cJTA=(JTextField)value;
            cJTA.setHorizontalAlignment(JTextField.RIGHT);
            if (isSelected&&print==0) cJTA.setBorder(BorderFactory.createMatteBorder(1,1,2,2,Color.BLACK));
            else cJTA.setBorder(BorderFactory.createMatteBorder(0,0,1,1,Color.BLACK));
            font(cJTA,row,column);
            return cJTA;
        }
    }
    class JTextAreaEditor2 extends AbstractCellEditor implements TableCellEditor {
        JTextField oldJTA=null,cJTA;
        public JTextAreaEditor2() {}
        public Object getCellEditorValue() {return cJTA;}
        public Component getTableCellEditorComponent(final JTable table,Object value,boolean isSelected,final int row,
                                                     final int column) {
            cJTA=(JTextField)value;
            if (print==1) return cJTA;
            if (oldJTA!=null) {
                oldJTA.setBorder(BorderFactory.createMatteBorder(1,1,2,2,Color.WHITE)); oldJTA.setCaretColor(Color.WHITE);
            }
            oldJTA=cJTA; cJTA.setBorder(BorderFactory.createMatteBorder(1,1,2,2,Color.BLACK));
            cJTA.setCaretColor(Color.BLACK); font(cJTA,row,column);
            if (signin.edit>0) {
                if (row!=rows.length-1) cJTA.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"check");
                cJTA.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0),"check");
                cJTA.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0),"check");
                cJTA.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_KP_UP, 0),"check");
                cJTA.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_KP_DOWN, 0),"check");
                cJTA.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0),"check");
                cJTA.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0),"check");
                cJTA.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_KP_LEFT, 0),"check");
                cJTA.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_KP_RIGHT, 0),"check");
            }
            cJTA.selectAll();
            return cJTA;
        }
    }
    class JTextAreaRenderer extends JLabel implements TableCellRenderer {
        public JTextAreaRenderer() {}
        public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,
                                                       int row,int column) {
            JTextArea cJTA=(JTextArea)value;
            if (isSelected&&print==0) {
                if (dbn.equals("podpis")||dbn.equals("podpis2")) cJTA.setBorder(BorderFactory.createMatteBorder(1,1,2,2,Color.WHITE));
                else cJTA.setBorder(BorderFactory.createMatteBorder(1,1,2,2,Color.BLACK));
            } else {
                 mBottom=1; mLeft=1; cellMap(row,column);
                 if (dbn.equals("podpis")||dbn.equals("podpis2")) cJTA.setBorder(BorderFactory.createMatteBorder(0,0,mBottom,mLeft,Color.WHITE));
                 else cJTA.setBorder(BorderFactory.createMatteBorder(0,0,mBottom,mLeft,Color.BLACK));
            }
            font(cJTA,row,column);
            return cJTA;
        }
    }
    class JTextAreaEditor extends AbstractCellEditor implements TableCellEditor {
        JTextArea oldJTA=null,cJTA;
        public JTextAreaEditor() {}
        public Object getCellEditorValue() {return cJTA;}
        public Component getTableCellEditorComponent(final JTable table,Object value,boolean isSelected,final int row,
                                                     final int column) {
            cJTA=(JTextArea)value; if (print==1) return cJTA;
            if (oldJTA!=null) {
                oldJTA.setBorder(BorderFactory.createMatteBorder(1,1,2,2,Color.WHITE)); oldJTA.setCaretColor(Color.WHITE);
            }
            oldJTA=cJTA; cJTA.setBorder(BorderFactory.createMatteBorder(1,1,2,2,Color.BLACK));
            cJTA.setCaretColor(Color.BLACK); font(cJTA,row,column);
            if (signin.edit>0) {
                if (row!=rows.length-1) cJTA.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"check");
                cJTA.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0),"check");
                cJTA.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0),"check");
                cJTA.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_KP_UP, 0),"check");
                cJTA.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_KP_DOWN, 0),"check");
                cJTA.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0),"check");
                cJTA.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0),"check");
                cJTA.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_KP_LEFT, 0),"check");
                cJTA.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_KP_RIGHT, 0),"check");
            }
            cJTA.selectAll();
            cJTA.addKeyListener(new KeyListener() {
                public void keyPressed(KeyEvent e) {
                    int l=e.getKeyCode();
                    if (l==KeyEvent.VK_ENTER) {
                        if (signin.edit==0) {
/*
                            if (dbn.equals("users")) {
                                int row=table.getSelectedRow(); int col=table.getSelectedColumn();
                                signin.sql.addRow(zpt,row);
                                zpt.dbName=dbn; zpt.cursorX=col; zpt.cursorY=row; new GetPage(signin,zpt);
                            } else {
*/
                                Dimension xy=cJTA.getPreferredSize(); double z=xy.getHeight(); int w=(int)Math.floor(z);
                                if (w>=zpt.rowSizes[row]-2) {zpt.rowSizes[row]=w+15; table.setRowHeight(row,zpt.rowSizes[row]);}
//                            }
                        } else cJTA.setCaretColor(Color.WHITE);
                    }
                    if (l==KeyEvent.VK_UP||l==KeyEvent.VK_DOWN||l==KeyEvent.VK_KP_UP||l==KeyEvent.VK_KP_DOWN||l==KeyEvent.VK_RIGHT||l==KeyEvent.VK_LEFT||l==KeyEvent.VK_KP_RIGHT||l==KeyEvent.VK_KP_LEFT) {
                        cJTA.setCaretColor(Color.WHITE);
                    }
                }
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode()==KeyEvent.VK_BACK_SPACE||e.getKeyCode()==KeyEvent.VK_DELETE) {
                        if (signin.edit==0) {
                            Dimension xy=cJTA.getPreferredSize(); double z=xy.getHeight(); int w=(int)Math.floor(z);
                            int max=16;
                            for (int i=0; i<rows[row].length; i++) if (i!=column) {
                                Dimension xyw=rows[row][i].getPreferredSize(); double zw=xyw.getHeight();
                                int ww=(int)Math.floor(zw)+2;
                                if (max<ww) max=ww;
                            }
                            if (w<zpt.rowSizes[row]-2&&zpt.rowSizes[row]>max) {
                                zpt.rowSizes[row]=table.getRowHeight(row)-15; table.setRowHeight(row,zpt.rowSizes[row]);
                    }   }   }
                }
                public void keyTyped(KeyEvent e) {}
            });
            cJTA.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent arg0) {}
                public void mouseEntered(MouseEvent arg0) {}
                public void mouseExited(MouseEvent arg0) {}
                public void mousePressed(MouseEvent arg0) {}
                public void mouseReleased(MouseEvent arg0) {
                    int k=table.getSelectedRow();
                    if (arg0.getButton()==3) {
                        int x=arg0.getX(); int y=arg0.getY();
                        if (signin.edit==0) {
                            JPopupMenu jpm=new JPopupMenu();
                            JMenuItem jmi0=new JMenuItem("Вырезать");        jpm.add(jmi0);
                            jmi0.addActionListener(new Cut(cJTA));
                            JMenuItem jmi1=new JMenuItem("Копировать");      jpm.add(jmi1);
                            jmi1.addActionListener(new Copy(cJTA));
                            JMenuItem jmi2=new JMenuItem("Вставить");        jpm.add(jmi2);
                            jmi2.addActionListener(new Insert(cJTA));
                            jpm.addSeparator();
                            JMenuItem jmi3=new JMenuItem("Удалить строку");  jpm.add(jmi3);
                            jmi3.addActionListener(new CutRow());
                            JMenuItem jmi4=new JMenuItem("Добавить строку"); jpm.add(jmi4);
                            jmi4.addActionListener(new AddRow());
                            String[] xtxzpt=zpt.dbName.split("_");
                            if (!xtxzpt[0].equals("users")) {
                                jpm.addSeparator();
                                JMenuItem jmi5=new JMenuItem("Отметить");        jpm.add(jmi5);
                                jmi5.addActionListener(new Otmetit());
                            }
                            jpm.show(cJTA,x,y);
                }   }   }
            });
            return cJTA;
        }
    }
    class Cut implements ActionListener {
        JTextComponent cJTA;
        public Cut(JTextComponent cJTAx) {cJTA=cJTAx;}
        public void actionPerformed(ActionEvent arg0) {cJTA.cut();}
    }
    class Copy implements ActionListener {
        JTextComponent cJTA;
        public Copy(JTextComponent cJTAx) {cJTA=cJTAx;}
        public void actionPerformed(ActionEvent arg0) {cJTA.copy();}
    }
    class Insert implements ActionListener {
        JTextComponent cJTA;
        public Insert(JTextComponent cJTAx) {cJTA=cJTAx;}
        public void actionPerformed(ActionEvent arg0) {cJTA.paste();}
    }
    class CutRow implements ActionListener {
        public CutRow() {}
        public void actionPerformed(ActionEvent arg0) {
            int row=table.getSelectedRow(); int col=table.getSelectedColumn(); signin.sql.deleteRow(zpt.dbName,row);
            zpt.dbName=dbn; zpt.cursorX=col; if (row==rows.length-1) zpt.cursorY=row-1; else zpt.cursorY=row;
            new GetPage(signin,zpt);
        }
    }
    class AddRow implements ActionListener {
        public AddRow() {}
        public void actionPerformed(ActionEvent arg0) {
            int row=table.getSelectedRow(); int col=table.getSelectedColumn(); signin.sql.addRow(zpt,row);
            zpt.dbName=dbn; zpt.cursorX=col; zpt.cursorY=row; new GetPage(signin,zpt);
        }
    }
    class Otmetit implements ActionListener {
        public Otmetit() {}
        public void actionPerformed(ActionEvent arg0) {
            String[] xtxzpt=dbn.split("_");
            if (xtxzpt[0].equals("users")) {
                signin.selectedRow=table.getSelectedRow();
            } else {
                zpt.rowEdit=table.getSelectedRow()-1; zpt.colEdit=table.getSelectedColumn()-1; signin.sql.setHeader(zpt);
            }
        }
    }
    void cellMap(int row,int col) {
        if (signin.edit>0) col=col+1;
        if (dbn.equals("t5")||dbn.equals("xt5")) {
            if (col==1) {
                if (row==0||row==1) mLeft=0;
                if (row>1&&row<18)  mBottom=0;
                if (row>18) mLeft=0;
            }
        }
        if (dbn.equals("t7")||dbn.equals("xt7")) {
            row=row+3;
            if (row==0) {if (col==1||col==2) mBottom=0; if (col==3||col==4||col==5||col==7||col==8||col==9) mLeft=0;}
        }
        if (dbn.equals("t8")||dbn.equals("xt8")) {
            row=row+3; if (row==0) {if (col==1||col==2||col==3) mBottom=0; if (col==4||col==5) mLeft=0;}
            if (col==1) {if (row<5||row>12) mLeft=0; else if (row>4&&row<12) mBottom=0;}
        }
        if (dbn.equals("t12")||dbn.equals("xt12")) {
            row=row+2;
            if (col==1) {
                if (row>=3&&row<8) mBottom=0;
                if (row==4) mBottom=1;
                if (row>8||row==2) mLeft=0;
                if (row==2) mLeft=0;
            }
        }
        if (dbn.equals("t10")||dbn.equals("xt10")) {
            row=row+2; if (col==1) {if (row<2) mLeft=0; else if (row>1&&row<5) mBottom=0; else if (row>5&&row<9) mBottom=0;}
        }
        if (dbn.equals("t6")||dbn.equals("xt6")) {
            row=row+3;
            if (col==1) {if (row<3) {mLeft=0; if (row==0) mBottom=0;} else if (row>2&&row<10) mBottom=0;}
            if (col==2&&row==0) mBottom=0; if (col==3&&row==0) mBottom=0; if (col==4&&row==0) mBottom=0;
            if (col==5&&row==0) mBottom=0; if (col==6&&row==0) mLeft=0;
        }
        if (dbn.equals("t4")||dbn.equals("xt4")) {
            row=row+2;
            if (col==1) {if (row<16) mLeft=0; if (row>15&&row<21) mBottom=0; if (row>21) mLeft=0;}
        }
        if (dbn.equals("t3")||dbn.equals("xt3")) {
            row=row+2;
            if (col==1) {
                if (row<11) {mLeft=0; if (row>2&&row<7) mBottom=0;}
                if (row>7&&row<10) mBottom=0;
                if (row>10&&row<24) mBottom=0;
                if (row>24&&row<28) mBottom=0;
            }
            if (col==2) { 
                if (row<3) mLeft=0;
                if (row>2&&row<7) mBottom=0;
                if (row>7&&row<10) mBottom=0;
                if (row>10&&row<23) mLeft=0;
                if (row>22) mLeft=0;
            }
        }
        if (dbn.equals("t2")||dbn.equals("xt2")) {
            row=row+2;
            if (col==1) {
                if (row<5) mLeft=0; if (row>4&&row<13) mBottom=0; if (row>13&&row<20) mLeft=0;
                if (row>19&&row<26) mBottom=0; if (row>26&&row<45) mLeft=0; if (row>44&&row<49) mBottom=0;
                if (row>49&&row<52) mLeft=0; if (row>51&&row<54) mBottom=0; if (row==55) mLeft=0;
                if (row>55&&row<58) mBottom=0; if (row>58) mLeft=0;
            }
        }
        if (dbn.equals("tx0")||dbn.equals("xtx0")) {
            row=row+1; if (col==1) {if (row<10) mLeft=0; if (row>9&&row<11) mBottom=0; if (row>11) mLeft=0;}
        }
    }
}