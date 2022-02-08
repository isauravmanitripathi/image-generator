import java.util.Random;

public class Main {

    public static void main(String[] args){
        Graph graph1 = new Graph(20,20);
        Random rand = new Random();

        int[] startPos = {12 , 2};

        Person newPerson = new Person("Jack", startPos, graph1);

        newPerson.move(1);

        boolean valid = true;

        while(valid) {
            newPerson.facingDirection = rand.nextInt(4);
            while(newPerson.doNotTry == newPerson.facingDirection) {
                newPerson.facingDirection = rand.nextInt(4);
            }

            if(!newPerson.move(1)) {
                valid = false;
            }

            try{
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            newPerson.posGraph.displayGraph();
        }
    }
    
}
