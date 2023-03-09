package exper;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

import adt.Map;
import impl.*;

public class SkipListVsOtherListExperiment {

    
    public static void main(String[] args) {
        String[] names = new String[12000];
        try {
            Scanner file = new Scanner(new File("surnames"));
            int i = 0;
            while(file.hasNextLine())
                names[i++] = file.nextLine();
            assert i == 1000;
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
        
        Random randy = new Random();
        DecimalFormat deci = new DecimalFormat("0.00E0");
        
        // "practice" runs
        for (int i = 0; i < 3; i++) {
            int[] qIndices = new int[4000];
            int[] dIndices = new int[4000];
            for (int j = 0; j < qIndices.length; j++) {
                qIndices[j] = randy.nextInt(names.length);
                dIndices[j] = randy.nextInt(names.length);
            }
            ArrayMap<String, String> am = new ArrayMap<String,String>();
            runBuild(am, names, 0, 4000);
            runQuery(am, names, qIndices);
            runRemove(am, names, dIndices);
            LinkedListMap<String, String> llm = new LinkedListMap<String,String>();
            runBuild(llm, names, 0, 4000);
            runQuery(llm, names, qIndices);
            runRemove(llm, names, dIndices);
            SortedArrayMap<String, String> sam = new SortedArrayMap<String,String>();
            runBuild(sam, names, 0, 4000);
            runQuery(sam, names, qIndices);
            runRemove(sam, names, dIndices);
            SortedLinkedListMap<String, String> sllm = new SortedLinkedListMap<String,String>();
            runBuild(sllm, names, 0, 4000);
            runQuery(sllm, names, qIndices);
            runRemove(sllm, names, dIndices);
           SkipListMap<String, String> slm = new SkipListMap<String,String>();
            runBuild(slm, names, 0, 4000);
            runQuery(slm, names, qIndices);
            runRemove(slm, names, dIndices);
        }
        

        int[] lookupses = new int[] { 1500, 3000, 6000, 12000 };
        
        int trials = 5;
        for (int i = 0; i < lookupses.length; i++) {
        
        long amTimeBuild = 0, llmTimeBuild = 0, samTimeBuild = 0, sllmTimeBuild = 0, slmTimeBuild  = 0,
                amTimeQuery = 0, llmTimeQuery = 0, samTimeQuery = 0, sllmTimeQuery = 0, slmTimeQuery  = 0,
                amTimeRem = 0, llmTimeRem = 0, samTimeRem = 0, sllmTimeRem = 0, slmTimeRem  = 0;
            int[] qIndices = new int[lookupses[i]];
            int[] dIndices = new int[lookupses[i]];
            for (int j = 0; j < qIndices.length; j++) {
                qIndices[j] = randy.nextInt(names.length);
                dIndices[j] = randy.nextInt(names.length);
            }
            int start = randy.nextInt(names.length);
            for (int j = 0; j < trials; j++) {
                ArrayMap<String, String> am = new ArrayMap<String,String>();
                amTimeBuild += runBuild(am, names, start, lookupses[i]);
                amTimeQuery += runQuery(am, names, qIndices);
                amTimeRem += runRemove(am, names, dIndices);
               LinkedListMap<String, String> llm = new LinkedListMap<String,String>();
                llmTimeBuild += runBuild(llm, names, start, lookupses[i]);
                llmTimeQuery += runQuery(llm, names, qIndices);
                llmTimeRem += runRemove(llm, names, dIndices);
                SortedArrayMap<String, String> sam = new SortedArrayMap<String,String>();
                samTimeBuild += runBuild(sam, names, start, lookupses[i]);
                samTimeQuery += runQuery(sam, names, qIndices);
                samTimeRem += runRemove(sam, names, dIndices);
               SortedLinkedListMap<String, String> sllm = new SortedLinkedListMap<String,String>();
                sllmTimeBuild += runBuild(sllm, names, start, lookupses[i]);
                sllmTimeQuery += runQuery(sllm, names, qIndices);
                sllmTimeRem += runRemove(sllm, names, dIndices);
               SkipListMap<String, String> slm = new SkipListMap<String,String>(4, (int) Math.log(lookupses[i]));
                slmTimeBuild += runBuild(slm, names, start, lookupses[i]);
                slmTimeQuery += runQuery(slm, names, qIndices);
                slmTimeRem += runRemove(slm, names, dIndices);
           }           
            System.out.println(lookupses[i] + " lookups");
            System.out.println("Array Map: " + deci.format(amTimeBuild / trials) + "& " + deci.format(amTimeQuery / trials)+ "& " + deci.format(amTimeRem / trials));
            System.out.println("Linked List Map: " + deci.format(llmTimeBuild / trials) + " &" + deci.format(llmTimeQuery / trials) + " &" + deci.format(llmTimeRem / trials));
            System.out.println("Sorted Array Map: " + deci.format(samTimeBuild / trials) + "& " + deci.format(samTimeQuery / trials) + "& " + deci.format(samTimeRem / trials));
            System.out.println("Sorted Linked List Map: " + deci.format(sllmTimeBuild / trials) + "& " + deci.format(sllmTimeQuery / trials) + "& " + deci.format(sllmTimeRem / trials));
            System.out.println("Skiplist Map: " + deci.format(slmTimeBuild / trials) +  "& " + deci.format(slmTimeQuery / trials) +  "& " + deci.format(slmTimeRem / trials));
    }
    }
    
    
    private static long runBuild(Map<String, String> map, String[] names, int start, int num) {
        long fore = System.nanoTime();
        //for (String s: names)
        //    map.put(s, "");
        for (int i = 0; i < num; i++)
            map.put(names[(start + i)%names.length], " ");
        long aft = System.nanoTime();
        return aft-fore;
    }
    private static long runQuery(Map<String, String> map, String[] names, int[] indices) {
        long fore = System.nanoTime();
        for (int i : indices)
            map.containsKey(names[i]);
        long aft = System.nanoTime();
        return aft-fore;
    }
    
    private static long runRemove(Map<String, String> map, String[] names, int[] indices) {
        long fore = System.nanoTime();
        for (int i : indices)
            map.remove(names[i]);
        long aft = System.nanoTime();
        return aft-fore;
    }
  

}
