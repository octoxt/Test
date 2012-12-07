package buhuchet;

import javax.swing.*;

public class Buhuchet {
    static Buhuchet signin;
    JFrame          jframe=null;
    int             edit=1,zadach=0,xtxtx=0,selectedRow=-1,parentNode;
    String          drive="c:/TergovNew",dbname="jtergovNew";
    JDesktopPane    jpanel;
    MySQL           sql;
    String          password,razrabotchik="nusxa",security="",title="������ ������ �������� �������",data;
    int             uID,cregion,X,Y,W,H,printxyz;
    String[]        months={"������","�������","����","������","���","����","����","������","��������","�������","������","�������"};
    String[][]      region={};
    JMenu           otchet2,print7;
    String[] ms={"����������� ��������� ����������� ������ ����� (��������������)",
                 "������ ������������ �������� (������������ �����)",
                 "����������� ����� ������ ������ ���������� (��������������)",
                 "������ ����� ���������� ������� ���� ������� (��������������)",
                 "������ �������� ������� ������ ����� ������ �������� ������ ��������� ������ ������ ���� �������� (������������ �����)",
                 "������� ������ ���������� (������������ �����)",
                 "������ ��������� ������ ���������� ���������� ������� ��������� ��������� (���� ��� ��������)",
                 "������ ����� ������ ����� ���������������� ����� ��������",
                 "����� ���������� �� �������� ���������� ����� �������� ��������� ������ ����� (��������������)",
                 "�������� �������� �������� ������� 364 �������  (��������������)","����������� ������ ���� ��������",
                 "������ ���������� ����������� (���� ������ �������� ������ ����� ������ ��������������)"
    };

    public static void main(String[] args) {
        UIManager.put("swing.boldMetal", Boolean.TRUE); signin=new Buhuchet();
        signin.sql=new MySQL(signin);
        if (signin.sql.install()==0) {
            Boolean bool=false;
            while (!bool) {
                LayoutMr lp=new LayoutMr(signin,0);
                if (lp.cancel!=0) {signin.sql.close(); System.exit(0);}
                else {
                    signin.password=lp.pField.getText().trim();
                    if (signin.password.equals("xoctoxtxbuhgalt")) signin.security="xoctoxtxbuhgalt";
                    if (signin.sql.userFind(signin.password)==0) signin.message("� ��� ��� ������!"); else bool=true;
                }
            }
            Menus menu=new Menus(signin);
        }
    }
    void message(Object s) {JOptionPane.showMessageDialog(null,s,"���������",JOptionPane.INFORMATION_MESSAGE);}
    int getUID(String login,int k) {int u=2*k-2; String s=""+login.charAt(u)+login.charAt(u+1);  return new Integer(s);}
    void getUserID() {
        signin.uID=3; String ln=signin.region[0][signin.cregion];
        if (signin.getUID(ln,1)==0) signin.uID=1; else {if (signin.getUID(ln,2)==0) signin.uID=2;}
    }
    void getJIF(String title) {
        JInternalFrame jif=signin.jpanel.getSelectedFrame();
        if (jif!=null) try {jif.setClosed(true);} catch (java.beans.PropertyVetoException ex) {}
        JInternalFrame jintframe=new JInternalFrame(title); jintframe.setBounds(0,0,1018,683);
        signin.jpanel.add(jintframe); jintframe.show();
    }
    String getTitle() {
        String s="";
        if (signin.uID==3) {
            int k=signin.getUID(signin.region[0][signin.cregion],1); 
            for (int i=0; i<signin.region[0].length; i++) {
                String st=signin.region[0][i];
                if (k==signin.getUID(st,1)&&signin.getUID(st,2)==0&&!st.equals(signin.region[0][signin.cregion])) {
                    s=signin.region[1][i]; break;
        }   }   }
        return s;
    }
}