package com.example.projecte2bo;

public class Persona {
    private int id;
    private String nom;
    private String cognom;
    private String dni;
    private Integer codiPostal;
    private String direccio;
    private String dataNaixement;
    private Integer genere;
    private Integer estudis;

    private String pwd;

    public Persona(int id, String nom, String cognom, String dni, Integer codiPostal, String direccio, String dataNaixement, Integer genere, Integer estudis, String pwd) {
        this.id = id;
        this.nom = nom;
        this.cognom = cognom;
        this.dni = dni;
        this.codiPostal = codiPostal;
        this.direccio = direccio;
        this.dataNaixement = dataNaixement;
        this.genere = genere;
        this.estudis = estudis;
        this.pwd = pwd;
    }
    public Persona(){
        this.id = 0;
        this.nom = null;
        this.cognom = null;
        this.dni = null;
        this.codiPostal = null;
        this.direccio = null;
        this.dataNaixement = null;
        this.genere = null;
        this.estudis = null;
        this.pwd = null;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Integer getCodiPostal() {
        return codiPostal;
    }

    public void setCodiPostal(Integer codiPostal) {
        this.codiPostal = codiPostal;
    }

    public String getDireccio() {
        return direccio;
    }

    public void setDireccio(String direccio) {
        this.direccio = direccio;
    }

    public String getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(String dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    public Integer getGenere() {
        return genere;
    }

    public void setGenere(Integer genere) {
        this.genere = genere;
    }

    public Integer getEstudis() {
        return estudis;
    }

    public void setEstudis(Integer estudis) {
        this.estudis = estudis;
    }
}
