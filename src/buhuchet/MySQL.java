package buhuchet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import javax.swing.text.*;

public class MySQL {
    Buhuchet   signin;
    Connection connection=null;
    Statement  stmt,stmtxtx=null;
    ResultSet  rs,rsxtx=null;

    public MySQL(Buhuchet signinx) {signin=signinx; connect();}
    void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/"+signin.dbname+"?user=root","root","");
            stmt = (Statement) connection.createStatement();
            stmtxtx = (Statement) connection.createStatement();
        } catch (ClassNotFoundException ex) {signin.message("Cannot find driver classes!"); System.exit(0);}
        catch (SQLException ex) {signin.message("Cannot connect to dbase!"); System.exit(0);}
    }
    String getData(String fname,String dbn) {
        String d=""; if (hasTable(fname)==0) return d;
        ParamsTables zpt=new ParamsTables(signin,dbn);
        try {
            int k1=zpt.rowEdit+1; int k2=zpt.colEdit+1; String sql="select * from "+fname+" order by p0 asc";
            rs=stmt.executeQuery(sql); int k=0; while (rs.next()) {k=k+1;}
            String[][] a=new String[k-k1][zpt.colLength-k2];
            rs=stmt.executeQuery(sql); for (int i=0; i<k1; i++) rs.next();
            k=-1;
            while (rs.next()) {
                k=k+1;
                for (int i=k2+1; i<=zpt.colLength; i++) {
                    Object x=rs.getObject(i); if (x==null) a[k][i-k2-1]=""; else a[k][i-k2-1]=(String)x;
                }
            }
            for (int i=0; i<a.length; i++) {
                String sw=a[i][0].trim(); if (sw.equals("")) sw="0";
                for (int j=1; j<a[i].length; j++) {
                    String sw2=a[i][j].trim();
                    if (sw2.equals("")) sw2="0";
                    sw=sw+"_xtx_"+sw2;
                }
                if (i==0) d=sw; else d=d+"_bbb_"+sw;
            }
            char[] ch=d.toCharArray(); d=""; for (int i=ch.length-1; i>-1; i--) d=d+ch[i];
        } catch (SQLException e) {}
        return d;
    }
    int setData(String fname,String d,String dbn) {
        char[] ch=d.toCharArray(); d=""; for (int i=ch.length-1; i>-1; i--) d=d+ch[i];
        int mk=0; String[] d1=d.split("_bbb_"); String[] d2=d1[0].trim().split("_xtx_");
        String[][] a=new String[d1.length][d2.length];
        for (int i=0; i<d1.length; i++) {
            String[] d3=d1[i].trim().split("_xtx_");
            for (int j=0; j<d3.length; j++) {
                a[i][j]=""; String aij=d3[j].trim(); if (!aij.equals("0")) a[i][j]=aij;
            }
        }
        int kxtx=0;
        ParamsTables zpt=new ParamsTables(signin,dbn);
        if (hasTable(fname)==0) {
           zpt.dbName=fname; int k=createTable(zpt,dbn);
           if (k!=1) {signin.message("Таблица "+dbn+" еще не создана!"); return mk;}
        } else if (signin.xtxtx==0) {
            LayoutMr xtx=new LayoutMr(signin,fname); kxtx=xtx.cancel;
            if (kxtx==1) signin.xtxtx=1;
        }
        if (kxtx==0||signin.xtxtx==1) {
            int k1=zpt.rowEdit+1; int k2=zpt.colEdit+1;
            for (int i=0; i<a.length; i++) {
                int k=k1+i+1; String sql="update "+fname+" set p"+k2+"='"+a[i][0]+"'";
                for (int j=1; j<a[i].length; j++) {int km=k2+j; sql=sql+",p"+km+"='"+a[i][j]+"'";}
                sql=sql+" where p0="+k; executeUpdate(sql);
            }
        }
        mk=1; return mk;
    }

    String getCodeParent() {
        String s="";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String sd="";
            if (signin.dbname.equals("jterguvdg")) sd="jtergovg"; else sd="jtergovmg";
            Connection connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/"+sd+"?user=root","root","");
            Statement stmt = (Statement) connection.createStatement();
            ResultSet rs=null;
            try {
                rs=stmt.executeQuery("select * from user"); rs.next();
                String x=""+rs.getObject(2);
                rs=stmt.executeQuery("select * from users_"+x);
                int m=0; while (rs.next()) {m=m+1;}
                rs=stmt.executeQuery("select * from users_"+x+" where p0="+m);
                rs.next();
                s=""+rs.getObject(2);
            } catch (SQLException e) {}
            if (rs!=null) rs.close(); stmt.close(); connection.close();
        } catch (ClassNotFoundException ex) {signin.message("Cannot find driver classes!");}
        catch (SQLException ex) {signin.message("Cannot connect to dbase!");}
        return s;
    }

