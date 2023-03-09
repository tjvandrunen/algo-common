package alg;

import adt.List;
import impl.LinkedList;

public class Routes {

    private static class Route {
        public final String description;
        public final double distance;
        public final int time;
        public Route(String description, double distance, int time) {
            this.description = description;
            this.distance = distance;
            this.time = time;
        }
    }
    
    public static void main(String[] args) {
        List<Route>  routes = new LinkedList<Route>();
        routes.add(new Route("Roosevelt-Naperville-Warrenville", 9.3, 21));
        routes.add(new Route("Roosevelt-Naperville-Butterfield-Winfield", 9.6, 27));
        routes.add(new Route("Roosevelt-Winfield", 9.5, 25));
        routes.add(new Route("Blanchard-Naperville-Warrenville", 9.1, 26));
        routes.add(new Route("Harrison-Washington-Naperville-Warrenville", 9.7, 25));

        Route shortest = null;
        int totalTime = 0;
        for (Route current : routes) {
            if (shortest == null || current.distance < shortest.distance)
                shortest = current;
            totalTime += current.time;
        }
        System.out.println("Shortest route is " + shortest.description);
        System.out.println("Average time is " + 
                ((double) totalTime)/routes.size());
    }

}
