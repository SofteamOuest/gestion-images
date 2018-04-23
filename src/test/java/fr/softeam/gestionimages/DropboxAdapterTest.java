package fr.softeam.gestionimages;


import fr.softeam.gestionimages.service.DropboxAdapter;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DropboxAdapterTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    DropboxAdapter dropboxAdapter;


}
