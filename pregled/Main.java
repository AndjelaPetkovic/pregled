package pregled;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static ArrayList<Pacijent> pacijenti = new ArrayList<Pacijent>();
	private static final String datoteka = "cekaonica.txt";;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Nutricionisticki pregled");
		VBox root = new VBox(10);
		createGui(root);
		
		Scene scene = new Scene(root, 650, 430);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	public void createGui(VBox root) {
		
		Label stanjeLb = new Label("Stanje u cekaonici:");
		TextArea stanjeTa = new TextArea();
		stanjeTa.setPrefHeight(350);
		stanjeTa.setEditable(false);
		stanjeTa.setMaxWidth(630);
				
		HBox bottom = new HBox(20);		
		Button pCekBtn = new Button("Pregled cekaonice");
		pCekBtn.setPrefWidth(250);
		Button pUredBtn = new Button("Pregled uredjeno");
		pUredBtn.setPrefWidth(250);
		
		bottom.getChildren().addAll(pCekBtn, pUredBtn);
		bottom.setAlignment(Pos.CENTER);
		
		ucitajiIspisi(pCekBtn, stanjeTa);
		sortirajiIspisi(pUredBtn, stanjeTa);
					
		root.getChildren().addAll(stanjeLb, stanjeTa, bottom);
		root.setPadding(new Insets(10,0,0,10));
	
	}
	
	public void ucitajiIspisi(Button ucitaj, TextArea ispisi) {
		
		ucitaj.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				StringBuilder sb = new StringBuilder();
											
				try {
					List<String> listaPacijenata = Files.readAllLines(Paths.get(datoteka));
					
					for (String l : listaPacijenata) {
						String[] podaci = l.split(", ");
						char pol = podaci[0].trim().charAt(0);
						int godine = Integer.parseInt(podaci[1].trim());
						double visina = Double.parseDouble(podaci[2].trim());
						double tezina = Double.parseDouble(podaci[3].trim());
						DailyActivity aktivnost = DailyActivity.valueOf(podaci[4].trim());
						
						Pacijent p = null;
						if (pol == 'M') {
							p = new Muskarac(tezina, visina, godine, aktivnost);
						}
						else {
							p = new Zena(tezina, visina, godine, aktivnost);
						}
						if (!pacijenti.contains(p)) {
							pacijenti.add(p);
						}
						sb.append(l + "\n");
					}
					
					if (listaPacijenata.isEmpty()) {
						ispisi.setText("Niko ne ceka u cekaonici!");
					}
					else {
						ispisi.setText(sb.toString());
					}		
				}
				catch(IOException e) {
					System.err.print("Greska prilikom ucitavanja datoteke");
					System.exit(1);
				}
			}			
		});
		
	}
	
	public void sortirajiIspisi(Button sortiraj, TextArea ispisi) {
		
		sortiraj.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				ispisi.clear();
				
				if (pacijenti.isEmpty()) {
					ispisi.setText("Prvo ucitajte datoteku");
				}
				else {
					Collections.sort(pacijenti);
					StringBuilder sb = new StringBuilder();
					for (Pacijent p : pacijenti) {
						sb.append(p);
					}
					ispisi.setText(sb.toString());	
				}
				
			}
			
		});
		
	}

}
