package exper;

import java.util.Random;

/**
 * Sanity check that all three approaches to making a random level
 * are equivalent.
 * @author tvandrun
 *
 */
public class RandLevelExperiment {
    private static Random rand = new Random();
    
    private static int randLevel1(int pInverse, int maxLevel) {
        for (int level = 0; level < maxLevel; level++)
            if (rand.nextInt(pInverse) != 0)
                return level;
        return maxLevel;
    }    
    
    private static int randLevel2(int pInverse, int maxLevel) {
        int level = 0;
        while(level < maxLevel && rand.nextInt(pInverse) == 0) level++;
        return level;
    }    
    
    private static int randLevel3(int pInverse, int maxLevel) {
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
        int[] res1 = new int[maxLevel+1],
              res2 = new int[maxLevel+1],
              res3 = new int[maxLevel+1];
                        
        for (int i = 0; i < numTrials; i++) {
            res1[randLevel1(pInverse, maxLevel)]++;
            res2[randLevel2(pInverse, maxLevel)]++;
            res3[randLevel3(pInverse, maxLevel)]++;
        }
        System.out.println("Version 1");
        for (int i = 0; i < res1.length; i++)
            System.out.println(i + " " + res1[i]);
        System.out.println("Version 2");
        for (int i = 0; i < res1.length; i++)
            System.out.println(i + " " + res2[i]);
        System.out.println("Version 3");
        for (int i = 0; i < res1.length; i++)
            System.out.println(i + " " + res3[i]);
        
    }
    
}
