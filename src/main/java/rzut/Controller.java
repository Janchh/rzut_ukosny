package rzut;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class Controller extends MainFrame implements Runnable{

    @FXML
    TextField vPocz;
    @FXML
    TextField mass;
    @FXML
    TextField grav;
    @FXML
    TextField res;
    @FXML
    TextField vwind;
    @FXML
    TextField dwind;
    @FXML
    TextField dthrow;
    @FXML
    TextField tim;
    @FXML
    TextField radi;
    @FXML
    TextField deg;
    @FXML
    TextField wys;
    @FXML
    Button button1; //zapisanie trajektorii
    @FXML
    Button button2; //zmiana języka
    @FXML
    Button otraj; //obliczanie trajektorii
    @FXML
    Label lb1;
    @FXML
    Label lb2;
    @FXML
    Label lb3;
    @FXML
    Label lb4;
    @FXML
    Label lb5;
    @FXML
    Label lb6;
    @FXML
    Label lb7;
    @FXML
    Label lb8;
    @FXML
    Label lb9;
    @FXML
    Label lb10;
    @FXML
    Label lb11;
    @FXML
    Label lb12;
    @FXML
    Label lb13;
    @FXML
    Canvas can1;
    public static boolean lang = false;
    private Window stage;
    int choose = 0;
    FileChooser fileChooser = new FileChooser();

    ArrayList<Double> x = new ArrayList<Double>();
    ArrayList<Double> y = new ArrayList<Double>();
    ArrayList<Double> z = new ArrayList<Double>();
    ArrayList<Double> t = new ArrayList<Double>();
    ArrayList<Double> x3d = new ArrayList<Double>();
    ArrayList<Double> y3d = new ArrayList<Double>();
    ArrayList<Double> size3d = new ArrayList<Double>();
    double maxx = 0;
    double maxy = 0;
    double maxz = 0;
    double maxx3d = 0;
    double maxy3d = 0;
    double minsize3d;
    boolean czy = false;
    int type = 0;

    boolean ifEndCalc = false;

    public boolean getIfEndCalc(){
      return ifEndCalc;
    };
    public boolean getLang(){
        return lang;
    }

    public void plEn(ActionEvent e){
        if(lang){
            //zmiana na j. polski
            pol();
            lang = false;
        } else{
            //zmiana na j. angielski
            eng();
            lang = true;
        }

    }
    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public void start(ActionEvent e){
        wczytaj();
        type = choose;
                if(!czy){
                    czy = true;
                    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
                    ExecutorService exec = Executors.newFixedThreadPool(2);
                    exec.execute(this::run);
                    exec.shutdown();
                }
                else{
                    czy = false;
                }

    }
    public void help(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (lang == false){
        alert.setTitle("Pomoc");
        alert.setHeaderText("Zaawansowany Symulator Rzutu Ukośnego GitHit200promax\nby Jan Chruśliński & Michał Ratajczyk @2023\njan.chruslinski.stud at pw.edu.pl\nmichal.ratajczyk.stud at pw.edu.pl");
        alert.setContentText("Ten program pozwala symulować ruch ukośny. \n\n" +
                                "Aby użyć programu, wykonaj następujące kroki: \n" +
                                "1. Wprowadź prędkość początkową, masę i inne parametry w menu po prawej stronie.\n" +
                                "2. Kliknij przycisk 'START/STOP', aby wystrzelić lub zatrzymać pocisk.\n" +
                                "3. Użyj przycisku 'Zapisz trajektorię', aby zapisać trajektorię do pliku csv.\n" +
                                "4. Dostosuj perspektywę za pomocą przycisków 'X', 'Y', 'Z', '3D'.\n" +
                                "Aby uzyskać dodatkową pomoc, zapoznaj się z dokumentacją programu lub skontaktuj się z twórcami.");
        alert.showAndWait().ifPresent(rs -> {
        });}
        else{
        alert.setTitle("Help");
        alert.setHeaderText("GitHit2000promax Advanced Trajectory Simulator\nby Ian Chruślińsky & Michael Ratajchyk @2023\njan.chruslinski.stud at pw.edu.pl\nmichal.ratajczyk.stud at pw.edu.pl");
        alert.setContentText("This program allows you to simulate projectile motion.\n\n" +
                "To use the program, follow these steps:\n" +
                "1. Enter the initial velocity, mass, and other parameters in the menu on the right.\n" +
                "2. Click the 'START/STOP' button to launch or stop the projectile.\n" +
                "3. Use the 'Save trajectory' button to save the trajectory to a csv file.\n" +
                "4. Adjust the perspective using the 'X', 'Y', 'Z', '3D' buttons.\n\n" +
                "For additional assistance, refer to the program's documentation or contact the creators.");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });}
    }
    public void oblicz(ActionEvent e) throws NotDoubleException{
        EulerMovementSimulation symulacja = new EulerMovementSimulation();

        try {
            symulacja.setV0(Double.parseDouble(vPocz.getText()));
        } catch (RuntimeException ex) {
            throw new NotDoubleException();
        }
        try {
            symulacja.setM(Double.parseDouble(mass.getText()));
        } catch (RuntimeException ex) {
            throw new NotDoubleException();
        }
        try {
            symulacja.setG(Double.parseDouble(grav.getText()));
        } catch (RuntimeException ex) {
            throw new NotDoubleException();
        }        try {
            symulacja.setoP(Double.parseDouble(res.getText()));
        } catch (RuntimeException ex) {
            throw new NotDoubleException();
        }        try {

        } catch (RuntimeException ex) {
            throw new NotDoubleException();
        }        try {
            symulacja.setvW(Double.parseDouble(vwind.getText()));
        } catch (RuntimeException ex) {
            throw new NotDoubleException();
        }        try {
            symulacja.setkW(Double.parseDouble(dwind.getText()));
        } catch (RuntimeException ex) {
            throw new NotDoubleException();
        }        try {
            symulacja.setdt(Double.parseDouble(dthrow.getText()));
        } catch (RuntimeException ex) {
            throw new NotDoubleException();
        }        try {
            symulacja.setT(Double.parseDouble(tim.getText()));
        } catch (RuntimeException ex) {
            throw new NotDoubleException();
        }
        try {
            symulacja.setR(Double.parseDouble(radi.getText()));
        } catch (RuntimeException ex) {
            throw new NotDoubleException();
        }try {
            symulacja.setPhi(Double.parseDouble(deg.getText()));
        } catch (RuntimeException ex) {
            throw new NotDoubleException();
        }try {
            symulacja.setHight(Double.parseDouble(wys.getText()));
        } catch (RuntimeException ex) {
            throw new NotDoubleException();
        }

        {
            //symulacja.wypisz();
            try {
                symulacja.simulate();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        }
    }
    public void zakObl(ActionEvent e){
        ifEndCalc = true;
    }
    public void save(ActionEvent e){
        if(!lang){
            fileChooser.setTitle("Zapisz trajektorię");
        }else{
            fileChooser.setTitle("Save trajectory");
        }
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            saveTextToFile(file);
        }
    }
    public void x(ActionEvent e){
        choose = 0;
    }
    public void y(ActionEvent e){
        choose = 1;
    }
    public void z(ActionEvent e){
        choose = 2;
    }
    public void td(ActionEvent e){
        choose = 3;
    }

    public void pol(){
        button1.setText("Zapisz trajektorię");
        button2.setText("EN");
        otraj.setText("Oblicz trajektorię");
        lb1.setText("Menu parametrów");
        lb2.setText("Prędkość początkowa");
        lb3.setText("Masa");
        lb4.setText("Przyspieszenie grawitacyjne");
        lb5.setText("Współczynnik oporu powietrza");
        lb6.setText("Prędkość wiatru");
        lb7.setText("Kierunek wiatru");
        lb8.setText("Kierunek rzutu");
        lb9.setText("Okres obrotu planety");
        lb10.setText("Odległość od osi planety");
        lb11.setText("Odchylenie od poziomu");
        lb12.setText("Wybór perspektywy");
        lb13.setText("Wysokość początkowa");
    }

    public void eng(){
        button1.setText("Save trajectory");
        button2.setText("PL");
        otraj.setText("Calculate trajectory");
        lb1.setText("Menu of parameters");
        lb2.setText("Initial speed");
        lb3.setText("Mass");
        lb4.setText("Gravitational acceleration");
        lb5.setText("Drag coefficient");
        lb6.setText("Wind speed");
        lb7.setText("Wind direction");
        lb8.setText("Throw direction");
        lb9.setText("The rotation period of the planet");
        lb10.setText("Distance from the axis of the planet");
        lb11.setText("Deviation from the level");
        lb12.setText("Perspective selection");
        lb13.setText("Starting hight");
    }

    private void saveTextToFile(File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            try (BufferedReader br = new BufferedReader(new FileReader("output.csv"))) {
                String line;
                String[] etykietyPl={
                        "Prędkość początkowa",
                        "Masa",
                        "Przyspieszenie grawitacyjne",
                        "Współczynnik oporu powietrza",
                        "Prędkość wiatru",
                        "Kierunek wiatru",
                        "Kierunek rzutu",
                        "Okres obrotu planety",
                        "Odległość od osi planety",
                        "Odchylenie od poziomu",
                        "Wysokość początkowa"};
                String[] etykietyEn={
                        "Initial speed",
                        "Mass",
                        "Gravitational acceleration",
                        "Drag coefficient",
                        "Wind speed",
                        "Wind direction",
                        "Throw direction",
                        "The rotation period of the planet",
                        "Distance from the axis of the planet",
                        "Deviation from the level",
                        "Starting hight"};
                for(int i = 0; i < 11; i++){
                    line = br.readLine();
                    if(!lang){
                        writer.println(etykietyPl[i]+": "+line);
                    }else{
                        writer.println(etykietyEn[i]+": "+line);
                    }
                }
                while ((line = br.readLine()) != null) {
                    writer.println(line);
                }
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("błąd zapisu");
        }
    }
    public void drawShapes(int i) {
        GraphicsContext gc = can1.getGraphicsContext2D();
        int h = (int) can1.getHeight();
        int w = (int) can1.getWidth();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, w, h);
        gc.setFill(Color.BLUE);
        if(type == 0){
            double res1 = h / max(maxz, maxy);
            gc.fillOval(res1 * y.get(i), h-res1 * z.get(i), 4, 4);
            gc.setFill(Color.BLACK);
            for(int j = 0; j < i; j++){
                gc.fillOval(res1 * y.get(j), h-res1 * z.get(j), 2, 2);
            }
        }
        if(type == 1){
            double res1 = h / max(maxz, maxx);
            gc.fillOval(res1 * x.get(i), h-res1 * z.get(i), 4, 4);
            gc.setFill(Color.BLACK);
            for(int j = 0; j < i; j++){
                gc.fillOval(res1 * x.get(j), h-res1 * z.get(j), 2, 2);
            }
        }
        if(type == 2){
            double res1 = h / max(maxx, maxy);
            gc.fillOval(res1 * x.get(i), h-res1 * y.get(i), 4, 4);
            gc.setFill(Color.BLACK);
            for(int j = 0; j < i; j++){
                gc.fillOval(res1 * x.get(j), h-res1 * y.get(j), 2, 2);
            }
        }
        if(type == 3){
            double res1 = h / max(maxx3d, maxy3d);
            double rozm = (1-((size3d.get(i)-minsize3d)/(maxx*maxx+maxz*maxz-minsize3d)))*20;
            gc.fillOval(res1 * x3d.get(i), h-res1 * y3d.get(i), rozm, rozm);
            gc.setFill(Color.BLACK);
            for(int j = 0; j < i; j++){
                gc.fillOval(res1 * x3d.get(j), h-res1 * y3d.get(j), 2, 2);
            }
        }
    }

    public void wczytaj(){
            try (BufferedReader br = new BufferedReader(new FileReader("output.csv"))) {
                maxx = 0;
                maxy = 0;
                maxz = 0;
                maxx3d = 0;
                maxy3d = 0;
                x.removeAll(x);
                y.removeAll(y);
                z.removeAll(z);
                x3d.removeAll(x3d);
                y3d.removeAll(y3d);
                size3d.clear();

                String line;
                for(int i = 0; i < 12; i++){
                    line = br.readLine();
                }
                double tmp1;
                double tmp2;
                double tmp3;
                double tmp;

                while ((line = br.readLine()) != null) {
                    String[] val = line.split(",");
                    tmp1 = Double.valueOf(val[0]);
                    x.add(tmp1);
                    maxx = max(tmp1, maxx);
                    tmp2 = Double.valueOf(val[1]);
                    y.add(tmp2);
                    maxy = max(tmp2, maxy);
                    tmp3 = Double.valueOf(val[2]);
                    z.add(tmp3);
                    maxz = max(tmp3, maxz);
                    tmp = Double.valueOf(val[3]);
                    t.add(tmp);
                    x3d.add(Math.sqrt(((tmp2+tmp1)/2)*((tmp2+tmp1)/2)+((3*tmp2-tmp1)/2)*((3*tmp2-tmp1)/2)));
                    y3d.add(tmp3);
                    maxx3d = max(maxx3d, x3d.get(x3d.size()-1));
                    maxy3d = max(maxy3d, y3d.get(y3d.size()-1));
                }
                int l = x.size();
                minsize3d = Math.sqrt(maxx*maxx+maxz*maxz);
                double maks = max(maxx, max(maxy, maxz));
                for(int i = 0; i < l; i++){
                    size3d.add(Math.sqrt((x.get(i)-maks)*(x.get(i)-maks)+(y.get(i))*(y.get(i))+(z.get(i)-maks)*(z.get(i)-maks)));
                    minsize3d = min(minsize3d, size3d.get(size3d.size()-1));
                }
            }
         catch (IOException ex) {
            System.out.println("błąd odczytu");
        }
    }

    private double max(double tmp, double maxx) {
        if(tmp > maxx){
            return tmp;
        }
        else{
            return maxx;
        }
    }
    private double min(double tmp, double maxx) {
        if(tmp < maxx){
            return tmp;
        }
        else{
            return maxx;
        }
    }

    public void run(){
        int l = x.size();
        for (int i = 0; i < l; i++) {
            if(!czy){
                break;
            }
            drawShapes(i);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        czy = false;
    }


}
