package mathex;

import impl.ArrayList;
import mathex.Syntax.Expression;

import java.util.StringTokenizer;

import static mathex.Syntax.*;

import adt.List;

public class Parser {

    public static class SyntaxError extends Error {
        private static final long serialVersionUID = 6303562563642304548L;
        public SyntaxError(String msg) { super(msg); }
    }
    public static void checkToken(StringTokenizer tokzer, String tok) {
        String next = tokzer.nextToken();
        if (! next.equals(tok))
            throw new SyntaxError("Expected " + tok + ", found " + next);
    }

    
    public static List<Assignment> parse(String program) {
        // The parsed program, represented as 
        List<Assignment> assignments = new ArrayList<Assignment>();
        // For each line in the program
        for (StringTokenizer lines = new StringTokenizer(program, "\n");
                lines.hasMoreTokens(); ) {
            // The current line in the program
            String line = lines.nextToken();
            // A tokenizer returning the tokesn in the current line
            StringTokenizer tokzer = new StringTokenizer(line, "=()+-*/", true);
            // The first token should be the lefthand side of an assignment
            String target = tokzer.nextToken();
            // The second token should be the assignment operator
            checkToken(tokzer, "=");
            // Parse the rest of the line as an expression, make an assignment,
            // and add it to the parsed program
            assignments.add(new Assignment(target, expression(tokzer)));
            }
        return assignments;
    }

    private static Operator operator(StringTokenizer tokzer) {
        String next = tokzer.nextToken();
        switch (next.charAt(0)) {
        case '+': return Operator.PLUS;
        case '-': return Operator.MINUS;
        case '*': return Operator.MUL;
        case '/': return Operator.DIV;
        default: throw new SyntaxError("Expected +,-,*, or /, found " + next);
        }
    }
    
    private static Expression expression(StringTokenizer tokzer) {
        String next = tokzer.nextToken();
        if (next.equals("(")) {
            Expression rand1 = expression(tokzer);
            Operator rator = operator(tokzer);
            Expression rand2 = expression(tokzer);
            checkToken(tokzer, ")");
            return new Arith(rand1, rator, rand2);
        }
        else try {
            return new Constant(Integer.parseInt(next));
        } catch (NumberFormatException nfe) {
            return new Variable(next);
        }
            
    }
            
        
    
    
}
