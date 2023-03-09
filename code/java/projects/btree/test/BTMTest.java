package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;

import static org.junit.Assert.*;

import impl.BTreeMap;

public class BTMTest {

    private BTreeMap<String, Integer> testMap;
    private String[] allKeys;
    public BTMTest () {
        try {
            Scanner file = new Scanner(new File("surnames"));
            int count = 0;
            while (file.hasNextLine()) { file.nextLine(); count++; }
            allKeys = new String[count];
            file.close();
            file = new Scanner(new File("surnames"));
            int i = 0;
            while(file.hasNextLine()) allKeys[i++] = file.nextLine();
            file.close();
        } catch (FileNotFoundException e) {
            assertTrue("File not found", false);
        }
    }
    
    private void populate(int keys) {
        if (keys == -1) keys = allKeys.length;
        for (int i = 0; i < keys && i < allKeys.length; i++) {
            //System.out.println(i + " " + allKeys[i]);
            testMap.put(allKeys[i],  i);
            //System.out.println(testMap);
        }
    }

    private void check(int keys, boolean checkVal) {
        if (keys == -1) keys = allKeys.length;
        for (int i = 0; i < keys && i < allKeys.length; i++) {
            //System.out.println(i + " " + allKeys[i]);
            if (checkVal) assertEquals(testMap.get(allKeys[i]).intValue(), i);
            else assertTrue(testMap.containsKey(allKeys[i]));
        }
    }

    private void checkIt(int keys) {
        if (keys == -1) keys = allKeys.length;
        String[] sortedKeys = new String[keys];
        for (int i = 0; i < sortedKeys.length; i++)
            sortedKeys[i] = allKeys[i];
        Arrays.sort(sortedKeys);
        Iterator<String> it = testMap.iterator();
        for (String key : sortedKeys) {
            assertTrue(it.hasNext());
            assertEquals(key, it.next());
        }
    }
    
    @Test
    public void oneNodePut() {
        testMap = new BTreeMap<String,Integer>(16);
        populate(15);
    }
    
    @Test
    public void oneNodeContainsKey() {
        testMap = new BTreeMap<String,Integer>(16);
        populate(15);
        check(15, false);
    }
    @Test
    public void oneNodeGet() {
        testMap = new BTreeMap<String,Integer>(16);
        populate(15);
        check(15, true);
    }
    @Test
    public void oneNodeIterator() {
        testMap = new BTreeMap<String,Integer>(16);
        populate(15);
        checkIt(15);
    }
    
    @Test
    public void bigPut() {
        testMap = new BTreeMap<String,Integer>(100);
        populate(-1);
    }
    
    @Test
    public void bigContainsKey() {
        testMap = new BTreeMap<String,Integer>(100);
        populate(-1);
        check(-1, false);
    }
    
    @Test    
    public void bigGet() {
        testMap = new BTreeMap<String,Integer>(100);
        populate(-1);
        check(-1, true);
    }
    @Test
    public void bigIterator() {
        testMap = new BTreeMap<String,Integer>(100);
        populate(-1);
        checkIt(-1);
    }
}
