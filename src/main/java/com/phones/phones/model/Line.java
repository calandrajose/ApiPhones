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
@Table(name = "line")
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String number;

    @NotNull
    private Date creationDate;

    @NotNull
    private boolean isActive;

    @JsonManagedReference
    @ManyToOne()
    @JoinColumn(name="id_line_type", nullable = false)
    private LineType lineType;

    @JsonManagedReference
    @ManyToOne()
    @JoinColumn(name="id_user", nullable = false)
    private User userLine;

    @JsonBackReference
    @OneToMany(mappedBy = "line")
    private List<Invoice> invoices;

    @JsonBackReference
    @OneToMany(mappedBy = "originLine")
    private List<Call> originCalls;

    @JsonBackReference
    @OneToMany(mappedBy = "destinationLine")
    private List<Call> destinationCalls;

}
