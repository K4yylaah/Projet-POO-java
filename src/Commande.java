import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Commande {
    private final LocalDateTime dateHeure;
    private final String details;

    public Commande(LocalDateTime dateHeure, String details) {
        this.dateHeure = dateHeure;
        this.details = details;

    }
    public LocalDateTime getDateHeure() {
        return dateHeure;
    }
    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "Commande{" + "dateHeure=" + dateHeure + ", details=" + details + '\'' + '}';
    }

}

