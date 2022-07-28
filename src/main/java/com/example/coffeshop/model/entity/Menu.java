package com.example.coffeshop.model.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_menu")
public class Menu {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idMenu;
    private String namaMenu;
    private String idJenis;
    private Integer stock;
    private Double harga;
    private String namaFile;
    private String type;

    @Lob
    private byte[] data;

    public Menu() {
    }

    public Menu(String fileName, String contentType, byte[] bytes, Menu menu) {
    }

    public Menu(String namaFile, String type, byte[] data) {
        this.namaFile = namaFile;
        this.type = type;
        this.data = data;
    }


    public String getNamaFile() {
        return namaFile;
    }

    public void setNamaFile(String namaFile) {
        this.namaFile = namaFile;
    }

    public String getType() {
        return type;
    }

    public void setType(String file) {
        this.type = type;
    }

    public byte[] getData() { return data; }

    public void setData(byte[] data) { this.data = data; }

    public String getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(String idMenu) {
        this.idMenu = idMenu;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public String getIdJenis() {
        return idJenis;
    }

    public void setIdJenis(String idJenis) {
        this.idJenis = idJenis;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    @OneToMany
    @JoinColumn(name = "idJenis", insertable = false, updatable = false)
    private List<Jenis> jenis;
}
