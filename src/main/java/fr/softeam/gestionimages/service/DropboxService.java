package fr.softeam.gestionimages.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.softeam.gestionimages.exception.GestionImagesException;
import fr.softeam.gestionimages.model.dropbox.*;
import fr.softeam.gestionimages.model.dropbox.responselistfolder.EntriesItem;
import fr.softeam.gestionimages.model.gestionimages.ResultDownloadModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.Optional;

@Service
public class DropboxService {

    @Autowired
    private RestTemplate restTemplate;

    private final String CONTENT_ROOT_URI = "https://content.dropboxapi.com/2";
    private final String API_ROOT_URI = "https://api.dropboxapi.com/2";
    private final String TOKEN_DROPBOX = "Bearer 8ZRm8hKr7PAAAAAAAAAADW-1oJDavRZIOn8gCTpAYbA-93icaUSc37pXowl2N_XN";
    private final String UPLOAD_URI = "/files/upload";
    private final String DOWNLOAD_URI = "/files/download";
    private final String LIST_FOLDER_URI = "/files/list_folder";

    public String uploadFile(String path, byte[] file,String extension) throws IOException,HttpClientErrorException {

        UploadDropboxModel uploadDropboxModel = new UploadDropboxModel();
        uploadDropboxModel.setPath("/"+path+"/"+path+"."+extension.substring(extension.indexOf("/")+1,extension.length()));
        uploadDropboxModel.setMode("overwrite");
        uploadDropboxModel.setAutorename(false);
        uploadDropboxModel.setMute(true);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set("Authorization",TOKEN_DROPBOX);
        ObjectMapper mapper = new ObjectMapper();
        headers.set("Dropbox-API-Arg",mapper.writeValueAsString(uploadDropboxModel));

        HttpEntity<byte[]> entity = new HttpEntity<>(file, headers);

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(CONTENT_ROOT_URI+UPLOAD_URI,HttpMethod.POST,entity,String.class);
        return responseEntity.toString();
    }

    public ResultDownloadModel downloadFile(String path) throws GestionImagesException, IOException {

        ObjectMapper mapper = new ObjectMapper();

        DownloadDropboxModel downloadDropboxModel = new DownloadDropboxModel();
        downloadDropboxModel.setPath(getOneFile(listFolder(path)));
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",TOKEN_DROPBOX);
        headers.setContentType(MediaType.TEXT_PLAIN);

        headers.set("Dropbox-API-Arg",mapper.writeValueAsString(downloadDropboxModel));
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        HttpEntity<byte[]> responseEntity =
                restTemplate.exchange(CONTENT_ROOT_URI+DOWNLOAD_URI,HttpMethod.POST,entity,byte[].class);

        //Lecture de la reponse
        byte[] body = responseEntity.getBody();
        HttpHeaders headersResponse = responseEntity.getHeaders();
        String json = headersResponse.get("dropbox-api-result").get(0);
        ResponseDownloadDropboxModel responseDownloadDropboxModel = mapper.readValue(json,ResponseDownloadDropboxModel.class);
        return new ResultDownloadModel(responseDownloadDropboxModel.getPathDisplay(),body);
    }

    public ResponseListFolderDropboxModel listFolder(String path) throws HttpClientErrorException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        ListFolderDropboxModel listFolderDropboxModel = new ListFolderDropboxModel();
        listFolderDropboxModel.setPath("/"+path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", TOKEN_DROPBOX);
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(listFolderDropboxModel), headers);
        HttpEntity<String> responseEntity =
                restTemplate.exchange(API_ROOT_URI + LIST_FOLDER_URI, HttpMethod.POST, entity, String.class);
        ResponseListFolderDropboxModel responseListFolderDropboxModel =
                mapper.readValue(responseEntity.getBody(), ResponseListFolderDropboxModel.class);
        return responseListFolderDropboxModel;
    }

    public String getOneFile(ResponseListFolderDropboxModel responseListFolderDropboxModel) throws GestionImagesException {
         Optional<EntriesItem> entriesItem = responseListFolderDropboxModel.getEntries()
                .stream().filter(s -> s.getTag().equals("file")).findFirst();
         if(entriesItem.isPresent()){
             return entriesItem.get().getPathLower();
         }else{
             throw new GestionImagesException("Aucun fichier dans le dossier.");
         }
    }
}
