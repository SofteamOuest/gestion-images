package fr.softeam.gestionimages.controller;

import fr.softeam.gestionimages.exception.GestionImagesException;
import fr.softeam.gestionimages.model.gestionimages.ResultDownloadModel;
import fr.softeam.gestionimages.service.GestionImagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class GestionImagesRessource {

    static final Logger LOG = LoggerFactory.getLogger(GestionImagesService.class);

    private GestionImagesService gestionImagesService;

    @Autowired
    public GestionImagesRessource(GestionImagesService gestionImagesService){
        this.gestionImagesService = gestionImagesService;
    }

    @RequestMapping(value="/photos/{idPerson}",method = RequestMethod.POST)
    public ResponseEntity<?> uploadPhoto(@PathVariable(value = "idPerson") String idPerson,
                                         @RequestParam("file") MultipartFile file) throws GestionImagesException {
        return new ResponseEntity(gestionImagesService.upload(idPerson,file), new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value="/photos/{idPerson}",method = RequestMethod.GET)
    public ResponseEntity<?> downloadPhoto(@PathVariable(value = "idPerson") String idPerson) throws GestionImagesException {
        ResultDownloadModel resultDownloadModel = gestionImagesService.download(idPerson);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Filename",resultDownloadModel.getFilename());
        return new ResponseEntity(resultDownloadModel.getFileData(), headers, HttpStatus.OK);
    }

    @ExceptionHandler(GestionImagesException.class)
    public ResponseEntity handleError(GestionImagesException exc){
        return new ResponseEntity(exc.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
