fun add(xx, element) = xx@[element];

fun set(xx, 0, element) = element::tl(xx)
  | set(x::rest, i, element) = x::set(rest, i-1, element);

fun get(x::rest, 0) = x
  | get(x::rest, i) = get(rest, i-1);

fun remove(x::rest, 0) = rest
  | remove(x::rest, i) = x::remove(rest, i-1);

fun insert(xx, 0, element) = element::xx
  | insert(x::rest, i, element) = x::insert(rest, i-1, element);

fun size([]) = 0
  | size(x::rest) = 1 + size(rest);


