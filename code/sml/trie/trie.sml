signature MAPK =
sig
  type k;
   val eq : k * k -> bool;
   val leq : k * k -> bool;
end;   

signature MAP = 
sig
  type 'v mapping;
  type k;
  exception NoSuchKey;

  val newMap :  'v mapping;

  val put:  'v mapping * k * 'v ->  'v mapping;
  val get:  'v mapping * k-> 'v;
  val containsKey: 'v mapping * k-> bool;
  val remove: 'v mapping * k-> 'v mapping;
  val keys: 'v mapping  -> k list;
end;


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

structure CharK : MAPK =
struct
  type k = char;
  fun eq(a:char, b) = a = b;
  fun leq(a:char, b) = a < b;
end;


(* The trie strucutre implementing the map ADT *)

structure TrieMap : MAP =
struct
  (* keys for this trie map are lists of type k *)
  type k = char list;
  (* each node has a map from k to other nodes *)
  structure nodeMap = ListMap(CharK);
  type 'v childMap = 'v nodeMap.mapping

  (* A node consists in SOME value if it is terminal, otherwise NONE;
      and a map from "characters" to child nodes  *)
  datatype 'v trie = Node of 'v option * 'v trie childMap;

  type 'v mapping = 'v trie;
  exception NoSuchKey;

   val newMap = Node(NONE,  nodeMap.newMap);

   fun put(Node(_, children), [], value) =  Node(SOME value, children) 
     | put(Node(v, children), c::rest, value) = 
          let val childTrie = nodeMap.get(children, c) 
                              handle nodeMap.NoSuchKey => newMap;
              val childTrieMod = put(childTrie, rest, value)
          in Node(v, nodeMap.put(children, c, childTrieMod)) end;

   fun get(Node(SOME v, children), []) = v
     | get(Node(NONE, children), []) = raise NoSuchKey
     | get(Node(_, children), c::rest) = 
          let val childTrie = nodeMap.get(children, c) 
              handle nodeMap.NoSuchKey => raise NoSuchKey;
          in get(childTrie, rest) end;

   fun containsKey(Node(SOME v, children), []) = true
     | containsKey(Node(NONE, children), []) = false
     | containsKey(Node(_, children), c::rest) =
         containsKey(nodeMap.get(children, c), rest)
          handle nodeMap.NoSuchKey => false;

   fun remove(trieMap, key) = trieMap;

   fun keys(trieMap) = []
end;


structure StringTrieMap =  (* Finish this for Exercise 8.26 *)
struct
  val newMap = (* ?? *)
  fun put(trieMap, key, value) = (* ?? *)
  fun get(trieMap, key) = (* ?? *)
end;



val et1 = StringTrieMap.newMap;


val et2 = StringTrieMap.put(et1, "Illinois", "Springfield");
StringTrieMap.get(et2, "Illinois");
