package fr.softeam.gestionimages.service;

import fr.softeam.gestionimages.exception.GestionImagesException;
import fr.softeam.gestionimages.model.gestionimages.ResultDownloadModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class GestionImagesService {

    private DropboxService dropboxService;

    @Autowired
    public GestionImagesService(DropboxService dropboxService){
        this.dropboxService = dropboxService;
    }

    public String upload(String idPerson, MultipartFile file) throws IOException, GestionImagesException {
        try{
            if(!file.getContentType().contains("image")){
                throw new GestionImagesException("Le fichier n'est pas une image");
            }
            return dropboxService.uploadFile(idPerson,file.getBytes(),file.getContentType());
        }catch(IOException e){
            throw new GestionImagesException(e.getMessage());
        }catch (HttpClientErrorException ex){
            throw new GestionImagesException(ex.getResponseBodyAsString());
        }
    }

    public ResultDownloadModel download(String path) throws GestionImagesException {
        try {
            return dropboxService.downloadFile(path);
        } catch (IOException e) {
            throw new GestionImagesException(e.getMessage());
        } catch (HttpClientErrorException ex){
            throw new GestionImagesException(ex.getResponseBodyAsString());
        }

    }
}
