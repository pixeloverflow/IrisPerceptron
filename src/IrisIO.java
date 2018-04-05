import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class IrisIO {

    public static ArrayList<Iris> readFromFileToIrisList(String file){
        File input = new File(file);
        ArrayList<Iris> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(input);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                double[] rawdata = new double[4];

                for (int i=0; i<=3 ;++i) {
                    rawdata[i] = Double.parseDouble(data[i]);
                }
                int spc;
                if (data[4].equals("Iris-virginica")) spc = 1;
                else spc = 0;
                list.add(new Iris(rawdata, spc));
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static ArrayList<double[]> readFromFileToRaw(String file){
        ArrayList<double[]> list = new ArrayList<>();

        ArrayList<Iris> processed = readFromFileToIrisList(file);
        for (Iris iris: processed) {
            list.add(iris.exportRaw());
        }

        return list;
    }
}
