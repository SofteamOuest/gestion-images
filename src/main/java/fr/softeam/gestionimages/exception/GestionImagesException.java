package fr.softeam.gestionimages.exception;

import fr.softeam.gestionimages.enums.GIE;

public class GestionImagesException extends Exception {

    public GestionImagesException(String reason){
        super(reason);
    }

    public GestionImagesException(GIE gie){
        super(gie.getMessage());
    }

    public GestionImagesException(GIE gie, String param){
        super(gie.getMessage()+" "+param);
    }

    public GestionImagesException(String reason, Throwable cause){
        super(reason, cause);
    }
}
