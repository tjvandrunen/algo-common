#ifndef LIST_INTERFACE
#define LIST_INTERFACE
struct list 
{

  /**
   * Adds item to the end of the list. This increases the size of the 
   * list by one.
   */
  void (*add)(struct list* this, void* item);

  /**
   * Sets element index to item. Behaviour is undefined if 
   * index in not in the range [0,size) where size if the 
   * size of the passed list.
   */
  void (*set)(struct list* this, int index, void* item);
  
  /**
   * Return the element at index. Behaviour is undefined if 
   * index in not in the range [0,size) where size if the 
   * size of the passed list.
   */
  void* (*get)(struct list * this, int index);

  /**
   * Removes the element at index. This decreases the index
   * of every item with an index in the range (index,size) 
   * by one. It also decreases the size of the list by one.
   * Behaviour is undefined if index in not in the range [0,size). 
   */
  //  void* (*remove)(struct list * this, int index);

  /**
   * Inserts item at index. This increases the index of every
   * item in the range [index,size) by one and increased the size 
   * of the array by one. Behaviour is undefined if index in not in 
   * the range [0,size]. 
   */
  void (*insert)(struct list* this, int index, void* item);

  /**
   * Returns the number of elements in the array.
   */
  int (*size)(struct list* this);

  /**
   * The data members of the object.
   */
  void* data;
};

#endif

