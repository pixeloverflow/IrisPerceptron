import java.util.ArrayList;

public class Perceptron {
    private double[] weights;
    private double threshold;
    private double newThreshold;
    private double learningParameter;
    private int corrects = 0;
    private int iterations = 0;

    public Perceptron(double _threshold, double _learningParameter, double[] _weights){
        threshold = _threshold;
        learningParameter = _learningParameter;
        weights = _weights;
    }

    public int classify(double[] iris) {
        double result = 0;
        for (int i = 0; i <= iris.length-1 ;++i) {
            result += iris[i] * weights[i];
        }
        newThreshold = result;
        if (result >= threshold) {
            result = 1;
            System.out.print("Iris-virginica");
        }
        else {
            result = 0;
            System.out.print("Iris-versicolor");
        }
        return (int)result;
    }

    public void train(ArrayList<Iris> _data){
        System.out.println();
        for (Iris iris: _data) {
            int result = classify(iris.exportRaw());
            if (result == iris.getSpecies()) {
                ++corrects;
                System.out.println(" Correct!");
            }
            else if (result != iris.getSpecies()) {
                adjustWeight(iris.getSpecies(), result);
                adjustThreshold();
                System.out.println(" Incorrect. Adjusting weights . . .");
            }
            ++iterations;
        }
    }

    private void adjustWeight(int desired, int actual){
        for (int i = 0;i <= weights.length-1;++i) {
            weights[i] += weights[i]*learningParameter*(desired-actual);
        }
    }

    private void adjustThreshold(){
        threshold += threshold*learningParameter*(threshold-newThreshold);
    }

    public void clearAccuracy(){
        corrects = 0;
        iterations = 0;
    }

    public double accuracy(){
        return corrects*100 / iterations;
    }
}
