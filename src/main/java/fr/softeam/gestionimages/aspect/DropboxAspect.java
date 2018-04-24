package fr.softeam.gestionimages.aspect;

import fr.softeam.gestionimages.service.DropboxAdapter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Aspect
public class DropboxAspect {

    private final DropboxAdapter dropboxAdapter;

    @Autowired
    public DropboxAspect(DropboxAdapter dropboxAdapter) {
        this.dropboxAdapter = dropboxAdapter;
    }

    static final Logger LOG = LoggerFactory.getLogger(DropboxAdapter.class);

    @After("execution(* fr.softeam.gestionimages.service.DropboxAdapter.uploadAndDeleteFile(..))")
    public void logAfterUpdateCall(JoinPoint joinPoint){
        String idPerson = (String)joinPoint.getArgs()[0];
        LOG.info("uploadAndDelete pour la personne : "+idPerson);
    }

    @AfterReturning("execution(* fr.softeam.gestionimages.service.DropboxAdapter.downloadFile(..))")
    public void logAfterDownloadCall(JoinPoint joinPoint){
        String idPerson = (String)joinPoint.getArgs()[0];
        LOG.info("Download file pour la personne : "+idPerson);
    }

    @AfterThrowing(value = "execution(* fr.softeam.gestionimages.service.DropboxAdapter.*(..))",throwing ="e")
    public void logAfterThrowingDowload(JoinPoint thisJoinPoint, Throwable e) {
        LOG.info("Exception - " + e.getMessage());
    }

}
