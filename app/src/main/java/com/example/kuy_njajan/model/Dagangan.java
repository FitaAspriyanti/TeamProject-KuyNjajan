package com.example.kuy_njajan.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "belisekarang")
public class Dagangan implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idK")
    public int idK;

    public String id;
    public String nama;
    public String jenis;
    public String asal;
    public String harga;
    public String deskripsi;
    public String foto_dagangan;
    public String id_toko;
    public int jumlah = 1;
    public boolean ceklis;
}
