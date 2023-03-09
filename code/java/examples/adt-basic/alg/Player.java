package alg;

import adt.Set;

public class Player {

    private Room currentRoom;
    private Set<Item> inventory;

    public void setLocation(Room room) {
        this.currentRoom = room;
    }

    public boolean hasItem(Item x) {
        return inventory.contains(x);
    }

    public void acquireItem(Item x) {
        inventory.add(x);
    }
}
