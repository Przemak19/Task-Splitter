package pl.crud.tasksplitter.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "company_membership")
public class CompanyMembership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private Boolean accepted;
}
