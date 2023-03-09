structure ListStack : STACK =
struct  
  type 'a stack = 'a list;
  exception Empty;
  
  val newStack = [];

   fun push(stk, item) = item::stk;

    fun top([]) = raise Empty 
      | top(tp::rest) = tp;

    fun pop([]) = raise Empty
      | pop(tp::rest) = rest;

    fun isEmpty([]) = true
      | isEmpty(tp::rest) = false
end;

