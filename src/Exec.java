import java.util.ArrayList;
import java.util.Scanner;

public class Exec {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Iris> trainingList = IrisIO.readFromFileToIrisList("training.txt");

        double _threshold = Math.random()*10%3;
        System.out.println("Enter learning parameter for perceptron");
        double _learningParam = scanner.nextDouble();
        double[] _weights = {Math.random(), Math.random(), Math.random(), Math.random()};

        Perceptron perc = new Perceptron(_threshold, _learningParam, _weights);

        System.out.println("Enter number of iterations");
        int iters = scanner.nextInt()-1;

        double[] accuracy = new double[iters+1];

        int curr = 0;
        while (iters >= curr){
            ++curr;
            System.out.println("iteration "+curr);
            perc.train(trainingList);
            accuracy[curr-1] = perc.accuracy();
            perc.clearAccuracy();
        }

        for (double num: accuracy) {
            System.out.println(num+"%");
        }

        System.out.println("Processing test.txt");

        ArrayList<Iris> testSet = IrisIO.readFromFileToIrisList("test.txt");
        perc.train(testSet);
        System.out.println("Accuracy: "+perc.accuracy());
        System.out.println('\n'+"Do you wish to input own data? (y/n)");
        System.out.println();
        String option = scanner.next();
        option.toLowerCase();
        if (option.charAt(0) == 'n') System.exit(0);
        else if (option.charAt(0) == 'y') {
            System.out.println("Enter data in format:\n[double],[double],[double],[double],[species_name]");
            String[] input = scanner.next().split(",");
            double[] rawInput = new double[4];
            for (int i = 0; i<=input.length-2 ;++i){
                rawInput[i] = Double.parseDouble(input[i]);
            }
            int i = perc.classify(rawInput);
        }
    }
}
