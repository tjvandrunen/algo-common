package exper;

import adt.Map;
import impl.SkipListMap;

public class SkipListExperiment {

    public static void main(String args[]) {
        int n = 100000;
        
        Map<Integer,Integer> map = new SkipListMap<Integer,Integer>(4, 16);
        for (int i = 0; i < n; i++){
            map.put(i,i);
        }
        // java stuff
        for(int k = 0; k <20; k++ ) {
            for (int i = 0; i < n; i++){
                map.get(i);
            }
        }
        long fore = System.currentTimeMillis();
        for (int i = 0; i < n; i++){
            map.get(i);
        }
        long aft = System.currentTimeMillis();
        System.out.println("Time to get " + n+ " ordered elements: " +(aft-fore)+"ms");
    }
}
