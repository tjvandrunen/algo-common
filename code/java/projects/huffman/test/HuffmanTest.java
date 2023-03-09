package test;

import static org.junit.Assert.*;

import org.junit.Test;

import alg.Huffman;
import alg.Huffman.EncodedMessage;

public class HuffmanTest {

    public void checkMsg(String msg) {
        EncodedMessage encMsg = Huffman.encode(msg);
        assertEquals(msg, Huffman.decode(encMsg));
    }
    
    @Test
    public void vaderTest() {
        checkMsg("IF THIS IS A CONSULAR SHIP THEN WHERE IS THE AMBASSADOR");
    }

}
