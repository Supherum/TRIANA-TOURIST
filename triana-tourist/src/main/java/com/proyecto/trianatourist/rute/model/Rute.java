package com.proyecto.trianatourist.rute.model;

import com.proyecto.trianatourist.poi.model.PointOfInteres;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder

@NamedEntityGraph(
        name = "graph-rute-poi",
        attributeNodes = @NamedAttributeNode(value = "listPointOfInterest")
)

public class Rute {

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

    @Builder.Default
    @ManyToMany (mappedBy = "listRute", fetch = FetchType.LAZY)
    private List<PointOfInteres> listPointOfInterest = new ArrayList();
}
