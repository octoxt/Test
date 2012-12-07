package buhuchet;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class LayoutMr implements LayoutManager {
    JTextField uField,pField;
    String     title,YearMonthDay;
    String[]   datas,conON={"  Ok  "};
    Buhuchet   signin;
    int        cancel=-1;

    public LayoutMr(Buhuchet signinx,int k) {
        signin=signinx;
        if (k==0) {title="�����������"; logPass(); }
        if (k==1) {title="���������";   logPass2();}
        if (k==2) {title="���������";   logPass3();}
        if (k==3) {title="��������� ������"; logPass4();}
        if (k==4||k==5||k==6) {
            title="������ �������"; if (k==5) title="�������������"; if (k==6) title="������ �������������";
            conON=new String[2]; conON[0]="  Ok  "; conON[1]="������"; logPass();
        }
        if (k==7) {title="���"; logPass5();}
        if (k==10) {title="�����"; logPass10();}
    }
    public LayoutMr(Buhuchet signinx,String dbn) {
        conON=new String[3]; conON[0]="  ��  "; conON[1]="�� ��� ����"; conON[2]="����������";
        title="������"; zapis(dbn);
    }
    void logPass3() {
        conON=new String[2]; conON[0]="  Ok  "; conON[1]="������";
        JPanel cPanel = new JPanel(false); cPanel.setLayout(new BoxLayout(cPanel,BoxLayout.X_AXIS));
        cPanel.setLayout(new GridLayout(0, 2)); JLabel label1 = new JLabel("���", JLabel.LEFT); cPanel.add(label1);
        String[] years =new String[50]; for (int i=0; i<50; i++) if (i<10) years[i]="200"+i; else years[i]="20"+i;
        JComboBox jcombobox1=new JComboBox(years); jcombobox1.setEditable(true);
        int year=Calendar.getInstance().get(Calendar.YEAR); int mth=0;
        if (signin.data!=null) {datas=signin.data.split("-"); year=new Integer(datas[0]); mth=new Integer(datas[1]);}
        int k=year-2000; jcombobox1.setSelectedIndex(k); cPanel.add(jcombobox1);
        JLabel label2 = new JLabel("�����", JLabel.LEFT); cPanel.add(label2);
        JComboBox jcombobox2=new JComboBox(signin.months); jcombobox2.setEditable(true);
        if (signin.data==null) mth=Calendar.getInstance().get(Calendar.MONTH);
        jcombobox2.setSelectedIndex(mth-1); jcombobox2.setMaximumRowCount(12); cPanel.add(jcombobox2);
        cancel=JOptionPane.showOptionDialog(null, cPanel,title,JOptionPane.PLAIN_MESSAGE,
                                              JOptionPane.PLAIN_MESSAGE, null,conON,conON[0]);
        if (cancel==0) {
            YearMonthDay=""+jcombobox1.getSelectedItem(); int kx=jcombobox2.getSelectedIndex()+1;
            String s=""+kx; if (kx<10) s="0"+kx; YearMonthDay=YearMonthDay+"-"+s;
        }
    }
//
    void logPass2() {
        String razr=""; Boolean bool=false;
        while (!bool) {
            logPass(); razr=pField.getText();
            if (signin.razrabotchik.equals(razr)) bool=true;
            else signin.message("� ��� ��� ������!");
        }

        String[] mints =new String[19];
        mints[0]="���������� ������������";
        mints[1]="������� �����";        mints[2]="������� �������";       mints[3]="��������� �������";
        mints[4]="������ �������";      mints[5]="������ �������";        mints[6]="������ �������";
        mints[7]="������ �������";       mints[8]="������� �������";       mints[9]="������� �������";
        mints[10]="�������� �������";    mints[11]="��������� �������";   mints[12]="�������� �������";
        mints[13]="������ �������";      mints[14]="��������������� ������������";
        mints[15]="��������� �������������";    mints[16]="������ �����������";
        mints[17]="��������� ����������";
        mints[18]="�������� �������";
        JComboBox jcombobox=new JComboBox(mints);
        JPanel cPanel=new JPanel(false);
        cPanel.setLayout(new BoxLayout(cPanel,BoxLayout.X_AXIS)); cPanel.setLayout(new GridLayout(0,1));
        jcombobox.setSelectedIndex(0); jcombobox.setEditable(true); cPanel.add(jcombobox);
        if (JOptionPane.showOptionDialog(null, cPanel,"���������",JOptionPane.OK_OPTION,
                                         JOptionPane.INFORMATION_MESSAGE, null, conON,conON[0]) == 0) {
            String s=""+jcombobox.getSelectedItem(); int ks=0; String cod="";
            for (int i=0; i<mints.length; i++) if (s.equals(mints[i])) {ks=i; break;}
            if (ks==0) cod="0000";
            else {
                if (ks<10) cod="0"+ks+"00"; else cod=""+ks+"00";
            }
            createTable(cod,s,mints);
        } else signin.sql.close();
    }
    void createTable(String login, String password,String[] mints) {
        String[][] a=new String[1][4];
        a[0][0]="1"; a[0][1]=login; a[0][2]=""; a[0][3]=password;
        String[] a2={"int(10)","varchar(20)","varchar(20)","varchar(300)"};
        signin.sql.createTable("user",a,a2);
        String s1=""+login.charAt(0)+login.charAt(1); int k1=new Integer(s1);
        String s2=""+login.charAt(2)+login.charAt(3); int k2=new Integer(s2);
        String sf="C:\\Program Files\\MySQL\\MySQL Server 5.0\\data\\"+signin.dbname;
        File dir=new File(sf); String[] fns=dir.list();
        for (int i=0; i<fns.length; i++) {
            String[] fn=fns[i].split("_");
            if (fn.length==4) {
                String s1z=fn[1]+"_"+fn[2]+"_"+fn[3]; String atable=fn[0]+"_"+s1z.substring(0,12);
                if (!login.equals(atable)) signin.sql.drop(atable);
            }
        }
        if (k1==0||k2==0) {
            if (k1==0) {signin.sql.createUz(mints); signin.message("��������� ����������� �������!"); signin.sql.close();}
            else {
                signin.jframe=new JFrame("���������");
                signin.jframe.setSize(707,431); signin.jframe.setLocation(163,136); signin.jpanel=new JDesktopPane();
                signin.jframe.add(signin.jpanel); signin.jframe.setVisible(true);
                signin.edit=0;
                ParamsTables zpt=new ParamsTables(signin,"users"); zpt.dbName=zpt.dbName+"_"+login;
                final GetPage xyz=new GetPage(signin,zpt);
                signin.jframe.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        signin.sql.xtxNabi(signin.selectedRow);
                        signin.message("��������� ����������� �������!"); signin.sql.close();
                    }
                });
                JToolBar toolbar=new JToolBar(); signin.jframe.add(BorderLayout.SOUTH,toolbar);
                JButton inst=new JButton("  ����������  "); toolbar.add(inst);
                inst.addActionListener(new Install());
            }
        } else signin.message("��������� ����������� �������!");
    }
    void logPass10() {
        String[] s=signin.sql.viborUsers();
        JComboBox jcombobox=new JComboBox(s);
        JPanel cPanel=new JPanel(false);
        cPanel.setLayout(new BoxLayout(cPanel,BoxLayout.X_AXIS)); cPanel.setLayout(new GridLayout(0,1));
        jcombobox.setSelectedIndex(0); jcombobox.setEditable(true); cPanel.add(jcombobox);
        jcombobox.setSelectedIndex(0); jcombobox.setEditable(true); cPanel.add(jcombobox);
        if (JOptionPane.showOptionDialog(null, cPanel,"�����",JOptionPane.OK_OPTION,
                                         JOptionPane.INFORMATION_MESSAGE, null, conON,conON[0]) == 0) {
            String sd=""+jcombobox.getSelectedItem(); int ks=0;
            for (int i=0; i<s.length; i++) if (sd.equals(s[i])) {ks=i; break;}
            ks=ks-1;
            signin.sql.xtxNabi(ks); signin.message("��������� ����������� �������!"); signin.sql.close();
        }
    }
    class Install implements ActionListener {
        public Install() {}
        public void actionPerformed(ActionEvent arg0) {
            new LayoutMr(signin,10);
        }
    }
