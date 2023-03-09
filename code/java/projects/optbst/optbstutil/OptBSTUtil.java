package optbstutil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

import impl.OptimalBSTData;
import impl.OptimalBSTMap;

public class OptBSTUtil {

    public static class FileFormatException extends RuntimeException {

        private static final long serialVersionUID = 5736995541392392815L;

        public FileFormatException(int lineNum) {
            super("At line" + lineNum);
        }

    }

    
    
    //public static OptimalBSTMap fromFile(String filename) throws FileNotFoundException {
    public static OptimalBSTData fromFile(String filename) throws FileNotFoundException {
       Scanner infile = new Scanner(new FileInputStream(filename));
        ArrayList<Double> missProbs = new ArrayList<Double>();
        ArrayList<String> keys = new ArrayList<String>();
        ArrayList<String> vals = new ArrayList<String>();
        ArrayList<Double> keyProbs = new ArrayList<Double>();
        
        int lineNum = -1;
        
        for(;;)  {
            // miss prob
            try {
                lineNum++;
                missProbs.add(Double.parseDouble(infile.nextLine()));
            } catch (NumberFormatException nfe) {
                infile.close();
                throw new FileFormatException(lineNum);
            }
            
            if (! infile.hasNext()) break;
            
            // key - val (optional) - key prob
            try {
                lineNum++;
                StringTokenizer tokey = new StringTokenizer(infile.nextLine());
                String key = tokey.nextToken();
                keys.add(key);
                String valOrProb = tokey.nextToken();
                if (tokey.hasMoreTokens()) {
                    vals.add(valOrProb);
                    valOrProb = tokey.nextToken();
                }
                else
                    vals.add(reverse(key));
                keyProbs.add(Double.parseDouble(valOrProb));
            } catch (Exception e) {
                infile.close();
                throw new FileFormatException(lineNum);
            }
            
        }
        infile.close();
        double[] missProbsA = new double[missProbs.size()];
        int i = 0;
        for (Double d : missProbs)
            missProbsA[i++] = d;
        double[] keyProbsA = new double[keyProbs.size()];
        i = 0;
        for (Double d: keyProbs)
            keyProbsA[i++] = d;
        

        return new OptimalBSTData(keys.toArray(new String[0]), 
                vals.toArray(new String[0]), keyProbsA, missProbsA);
    }
    
    public static double expectedSearchCost(OptimalBSTMap tree, OptimalBSTData raw) {
        double[] contributions = new double[2 * raw.keys.length + 1];
        int j = 0;
        // miss
        contributions[j++] = tree.nodesVisited(" ") * raw.missProbs[0];
        for (int i = 0; i < raw.keys.length; i++) {
            // hit
            contributions[j++] = tree.nodesVisited(raw.keys[i]) * raw.keyProbs[i];
            // miss
            contributions[j++] = 
                    tree.nodesVisited(raw.keys[i] + " ") * raw.missProbs[i+1];
        }
        Arrays.sort(contributions);
        double expectedCost = 0;
        for (double d : contributions)
            expectedCost += d;
        return expectedCost;
    }
    private static String reverse(String key) {
        String toReturn = "";
        for (char c : key.toCharArray())
            toReturn = c + toReturn;
        return toReturn;
    }

}
