class Mot_dict {
    private String mot;
    private String definition;

    public Mot_dict(String mot, String definition) {
        this.mot = mot;
        this.definition = definition;
    }

    public String getMot() {
        return mot;
    }

    public String getDéfinition() {
        return definition;
    }

    public void setDéfinition(String definition) {
        this.definition = definition;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    public boolean synonyme(String autreMot) {
        return this.mot.equalsIgnoreCase(autreMot);
    }
}

class Dictionnaire {
    private int nb_mots;
    private Mot_dict[] Dict;
    private String Nom;

    public Dictionnaire(String nom, int taille) {
        Nom = nom;
        nb_mots = 0;
        Dict = new Mot_dict[taille];
    }

    public Dictionnaire(String nom) {
        this(nom, 100);
    }

    public void Ajouter_Mot(Mot_dict m) {
        if (nb_mots < Dict.length) {
            Dict[nb_mots] = m;
            nb_mots++;
            Trier();
        }
    }

    public void Trier() {
        for (int i = 0; i < nb_mots - 1; i++) {
            for (int j = i + 1; j < nb_mots; j++) {
                if (Dict[i].getMot().compareToIgnoreCase(Dict[j].getMot()) > 0) {
                    Mot_dict temp = Dict[i];
                    Dict[i] = Dict[j];
                    Dict[j] = temp;
                }
            }
        }
    }

    public void Supprimer_Mot(Mot_dict m) {
        for (int i = 0; i < nb_mots; i++) {
            if (Dict[i].getMot().equalsIgnoreCase(m.getMot())) {
                for (int j = i; j < nb_mots - 1; j++) {
                    Dict[j] = Dict[j + 1];
                }

                Dict[nb_mots - 1] = null;
                nb_mots--;
                return;
            }
        }
    }

    public String Recherche_dicho(String mot) {
        int debut = 0;
        int fin = nb_mots - 1;

        while (debut <= fin) {
            int milieu = (debut + fin) / 2;

            int comparaison =
                Dict[milieu].getMot().compareToIgnoreCase(mot);

            if (comparaison == 0) {
                return Dict[milieu].getDéfinition();
            }

            if (comparaison < 0) {
                debut = milieu + 1;
            } else {
                fin = milieu - 1;
            }
        }

        return "Mot introuvable";
    }

    public void Lister_dictionnaire() {
        System.out.println("Dictionnaire : " + Nom);

        for (int i = 0; i < nb_mots; i++) {
            System.out.println(
                Dict[i].getMot() + " : " + Dict[i].getDéfinition()
            );
        }
    }

    public int Nombre_synonyme(Mot_dict m) {
        int nombre = 0;

        for (int i = 0; i < nb_mots; i++) {
            if (!Dict[i].getMot().equalsIgnoreCase(m.getMot())
                    && Dict[i].getDéfinition()
                    .equalsIgnoreCase(m.getDéfinition())) {
                nombre++;
            }
        }

        return nombre;
    }
}

public class Main {
    public static void main(String[] args) {

        Dictionnaire d = new Dictionnaire("Mon dictionnaire", 10);

        Mot_dict m1 = new Mot_dict("Courage", "Force morale");
        Mot_dict m2 = new Mot_dict("Bonheur", "Grande joie");
        Mot_dict m3 = new Mot_dict("Tristesse", "Grande peine");
        Mot_dict m4 = new Mot_dict("Aventure", "Experience");

        d.Ajouter_Mot(m1);
        d.Ajouter_Mot(m2);
        d.Ajouter_Mot(m3);
        d.Ajouter_Mot(m4);

        d.Lister_dictionnaire();

        System.out.println("Definition : " + d.Recherche_dicho("Courage"));

        System.out.println(
            "Nombre de synonymes : " + d.Nombre_synonyme(m2)
        );

        d.Supprimer_Mot(m3);
        d.Lister_dictionnaire();
    }
}