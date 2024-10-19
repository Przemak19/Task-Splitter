package pl.crud.tasksplitter.entities;

import jakarta.persistence.*;
import lombok.Data;
import pl.crud.tasksplitter.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "task")
public class CompanyTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;
    @Column(name = "task_status")
    private TaskStatus taskStatus;
    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "companyTask", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> review;

    @Column(name = "created_at")
    private final LocalDateTime createdAt = LocalDateTime.now();
}