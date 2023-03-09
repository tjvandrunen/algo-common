package impl;

/**
 * HeapPositionAware
 * 
 * Interface to define how an optimizied heap should interact
 * with its elements, which need to be aware of their position
 * in the heap. A "heap position aware" is an object that is
 * aware of its array index in a heap.
 * 
 * This object should be in only one heap at a time (referred to below
 * as "the heap.")
 * 
 * @author Thomas VanDrunen
 * Algorithmic Commonplaces
 */
public interface HeapPositionAware {

    /**
     * Indicate to this object is new position in the heap.
     * @param pos The (new) position this object has in the heap.
     */
    void setPosition(int pos);
    
    /**
     * Query this object for its position in the heap.
     * @return This object's position in the heap.
     */
    int getPosition();
    
}
