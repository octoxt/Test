package buhuchet;

import javax.swing.*;
import javax.swing.text.*;
import java.lang.*;

public class SetSQL {
    Buhuchet      signin;
    ParamsTables  zpt;
    JTextComponent[][] rows;
    String[]      sx;

    public SetSQL(Buhuchet signinx,ParamsTables z,JTextComponent[][] rowsx) {
        signin=signinx; zpt=z; rows=rowsx; sx=zpt.dbName.split("_");
    }
    JTextComponent[][] setRows() {asosiyKursatkich(); update(); return rows;}
    void update() {
        for (int i=0; i<rows.length; i++) {
            int k=i+1;
            String sql="update "+zpt.dbName+" set p0="+rows[i][0].getText();
            for (int j=1; j<rows[i].length; j++) sql=sql+",p"+j+"='"+rows[i][j].getText()+"'";
            sql=sql+" where p0="+k; signin.sql.executeUpdate(sql);
        }
    }
    void setCells_tx1() {
        int m=new Integer(sx[2]); m=m-1; ParamsTables zpt_xt2=new ParamsTables(signin,"t2"); double n=0;
        int[] x=new int[8]; for (int i=0; i<x.length; i++) x[i]=0;
        for (int i=1; i<signin.region[0].length; i++) {
            String dbn="xt2_"+signin.region[0][i]+"_"+m+"_"+sx[3];
            JTextComponent[][] rows_xt2Old=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt2);
            dbn="xt2_"+signin.region[0][i]+"_"+sx[2]+"_"+sx[3];
            JTextComponent[][] rows_xt2=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt2);

