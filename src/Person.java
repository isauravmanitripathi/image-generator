public class Person {


    public int facingDirection = 0; // 0 = North, 1 = East, 2 = South, 3 = West
    public int doNotTry = 999; // out of the bounds of 0-3 as you can try them all currently
    public String personName = "";
    public int[] currentPosition;
    public Graph posGraph;
    public int outOfBoundsLimit = 0;

    public Person(String name, int[] startPosition, Graph space) {
        personName = name;
        currentPosition = startPosition;
        posGraph = space;
    }

    public boolean move(int stepAmount) {
        boolean canMove = true;

        try {
            int[] newPosition = {currentPosition[0], currentPosition[1]};

            switch (facingDirection) {
                case 0:
                    // Facing north (y pos ++)
                    newPosition[1] += stepAmount;
                    break;

                case 1:
                    // Facing east (x pos ++)
                    newPosition[0] += stepAmount;
                    break;

                case 2:
                    // Facing south (y pos --)
                    newPosition[1] -= stepAmount;
                    break;

                case 3:
                    // Facing west (x pos --)
                    newPosition[0] -= stepAmount;
                    break;
            }

            posGraph.removePoint(currentPosition[0], currentPosition[1], true, true);

            if (posGraph.addPoint(newPosition[0], newPosition[1])) { // Remove the old position of the person, add the new position.
                currentPosition[0] = newPosition[0];
                currentPosition[1] = newPosition[1];
                outOfBoundsLimit = 0;
            } else {
                doNotTry = facingDirection;
                // System.out.println("NOTE: Out of bounds!");
                outOfBoundsLimit++;
                if (outOfBoundsLimit > 4) {
                    //System.out.println("Out of bounds limit reached; exiting program");
                    // TODO: Break out [#] DONE - Using return statement
                    canMove = false;

                }
            }

        } catch (Exception e) {
            // System.out.println("Can't move:"); // out of the bounds of the graph
            // System.out.println(e);
            assert true;
        }

        return canMove;
    }
}
