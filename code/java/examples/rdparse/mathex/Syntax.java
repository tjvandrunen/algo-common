package mathex;

import adt.Map;

public class Syntax {

    public static enum Operator { 
        PLUS, MINUS, MUL, DIV
    }
    public static interface Expression { int evaluate(Map<String, Integer> symbolTab); }
    public static class Constant implements Expression {
        private int val;
        public Constant(int val) { this.val = val; }
        public String toString() { return "" + val; }
        public int evaluate(Map<String, Integer> symbolTab) {
            return val;
        }
    }
    public static class Variable implements Expression {
        private String var;
        public Variable(String var) { this.var = var; }
        public String toString() { return var; }
        public int evaluate(Map<String, Integer> symbolTab) {
            return symbolTab.get(var);
        }
    }
    public static class Arith implements Expression {
        private Expression left, right; 
        private Operator op;
        public Arith(Expression left, Operator op, Expression right) {
            this.left = left;
            this.op = op;
            this.right = right;
        }
        public String toString() { return "(" + left + op + right + ")"; }
        public int evaluate(Map<String, Integer> symbolTab) {
            int leftVal = left.evaluate(symbolTab);
            int rightVal = right.evaluate(symbolTab);
            switch (op) {
            case PLUS : return leftVal + rightVal;
            case MINUS : return leftVal - rightVal;
            case MUL : return leftVal * rightVal;
            case DIV : return leftVal / rightVal;
            default: return 0;
            }
        }
    }
    public static class Assignment {
        private String leftHandSide;
        private Expression rightHandSide;
        public Assignment(String leftHandSide, Expression rightHandSide) {
            this.leftHandSide = leftHandSide;
            this.rightHandSide = rightHandSide;
        }
        public String toString() { return leftHandSide + " = " + rightHandSide; }
        public void execute(Map<String, Integer> symbolTab) {
            symbolTab.put(leftHandSide, rightHandSide.evaluate(symbolTab));
        }
    }
    
}
