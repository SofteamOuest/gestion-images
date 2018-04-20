package fr.softeam.gestionimages.controller;

import fr.softeam.gestionimages.exception.GestionImagesException;
import fr.softeam.gestionimages.model.gestionimages.ResultDownloadModel;
import fr.softeam.gestionimages.service.GestionImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
public class GestionImagesController {

    private GestionImagesService gestionImagesService;

    @Autowired
    public GestionImagesController(GestionImagesService gestionImagesService){
        this.gestionImagesService = gestionImagesService;
    }

    @RequestMapping(value="/photos/{idPerson}",method = RequestMethod.POST)
    public ResponseEntity<?> uploadPhoto(@PathVariable(value = "idPerson") String idPerson,
                                         @RequestParam("file") MultipartFile file) throws GestionImagesException {
        try{
            return new ResponseEntity(gestionImagesService.upload(idPerson,file), new HttpHeaders(), HttpStatus.OK);
        }catch (IOException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value="/photos/{idPerson}",method = RequestMethod.GET)
    public ResponseEntity<?> downloadPhoto(@PathVariable(value = "idPerson") String idPerson) throws GestionImagesException {
        ResultDownloadModel resultDownloadModel = gestionImagesService.download(idPerson);
        return new ResponseEntity(resultDownloadModel.getFileData(), new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler(GestionImagesException.class)
    public ResponseEntity handleError(GestionImagesException exc){
        return new ResponseEntity(exc.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
