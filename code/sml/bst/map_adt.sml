signature MAP = 
sig
  type ('k, 'v) mapping;
  exception NoSuchKey;

  val newMap : ('k, 'v) mapping;

  val put: ('k, 'v) mapping * 'k * 'v -> ('k, 'v) mapping;
  val get: ('k, 'v) mapping * 'k-> 'v;
  val containsKey: ('k, 'v) mapping * 'k-> bool;
  val remove: ('k, 'v) mapping * 'k->('k, 'v) mapping;
  val keys: ('k, 'v) mapping  -> 'k list;
end;
