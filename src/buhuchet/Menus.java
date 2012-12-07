package buhuchet;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class Menus {
    Buhuchet signin;
    public Menus(Buhuchet signinx) {
        signin=signinx; signin.sql.regions(); signin.getUserID(); signin.parentNode=signin.uID;
        int year=Calendar.getInstance().get(Calendar.YEAR);
        int mont=Calendar.getInstance().get(Calendar.MONTH);
        String titlex=signin.title+" - ";
        String s=signin.getTitle(); if (!s.equals("")) titlex=titlex+s+" ";
        titlex=titlex+signin.region[1][0]+" ������ ";
        titlex=titlex+year+" ��� "+signin.months[mont]+" ��� ����.";
        int montx=mont+1; String smontx=""+montx; if (montx<10) smontx="0"+montx; signin.data=""+year+"-"+smontx;
        signin.jframe=new JFrame(); signin.jframe.setTitle(titlex);
        signin.jframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {signin.sql.setParams(); signin.sql.close();}
        });
        signin.sql.getParams("window");
        signin.jframe.setSize(signin.W,signin.H); signin.jframe.setLocation(signin.X,signin.Y);
        JMenuBar mb = new JMenuBar();
            JMenu otchet = new JMenu("���������"); addMenuListener(otchet);
                JMenuItem[] otchxt=new JMenuItem[signin.ms.length];
                for (int i=0; i<signin.ms.length; i++) {
                    int k=i;
                    if (i>0&&i<5) k=k+1; if(i==5) k=1;
                    otchxt[k]=new JMenuItem(signin.ms[k]); int il=k+1;
                    otchxt[k].addActionListener(new ActionLr(signin,"t"+il,0,1));
                    otchet.add(otchxt[k]); if (i==0||il==2||il==6) otchet.addSeparator();

                }
            mb.add(otchet);
            if (signin.uID<3) {
                signin.otchet2=new JMenu("���������� ���������"); addMenuListener(signin.otchet2);
                JMenuItem[] otchet2x=new JMenuItem[10];
                for (int i=0; i<10; i++) {
                    int k=i+1;
                    otchet2x[i]=new JMenuItem(""+k+" - ����� ������");
                    otchet2x[i].addActionListener(new ActionLr(signin,"tx"+k,0,2));
                    signin.otchet2.add(otchet2x[i]); if (i==2||i==5) signin.otchet2.addSeparator();
                }
                mb.add(signin.otchet2);
            }
            JMenu print = new JMenu("������"); addMenuListener(print);
                JMenuItem print2=new JMenuItem("������ ������������");
                print2.addActionListener(new ActionLr(signin,"",1,3)); print.add(print2);
                print.addSeparator();
                JMenuItem print3=new JMenuItem("1 - �����");
                print3.addActionListener(new ActionLr(signin,"",2,3)); print.add(print3);
                JMenuItem print4=new JMenuItem("2 - �����");
                print4.addActionListener(new ActionLr(signin,"",3,3)); print.add(print4);
                JMenuItem print5=new JMenuItem("3 - �����");
                print5.addActionListener(new ActionLr(signin,"",4,3)); print.add(print5);
                JMenuItem print6=new JMenuItem("4 - �����");
                print6.addActionListener(new ActionLr(signin,"",5,3)); print.add(print6);
                if (signin.uID<3) {
                    print.addSeparator();
                    signin.print7=new JMenu("�������� �����������"); print.add(signin.print7);
                    JMenuItem print71=new JMenuItem("1-����� �� 2-����� ���������");
                    print71.addActionListener(new ActionLr(signin,"",6,4)); signin.print7.add(print71);
                    JMenuItem print72=new JMenuItem("3-����� �� 4-����� ���������");
                    print72.addActionListener(new ActionLr(signin,"",7,4)); signin.print7.add(print72);
                    signin.print7.addSeparator();
                    JMenuItem print73=new JMenuItem("5-����� �� 6-����� ���������");
                    print73.addActionListener(new ActionLr(signin,"",8,4)); signin.print7.add(print73);
                    JMenuItem print74=new JMenuItem("7-����� �� 8-����� ���������");
                    print74.addActionListener(new ActionLr(signin,"",9,4)); signin.print7.add(print74);
                    JMenuItem print75=new JMenuItem("9-����� �� 10-����� ���������");
                    print75.addActionListener(new ActionLr(signin,"",10,4)); signin.print7.add(print75);
                }
            mb.add(print);
            JMenu serv = new JMenu("������"); addMenuListener(serv);
                JMenuItem serv5=new JMenuItem("����");
                serv5.addActionListener(new ActionLr(signin,1)); serv.add(serv5);
            //    if (signin.region[0].length>1) {
                if (signin.uID<3) {
                    JMenu serv6=new JMenu("�������"); int kp=0;
                        JMenuItem[] serv6x=new JMenuItem[signin.region[0].length];
                        for (int i=0; i<signin.region[0].length; i++) {
                            kp=kp+1;
                            serv6x[i]=new JMenuItem(signin.region[1][i]);
                            if (i==0) serv6x[i].setForeground(Color.BLUE);
                            serv6x[i].addActionListener(new ActionLr(signin,i,1)); serv6.add(serv6x[i]);
                            if (signin.region[0].length>1&&i==0) serv6.addSeparator();
                            if (kp==4&&i<signin.region[0].length-2) {kp=0; serv6.addSeparator();}
                        }
                     serv.add(serv6);
                }
                serv.addSeparator();
                JMenuItem serv1=new JMenuItem("������� ����������");
                serv1.addActionListener(new ActionLr(signin,2)); serv.add(serv1);
                JMenuItem serv2=new JMenuItem("���������� �������");
                serv2.addActionListener(new ActionLr(signin,0,2));  serv.add(serv2);
                JMenuItem serv3=new JMenuItem("���������� ����� �����");
                serv3.addActionListener(new ActionLr(signin,1,2));  serv.add(serv3);
                serv.addSeparator();
                JMenuItem serv311=new JMenuItem("���������� ��������");
                serv311.addActionListener(new ActionLr(signin,0,4));  serv.add(serv311);
                JMenuItem serv312=new JMenuItem("���������� �������� ����");
                serv312.addActionListener(new ActionLr(signin,1,4));  serv.add(serv312);
                serv.addSeparator();
                JMenuItem serv4=new JMenuItem("����������� ����� �������"); serv4.addActionListener(new ActionLr(signin,3));
                serv.add(serv4);
                //
                JMenuItem serv8=new JMenuItem("������ �������"); serv8.addActionListener(new ActionLr(signin,4));
                serv.add(serv8);
                //
                /*
                JMenuItem serv9=new JMenuItem("��������������� �������"); serv9.addActionListener(new ActionLr(signin,5));
                serv.add(serv9);
                */
                if (signin.security.equals("xoctoxtxbuhgalt")) {
                    JMenuItem serv11=new JMenuItem("���������");
                    serv11.addActionListener(new ActionLr(signin,"userw",0,7));
                    serv.add(serv11);
                }
                serv.addSeparator();
                JMenuItem serv10xw=new JMenuItem("������ ������������ ���� ������� �������");
                serv10xw.addActionListener(new ActionLr(signin,"podpis",0,7));
                serv.add(serv10xw);
                JMenuItem serv10xwd=new JMenuItem("4 - ����� ���� ������� �������");
                serv10xwd.addActionListener(new ActionLr(signin,"podpis2",0,7));
                serv.add(serv10xwd);
                
                serv.addSeparator();
                /*
                JMenu serv7=new JMenu("���������� �������� ������");
                    JMenuItem[] serv7x=new JMenuItem[signin.ms.length];
                    for (int i=0; i<signin.ms.length; i++) {
                        serv7x[i]=new JMenuItem(signin.ms[i]); int il=i+1;
                        serv7x[i].addActionListener(new ActionLr(signin,"t"+il,0,7));
                        serv7.add(serv7x[i]); if (il==2||il==6) serv7.addSeparator();
                    }
                serv.add(serv7);
                */
                JMenu serv10=new JMenu("���������� �������� �������");
                    JMenuItem serv10x=new JMenuItem("������ ������������");
                    serv10x.addActionListener(new ActionLr(signin,"tx0",0,7));
                    serv10.add(serv10x); serv10.addSeparator();
                    JMenuItem[] serv10x2=new JMenuItem[10];
                    for (int i=0; i<10; i++) {
                        int k=i+1;
                        serv10x2[i]=new JMenuItem(""+k+" - ����� ������");
                        serv10x2[i].addActionListener(new ActionLr(signin,"tx"+k,0,7));
                        serv10.add(serv10x2[i]); if (i==4) serv10.addSeparator();
                    }
                serv.add(serv10);
                
            mb.add(serv);
            JMenu spravka=new JMenu("�����(Help)"); addMenuListener(spravka);
                JMenuItem spravka1=new JMenuItem("�����");
                spravka1.addActionListener(new ActionLr(signin,1,3)); spravka.add(spravka1);
                
                JMenuItem spravka2=new JMenuItem("���������");
                spravka2.addActionListener(new ActionLr(signin,2,3)); spravka.add(spravka2); spravka.addSeparator();
                
                JMenuItem spravka3=new JMenuItem("��������� ������");
                spravka3.addActionListener(new ActionLr(signin,3,3)); spravka.add(spravka3);
            mb.add(spravka);
        signin.jframe.setJMenuBar(mb);
 	signin.jpanel=new JDesktopPane(); signin.jframe.add(signin.jpanel); signin.jframe.setVisible(true);
    }
    void addMenuListener(JMenu jmenu) {
        jmenu.addMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {
                JInternalFrame jif=signin.jpanel.getSelectedFrame();
                if (jif!=null) try {jif.setClosed(true);} catch (java.beans.PropertyVetoException ex) {}
            }
            public void menuDeselected(MenuEvent e) {}
            public void menuCanceled(MenuEvent e) {}
        });
    }
}