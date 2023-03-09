package adt;

/**
 * WrongNSetClassException
 * 
 * Class for exceptions to be thrown if one of the NSet
 * methods are called on an object of one class but a parameter
 * of another class or two sets drawn from different universes
 * (having different ranges).
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 * June 12, 2015
 */
public class BadNSetParameterException extends RuntimeException {

    private static final long serialVersionUID = 667339263345958043L;
    
    public BadNSetParameterException(String msg) {
        super(msg);
    }

}
