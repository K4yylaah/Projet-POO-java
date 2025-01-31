import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class HistoryCommand {
    private List<Commande> commandes;

    public void historyCommand() {
        this.commandes = new ArrayList<>();
    }

    public void addCommand (Commande commande){
        commandes.add(commande);
    }

    public List<Commande> consultCommandes() {
        commandes.sort(Comparator.comparing(Commande::getDateHeure).reversed());
        return commandes;
    }

}