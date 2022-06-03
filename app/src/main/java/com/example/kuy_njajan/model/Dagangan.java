package com.example.kuy_njajan.model;

import java.io.Serializable;

//@Entity(tableName = "troli")
public class Dagangan implements Serializable {

//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "idDg")
//    public int idDg;

    public String id_barang;
    public String nama;
    public String jenis;
    public String asal;
    public String harga;
    public String deskripsi;
    public String foto_dagangan;
    public String id_toko;

}