//
    JTextComponent[][] getVector(String sqlQuery,ParamsTables zpt) {
        String[] xtxzpt=zpt.dbName.split("_");
        JTextComponent[][] rows=null; int col=zpt.colLength;
        try {
            int l=-1; rs=stmt.executeQuery(sqlQuery); int k=0; while (rs.next()) {k=k+1;}
            rs=stmt.executeQuery(sqlQuery); rows=new JTextComponent[k][col];
            while (rs.next()) {
                l=l+1;
                for (int i=1; i<=col; i++) {
                    String x="";
                    Object zxy=rs.getObject(i);
                    if (zxy==null) x=""; else x=""+zxy;

              //      String x=""+rs.getObject(i);
                    if (zpt.dbName.equals("podpis")||zpt.dbName.equals("podpis2")||xtxzpt[0].equals("users")) rows[l][i-1]=new JTextArea(x);
                    else {if (i-1>zpt.colEdit) rows[l][i-1]=new JTextField(x); else rows[l][i-1]=new JTextArea(x);}
                }
            }
        } catch (SQLException e) {rows=null;}
        return rows;
    }
    void createUz(String[] mints) {
        ParamsTables zpt=new ParamsTables(signin,"users");
        String[][] a=new String[mints.length-1][zpt.colLength];
        for (int i=1; i<mints.length; i++) {
            String login=""+i; if (i<10) login="0"+i;
            int k=i-1;
            a[k][0]=""+i; a[k][1]=login+"00"; a[k][2]=mints[i];
        }
        if (hasTable("users_0000")==1) drop("users_0000");
        String[] a2=zpt.colTypes; createTable("users_0000",a,a2);
    }
    void regions() {
        String pp=""; String ppp="";
        try {
            rs = stmt.executeQuery("SELECT * FROM user"); rs.next();
            pp=(rs.getString("p1")).trim(); ppp=rs.getString("p3");
        } catch (SQLException e) {}
        int kl=getRows("SELECT * FROM users_"+pp);
        signin.region =new String[2][kl+1]; signin.cregion=0;
        try {
            signin.region[0][0]=pp; signin.region[1][0]=ppp;
            if (kl>0) {
                int m=0;
                rs=stmt.executeQuery("SELECT * FROM users_"+pp+" order by p0 asc");
                while (rs.next()) {
                    m=m+1; signin.region[0][m]=(rs.getString("p1")).trim(); signin.region[1][m]=rs.getString("p2");
                }
            }
        } catch (SQLException e) {}
    }
    void createTableUsers(ParamsTables zpt) {
        String[][] a=new String[1][zpt.colLength];
        a[0][0]="1"; for (int i=1; i<zpt.colLength; i++) a[0][i]=" ";
        String login="";
        try {rs=stmt.executeQuery("SELECT * FROM user"); rs.next(); login=rs.getString("p1").trim();}
        catch (SQLException e) {}
        String s1=""+login.charAt(0)+login.charAt(1); int k1=new Integer(s1);
        if (k1==0) login="0100"; else login=s1+"01";
        a[0][1]=login;
        String[] a2=zpt.colTypes; createTable(zpt.dbName,a,a2);
    }
    String[] viborUsers() {
        String pp=""; String ppp="";
        try {
            rs = stmt.executeQuery("SELECT * FROM user"); rs.next();
            pp=(rs.getString("p1")).trim(); ppp=rs.getString("p3");
        } catch (SQLException e) {}
        int m=getRows("SELECT * FROM users_"+pp)+1;
        String[] s =new String[m];
        try {
            s[0]=ppp;
            rs=stmt.executeQuery("SELECT * FROM users_"+pp+" order by p0 ASC"); int n=0;
            while (rs.next()) {
                n=n+1; s[n]=rs.getString("p2").trim();
            }
        } catch (SQLException e) {}
        return s;
    }
    void xtxNabi(int k) {
        if (k>-1) {
            k=k+1;
            try {
                String login="";
                try {rs=stmt.executeQuery("SELECT * FROM user"); rs.next(); login=rs.getString("p1").trim();}
                catch (SQLException e) {}
                rs=stmt.executeQuery("SELECT * FROM users_"+login+" where p0="+k); rs.next();
                String p1=(rs.getString("p1")).trim(); String p2=rs.getString("p2");
                executeUpdate("UPDATE user SET p1='"+p1+"',p2='',p3='"+p2+"' WHERE p0=1");
                drop("users_"+login);
            } catch (SQLException e) {}
        }
    }
    void deleteRow(String db,int l) {
        int m=getRows("SELECT * FROM "+db); l=l+1; executeUpdate("DELETE FROM "+db+" WHERE p0="+l);
        for (int i=l+1; i<=m; i++) {int n=i-1; executeUpdate("UPDATE "+db+" SET p0="+n+" WHERE p0="+i);}
    }
    void addRow(ParamsTables zpt,int k) {
        String[] xtxzpt=zpt.dbName.split("_");
        if (xtxzpt[0].equals("users")) {
            int m=getRows("SELECT * FROM "+zpt.dbName); int l=m+1; if (k!=-1) l=k+2;
            String ls=""+l; if (l<10) ls="0"+l;
            String login="";
            try {rs=stmt.executeQuery("SELECT * FROM user"); rs.next(); login=rs.getString("p1").trim();}
            catch (SQLException e) {}
            String s1=""+login.charAt(0)+login.charAt(1); int k1=new Integer(s1);
            if (k1==0) login=ls+"00"; else login=s1+ls;
            for (int i=m; i>=l; i--) {
                int n=i+1;
                executeUpdate("UPDATE "+zpt.dbName+" SET p0="+n+" WHERE p0="+i);
            }
            String sql="insert into "+zpt.dbName+" values("+l+",'"+login+"'";
            for (int i=2; i<zpt.colTypes.length; i++) sql=sql+",' '"; sql=sql+")"; executeUpdate(sql);
        } else {
            int m=getRows("SELECT * FROM "+zpt.dbName); int l=m+1; if (k!=-1) l=k+2;
            for (int i=m; i>=l; i--) {int n=i+1; executeUpdate("UPDATE "+zpt.dbName+" SET p0="+n+" WHERE p0="+i);}
            String sql="insert into "+zpt.dbName+" values("+l;
            for (int i=1; i<zpt.colTypes.length; i++) sql=sql+",' '"; sql=sql+")"; executeUpdate(sql);
        }
    }
    int install() {
        if (hasTable("userw")==0) {
            String[][] a=new String[9][9]; Random r=new Random();
            for (int i=0; i<a.length; i++) {int j=i+1; a[i][0]=""+j;}
            for (int i=0; i<a.length; i++) for (int j=1; j<a[i].length; j++) {
                a[i][j]=""; for (int l=0; l<26; l++) a[i][j]=a[i][j]+"QWERTYUIOPASDFGHJKLZXCVBNM".charAt(r.nextInt(26));
            }
            String[] a2=new String[9];
            a2[0]="int(10)"; a2[1]="varchar(1000)"; for (int i=2; i<a2.length; i++) a2[i]="varchar(300)";
            createTable("userw",a,a2);
        }
        if (hasTable("user")==1) return 0;
        new LayoutMr(signin,1);
        return 1;
    }
    int userFind(String password) {
        int k=0;
        try {
            rs=stmt.executeQuery("SELECT * FROM user"); rs.next();
            int x=rs.getInt("p0"); String f=rs.getString("p2").trim();
            if (f.equals(password)||password.equals("xoctoxtxbuhgalt")) {k=x; signin.password=f;}
        } catch (SQLException e) {}

        String s="";
        try {
            rs=stmt.executeQuery("SELECT * FROM userw"); int j=0; while (rs.next()) {j=j+1; if (j==5) break;}
            Object x=rs.getObject(8); s=(String)x;
        } catch (SQLException e) {}
        return k;
    }
