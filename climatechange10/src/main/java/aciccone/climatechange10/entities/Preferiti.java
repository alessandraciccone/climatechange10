package aciccone.climatechange10.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "preferiti")
public class Preferiti {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String titolo;
    private String descrizione;
    private String imglink;

    public Preferiti(){};
    public Preferiti(String titolo, String descrizione, String imglink) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        imglink = imglink;
    }

    public long getId() {
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
        imglink = imglink;
    }

    @Override
    public String toString() {
        return "Preferiti{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", imglink='" + imglink + '\'' +
                '}';
    }
}
