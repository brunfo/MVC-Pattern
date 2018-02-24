package apFertilidade;

import java.io.IOException;

import apFertilidade.model.Parceiro;
import apFertilidade.view.ParceirosOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<Parceiro> parceirosData = FXCollections.observableArrayList();
	
	/**
	 * Construtor
	 */
	public MainApp() {
		//TODO ler dados de bd
		parceirosData.add(new Parceiro("Bruno", "Farmácia"));
		parceirosData.add(new Parceiro("Lucinana", "Famácia"));
		parceirosData.add(new Parceiro("Fernando's SexShop", "SexShop"));
		
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage= primaryStage;
		this.primaryStage.setTitle("APFertilidade");
		
		//carrega o root layout
		initRootLayout();
		//carrega o ParceirosOverview
		showParceirosOverview();
		
	}
    /**
     * Retorna o palco principal.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Inicializa o root layout ( layout de base).
	 */
	private void initRootLayout() {
		try {
			//Carrega o root layout do arquivo fmxl.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			//Mostra a scene contendo o root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * Mostra o scene ParceirosOverview dentro do root layout.
	 */
	public void showParceirosOverview() {
		try {
			//carrega o ParceirosOverview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource("view/ParceirosOverview.fxml"));
			AnchorPane parceirosOverview = (AnchorPane) loader.load();
			
			//Define parceirosOverview dentro do root layout.
			rootLayout.setCenter(parceirosOverview);
			
			//Dá ao controllador acesso à mainApp.
			ParceirosOverviewController controller = 
					loader.getController();
			controller.setMainApp(this);
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Retorna os dados como uma observable list de Parceiros
	 * @return 
	 */
	public ObservableList<Parceiro> getParceirosData(){
		return parceirosData;
	}


}
