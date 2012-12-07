package buhuchet;

import java.util.*;
import java.lang.*;
import javax.swing.*;
import javax.swing.text.*;

public class SetCells {
    Buhuchet      signin;
    ParamsTables  zpt;
    JTextComponent[][] rows;
    String[]      childs;
    int           ident=0;
    String        sqlQuery;
    String[]      sx;

    int getx(int row,int col) {
        int k=0; String x=rows[row][col].getText().trim(); if (!x.equals("")) k=new Integer(x); return k;
    }
    int getx(JTextComponent[][] rows,int row,int col) {
        if (rows==null) return 0;
        int k=0; String x=rows[row][col].getText().trim(); if (!x.equals("")) k=new Integer(x); return k;
    }
    void set(double k,int row,int col) {
        String x=""; if (k!=0) x=""+k;
        if (col>zpt.colEdit) rows[row][col]=new JTextField(x); else rows[row][col]=new JTextArea(x);
        savex(row,col);
    }
    void set2(int k,int row,int col) {
        String x=""; if (k!=0) x=""+k;
        if (col>zpt.colEdit) rows[row][col]=new JTextField(x); else rows[row][col]=new JTextArea(x);
        savex(row,col);
    }
    void savex(int row,int col) {
        String str=rows[row][col].getText(); if (col>=zpt.colEdit+1) str=str.trim(); if (str.equals("0")) str="";
        int rowy=row+1;
        String sql="update "+zpt.dbName+" set p"+col+"='"+str+"' where p0="+rowy; signin.sql.executeUpdate(sql);
    }
    JTextComponent[][] otchets(int row,int col) {
        sx=zpt.dbName.split("_"); String s=sx[0];
        savex(row,col);
        if (s.equals("xtx1"))  setCells_xtx1(row,col);  if (s.equals("xtx2"))  setCells_xtx2(row,col);
        if (s.equals("xtx3"))  setCells_xtx3(row,col);  if (s.equals("xtx4"))  setCells_xtx4(row,col);
        if (s.equals("xtx5"))  setCells_xtx5(row,col);  if (s.equals("xtx6"))  setCells_xtx6(row,col);
        if (s.equals("xtx7"))  setCells_xtx7(row,col);  if (s.equals("xtx8"))  setCells_xtx8(row,col);
        if (s.equals("xtx9"))  setCells_xtx9(row,col);  if (s.equals("xtx10")) setCells_xtx10(row,col);
        return rows;
    }
    void otchets(JTextComponent[][] rowsx) {rows=rowsx;}
    void setCells_xtx1(int row,int col) {
        int kp=signin.region[0].length;
        if (col==2||col==5||col==8||col==11) {
            double n=0;
            int k=getx(row,col); int l=getx(row,col+1);
            if (k!=0) {n=(l+0.0)/k-1; double nx=Math.round(Math.abs(n)*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(n,row,col+2);
            k=0; for (int i=1; i<kp; i++) k=k+getx(i-1,col); set2(k,kp-1,col);
            l=getx(kp-1,col+1);
            if (k!=0) {n=(l+0.0)/k-1; double nx=Math.round(Math.abs(n)*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(n,kp-1,col+2);
        }
        if (col==3||col==6||col==9||col==12) {
            double n=0;
            int k=getx(row,col-1); int l=getx(row,col);
            if (k!=0) {n=(l+0.0)/k-1; double nx=Math.round(Math.abs(n)*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(n,row,col+1);
            k=0; for (int i=1; i<kp; i++) k=k+getx(i-1,col); set2(k,kp-1,col);
            l=getx(kp-1,col-1);
            if (k!=0) {n=(k+0.0)/l-1; double nx=Math.round(Math.abs(n)*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(n,kp-1,col+1);
        }
        if (col==4||col==7||col==10||col==13) {
            double n=0;
            int k=getx(row,col-2); int l=getx(row,col-1);
            if (k!=0) {n=(l+0.0)/k-1; double nx=Math.round(Math.abs(n)*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(n,row,col);
        }
    }
    void setCells_xtx2(int row,int col) {
        int kp=signin.region[0].length; ParamsTables zpt_xtx1=new ParamsTables(signin,"tx1");
        String dbn="xtx1_"+signin.region[0][0]+"_"+sx[2]+"_"+sx[3];
        JTextComponent[][] rows_xtx1Old=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xtx1);
        if (col==8||col==10||col==12||col==14) {
            double n=0; int col2=0;
            if (col==8) col2=8; else {if (col==10) col2=9; else {if (col==12) col2=11; else col2=12;}}
            int k=getx(rows_xtx1Old,row,col2); int l=getx(row,col);
            if (k!=0) {n=(l+0.0)/k; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(n,row,col+1);
            k=0; for (int i=1; i<kp; i++) k=k+getx(rows_xtx1Old,i-1,col2);
            l=0; for (int i=1; i<kp; i++) l=l+getx(i-1,col); set2(l,kp-1,col);
            if (k!=0) {n=(l+0.0)/k; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(n,kp-1,col+1);
        }
        if (col==2||col==5) {
            double n=0;
            int k=getx(row,col); int l=getx(row,col+1);
            if (k!=0) {n=(l+0.0)/k-1; double nx=Math.round(Math.abs(n)*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(n,row,col+2);
            k=0; for (int i=1; i<kp; i++) k=k+getx(i-1,col); set2(k,kp-1,col);
            l=getx(kp-1,col+1);
            if (k!=0) {n=(l+0.0)/k-1; double nx=Math.round(Math.abs(n)*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(n,kp-1,col+2);
        }
        if (col==3||col==6) {
            double n=0;
            int k=getx(row,col-1); int l=getx(row,col);
            if (k!=0) {n=(l+0.0)/k-1; double nx=Math.round(Math.abs(n)*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(n,row,col+1);
            k=0; for (int i=1; i<kp; i++) k=k+getx(i-1,col); set2(k,kp-1,col);
            l=getx(kp-1,col-1);
            if (k!=0) {n=(k+0.0)/l-1; double nx=Math.round(Math.abs(n)*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(n,kp-1,col+1);
        }
    }
    void setCells_xtx3(int row,int col) {
        int kp=signin.region[0].length; ParamsTables zpt_xtx1=new ParamsTables(signin,"tx1");
        String dbn="xtx1_"+signin.region[0][0]+"_"+sx[2]+"_"+sx[3];
        JTextComponent[][] rows_xtx1Old=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xtx1);
        if (col==2||col==4||col==6||col==8) {
            double n=0; int col2=0;
            if (col==2) col2=8; else {if (col==4) col2=9; else {if (col==6) col2=11; else col2=12;}}
            int k=getx(rows_xtx1Old,row,col2); int l=getx(row,col);
            if (k!=0) {n=(l+0.0)/k; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(n,row,col+1);
            k=0; for (int i=1; i<kp; i++) k=k+getx(rows_xtx1Old,i-1,col2);
            l=0; for (int i=1; i<kp; i++) l=l+getx(i-1,col); set2(l,kp-1,col);
            if (k!=0) {n=(l+0.0)/k; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(n,kp-1,col+1);
        }
        if (col>8) {int k=0; for (int i=1; i<kp; i++) k=k+getx(i-1,col); set2(k,kp-1,col);}
    }
    void setCells_xtx4(int row,int col) {
        int kp=signin.region[0].length; ParamsTables zpt_xtx2=new ParamsTables(signin,"tx2");
        String dbn="xtx2_"+signin.region[0][0]+"_"+sx[2]+"_"+sx[3];
        JTextComponent[][] rows_xtx2Old=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xtx2);
        if (col>1&&col<6) {int k=0; for (int i=1; i<kp; i++) k=k+getx(i-1,col); set2(k,kp-1,col);}
        if (col==6||col==8||col==10||col==12) {
            double n=0; int col2=0;
            if (col==6) col2=2; else {if (col==8) col2=3; else {if (col==10) col2=5; else col2=6;}}
            int k=getx(rows_xtx2Old,row,col2); int l=getx(row,col);
            if (k!=0) {n=(l+0.0)/k; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(n,row,col+1);
            k=0; for (int i=1; i<kp; i++) k=k+getx(rows_xtx2Old,i-1,col2);
            l=0; for (int i=1; i<kp; i++) l=l+getx(i-1,col); set2(l,kp-1,col);
            if (k!=0) {n=(l+0.0)/k; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(n,kp-1,col+1);
        }
    }
    void setCells_xtx5(int row,int col) {
        int kp=signin.region[0].length; int k=0; for (int i=1; i<kp; i++) k=k+getx(i-1,col); set2(k,kp-1,col);
    }
    void setCells_xtx6(int row,int col) {
        int kp=signin.region[0].length; int k=0; for (int i=1; i<kp; i++) k=k+getx(i-1,col); set2(k,kp-1,col);
    }
    void setCells_xtx7(int row,int col) {
        int kp=signin.region[0].length; int k=0; for (int i=1; i<kp; i++) k=k+getx(i-1,col); set2(k,kp-1,col);
    }
    void setCells_xtx8(int row,int col) {
        int kp=signin.region[0].length; int k=0; for (int i=1; i<kp; i++) k=k+getx(i-1,col); set2(k,kp-1,col);
    }
    void setCells_xtx9(int row,int col) {
        int kp=signin.region[0].length; int k=0; for (int i=1; i<kp; i++) k=k+getx(i-1,col); set2(k,kp-1,col);
    }
    void setCells_xtx10(int row,int col) {
        int m=new Integer(sx[3]); int kp=signin.region[0].length;
        if (col==2||col==6) {
            double n=0;
            int k=getx(row,col); int l=getx(row,col+1);
            if (l!=0) {n=(k+0.0)/l; double nx=Math.round(n*100); nx=nx/100; n=nx;} else n=0;
            set(n,row,col+2);
            if (l!=0) {n=(k+0.0)/(l*m); double nx=Math.round(n*100); nx=nx/100; n=nx;} else n=0;
            set(n,row,col+3);
            k=0; for (int i=1; i<kp; i++) k=k+getx(i-1,col); set2(k,kp-1,col);
            l=getx(kp-1,col+1);
            if (l!=0) {n=(k+0.0)/l; double nx=Math.round(n*100); nx=nx/100; n=nx;} else n=0;
            set(n,kp-1,col+2);
            if (l!=0) {n=(k+0.0)/(l*m); double nx=Math.round(n*100); nx=nx/100; n=nx;} else n=0;
            set(n,kp-1,col+3);
        }
        if (col==3||col==7) {
            double n=0;
            int k=getx(row,col-1); int l=getx(row,col);
            if (l!=0) {n=(k+0.0)/l; double nx=Math.round(n*100); nx=nx/100; n=nx;} else n=0;
            set(n,row,col+1);
            if (l!=0) {n=(k+0.0)/(l*m); double nx=Math.round(n*100); nx=nx/100; n=nx;} else n=0;
            set(n,row,col+2);
            k=getx(kp-1,col-1);
            l=0; for (int i=1; i<kp; i++) l=l+getx(i-1,col); set2(l,kp-1,col);
            if (l!=0) {n=(k+0.0)/l; double nx=Math.round(n*100); nx=nx/100; n=nx;} else n=0;
            set(n,kp-1,col+1);
            if (l!=0) {n=(k+0.0)/(l*m); double nx=Math.round(n*100); nx=nx/100; n=nx;} else n=0;
            set(n,kp-1,col+2);
        }
        if (col==4||col==5||col==8||col==9) {
            double n=0;
            int k=getx(row,2); int l=getx(row,3);
            if (l!=0) {n=(k+0.0)/l; double nx=Math.round(n*100); nx=nx/100; n=nx;} else n=0;
            set(n,row,4);
            if (l!=0) {n=(k+0.0)/(l*m); double nx=Math.round(n*100); nx=nx/100; n=nx;} else n=0;
            set(n,row,5);

            k=getx(row,6); l=getx(row,7);
            if (l!=0) {n=(k+0.0)/l; double nx=Math.round(n*100); nx=nx/100; n=nx;} else n=0;
            set(n,row,8);
            if (l!=0) {n=(k+0.0)/(l*m); double nx=Math.round(n*100); nx=nx/100; n=nx;} else n=0;
            set(n,row,9);
        }
    }
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public SetCells(Buhuchet signinx,ParamsTables z) {
        signin=signinx; zpt=z;
//signin.message("SetCells="+signin.edit);
        if (signin.edit==0) {
            if (signin.sql.hasTable(zpt.dbName)==0) {
                String[] xtxzpt=zpt.dbName.split("_");
                if (xtxzpt[0].equals("users")) signin.sql.createTableUsers(zpt);
                else {
                    String[][] a=new String[1][zpt.colLength];
                    a[0][0]="1"; for (int i=1; i<zpt.colLength; i++) a[0][i]=" ";
                    String[] a2=zpt.colTypes; signin.sql.createTable(zpt.dbName,a,a2);
                }
            }
            return;
        }
        String dbn=zpt.dbName; String sg=dbn.substring(0,2);
        String[] ymx=signin.data.split("-"); String ym="_"+signin.region[0][signin.cregion]+"_"+ymx[0]+"_"+ymx[1];
        String F1=zpt.dbName+ym; String F4="x"+zpt.dbName+ym;
        if (signin.sql.hasTable(F1)==0) {
            String d=dbn;
            zpt.dbName=F1; if (signin.sql.createTable(zpt,d)==0) {signin.message("Шаблон не создан!"); return;}
            zpt.dbName=F4; if (!sg.equals("tx")) {if (signin.uID==3) {String dx=getLast(F4); if (!dx.equals("")) d=dx;}}
            signin.sql.createTable(zpt,d);
        } else {if (dbn.equals("t2")) if (!getLast(F1).equals("")) ident=1;}
        if (signin.zadach==1) {
        if (signin.parentNode+1==signin.uID||signin.uID==3) {
            zpt.dbName=F4; rows=getCells(zpt.dbName); String[] sx=zpt.dbName.split("_");
//signin.message("SetCells="+F4);
            if (sx[0].equals("xt2")) {
                String d=getLast(zpt.dbName,1);
                if (!d.equals("")) {
                    ident=1;
                    int k=signin.sql.getCell(d,0,4); set(k,1,1); save(1,1);
                    int m=signin.sql.getCell(d,0,5); set(m,1,2); save(1,2);
                    k=signin.sql.getCell(d,1,4);     set(k,2,1); save(2,1);
                    m=signin.sql.getCell(d,1,5);     set(m,2,2); save(2,2);
                }
                String db="xt4_"+sx[1]+"_"+sx[2]+"_"+sx[3];
                if (signin.sql.hasTable(db)==1) {
                    int k=signin.sql.getCell(db,12,4); set(k,7,1); save(7,1);
                    int m=signin.sql.getCell(db,12,5); set(m,7,2); save(7,2);
                }

                db="xt5_"+sx[1]+"_"+sx[2]+"_"+sx[3];
                if (signin.sql.hasTable(db)==1) {
                    int k=signin.sql.getCell(db,0,4)+signin.sql.getCell(db,19,4)+signin.sql.getCell(db,21,4);
                    int l=signin.sql.getCell(db,0,6)+signin.sql.getCell(db,19,6)+signin.sql.getCell(db,21,6);
                    set(k,10,1); save(10,1);
                    set(l,10,2); save(10,2);
                }
//
                db="xt1_"+sx[1]+"_"+sx[2]+"_"+sx[3]; int k=0; int m=0; int k2=0; int m2=0;
                if (signin.sql.hasTable(db)==1) {
                    k=signin.sql.getCell(db,44,3)+signin.sql.getCell(db,45,3)+
                      signin.sql.getCell(db,44,7)+signin.sql.getCell(db,45,7)+
                      signin.sql.getCell(db,44,9)+signin.sql.getCell(db,45,9)+
                      signin.sql.getCell(db,44,13)+signin.sql.getCell(db,45,13);
                    m=signin.sql.getCell(db,44,5)+signin.sql.getCell(db,45,5)+
                      signin.sql.getCell(db,44,8)+signin.sql.getCell(db,45,8)+
                      signin.sql.getCell(db,44,11)+signin.sql.getCell(db,45,11)+
                      signin.sql.getCell(db,44,15)+signin.sql.getCell(db,45,15);

                    k2=signin.sql.getCell(db,44,7);
                    m2=signin.sql.getCell(db,44,8);
                }
                db="xt4_"+sx[1]+"_"+sx[2]+"_"+sx[3];
                if (signin.sql.hasTable(db)==1) {
                    k=k+signin.sql.getCell(db,14,4)+signin.sql.getCell(db,15,4);
                    m=m+signin.sql.getCell(db,14,5)+signin.sql.getCell(db,15,5);

                    k2=k2+signin.sql.getCell(db,15,4);
                    m2=m2+signin.sql.getCell(db,15,5);
                }
                db="xt6_"+sx[1]+"_"+sx[2]+"_"+sx[3];
                if (signin.sql.hasTable(db)==1) {
                    k=k+signin.sql.getCell(db,0,4)+signin.sql.getCell(db,2,4);
                    m=m+signin.sql.getCell(db,0,5)+signin.sql.getCell(db,2,5);

                    k2=k2+signin.sql.getCell(db,2,4);
                    m2=m2+signin.sql.getCell(db,2,5);
                }
           //     set(k,13,1); save(13,1); set(m,13,2); save(13,2);
                set(k2,26,1); save(26,1); set(m2,26,2); save(26,2);
//
            }
            if (sx[0].equals("xt3")) {
                String db="xt1_"+sx[1]+"_"+sx[2]+"_"+sx[3];
                int x=signin.sql.getCell(db,44,3)+signin.sql.getCell(db,45,3)+signin.sql.getCell(db,44,7)+
                    signin.sql.getCell(db,45,7)+signin.sql.getCell(db,44,9)+signin.sql.getCell(db,45,9)+
                    signin.sql.getCell(db,44,13)+signin.sql.getCell(db,45,13);
                    set(x,1,1); save(1,1);
                int y=signin.sql.getCell(db,44,5)+signin.sql.getCell(db,45,5)+signin.sql.getCell(db,44,8)+
                    signin.sql.getCell(db,45,8)+signin.sql.getCell(db,44,11)+signin.sql.getCell(db,45,11)+
                    signin.sql.getCell(db,44,15)+signin.sql.getCell(db,45,15);
                    set(y,1,2); save(1,2);
            }
        }
        } else
        if (signin.uID==3) {
            zpt.dbName=F1; rows=getCells(zpt.dbName); String[] sx=zpt.dbName.split("_");
            if (dbn.equals("t2")) {
                String d=getLast(zpt.dbName);
                if (!d.equals("")) {
                    ident=1;
                    int k=signin.sql.getCell(d,48,4); set(k,1,1); save(1,1);
                    int m=signin.sql.getCell(d,48,5); set(m,1,2); save(1,2);
                    k=signin.sql.getCell(d,49,4);     set(k,2,1); save(2,1);
                    m=signin.sql.getCell(d,49,5);     set(m,2,2); save(2,2);
                }
                String db="t4_"+sx[1]+"_"+sx[2]+"_"+sx[3];
                if (signin.sql.hasTable(db)==1) {
                    int k=signin.sql.getCell(db,12,4); set(k,7,1); save(7,1);
                    int m=signin.sql.getCell(db,12,5); set(m,7,2); save(7,2);
                }

                db="t5_"+sx[1]+"_"+sx[2]+"_"+sx[3];
                if (signin.sql.hasTable(db)==1) {
                    int k=signin.sql.getCell(db,0,4)+signin.sql.getCell(db,19,4)+signin.sql.getCell(db,21,4);
                    int l=signin.sql.getCell(db,0,6)+signin.sql.getCell(db,19,6)+signin.sql.getCell(db,21,6);
                    set(k,10,1); save(10,1);
                    set(l,10,2); save(10,2);
                }
//
                db="t1_"+sx[1]+"_"+sx[2]+"_"+sx[3]; int k=0; int m=0; int k2=0; int m2=0;
                if (signin.sql.hasTable(db)==1) {
                    k=signin.sql.getCell(db,44,3)+signin.sql.getCell(db,45,3)+
                      signin.sql.getCell(db,44,7)+signin.sql.getCell(db,45,7)+
                      signin.sql.getCell(db,44,9)+signin.sql.getCell(db,45,9)+
                      signin.sql.getCell(db,44,13)+signin.sql.getCell(db,45,13);
                    m=signin.sql.getCell(db,44,5)+signin.sql.getCell(db,45,5)+
                      signin.sql.getCell(db,44,8)+signin.sql.getCell(db,45,8)+
                      signin.sql.getCell(db,44,11)+signin.sql.getCell(db,45,11)+
                      signin.sql.getCell(db,44,15)+signin.sql.getCell(db,45,15);

                    k2=signin.sql.getCell(db,44,7);
                    m2=signin.sql.getCell(db,44,8);
                }
                db="t4_"+sx[1]+"_"+sx[2]+"_"+sx[3];
                if (signin.sql.hasTable(db)==1) {
                    k=k+signin.sql.getCell(db,14,4)+signin.sql.getCell(db,15,4);
                    m=m+signin.sql.getCell(db,14,5)+signin.sql.getCell(db,15,5);

                    k2=k2+signin.sql.getCell(db,15,4);
                    m2=m2+signin.sql.getCell(db,15,5);
                }
                db="t6_"+sx[1]+"_"+sx[2]+"_"+sx[3];
                if (signin.sql.hasTable(db)==1) {
                    k=k+signin.sql.getCell(db,0,4)+signin.sql.getCell(db,2,4);
                    m=m+signin.sql.getCell(db,0,5)+signin.sql.getCell(db,2,5);

                    k2=k2+signin.sql.getCell(db,2,4);
                    m2=m2+signin.sql.getCell(db,2,5);
                }
                set(k,13,1); save(13,1); set(m,13,2); save(13,2);
                set(k2,26,1); save(26,1); set(m2,26,2); save(26,2);
//
            }
            if (sx[0].equals("t3")) {
                String db="t1_"+sx[1]+"_"+sx[2]+"_"+sx[3];
                int x=signin.sql.getCell(db,44,3)+signin.sql.getCell(db,45,3)+signin.sql.getCell(db,44,7)+
                    signin.sql.getCell(db,45,7)+signin.sql.getCell(db,44,9)+signin.sql.getCell(db,45,9)+
                    signin.sql.getCell(db,44,13)+signin.sql.getCell(db,45,13);
                    set(x,1,1); save(1,1);
                int y=signin.sql.getCell(db,44,5)+signin.sql.getCell(db,45,5)+signin.sql.getCell(db,44,8)+
                    signin.sql.getCell(db,45,8)+signin.sql.getCell(db,44,11)+signin.sql.getCell(db,45,11)+
                    signin.sql.getCell(db,44,15)+signin.sql.getCell(db,45,15);
                    set(y,1,2); save(1,2);
            }
        }
        if (!sg.equals("tx")&&signin.uID<3) {
            if (signin.zadach==0) {
                if (signin.region[0][0].equals("0000")) {
                    if (signin.cregion==0) summator(F4);
                } else {
                   if (signin.cregion>0) summator(F1);
                   summator(F4);
                }
            }
            else {
                if (signin.parentNode==signin.uID) {
          //          summator(F1);
                    summator(F4);
                }
            }
        }
        zpt.dbName=F1; if (signin.edit==2||signin.edit==3) zpt.dbName=F4;
    }
    void summator(String dbn) {
        zpt.dbName=dbn; rows=getCells(zpt.dbName);
/*
        int m=0;
        if (signin.zadach==1) for (int i=0; i<rows.length; i++) {
            for (int j=zpt.colEdit+1; j<zpt.colLength; j++) if (!rows[i][j].getText().trim().equals("")) {m=1; break;}
            if (m==1) break;
        }
*/
//        if (signin.zadach==0||(signin.zadach==1&&m==0)) {
        if (signin.zadach==0||signin.zadach==1) {
            String[] sw=zpt.dbName.split("_"); String sd="_"+sw[2]+"_"+sw[3];
            int kr=rows.length-zpt.rowEdit; int kc=zpt.colLength-zpt.colEdit;
            for (int i=1; i<kr; i++) for (int j=1; j<kc; j++) set(0,i,j);
            for (int i=1; i<signin.region[0].length; i++) {
                String d=sw[0]+"_"+signin.region[0][i]+sd; JTextComponent[][] rs=getCells(d); if (rs!=null) makeTable(rs);
            }
        }
        save(zpt.dbName);
    }
    int get(JTextComponent[][] rows,int row,int col) {
        row=zpt.rowEdit+row; col=zpt.colEdit+col;
        int k=0; String x=rows[row][col].getText().trim(); if (!x.equals("")) k=new Integer(x); return k;
    }
    void makeTable(JTextComponent[][] rs) {
        int kr=rows.length-zpt.rowEdit; int kc=zpt.colLength-zpt.colEdit;
        for (int i=1; i<kr; i++) for (int j=1; j<kc; j++) set(get(rows,i,j)+get(rs,i,j),i,j);
    }
    void save(String dbn) {
        for (int i=0; i<rows.length; i++) {
            int k=i+1; String sql="update "+dbn+" set p0="+rows[i][0].getText();
            for (int j=1; j<rows[i].length; j++) sql=sql+",p"+j+"='"+rows[i][j].getText()+"'";
            sql=sql+" where p0="+k; signin.sql.executeUpdate(sql);
        }
    }
    String getLast(String dbn,int k) {
        String d="";
        String[] sx=dbn.split("_");
        String dbx=sx[0]+"_"+sx[1]+"_"+sx[2]+"_"; String db=""; int month=new Integer(sx[3]);
        for (int i=month-1; i>0; i--) {
            if (i<10) db=dbx+"0"+i; else db=dbx+i;
            if (signin.sql.hasTable(db)==1) {d=db; break;}
        }
        return d;
    }
    String getLast(String dbn) {
        String d="";
        if (signin.zadach==0) {
            String[] sx=dbn.split("_");
            String dbx=sx[0]+"_"+sx[1]+"_"+sx[2]+"_"; String db=""; int month=new Integer(sx[3]);
            for (int i=month-1; i>0; i--) {
                if (i<10) db=dbx+"0"+i; else db=dbx+i;
                if (signin.sql.hasTable(db)==1) {d=db; break;}
            }
        }
        return d;
    }
    JTextComponent[][] getCells(String dbn) {
        sqlQuery="select * from "+dbn+" order by p0 asc"; return signin.sql.getVector(sqlQuery,zpt);
    }
    JTextComponent[][] getCells() {rows=getCells(zpt.dbName); if (rows!=null) zpt.rowSizes=new int[rows.length]; return rows;}
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    JTextComponent[][] setCells(int row,int col) {
        String[] sx=zpt.dbName.split("_"); String s=sx[0];
        save(row-zpt.rowEdit,col-zpt.colEdit);
        if (signin.edit==0) return rows;
        if (signin.zadach==1) {
            if (s.equals("xt1"))  setCells_t1(row,col);  if (s.equals("xt2"))  setCells_t2(row,col);
            if (s.equals("xt3"))  setCells_t3(row,col);  if (s.equals("xt4"))  setCells_t4(row,col);
            if (s.equals("xt5"))  setCells_t5(row,col);  if (s.equals("xt6"))  setCells_t6(row,col);
            if (s.equals("xt7"))  setCells_t7(row,col);  if (s.equals("xt8"))  setCells_t8(row,col);
            if (s.equals("xt9"))  setCells_t9(row,col);  if (s.equals("xt10")) setCells_t10(row,col);
            if (s.equals("xt11")) setCells_t11(row,col); if (s.equals("xt12")) setCells_t12(row,col);
        } else {
            if (s.equals("t1"))  setCells_t1(row,col);  if (s.equals("t2"))  setCells_t2(row,col);
            if (s.equals("t3"))  setCells_t3(row,col);  if (s.equals("t4"))  setCells_t4(row,col);
            if (s.equals("t5"))  setCells_t5(row,col);  if (s.equals("t6"))  setCells_t6(row,col);
            if (s.equals("t7"))  setCells_t7(row,col);  if (s.equals("t8"))  setCells_t8(row,col);
            if (s.equals("t9"))  setCells_t9(row,col);  if (s.equals("t10")) setCells_t10(row,col);
            if (s.equals("t11")) setCells_t11(row,col); if (s.equals("t12")) setCells_t12(row,col);
        }
        return rows;
    }
    void save(int row,int col) {
        row=zpt.rowEdit+row; col=zpt.colEdit+col;
        String[] sw=zpt.dbName.split("_");
        String str=rows[row][col].getText();
        if (col>=zpt.colEdit+1) {if (!zpt.dbName.equals("podpis")&&!zpt.dbName.equals("podpis2")) str=str.trim();}
        if (str.equals("0")) str="";
        int rowy=row+1;
        String sql="update "+zpt.dbName+" set p"+col+"='"+str+"' where p0="+rowy; signin.sql.executeUpdate(sql);
        if (signin.edit==0) return;
        int x=0; if (!str.equals("")) x=new Integer(str);
        int y=signin.sql.getCell("x"+zpt.dbName,row,col);
        int z=0; String d=getLast("x"+zpt.dbName); if (!d.equals("")) z=signin.sql.getCell(d,row,col);
        int w=x+z-y;
        int q=x+z;
        if (sw[0].equals("t2")&&row==0&&ident==1) return;
        if (sw[0].equals("t2")&&row==1&&ident==1) return;
        if (sw[0].equals("t2")&&row>47) q=x;
        if (sw[0].equals("t10")) q=x;
        if (q==0) str=""; else str=""+q;
        sql="update x"+zpt.dbName+" set p"+col+"='"+str+"' where p0="+rowy; signin.sql.executeUpdate(sql);
        if (sw[0].equals("t2")&&row>47) return;
        if (sw[0].equals("t10")) return;
        String login=signin.region[0][signin.cregion]; int kd=new Integer(sw[3]);
        for (int i=kd+1; i<13; i++) {
            String sw3=""+i; if (i<10) sw3="0"+i; d="x"+sw[0]+"_"+login+"_"+sw[2]+"_"+sw3;
            if (signin.sql.hasTable(d)==1) {
                x=signin.sql.getCell(d,row,col)+w; if (x==0) str=""; else str=""+x;
                sql="update "+d+" set p"+col+"='"+str+"' where p0="+rowy; signin.sql.executeUpdate(sql);
            }
        }
    }
    int get(int row,int col) {
        if (rows==null) return 0;
        row=zpt.rowEdit+row; col=zpt.colEdit+col; int k=0; int l=0; String x=rows[row][col].getText().trim();
        if (!x.equals("")) {try {k=new Integer(x);} catch (NumberFormatException e) {l=1;}}
        if (l==1) {k=0; signin.message(x+" - это не число!");}
        return k;
    }
    void set(int k,int row,int col) {
        row=zpt.rowEdit+row; col=zpt.colEdit+col;
        String x=""; if (k>0) x=""+k;
        if (col>zpt.colEdit) rows[row][col]=new JTextField(x); else rows[row][col]=new JTextArea(x);
    }
    void setCells_t1(int row,int col)  {
        for (int i=1; i<15; i++) {
            int k=0; for (int j=1; j<45; j++) {if (j==11||j==18||j==41) continue; k=k+get(j,i);}
            set(k,45,i); save(45,i);
        }
    }
    void setCells_t2(int row,int col)  {
        int x=0; for (int i=5; i<13; i++) x=x+get(i,1); set(x,3,1); save(3,1);
        x=0; for (int i=5; i<13; i++) x=x+get(i,2);     set(x,3,2); save(3,2);
/*
        x=get(20,1)+get(22,1)+get(24,1)+get(27,1); set(x,14,1); save(14,1);
        x=get(20,2)+get(22,2)+get(24,2)+get(27,2); set(x,14,2); save(14,2);
*/
        x=get(44,1)+get(45,1)+get(47,1)+get(48,1); set(x,42,1); save(42,1);
        x=get(44,2)+get(45,2)+get(47,2)+get(48,2); set(x,42,2); save(42,2);

        x=get(1,1)+get(3,1)-get(13,1)-get(38,1)-get(40,1)-get(42,1); set(x,49,1); save(49,1);
        x=get(1,2)+get(3,2)-get(13,2)-get(38,2)-get(40,2)-get(42,2); set(x,49,2); save(49,2);

        x=get(2,1)+get(4,1)-get(14,1)-get(39,1)-get(41,1)-get(43,1); set(x,50,1); save(50,1);
        x=get(2,2)+get(4,2)-get(14,2)-get(39,2)-get(41,2)-get(43,2); set(x,50,2); save(50,2);
/*
        x=get(19,1)+get(21,1)+get(23,1)+get(25,1)+get(26,1); set(x,13,1); save(13,1);
        x=get(19,2)+get(21,2)+get(23,2)+get(25,2)+get(26,2); set(x,13,2); save(13,2);
*/
    }
    void setCells_t3(int row,int col)  {}
    void setCells_t4(int row,int col)  {}
    void setCells_t5(int row,int col)  {}
    void setCells_t6(int row,int col)  {
        int x=0;
        for (int i=1; i<9; i++) {
            x=get(i,3)+get(i,5)+get(i,7);
            set(x,i,1); save(i,1);
            x=get(i,4)+get(i,6)+get(i,8);
            set(x,i,2); save(i,2);
        }
    }
    void setCells_t7(int row,int col)  {}
    void setCells_t8(int row,int col)  {}
    void setCells_t9(int row,int col)  {
        int x=0; for (int i=1; i<12; i++) x=x+get(i,1); set(x,12,1); save(12,1);
        x=0; for (int i=1; i<12; i++) x=x+get(i,2); set(x,12,2); save(12,2);
        x=0; for (int i=1; i<12; i++) x=x+get(i,3); set(x,12,3); save(12,3);
        x=0; for (int i=1; i<12; i++) x=x+get(i,4); set(x,12,4); save(12,4);
    }
    void setCells_t10(int row,int col) {}
    void setCells_t11(int row,int col) {}
    void setCells_t12(int row,int col) {}
}