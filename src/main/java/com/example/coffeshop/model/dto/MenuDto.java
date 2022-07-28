package com.example.coffeshop.model.dto;

public class MenuDto {
    private String idMenu;
    private String namaMenu;
    private String namaJenis;
    private Integer stock;
    private Double harga;

    private String name;
    private String url;
    private String type;
    private long size;

    public MenuDto(String namaMenu, String namaJenis, Integer stock, Double harga, String name, String url, String type, long size) {
        this.namaMenu = namaMenu;
        this.namaJenis = namaJenis;
        this.stock = stock;
        this.harga = harga;
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
    }
    public MenuDto(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

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

    public String getNamaJenis() {
        return namaJenis;
    }

    public void setNamaJenis(String namaJenis) {
        this.namaJenis = namaJenis;
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
}
