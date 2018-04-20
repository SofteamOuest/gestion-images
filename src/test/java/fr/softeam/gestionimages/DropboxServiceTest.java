package fr.softeam.gestionimages;

import fr.softeam.gestionimages.exception.GestionImagesException;
import fr.softeam.gestionimages.service.DropboxService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DropboxServiceTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    DropboxService dropboxService;

    @Test
    public void uploadFileTest() throws IOException {
        Path path = Paths.get("C:\\Users\\softeam2\\Desktop\\test.txt");
        byte[] data = Files.readAllBytes(path);
        ResponseEntity<String> myEntity = new ResponseEntity<>(HttpStatus.ACCEPTED);
        Mockito.when(restTemplate.exchange(
                Matchers.eq("/objects/get-objectA"),
                Matchers.eq(HttpMethod.POST),
                Matchers.<HttpEntity<String>>any(),
                Matchers.<ParameterizedTypeReference<String>>any())).thenReturn(myEntity);
        String res = dropboxService.uploadFile("/boceno_paul-jakez_19912610",data,".png");
        Assert.assertEquals("test",res);
    }

    @Test
    public void downloadFileTest() throws GestionImagesException, IOException {
        System.out.println(dropboxService.downloadFile("/boceno_paul-jakez_19911026/test.png"));
    }

    @Test
    public void getOneFileTest() throws IOException, GestionImagesException {
        Assert.assertEquals("/boceno_paul-jakez_19911026/boceno_paul-jakez_19911026.png",
                dropboxService.getOneFile(dropboxService.listFolder("/boceno_paul-jakez_19911026")));
    }
}
