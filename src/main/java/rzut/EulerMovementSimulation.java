package rzut;

import java.io.FileWriter;
import java.io.IOException;

public class EulerMovementSimulation {


    //pola
    double v0 = 0; //prędkość początkowa
    double m = 0; //masa
    double g = 0; //przyspieszenie grawitacyjne
    double oP = 0; //opór powietrza
    double vW = 0; //prędkość wiatru
    double kW = 0; //kierunek wiatru
    double dt = 0; //kierunek rzutu
    double T = 0; //okres obrotu planety
    double r = 0; //odległośc od osi obrotu planety
    double phi = 0; //odchylenie od poziomu
   // int Num_steps = 0; //ilosc krokow symulacji

    public double getV0() {
        return v0;
    }

    public void setV0(double v0) {
        this.v0 = v0;
    }

    public double getdt() {
        return dt;
    }

    public void setdt(double dt) {
        this.dt = dt;
    }

    public double getM() {
        return m;
    }

    public void setM(double m) {
        this.m = m;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getoP() {
        return oP;
    }

    public void setoP(double oP) {
        this.oP = oP;
    }

    public double getvW() {
        return vW;
    }

    public void setvW(double vW) {
        this.vW = vW;
    }

    public double getkW() {
        return kW;
    }

    public void setkW(double kW) {
        this.kW = kW;
    }

    public double getT() {
        return T;
    }

    public void setT(double t) {
        T = t;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getPhi() {
        return phi;
    }

    public void setPhi(double phi) {
        this.phi = phi;
    }
    
/*    public int getNum_steps(){
        return Num_steps;    
    }
    
    public void setNum_steps(double num_steps){
        this.Num_steps = num_steps;
    }*/

    public void wypisz(){
        System.out.println(v0);
        System.out.println(m);
        System.out.println(g);
        System.out.println(oP);
        System.out.println(vW);
        System.out.println(kW);
        System.out.println(T);
        System.out.println(r);
        System.out.println(phi);
    }

    // Vector field function
    public static double[] vectorField(double x, double y, double z) {
        double[] v = new double[3];
        // Define vector field equations here
        kW = getkW();
        vK = getvK();
        oP = getoP();
        g = getG();
        phi = getPhi();
        v0 = getV0();
        v[0] = (Math.cos(kW)*vK)*Math.exp(-oP);
        v[1] = Math.sin(kW)*vK*Math.exp(-oP);
        v[2] = -g*Math.exp(-oP)+Math.sin(phi)*v0;
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

    public static void simulate(String[] args) throws IOException {

        // Define simulation parameters
        double[] pos = {0, 0, 0, 0};
        double stepSize = 0.1;
     /*   int numSteps = getNum_steps();*/
        String fileName = "output.csv";

        FileWriter writer = new FileWriter(fileName);
        writer.append("x,y,z,t\n");

        while (pos[2]>=0) {
            pos = eulerMethod(pos, stepSize, pos[3]);
            writer.append(pos[0] + "," + pos[1] + "," + pos[2] + "," + pos[3] + "\n");
        }

        writer.flush();
        writer.close();

    }*/
}
