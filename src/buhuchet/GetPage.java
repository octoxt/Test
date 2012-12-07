package buhuchet;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.text.*;
import javax.swing.table.*;

public class GetPage {
    Buhuchet     signin;
    ParamsTables zpt;
    GetTable     tablex;
    String       dbn;
    int          k;

    public GetPage(Buhuchet signinx,ParamsTables zptx) {
        signin=signinx; zpt=zptx; dbn=zpt.dbName;
String[] dbnxtx=dbn.split("_");
//signin.message(signin.getUID(signin.region[0][signin.cregion],2));
if (!dbnxtx[0].equals("users")&&!dbnxtx[0].equals("podpis")&&!dbnxtx[0].equals("podpis2")) {
    if (signin.cregion==0&&signin.getUID(signin.region[0][signin.cregion],2)==0) signin.edit=2;
}
        Cursor cursor=new Cursor(Cursor.WAIT_CURSOR); signin.jpanel.setCursor(cursor);
        tablex=new GetTable(signin,zpt,0);
        if (tablex.table==null) {cursor=new Cursor(Cursor.DEFAULT_CURSOR); signin.jpanel.setCursor(cursor); return;}
        String d="";
        String[] xtxzpt=dbn.split("_");
        if (!xtxzpt[0].equals("users")&&!dbn.equals("userw")&&!dbn.equals("podpis")&&!dbn.equals("podpis2")) {
            String[] dw=signin.data.split("-"); int mont=new Integer(dw[1]);
            d=dw[0]+" йил "+signin.months[mont-1]+" ойи учун";
            if (signin.edit>1) d="йил бошидан "+signin.data+" ойи буйича";
        }
        String title="Асосий курсаткичлар";
        if (dbn.equals("userw")) title="Кодировка";
        else {
            if (xtxzpt[0].equals("users")) title="Список пользователей";
            else {
                if (dbn.equals("podpis")||dbn.equals("podpis2")) title="Подписы руководителей";
                else {
                    if (!dbn.substring(0,2).equals("tx")) {int m=new Integer(dbn.substring(1)); title=signin.ms[m-1];}
                    else {int m=new Integer(dbn.substring(2)); if (m>0) title="Тахлилий маълумот. "+m+" - сонли жадвал";}
                }
            }
        }
        if (signin.edit==0) signin.getJIF(title); else signin.getJIF(title+" - "+d);
        JInternalFrame jif=signin.jpanel.getSelectedFrame();
        jif.setBackground(Color.WHITE);
        JPanel parent=new JPanel(false); parent.setBackground(Color.WHITE);
        parent.setLayout(new BorderLayout()); jif.add(parent);
        JScrollPane scrollpane=new JScrollPane(tablex.table); scrollpane.setBackground(Color.WHITE);
        parent.add(scrollpane);
        tablex.table.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                    int row=tablex.table.getSelectedRow(); int rowk=tablex.table.getModel().getRowCount()-1;
                    if (row==rowk) {
                        int col=tablex.table.getSelectedColumn();
                        if (tablex.table.getModel().isCellEditable(row,col+1)) {
                            tablex.table.addColumnSelectionInterval(col,col);
                            tablex.table.addRowSelectionInterval(row-1,row-1);
                            tablex.table.editCellAt(row,col);
            }   }   }   }
            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
        });
        if (dbn.equals("tx0")) tx0Header(parent,d);
        if (dbn.equals("t1")) t1Header(parent,d); if (dbn.equals("t2")) t2Header(parent);
        if (!xtxzpt[0].equals("users")&&!dbn.equals("userw")&&!dbn.equals("podpis")&&!dbn.equals("podpis2")&&signin.edit>0) {
            JToolBar toolbar=new JToolBar(); jif.add(BorderLayout.SOUTH,toolbar);
            String sg=dbn.substring(0,2);
            if (!sg.equals("tx")) {
                if (signin.zadach==0) {
boolean boolx=signin.cregion==0&&signin.getUID(signin.region[0][signin.cregion],2)>0;
if (signin.cregion>0||boolx) {
if (!signin.region[0][0].equals("0000"))
{
//signin.message(signin.region[0][0]+"="+signin.region[0][signin.cregion]);
                    String[] dw=signin.data.split("-"); int mont=new Integer(dw[1]);
                    JButton month=new JButton("  F1 - "+dw[0]+" йил "+signin.months[mont-1]+" ойи учун");
                    toolbar.add(month);
                    month.addActionListener(new MonthYear(1));
                    JButton year =new JButton("  F4 - Йил бошидан "+signin.data+" ойи буйича  "); toolbar.add(year);
                    year.addActionListener(new MonthYear(2));
                    if (signin.edit==1) month.setForeground(Color.BLUE); else year.setForeground(Color.BLUE);
                    addKeyListener(tablex.table,signin.edit);
} else signin.edit=2;
}
                }
                String[] sw=zpt.dbName.split("_");
                if (!sw[0].equals("t10")) {
                    addKeyListener(tablex.table,4);
                    JButton f8=new JButton("  F8 - Мантикий текшириш  "); toolbar.add(f8);
                    f8.addActionListener(new F8(1));
                }
            }
            JButton f9=new JButton("  F9 - Олдинги жадвал  "); toolbar.add(f9);
            f9.addActionListener(new F9_10(1,9)); addKeyListenerF9_10(tablex.table,9);
            JButton f10=new JButton("  F10 - Кейинги жадвал  "); toolbar.add(f10);
            f10.addActionListener(new F9_10(1,10)); addKeyListenerF9_10(tablex.table,10);
            /*
            JButton f12=new JButton(" F12 - в Excel "); toolbar.add(f12);
            f12.addActionListener(new F12(1)); addKeyListenerF12(tablex.table);
            */
        }
        if (zpt.errorPage>0) {
            JPanel cPanel=new JPanel(false); cPanel.setBackground(Color.WHITE);
            cPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); cPanel.setLayout(new BoxLayout(cPanel,BoxLayout.Y_AXIS));
            String sk=tablex.getErrors(1); int size=1;
            if (!sk.equals("Жами 0 та хато!")) {
                String[] sw=sk.split("_xtx_"); size=size+sw.length;
                for (int i=0; i<sw.length; i++) {JLabel uL=new JLabel(sw[i],JLabel.LEFT); cPanel.add(uL);}
            } else {JLabel uL=new JLabel("Жадвалда хато йук!",JLabel.LEFT); cPanel.add(uL);}
            JScrollPane scrollpane2=new JScrollPane(cPanel);
            size=15*size+5; if (size>100) size=100;
            scrollpane2.setPreferredSize(new Dimension(jif.getWidth(),size));
            parent.add(scrollpane2,BorderLayout.SOUTH);
            zpt.errorPage=0;
        }
        tablex.table.requestFocus(true);
        cursor=new Cursor(Cursor.DEFAULT_CURSOR); signin.jpanel.setCursor(cursor);
    }
    class F12 implements ActionListener {
        public F12(int k) {if (k==0) F12();}
        public void actionPerformed(ActionEvent arg0) {F12();}
        void F12() {new ToExcel(signin,zpt,tablex.table);}
    }
    void addKeyListenerF12(Component comp) {
        comp.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {if (e.getKeyCode()==KeyEvent.VK_F12) new F12(0);}
            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
        });
    }
    class MonthYear implements ActionListener {
        int      v;
        public MonthYear(int vx) {v=vx;}
        public void actionPerformed(ActionEvent arg0) {signin.edit=v; zpt.dbName=dbn; new GetPage(signin,zpt);}
    }
    void addKeyListener(Component comp,final int v) {
        comp.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (v==4) {if (e.getKeyCode()==KeyEvent.VK_F8) new F8(0); return;}
                boolean b=(e.getKeyCode()==KeyEvent.VK_F1||e.getKeyCode()==KeyEvent.VK_F4); int vx=1; if (v==1) vx=2;
                if (b) {signin.edit=vx; zpt.dbName=dbn; new GetPage(signin,zpt);}
            }
            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
        });
    }
    class F8 implements ActionListener {
        public F8(int k) {if (k==0) F8();}
        public void actionPerformed(ActionEvent arg0) {F8();}
        void F8() {zpt.errorPage=1; zpt.dbName=dbn; new GetPage(signin,zpt);}
    }
    class F9_10 implements ActionListener {
        int m;
        public F9_10(int k,int mx) {m=mx; if (k==0) F9_10();}
        public void actionPerformed(ActionEvent arg0) {F9_10();}
        void F9_10() {
            String sg=dbn.substring(0,2);
            if (sg.equals("tx")) {
                String s=dbn.substring(2); int l=new Integer(s);
                if (m==9) {if (l==1) l=10; else l=l-1;} else {if (l==10) l=1; else l=l+1;}
                dbn="tx"+l;
            } else {
                String s=dbn.substring(1); int l=new Integer(s);
                if (m==9) {if (l==7) l=2; else {if (l==2) l=6; else {if (l==3) l=1; else {if (l>1) l=l-1; else l=12;}}}}
                else {if (l==1) l=3; else {if (l==6) l=2; else {if (l==2) l=7; else {if (l<12) l=l+1; else l=1;}}}}
                dbn="t"+l;
            }
            if (sg.equals("tx")) signin.edit=2;
            ParamsTables zpt=new ParamsTables(signin,dbn); new GetPage(signin,zpt);
        }
    }
    void addKeyListenerF9_10(Component comp,final int f910) {
        comp.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if ((f910==9&&e.getKeyCode()==KeyEvent.VK_F9)||(f910==10&&e.getKeyCode()==KeyEvent.VK_F10)) new F9_10(0,f910);
            }
            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
        });
    }

    public GetPage(int kx,Buhuchet signinx) {
        k=kx; signin=signinx;
        Cursor cursor=new Cursor(Cursor.WAIT_CURSOR); signin.jpanel.setCursor(cursor);
        String d=signin.data+" ойи учун"; if (signin.edit>1) d="йил бошидан "+signin.data+" ойи буйича";
        String title="Асосий курсаткичлар";
        if (k<6) {int kd=k-1; if (kd>0) title=""+kd+" - варак";}
        else {int kd=2*(k-5)-1; int kdx=kd+1; title="Тахлилий маълумотлар, "+kd+"-сонли ва "+kdx+"-сонли жадваллар";}
        signin.getJIF("Печать:  "+title+" - "+d); JInternalFrame jif=signin.jpanel.getSelectedFrame();
        JPanel parent=new JPanel(false); parent.setLayout(new BorderLayout()); jif.add(parent);
        JPanel panel=new JPanel(false); panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JScrollPane scrollpane=new JScrollPane(panel); parent.add(scrollpane);
        if (k>5&&k<11) {
            int kp=k-5; int ky=2*kp-1; int kz=ky+1;
            JPanel panel1=new JPanel(false); panel1.setBackground(Color.WHITE);
            panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS)); panel.add(panel1);
            if (k==6) {
                JLabel uL=new JLabel("-"); uL.setForeground(Color.WHITE); panel1.add(uL);
                JLabel uL1=new JLabel("Тергов ишлари юзасидан тахлилий маълумотлар"); panel1.add(uL1);
                String sx=signin.region[1][signin.cregion]; String s=signin.getTitle(); if (!s.equals("")) sx=s+" "+sx;
                JLabel uL2=new JLabel(sx+" - "+d); panel1.add(uL2);
                uL=new JLabel("-"); uL.setForeground(Color.WHITE); panel1.add(uL);
            }
            if (kp>1) {JLabel uL3=new JLabel("-"+kp+"-"); panel1.add(uL3);}
            JPanel panel2=new JPanel(false); panel2.setBackground(Color.WHITE);
            panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS)); panel.add(panel2);
            int k=getErrors(panel2,"tx"+ky);
            JLabel uL=new JLabel("-"); uL.setForeground(Color.WHITE); panel.add(uL);
            JPanel panel3=new JPanel(false); panel3.setBackground(Color.WHITE);
            panel3.setLayout(new BoxLayout(panel3,BoxLayout.Y_AXIS)); panel.add(panel3);
            k=k+getErrors(panel3,"tx"+kz);
            Dimension wh=panel.getPreferredSize(); double h=wh.getHeight(); double hx=630-h;
            int m=0; if (hx>0) m=(int)Math.floor(hx/16)+1;
            for (int i=0; i<m; i++) {uL=new JLabel("-"); uL.setForeground(Color.WHITE); panel.add(uL);}
        }
        if (k==1) {
            JPanel panel1=new JPanel(false); panel1.setBackground(Color.WHITE);
            panel1.setLayout(new FlowLayout(FlowLayout.LEFT)); panel.add(panel1);
            tx0Header1(panel1,d);
            JPanel panel2=new JPanel(false); panel2.setBackground(Color.WHITE);
            panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS)); panel.add(panel2);
            int kerr=getErrors(panel2,"tx0");
