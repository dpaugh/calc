package calc;
import com.sun.istack.internal.localization.NullLocalizable;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class calcGUI {
    private calc newcalc;
    private String screenText;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("Mode");
    private JTextPane screen = new JTextPane();
    private JButton one = new JButton("1");
    private JButton two = new JButton("2");
    private JButton three = new JButton("3");
    private JButton four = new JButton("4");
    private JButton five = new JButton("5");
    private JButton six = new JButton("6");
    private JButton seven = new JButton("7");
    private JButton eight = new JButton("8");
    private JButton nine = new JButton("9");
    private JButton zero = new JButton("0");
    private JButton decimal = new JButton(".");
    private JButton add = new JButton("+");
    private JButton subtract = new JButton("-");
    private JButton multiply = new JButton("*");
    private JButton divide = new JButton("÷");
    private JButton sqrt = new JButton("√");
    private JButton reciprocal = new JButton("<html>x<font size='2'><sup>-1</sup></font></html>");
    private JButton percent = new JButton("%");
    private JButton plusMinus = new JButton("±");
    private JButton del = new JButton("←");
    private JButton clearEntry = new JButton("CE");
    private JButton clearAll = new JButton("C");
    private JButton equals = new JButton("=");
    private JButton square = new JButton("<html>x<font size='2'><sup>2</sup></font></html>");
    private JButton cube = new JButton("<html>x<font size='2'><sup>3</sup></font></html>");
    private JButton exponent = new JButton("<html>x<font size='2'><sup>n</sup></font></html>");
    private JButton log = new JButton("log");
    private JButton ln = new JButton("ln");
    private JButton cuberoot = new JButton("<html><font size='2'><sup>3</sup></font>√x</html>");
    private JButton root = new JButton("<html><font size='2'><sup>n</sup></font>√x</html>");
    private JButton pow10 = new JButton("<html>10<font size='2'><sup>n</sup></font></html>");
    private JButton pow2 = new JButton("<html>2<font size='2'><sup>n</sup></font></html>");
    private JButton mod = new JButton("Mod");
    private JButton sin = new JButton("sin");
    private JButton cos = new JButton("cos");
    private JButton tan = new JButton("tan");
    private JButton sec = new JButton("sec");
    private JButton csc = new JButton("csc");
    private JButton cot = new JButton("cot");
    private JButton arcsin = new JButton("<html>sin<font size='2'><sup>-1</sup></font></html>");
    private JButton arccos = new JButton("<html>cos<font size='2'><sup>-1</sup></font></html>");
    private JButton arctan = new JButton("<html>tan<font size='2'><sup>-1</sup></font></html>");
    private JButton arcsec = new JButton("<html>sec<font size='2'><sup>-1</sup></font></html>");
    private JButton arccsc = new JButton("<html>csc<font size='2'><sup>-1</sup></font></html>");
    private JButton arccot = new JButton("<html>cot<font size='2'><sup>-1</sup></font></html>");
    private JButton sinh = new JButton("sinh");
    private JButton cosh = new JButton("cosh");
    private JButton tanh = new JButton("tanh");
    private JButton numPi = new JButton("π");
    private JButton numE = new JButton("e");
    private JRadioButtonMenuItem prefixRadioButton = new JRadioButtonMenuItem("Prefix");
    private JRadioButtonMenuItem infixRadioButton = new JRadioButtonMenuItem("Infix", true);
    private JRadioButtonMenuItem postfixRadioButton = new JRadioButtonMenuItem("Postfix");
    private ButtonGroup modeSelector = new ButtonGroup();
    String op = "ADD";
    boolean newEntry = true;
    boolean newValue = true;

    public calcGUI() {
        newcalc = new calc();

        modeSelector.add(prefixRadioButton);
        modeSelector.add(infixRadioButton);
        modeSelector.add(postfixRadioButton);
        menu.add(prefixRadioButton);
        menu.add(infixRadioButton);
        menu.add(postfixRadioButton);
        menuBar.add(menu);

        SimpleAttributeSet screenAttribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(screenAttribs, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setFontFamily(screenAttribs, "Courier New");
        StyleConstants.setFontSize(screenAttribs, 20);
        StyleConstants.setBold(screenAttribs, true);

        screen.setEditable(false);
        screenText = "0";
        screen.setParagraphAttributes(screenAttribs, true);
        screen.setText(screenText);

        // Button event/key binding block
        JButton buttonPile[] = {one, two, three, four, five, six, seven, eight, nine, zero, decimal,
                                add, subtract, multiply, divide, sqrt, reciprocal, percent, plusMinus,
                                del, clearEntry, clearAll, equals, square, cube, exponent, log, ln,
                                cuberoot, root, pow10, pow2, mod, sin, cos, tan, sec, csc, cot,
                                arcsin, arccos, arctan, arcsec, arccsc, arccot, sinh, cosh, tanh,
                                numPi, numE};

        String keyPile = "1234567890.";

        int keyEventPile[] = {KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD2, KeyEvent.VK_NUMPAD3, KeyEvent.VK_NUMPAD4,
                              KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD6, KeyEvent.VK_NUMPAD7, KeyEvent.VK_NUMPAD8,
                              KeyEvent.VK_NUMPAD9, KeyEvent.VK_NUMPAD0, KeyEvent.VK_PERIOD};

        String opPile[] =  {"ADD","SUB","MUL","DIV","SQT","RCP","PCT","NEG","DEL","CLE","CLR","EQL","SQR",
                            "CUB","EXP","LOG","_LN","CRT","YRT","P10","PW2","MOD","SIN","COS","TAN","SEC",
                            "CSC","COT","ASN","ACS","ATN","ASC","ACC","ACT","SNH","CSH","TNH","NPI","NME"};

        for (int i = 0; i < 11; i++) {
            char j = keyPile.charAt(i);
            buttonPile[i].addActionListener(e -> keyNumber(j));
            buttonPile[i].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyEventPile[i],0),"pressed");
            buttonPile[i].getActionMap().put("pressed",new keyNumAction(j));
            buttonPile[i].setMargin(new Insets(0,0,0,0));
        }

        for (int i = 0; i < opPile.length; i++) {
            String j = opPile[i];
            buttonPile[i+11].addActionListener(e -> keyOperator(j));
            buttonPile[i+11].setMargin(new Insets(0,0,0,0));
        }

        add.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ADD,0),"pressed");
        subtract.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT,0),"pressed");
        multiply.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_MULTIPLY,0),"pressed");
        divide.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DIVIDE,0),"pressed");
        del.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0),"pressed");
        del.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE,0),"pressed");
        equals.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"pressed");
        add.getActionMap().put("pressed",new keyOpAction("ADD"));
        subtract.getActionMap().put("pressed",new keyOpAction("SUB"));
        multiply.getActionMap().put("pressed",new keyOpAction("MUL"));
        divide.getActionMap().put("pressed",new keyOpAction("DIV"));
        del.getActionMap().put("pressed",new keyOpAction("DEL"));
        equals.getActionMap().put("pressed",new keyOpAction("EQL"));
        // End button event/key binding block

        // Layout block
        JButton layoutPile[] = {sinh, sec, sin, cuberoot, square, del, clearEntry, clearAll, plusMinus, sqrt,
                                cosh, csc, cos, root, cube, seven, eight, nine, divide, percent,
                                tanh, cot, tan, log, exponent, four, five, six, multiply, reciprocal,
                                arcsin, arccos, arctan, ln, pow10, one, two, three, subtract, equals,
                                arcsec, arccsc, arccot, mod, pow2, zero, decimal, add};

        for (int y = 87, i = 0; i < 5; y += 30, i++) {
            for (int x = 10, j = 0; j < 10; x += 55, j++) {
                switch(j) {
                    case 9:
                        if (i == 3) { // double height equals button at (3, 9)
                            layoutPile[i*10+j].setBounds(x, y, 50, 55);
                            break;
                        }
                    case 8: // no eighth or ninth button on last row
                        if (i == 4) break;
                    case 5:
                        if (i == 4) { // double width zero button at (4, 5)
                            layoutPile[i*10+j].setBounds(x, y, 105, 25);
                            x += 55;
                            break;
                        }
                    default:
                        layoutPile[i*10+j].setBounds(x,y,50,25);
                }
            }
        }
        // End layout block

        // Window construction block
        JFrame f = new JFrame();

        f.setJMenuBar(menuBar);

        for (int i = 0; i < layoutPile.length; i++) {
            f.add(layoutPile[i]);
        }

        screen.setBounds(10,50,539,20);
        f.add(screen);

        f.setSize(571,293);
        f.setLayout(null);
        f.setResizable(false);
        f.setVisible(true);
        del.grabFocus();
        // End window construction block
    }

    private void keyNumber(char key) {
        switch(key) {
            case '0':
                if (newEntry) { break; }
            case '.':
                if (screenText.indexOf('.') > 0) { break; }
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            default:
                if (newEntry) {
                    if (key == '.') screenText += key;
                    else screenText = Character.toString(key);
                    newEntry = false;
                }
                else { screenText += key; }
                screen.setText(screenText);
        }
    }

    private void keyOperator(String key) {
        switch(key) {
            case "ADD":
            case "SUB":
            case "MUL":
            case "DIV":
            case "EXP":
            case "YRT":
            case "PCT":
            case "MOD":
                if (prefixRadioButton.isSelected()) { op = key; }
                else if (infixRadioButton.isSelected()) {
                    if (newValue) {
                        newcalc.doCalc("NOP", Double.parseDouble(screenText));
                        newValue = false;
                    } else {
                        if (!newEntry) newcalc.doCalc(op, Double.parseDouble(screenText));
                        screenText = newcalc.getOutput();
                        screen.setText(screenText);
                    }
                    op = key;
                    newEntry = true;
                }
                else if (postfixRadioButton.isSelected()){
                    op = key;
                    newcalc.doCalc(op, Double.parseDouble(screenText));
                    screen.setText(newcalc.getOutput());
                    newEntry = true;
                }
                break;
            case "SQR":
            case "CUB":
            case "SQT":
            case "CRT":
            case "RCP":
            case "NEG":
            case "LOG":
            case "_LN":
            case "P10":
            case "PW2":
            case "SIN":
            case "COS":
            case "TAN":
            case "SEC":
            case "CSC":
            case "COT":
            case "ASN":
            case "ACS":
            case "ATN":
            case "ASC":
            case "ACC":
            case "ACT":
            case "SNH":
            case "CSH":
            case "TNH":
                newcalc.doCalc(key, Double.parseDouble(screenText));
                screen.setText(newcalc.getOutput());
                newEntry = true;
                break;
            case "DEL":
                if (!newEntry) {
                    if (screenText.length() > 1) {
                        screenText = screenText.substring(0, screenText.length() - 1);
                    } else { screenText = "0"; newEntry = true; }
                    screen.setText(screenText);
                }
                break;
            case "CLR":
                newValue = true;
                newcalc.opClearAll();
            case "CLE":
                screenText = "0";
                screen.setText(screenText);
                newEntry = true;
                break;
            case "EQL":
                if (prefixRadioButton.isSelected()) {
                     if (newValue) {
                         newcalc.doCalc("NOP", Double.parseDouble(screenText));
                         newValue = false;
                     } else newcalc.doCalc(op, Double.parseDouble(screenText));
                     screen.setText(newcalc.getOutput());
                }
                else if (infixRadioButton.isSelected()) {
                    newcalc.doCalc(op, Double.parseDouble(screenText));
                    screen.setText(newcalc.getOutput());
                }
                else if (postfixRadioButton.isSelected()) {
                    if (newValue) newValue = false;
                    else screenText = newcalc.getOutput();
                    newcalc.doCalc("NOP", Double.parseDouble(screenText));
                }
                newEntry = true;
                break;
            default:
        }
    }

    private class keyNumAction extends AbstractAction {
        char keyText;

        public keyNumAction(char keyText) {
            this.keyText = keyText;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            keyNumber(keyText);
        }
    }

    private class keyOpAction extends AbstractAction {
        String opText;

        public keyOpAction(String opText) {
            this.opText = opText;
        }

        @Override
        public void actionPerformed(ActionEvent e) { keyOperator(opText); }
    }

    public static void main(String[] args) {
        calcGUI calculator = new calcGUI();
    }
}
