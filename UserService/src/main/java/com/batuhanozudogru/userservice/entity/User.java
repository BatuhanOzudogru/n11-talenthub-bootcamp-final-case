package com.batuhanozudogru.userservice.entity;

import com.batuhanozudogru.userservice.general.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USR_USER")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    private Long id;

    @Column (name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column (name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "turkish_republic_id_number", length = 11, nullable = false, unique = true, updatable = false)
    private String turkishRepublicIdNumber;

    @Column(name = "username", length = 30, nullable = false, unique = true)
    private String username;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "latitude", nullable = false)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false)
    private BigDecimal longitude;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)

    private List<UserReview> reviewList;

}

