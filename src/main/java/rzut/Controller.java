package rzut;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {

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
    public void plEn(ActionEvent e){
        System.out.println("zmiana");
    }
    public void start(ActionEvent e){
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
    }
    public void save(ActionEvent e){
        System.out.println("zapisz");
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
}