            int k=get(rows_xt2Old,zpt_xt2,3,1); if (rows_xt2Old==null) k=get(rows,zpt,i,1); set(zpt,k,i,1); x[0]=x[0]+k;
            int l=get(rows_xt2,zpt_xt2,3,1);    if (rows_xt2==null) l=get(rows,zpt,i,2); set(zpt,l,i,2); x[1]=x[1]+l;
            if (k!=0) {n=(l+0.0)/k-1; double nx=Math.round(Math.abs(n)*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(zpt,n,i,3);
            k=get(rows_xt2Old,zpt_xt2,3,2); if (rows_xt2Old==null) k=get(rows,zpt,i,4); set(zpt,k,i,4); x[2]=x[2]+k;
            l=get(rows_xt2,zpt_xt2,3,2); if (rows_xt2==null) l=get(rows,zpt,i,5); set(zpt,l,i,5);    x[3]=x[3]+l;
            if (k!=0) {n=(l+0.0)/k-1; double nx=Math.round(Math.abs(n)*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(zpt,n,i,6);

            k=get(rows_xt2Old,zpt_xt2,13,1); if (rows_xt2Old==null) k=get(rows,zpt,i,7); set(zpt,k,i,7); x[4]=x[4]+k;
            l=get(rows_xt2,zpt_xt2,13,1); if (rows_xt2==null) l=get(rows,zpt,i,8); set(zpt,l,i,8);    x[5]=x[5]+l;
            if (k!=0) {n=(l+0.0)/k-1; double nx=Math.round(Math.abs(n)*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(zpt,n,i,9);
            k=get(rows_xt2Old,zpt_xt2,13,2); if (rows_xt2Old==null) k=get(rows,zpt,i,10); set(zpt,k,i,10); x[6]=x[6]+k;
            l=get(rows_xt2,zpt_xt2,13,2); if (rows_xt2==null) l=get(rows,zpt,i,11); set(zpt,l,i,11);    x[7]=x[7]+l;
            if (k!=0) {n=(l+0.0)/k-1; double nx=Math.round(Math.abs(n)*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(zpt,n,i,12);
        }
        m=signin.region[0].length;
        set(zpt,x[0],m,1); set(zpt,x[1],m,2);
        if (x[0]!=0) {n=(x[1]+0.0)/x[0]-1; double nx=Math.round(Math.abs(n)*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
        else n=0;
        set(zpt,n,m,3);

        set(zpt,x[2],m,4); set(zpt,x[3],m,5);
        if (x[2]!=0) {n=(x[3]+0.0)/x[2]-1; double nx=Math.round(Math.abs(n)*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
        else n=0;
        set(zpt,n,m,6);

        set(zpt,x[4],m,7); set(zpt,x[5],m,8);
        if (x[4]!=0) {n=(x[5]+0.0)/x[4]-1; double nx=Math.round(Math.abs(n)*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
        else n=0;
        set(zpt,n,m,9);

        set(zpt,x[6],m,10); set(zpt,x[7],m,11);
        if (x[6]!=0) {n=(x[7]+0.0)/x[6]-1; double nx=Math.round(Math.abs(n)*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
        else n=0;
        set(zpt,n,m,12);
    }
    void setCells_tx2() {
        int m=new Integer(sx[2]); m=m-1; ParamsTables zpt_xt2=new ParamsTables(signin,"t2"); double n=0;
        ParamsTables zpt_xtx1=new ParamsTables(signin,"tx1");
        String dbn="xtx1_"+signin.region[0][0]+"_"+sx[2]+"_"+sx[3];
        JTextComponent[][] rows_xtx1Old=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xtx1);
        int[] x=new int[14]; for (int i=0; i<x.length; i++) x[i]=0;
        int[] y=new int[14]; for (int i=0; i<y.length; i++) y[i]=0;
        for (int i=1; i<signin.region[0].length; i++) {
            int k=0; int l=0;
            dbn="xt2_"+signin.region[0][i]+"_"+m+"_"+sx[3];
            JTextComponent[][] rows_xt2Old=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt2);
            dbn="xt2_"+signin.region[0][i]+"_"+sx[2]+"_"+sx[3];
            JTextComponent[][] rows_xt2=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt2);

            if (rows_xt2Old!=null) k=get(rows_xt2Old,zpt_xt2,19,1)+get(rows_xt2Old,zpt_xt2,21,1)+
                                     get(rows_xt2Old,zpt_xt2,23,1)+get(rows_xt2Old,zpt_xt2,25,1);
            else k=get(rows,zpt,i,1); set(zpt,k,i,1); x[0]=x[0]+k;
            if (rows_xt2!=null) l=get(rows_xt2,zpt_xt2,19,1)+get(rows_xt2,zpt_xt2,21,1)+
                                     get(rows_xt2,zpt_xt2,23,1)+get(rows_xt2,zpt_xt2,25,1);
            else l=get(rows,zpt,i,2); set(zpt,l,i,2); x[1]=x[1]+l;
            if (k!=0) {n=(l+0.0)/k-1; double nx=Math.round(Math.abs(n)*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(zpt,n,i,3);

            if (rows_xt2Old!=null) k=get(rows_xt2Old,zpt_xt2,19,2)+get(rows_xt2Old,zpt_xt2,21,2)+
                                     get(rows_xt2Old,zpt_xt2,23,2)+get(rows_xt2Old,zpt_xt2,25,2);
            else k=get(rows,zpt,i,4); set(zpt,k,i,4); x[3]=x[3]+k;
            if (rows_xt2!=null) l=get(rows_xt2,zpt_xt2,19,2)+get(rows_xt2,zpt_xt2,21,2)+
                                     get(rows_xt2,zpt_xt2,23,2)+get(rows_xt2,zpt_xt2,25,2);
            else l=get(rows,zpt,i,5); set(zpt,l,i,5); x[4]=x[4]+l;
            if (k!=0) {n=(l+0.0)/k-1; double nx=Math.round(Math.abs(n)*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(zpt,n,i,6);

            if (rows_xt2Old!=null) k=get(rows_xt2Old,zpt_xt2,13,1);
            else if (rows_xtx1Old!=null) k=get(rows_xtx1Old,zpt_xtx1,i,7);
            y[6]=y[6]+k;
            if (rows_xt2Old!=null) l=get(rows_xt2Old,zpt_xt2,26,1); else l=get(rows,zpt,i,7); set(zpt,l,i,7); x[6]=x[6]+l;
            if (k!=0) {n=(l+0.0)/k; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(zpt,n,i,8);

            if (rows_xt2!=null) k=get(rows_xt2,zpt_xt2,13,1); else if (rows_xtx1Old!=null) k=get(rows_xtx1Old,zpt_xtx1,i,8);
            y[8]=y[8]+k;
            if (rows_xt2!=null) l=get(rows_xt2,zpt_xt2,26,1); else l=get(rows,zpt,i,9); set(zpt,l,i,9); x[8]=x[8]+l;
            if (k!=0) {n=(l+0.0)/k; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(zpt,n,i,10);

            if (rows_xt2Old!=null) k=get(rows_xt2Old,zpt_xt2,13,2);
            else if (rows_xtx1Old!=null) k=get(rows_xtx1Old,zpt_xtx1,i,10);
            y[10]=y[10]+k;
            if (rows_xt2Old!=null) l=get(rows_xt2Old,zpt_xt2,26,2); else l=get(rows,zpt,i,11); set(zpt,l,i,11); x[10]=x[10]+l;
            if (k!=0) {n=(l+0.0)/k; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(zpt,n,i,12);

            if (rows_xt2!=null) k=get(rows_xt2,zpt_xt2,13,2);
            else if (rows_xtx1Old!=null) k=get(rows_xtx1Old,zpt_xtx1,i,11);
            y[12]=y[12]+k;
            if (rows_xt2!=null) l=get(rows_xt2,zpt_xt2,26,2); else l=get(rows,zpt,i,13); set(zpt,l,i,13); x[12]=x[12]+l;
            if (k!=0) {n=(l+0.0)/k; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(zpt,n,i,14);
        }
        int mx=signin.region[0].length;
        for (int i=0; i<x.length; i++) set(zpt,x[i],mx,1+i);
        if (x[0]!=0) {n=(x[1]+0.0)/x[0]-1; double nx=Math.round(Math.abs(n)*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
        else n=0;
        set(zpt,n,mx,3);
        if (x[3]!=0) {n=(x[4]+0.0)/x[3]-1; double nx=Math.round(Math.abs(n)*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
        else n=0;
        set(zpt,n,mx,6);
        if (y[6]!=0) {n=(x[6]+0.0)/y[6]; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
        else n=0;
        set(zpt,n,mx,8);
        if (y[8]!=0) {n=(x[8]+0.0)/y[8]; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
        else n=0;
        set(zpt,n,mx,10);
        if (y[10]!=0) {n=(x[10]+0.0)/y[10]; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
        else n=0;
        set(zpt,n,mx,12);
        if (y[12]!=0) {n=(x[12]+0.0)/y[12]; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
        else n=0;
        set(zpt,n,mx,14);
    }
    void setCells_tx3() {
        int m=new Integer(sx[2]); m=m-1;
        ParamsTables zpt_xt1=new ParamsTables(signin,"t1"); ParamsTables zpt_xt2=new ParamsTables(signin,"t2");
        ParamsTables zpt_xt9=new ParamsTables(signin,"t9");
        double n=0;
        ParamsTables zpt_xtx1=new ParamsTables(signin,"tx1");
        String dbn="xtx1_"+signin.region[0][0]+"_"+sx[2]+"_"+sx[3];
        JTextComponent[][] rows_xtx1Old=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xtx1);
        int[] x=new int[11]; for (int i=0; i<x.length; i++) x[i]=0;
        int[] y=new int[11]; for (int i=0; i<y.length; i++) y[i]=0;
        for (int i=1; i<signin.region[0].length; i++) {
            int k=0; int l=0;
            dbn="xt2_"+signin.region[0][i]+"_"+m+"_"+sx[3];
            JTextComponent[][] rows_xt2Old=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt2);
            dbn="xt2_"+signin.region[0][i]+"_"+sx[2]+"_"+sx[3];
            JTextComponent[][] rows_xt2=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt2);
            dbn="xt1_"+signin.region[0][i]+"_"+sx[2]+"_"+sx[3];
            JTextComponent[][] rows_xt1=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt1);
            dbn="xt9_"+signin.region[0][i]+"_"+sx[2]+"_"+sx[3];
            JTextComponent[][] rows_xt9=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt9);

            if (rows_xt2Old!=null) k=get(rows_xt2Old,zpt_xt2,13,1); else if (rows_xtx1Old!=null) k=get(rows_xtx1Old,zpt_xtx1,i,7);
            y[0]=y[0]+k;
            if (rows_xt2Old!=null) l=get(rows_xt2Old,zpt_xt2,17,1); else l=get(rows,zpt,i,1); set(zpt,l,i,1); x[0]=x[0]+l;
            if (k!=0) {n=(l+0.0)/k; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(zpt,n,i,2);

            if (rows_xt2!=null) k=get(rows_xt2,zpt_xt2,13,1); else if (rows_xtx1Old!=null) k=get(rows_xtx1Old,zpt_xtx1,i,8);
            y[2]=y[2]+k;
            if (rows_xt2!=null) l=get(rows_xt2,zpt_xt2,17,1); else l=get(rows,zpt,i,3); set(zpt,l,i,3); x[2]=x[2]+l;
            if (k!=0) {n=(l+0.0)/k; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(zpt,n,i,4);

            if (rows_xt2Old!=null) k=get(rows_xt2Old,zpt_xt2,13,2); else if (rows_xtx1Old!=null) k=get(rows_xtx1Old,zpt_xtx1,i,10);
            y[4]=y[4]+k;
            if (rows_xt2Old!=null) l=get(rows_xt2Old,zpt_xt2,17,2); else l=get(rows,zpt,i,5); set(zpt,l,i,5); x[4]=x[4]+l;
            if (k!=0) {n=(l+0.0)/k; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(zpt,n,i,6);

            if (rows_xt2!=null) k=get(rows_xt2,zpt_xt2,13,2); else if (rows_xtx1Old!=null) k=get(rows_xtx1Old,zpt_xtx1,i,11);
            y[6]=y[6]+k;
            if (rows_xt2!=null) l=get(rows_xt2,zpt_xt2,17,2); else l=get(rows,zpt,i,7); set(zpt,l,i,7); x[6]=x[6]+l;
            if (k!=0) {n=(l+0.0)/k; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(zpt,n,i,8);

            if (rows_xt1!=null) {
                k=get(rows_xt1,zpt_xt1,45,2)+get(rows_xt1,zpt_xt1,45,8)+get(rows_xt1,zpt_xt1,45,12);
                k=k+get(rows_xt1,zpt_xt1,46,2)+get(rows_xt1,zpt_xt1,46,8)+get(rows_xt1,zpt_xt1,46,12);
            } else k=get(rows,zpt,i,9); set(zpt,k,i,9); x[8]=x[8]+k;
            if (rows_xt1!=null) {
                l=get(rows_xt1,zpt_xt1,45,4)+get(rows_xt1,zpt_xt1,45,10)+get(rows_xt1,zpt_xt1,45,14);
                l=l+get(rows_xt1,zpt_xt1,46,4)+get(rows_xt1,zpt_xt1,46,10)+get(rows_xt1,zpt_xt1,46,14);
            } else l=get(rows,zpt,i,9); set(zpt,l,i,10); x[9]=x[9]+l;
            if (rows_xt1!=null) k=get(rows_xt9,zpt_xt9,12,3); else k=get(rows,zpt,i,11); set(zpt,k,i,11); x[10]=x[10]+k;
        }
        int mx=signin.region[0].length;
        for (int i=0; i<x.length; i++) set(zpt,x[i],mx,1+i);
        if (y[0]!=0) {n=(x[0]+0.0)/y[0]; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
        else n=0;
        set(zpt,n,mx,2);
        if (y[2]!=0) {n=(x[2]+0.0)/y[2]; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
        else n=0;
        set(zpt,n,mx,4);
        if (y[4]!=0) {n=(x[4]+0.0)/y[4]; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
        else n=0;
        set(zpt,n,mx,6);
        if (y[6]!=0) {n=(x[6]+0.0)/y[6]; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
        else n=0;
        set(zpt,n,mx,8);
    }
    void setCells_tx4() {
        if (signin.printxyz==1) {
            String dbn="xtx4_"+signin.region[0][0]+"_"+sx[2]+"_"+sx[3]; ParamsTables zpt_xtx4=new ParamsTables(signin,"tx4");
            JTextComponent[][] rows_xtx4=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xtx4);
            rows=rows_xtx4;
            return;
        }
        int m=new Integer(sx[2]); m=m-1;
        ParamsTables zpt_xt5=new ParamsTables(signin,"t5"); ParamsTables zpt_xt2=new ParamsTables(signin,"t2");
        double n=0;
        int[] x=new int[12]; for (int i=0; i<x.length; i++) x[i]=0;
        int[] y=new int[12]; for (int i=0; i<y.length; i++) y[i]=0;
        String dbn="xtx2_"+signin.region[0][0]+"_"+sx[2]+"_"+sx[3]; ParamsTables zpt_xtx2=new ParamsTables(signin,"tx2");
        JTextComponent[][] rows_xtx2=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xtx2);

        for (int i=1; i<signin.region[0].length; i++) {
            int k=0; int l=0;
            dbn="xt5_"+signin.region[0][i]+"_"+m+"_"+sx[3];
            JTextComponent[][] rows_xt5Old=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt5);
            dbn="xt5_"+signin.region[0][i]+"_"+sx[2]+"_"+sx[3];
            JTextComponent[][] rows_xt5=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt5);
            if (rows_xt5Old!=null) k=get(rows_xt5Old,zpt_xt5,1,1); else k=get(rows,zpt,i,1); set(zpt,k,i,1); x[0]=x[0]+k;
            if (rows_xt5!=null) l=get(rows_xt5,zpt_xt5,1,1);    else l=get(rows,zpt,i,2); set(zpt,l,i,2); x[1]=x[1]+l;

            if (rows_xt5Old!=null) k=get(rows_xt5Old,zpt_xt5,1,3); else k=get(rows,zpt,i,3); set(zpt,k,i,3); x[2]=x[2]+k;
            if (rows_xt5!=null) l=get(rows_xt5,zpt_xt5,1,3); else l=get(rows,zpt,i,4); set(zpt,l,i,4); x[3]=x[3]+l;
            dbn="xt2_"+signin.region[0][i]+"_"+m+"_"+sx[3];
            JTextComponent[][] rows_xt2Old=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt2);

            dbn="xt2_"+signin.region[0][i]+"_"+sx[2]+"_"+sx[3];
            JTextComponent[][] rows_xt2=null;
            rows_xt2=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt2);

            if (rows_xt2Old!=null) k=get(rows_xt2Old,zpt_xt2,13,1);
            else if (rows_xtx2!=null) k=get(rows_xtx2,zpt_xtx2,i,1);
            y[4]=y[4]+k;
            if (rows_xt5Old!=null) l=get(rows_xt5Old,zpt_xt5,1,2); else l=get(rows,zpt,i,5); set(zpt,l,i,5); x[4]=x[4]+l;
            if (k!=0) {n=(l+0.0)/k; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(zpt,n,i,6);
            if (rows_xt2!=null) k=get(rows_xt2,zpt_xt2,13,1);
            else if (rows_xtx2!=null) k=get(rows_xtx2,zpt_xtx2,i,2);
            y[6]=y[6]+k;
            if (rows_xt5!=null) l=get(rows_xt5,zpt_xt5,1,2); else l=get(rows,zpt,i,7); set(zpt,l,i,7); x[6]=x[6]+l;
            if (k!=0) {n=(l+0.0)/k; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(zpt,n,i,8);
            if (rows_xt2Old!=null) k=get(rows_xt2Old,zpt_xt2,13,2);
            else if (rows_xtx2!=null) k=get(rows_xtx2,zpt_xtx2,i,4);
            y[8]=y[8]+k;
            if (rows_xt5Old!=null) l=get(rows_xt5Old,zpt_xt5,1,4); else l=get(rows,zpt,i,9); set(zpt,l,i,9); x[8]=x[8]+l;
            if (k!=0) {n=(l+0.0)/k; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(zpt,n,i,10);
            if (rows_xt2!=null) k=get(rows_xt2,zpt_xt2,13,2);
            else if (rows_xtx2!=null) k=get(rows_xtx2,zpt_xtx2,i,4);
            y[10]=y[10]+k;
            if (rows_xt5!=null) l=get(rows_xt5,zpt_xt5,1,4); else l=get(rows,zpt,i,11); set(zpt,l,i,11); x[10]=x[10]+l;
            if (k!=0) {n=(l+0.0)/k; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
            else n=0;
            set(zpt,n,i,12);
        }
        int mx=signin.region[0].length;
        for (int i=0; i<x.length; i++) set(zpt,x[i],mx,1+i);
        if (y[4]!=0) {n=(x[4]+0.0)/y[4]; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
        else n=0;
        set(zpt,n,mx,6);

        if (y[4]!=0) {n=(x[4]+0.0)/y[4]; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
        else n=0;
        set(zpt,n,mx,6);
        if (y[6]!=0) {n=(x[6]+0.0)/y[6]; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
        else n=0;
        set(zpt,n,mx,8);
        if (y[8]!=0) {n=(x[8]+0.0)/y[8]; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
        else n=0;
        set(zpt,n,mx,10);
        if (y[10]!=0) {n=(x[10]+0.0)/y[10]; double nx=Math.round(n*1000); nx=nx/10; if (n<0) n=-nx; else n=nx;}
        else n=0;
        set(zpt,n,mx,12);
    }
    void setCells_tx5() {
        int mx=new Integer(sx[2]); mx=mx-1;
        ParamsTables zpt_xt2=new ParamsTables(signin,"t2");
        double n=0;
        int[] x=new int[16]; for (int i=0; i<x.length; i++) x[i]=0;
        for (int i=1; i<signin.region[0].length; i++) {
            int k=0; int l=0; int m=0; int mn=0;
            String dbn="xt2_"+signin.region[0][i]+"_"+sx[2]+"_"+sx[3];
            JTextComponent[][] rows_xt2=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt2);
            if (rows_xt2!=null) k=get(rows_xt2,zpt_xt2,28,1); else k=get(rows,zpt,i,2); set(zpt,k,i,2); x[1]=x[1]+k;
            if (rows_xt2!=null) l=get(rows_xt2,zpt_xt2,32,1); else l=get(rows,zpt,i,4); set(zpt,l,i,4); x[3]=x[3]+l;
            if (rows_xt2!=null) m=get(rows_xt2,zpt_xt2,30,1); else m=get(rows,zpt,i,6); set(zpt,m,i,6); x[5]=x[5]+m;
            if (rows_xt2!=null) mn=get(rows_xt2,zpt_xt2,34,1); else mn=get(rows,zpt,i,8); set(zpt,mn,i,8); x[7]=x[7]+mn;

            if (rows_xt2!=null) k=get(rows_xt2,zpt_xt2,28,2); else k=get(rows,zpt,i,10); set(zpt,k,i,10); x[9]=x[9]+k;
            if (rows_xt2!=null) l=get(rows_xt2,zpt_xt2,32,2); else l=get(rows,zpt,i,12); set(zpt,l,i,12); x[11]=x[11]+l;
            if (rows_xt2!=null) m=get(rows_xt2,zpt_xt2,30,2); else m=get(rows,zpt,i,14); set(zpt,m,i,14); x[13]=x[13]+m;
            if (rows_xt2!=null) mn=get(rows_xt2,zpt_xt2,34,2); else mn=get(rows,zpt,i,16); set(zpt,mn,i,16); x[15]=x[15]+mn;

            dbn="xt2_"+signin.region[0][i]+"_"+mx+"_"+sx[3];
            rows_xt2=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt2);
            if (rows_xt2!=null) k=get(rows_xt2,zpt_xt2,28,1); else k=get(rows,zpt,i,1); set(zpt,k,i,1); x[0]=x[0]+k;
            if (rows_xt2!=null) l=get(rows_xt2,zpt_xt2,32,1); else l=get(rows,zpt,i,3); set(zpt,l,i,3); x[2]=x[2]+l;
            if (rows_xt2!=null) m=get(rows_xt2,zpt_xt2,30,1); else m=get(rows,zpt,i,5); set(zpt,m,i,5); x[4]=x[4]+m;
            if (rows_xt2!=null) mn=get(rows_xt2,zpt_xt2,34,1); else mn=get(rows,zpt,i,7); set(zpt,mn,i,7); x[6]=x[6]+mn;

            if (rows_xt2!=null) k=get(rows_xt2,zpt_xt2,28,2); else k=get(rows,zpt,i,9); set(zpt,k,i,9); x[8]=x[8]+k;
            if (rows_xt2!=null) l=get(rows_xt2,zpt_xt2,32,2); else l=get(rows,zpt,i,11); set(zpt,l,i,11); x[10]=x[10]+l;
            if (rows_xt2!=null) m=get(rows_xt2,zpt_xt2,30,2); else m=get(rows,zpt,i,13); set(zpt,m,i,13); x[12]=x[12]+m;
            if (rows_xt2!=null) mn=get(rows_xt2,zpt_xt2,34,2); else mn=get(rows,zpt,i,15); set(zpt,mn,i,15); x[14]=x[14]+mn;
        }
        for (int i=0; i<x.length; i++) set(zpt,x[i],signin.region[0].length,1+i);
    }
    void setCells_tx6() {
        ParamsTables zpt_xt2=new ParamsTables(signin,"t2"); ParamsTables zpt_xt8=new ParamsTables(signin,"t8");
        double n=0;
        int[] x=new int[12]; for (int i=0; i<x.length; i++) x[i]=0;
        for (int i=1; i<signin.region[0].length; i++) {
            int k=0; int l=0; int m=0;
            String dbn="xt2_"+signin.region[0][i]+"_"+sx[2]+"_"+sx[3];
            JTextComponent[][] rows_xt2=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt2);
            if (rows_xt2!=null) k=get(rows_xt2,zpt_xt2,42,1); else k=get(rows,zpt,i,1); set(zpt,k,i,1); x[0]=x[0]+k;
            if (rows_xt2!=null) l=get(rows_xt2,zpt_xt2,44,1); else l=get(rows,zpt,i,2); set(zpt,l,i,2); x[1]=x[1]+l;
            if (rows_xt2!=null) m=get(rows_xt2,zpt_xt2,45,1); else m=get(rows,zpt,i,3); set(zpt,m,i,3); x[2]=x[2]+m;

            if (rows_xt2!=null) k=get(rows_xt2,zpt_xt2,42,2); else k=get(rows,zpt,i,4); set(zpt,k,i,4); x[3]=x[3]+k;
            if (rows_xt2!=null) l=get(rows_xt2,zpt_xt2,44,2); else l=get(rows,zpt,i,5); set(zpt,l,i,5); x[4]=x[4]+l;
            if (rows_xt2!=null) m=get(rows_xt2,zpt_xt2,45,2); else m=get(rows,zpt,i,6); set(zpt,m,i,6); x[5]=x[5]+m;

            dbn="xt8_"+signin.region[0][i]+"_"+sx[2]+"_"+sx[3];
            JTextComponent[][] rows_xt8=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt8);
            if (rows_xt8!=null) k=get(rows_xt8,zpt_xt8,1,1); else k=get(rows,zpt,i,7); set(zpt,k,i,7); x[6]=x[6]+k;
            if (rows_xt8!=null) l=get(rows_xt8,zpt_xt8,1,2); else l=get(rows,zpt,i,8); set(zpt,l,i,8); x[7]=x[7]+l;
            if (rows_xt8!=null) m=get(rows_xt8,zpt_xt8,2,1)+get(rows_xt8,zpt_xt8,2,2);
            else m=get(rows,zpt,i,9); set(zpt,m,i,9); x[8]=x[8]+m;

            if (rows_xt8!=null) k=get(rows_xt8,zpt_xt8,11,1); else k=get(rows,zpt,i,10); set(zpt,k,i,10); x[9]=x[9]+k;
            if (rows_xt8!=null) l=get(rows_xt8,zpt_xt8,11,2); else l=get(rows,zpt,i,11); set(zpt,l,i,11); x[10]=x[10]+l;
            if (rows_xt8!=null) m=get(rows_xt8,zpt_xt8,12,1)+get(rows_xt8,zpt_xt8,12,2);
            else m=get(rows,zpt,i,12); set(zpt,m,i,12); x[11]=x[11]+m;
        }
        for (int i=0; i<x.length; i++) set(zpt,x[i],signin.region[0].length,1+i);
    }
    void setCells_tx7() {
        ParamsTables zpt_xt8=new ParamsTables(signin,"t8"); ParamsTables zpt_xt12=new ParamsTables(signin,"t12");
        double n=0;
        int[] x=new int[9]; for (int i=0; i<x.length; i++) x[i]=0;
        for (int i=1; i<signin.region[0].length; i++) {
            int k=0; int l=0; int m=0;
            String dbn="xt8_"+signin.region[0][i]+"_"+sx[2]+"_"+sx[3];
            JTextComponent[][] rows_xt8=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt8);
            if (rows_xt8!=null) k=get(rows_xt8,zpt_xt8,3,1); else k=get(rows,zpt,i,1); set(zpt,k,i,1); x[0]=x[0]+k;
            if (rows_xt8!=null) l=get(rows_xt8,zpt_xt8,3,2); else l=get(rows,zpt,i,2); set(zpt,l,i,2); x[1]=x[1]+l;
            if (rows_xt8!=null) m=get(rows_xt8,zpt_xt8,4,1)+get(rows_xt8,zpt_xt8,4,2);
            else m=get(rows,zpt,i,3); set(zpt,m,i,3); x[2]=x[2]+m;

            if (rows_xt8!=null) k=get(rows_xt8,zpt_xt8,5,1); else k=get(rows,zpt,i,4); set(zpt,k,i,4); x[3]=x[3]+k;
            if (rows_xt8!=null) l=get(rows_xt8,zpt_xt8,5,2); else l=get(rows,zpt,i,5); set(zpt,l,i,5); x[4]=x[4]+l;
            if (rows_xt8!=null) m=get(rows_xt8,zpt_xt8,6,1)+get(rows_xt8,zpt_xt8,6,2);
            else m=get(rows,zpt,i,6); set(zpt,m,i,6); x[5]=x[5]+m;

            dbn="xt12_"+signin.region[0][i]+"_"+sx[2]+"_"+sx[3];
            JTextComponent[][] rows_xt12=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt12);
            if (rows_xt12!=null) k=get(rows_xt12,zpt_xt12,8,1); else k=get(rows,zpt,i,7); set(zpt,k,i,7); x[6]=x[6]+k;
            if (rows_xt12!=null) l=get(rows_xt12,zpt_xt12,8,2); else l=get(rows,zpt,i,8); set(zpt,l,i,8); x[7]=x[7]+l;
            if (rows_xt12!=null) m=get(rows_xt12,zpt_xt12,8,3);
            else m=get(rows,zpt,i,9); set(zpt,m,i,9); x[8]=x[8]+m;
        }
        for (int i=0; i<x.length; i++) set(zpt,x[i],signin.region[0].length,1+i);
    }
    void setCells_tx8() {
        ParamsTables zpt_xt2=new ParamsTables(signin,"t2");
        double n=0;
        int[] x=new int[9]; for (int i=0; i<x.length; i++) x[i]=0;
        for (int i=1; i<signin.region[0].length; i++) {
            int k=0; int l=0; int m=0;
            String dbn="xt2_"+signin.region[0][i]+"_"+sx[2]+"_"+sx[3];
            JTextComponent[][] rows_xt2=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt2);
            if (rows_xt2!=null) k=get(rows_xt2,zpt_xt2,38,1); else k=get(rows,zpt,i,1); set(zpt,k,i,1); x[0]=x[0]+k;
            if (rows_xt2!=null) l=get(rows_xt2,zpt_xt2,38,2); else l=get(rows,zpt,i,2); set(zpt,l,i,2); x[1]=x[1]+l;
            if (rows_xt2!=null) m=get(rows_xt2,zpt_xt2,39,1)+get(rows_xt2,zpt_xt2,39,2);
            else m=get(rows,zpt,i,3); set(zpt,m,i,3); x[2]=x[2]+m;

            if (rows_xt2!=null) k=get(rows_xt2,zpt_xt2,40,1); else k=get(rows,zpt,i,4); set(zpt,k,i,4); x[3]=x[3]+k;
            if (rows_xt2!=null) l=get(rows_xt2,zpt_xt2,40,2); else l=get(rows,zpt,i,5); set(zpt,l,i,5); x[4]=x[4]+l;
            if (rows_xt2!=null) m=get(rows_xt2,zpt_xt2,41,1)+get(rows_xt2,zpt_xt2,41,2);
            else m=get(rows,zpt,i,6); set(zpt,m,i,6); x[5]=x[5]+m;

            if (rows_xt2!=null) k=get(rows_xt2,zpt_xt2,49,1); else k=get(rows,zpt,i,7); set(zpt,k,i,7); x[6]=x[6]+k;
            if (rows_xt2!=null) l=get(rows_xt2,zpt_xt2,49,2); else l=get(rows,zpt,i,8); set(zpt,l,i,8); x[7]=x[7]+l;
            if (rows_xt2!=null) m=get(rows_xt2,zpt_xt2,50,1)+get(rows_xt2,zpt_xt2,50,2);
            else m=get(rows,zpt,i,9); set(zpt,m,i,9); x[8]=x[8]+m;
        }
        for (int i=0; i<x.length; i++) set(zpt,x[i],signin.region[0].length,1+i);
    }
    void setCells_tx9() {
        if (signin.printxyz==1) {
            String dbn="xtx9_"+signin.region[0][0]+"_"+sx[2]+"_"+sx[3]; ParamsTables zpt_xtx9=new ParamsTables(signin,"tx9");
            JTextComponent[][] rows_xtx9=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xtx9);
            rows=rows_xtx9;
            return;
        }
        ParamsTables zpt_xt1=new ParamsTables(signin,"t1"); ParamsTables zpt_xt2=new ParamsTables(signin,"t2");
        ParamsTables zpt_xt4=new ParamsTables(signin,"t4"); ParamsTables zpt_xt6=new ParamsTables(signin,"t6");
        double n=0;
        int[] x=new int[12]; for (int i=0; i<x.length; i++) x[i]=0;
        for (int i=1; i<signin.region[0].length; i++) {
            int k=0; int l=0;
            String dbn="xt2_"+signin.region[0][i]+"_"+sx[2]+"_"+sx[3];
            JTextComponent[][] rows_xt2=null;
            rows_xt2=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt2);
            if (rows_xt2!=null) k=get(rows_xt2,zpt_xt2,26,1); else k=get(rows,zpt,i,1); set(zpt,k,i,1); x[0]=x[0]+k;
            if (rows_xt2!=null) l=get(rows_xt2,zpt_xt2,26,2); else l=get(rows,zpt,i,2); set(zpt,l,i,2); x[1]=x[1]+l;

            dbn="xt4_"+signin.region[0][i]+"_"+sx[2]+"_"+sx[3];
            JTextComponent[][] rows_xt4=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt4);
            if (rows_xt4!=null) k=get(rows_xt4,zpt_xt4,1,1)+get(rows_xt4,zpt_xt4,2,1)+get(rows_xt4,zpt_xt4,3,1);
            else k=get(rows,zpt,i,3); set(zpt,k,i,3); x[2]=x[2]+k;
            if (rows_xt4!=null) l=get(rows_xt4,zpt_xt4,1,2)+get(rows_xt4,zpt_xt4,2,2)+get(rows_xt4,zpt_xt4,3,2);
            else l=get(rows,zpt,i,4); set(zpt,l,i,4); x[3]=x[3]+l;

            k=0; for (int j=4; j<13; j++) k=k+get(rows_xt4,zpt_xt4,j,1);
            if (rows_xt4==null) k=get(rows,zpt,i,5); set(zpt,k,i,5); x[4]=x[4]+k;
            l=0; for (int j=4; j<13; j++) l=l+get(rows_xt4,zpt_xt4,j,2);
            if (rows_xt4==null) l=get(rows,zpt,i,6); set(zpt,l,i,6); x[5]=x[5]+l;

            k=get(rows_xt4,zpt_xt4,16,1); if (rows_xt4==null) k=get(rows,zpt,i,7); set(zpt,k,i,7); x[6]=x[6]+k;
            l=get(rows_xt4,zpt_xt4,16,2); if (rows_xt4==null) l=get(rows,zpt,i,8); set(zpt,l,i,8); x[7]=x[7]+l;

            dbn="xt6_"+signin.region[0][i]+"_"+sx[2]+"_"+sx[3];
            JTextComponent[][] rows_xt6=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt6);
            k=get(rows_xt6,zpt_xt6,3,1); if (rows_xt6==null) k=get(rows,zpt,i,9); set(zpt,k,i,9); x[8]=x[8]+k;
            l=get(rows_xt6,zpt_xt6,3,2); if (rows_xt6==null) l=get(rows,zpt,i,10); set(zpt,l,i,10); x[9]=x[9]+l;

            dbn="xt1_"+signin.region[0][i]+"_"+sx[2]+"_"+sx[3];
            JTextComponent[][] rows_xt1=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt1);
            k=get(rows_xt1,zpt_xt1,45,12); if (rows_xt1==null) k=get(rows,zpt,i,11); set(zpt,k,i,11); x[10]=x[10]+k;
            l=get(rows_xt1,zpt_xt1,45,14); if (rows_xt1==null) l=get(rows,zpt,i,12); set(zpt,l,i,12); x[11]=x[11]+l;
        }
        for (int i=0; i<x.length; i++) set(zpt,x[i],signin.region[0].length,1+i);
    }
    void setCells_tx10() {
        int m=new Integer(sx[3]); ParamsTables zpt_xt2=new ParamsTables(signin,"t2"); double n=0;
        int[] x=new int[4]; for (int i=0; i<x.length; i++) x[i]=0;
        for (int i=1; i<signin.region[0].length; i++) {
            String dbn="xt2_"+signin.region[0][i]+"_"+sx[2]+"_"+sx[3];
            JTextComponent[][] rows_xt2=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt2);

            int k=get(rows_xt2,zpt_xt2,13,1); if (rows_xt2==null) k=get(rows,zpt,i,1); set(zpt,k,i,1); x[0]=x[0]+k;
            int l=get(rows_xt2,zpt_xt2,58,1); if (rows_xt2==null) l=get(rows,zpt,i,2); set(zpt,l,i,2); x[1]=x[1]+l;
            if (l!=0) {n=(k+0.0)/l; double nx=Math.round(n*100); nx=nx/100; n=nx;} else n=0;
            set(zpt,n,i,3);
            if (l!=0) {n=(k+0.0)/(l*m); double nx=Math.round(n*100); nx=nx/100; n=nx;} else n=0;
            set(zpt,n,i,4);

            k=get(rows_xt2,zpt_xt2,13,2); if (rows_xt2==null) k=get(rows,zpt,i,5); set(zpt,k,i,5); x[2]=x[2]+k;
            l=get(rows_xt2,zpt_xt2,58,2); if (rows_xt2==null) l=get(rows,zpt,i,6); set(zpt,l,i,6); x[3]=x[3]+l;
            if (l!=0) {n=(k+0.0)/l; double nx=Math.round(n*100); nx=nx/100; n=nx;} else n=0;
            set(zpt,n,i,7);
            if (l!=0) {n=(k+0.0)/(l*m); double nx=Math.round(n*100); nx=nx/100; n=nx;} else n=0;
            set(zpt,n,i,8);
        }
        int mx=signin.region[0].length;
        set(zpt,x[0],mx,1); set(zpt,x[1],mx,2);
        if (x[1]!=0) {n=(x[0]+0.0)/x[1]; double nx=Math.round(n*100); nx=nx/100; n=nx;} else n=0;
        set(zpt,n,mx,3);
        if (x[1]!=0) {n=(x[0]+0.0)/(x[1]*m); double nx=Math.round(n*100); nx=nx/100; n=nx;} else n=0;
        set(zpt,n,mx,4);
        set(zpt,x[2],mx,5); set(zpt,x[3],mx,6);
        if (x[3]!=0) {n=(x[2]+0.0)/x[3]; double nx=Math.round(n*100); nx=nx/100; n=nx;} else n=0;
        set(zpt,n,mx,7);
        if (x[3]!=0) {n=(x[2]+0.0)/(x[3]*m); double nx=Math.round(n*100); nx=nx/100; n=nx;} else n=0;
        set(zpt,n,mx,8);
    }
    void setCells(String s) {
        if (s.equals("tx1"))  setCells_tx1();  if (s.equals("tx2"))  setCells_tx2();
        if (s.equals("tx3"))  setCells_tx3();  if (s.equals("tx4"))  setCells_tx4();
        if (s.equals("tx5"))  setCells_tx5();  if (s.equals("tx6"))  setCells_tx6();
        if (s.equals("tx7"))  setCells_tx7();  if (s.equals("tx8"))  setCells_tx8();
        if (s.equals("tx9"))  setCells_tx9();  if (s.equals("tx10")) setCells_tx10();
    }
    JTextComponent[][] setRows(String dbn) {
        int l=rows.length; int k=signin.region[0].length;
        if (l!=k) {
            signin.sql.executeUpdate("delete from "+zpt.dbName+" where p0>0");
            rows=new JTextComponent[k][zpt.colLength]; zpt.rowSizes=new int[k];
            for (int i=0; i<k; i++) {
                int n=i+1; String x=""+n;
                if (0>zpt.colEdit) rows[i][0]=new JTextField(x); else rows[i][0]=new JTextArea(x);
                String sql="insert into "+zpt.dbName+" values("+n;
                for (int j=1; j<zpt.colLength; j++) {
                    x=""; if (j>zpt.colEdit) rows[i][j]=new JTextField(x); else rows[i][j]=new JTextArea(x);
                    sql=sql+",' '";
                }
                sql=sql+")"; signin.sql.executeUpdate(sql);
            }
        }
        for (int i=0; i<rows.length-1; i++) {
             String x=" "+signin.region[1][i+1];
             if (1>zpt.colEdit) rows[i][1]=new JTextField(x); else rows[i][1]=new JTextArea(x);
        }
        String x=" "+signin.region[1][0];
        if (1>zpt.colEdit) rows[rows.length-1][1]=new JTextField(x); else rows[rows.length-1][1]=new JTextArea(x);
        setCells(dbn); update();
        return rows;
    }
    int get(JTextComponent[][] rows,ParamsTables zpt,int row,int col) {
        if (rows==null) return 0;
        row=zpt.rowEdit+row; col=zpt.colEdit+col;
        int k=0; int l=0; String x=rows[row][col].getText().trim();
        if (!x.equals("")) {try {k=new Integer(x);} catch (NumberFormatException e) {l=1;}}
        if (l==1) {k=0; signin.message(x+" - это не число!");}
        return k;
    }
    void set(ParamsTables zpt,double k,int row,int col) {
        row=zpt.rowEdit+row; col=zpt.colEdit+col;
        String x=" "; if (k!=0) x=" "+k;
        if (col>zpt.colEdit) rows[row][col]=new JTextField(x); else rows[row][col]=new JTextArea(x);
    }
    void set(ParamsTables zpt,int k,int row,int col) {
        row=zpt.rowEdit+row; col=zpt.colEdit+col;
        String x=" "; if (k!=0) x=" "+k;
        if (col>zpt.colEdit) rows[row][col]=new JTextField(x); else rows[row][col]=new JTextArea(x);
    }
    void asosiyKursatkich() {
        String dbn="xt1_"+sx[1]+"_"+sx[2]+"_"+sx[3]; ParamsTables zpt_xt1=new ParamsTables(signin,"t1");
        JTextComponent[][] rows_xt1=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt1);
        dbn="xt2_"+sx[1]+"_"+sx[2]+"_"+sx[3]; ParamsTables zpt_xt2=new ParamsTables(signin,"t2");
        JTextComponent[][] rows_xt2=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt2);
        dbn="xt5_"+sx[1]+"_"+sx[2]+"_"+sx[3]; ParamsTables zpt_xt5=new ParamsTables(signin,"t5");
        JTextComponent[][] rows_xt5=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt5);
        dbn="xt8_"+sx[1]+"_"+sx[2]+"_"+sx[3]; ParamsTables zpt_xt8=new ParamsTables(signin,"t8");
        JTextComponent[][] rows_xt8=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt8);
        dbn="xt9_"+sx[1]+"_"+sx[2]+"_"+sx[3]; ParamsTables zpt_xt9=new ParamsTables(signin,"t9");
        JTextComponent[][] rows_xt9=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt9);
        dbn="xt12_"+sx[1]+"_"+sx[2]+"_"+sx[3]; ParamsTables zpt_xt12=new ParamsTables(signin,"t12");
        JTextComponent[][] rows_xt12=signin.sql.getVector("select * from "+dbn+" order by p0 asc",zpt_xt12);
        
        int k=get(rows_xt2,zpt_xt2,1,1); set(zpt,k,1,1); k=get(rows_xt2,zpt_xt2,1,2); set(zpt,k,1,2);
        k=get(rows_xt2,zpt_xt2,2,1)+get(rows_xt2,zpt_xt2,2,2); set(zpt,k,1,3);

        k=get(rows_xt2,zpt_xt2,3,1); set(zpt,k,2,1); k=get(rows_xt2,zpt_xt2,3,2); set(zpt,k,2,2);
        k=get(rows_xt2,zpt_xt2,4,1)+get(rows_xt2,zpt_xt2,4,2); set(zpt,k,2,3);

        k=get(rows_xt2,zpt_xt2,13,1); set(zpt,k,3,1); k=get(rows_xt2,zpt_xt2,13,2); set(zpt,k,3,2);
        k=get(rows_xt2,zpt_xt2,14,1)+get(rows_xt2,zpt_xt2,14,2); set(zpt,k,3,3);

        k=get(rows_xt2,zpt_xt2,17,1); set(zpt,k,4,1); k=get(rows_xt2,zpt_xt2,17,2); set(zpt,k,4,2);
        k=get(rows_xt2,zpt_xt2,18,1)+get(rows_xt2,zpt_xt2,18,2); set(zpt,k,4,3);

        k=get(rows_xt2,zpt_xt2,19,1)+get(rows_xt2,zpt_xt2,21,1)+get(rows_xt2,zpt_xt2,23,1)+get(rows_xt2,zpt_xt2,25,1);
        set(zpt,k,5,1);
        k=get(rows_xt2,zpt_xt2,19,2)+get(rows_xt2,zpt_xt2,21,2)+get(rows_xt2,zpt_xt2,23,2)+get(rows_xt2,zpt_xt2,25,2);
        set(zpt,k,5,2);
        k=get(rows_xt2,zpt_xt2,20,1)+get(rows_xt2,zpt_xt2,22,1)+get(rows_xt2,zpt_xt2,24,1);
        k=k+get(rows_xt2,zpt_xt2,20,2)+get(rows_xt2,zpt_xt2,22,2)+get(rows_xt2,zpt_xt2,24,2);
        set(zpt,k,5,3);

        k=get(rows_xt5,zpt_xt5,1,1); set(zpt,k,6,1);
        k=get(rows_xt5,zpt_xt5,1,3); set(zpt,k,6,2);
        k=get(rows_xt5,zpt_xt5,3,1)+get(rows_xt5,zpt_xt5,3,3); set(zpt,k,6,3);

        k=get(rows_xt1,zpt_xt1,45,2)+get(rows_xt1,zpt_xt1,45,8)+get(rows_xt1,zpt_xt1,45,12);
        k=k+get(rows_xt1,zpt_xt1,46,2)+get(rows_xt1,zpt_xt1,46,8)+get(rows_xt1,zpt_xt1,46,12);
        set(zpt,k,7,1);
        k=get(rows_xt1,zpt_xt1,45,4)+get(rows_xt1,zpt_xt1,45,10)+get(rows_xt1,zpt_xt1,45,14);
        k=k+get(rows_xt1,zpt_xt1,46,4)+get(rows_xt1,zpt_xt1,46,10)+get(rows_xt1,zpt_xt1,46,14);
        set(zpt,k,7,2);
        k=get(rows_xt9,zpt_xt9,12,3);
        set(zpt,k,7,3);

        k=get(rows_xt2,zpt_xt2,26,1); set(zpt,k,8,1); k=get(rows_xt2,zpt_xt2,26,2); set(zpt,k,8,2);
        k=get(rows_xt2,zpt_xt2,27,1)+get(rows_xt2,zpt_xt2,27,2); set(zpt,k,8,3);

        k=get(rows_xt2,zpt_xt2,42,1); set(zpt,k,9,1); k=get(rows_xt2,zpt_xt2,42,2); set(zpt,k,9,2);
        k=get(rows_xt2,zpt_xt2,43,1)+get(rows_xt2,zpt_xt2,43,2); set(zpt,k,9,3);

        k=get(rows_xt2,zpt_xt2,45,1); set(zpt,k,10,1); k=get(rows_xt2,zpt_xt2,45,2); set(zpt,k,10,2);
        k=get(rows_xt2,zpt_xt2,46,1)+get(rows_xt2,zpt_xt2,46,2); set(zpt,k,10,3);

        k=get(rows_xt2,zpt_xt2,44,1); set(zpt,k,11,1); k=get(rows_xt2,zpt_xt2,44,2); set(zpt,k,11,2);
        k=0; set(zpt,k,11,3);

        k=get(rows_xt2,zpt_xt2,38,1); set(zpt,k,12,1);
        k=get(rows_xt2,zpt_xt2,38,2); set(zpt,k,12,2);
        k=get(rows_xt2,zpt_xt2,39,1)+get(rows_xt2,zpt_xt2,39,2); set(zpt,k,12,3);

        k=get(rows_xt2,zpt_xt2,40,1); set(zpt,k,13,1); k=get(rows_xt2,zpt_xt2,40,2); set(zpt,k,13,2);
        k=get(rows_xt2,zpt_xt2,41,1)+get(rows_xt2,zpt_xt2,41,2); set(zpt,k,13,3);
/////
        k=get(rows_xt2,zpt_xt2,28,1); set(zpt,k,14,1); k=get(rows_xt2,zpt_xt2,28,2); set(zpt,k,14,2);
        k=get(rows_xt2,zpt_xt2,29,1)+get(rows_xt2,zpt_xt2,29,2); set(zpt,k,14,3);

        k=get(rows_xt2,zpt_xt2,30,1); set(zpt,k,15,1); k=get(rows_xt2,zpt_xt2,30,2); set(zpt,k,15,2);
        k=get(rows_xt2,zpt_xt2,31,1)+get(rows_xt2,zpt_xt2,31,2); set(zpt,k,15,3);

        k=get(rows_xt2,zpt_xt2,32,1); set(zpt,k,16,1); k=get(rows_xt2,zpt_xt2,32,2); set(zpt,k,16,2);
        k=get(rows_xt2,zpt_xt2,33,1)+get(rows_xt2,zpt_xt2,33,2); set(zpt,k,16,3);

        k=get(rows_xt2,zpt_xt2,34,1); set(zpt,k,17,1); k=get(rows_xt2,zpt_xt2,34,2); set(zpt,k,17,2);
        k=get(rows_xt2,zpt_xt2,35,1)+get(rows_xt2,zpt_xt2,35,2); set(zpt,k,17,3);

        k=get(rows_xt2,zpt_xt2,36,1); set(zpt,k,18,1); k=get(rows_xt2,zpt_xt2,36,2); set(zpt,k,18,2);
        k=get(rows_xt2,zpt_xt2,37,1)+get(rows_xt2,zpt_xt2,37,2); set(zpt,k,18,3);
/////
        k=get(rows_xt5,zpt_xt5,1,2); set(zpt,k,19,1); k=get(rows_xt5,zpt_xt5,1,4); set(zpt,k,19,2);
        k=get(rows_xt5,zpt_xt5,2,2)+get(rows_xt5,zpt_xt5,2,4); set(zpt,k,19,3);

        k=get(rows_xt2,zpt_xt2,49,1); set(zpt,k,20,1); k=get(rows_xt2,zpt_xt2,49,2); set(zpt,k,20,2);
        k=get(rows_xt2,zpt_xt2,50,1)+get(rows_xt2,zpt_xt2,50,2); set(zpt,k,20,3);

        k=get(rows_xt8,zpt_xt8,1,1); set(zpt,k,21,1);
        k=get(rows_xt8,zpt_xt8,1,2)+get(rows_xt8,zpt_xt8,1,3); set(zpt,k,21,2);
        k=get(rows_xt8,zpt_xt8,2,1)+get(rows_xt8,zpt_xt8,2,2); set(zpt,k,21,3);

        k=get(rows_xt8,zpt_xt8,11,1); set(zpt,k,22,1); k=get(rows_xt8,zpt_xt8,11,2); set(zpt,k,22,2);
        k=get(rows_xt8,zpt_xt8,12,1)+get(rows_xt8,zpt_xt8,12,2); set(zpt,k,22,3);

        k=get(rows_xt8,zpt_xt8,3,1); set(zpt,k,23,1); k=get(rows_xt8,zpt_xt8,3,2); set(zpt,k,23,2);
        k=get(rows_xt8,zpt_xt8,4,1)+get(rows_xt8,zpt_xt8,4,2); set(zpt,k,23,3);

        k=get(rows_xt8,zpt_xt8,5,1); set(zpt,k,24,1);
        k=get(rows_xt8,zpt_xt8,5,2)+get(rows_xt8,zpt_xt8,5,3); set(zpt,k,24,2);
        k=get(rows_xt8,zpt_xt8,6,1)+get(rows_xt8,zpt_xt8,6,2); set(zpt,k,24,3);

        k=get(rows_xt12,zpt_xt12,8,1); set(zpt,k,25,1); k=get(rows_xt12,zpt_xt12,8,2); set(zpt,k,25,2);
        k=get(rows_xt12,zpt_xt12,8,3); set(zpt,k,25,3);
    }
}