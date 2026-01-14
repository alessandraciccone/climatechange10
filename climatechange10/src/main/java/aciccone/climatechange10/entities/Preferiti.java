package aciccone.climatechange10.entities;

import aciccone.climatechange10.entities.User;
import jakarta.persistence.*;

@Entity
@Table(name = "preferiti")
public class Preferiti {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "preferiti_seq"
    )
    @SequenceGenerator(
            name = "preferiti_seq",
            sequenceName = "preferiti_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false)
    private String titolo;

    @Column(length = 1000)
    private String descrizione;

    private String imglink;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Preferiti() {
    }

    public Preferiti(Long id, String titolo, String descrizione, String imglink, User user) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.imglink = imglink;
        this.user = user;
    }

    public Long getId() {
        return id;
    }
    public String getTitolo() {
        return titolo;
    }
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    public String getDescrizione() {
        return descrizione;

    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public String getImglink() {
        return imglink;
    }
    public void setImglink(String imglink) {
        this.imglink = imglink;
    }
    public User getUser() {
        return user;}
    public void setUser(User user) {
        this.user = user;
    }
    
}
