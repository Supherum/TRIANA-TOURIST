package com.proyecto.trianatourist.poi.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.proyecto.trianatourist.poi.Views.PointOfInteresViews;
import com.proyecto.trianatourist.validator.uniqueurl.anotation.UniqueUrl;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;
@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
@UniqueUrl.List({
        @UniqueUrl(urlPrimary = "coverPhoto",urlSecondary = "photo2", message ="La url debe ser única"),
        @UniqueUrl(urlPrimary = "photo2",urlSecondary = "photo3", message ="La url debe ser única"),
        @UniqueUrl(urlPrimary = "coverPhoto",urlSecondary = "photo3", message ="La url debe ser única")
})
public class PointOfInteresGenericDto {

    @JsonView(value = {PointOfInteresViews.Detail.class})
    private UUID id;
    @NotBlank(message = "{name.blank}")
    @JsonView(value = {PointOfInteresViews.Normal.class})
    private String name;
    @Pattern(regexp = "^(\\-?([0-8]?[0-9](\\.\\d+)?|90(.[0]+)?)\\s?[,]\\s?)+(\\-?([1]?[0-7]?[0-9](\\.\\d+)?|180((.[0]+)?)))$", message = "{ubicacion}")
    @JsonView(value = {PointOfInteresViews.Normal.class})
    private String location;
    @JsonView(value = {PointOfInteresViews.Detail.class})
    private String description;
    @JsonView(value = {PointOfInteresViews.Detail.class})
    private LocalDate date;
    @JsonView(value = {PointOfInteresViews.Normal.class})
    @NotBlank(message = "{photo.blank}")
    @URL(message = "{url.format}")
    private String coverPhoto;
    @URL(message = "{url.format}")
    @JsonView(value = {PointOfInteresViews.Detail.class})
    private String photo2,photo3;

}
