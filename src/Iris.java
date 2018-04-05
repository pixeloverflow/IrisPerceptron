public class Iris implements Comparable<Iris>{
    private double sl;  //sepal length
    private double sw;  //sepal width
    private double pl;  //petal length
    private double pw;  //petal width
    private int species;  // virginica = 1 && versicolor = 0

    public Iris(double[] params, int _species){
        sl = params[0];
        sw = params[1];
        pl = params[2];
        pw = params[3];
        species = _species;
    }

    public double distanceTo(Iris o) {
        double result = 0;
        result += Math.pow(sl - o.sl, 2);
        result += Math.pow(sw - o.sw, 2);
        result += Math.pow(pl - o.pl, 2);
        result += Math.pow(pw - o.pw, 2);

        return result;
    }

    public double distanceTo(double[] o) {
        double result = 0;
        result += Math.pow(sl - o[0], 2);
        result += Math.pow(sw - o[1], 2);
        result += Math.pow(pl - o[2], 2);
        result += Math.pow(pw - o[3], 2);

        return result;
    }

    public double[] exportRaw(){
        return new double[]{sl, sw, pl, pw};
    }

    public int getSpecies() {
        return species;
    }

    @Override
    public int compareTo(Iris o) {
        double[] zero = {0,0,0,0};
        double one = distanceTo(zero);
        double two = o.distanceTo(zero);

        return Double.compare(one, two);
    }
}

