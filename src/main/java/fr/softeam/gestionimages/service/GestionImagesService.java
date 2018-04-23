package fr.softeam.gestionimages.service;

import fr.softeam.gestionimages.exception.GestionImagesException;
import fr.softeam.gestionimages.model.gestionimages.ResultDownloadModel;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class GestionImagesService {

    private DropboxAdapter dropboxAdapter;

    @Autowired
    public GestionImagesService(DropboxAdapter dropboxAdapter){
        this.dropboxAdapter = dropboxAdapter;
    }

    public String upload(String idPerson, MultipartFile file) throws GestionImagesException {
        try{
            if(idPerson == null || idPerson.trim().isEmpty()){
                throw new GestionImagesException("Identifiant de la person vide.");
            }
            if(file == null){
                throw new GestionImagesException("Le fichier n'est pas une image.");
            }
            return dropboxAdapter.uploadFile(idPerson.toLowerCase(),file.getBytes(),FilenameUtils.getExtension(file.getOriginalFilename()));
        }catch(IOException e){
            throw new GestionImagesException(e.getMessage());
        }catch (HttpClientErrorException ex){
            throw new GestionImagesException(ex.getResponseBodyAsString());
        }
    }

    public ResultDownloadModel download(String idPerson) throws GestionImagesException {
        try {
            if(idPerson == null || idPerson.trim().isEmpty()){
                throw new GestionImagesException("Identifiant de la person vide.");
            }
            return dropboxAdapter.downloadFile(idPerson.toLowerCase());
        } catch (IOException e) {
            throw new GestionImagesException(e.getMessage());
        } catch (HttpClientErrorException ex){
            throw new GestionImagesException(ex.getResponseBodyAsString());
        }

    }
}
