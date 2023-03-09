package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import adt.NSet;
import impl.BArrayNSet;
import impl.BitVecNSet;

public class BVNSTest extends NSetTest {

    protected void reset() {
        testSet = new BitVecNSet(data.length);
    }

    private static int[] areacodes;
    
    static {
        areacodes = new int[303];
        try {
            Scanner file = new Scanner(new File("areacodes"));
            int i = 0;
            while (file.hasNextLine()) 
                areacodes[i++] = Integer.parseInt(file.nextLine());
            file.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File areacodes missing");
        }
        
    }
    
    private static class EfficiencyException extends RuntimeException {
        public EfficiencyException(String msg) { super(msg); }
    }
    
    @Test
    public void efficientUnion() {
        long baTime = 0, bvTime = 0, fore = 0, aft = 0;
        for (int i = 0; i < 4; i++) {
            BArrayNSet a = new BArrayNSet(1000),
                    b = new BArrayNSet(1000);
            buildBig(a, b);
            if (i == 3) fore = System.nanoTime();
            a.union(b);
            if (i == 3) {
                aft = System.nanoTime();
                baTime = aft - fore;
            }
        }        
        for (int i = 0; i < 4; i++) {
            BitVecNSet a = new BitVecNSet(1000),
                    b = new BitVecNSet(1000);
            buildBig(a, b);
            if (i == 3) fore = System.nanoTime();
            a.union(b);
            if (i == 3) {
                aft = System.nanoTime();
                bvTime = aft - fore;
            }            
        }        
        if ((baTime / bvTime) < 3)
            throw new EfficiencyException("BitVecNSet.union() too slow.");
    }

    @Test
    public void efficientIntersection() {
        long baTime = 0, bvTime = 0, fore = 0, aft = 0;
        for (int i = 0; i < 4; i++) {
            BArrayNSet a = new BArrayNSet(1000),
                    b = new BArrayNSet(1000);
            buildBig(a, b);
            if (i == 3) fore = System.nanoTime();
            a.intersection(b);
            if (i == 3) {
                aft = System.nanoTime();
                baTime = aft - fore;
            }
        }        
        for (int i = 0; i < 4; i++) {
            BitVecNSet a = new BitVecNSet(1000),
                    b = new BitVecNSet(1000);
            buildBig(a, b);
            if (i == 3) fore = System.nanoTime();
            a.intersection(b);
            if (i == 3) {
                aft = System.nanoTime();
                bvTime = aft - fore;
            }            
        }        
        if ((baTime / bvTime) < 3)
            throw new EfficiencyException("BitVecNSet.intersection() too slow.");
    }

    @Test
    public void efficientDifference() {
        long baTime = 0, bvTime = 0, fore = 0, aft = 0;
        for (int i = 0; i < 4; i++) {
            BArrayNSet a = new BArrayNSet(1000),
                    b = new BArrayNSet(1000);
            buildBig(a, b);
            if (i == 3) fore = System.nanoTime();
            a.difference(b);
            if (i == 3) {
                aft = System.nanoTime();
                baTime = aft - fore;
            }
        }        
        for (int i = 0; i < 4; i++) {
            BitVecNSet a = new BitVecNSet(1000),
                    b = new BitVecNSet(1000);
            buildBig(a, b);
            if (i == 3) fore = System.nanoTime();
            a.difference(b);
            if (i == 3) {
                aft = System.nanoTime();
                bvTime = aft - fore;
            }            
        }        
        if ((baTime / bvTime) < 3)
            throw new EfficiencyException("BitVecNSet.complement() too slow.");
    }

    private void buildBig(NSet a, NSet b) {
        for (int i = 0; i < areacodes.length; i++) {
            if (i % 2 == 0) a.add(areacodes[i]);
            if (i % 3 == 0) b.add(areacodes[i]);
        }
    }
}
