package etud;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/** Représente une personne */
public class Personne {
	
	
	/** Le nom de l'entité */
	private String nom;
	/** La couleur de l'entité */
	private String couleur;
	/** La forme de l'entité */
	private Node maForme;
	
	
	/** La position en X */
	private double x;
	/** La position en Y */
	private double y;

	/**
	 * Construit une personne.
	 *
	 * @param nom     son nom
	 * @param x       sa position initiale en X
	 * @param y       sa position initiale en Y
	 * @param couleur sa couleur
	 */
	public Personne(String nom, double x, double y, String couleur) {
		this.x = x;
		this.y = y;
		setMaForme(new Circle(0, 0, 10, Color.valueOf(couleur)));
		getMaForme().setTranslateX(x);
		getMaForme().setTranslateY(y);
	}
	
	/** Change la forme de l'entité */
	public void setMaForme(Node maForme) {
		this.maForme = maForme;
		maForme.setUserData(this); // Permet de retrouver le modèle associé à la vue
	}

	/** Retourne la forme de l'entité */
	public Node getMaForme() {
		return maForme;
	}

	
	public void deplacer() {
		// Ajouter un délpacement
		getMaForme().setTranslateX(x);
	}
}
