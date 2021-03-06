package stage2_NuevaLista;

import classes.ListadeCompra;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private TextField inputNombreLista;
    @FXML
    private TextArea inputDescripLista;

    private ObservableList<ListadeCompra> dataPrincipal;

    public void asignarData(ObservableList<ListadeCompra> data) {
        this.dataPrincipal = data;
    }

    public void crearLista(ActionEvent event) {
        //Crear nueva lista
        try {
            //Carga las ventanas nuevas
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/stage1_Listas/screen.fxml"));
            Parent root1 = loader1.load();

            FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/stage3_ListaDescrip/screen.fxml"));
            Parent root3 = loader3.load();
            Stage screen3 = new Stage();
            screen3.setTitle("Lista Descripcion");
            screen3.setScene(new Scene(root3, 500, 500));

            //Se obtienen los valores ingresados
            String nombreListaNueva = inputNombreLista.getText();
            String descripListaNueva = inputDescripLista.getText();

            if (inputNombreLista != null && inputDescripLista != null) {
                //Nueva lista con valores ingresados
                ListadeCompra nuevaLista = new ListadeCompra(nombreListaNueva, descripListaNueva);

                //Se agrega la lista a lista de listas
                this.dataPrincipal.add(nuevaLista);
                stage1_Listas.Controller listasController = loader1.getController();
                listasController.setData(this.dataPrincipal);

                //Se manda la nueva lista a la pantalla de edicion de lista
                stage3_ListaDescrip.Controller listaDescripController = loader3.getController();
                listaDescripController.setScreen(nuevaLista);

                //Cierra ventana actual
                ((Node)(event.getSource())).getScene().getWindow().hide();
                //Muestra las ventanas nuevas
                screen3.show();
            } else {
                System.out.println("Falta llenar un campo");
            }

        } catch (Exception e)  {
            System.out.println("No se pudo cargar la pagina");
        }
    }

    public void retroceder(ActionEvent event) {
        try {
            //Cierra ventana actual
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (Exception e)  {
            System.out.println("No se pudo retroceder");
        }
    }
}
