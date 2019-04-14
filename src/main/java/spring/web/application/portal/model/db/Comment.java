package spring.web.application.portal.model.db;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    /*@ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE)*/
    //@ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne
    private Post post;

    @Column(length = 15000)
    private String text;
}
