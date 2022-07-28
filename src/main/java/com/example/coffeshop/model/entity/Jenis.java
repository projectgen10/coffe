package com.example.coffeshop.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_jenis")
public class Jenis {
    @Id
    private String idJenis;
    private String namaJenis;

    public String getIdJenis() { return idJenis; }

    public void setIdJenis(String idJenis) { this.idJenis = idJenis; }

    public String getNamaJenis() {
        return namaJenis;
    }

    public void setNamaJenis(String namaJenis) {
        this.namaJenis = namaJenis;
    }

    @ManyToOne
    @JoinColumn(name = "idMenu")
    private Menu menu;
}
