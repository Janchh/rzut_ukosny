package rzut;

import java.io.FileWriter;
import java.io.IOException;

public class EulerMovementSimulation {

    public static double[] vectorField(double x, double y, double z) {
        double[] v = new double[3];
        v[0] = x * y;
        v[1] = Math.cos(z*x);
        v[2] = Math.sin(y*y);
        return v;
    }

    public static double[] eulerMethod(double[] pos, double stepSize, double t) {
        double[] newPos = new double[4];
        newPos[0] = pos[0] + stepSize * vectorField(pos[0], pos[1], pos[2])[0];
        newPos[1] = pos[1] + stepSize * vectorField(pos[0], pos[1], pos[2])[1];
        newPos[2] = pos[2] + stepSize * vectorField(pos[0], pos[1], pos[2])[2];
        newPos[3] = t + stepSize;
        return newPos;
    }

    public static void main(String[] args) throws IOException {

        double[] pos = {0, 0, 0, 0};
        double stepSize = 0.1;
        int numSteps = 10000;
        String fileName = "output.csv";

        FileWriter writer = new FileWriter(fileName);
        writer.append("x,y,z,t\n");

        for (int i = 0; i < numSteps; i++) {
            pos = eulerMethod(pos, stepSize, pos[3]);
            writer.append(pos[0] + "," + pos[1] + "," + pos[2] + "," + pos[3] + "\n");
        }

        writer.flush();
        writer.close();

    }
}
