package edu.team.carshopbackend.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class PhotoClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public UploadPhotoResponse uploadPhoto(Long carId, MultipartFile file) throws IOException {
        String url = "http://localhost:8000/cars/" + carId + "/photos";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new MultipartInputStreamFileResource(file));

        HttpEntity<MultiValueMap<String, Object>> request =
                new HttpEntity<>(body, headers);

        ResponseEntity<UploadPhotoResponse> response =
                restTemplate.postForEntity(url, request, UploadPhotoResponse.class);

        return response.getBody();
    }
}

