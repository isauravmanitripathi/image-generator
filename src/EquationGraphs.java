import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class EquationGraphs {
    public static void main(String[] args) {
        outputGraph();
    }

    public static void outputGraph() {
        int sampleAmount = 1;
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
            fileContents = fileContents.substring(0, fileContents.length()-1);
            for(int i = 0; i < sampleAmount; i++) {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Graph currentGraph = drawGraph();

                byte[] bytes = (fileContents + (currentGraph.returnGraph())).getBytes();
                fout.write(bytes);
            }
            System.out.println("Generated Successfully");
            fout.close();
            reader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Graph drawGraph() {
        Graph graph1 = new Graph(100, 100);

        int[] startPos = {0, 0};

        Person newPerson = new Person("Graph Plotter", startPos, graph1);

        // Work out equation

        int y = 0;
        int x = 0;
        int prevY = y; // This is to reference later for the char / or -
        boolean valid = true;
        int pointType = 2; // 1 = "-", 3 = "/", 4 = "\"

        while (valid) {
            //y = (int) Math.pow(x, 3); // y = x^2
            y = (int) Math.pow((x - 2), 3) + 3; // (x - 2)^3 + 3

            if (prevY > y) pointType = 4; // The previous point was higher, so we are going down.
            else if (prevY < y) pointType = 3; // The previous point was lower, so we are going up.
            else pointType = 1; // We have not changed height as prevY == y; So continue straight ("-")

            if (x == 0) pointType = 2; // If this is the start of the line, we mark it with the significant point char ("X")

            if (y > graph1.ySize) {
                valid = false;
            } else if (x == graph1.xSize) {
                valid = false;
            } else {
                System.out.println("Added point: " + x + " " + y);
                newPerson.posGraph.addPointType(x, y, pointType); // Plot point
            }

            x++;
            prevY = y;
        }

        return newPerson.posGraph;
    }
}
