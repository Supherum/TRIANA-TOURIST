package com.proyecto.trianatourist.category.model;

import com.proyecto.trianatourist.poi.model.PointOfInteres;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder

@NamedEntityGraph(
        name = "graph-category-poi",
        attributeNodes = @NamedAttributeNode(value = "listPointOfInterest")
        )

public class Category implements Serializable {

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

    @ManyToMany (mappedBy = "listCategory", fetch = FetchType.LAZY)
    @Builder.Default
    private List<PointOfInteres> listPointOfInterest =new ArrayList();
}
