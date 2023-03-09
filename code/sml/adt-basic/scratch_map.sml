functor ScratchMap(K:MAPK) : MAP =
struct
  type k = K.k;
  datatype 'v mapping = Empty | Node of k * 'v *  'v mapping;
  exception NoSuchKey;

  val newMap = Empty;

  fun put(Empty, key, value) = Node(key, value, Empty)
    | put(Node(ke, va, rest), key, value) =
        if K.eq(ke,key) then Node(ke, value, rest)
	else Node(ke, va, put(rest, key, value));
  fun get(Empty, key) = raise NoSuchKey
    | get(Node(ke, va, rest), key) =
        if K.eq(ke, key) then va
        else get(rest, key);
  fun containsKey(Empty, key) = false
    | containsKey(Node(ke, _, rest), key) =
        K.eq(ke, key) orelse containsKey(rest, key);
  fun remove(Empty, key) = raise NoSuchKey
    | remove(Node(ke, va, rest), key) =
        if K.eq(ke, key) then rest
        else Node(ke, va, remove(rest, key));
  fun keys(Empty) = []
    | keys(Node(ke, va, rest)) = ke::keys(rest);


end;
