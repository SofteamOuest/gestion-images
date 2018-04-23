package fr.softeam.gestionimages.aspect;

import fr.softeam.gestionimages.service.GestionImagesService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class GestionImagesAspect {

    private final GestionImagesService gestionImagesService;

    @Autowired
    public GestionImagesAspect(GestionImagesService gestionImagesService) {
        this.gestionImagesService = gestionImagesService;
    }

    static final Logger LOG = LoggerFactory.getLogger(GestionImagesService.class);

    @After("execution(* fr.softeam.gestionimages.service.GestionImagesService.upload(..))")
    public void logAfterUpdateCall(JoinPoint joinPoint){
        String idPerson = (String)joinPoint.getArgs()[0];
        LOG.info("Appel service rest Upload pour la personne : "+idPerson);
    }

    @After("execution(* fr.softeam.gestionimages.service.GestionImagesService.download(..))")
    public void logAfterDownloadCall(JoinPoint joinPoint){
        String idPerson = (String)joinPoint.getArgs()[0];
        LOG.info("Appel service rest Download pour la personne : "+idPerson);
    }
}
