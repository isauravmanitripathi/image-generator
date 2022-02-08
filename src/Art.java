import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Art {
    public static int counter = 0;

    public static void main(String[] args) {
        outputArt();

    }

    public static void outputArt() {
        int sampleAmount = 10;
        int data = 0;
        String fileContents = "";
        String fileName = "C:\\Users\\torin\\Desktop\\General\\Resources\\Personal\\Development\\Java\\AutoGen Art\\art.txt";

        try {
            FileOutputStream fout = new FileOutputStream(fileName);
            FileReader reader = new FileReader(fileName);
            while (data != -1) {
                data = reader.read();
                fileContents += (char) data;
            }
            for(int i = 0; i < sampleAmount; i++) {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Graph currentGraph = makeArt();
                Graph oldGraph = currentGraph;


                for (int j = 0; j < 10; ++j) {
                    oldGraph.copyFrom(currentGraph);
                    currentGraph = advancedArt(oldGraph.contents);
                    System.out.println(currentGraph.returnGraph());
                }


                byte[] bytes = (fileContents + advancedArt(currentGraph.contents).returnGraph()).getBytes();
                fout.write(bytes);
            }
            System.out.println("Generated Successfully");
            fout.close();
            reader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static Graph makeArt() {
        Graph graph1 = new Graph(50, 50);
        Random rand = new Random();

        // System.out.println(graph1.getGraphString());

        int[] startPos = {25, 25};

        Person newPerson = new Person("Dave", startPos, graph1);



        newPerson.move(1);

        boolean valid = true;

        while(valid) { // Runs until the pointer gets stuck in a square of walls

            newPerson.facingDirection = controlledChances(); // can be changed to 4 for exactly equal chances
            while (newPerson.doNotTry == newPerson.facingDirection) {
                newPerson.facingDirection = controlledChances();
            }

            if ( !newPerson.move(1) ) {
                valid = false;
            }





        }

        counter++;
        //System.out.println(counter);

        return newPerson.posGraph;



    }

    public static Graph advancedArt(ArrayList<ArrayList<Integer>> contents) {
        Graph graph1 = new Graph(50, 50);
        graph1.setGraph(contents);
        Random rand = new Random();

        // System.out.println(graph1.getGraphString());

        int[] startPos = {25, 25};

        Person newPerson = new Person("Dave", startPos, graph1);



        newPerson.move(1);

        boolean valid = true;

        while(valid) { // Runs until the pointer gets stuck in a square of walls

            newPerson.facingDirection = controlledChances();
            while (newPerson.doNotTry == newPerson.facingDirection) {
                newPerson.facingDirection = controlledChances();
            }

            if ( !newPerson.move(1) ) {
                valid = false;
            }





        }

        counter++;
        //System.out.println(counter);

        return newPerson.posGraph;
    }

    public static int controlledChances() { // Control chances of drawing point changing direction and which direction it changes to
        Random rand = new Random();

        int chance = rand.nextInt(100); // Random number in 100
        int facingDirection = 0;

        if (chance < 25) {
            facingDirection = 0; // North
        } else if (chance < 50) {
            facingDirection = 2; // South
        } else if (chance < 75) {
            facingDirection = 1; // East
        } else {
            facingDirection = 3; // West
        }


        return facingDirection;
    }
}