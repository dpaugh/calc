package calc;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * This class provides a simple set of calculator functions.
 *
 * @author Dirk W. Paugh II
 * @since 08/13/2017
 */
public class calc {

    /**
     * A simple stack implementation to facilitate the storing of values.
     */
    private class Stack {
        double data[];
        int capacity, elements;

        public Stack(int size) {
            data = new double[size];
            capacity = size;
            elements = 0;
        }

        public double push(double value) {
            if (elements < capacity) {
                data[elements] = value;
                elements++;
            }

            return value;
        }

        public double pop() {
            if (elements > 0) {
                double value = data[--elements];
                return value;
            } else return 0;
        }

        public double peek() {
            if (elements > 0) {
                return data[elements-1];
            } else return 0;
        }

        public boolean isEmpty() {
            return elements == 0;
        }

        public boolean isFull() {
            return elements == capacity;
        }

        public void clear() { elements = 0; }
    }

    private Stack stack;
    private String op;
    private double output;
    DecimalFormat df;

    public calc() {
        stack = new Stack(1);
        op = "NONE";
        output = 0;
        df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340); //340 = DecimalFormat.DOUBLE_FRACTION_DIGITS
    }

    /**
     * Adds two values and pushes the result onto the stack.
     * @param op1 The first operand.
     * @param op2 The second operand.
     */
    public void add(double op1, double op2) { output = stack.push(op1 + op2); }

    /**
     * Subtracts two values and pushes the result onto the stack.
     * @param op1 The first operand.
     * @param op2 The second operand.
     */
    public void sub(double op1, double op2) { output = stack.push(op1 - op2); }

    /**
     * Multiplies two values and pushes the result onto the stack.
     * @param op1 The first operand.
     * @param op2 The second operand.
     */
    public void mul(double op1, double op2) { output = stack.push(op1 * op2); }

    /**
     * Calculates the quotient of two values and pushes the result onto the stack.
     * If the divisor is zero, the result is set to zero.
     * @param op1 The dividend.
     * @param op2 The divisor.
     */
    public void div(double op1, double op2) { if (op2 > 0) { output = stack.push(op1 / op2); } else { output = stack.push(0); } }

    /**
     * Generic exponent. Pushes the result onto the stack.
     * @param op1 The base.
     * @param op2 The exponent.
     */
    public void exp(double op1, double op2) { output = stack.push(Math.pow(op1,op2)); }

    /**
     * Gemeric root. Pushes the result onto the stack. if the radicand is negative,
     * the result is set to zero.
     * @param op1 The radicand.
     * @param op2 The index.
     */
    public void root(double op1, double op2) { if (op1 > -1) { output = stack.push(Math.pow(op1,1/op2)); } else { output = stack.push(0); } }

    /**
     * 
     * @param op1
     * @param op2
     */
    public void pct(double op1, double op2) { output = stack.push(op1 * (op2/100)); }
    public void mod(double op1, double op2) { output = stack.push(op1 % op2); }
    public void add(double op) { add(stack.pop(), op); }
    public void sub(double op) { sub(stack.pop(), op); }
    public void mul(double op) { mul(stack.pop(), op); }
    public void div(double op) { div(stack.pop(), op); }
    public void exp(double op) { exp(stack.pop(), op); }
    public void root(double op) { root(stack.pop(), op); }
    public void pct(double op) { pct(stack.pop(), op); }
    public void mod(double op) { mod(stack.pop(), op); }
    public void sqr(double op) { exp(op,2); }
    public void cube(double op) { exp(op,3); }
    public void sqrt(double op) { root(op,2); }
    public void cbrt(double op) { root(op,3); }
    public void rcp(double op) { exp(op,-1); }
    public void neg(double op) { mul(op,-1); }
    public void log(double op) { output = stack.push(Math.log10(op)); }
    public void ln(double op) { output = stack.push(Math.log(op)); }
    public void pow10(double op) { exp(10, op); }
    public void pow2(double op) { exp(2, op); }
    public void sin(double op) { output = stack.push(Math.sin(op)); }
    public void cos(double op) { output = stack.push(Math.cos(op)); }
    public void tan(double op) { output = stack.push(Math.tan(op)); }
    public void sec(double op) { rcp(Math.sin(op)); }
    public void csc(double op) { rcp(Math.cos(op)); }
    public void cot(double op) { rcp(Math.tan(op)); }
    public void arcsin(double op) { output = stack.push(Math.asin(op)); }
    public void arccos(double op) { output = stack.push(Math.acos(op)); }
    public void arctan(double op) { output = stack.push(Math.atan(op)); }
    public void arcsec(double op) { rcp(Math.asin(op)); }
    public void arccsc(double op) { rcp(Math.acos(op)); }
    public void arccot(double op) { rcp(Math.atan(op)); }
    public void sinh(double op) { output = stack.push(Math.sinh(op)); }
    public void cosh(double op) { output = stack.push(Math.cosh(op)); }
    public void tanh(double op) { output = stack.push(Math.tanh(op)); }

    /**
     * Returns the currently stored output.
     *
     * @return The output of the last operation.
     */
    public String getOutput() {
        return df.format(output);
    }

    /**
     * Provides a simple stack-based interface. If the specified operation is unary, it is
     * performed on the passed operand. If it is binary, the operand on the stack (0 if none have
     * been pushed) is used as the first operand, and the passed value as the second.
     *
     * @param mode A three-character operator string. Currently supported operations:
     *             ADD (addition), SUB (subtraction), MUL (multiplication),
     *             DIV (division), SQT (square root), RCP (reciprocal),
     *             PCT (percent), NEG (negate), SQR (x^2), CUB (x^3),
     *             EXP (x^y), LOG (base 10 log), _LN (natural log),
     *             CRT (cube root), YRT (generic root), P10 (10^x), PW2 (2^x),
     *             MOD (modulo), SIN, COS, TAN, SEC, CSC, COT, ASN (arcsin),
     *             ACS (arccos), ATN (arctan), ASC (arcsec), ACC (arccsc),
     *             ACT (arccot), SNH (sinh), CSH (cosh), and TNH (tanh).
     *             NOP (no operation) simply pushes the passed value onto the stack.
     * @param value The operand.
     */
    public void doCalc(String mode, double value) {
        switch(mode) {
            case "ADD":
                add(value);
                break;
            case "SUB":
                sub(value);
                break;
            case "MUL":
                mul(value);
                break;
            case "DIV":
                div(value);
                break;
            case "SQT":
                sqrt(value);
                break;
            case "RCP":
                rcp(value);
                break;
            case "PCT":
                pct(value);
                break;
            case "NEG":
                neg(value);
                break;
            case "SQR":
                sqr(value);
                break;
            case "CUB":
                cube(value);
                break;
            case "EXP":
                exp(value);
                break;
            case "CRT":
                cbrt(value);
                break;
            case "YRT":
                root(value);
                break;
            case "LOG":
                log(value);
                break;
            case "_LN":
                ln(value);
                break;
            case "P10":
                pow10(value);
                break;
            case "PW2":
                pow2(value);
                break;
            case "MOD":
                mod(value);
                break;
            case "SIN":
                sin(value);
                break;
            case "COS":
                cos(value);
                break;
            case "TAN":
                tan(value);
                break;
            case "SEC":
                sec(value);
                break;
            case "CSC":
                csc(value);
                break;
            case "COT":
                cot(value);
                break;
            case "ASN":
                arcsin(value);
                break;
            case "ACS":
                arccos(value);
                break;
            case "ATN":
                arctan(value);
                break;
            case "ASC":
                arcsec(value);
                break;
            case "ACC":
                arccsc(value);
                break;
            case "ACT":
                arccot(value);
                break;
            case "SNH":
                sinh(value);
                break;
            case "CSH":
                cosh(value);
                break;
            case "TNH":
                tanh(value);
                break;
            case "NOP":
                if (stack.isFull()) { stack.clear(); }
                stack.push(value);
                output = value;
            default:
        }
    }

    /**
     * Resets the calculator state.
     */
    public void opClearAll() {
        stack.clear();
        output = 0;
    }
}
