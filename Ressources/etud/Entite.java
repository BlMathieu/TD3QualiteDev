package etud;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

/** Représente une entité */
public abstract class Entite {
	/** La couleur utilisée pour présenter une entité sélectionnée */
	public static final Color COULEUR_SELECTION = new Color(1, 0, 0, 0.5);

	/** Le nom de l'entité */
	private String nom;
	/** La couleur de l'entité */
	private String couleur;
	/** La forme de l'entité */
	private Node maForme;

	/** Change la forme de l'entité */
	public void setMaForme(Node maForme) {
		this.maForme = maForme;
		maForme.setUserData(this); // Permet de retrouver le modèle associé à la vue
	}

	/** Retourne la forme de l'entité */
	public Node getMaForme() {
		return maForme;
	}

	/**
	 * Crée une entité.
	 * 
	 * @param nom     le nom de l'entité
	 * @param couleur sa couleur
	 */
	public Entite(String nom, String couleur) {
		this.nom = nom;
		this.couleur = couleur;
	}

	/** Restaure la couleur originale de l'entité */
	public void couleurNormal() {
		((Shape) maForme).setFill(Color.valueOf(couleur));
	}

	/** Peint l'entité en "sélectionné" */
	public void couleurSelection() {
		((Shape) maForme).setFill(COULEUR_SELECTION);
	}

	/**
	 * Ajoute une bordure pour représenter l'appartenance à un groupe.
	 * 
	 * @param couleurs un tableau de couleurs
	 */
	public void setBordure(Color... couleurs) {
		Stop[] stops = new Stop[couleurs.length * 2];
		for (int i = 0; i < couleurs.length; ++i) {
			stops[i * 2 + 0] = new Stop((2 * i) / (stops.length - 1.0), Color.WHITE);
			stops[i * 2 + 1] = new Stop((2 * i + 1) / (stops.length - 1.0), couleurs[i]);
		}

		((Shape) maForme).setStroke(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops));
		((Shape) maForme).setStrokeType(StrokeType.OUTSIDE);
		((Shape) maForme).setStrokeWidth(5);
	}

	/** Remet la bordure normale pour cette entité */
	public void restaureBordure() {
		((Shape) maForme).setStroke(Color.TRANSPARENT);
	}

	/** Retourne la couleur de l'entité */
	public String getCouleur() {
		return couleur;
	}

	/** Retourne le nom de l'entité */
	public String getNom() {
		return nom;
	}

	/**
	 * Méthode proxy pour faciliter l'accès à la forme.
	 * 
	 * @see Node#setOnMouseClicked(EventHandler)
	 */
	public void setOnMouseClicked(EventHandler<? super MouseEvent> mouseClicked) {
		// Rappel : JavaFX applique d'abord les filtres du noeud principal vers les
		// feuilles
		// puis les handlers en remontant vers le noeud principal
		// s'arrête dès que l'événement est consommé.
		/*
		 * // applique aux enfants d'abord maForme.setOnMouseClicked(mouseClicked); //
		 * applique aux parents d'abord maForme.addEventFilter(MouseEvent.MOUSE_CLICKED,
		 * mouseClicked);
		 */
		maForme.setOnMouseClicked(mouseClicked);
	}

	/** Déplace l'entité */
	public abstract void deplacer();
}
