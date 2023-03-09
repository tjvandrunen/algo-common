package alg;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import adt.Bag;
import adt.Set;
import impl.ListBag;
import impl.MapSet;

public class WordCount {

    public static void main(String[] args) {
        try {
            Scanner file = new Scanner(new FileInputStream(args[0]));
            Bag<String> tally = new ListBag<String>();
            Set<String> vocabulary = new MapSet<String>();
            
            // tally the words
            while(file.hasNext()) 
                for (StringTokenizer tokey = new StringTokenizer(file.nextLine(), 
                        "!()-;:\'\",.?1234567890 ");
                        tokey.hasMoreTokens(); ) {
                    String current = tokey.nextToken();
                    tally.add(current);
                    vocabulary.add(current);
                }
            file.close();

            // sort them by frequency 
            String[] wordsByFreq = new String[vocabulary.size()];
            int i = 0;
            for (String word : vocabulary) {
                int freq = tally.count(word);
                int j = i;
                while(j > 0 &&  tally.count(wordsByFreq[j-1]) < freq) {
                    wordsByFreq[j] = wordsByFreq[j-1];
                    j--;
                }
                wordsByFreq[j] = word;
                i++;
            }            

            // print them from most frequent to least frequent
            System.out.println("Word\tfrequency");
            for (String word : wordsByFreq)
                System.out.println(word + "\t" + tally.count(word)); 
            
        } catch(IOException ioe) { }
    }

}
