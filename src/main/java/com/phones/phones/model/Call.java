package com.phones.phones.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "`call`")
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer duration;

    @NotNull
    private Float totalPrice;

    @NotNull
    private Date creationDate;

    @NotNull
    @JsonManagedReference
    @ManyToOne()
    @JoinColumn(name="id_origin_line", nullable = false)
    private Line originLine;

    @NotNull
    @JsonManagedReference
    @ManyToOne()
    @JoinColumn(name="id_destination_line", nullable = false)
    private Line destinationLine;

    @NotNull
    @JsonManagedReference
    @ManyToOne()
    @JoinColumn(name = "id_rate", nullable = false)
    private Rate rate;

    @NotNull
    @JsonManagedReference
    @ManyToOne()
    @JoinColumn(name="id_invoice", nullable = false)
    private Invoice invoice;

}
