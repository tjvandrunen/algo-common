signature STACK = 
sig
        type 'a stack;
        exception Empty;

        val newStack : 'a stack;

        val push: 'a stack * 'a -> 'a stack;
        val top: 'a stack -> 'a ;            (* raises Empty *)
        val pop: 'a stack -> 'a stack   (* raises Empty *)
        val isEmpty: 'a stack -> bool
end;



