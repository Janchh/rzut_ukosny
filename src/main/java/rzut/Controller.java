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


public class Controller extends MainFrame{

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
    Canvas can1;
    boolean lang = false;
    private Window stage;
    FileChooser fileChooser = new FileChooser();

    ArrayList<Double> x = new ArrayList<Double>();
    ArrayList<Double> y = new ArrayList<Double>();
    ArrayList<Double> z = new ArrayList<Double>();
    ArrayList<Double> t = new ArrayList<Double>();
    double maxx = 0;
    double maxy = 0;
    double maxz = 0;

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
    public void start(ActionEvent e){
        wczytaj();
        drawShapes();
    }
    public void help(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tekst pomocy");
        alert.setHeaderText("Informacje o programie znajdziesz pod linkiem");
        alert.setContentText("Link do pomocy");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }
    public void oblicz(ActionEvent e){
        EulerMovementSimulation symulacja = new EulerMovementSimulation();
        symulacja.setV0(Double.parseDouble(vPocz.getText()));
        symulacja.setM(Double.parseDouble(mass.getText()));
        symulacja.setG(Double.parseDouble(grav.getText()));
        symulacja.setoP(Double.parseDouble(res.getText()));
        symulacja.setvW(Double.parseDouble(vwind.getText()));
        symulacja.setkW(Double.parseDouble(dwind.getText()));
        symulacja.setdt(Double.parseDouble(dthrow.getText()));
        symulacja.setT(Double.parseDouble(tim.getText()));
        symulacja.setR(Double.parseDouble(radi.getText()));
        symulacja.setPhi(Double.parseDouble(deg.getText()));
        //symulacja.wypisz();
        try {
            symulacja.simulate();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
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
        System.out.println("x");
    }
    public void y(ActionEvent e){
        System.out.println("y");
    }
    public void z(ActionEvent e){
        System.out.println("z");
    }
    public void td(ActionEvent e){
        System.out.println("td");
    }
    public void all(ActionEvent e){
        System.out.println("all");
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
                        "Odchylenie od poziomu"};
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
                        "Deviation from the level"};
                for(int i = 0; i < 10; i++){
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

    public void drawShapes() {
        GraphicsContext gc = can1.getGraphicsContext2D();
        int h = (int) can1.getHeight();
        int w = (int) can1.getWidth();
        gc.setFill(Color.BLUE);
        int l = x.size();
        double resX = w / maxx;
        double resY = h / maxy;
        for (int i = 0; i < l; i++){
            gc.fillOval(resX * x.get(i), resY * y.get(i), 2, 2);
        }
    }

    public void wczytaj(){

            try (BufferedReader br = new BufferedReader(new FileReader("output.csv"))) {
                String line;
                for(int i = 0; i < 11; i++){
                    line = br.readLine();
                }
                double tmp;

                while ((line = br.readLine()) != null) {
                    String[] val = line.split(",");
                    tmp = Double.valueOf(val[0]);
                    x.add(tmp);
                    maxx = max(tmp, maxx);
                    tmp = Double.valueOf(val[1]);
                    y.add(tmp);
                    maxy = max(tmp, maxy);
                    tmp = Double.valueOf(val[2]);
                    z.add(tmp);
                    maxz = max(tmp, maxz);
                    tmp = Double.valueOf(val[3]);
                    t.add(tmp);
                }
            }
         catch (IOException ex) {
            System.out.println("błąd zapisu");
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
}
