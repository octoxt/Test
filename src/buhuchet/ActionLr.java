package buhuchet;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.zip.*;

public class ActionLr implements ActionListener {
    int      k,cr,crx;
    Buhuchet signin;
    String   sx,s=null,ym;
    int[]    fs=new int[12];

    public ActionLr(Buhuchet signinx,String sx,int kx,int crx) {
        signin=signinx;
        if (crx==1) {s=sx; cr=1; return;}
        if (crx==2) {s=sx; cr=2; return;}
        if (crx==3) {k=kx; cr=3; return;}
        if (crx==4) {k=kx; cr=4; return;}
        if (crx==7) {s=sx; cr=7; return;}
    }    
    public ActionLr(Buhuchet signinx,int kx,int kl) {signin=signinx; k=kx; crx=kl; cr=5;}
    public ActionLr(Buhuchet signinx,int kl) {signin=signinx; crx=kl; cr=6;}
    public void actionPerformed(ActionEvent arg0) {
        if (cr==1) {
            if (signin.zadach==1) signin.edit=3; else {if (signin.edit<1||signin.edit>2) signin.edit=1;}
            ParamsTables zpt=new ParamsTables(signin,s); new GetPage(signin,zpt);
        }
        if (cr==2) {
            signin.edit=2;
            ParamsTables zpt=new ParamsTables(signin,s); new GetPage(signin,zpt);
        }
        if (cr==3) {
            if (k==1) {if (signin.zadach==0) signin.edit=2; else signin.edit=3;}
            if (k>1) {
                if (signin.zadach==1) signin.edit=3; else {if (signin.edit<1||signin.edit>2) signin.edit=1;}
            }
            new GetPage(k,signin);
        }
        if (cr==4) {
            if (signin.zadach==0) signin.edit=2; else signin.edit=3;
            new GetPage(k,signin);
        }
        if (cr==5) {
            if (crx==1) getRegion(); if (crx==2) fileChooser(); if (crx==3) help(k);
            if (crx==4) archivs();
        }
        if (cr==6) {
            if (crx==1) getDate();    if (crx==2) new LayoutMr(signin,3); if (crx==3) reInstall();
            if (crx==4) clearDBase(); if (crx==5) reestrUsers();
        }
        if (cr==7) {
            signin.edit=0;
            ParamsTables zpt=new ParamsTables(signin,s); new GetPage(signin,zpt);
        }
    }
    void fileChooser() {
        JFileChooser chooser=new JFileChooser(); chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogType(JFileChooser.CUSTOM_DIALOG);
        if (k==0) chooser.setApproveButtonText("Отправить базу"); else chooser.setApproveButtonText("Принять базу");
        int retval=chooser.showDialog(signin.jpanel, null);
        if (retval==JFileChooser.APPROVE_OPTION) {
            File theFile=chooser.getSelectedFile(); if (theFile!=null&&theFile.isDirectory()) s=theFile.getPath();
        } else return;
        if (s!=null) {
            String[] ymx=signin.data.split("-"); ym=ymx[0]+"_"+ymx[1];
            if (k==0) {
                String xtxs=signin.region[0][signin.cregion];

                if (signin.dbname.equals("jterguvdg")||signin.dbname.equals("jterguvdmg")) {
                    if (signin.getUID(signin.region[0][signin.cregion],2)==0) xtxs=signin.sql.getCodeParent();
                }

                sx=s+"\\MG_"+xtxs+"_"+ym;
                File dir=new File(sx); File df=null;
                if (!dir.mkdir()) {
                    String[] fns=dir.list(); for (int i=0; i<fns.length; i++) {df=new File(sx+"\\"+fns[i]); df.delete();}
                }
                exportx(xtxs);
            } else {
                sx=s+"\\MG_"+signin.region[0][signin.cregion]+"_"+ym;
                importx();
            }
        }
        String s=""; for (int i=0; i<fs.length; i++) if (fs[i]==0) {int l=i+1; if (s.equals("")) s="("+l; else s=s+", "+l;}
        if (!s.equals("")) {
            s=s+")"; String st=signin.data+" ойи учун ";
            if (k==0) st="Маълумотни жунатишда "+st; else st="Маълумотни кабул килишда "+st;
            st=st+"\n"+s+"-номерли жадваллар йук."; signin.message(st);
        }
    }
    void exportx(String xtxs) {
        for (int i=0; i<fs.length; i++) {
            fs[i]=0; int m=i+1; String fname="t"+m+"_"+signin.region[0][signin.cregion]+"_"+ym;
            String sd=signin.sql.getData(fname,"t"+m);
            if (sd.equals("")) continue; fs[i]=1;
            fname="t"+m+"_"+xtxs+"_"+ym;
            try {FileWriter out=new FileWriter(sx+"\\"+fname+".xtx"); out.write(sd,0,sd.length()); out.close();}
            catch (IOException e) {}
            fname="xt"+m+"_"+signin.region[0][signin.cregion]+"_"+ym;
            sd=signin.sql.getData(fname,"t"+m);
            if (sd.equals("")) continue;
            fname="xt"+m+"_"+xtxs+"_"+ym;
            try {FileWriter out=new FileWriter(sx+"\\"+fname+".xtx"); out.write(sd,0,sd.length()); out.close();}
            catch (IOException e) {}
        }
    }
    void importx() {
        char[] buf=new char[1]; signin.xtxtx=0;
        for (int i=0; i<fs.length; i++) {
            fs[i]=0; int m=i+1; String fname="t"+m+"_"+signin.region[0][signin.cregion]+"_"+ym;
            String sd="";
            try {
                FileReader in=new FileReader(sx+"\\"+fname+".xtx");
                while (in.read(buf,0,buf.length)!=-1) {String sdf=new String(buf); sd=sd+sdf.trim(); buf=new char[1];}
                in.close();
            } catch (IOException e) {continue;}
            int k=signin.sql.setData(fname,sd,"t"+m); if (k==1) fs[i]=1;
            fname="xt"+m+"_"+signin.region[0][signin.cregion]+"_"+ym;
            sd="";
            try {
                FileReader in=new FileReader(sx+"\\"+fname+".xtx");
                while (in.read(buf,0,buf.length)!=-1) {String sdf=new String(buf); sd=sd+sdf.trim(); buf=new char[1];}
                in.close();
            } catch (IOException e) {continue;}
            k=signin.sql.setData(fname,sd,"t"+m);
        }
    }
    void archivs() {
        String datep=""; LayoutMr x=new LayoutMr(signin,7); if (x.cancel==0) datep=x.YearMonthDay;
        String sf="C:\\Program Files\\MySQL\\MySQL Server 5.0\\data\\"+signin.dbname;
        if (k==0) {
            byte[] buf = new byte[1024];
            String outFilename=signin.drive+"/archiv/"+datep+".zip";
            try {
                int xtx=0;
                ZipOutputStream out=null;
                File dir=new File(sf); String[] fns=dir.list(); int m=0;
                for (int i=0; i<fns.length; i++) {
                    String[] fn=fns[i].split("_");
                    if (fn.length==4&&fn[2].equals(datep)) {
                        xtx=xtx+1;
                        if (xtx==1) out=new ZipOutputStream(new FileOutputStream(outFilename));
                        m=m+1; String fileName=sf+"\\"+fns[i];
                        FileInputStream in=new FileInputStream(fileName);
                        out.putNextEntry(new ZipEntry(fns[i]));
                        int len; while ((len=in.read(buf))>0) out.write(buf,0,len);
                        out.closeEntry(); in.close();
                        if (m==3) {
                            m=0; String s1=fn[1]+"_"+fn[2]+"_"+fn[3]; String atable=fn[0]+"_"+s1.substring(0,12);
                            signin.sql.drop(atable);
                        }
                    }
                }
                if (xtx==0) signin.message("Не имеются таблицы "+datep+" года");
                if (out!=null) out.close();
            } catch (IOException e) {}
        } else {
            try {

                File dir=new File(sf); String[] fns=dir.list(); int m=0;
                for (int i=0; i<fns.length; i++) {
                    String[] fn=fns[i].split("_");
                    if (fn.length==4&&fn[2].equals(datep)) {
                        m=m+1;
                        if (m==3) {
                            m=0; String s1=fn[1]+"_"+fn[2]+"_"+fn[3]; String atable=fn[0]+"_"+s1.substring(0,12);
                            signin.sql.drop(atable);
                        }
                    }
                }

                String inFilename=signin.drive+"/archiv/"+datep+".zip";
                ZipInputStream in=new ZipInputStream(new FileInputStream(inFilename));
                ZipFile zf=new ZipFile(inFilename);
                for (Enumeration entries=zf.entries(); entries.hasMoreElements();) {
                    String zipEntryName=sf+"\\"+((ZipEntry)entries.nextElement()).getName();
                    ZipEntry entry=in.getNextEntry();
                    OutputStream out=new FileOutputStream(zipEntryName);
                    byte[] buf=new byte[1024]; int len; while ((len=in.read(buf))>0) out.write(buf,0,len);
                    out.close();
                }
                in.close();
            } catch (IOException e) {}
        }
    }
    void clearDBase() {
        String razr=""; Boolean bool=false; int x1=0; int x2=0; int x3=0; int x4=0; String datep="";
        while (!bool) {
            LayoutMr lp=new LayoutMr(signin,4);
            if (lp.cancel==1||lp.cancel==-1) bool=true;
            if (lp.cancel==0) {
                razr=lp.pField.getText();
                if (signin.razrabotchik.equals(razr)) {
                    LayoutMr x=new LayoutMr(signin,2);
                    if (x.cancel==0) {
                        datep=x.YearMonthDay; String[] y=datep.split("-");
                        x1=new Integer(y[0]); x2=new Integer(y[1]);
                    }
                    String sf="C:\\Program Files\\MySQL\\MySQL Server 5.0\\data\\"+signin.dbname;
                    File dir=new File(sf); String[] fns=dir.list();
                    for (int i=0; i<fns.length; i++) {
                        String[] fn=fns[i].split("_");
                        if (fn.length==4) {
                            String s1=fn[1]+"_"+fn[2]+"_"+fn[3]; String atable=fn[0]+"_"+s1.substring(0,12);
                            String[] fn3=s1.substring(0,12).split("_");
                            x3=new Integer(fn[2]); x4=new Integer(fn3[2]); if (x3>=x1&&x4>=x2) signin.sql.drop(atable);
                        }
                    }
                    bool=true;
                } else signin.message("У Вас нет доступ!");
            }
        }
    }
    void getDate() {
        LayoutMr x=new LayoutMr(signin,2);
        if (x.cancel==0) {
            String titlex=signin.title+" - ";
            String s=signin.getTitle(); if (!s.equals("")) titlex=titlex+s+" ";
            titlex=titlex+signin.region[1][signin.cregion]+" буйича ";
            signin.data=x.YearMonthDay; String[] y=signin.data.split("-"); int z=new Integer(y[1]); z=z-1;
            titlex=titlex+y[0]+" йил "+signin.months[z]+" ойи учун.";
            signin.jframe.setTitle(titlex);
        }
    }
    void getRegion() {
        signin.cregion=k; signin.getUserID();
        if (signin.print7!=null) {
            if (signin.uID==3) {signin.otchet2.setEnabled(false); signin.print7.setEnabled(false);}
            else {signin.otchet2.setEnabled(true); signin.print7.setEnabled(true);}
            if (signin.parentNode==1&&signin.uID==2) {signin.otchet2.setEnabled(false); signin.print7.setEnabled(false);}
        }
        String titlex=signin.title+" - ";
        String s=signin.getTitle(); if (!s.equals("")) titlex=titlex+s+" ";
        titlex=titlex+signin.region[1][signin.cregion]+" буйича ";
        String[] y=signin.data.split("-"); int z=new Integer(y[1]);
        titlex=titlex+y[0]+" йил "+signin.months[z-1]+" ойи учун."; signin.jframe.setTitle(titlex);
    }
    void reInstall() {
        String razr=""; Boolean bool=false;
        while (!bool) {
            LayoutMr lp=new LayoutMr(signin,5);
            if (lp.cancel==1||lp.cancel==-1) bool=true;
            if (lp.cancel==0) {
                razr=lp.pField.getText();
                if (signin.razrabotchik.equals(razr)) {
                    signin.sql.drop("user"); //signin.sql.drop("users");
                    signin.message("Заново запустите программу!"); bool=true;
                    signin.sql.close();
                } else signin.message("У Вас нет доступ!");
            }
        }
    }
    void reestrUsers() {
        String razr=""; Boolean bool=false;
        while (!bool) {
            LayoutMr lp=new LayoutMr(signin,6);
            if (lp.cancel==1||lp.cancel==-1) bool=true;
            if (lp.cancel==0) {
                razr=lp.pField.getText();
                if (signin.razrabotchik.equals(razr)) {
                    signin.edit=0; ParamsTables zpt=new ParamsTables(signin,"users"); new GetPage(signin,zpt); bool=true;
                } else signin.message("У Вас нет доступ!");
            }
        }
    }
    void help(int k) {
        Cursor cursor=new Cursor(Cursor.WAIT_CURSOR); signin.jpanel.setCursor(cursor);
        if (k==3) {
            String s="Тергов ишлари юзасидан хисобот\n \nДастур прокуратура ва ИИВ органлари ходимлари учун езилди.\n";
            s=s+"Автор:  Хужаев Т.Х.\n \n";
            JOptionPane.showMessageDialog(null,s,"О программе",JOptionPane.INFORMATION_MESSAGE);
            cursor=new Cursor(Cursor.DEFAULT_CURSOR); signin.jpanel.setCursor(cursor);
            return;
        }
        String s="Ердам(Help)"; if (k==2) s="Йурикнома"; signin.getJIF(s);
        JTextPane jtp=new JTextPane();
        jtp.setFont(new Font("Times New Roman",Font.PLAIN,14));
        if (k==1) {
            s=signin.drive+"/resources/help1.txt";
            if (signin.getUID(signin.region[0][0],2)==0) s=signin.drive+"/resources/help2.txt";
        } else s=signin.drive+"/resources/inst.txt";
        char[] buf=new char[8]; String sd="";
        try {
            FileReader in=new FileReader(s);
            while (in.read(buf,0,buf.length)!=-1) {String sdf=new String(buf); sd=sd+sdf; buf=new char[1];}
            in.close();
        } catch (IOException e) {}
        jtp.setText(sd); jtp.setEditable(false);
        JScrollPane scrollpane=new JScrollPane(jtp); signin.jpanel.getSelectedFrame().add(scrollpane);
        cursor=new Cursor(Cursor.DEFAULT_CURSOR); signin.jpanel.setCursor(cursor);
    }
}