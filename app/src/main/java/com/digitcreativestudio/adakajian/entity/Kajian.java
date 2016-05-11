package com.digitcreativestudio.adakajian.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ADIK on 10/01/2016.
 */
public class Kajian implements Parcelable{
    private String judul_kajian;
    private String lokasi_kajian;
    private String tanggal_kajian;
    private String jam_kajian;
    private String ustad_kajian;
    private String pamflet_kajian;
    private String keterangan_kajian;
    private String kategori_kajian;
    private double latitude;
    private double longitude;

    //==================== GETTER ===================================
    public String getJudul_kajian(){
        return judul_kajian;
    }

    public String getLokasi_kajian(){
        return lokasi_kajian;
    }

    public String getTanggal_kajian(){
        return tanggal_kajian;
    }

    public String getJam_kajian(){
        return jam_kajian;
    }

    public String getUstad_kajian(){
        return ustad_kajian;
    }

    public String getPamflet_kajian(){
        return pamflet_kajian;
    }

    public String getKeterangan_kajian(){
        return keterangan_kajian;
    }

    public String getKategori_kajian(){
        return kategori_kajian;
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    //============================ SETTER ===============================================
    public void setNamaProduk (String nama_produk){
        this.judul_kajian = nama_produk;
    }

    public void setLokasi_kajian(String lokasi_kajian){
        this.lokasi_kajian = lokasi_kajian;
    }

    public void setTanggal_kajian(String tanggal_kajian){
        this.tanggal_kajian = tanggal_kajian;
    }

    public void setJam_kajian(String jam_kajian){
        this.jam_kajian = jam_kajian;
    }

    public void setUstad_kajian(String ustad_kajian){
        this.ustad_kajian = ustad_kajian;
    }

    public void setPamflet_kajian(String pamflet_kajian){
        this.pamflet_kajian = pamflet_kajian;
    }

    public void setKeterangan_kajian(String keterangan_kajian){
        this.keterangan_kajian= keterangan_kajian;
    }

    public void setKategori_kajian(String kategori_kajian){
        this.kategori_kajian= kategori_kajian;
    }

    public void setLatitude(double latitude){
        this.latitude= latitude;
    }

    public void setLongitude(double longitude){
        this.longitude= longitude;
    }


    //========================== PARCEL ==========================================
    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * Storing the Student data to Parcel object
     **/
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(judul_kajian);
        dest.writeString(lokasi_kajian);
        dest.writeString(tanggal_kajian);
        dest.writeString(jam_kajian);
        dest.writeString(ustad_kajian);
        dest.writeString(pamflet_kajian);
        dest.writeString(keterangan_kajian);
        dest.writeString(kategori_kajian);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }

    /**
     * A constructor that initializes the Student object
     **/
    public Kajian(String nama_produk, String merek_produk,
                  String pabrik_produk, String bahan_produk,
                  String status_produk, String pamflet_kajian,
                  String keterangan_kajian, String kategori_kajian,
                  double latitude, double longitude){
        this.judul_kajian = nama_produk;
        this.lokasi_kajian = merek_produk;
        this.tanggal_kajian = pabrik_produk;
        this.jam_kajian = bahan_produk;
        this.ustad_kajian = status_produk;
        this.pamflet_kajian = pamflet_kajian;
        this.keterangan_kajian = keterangan_kajian;
        this.kategori_kajian = kategori_kajian;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Retrieving Student data from Parcel object
     * This constructor is invoked by the method createFromParcel(Parcel source) of
     * the object CREATOR
     **/
    private Kajian(Parcel in){
        this.judul_kajian = in.readString();
        this.lokasi_kajian = in.readString();
        this.tanggal_kajian = in.readString();
        this.jam_kajian = in.readString();
        this.ustad_kajian = in.readString();
        this.pamflet_kajian = in.readString();
        this.keterangan_kajian = in.readString();
        this.kategori_kajian = in.readString();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
    }

    public static final Creator<Kajian> CREATOR = new Creator<Kajian>() {

        @Override
        public Kajian createFromParcel(Parcel source) {
            return new Kajian(source);
        }

        @Override
        public Kajian[] newArray(int size) {
            return new Kajian[size];
        }
    };
}
