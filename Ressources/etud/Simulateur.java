package etud;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.HashSet;
import java.util.Random;
import java.util.ResourceBundle;

/** Le contrôleur de l'application */
public class Simulateur implements Initializable {

	/** Le nombre de personnes et de groupes */
	int nbElements;

	/** Le panneau où seront dessinés/sélectionnés les personnes et les groupes */
	@FXML
	Pane contenu;

	/** L'ensemble des entités sélectionnées */
	private HashSet<Entite> selection = new HashSet<>();

	/** Une liste de couleurs */
	public static final String[] COLS = new String[] { "black", "brown", "red", "orange", "yellow", "green", "blue",
			"purple", "gray" };

	/** Le générateur aléatoire pour choisir les positions et les couleurs */
	private Random hasard = new Random();

	/** Prépare la vue et le modèle */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		for (int i = 0; i < 10; i++) {
			creePersonne();
		}
	}

	/** Crée une personne aléatoire et l'ajoute à la foule */
	public void creePersonne() {
		double x = hasard.nextDouble() * 780 + 10;
		double y = hasard.nextDouble() * 550 + 10;
		++nbElements;
		Personne p = new Personne("P" + nbElements, x, y, COLS[nbElements % COLS.length]);
		contenu.getChildren().add(p.getMaForme());
		p.setOnMouseClicked(this::mouseClicked);
	}

	/**
	 * Gère le clic souris pour (dé)sélectionner les personnes et les groupes.
	 * 
	 * @see Node#setOnMouseClicked(EventHandler)
	 */
	public void mouseClicked(MouseEvent mouseEvent) {
		Node source = (Node) mouseEvent.getSource();

		Entite personne = (Entite) source.getUserData();
		if (selection.contains(personne)) {
			selection.remove(personne);
			personne.couleurNormal();
		} else {
			selection.add(personne);
			personne.couleurSelection();
		}
		mouseEvent.consume();
	}

	/**
	 * Gère l'action du menu créer un groupe.
	 * 
	 * @see MenuItem#getOnAction()
	 */
	public void creerGroupes(ActionEvent event) {
		++nbElements;
		Groupe grp = new Groupe("groupe" + nbElements, COLS[hasard.nextInt(COLS.length)]);
		grp.setOnMouseClicked(this::mouseClicked);
		contenu.getChildren().add(grp.getMaForme());
		for (Entite p : selection) {
			grp.ajouter(p);
			p.couleurNormal(); // retire la sélection
		}
		selection.clear();
//    grp.getMaForme().toBack();
	}

	/**
	 * Gère l'action du menu déplace la sélection.
	 * 
	 * @see MenuItem#getOnAction()
	 */
	public void deplacer(ActionEvent event) {
		}
	}

	/**
	 * Gère l'action du menu créer une personne.
	 * 
	 * @see MenuItem#getOnAction()
	 */
	public void creerPersonnne(ActionEvent event) {
		creePersonne();
	}

	/**
	 * Gère l'action du menu Quitter.
	 * 
	 * @see MenuItem#getOnAction()
	 */
	public void quitter(ActionEvent event) {
		System.exit(0);
	}

	/**
	 * Gère l'action du menu A Propos.
	 * 
	 * @see MenuItem#getOnAction()
	 */
	public void aPropos(ActionEvent event) {
		Alert a = new Alert(Alert.AlertType.INFORMATION);
		a.setTitle("A Propos");
		a.setHeaderText("Simulateur de personnes");
		a.setContentText("M3105 - Patrons de conception\nPatron Composite");
		a.getButtonTypes().setAll(ButtonType.CLOSE);
		a.showAndWait();
	}
}
