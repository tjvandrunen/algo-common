package ckyparse;


public class Grammar<NT> {

    public static abstract class SyntaxTree<NT> {
        public final NT nt;
        SyntaxTree(NT nt) { this.nt = nt; }
    }
    public static class PartOfSpeech<NT> extends SyntaxTree<NT> {
        public final String word;

        public PartOfSpeech(NT nt, String word) {
            super(nt);
            this.word = word;
        }
        public String toString() {
            return "[" + nt + " " + word + "]";
        }
    }
    public static class UnitProduction<NT> extends SyntaxTree<NT> {
        public final SyntaxTree<NT> child;

        public UnitProduction(NT nt, SyntaxTree<NT> child) {
            super(nt);
            this.child = child;
        }
        public String toString() {
            return "(" + nt + " " + child + ")";
        }
       
    }
    public static class DualProduction<NT> extends SyntaxTree<NT> {

        public final SyntaxTree<NT> left, right;

        public DualProduction(NT nt, SyntaxTree<NT> left, SyntaxTree<NT> right) {
            super(nt);
            this.left = left;
            this.right = right;
        }
        public String toString() {
            return "(" + nt + " " + left + " " + right +")";
        }
    }
 
    private NT[][] units;
    private NT[][] duals;
    
    public Grammar(NT[][] units, NT[][] duals) {
        this.units = units;
        this.duals = duals;
    }
    
}