/*
            JPanel panel3=new JPanel(false); panel3.setBackground(Color.WHITE);
            panel3.setLayout(new FlowLayout(FlowLayout.LEFT)); panel.add(panel3);
            tx0Header2(panel3);
*/
            JPanel panel3xtx=new JPanel(false); panel3xtx.setBackground(Color.WHITE);
            panel3xtx.setLayout(new BoxLayout(panel3xtx,BoxLayout.Y_AXIS)); panel.add(panel3xtx);
            kerr=kerr+getErrors(panel3xtx,"podpis");
        }
        if (k==2) {
            JPanel panel1=new JPanel(false); panel1.setBackground(Color.WHITE);
            panel1.setLayout(new FlowLayout(FlowLayout.CENTER)); panel.add(panel1);
            t1Header1(panel1);
            JPanel panel2=new JPanel(false); panel2.setBackground(Color.WHITE);
            panel2.setLayout(new FlowLayout(FlowLayout.CENTER)); panel.add(panel2);
            t1Header2(panel2,d);
            JPanel panel3=new JPanel(false); panel3.setBackground(Color.WHITE);
            panel3.setLayout(new BoxLayout(panel3,BoxLayout.Y_AXIS)); panel.add(panel3);
            int kerr=getErrors(panel3,"t1");
            JPanel panel4=new JPanel(false); panel4.setBackground(Color.WHITE);
            panel4.setLayout(new FlowLayout(FlowLayout.LEFT)); panel.add(panel4);
   //         if (kerr>0) {JLabel uL=new JLabel("Жадвалда "+kerr+" та хато бор!",JLabel.LEFT); panel4.add(uL);}
        }
        if (k==3) {
            JPanel panel1=new JPanel(false); panel1.setBackground(Color.WHITE);
            panel1.setLayout(new FlowLayout(FlowLayout.LEFT)); panel.add(panel1);
JLabel uL=new JLabel(signin.region[1][signin.cregion]+"                         ",JLabel.LEFT); panel1.add(uL);
            t2Header(panel1);
            JPanel panel2=new JPanel(false); panel2.setBackground(Color.WHITE);
            panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS)); panel.add(panel2);
            int kerr=getErrors(panel2,"t2");
            JPanel panel3=new JPanel(false); panel3.setBackground(Color.WHITE);
            panel3.setLayout(new FlowLayout(FlowLayout.LEFT)); panel.add(panel3);
  //          if (kerr>0) {JLabel uL=new JLabel("Жадвалда "+kerr+" та хато бор!",JLabel.LEFT); panel3.add(uL);}
        }
        if (k==4) {
            JPanel panel1=new JPanel(false); panel1.setBackground(Color.WHITE);
            panel1.setLayout(new FlowLayout(FlowLayout.LEFT)); panel.add(panel1);
            JLabel uL=new JLabel(signin.region[1][signin.cregion]+"                         "+"2 - булим",JLabel.LEFT); panel1.add(uL);
            JPanel panel2=new JPanel(false); panel2.setBackground(Color.WHITE);
            panel2.setLayout(new FlowLayout(FlowLayout.LEFT)); panel.add(panel2);

                JPanel panel21=new JPanel(false); panel21.setBackground(Color.WHITE);
                panel21.setLayout(new BoxLayout(panel21,BoxLayout.Y_AXIS)); panel2.add(panel21);
                int kerr=getErrors(panel21,"t3");

                JPanel panel22=new JPanel(false); panel22.setBackground(Color.WHITE);
                panel22.setLayout(new BoxLayout(panel22,BoxLayout.Y_AXIS)); panel2.add(panel22);
                kerr=kerr+getErrors(panel22,"t4");
            JPanel panel3=new JPanel(false); panel3.setBackground(Color.WHITE);
            panel3.setLayout(new BoxLayout(panel3,BoxLayout.Y_AXIS)); panel.add(panel3);
            kerr=kerr+getErrors(panel3,"t5");
            JPanel panel4=new JPanel(false); panel4.setBackground(Color.WHITE);
            panel4.setLayout(new BoxLayout(panel4,BoxLayout.Y_AXIS)); panel.add(panel4);
            kerr=kerr+getErrors(panel4,"t6");

            JPanel panel5=new JPanel(false); panel5.setBackground(Color.WHITE);
            panel5.setLayout(new FlowLayout(FlowLayout.LEFT)); panel.add(panel5);
    //        if (kerr>0) {JLabel uLt=new JLabel("Жадвалларда жами "+kerr+" та хато бор!",JLabel.LEFT); panel5.add(uLt);}
        }
        if (k==5) {
            JPanel panel1ww=new JPanel(false); panel1ww.setBackground(Color.WHITE);
            JLabel uL=new JLabel(signin.region[1][signin.cregion],JLabel.LEFT); panel1ww.add(uL);
            panel.add(panel1ww);




            JPanel panel1=new JPanel(false); panel1.setBackground(Color.WHITE);
            panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));
            panel.add(panel1);
            int kerr=getErrors(panel1,"t7");
            JPanel panel2=new JPanel(false); panel2.setBackground(Color.WHITE);
            panel2.setLayout(new FlowLayout(FlowLayout.LEFT)); panel.add(panel2);
                JPanel panel21=new JPanel(false); panel21.setBackground(Color.WHITE);
                panel21.setLayout(new FlowLayout(FlowLayout.LEFT)); panel2.add(panel21);
                panel21.setLayout(new BoxLayout(panel21,BoxLayout.Y_AXIS));
                    JPanel panel211=new JPanel(false); panel211.setBackground(Color.WHITE);
                    panel211.setLayout(new BoxLayout(panel211,BoxLayout.Y_AXIS));
                    panel21.add(panel211);
                    kerr=kerr+getErrors(panel211,"t8");
                    JPanel panel212=new JPanel(false); panel212.setBackground(Color.WHITE);
                    panel212.setLayout(new BoxLayout(panel212,BoxLayout.Y_AXIS));
                    panel21.add(panel212);
