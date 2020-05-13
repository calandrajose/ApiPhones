package com.phones.phones.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @NotNull
    @JsonBackReference(value = "lineTypeLine")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_line_type", nullable = false)
    private LineType lineType;

    @NotNull
    @JsonBackReference(value="userLine")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @OneToMany(mappedBy = "line")
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "originLine")
    private List<Call> originCalls;

    @OneToMany(mappedBy = "destinationLine")
    private List<Call> destinationCalls;

}
