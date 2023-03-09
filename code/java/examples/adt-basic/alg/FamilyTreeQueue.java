package alg;

import java.util.Scanner;

import adt.Queue;
import impl.ListQueue;


public class FamilyTreeQueue {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Queue<Person> people = new ListQueue<Person>();
        System.out.println("Who is the first ancestor?");
        people.enqueue(new Person(keyboard.nextLine()));
        while (! people.isEmpty()) {
            Person current = people.remove();
            System.out.println("How many children does " +
                    current.name + " have?");
            for(int numChildren = Integer.parseInt(keyboard.nextLine());
               numChildren > 0; numChildren--) {
                System.out.println("Who is " + current.name + "'s next child?");
                Person next = new Person(keyboard.nextLine());
                current.addChild(next);
                people.enqueue(next);
            }
        }
        keyboard.close();

    }

}
