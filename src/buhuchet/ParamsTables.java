package buhuchet;

public class ParamsTables {
    Buhuchet signin;
    String   dbName;
    String[] colTypes;
    int[]    columnSizes,rowSizes;
    int      colLength,rowEdit,colEdit,cursorX,cursorY,errorPage,tx;

    public ParamsTables(Buhuchet signinx,String sx) {
        signin=signinx; dbName=sx; errorPage=0; tx=0;
        if (sx.equals("t12")) colLength=7;  if (sx.equals("t11")) colLength=5;  if (sx.equals("t10")) colLength=6;
        if (sx.equals("t9"))  colLength=7;  if (sx.equals("t8"))  colLength=7;  if (sx.equals("t7"))  colLength=13;
        if (sx.equals("t6"))  colLength=12;  if (sx.equals("t5"))  colLength=8;  if (sx.equals("t4"))  colLength=6;
        if (sx.equals("t3"))  colLength=7;  if (sx.equals("t2"))  colLength=6;  if (sx.equals("t1"))  colLength=17;
        if (sx.equals("tx0")) colLength=7;  if (sx.equals("tx1")) colLength=14; if (sx.equals("tx2")) colLength=16;
        if (sx.equals("tx3")) colLength=13; if (sx.equals("tx4")) colLength=14; if (sx.equals("tx5")) colLength=18;
        if (sx.equals("tx6")) colLength=14; if (sx.equals("tx7")) colLength=11; if (sx.equals("tx8")) colLength=11;
        if (sx.equals("tx9")) colLength=14; if (sx.equals("tx10")) colLength=10;
        if (sx.equals("tx1")||sx.equals("tx2")||sx.equals("tx3")||sx.equals("tx4")||sx.equals("tx5")) tx=1;
        if (sx.equals("tx6")||sx.equals("tx7")||sx.equals("tx8")||sx.equals("tx9")||sx.equals("tx10")) tx=1;
        if (sx.equals("users")) colLength=3; if (sx.equals("userw")) colLength=9; if (sx.equals("podpis")) colLength=4; if (sx.equals("podpis2")) colLength=4;
        colTypes=new String[colLength]; colTypes[0]="int(10)"; colTypes[1]="varchar(1000)";
        for (int i=2; i<colLength; i++) colTypes[i]="varchar(300)";
        int[] a=signin.sql.readHeader(colLength,dbName); rowEdit=a[0]; colEdit=a[1];
        if (sx.equals("t5")) {colTypes[1]="varchar(300)"; colTypes[2]="varchar(1000)";}
        if (sx.equals("podpis")||sx.equals("podpis2")) {colTypes[1]="varchar(1000)"; colTypes[2]="varchar(1000)"; colTypes[3]="varchar(1000)";}
        cursorX=colEdit+1; cursorY=rowEdit+1;
        columnSizes=new int[colLength]; for (int i=0; i<columnSizes.length; i++) columnSizes[i]=a[i+2];
    }
}