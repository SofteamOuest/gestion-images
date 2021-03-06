package fr.softeam.gestionimages.aspect;

import fr.softeam.gestionimages.service.GestionImagesService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Aspect
public class GestionImagesAspect {

    private final GestionImagesService gestionImagesService;

    @Autowired
    public GestionImagesAspect(GestionImagesService gestionImagesService) {
        this.gestionImagesService = gestionImagesService;
    }

    static final Logger LOG = LoggerFactory.getLogger(GestionImagesService.class);

    @AfterReturning("execution(* fr.softeam.gestionimages.service.GestionImagesService.upload(..))")
    public void logAfterUpdateCall(JoinPoint joinPoint){
        String idPerson = (String)joinPoint.getArgs()[0];
        MultipartFile file = (MultipartFile)joinPoint.getArgs()[1];
        LOG.info("Upload pour la personne : " + idPerson
                + " / fichier : " + file.getOriginalFilename()
                + " - taille " + file.getSize() + "octet(s)"
                + " - contentType " + file.getContentType());
    }

    @AfterReturning("execution(* fr.softeam.gestionimages.service.GestionImagesService.download(..))")
    public void logAfterDownloadCall(JoinPoint joinPoint){
        String idPerson = (String)joinPoint.getArgs()[0];
        LOG.info("Download file pour la personne : " + idPerson);
    }

    @AfterThrowing(value = "execution(* fr.softeam.gestionimages.service.GestionImagesService.*(..))",throwing ="e")
    public void logAfterThrowingDowload(JoinPoint thisJoinPoint, Throwable e) {
        LOG.info("Exception - " + e.getMessage());
    }

}
