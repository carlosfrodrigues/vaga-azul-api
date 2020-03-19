package com.carlos.vagazul.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vacancy")
public class Vacancy {

    private long id;
    private double latitude;
    private double longitude;
 
    public Vacancy() {
  
    }
 
    public Vacancy(double latitude, double longitude) {
         this.latitude = latitude;
         this.longitude = longitude;
    }
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
 
    @Column(name = "latitude", nullable = false)
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
 
    @Column(name = "longitude", nullable = false)
    public double getLongitude() {
        return latitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Vacancy in [latitude=" + latitude + ", longitude=" + longitude + "]";
    }
 
}