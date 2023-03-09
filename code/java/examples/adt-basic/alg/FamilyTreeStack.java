package alg;

import java.util.Scanner;

import adt.Stack;
import impl.ListStackTopFront;

public class FamilyTreeStack {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Stack<Person> people = new ListStackTopFront<Person>();
        System.out.println("Who is the first ancestor?");
        people.push(new Person(keyboard.nextLine()));
        while (! people.isEmpty()) {
            Person current = people.top();
            System.out.println("Does " + current.name + " have any " +
                    (current.numChildren() > 0? "more " : "") +
                    "children? (y/n)");
            if (keyboard.nextLine().equals("y")) {
                System.out.println("Who is the next child?");
                Person next = new Person(keyboard.nextLine());
                current.addChild(next);
                people.push(next);
            }
            else
                people.pop();
        }
        keyboard.close();
    }

}
