package alg;

import adt.Map;
import adt.Set;

public class Room {

    private String name;
    private String description;
    private Set<Item> itemsInRoom;
    private Map<String,Room> directions;
    
    public void look() {
        System.out.println("You are in the " + name);
        System.out.println(description);
        System.out.println("You can go in the following directions:");
        for (String direction : directions) 
            System.out.println("\t" + direction);
        System.out.println("The following items are in the room:");
        for (Item x: itemsInRoom)
            System.out.println("\t" + x);
    }

    public void go(String direction, Player p) {
        if (directions.containsKey(direction))
            p.setLocation(directions.get(direction));
        else
            System.out.println("You cannot go " + direction + "!");
    }

    public void pickup(Item x, Player p) {
        if (itemsInRoom.contains(x)) 
            if (p.hasItem(x))
                System.out.println("You already have a(n) " + x + "!");
            else {
                p.acquireItem(x);
                System.out.println("Now you have a(n) " + x + "!");
            }
        else
            System.out.println("You cannot pick up " + x + "!");
    }
    
}