//
                    kerr=kerr+getErrors(panel212,"t10");

                JPanel panel22=new JPanel(false); panel22.setBackground(Color.WHITE);
                panel22.setLayout(new FlowLayout(FlowLayout.LEFT)); panel2.add(panel22);
                panel22.setLayout(new BoxLayout(panel22,BoxLayout.Y_AXIS));
                    JPanel panel221=new JPanel(false); panel221.setBackground(Color.WHITE);
                    panel221.setLayout(new BoxLayout(panel221,BoxLayout.Y_AXIS));
                    panel22.add(panel221);
                    kerr=kerr+getErrors(panel221,"t9");
                    JPanel panel222=new JPanel(false); panel222.setBackground(Color.WHITE);
                    panel222.setLayout(new BoxLayout(panel222,BoxLayout.Y_AXIS));
                    panel22.add(panel222);
                    kerr=kerr+getErrors(panel222,"t11");
            JPanel panel3=new JPanel(false); panel3.setBackground(Color.WHITE);
            panel3.setLayout(new BoxLayout(panel3,BoxLayout.Y_AXIS)); panel.add(panel3);
            kerr=kerr+getErrors(panel3,"t12");

            JPanel panel4=new JPanel(false); panel4.setBackground(Color.WHITE);
            panel4.setLayout(new FlowLayout(FlowLayout.LEFT)); panel.add(panel4);
            panel4.setLayout(new BoxLayout(panel4,BoxLayout.Y_AXIS)); panel4.setLayout(new GridLayout(0, 1));
    //        if (kerr>0) {JLabel uL0=new JLabel("Жадвалларда жами "+kerr+" та хато бор!",JLabel.LEFT); panel4.add(uL0);}