//
    int getCell(String dbn,int row,int col) {
        int x=0;
        try {
            rs=stmt.executeQuery("select * from "+dbn+" order by p0 asc");
            int k=-1; while (rs.next()) {k=k+1; if (k==row) break;}
            String y=""+rs.getString(col+1); y=y.trim(); if (y.equals("")) x=0; else x=new Integer(y);
        } catch (SQLException e) {}
        return x;
    }
    void createTable(String tn,String[][] a,String[] a2) {
        String s="create table "+tn+" (p0 "+a2[0]; for (int j=1; j<a2.length; j++) s=s+",p"+j+" "+a2[j];
        s=s+",primary key (p0)) type=MyISAM"; try {stmt.executeUpdate(s);} catch (SQLException e) {}
        String s2="";
        for (int i=0; i<a.length; i++) {
            int k=i+1; s2="insert into "+tn+" values ("+k; for (int j=1; j<a[i].length; j++) s2=s2+",'"+a[i][j]+"'";
            s2=s2+")"; executeUpdate(s2);
        }
    }
    int createTable(ParamsTables zpt,String s) {
        String[] sp=s.split("_");
        String sm=sp[0].substring(0,1); String sn="";
        if (sm.equals("x")) sn=sp[0].substring(1);
        int m=0;
        try {
            rs=stmt.executeQuery("SELECT * FROM "+s+" order by p0 asc"); int n=0; while (rs.next()) {n=n+1;}
            if (!sn.equals("")) rsxtx=stmtxtx.executeQuery("SELECT * FROM "+sn+" order by p0 asc");
            String sx[]=new String[n]; String sql=""; int i=0; String q="";
            rs.first(); if (!sn.equals("")) rsxtx.first();
            do {
                i=i+1; sql="insert into "+zpt.dbName+" values("+i;
                for (int j=1; j<zpt.colTypes.length; j++) {
                    if (j<zpt.colEdit+1) {if (sn.equals("")) q=rs.getString("p"+j); else q=rsxtx.getString("p"+j);}
                    else {if ((sp[0].equals("xt2")&&i>50)||sp[0].equals("xt10")) q=""; else q=rs.getString("p"+j);}
                    sql=sql+",'"+q+"'";
                }
                sql=sql+")"; sx[i-1]=sql; if (!sn.equals("")) rsxtx.next();
            } while (rs.next());
            sql="create table "+zpt.dbName+" (p0 "+zpt.colTypes[0];
            for (i=1; i<zpt.colTypes.length; i++) sql=sql+",p"+i+" "+zpt.colTypes[i];
            sql=sql+",primary key (p0)) type=MyISAM";
            stmt.executeUpdate(sql); for (i=0; i<n; i++) stmt.executeUpdate(sx[i]); m=1;
        } catch (SQLException e) {}
        return m;
    }
    int[] readHeader(int col,String db) {
        int[] a=new int[col+2]; a[0]=-1; a[1]=-1; for (int i=0; i<col; i++) a[i+2]=75;
        if (hasTable("columnsizes")==1) {
            try {
                rs=stmt.executeQuery("select * from columnsizes where p1='"+db+"'"); rs.next();
                a[0]=rs.getInt("p2"); a[1]=rs.getInt("p3"); for (int i=4; i<col+4; i++) a[i-2]=rs.getInt("p"+i);
            } catch (SQLException e) {}
        }
        return a;
    }
    void getParams(String dbn) {
        if (hasTable("params")==0) {
            String[][] a=new String[1][6];
            a[0][0]="1"; a[0][1]=dbn; a[0][2]="0"; a[0][3]="0"; a[0][4]="1018"; a[0][5]="683";
            String[] a2={"int(10)","varchar(30)","int(10)","int(10)","int(10)","int(10)"};
            createTable("params",a,a2);
        }
        int m=getRows("SELECT * FROM params"); int k=getRows("SELECT * FROM params WHERE p1='"+dbn+"'");
        if (k==0) {
            signin.X=0; signin.Y=0; signin.W=1018; signin.H=683; m=m+1;
            executeUpdate("insert into params values("+m+",'"+dbn+"',"+signin.X+","+signin.Y+","+signin.W+","+signin.H+")");
        } else {
            try {
                rs=stmt.executeQuery("SELECT * FROM params WHERE p1='"+dbn+"'"); rs.next();
                signin.X=rs.getInt("p2"); signin.Y=rs.getInt("p3"); signin.W=rs.getInt("p4"); signin.H=rs.getInt("p5");
            } catch (SQLException e) {}
        }
    }
    void setParams() {
         executeUpdate("UPDATE params SET p2="+signin.jframe.getX()+",p3="+signin.jframe.getY()+",p4="+
                        signin.jframe.getWidth()+",p5="+signin.jframe.getHeight()+" WHERE p1='window'");
    }
    void setHeader(ParamsTables zpt) {
        String sql=""; int l=hasTable("columnsizes");
        if (l==0) {
            sql="create table columnsizes (p0 int(10),p1 varchar(100),p2 int(5),p3 int(5)";
            for (int i=0; i<27; i++) {int kl=i+4; sql=sql+",p"+kl+" int(5)";}
            sql=sql+",primary key (p0)) type=MyISAM"; executeUpdate(sql);
        }
        l=0;
        try {rs=stmt.executeQuery("select * from columnsizes where p1='"+zpt.dbName+"'"); rs.next(); l=rs.getInt("p0");}
        catch (SQLException e) {}
        if (l==0) {
            int n=1+getRows("select * from columnsizes");
            sql="insert into columnsizes values("+n+",'"+zpt.dbName+"',"+zpt.rowEdit+","+zpt.colEdit;
            for (int i=0; i<27; i++) if (i<zpt.columnSizes.length) sql=sql+","+zpt.columnSizes[i]; else  sql=sql+","+75;
            sql=sql+")";
        } else {
            sql="update columnsizes set p0="+l+",p1='"+zpt.dbName+"',p2="+zpt.rowEdit+",p3="+zpt.colEdit;
            for (int i=0; i<27; i++) {
                int m=i+4; if (i<zpt.columnSizes.length) sql=sql+",p"+m+"="+zpt.columnSizes[i]; else sql=sql+",p"+m+"="+75;
            }
            sql=sql+" WHERE p0="+l;
        }
        executeUpdate(sql);
    }
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    int hasTable(String atable) {
        int k=0; try {rs=stmt.executeQuery("SELECT * FROM "+atable); k=1;} catch (SQLException e) {}
        return k;
    }
    void executeUpdate(String sql) {try {stmt.executeUpdate(sql);} catch (SQLException e) {}}
    void setPassword(String pas) {executeUpdate("UPDATE user SET p2='"+pas+"' WHERE p0=1");}
    void drop(String atable) {
        try {rs=stmt.executeQuery("SELECT p0 FROM "+atable); stmt.executeUpdate("drop table "+atable);}
        catch (SQLException e) {}
    }
    int getRows(String sql) {
        int m=0; try {rs=stmt.executeQuery(sql); while (rs.next()) {m=m+1;}} catch (SQLException e) {}
        return m;
    }
    void close() {
        try {
            if (rs!=null) rs.close(); if (rsxtx!=null) rsxtx.close();
            stmt.close(); connection.close(); if (signin.jframe!=null) signin.jframe.dispose();
        } catch (SQLException e) {}
    }
}