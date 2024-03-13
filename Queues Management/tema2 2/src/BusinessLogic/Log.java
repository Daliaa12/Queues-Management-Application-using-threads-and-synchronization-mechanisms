package BusinessLogic;

import javax.swing.*;

public class Log {

    private static JTextArea textArea;
    public static void setTextArea (JTextArea textArea2) {
        textArea = textArea2;
    }
    public static void print (String text) {
        textArea.append("\n" + text);
    }

}
