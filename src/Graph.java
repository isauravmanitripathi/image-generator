import java.util.ArrayList;
import java.util.Arrays;

public class Graph {

    public ArrayList<ArrayList<Integer>> contents = new ArrayList<ArrayList<Integer>>(); // Multi Dimensional 'ArrayList' for graph contents

    public int xSize; // Global variables
    public int ySize;
    public char wallChar = '-'; // "-" to represent flat lines
    public char pointChar = 'X'; // for default plot points
    public char trailChar = '/'; // "/" to represent gradient lines positive
    public char negTrailChar = '\\'; // "\" to represent negative grad line

    public Graph(int x, int y) {
        x++;
        y++;


        this.xSize = x;
        this.ySize = y;

        for (int i = 0; i < y; i++) { // Fill up the contents with empty spaces
            contents.add(new ArrayList<Integer>());

            for (int i2 = 0; i2 < x; i2++) {
                contents.get(i).add(0);
            }
        }

    }

    public void setGraph(ArrayList<ArrayList<Integer>> newContents) {
        contents = newContents;
    }

    public java.lang.String getGraphString() { // Simply returns the raw Array String
        return Arrays.toString((this.contents).toArray());
    }

    public boolean addPoint(int x, int y) { // Adds a point of significance
        boolean ableToAdd = true;

        if (contents.get(y).get(x) == 1) {
            ableToAdd = false;
        } else {
            this.contents.get(y).set(x, 2);
        }


        return ableToAdd;
    }

    public boolean addPointType(int x, int y, int type) { // Adds a point of specific type
        boolean ableToAdd = false;

        if (x > -1 && y > -1) {
            ableToAdd = true;

            if (contents.get(y).get(x) == 1) {
                ableToAdd = false;
            } else {
                this.contents.get(y).set(x, type);
            }
        }


        return ableToAdd;
    }


    public boolean removePoint(int x, int y, boolean trail, boolean wallTrail) { // Removes a point of significance
        boolean ableToRemove = true;

        if (contents.get(y).get(x) == 1) {
            ableToRemove = false;
        } else {
            if (trail) {
                if (wallTrail) {
                    addWall(x, y);
                } else {
                    this.contents.get(y).set(x, 3);
                }
            } else {
                this.contents.get(y).set(x, 0);
            }
        }

        return ableToRemove;
    }

    public void addWall(int x, int y) { // Adds a wall point - object
        this.contents.get(y).set(x, 1);
    }

    public void constructRectangle(int x, int y, int side1, int side2) { // Constructs a rectangle out of walls
        int wallX = side1;
        int wallY = side2;

        // Two vertical lines
        constructWall(x, y, wallY, 1);
        constructWall(x+wallX, y, wallY, 1);

        // Two horizontal lines
        constructWall(x, y, wallX, 2);
        constructWall(x, y+wallY, wallX+1, 2);
        /*
        Why wallX+1?:

        | | | |
        | 0 0 0 |
        | 0 0 0 |
        | | | | |

        This type of shape is made with the top corner missing, as the x length has to be 1 longer to complete it.
         */
    }

    public void constructWall(int x, int y, int length, int direction) { // Constructs an array of points in a direction
        int wallY = y;
        int wallX = x;

        for (int i = 0; i < length; i++) { 
            addWall(wallX, wallY); // Add a wall point

            switch (direction) {
                case 1: // Facing North, add to Y coord
                    wallY++;
                    break;
                case 2: // Facing East, add to X coord
                    wallX++;
                    break;
                case 3: // Facing South, subtract from Y coord
                    wallY--;
                    break;
                case 4: // Facing West, subtract from X coord
                    wallX--;
                    break;
                default:
                    wallX++;
            }
        }
    }

    public void displayGraph() { // Instead of raw formatted arrays, this will display the graph in graph form -> console
        ArrayList<Integer> toPrint = new ArrayList<Integer>(); 
        int i = contents.size()-1;

        while (i != -1) { // Loop through the arrays in contents
            // System.out.println("Getting: " + i);
            if (i < 10) { // If i is single digits
                System.out.print(i + " | ");
            } else { // If it is double digits, in order to prevent offsetting the whole line of the graph, we just print the 2nd digit
                System.out.print(Integer.toString(i).charAt(1) + " | "); // Prints the second digit of the double or triple digit number
            }

            toPrint = contents.get(i);
            for (int point : toPrint) { // This now converts the numbers to signs when outputting
                switch (point) {
                    case 1: // 1 == wall, represented by '-'
                        System.out.print(wallChar + " ");
                        break;
                    case 2: // 2 == significant point, represented by 'X'
                        System.out.print(pointChar + " ");
                        break;
                    case 3:
                        System.out.print(trailChar + " "); // "/"
                        break;
                    case 4:
                        System.out.println(negTrailChar + " "); // "\"
                        break;
                    default: // Default is to just print the integer value (usually just prints 0 to represent emptiness)
                        System.out.print(point + " ");

                }

            }
            System.out.println();
            
            // System.out.println(Arrays.toString(toPrint.toArray()));

            


            i--;
        }

        System.out.print("  ");

        for (int j = 0; j < contents.size()+1; j++) System.out.print("- "); // contents.size() +1 just to fill to the end of the graph

        System.out.println();

        System.out.print("    "); // Empty spaces (4) to align the X counter row to the graph


        for (int x = 0; x < contents.size(); x++) {
            if (x < 10) {
                System.out.print(x + " ");
            } else {
                System.out.print(Integer.toString(x).charAt(1) + " "); // Prints the first digit of the double or triple digit number
            }
        }
        // System.out.println("0");
        System.out.println("\n\n");

    }

    public String returnGraph() {
        ArrayList<Integer> toPrint = new ArrayList<Integer>();
        int i = contents.size()-1;
        String toReturn = "";

        while (i != -1) { // Loop through the arrays in contents
            // toReturn += "Getting: " + i);
            if (i < 10) { // If i is single digits
                toReturn += (i + " | ");
            } else { // If it is double digits, in order to prevent offsetting the whole line of the graph, we just print the 2nd digit
                toReturn += (Integer.toString(i).charAt(1) + " | "); // Prints the second digit of the double or triple digit number
            }

            toPrint = contents.get(i);
            for (int point : toPrint) { // This now converts the numbers to signs when outputting
                switch (point) {
                    case 1: // 1 == wall, represented by '.'
                        toReturn += (wallChar + " ");
                        break;
                    case 2: // 2 == significant point, represented by 'X'
                        toReturn += (pointChar + " ");
                        break;
                    case 3:
                        toReturn += (trailChar + " ");
                        break;
                    default: // Default is to just print the integer value (usually just prints 0 to represent emptiness)
                        toReturn += ("  "); // (point + " ") ("█ ") if you wish to use a square

                }

            }
            toReturn += "\n";

            // toReturn += ln(Arrays.toString(toPrint.toArray()));




            i--;
        }

        toReturn += ("  ");

        for (int j = 0; j < contents.size()+1; j++) toReturn += ("- "); // contents.size() +1 just to fill to the end of the graph

        toReturn += "\n";

        toReturn += ("    "); // Empty spaces (4) to align the X counter row to the graph


        for (int x = 0; x < contents.size(); x++) {
            if (x < 10) {
                toReturn += (x + " ");
            } else {
                toReturn += (Integer.toString(x).charAt(1) + " "); // Prints the first digit of the double or triple digit number
            }
        }
        // toReturn += "0");
        toReturn += "\n\n";

        return toReturn;

    }

    public void copyFrom(Graph graph) {
        this.contents = graph.contents;
    }
}