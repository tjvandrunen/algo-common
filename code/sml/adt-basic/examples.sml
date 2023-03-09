fun reverse(x) = 
  let fun reverseHelper(0, stk) = (0, stk, 1)
        | reverseHelper(x, stk) = 
          let val (y, stk2, pow) = reverseHelper(x div 10, push(stk, x mod 10));
          in (top(stk2) * pow + y, pop(stk2), pow * 10) end
  in
    #1(reverseHelper(x, newStack))
  end;




 
