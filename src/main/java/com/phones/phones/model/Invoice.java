package com.phones.phones.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer numberCalls;

    @NotNull
    private Float costPrice;

    @NotNull
    private Float totalPrice;

    @NotNull
    private Date creationDate;

    @NotNull
    private Date dueDate;

    @JsonManagedReference
    @ManyToOne()
    @JoinColumn(name="id_line", nullable = false)
    private Line line;

    @JsonBackReference
    @OneToMany(mappedBy = "invoice")
    private List<Call> calls;

}