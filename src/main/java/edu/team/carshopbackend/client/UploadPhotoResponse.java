package edu.team.carshopbackend.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadPhotoResponse {
    private Long id;
    private String url;
}
