package buhuchet;

import javax.swing.*;
import javax.swing.table.*;

public class ToExcel {
    Buhuchet     signin;
    ParamsTables zpt;
    JTable       table;

    public ToExcel(Buhuchet signinx,ParamsTables z,JTable tablex) {
        signin=signinx; zpt=z; table=tablex; String application="Excel.Application";

    }
}