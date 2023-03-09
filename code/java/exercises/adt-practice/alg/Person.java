package alg;

import adt.List;
import impl.LinkedList;

public class Person {
    public final String name;
    private List<Person> children;
    public Person(String name) {
        this.name = name;
        children = new LinkedList<Person>();
    }
    public void addChild(Person child) { children.add(child); }
    public int numChildren() { return children.size(); }
}
