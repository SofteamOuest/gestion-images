package fr.softeam.gestionimages.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.softeam.gestionimages.enums.GIE;
import fr.softeam.gestionimages.exception.GestionImagesException;
import fr.softeam.gestionimages.model.dropbox.*;
import fr.softeam.gestionimages.model.dropbox.responselistfolder.EntriesItem;
import fr.softeam.gestionimages.model.gestionimages.ResultDownloadModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Optional;

@Service
public class DropboxAdapter {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public DropboxAdapter(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    static final Logger LOG = LoggerFactory.getLogger(DropboxAdapter.class);

    @Value("${CONTENT_ROOT_URI}")
    private String contentRootUri;
    @Value("${API_ROOT_URI}")
    private String apiRootUri;
    @Value("${TOKEN_DROPBOX}")
    private String tokenDropbox;
    @Value("${UPLOAD_URI}")
    private String uploadUri;
    @Value("${DOWNLOAD_URI}")
    private String downloadUri;
    @Value("${LIST_FOLDER_URI}")
    private String listFolderUri;
    @Value("${DELETE_URI}")
    private String deleteUri;

    public String uploadAndDeleteFile(String idPerson, byte[] file,String extension) throws IOException, HttpClientErrorException, GestionImagesException {
        ObjectMapper mapper = new ObjectMapper();
        String path = "/"+idPerson+"/"+idPerson+"."+extension;

        //Controle si un fichier existe deja dans le dossier avec une autre extension
        String pathFileDb = getOneFile(listFolder(idPerson));
        if(pathFileDb != null && !path.equals(pathFileDb)){
            try {
                deleteFile(pathFileDb);
            }catch (Exception e){
                throw new GestionImagesException(e.getMessage());
            }
        }
        return uploadFile(path,file);
    }

    private String uploadFile(String path, byte[] file) throws IOException, HttpClientErrorException {
        LOG.info("Appel POST sur "+ contentRootUri + uploadUri);
        ObjectMapper mapper = new ObjectMapper();

        UploadDropboxModel uploadDropboxModel = new UploadDropboxModel();
        uploadDropboxModel.initDropboxModel(path);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set("Authorization", "Bearer "+ tokenDropbox);

        headers.set("Dropbox-API-Arg",mapper.writeValueAsString(uploadDropboxModel));

        HttpEntity<byte[]> entity = new HttpEntity<>(file, headers);
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(contentRootUri + uploadUri,HttpMethod.POST,entity,String.class);
        return "Fichier déposé sur dropbox.";
    }

    ResultDownloadModel downloadFile(String idPerson) throws GestionImagesException, IOException {
        LOG.info("Appel POST sur "+ contentRootUri + downloadUri);
        ObjectMapper mapper = new ObjectMapper();
        String path = getOneFile(listFolder(idPerson));
        if(path == null){
            throw new GestionImagesException(GIE.CODE_003,idPerson);
        }
        DownloadDropboxModel downloadDropboxModel = new DownloadDropboxModel();
        downloadDropboxModel.setPath(path);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+ tokenDropbox);
        headers.setContentType(MediaType.TEXT_PLAIN);

        headers.set("Dropbox-API-Arg",mapper.writeValueAsString(downloadDropboxModel));
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        HttpEntity<byte[]> responseEntity =
                restTemplate.exchange(contentRootUri + downloadUri,HttpMethod.POST,entity,byte[].class);

        //Lecture de la reponse
        byte[] body = responseEntity.getBody();
        HttpHeaders headersResponse = responseEntity.getHeaders();
        String json = headersResponse.get("dropbox-api-result").get(0);
        ResponseDownloadDropboxModel responseDownloadDropboxModel = mapper.readValue(json,ResponseDownloadDropboxModel.class);
        return new ResultDownloadModel(responseDownloadDropboxModel.getName(),body);
    }

    private ResponseListFolderDropboxModel listFolder(String idPerson) throws HttpClientErrorException, IOException {
        LOG.info("Appel POST sur "+ apiRootUri + listFolderUri);
        ObjectMapper mapper = new ObjectMapper();
        ListFolderDropboxModel listFolderDropboxModel = new ListFolderDropboxModel();
        listFolderDropboxModel.setPath("/"+idPerson);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ tokenDropbox);
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(listFolderDropboxModel), headers);
        HttpEntity<String> responseEntity =
                restTemplate.exchange(apiRootUri + listFolderUri, HttpMethod.POST, entity, String.class);
        return mapper.readValue(responseEntity.getBody(), ResponseListFolderDropboxModel.class);
    }

    private String getOneFile(ResponseListFolderDropboxModel responseListFolderDropboxModel) {
         Optional<EntriesItem> entriesItem = responseListFolderDropboxModel.getEntries()
                .stream().filter(s -> s.getTag().equals("file")).findFirst();
        return entriesItem.map(EntriesItem::getPathLower).orElse(null);
    }

    private void deleteFile(String path) throws HttpClientErrorException, JsonProcessingException {
        LOG.info("Appel POST sur "+ apiRootUri + deleteUri);
        ObjectMapper mapper = new ObjectMapper();
        DeleteDropboxModel deleteDropboxModel = new DeleteDropboxModel(path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ tokenDropbox);
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(deleteDropboxModel), headers);
        HttpEntity<String> responseEntity =
                restTemplate.exchange(apiRootUri + deleteUri, HttpMethod.POST, entity, String.class);
    }
}