//
    void zapis(String dbn) {
        JLabel pLabel=new JLabel("���������� ������� "+dbn+". ��������?",JLabel.LEFT);
        JPanel cPanel=new JPanel(false); cPanel.setLayout(new BoxLayout(cPanel,BoxLayout.X_AXIS));
        cPanel.setLayout(new GridLayout(0,1)); cPanel.add(pLabel);
        cancel=JOptionPane.showOptionDialog(null,cPanel,title,JOptionPane.OK_OPTION,
                                            JOptionPane.INFORMATION_MESSAGE,null,conON,conON[2]);
    }
    void logPass() {
        JLabel pLabel=new JLabel("������",JLabel.LEFT); pField=new JPasswordField("");
        JPanel cPanel=new JPanel(false); cPanel.setLayout(new BoxLayout(cPanel,BoxLayout.X_AXIS));
        cPanel.setLayout(new GridLayout(0, 2)); cPanel.add(pLabel); cPanel.add(pField);
        cancel=JOptionPane.showOptionDialog(null,cPanel,title,JOptionPane.OK_OPTION,
                                            JOptionPane.INFORMATION_MESSAGE,null,conON,conON[0]);
    }
    void logPass5() {
        conON=new String[2]; conON[0]="  Ok  "; conON[1]="������";
        JPanel cPanel = new JPanel(false); cPanel.setLayout(new BoxLayout(cPanel,BoxLayout.X_AXIS));
        cPanel.setLayout(new GridLayout(0, 2)); JLabel label1 = new JLabel("���", JLabel.LEFT); cPanel.add(label1);
        String[] years =new String[50]; for (int i=0; i<50; i++) if (i<10) years[i]="200"+i; else years[i]="20"+i;
        JComboBox jcombobox1=new JComboBox(years); jcombobox1.setEditable(true);
        int year=Calendar.getInstance().get(Calendar.YEAR);
        if (signin.data!=null) {datas=signin.data.split("-"); year=new Integer(datas[0]);}
        int k=year-2000; jcombobox1.setSelectedIndex(k); cPanel.add(jcombobox1);
        cancel=JOptionPane.showOptionDialog(null, cPanel,title,JOptionPane.PLAIN_MESSAGE,
                                              JOptionPane.PLAIN_MESSAGE, null,conON,conON[0]);
        if (cancel==0) {YearMonthDay=""+jcombobox1.getSelectedItem();}
    }
    void logPass4() {
        conON=new String[2]; conON[0]="  Ok  "; conON[1]="������";
        JLabel uLabel=new JLabel("������ ������", JLabel.LEFT); uField=new JPasswordField("");
        JLabel pLabel=new JLabel("����� ������", JLabel.LEFT);  pField=new JPasswordField("");
        JPanel cPanel=new JPanel(false); cPanel.setLayout(new BoxLayout(cPanel,BoxLayout.X_AXIS));
        cPanel.setLayout(new GridLayout(0, 2)); cPanel.add(uLabel); cPanel.add(uField);
        cPanel.add(pLabel); cPanel.add(pField);
        if (JOptionPane.showOptionDialog(null,cPanel,title,JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,
                                               null,conON,conON[0])==0) {
            String pas1=uField.getText(); String pas2=pField.getText();
            if (signin.password.equals(pas1)) {signin.sql.setPassword(pas2); signin.password=pas2;}
            else {signin.message("����������� ������� ������ ������!"); logPass4();}
        }
    }
    public void addLayoutComponent(String s, Component c) {}
    public void layoutContainer(Container c) {}
    public Dimension minimumLayoutSize(Container c) {return null;}
    public Dimension preferredLayoutSize(Container c) {return null;}
    public void removeLayoutComponent(Component c) {}
}