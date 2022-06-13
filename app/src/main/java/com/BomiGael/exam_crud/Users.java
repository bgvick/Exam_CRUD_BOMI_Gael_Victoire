package com.BomiGael.exam_crud;

public class Users {

    String nom,mail,contact,mot_de_passe;

     public Users() {

     }

    public Users(String nom, String mail, String contact, String mot_de_passe) {
        this.nom = nom;
        this.mail = mail;
        this.contact = contact;
        this.mot_de_passe = mot_de_passe;
    }

    public Users(String nom, String mail,String mot_de_passe) {
        this.nom = nom;
        this.mail = mail;
        this.contact = contact;
        this.mot_de_passe = mot_de_passe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }
}
