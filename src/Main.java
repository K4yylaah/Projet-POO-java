import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
         HistoryCommand history = new HistoryCommand();

         history.addCommand(new Commande(LocalDateTime.now().minusDays(2), "Commande 1"));
         history.addCommand(new Commande(LocalDateTime.now().minusDays(1), "Commande 2"));
         history.addCommand(new Commande(LocalDateTime.now(), "Commande 3"));

        System.out.println("Historique des commandes (décroissante)");
        for (Commande c : history.consultCommandes()) {
            System.out.println(c);
        }
    }
}