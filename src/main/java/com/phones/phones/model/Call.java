package com.phones.phones.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`calls`")
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer duration;

    @NotNull
    private Float totalPrice;

    // testear
    @JsonIgnore
    private Date creationDate;

    @NotNull
    @JsonBackReference(value = "originLineCall")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_origin_line", nullable = false)
    private Line originLine;

    @NotNull
    @JsonBackReference(value = "destinationLineCall")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_destination_line", nullable = false)
    private Line destinationLine;

    @NotNull
    @JsonBackReference(value = "rateCall")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rate", nullable = false)
    private Rate rate;

    @NotNull
    @JsonBackReference(value = "invoiceCall")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_invoice", nullable = false)
    private Invoice invoice;

}
