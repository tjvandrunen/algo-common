(*  signture defining the map ADT *)
signature MAP = 
sig
  type  mapping;
  exception NoSuchKey;

  val newMap :  mapping;

  val put:  mapping * string * string ->  mapping;
  val get:  mapping * string-> string;
  val containsKey:  mapping * string-> bool;
  val remove:  mapping * string-> mapping;
  val keys:  mapping  -> string list;
(*  val min: mapping -> string;
  val rMin: mapping -> mapping; *)
end;


(* The BST structure implementing the map ADT *)

structure BSTMap : MAP =
struct
  datatype  bst = Null | Node of string * string *  bst *  bst;
  type  mapping =  bst;
  exception NoSuchKey;

  val newMap = Null;

  fun put(Null, key, value) = Node(key, value, Null, Null) 
    | put(Node(ke, va, left, right), key, value) = 
                 if key < ke then Node(ke, va,  put(left, key, value), right)
                 else if key > ke then Node(ke, va, left, put(right, key, value))
                 else Node(key, value, left, right);

  fun get(Null, key) = raise NoSuchKey
    | get(Node(ke, va, left, right), key) = (* add code here - Ex 5.4 *)

  fun containsKey(Null, key) = false
    | containsKey(Node(ke, va, left, right), key) =
                if key < ke then containsKey(left, key)
                 else if key > ke then containsKey(right, key)
                 else true;

  fun successor(Null) = raise NoSuchKey
    | successor(Node(ke, va, Null, right)) = (ke, va, right)
    | successor(Node(ke, va, left, right)) =
      let val (mink, minv, leftover) = successor(left)
      in (mink, minv, Node(ke, va, leftover, right))
      end;
(*
  fun rMin(tr) = 
      let val (_, _, r) = successor(tr)
      in r
      end;
*)
  fun merge(x, Null) = x
    | merge(x, y) =
      let val (mink, minv, left) = successor(y);
        in Node(mink, minv, x, left)
       end;

  fun remove(Null, key) = Null
    | remove(Node(ke, va, left, right), key) =
      if key = ke then merge(left, right)
      else if key < ke then Node(ke, va, remove(left, key), right)
                         else Node(ke, va, left, remove(right, key));
(*
  fun min(Null) = raise NoSuchKey
    | min(Node(ke, _, Null, right)) = ke
    | min(Node(ke, _, left, right)) = min(left);
*)
  fun keys(Null) = []
    | keys(Node(ke, va, left, right)) = keys(left)@ke::keys(right);

end;

(* MLUnit *)

datatype testResult = Pass | Fail of string | Error of exn;

fun runTest(t) = if t() then Pass else Fail(":(") handle xxx => Error(xxx);

fun runTests([]) = []
  | runTests(f::rest) = runTest(f) :: runTests(rest);



(* val data = [5, 7, 2, 3, 8, 13, 4, 17, 1, 9, 16, 19, 18, 6, 19]; *)
val data = ["E","G","B","C","H","M","D","Q","A","I","P","S","R","F","S"];

exception NoMoreData;

open BSTMap

fun populate(n) =
    let fun pop(0, moreData) = newMap
          | pop(nn, []) = raise NoMoreData
          | pop(nn, x::moreData) = put(pop(nn-1, moreData), x, "x" );
    in
        pop(n, data)
    end;

fun putContainsKey() =
    containsKey(populate(1), "E");

fun bigPutContainsKey() = 
    let val ttt = populate(15);
    in
        (containsKey(ttt, "B") andalso 
         containsKey(ttt, "P") andalso
         containsKey(ttt, "M") andalso 
         containsKey(ttt, "G"))
    end;

(*
fun putMin() =
     let val ttt = populate(15);
     in min(ttt) = "A"
     end;
*)

fun putRemoveMin() =
    let val ttt = populate(15);
    in not (containsKey(remove(ttt, "A"), "A"))
    end;

(*
fun putRemoveMins() =
    let val ttt = populate(15);
    in not (containsKey(rMin(ttt), "A") orelse
            containsKey(rMin(rMin(ttt)), "B") orelse
            containsKey(rMin(rMin(rMin(ttt))), "C") orelse
            containsKey(rMin(rMin(rMin(rMin(ttt)))), "D"))
    end;
*)  
  
fun putRemove() =
     let val ttt = populate(15);
     in containsKey(ttt, "Q") andalso not (containsKey(remove(ttt, "Q"), "Q"))
     end;

fun putRemoveStillThere() =
     let val ttt = populate(15);
     in containsKey(remove(ttt, "Q"), "E")
     end;

val allTests = [putContainsKey, bigPutContainsKey, (*putMin,*) putRemoveMin, (*putRemoveMins,*)
                putRemove, putRemoveStillThere];

runTests(allTests);
