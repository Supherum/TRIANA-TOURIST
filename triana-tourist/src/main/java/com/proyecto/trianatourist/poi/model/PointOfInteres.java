package com.proyecto.trianatourist.poi.model;

import com.proyecto.trianatourist.category.model.Category;
import com.proyecto.trianatourist.rute.model.Rute;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder

@NamedEntityGraph(
        name = "graph-poi-category",
        attributeNodes = {
                @NamedAttributeNode(value = "listCategory")
        })


public class PointOfInteres implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    private UUID id;

    private String name;
    private String location;
    private String description;
    private LocalDate date;
    private String coverPhoto;
    private String photo2,photo3;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Category> listCategory =new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @Builder.Default
    private List<Rute> listRute =new ArrayList<>();


    // HELPERS Category
    public void addCategoryToPoi(Category c){
        this.listCategory.add(c);
        c.getListPointOfInterest().add(this);
    }

    public void removeCategoryToPoi(Category c){
        this.listCategory.remove(c);
        c.getListPointOfInterest().remove(this);
    }

    // HELPERS Rute
    public void addRuteToPoi(Rute r){
        this.listRute.add(r);
        r.getListPointOfInterest().add(this);
    }
    public void removeRuteToPoi(Rute r){
        this.listRute.remove(r);
        r.getListPointOfInterest().remove(this);
    }
}
