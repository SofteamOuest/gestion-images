package fr.softeam.gestionimages.enums;

public enum GIE {
    CODE_001("Identifiant de la person vide."),
    CODE_002("Le fichier n'est pas une image."),
    CODE_003("Pas de fichier dans le dossier de la personne");

    private String message;

    GIE(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
