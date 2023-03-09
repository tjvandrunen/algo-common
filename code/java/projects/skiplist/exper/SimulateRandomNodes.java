package exper;

import java.util.Random;

public class SimulateRandomNodes {
    private static Random rand = new Random();

    private static int randLevel(int pInverse, int maxLevel) {
        int pm = (int) Math.pow(pInverse, maxLevel);
        int nonce = rand.nextInt(pm);
        int level = 0;
        while (level < maxLevel && nonce % pInverse == 0) {
            level++;
            nonce /= pInverse;
        }
        return level;
    }

    public static void main(String[] args) {
        int pInverse = Integer.parseInt(args[0]),
                maxLevel = Integer.parseInt(args[1]),
                numTrials = Integer.parseInt(args[2]);
        for (int i = 0; i < numTrials; i++)
            System.out.println(randLevel(pInverse, maxLevel));
    }
    
}
