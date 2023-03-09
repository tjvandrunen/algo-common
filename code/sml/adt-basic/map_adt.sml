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
