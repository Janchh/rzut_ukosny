package rzut;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.FileWriter;
import java.io.IOException;

public class EulerMovementSimulation {



    //pola
    static double v0 = 0; //prędkość początkowa

    static double hight = 0;
    static double stepSize = 0.01;
    double m = 0; //masa
    static double g = 0; //przyspieszenie grawitacyjne
    static double oP = 0; //opór powietrza
    static double vW = 0; //prędkość wiatru
    static double kW = 0; //kierunek wiatru
    double dt = 0; //kierunek rzutu
    static double T = 0; //okres obrotu planety
    static double r = 0; //odległośc od osi obrotu planety
    static double phi = 0; //odchylenie od poziomu

    static double x, y, z, px, py, pz, t;



   // int Num_steps = 0; //ilosc krokow symulacji
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

    public double getHight(){
        return hight;
    }

    public void setHight(double wys){
        hight = wys;
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

    public static void updateMomentum(){
        double[] vecV = new double[3];
        vecV[0] = px;
        vecV[1] = py;
        vecV[2] = pz;
        double[] omega = new double[3];
        omega[0] = T*Math.sin(r);
        omega[1] = T*Math.cos(r);
        omega[2] = 0;
        double[] cross = new double[3];
        cross[0] = (-2)*(omega[1]*vecV[2]-vecV[1]*omega[2]);
        cross[1] = (-2)*(omega[2]*vecV[0]-vecV[2]*omega[0]);
        cross[2] = (-2)*(omega[0]*vecV[1]-vecV[0]*omega[1]);

        px += vW * Math.cos(kW)*stepSize +cross[0]*stepSize;
        py += vW * Math.sin(kW)*stepSize +cross[1]*stepSize;
        pz += -g*stepSize+cross[2]*stepSize;

        if (px>=0){
            px -= oP*px*px*stepSize;
        }
        else{
            px += oP*px*px*stepSize;
        }

        if(py>=0){
            py -= oP*py*py*stepSize;
        }
        else{
            py += oP*py*py*stepSize;
        }
        if(pz>=0){
            pz -= oP*pz*pz*stepSize;
        }
        else {
            pz += oP * pz * pz * stepSize;
        }
        //static double oP = 0; //opór powietrza
        //static double vW = 0; //prędkość wiatru
        //static double kW = 0; //kierunek wiatru

    }

    public static double[] determineForce(){
        double[] vecV = new double[3];
        vecV[0] = px;
        vecV[1] = py;
        vecV[2] = pz;
        double[] omega = new double[3];
        omega[0] = T*Math.sin(r);
        omega[1] = T*Math.cos(r);
        omega[2] = 0;
        double[] F = new double[3];
        F[0] = (-2)*(omega[1]*vecV[2]-vecV[1]*omega[2]);
        F[1] = (-2)*(omega[2]*vecV[0]-vecV[2]*omega[0]);
        F[2] = (-2)*(omega[0]*vecV[1]-vecV[0]*omega[1]);

        F[0] += vW * Math.cos(kW)*stepSize;
        F[1] += vW * Math.sin(kW)*stepSize;
        F[2] += -g;

        if (px>=0){
            F[0] -= oP*px*px;
        }
        else{
            F[0] += oP*px*px;
        }

        if(py>=0){
            F[1] -= oP*py*py;
        }
        else{
            F[1] += oP*py*py;
        }
        if(pz>=0){
            F[2] -= oP*pz*pz;
        }
        else {
            F[2] += oP * pz * pz;
        }

        return F;
    }
    public static void updatePosition() {
        double[] Force = determineForce();
        x += px * stepSize + 0.5 * Force[0] * stepSize * stepSize;
        y += py * stepSize + 0.5 * Force[1] * stepSize * stepSize;
        z += pz * stepSize + 0.5 * Force[2] * stepSize * stepSize;
    }
    // Vector field function
    /*public static double[] vectorField(double x, double y, double z, double t) {
        double[] v = new double[3];
        // Define vector field equations here
        v[0] = (Math.cos(kW)*vW)*Math.exp(-oP);
        v[1] = Math.sin(kW)*vW*Math.exp(-oP);
        v[2] = (-g*t+Math.sin(phi)*v0)*Math.exp(-oP);
        return v;
    }

    public double[] eulerMethod(double[] pos, double stepSize, double t) {
        double[] newPos = new double[4];
        newPos[0] = pos[0] + stepSize * vectorField(pos[0], pos[1], pos[2], pos[3])[0];
        newPos[1] = pos[1] + stepSize * vectorField(pos[0], pos[1], pos[2], pos[3])[1];
        newPos[2] = pos[2] + stepSize * vectorField(pos[0], pos[1], pos[2], pos[3])[2];
        newPos[3] = t + stepSize;
        return newPos;
    }*/

    public void simulate() throws IOException {

        // Define simulation parameters
        double[] pos = {0, 0, 0, 0};
        double stepSize = 0.01;
        /*   int numSteps = getNum_steps();*/
        String fileName = "output.csv";
        FileWriter writer = new FileWriter(fileName);
        writer.append(v0+"\n");
        writer.append(m+"\n");
        writer.append(g+"\n");
        writer.append(oP+"\n");
        writer.append(vW+"\n");
        writer.append(kW+"\n");
        writer.append(dt+"\n");
        writer.append(T+"\n");
        writer.append(r+"\n");
        writer.append(phi+"\n");
        writer.append(hight+"\n");
        writer.append("x,y,z,t\n");
        z=hight;
        int ilo = 0;
        pz = Math.cos(phi)*v0;
        px = Math.sin(phi)*v0;

        while (pos[2]>=0) {
            ilo += 1;
            pos[0] = x;
            pos[1] = y;
            pos[2] = z;
            pos[3] = t;

            updateMomentum();
            updatePosition();
            t+=stepSize;
            if(ilo % 3 == 0) {
                writer.append(pos[0] + "," + pos[1] + "," + pos[2] + "," + pos[3] + "\n");
            }
            //System.out.println(pos[0] + "," + pos[1] + "," + pos[2] + "," + pos[3] + "\n");
        }

        writer.flush();
        writer.close();

    }
}
