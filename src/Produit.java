public class Produit {
    private final String nom;
    private final String categorie;
    private final double prix;
    private final int quantite;

    public Produit(String nom, String categorie, double prix, int quantite) {
        if (prix <= 0 || quantite <= 0) {
            throw new IllegalArgumentException("Le prix et la quantité doivent être supérieurs à 0 !");
        }
        this.nom = nom;
        this.categorie = categorie;
        this.prix = prix;
        this.quantite = quantite;
    }

    public String getNom() { return nom; }
    public String getCategorie() { return categorie; }
    public double getPrix() { return prix; }
    public int getQuantite() { return quantite; }

    @Override
    public String toString() {
        return nom + " | Catégorie: " + categorie + " | Prix: " + prix + "€ | Quantité: " + quantite;
    }
}
