
functor ListMap(K:MAPK) : MAP =
struct
  type k = K.k;
  type 'v mapping = (k * 'v) list;
  exception NoSuchKey;

  val newMap = []:(k * 'v) list;

  fun put([], key, value) = [(key, value)]
    | put((ke,va)::rest, key, value) =
        if K.eq(ke, key) then  (ke, value)::rest 
        else (ke, va)::put(rest, key, value);
  fun get([], key) = raise NoSuchKey
    | get((ke, va)::rest, key) =
        if K.eq(ke, key) then va 
        else get(rest, key);
  fun containsKey([], key) = false
    | containsKey((ke, _)::rest, key) = 
       K.eq(ke, key) orelse containsKey(rest, key);
  fun remove([], key) = []
    | remove((ke, va)::rest, key) =
        if K.eq(ke, key) then rest else (ke, va)::remove(rest, key);
  fun keys([]) = []
    | keys((ke, va)::rest) = ke::keys(rest);
end;