/*
            JPanel cPanel2=new JPanel(false); cPanel2.setBackground(Color.WHITE);
            cPanel2.setLayout(new BoxLayout(cPanel2,BoxLayout.X_AXIS));
            cPanel2.setLayout(new GridLayout(0,3)); panel.add(cPanel2);

            JLabel uL1xxx=new JLabel("",JLabel.CENTER); cPanel2.add(uL1xxx);
            uL1xxx=new JLabel("",JLabel.CENTER); cPanel2.add(uL1xxx);
            uL1xxx=new JLabel("",JLabel.CENTER); cPanel2.add(uL1xxx);

            JLabel uL1=new JLabel("____ нусхада бажарилди",JLabel.CENTER); cPanel2.add(uL1);
            uL1.setFont(new Font("Times New Roman",Font.PLAIN,12));

            String sm=signin.region[1][signin.cregion];
            if (signin.uID==0) sm=sm+" Бош прокурори"; else sm=sm+" прокурори";
            JLabel uL2=new JLabel(sm,JLabel.CENTER); cPanel2.add(uL2);
            uL2.setFont(new Font("Times New Roman",Font.PLAIN,12));

            sm=signin.region[1][signin.cregion];
            if (signin.uID==3) sm=sm+" ИИБ"; else sm=sm+" ИИББ";
            JLabel uL3=new JLabel(sm,JLabel.CENTER); cPanel2.add(uL3);
            uL3.setFont(new Font("Times New Roman",Font.PLAIN,12));
            JLabel uL4=new JLabel("1-нусха _______, 2-нусха _______",JLabel.CENTER); cPanel2.add(uL4);
            uL4.setFont(new Font("Times New Roman",Font.PLAIN,12));
            JLabel uL5=new JLabel("",JLabel.CENTER); cPanel2.add(uL5);

            if (signin.uID==3) sm=" булимининг бошлиги"; else sm=" бошлиги";
            JLabel uL6=new JLabel(sm,JLabel.CENTER); cPanel2.add(uL6);
            uL6.setFont(new Font("Times New Roman",Font.PLAIN,12));
            JLabel uL7=new JLabel("'___'___________20___йил",JLabel.CENTER); cPanel2.add(uL7);
            uL7.setFont(new Font("Times New Roman",Font.PLAIN,12));
            JLabel uL8=new JLabel("",JLabel.CENTER); cPanel2.add(uL8);
            JLabel uL9=new JLabel("",JLabel.CENTER); cPanel2.add(uL9);
            JLabel uL10=new JLabel("",JLabel.CENTER); cPanel2.add(uL10);
            JLabel uL11=new JLabel("",JLabel.CENTER); cPanel2.add(uL11);
            JLabel uL12=new JLabel("",JLabel.CENTER); cPanel2.add(uL12);
            JLabel uL13=new JLabel("",JLabel.CENTER); cPanel2.add(uL13);
            JLabel uL14=new JLabel("",JLabel.CENTER); cPanel2.add(uL14);
            JLabel uL15=new JLabel("",JLabel.CENTER); cPanel2.add(uL15);
            JLabel uL16=new JLabel("ижрочи:_____________",JLabel.CENTER); cPanel2.add(uL16);
            uL16.setFont(new Font("Times New Roman",Font.PLAIN,12));
            JLabel uL17=new JLabel("",JLabel.CENTER); cPanel2.add(uL17);

            sm=signin.region[1][signin.cregion];
            JLabel uL18=new JLabel(sm,JLabel.CENTER); cPanel2.add(uL18);
            uL18.setFont(new Font("Times New Roman",Font.PLAIN,12));
            uL14=new JLabel("",JLabel.CENTER); cPanel2.add(uL14);
            uL15=new JLabel("",JLabel.CENTER); cPanel2.add(uL15);
            if (signin.uID==3) sm="ИИБ"; else sm="ИИББ";
            uL18=new JLabel(sm+" тергов булими бошлиги",JLabel.CENTER); cPanel2.add(uL18);
            uL18.setFont(new Font("Times New Roman",Font.PLAIN,12));
*/
            JPanel panel3xtx=new JPanel(false); panel3xtx.setBackground(Color.WHITE);
            panel3xtx.setLayout(new BoxLayout(panel3xtx,BoxLayout.Y_AXIS)); panel.add(panel3xtx);
            kerr=kerr+getErrors(panel3xtx,"podpis2");
        }
        JToolBar toolbar=new JToolBar(); jif.add(BorderLayout.SOUTH,toolbar);
        if (k>1&&k<6) {
            if (signin.zadach==0) {
/*
                JButton month=new JButton("  F1 - "+signin.data+" ойи учун  "); toolbar.add(month);
                month.addActionListener(new MonthYear2(k,1));
                JButton year =new JButton("  F4 - Йил бошидан "+signin.data+" ойи буйича  "); toolbar.add(year);
                year.addActionListener(new MonthYear2(k,2));
                if (signin.edit==1) month.setForeground(Color.BLUE); else year.setForeground(Color.BLUE);
                addKeyListenerPrint(jif,k,signin.edit);
*/
            }
        }
        JButton f9=new JButton("  F9 - Олдинги варак  "); toolbar.add(f9);
        f9.addActionListener(new F9_10Print(1,9,k));
        addKeyListenerF9_10Print(jif,9,k);
        JButton f10=new JButton("  F10 - Кейинги варак  "); toolbar.add(f10);
        f10.addActionListener(new F9_10Print(1,10,k));
        addKeyListenerF9_10Print(jif,10,k);

        JButton print=new JButton(new ImageIcon("D:/Projects/eclipse/TergovNew/resources/images/print.gif")); toolbar.add(print);
        print.addActionListener(new Print(panel));
        jif.requestFocus(true);
        cursor=new Cursor(Cursor.DEFAULT_CURSOR); signin.jpanel.setCursor(cursor);
    }
    int getErrors(JPanel panelt,String t) {
        ParamsTables zpt=new ParamsTables(signin,t); GetTable table=null;
        if (zpt.dbName.equals("podpis")||zpt.dbName.equals("podpis2")) {
            int x=signin.edit;
            signin.edit=0; table=new GetTable(signin,zpt,0);
            signin.edit=x;
        } else table=new GetTable(signin,zpt,1);
        JPanel panelt1=new JPanel(false); panelt1.setBackground(Color.WHITE);
        FlowLayout flh=new FlowLayout(FlowLayout.LEFT); flh.setVgap(0); panelt1.setLayout(flh);
        panelt.add(panelt1);
        JTableHeader header=table.table.getTableHeader();
        if (!zpt.dbName.equals("podpis")&&!zpt.dbName.equals("podpis2")) header.setBorder(BorderFactory.createMatteBorder(1,1,0,0,Color.BLACK));
        panelt1.add(header);
        JPanel panelt2=new JPanel(false); panelt2.setBackground(Color.WHITE);
        FlowLayout fl=new FlowLayout(FlowLayout.LEFT); fl.setVgap(0); panelt2.setLayout(fl);
        panelt.add(panelt2); panelt2.add(table.table);
        int kerr=0;
        if (!zpt.dbName.equals("podpis")&&!zpt.dbName.equals("podpis2")) {
            String sw=table.getErrors(0); String[] swxtx=zpt.dbName.split("_");
            if (swxtx[0].equals("t10")&&signin.edit==1) {} else if (!sw.equals("")) kerr=new Integer(sw);
        }
        return kerr;
    }
    class MonthYear2 implements ActionListener {
        int      k,v;
        public MonthYear2(int kx,int vx) {k=kx; v=vx;}
        public void actionPerformed(ActionEvent arg0) {
            signin.edit=v;
            if (k==1) signin.edit=2; if (k>5) signin.edit=3;
            new GetPage(k,signin);
        }
    }
    class F9_10Print implements ActionListener {
        int m,k;
        public F9_10Print(int l,int mx,int kx) {m=mx; k=kx; if (l==0) F9_10();}
        public void actionPerformed(ActionEvent arg0) {F9_10();}
        void F9_10() {
            if (k<6) {if (m==9) {
                if (k>1) k=k-1; else k=5; if (k==1) signin.edit=2;} else {if (k<5) k=k+1; else {k=1; signin.edit=2;}}
            } else {signin.edit=2; if (m==9) {if (k>6) k=k-1; else k=10;} else {if (k<10) k=k+1; else k=6;}}
            new GetPage(k,signin);
        }
    }
    void addKeyListenerF9_10Print(Component comp,final int m,final int k) {
        comp.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if ((m==9&&e.getKeyCode()==KeyEvent.VK_F9)||(m==10&&e.getKeyCode()==KeyEvent.VK_F10)) new F9_10Print(0,m,k);
            }
            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
        });
    }
    void addKeyListenerPrint(Component comp,final int k,final int v) {
        comp.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                boolean b=(e.getKeyCode()==KeyEvent.VK_F1||e.getKeyCode()==KeyEvent.VK_F4); int vx=1; if (v==1) vx=2;
                if (b) {signin.edit=vx; new GetPage(k,signin);}
            }
            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
        });
    }
    class Print implements ActionListener,Printable {
        private Component comp;
        public Print(Component comp) {this.comp=comp;}
        public int print(Graphics g,PageFormat pageFormat,int pageIndex) {
            if (pageIndex>0) return (NO_SUCH_PAGE);
            else {
                Graphics2D g2d=(Graphics2D)g;
                g2d.translate((int)pageFormat.getImageableX(),(int)pageFormat.getImageableY());
                if (k==1) g2d.scale(0.74,0.95);
                if (k==2) g2d.scale(0.5,0.5);
                if (k==3) g2d.scale(0.68,0.7);
                if (k==4) g2d.scale(0.5,0.5);
                if (k==5) g2d.scale(0.47,0.65);
                if (k==6) g2d.scale(0.65,0.55);
                if (k==7) g2d.scale(0.73,0.6);
                if (k==8) g2d.scale(0.6,0.6);
                if (k==9) g2d.scale(0.75,0.55);
                if (k==10) g2d.scale(0.75,0.65);
                comp.paint(g2d);
                return (PAGE_EXISTS);
            }
        }
        public void print() {
            PrinterJob printJob=PrinterJob.getPrinterJob(); printJob.setPrintable(this);
            /*
            PrintRequestAttributeSet aset=new HashPrintRequestAttributeSet();
            aset.add(MediaSizeName.ISO_A4);
            aset.add(OrientationRequested.PORTRAIT);
            */
            if (printJob.printDialog()) try {
                PageFormat pf=printJob.pageDialog(printJob.defaultPage());
           //     PageFormat pf=printJob.pageDialog(aset);
                printJob.print();
            } catch(PrinterException pe) {}
        }
        public void actionPerformed(ActionEvent arg0) {print();}
    }

    void tx0Header(JPanel parent,String d) {tx0Header1(parent,d); tx0Header2(parent);}
    void tx0Header1(JPanel parent,String d) {
        ParamsTables zptw=new ParamsTables(signin,"tx0"); int m=0;
        for (int i=1; i<zptw.columnSizes.length; i++) m=m+zptw.columnSizes[i];
        JPanel cPanel=new JPanel(false);
        cPanel.setBackground(Color.WHITE);
        cPanel.setPreferredSize(new Dimension(m,70));
        cPanel.setLayout(new BoxLayout(cPanel,BoxLayout.X_AXIS));
        cPanel.setLayout(new GridLayout(0,3));
        JLabel uL1=new JLabel("УЗБЕКИСТОН РЕСПУБЛИКАСИ",JLabel.CENTER); cPanel.add(uL1);
        uL1.setFont(new Font("Times New Roman",Font.PLAIN,12));
        JLabel uL2=new JLabel("АСОСИЙ КЎРСАТКИЧЛАР",JLabel.CENTER);
        uL2.setFont(new Font("Times New Roman",Font.BOLD,12));
        cPanel.add(uL2);
        JLabel uL3=new JLabel("Почта оркали - хар ойлик",JLabel.CENTER); cPanel.add(uL3);
        uL3.setFont(new Font("Times New Roman",Font.PLAIN,12));
        JLabel uL4=new JLabel("ПРОКУРАТУРАСИ",JLabel.CENTER); cPanel.add(uL4);
        uL4.setFont(new Font("Times New Roman",Font.PLAIN,12));
        JLabel uL5=new JLabel("",JLabel.CENTER); cPanel.add(uL5);
        JLabel uL6=new JLabel("Туман,шахар,вилоят органлари",JLabel.CENTER); cPanel.add(uL6);
        uL6.setFont(new Font("Times New Roman",Font.PLAIN,12));
        JLabel uL7=new JLabel("",JLabel.LEFT); cPanel.add(uL7);
        JLabel uL8=new JLabel("Прокуратура ва ички ишлар",JLabel.CENTER); cPanel.add(uL8);
        uL8.setFont(new Font("Times New Roman",Font.PLAIN,12));
        JLabel uL8x=new JLabel("томонидан курсатилган муддатга",JLabel.CENTER); cPanel.add(uL8x);
        uL8x.setFont(new Font("Times New Roman",Font.PLAIN,12));
        JLabel uL9=new JLabel("УЗБЕКИСТОН РЕСПУБЛИКАСИ",JLabel.CENTER); cPanel.add(uL9);
        uL9.setFont(new Font("Times New Roman",Font.PLAIN,12));
        JLabel uL10=new JLabel("органлари тергов ишлари юзасидан",JLabel.CENTER); cPanel.add(uL10);
        uL10.setFont(new Font("Times New Roman",Font.PLAIN,12));
        JLabel uL11=new JLabel("буйсуниш тартибида такдим",JLabel.CENTER); cPanel.add(uL11);
        uL11.setFont(new Font("Times New Roman",Font.PLAIN,12));
        JLabel uL12=new JLabel("ИЧКИ ИШЛАР ВАЗИРЛИГИ",JLabel.CENTER); cPanel.add(uL12);
        uL12.setFont(new Font("Times New Roman",Font.PLAIN,12));
        String s=signin.getTitle(); String sx=signin.region[1][signin.cregion]; if (!s.equals("")) sx=s+" "+sx;
        JLabel uL13=new JLabel(d,JLabel.CENTER); cPanel.add(uL13);
        uL13.setFont(new Font("Times New Roman",Font.PLAIN,12));
        JLabel uL14=new JLabel("килинади",JLabel.CENTER); cPanel.add(uL14);
        uL14.setFont(new Font("Times New Roman",Font.PLAIN,12));
        JLabel uL15=new JLabel("",JLabel.CENTER); cPanel.add(uL15);
        uL15.setFont(new Font("Times New Roman",Font.PLAIN,12));
        JLabel uL16=new JLabel(sx,JLabel.CENTER); cPanel.add(uL16);
        uL16.setFont(new Font("Times New Roman",Font.PLAIN,12));
        JLabel uL17=new JLabel("(усиб борувчи натижалар билан)",JLabel.CENTER); cPanel.add(uL17);
        uL17.setFont(new Font("Times New Roman",Font.PLAIN,12));
        parent.add(cPanel,BorderLayout.NORTH);
    }
    void tx0Header2(JPanel parent) {
        ParamsTables zptw=new ParamsTables(signin,"tx0"); int m=0;
        for (int i=1; i<zptw.columnSizes.length; i++) m=m+zptw.columnSizes[i];
        JPanel cPanel2=new JPanel(false); cPanel2.setBackground(Color.WHITE);
        cPanel2.setPreferredSize(new Dimension(m,70));
        cPanel2.setLayout(new BoxLayout(cPanel2,BoxLayout.X_AXIS));
        cPanel2.setLayout(new GridLayout(0,3));

        JLabel uL1xxx=new JLabel("",JLabel.CENTER); cPanel2.add(uL1xxx);
        uL1xxx=new JLabel("",JLabel.CENTER); cPanel2.add(uL1xxx);
        uL1xxx=new JLabel("",JLabel.CENTER); cPanel2.add(uL1xxx);

        JLabel uL1=new JLabel("____ нусхада бажарилди",JLabel.CENTER); cPanel2.add(uL1);
        uL1.setFont(new Font("Times New Roman",Font.PLAIN,12));

        String sm=signin.region[1][signin.cregion];
        if (signin.uID==0) sm=sm+" Бош прокурори"; else sm=sm+" прокурори";
        JLabel uL2=new JLabel(sm,JLabel.CENTER); cPanel2.add(uL2);
        uL2.setFont(new Font("Times New Roman",Font.PLAIN,12));

        sm=signin.region[1][signin.cregion];
        if (signin.uID==3) sm=sm+" ИИБ"; else sm=sm+" ИИББ";
        JLabel uL3=new JLabel(sm,JLabel.CENTER); cPanel2.add(uL3);
        uL3.setFont(new Font("Times New Roman",Font.PLAIN,12));
        JLabel uL4=new JLabel("1-нусха _______, 2-нусха _______",JLabel.CENTER); cPanel2.add(uL4);
        uL4.setFont(new Font("Times New Roman",Font.PLAIN,12));
        JLabel uL5=new JLabel("",JLabel.CENTER); cPanel2.add(uL5);

        if (signin.uID==3) sm=" булимининг бошлиги"; else sm=" бошлиги";
        JLabel uL6=new JLabel(sm,JLabel.CENTER); cPanel2.add(uL6);

        uL6.setFont(new Font("Times New Roman",Font.PLAIN,12));
        JLabel uL7=new JLabel("'___'___________20___йил",JLabel.CENTER); cPanel2.add(uL7);
        uL7.setFont(new Font("Times New Roman",Font.PLAIN,12));
        JLabel uL8=new JLabel("",JLabel.CENTER); cPanel2.add(uL8);
        JLabel uL9=new JLabel("",JLabel.CENTER); cPanel2.add(uL9);
        JLabel uL10=new JLabel("",JLabel.CENTER); cPanel2.add(uL10);
        JLabel uL11=new JLabel("",JLabel.CENTER); cPanel2.add(uL11);
        JLabel uL12=new JLabel("",JLabel.CENTER); cPanel2.add(uL12);
        JLabel uL13=new JLabel("",JLabel.CENTER); cPanel2.add(uL13);
        JLabel uL14=new JLabel("",JLabel.CENTER); cPanel2.add(uL14);
        JLabel uL15=new JLabel("",JLabel.CENTER); cPanel2.add(uL15);
        JLabel uL16=new JLabel("ижрочи:_____________",JLabel.CENTER); cPanel2.add(uL16);
        uL16.setFont(new Font("Times New Roman",Font.PLAIN,12));
        JLabel uL17=new JLabel("",JLabel.CENTER); cPanel2.add(uL17);

        sm=signin.region[1][signin.cregion];
        JLabel uL18=new JLabel(sm,JLabel.CENTER); cPanel2.add(uL18);
        uL18.setFont(new Font("Times New Roman",Font.PLAIN,12));

        uL14=new JLabel("",JLabel.CENTER); cPanel2.add(uL14);
        uL15=new JLabel("",JLabel.CENTER); cPanel2.add(uL15);

        if (signin.uID==3) sm="ИИБ"; else sm="ИИББ";
        uL18=new JLabel(sm+" тергов булими бошлиги",JLabel.CENTER); cPanel2.add(uL18);
        uL18.setFont(new Font("Times New Roman",Font.PLAIN,12));
        parent.add(cPanel2,BorderLayout.SOUTH);
    }
    void t1Header(JPanel parent,String d) {
        JPanel cPanel=new JPanel(false); cPanel.setBackground(Color.WHITE);
        t1Header1(cPanel); t1Header2(cPanel,d);
        parent.add(cPanel,BorderLayout.NORTH);
    }
    void t1Header1(JPanel cPanel) {
        cPanel.setBackground(Color.WHITE);
        cPanel.setLayout(new BoxLayout(cPanel,BoxLayout.Y_AXIS)); cPanel.setLayout(new GridLayout(0, 1));
        JLabel uL1=new JLabel("Узбекистон Республикаси Бош прокурори ва Ички",JLabel.RIGHT);
        uL1.setFont(new Font("Times New Roman",Font.BOLD,12)); cPanel.add(uL1);
        JLabel uL2=new JLabel("ишлар вазирининг 2009 йил 31 октябрдаги 2Б/134-сонли",JLabel.RIGHT);
        uL2.setFont(new Font("Times New Roman",Font.BOLD,12)); cPanel.add(uL2);
        JLabel uL3=new JLabel("Кўшма буйругига 1-илова",JLabel.RIGHT);
        uL3.setFont(new Font("Times New Roman",Font.BOLD,12)); cPanel.add(uL3);
    }
    void t1Header2(JPanel cPanel,String d) {
        cPanel.setBackground(Color.WHITE);
        cPanel.setLayout(new BoxLayout(cPanel,BoxLayout.Y_AXIS)); cPanel.setLayout(new GridLayout(0, 1));
        JLabel uLabel=new JLabel("ТЕРГОВ ИШЛАРИ ЮЗАСИДАН ХИСОБОТ",JLabel.CENTER);
        uLabel.setFont(new Font("Times New Roman",Font.BOLD,14));
        cPanel.add(uLabel);
        JLabel uLabel2=new JLabel(d,JLabel.CENTER);
        uLabel2.setFont(new Font("Times New Roman",Font.BOLD,12));
        cPanel.add(uLabel2);
        String sx=signin.region[1][signin.cregion]; String s=signin.getTitle(); if (!s.equals("")) sx=s+" "+sx;
        JLabel uLabel3=new JLabel(sx+" бўйича",JLabel.CENTER);
        uLabel3.setFont(new Font("Times New Roman",Font.BOLD,12));
        cPanel.add(uLabel3);
        JLabel uL4=new JLabel("",JLabel.RIGHT);
        uL4.setFont(new Font("Times New Roman",Font.BOLD,12));
        cPanel.add(uL4);
        JLabel uL5=new JLabel("1 - бўлим. Хар ойлик ягона статистик хисобот (1-Я шакл)",JLabel.RIGHT);
        uL5.setFont(new Font("Times New Roman",Font.BOLD,12));
        cPanel.add(uL5);
    }
    void t2Header(JPanel parent) {
        JPanel cPanel=new JPanel(false); cPanel.setBackground(Color.WHITE);
        cPanel.setLayout(new BoxLayout(cPanel,BoxLayout.Y_AXIS));
        cPanel.setLayout(new GridLayout(0, 1));
        JLabel uL1=new JLabel("2 - жадвал. Тергов аппаратининг фаолияти",JLabel.LEFT); cPanel.add(uL1);
        parent.add(cPanel,BorderLayout.NORTH);
    }
}