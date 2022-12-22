package com.esmt.formation.java.objets;

import java.io.Serializable;

public class Contact implements Serializable, Comparable<Contact> {
	
	private static final long serialVersionUID = 1L;
	
	private String nom ;
	private String prenom ;
	private String numeroDeTelephone ;
	private String email ; 

	public Contact (String nom, String prenom, String numeroDeTelephone, String email) {
		this.nom = nom;
		this.prenom = prenom;
		this.numeroDeTelephone = numeroDeTelephone;
		this.email = email;
	}
	
	public Contact() {}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNumeroDeTelephone() {
		return numeroDeTelephone;
	}
	public void setNumeroDeTelephone(String numeroDeTelephone) {
		this.numeroDeTelephone = numeroDeTelephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Contact [nom=" + nom + ", prenom=" + prenom + ", numeroDeTelephone=" + numeroDeTelephone + ", email="
				+ email + "]";
	}

	@Override
	public int compareTo (Contact o) {
		if ( nom.equals (o.nom) && 
				prenom.equals(o.prenom) && 
				numeroDeTelephone.equals(o.numeroDeTelephone) &&
				email.equals(o.email)) {
			return 1 ;
		}	
		return 0;
	}
	
	@Override
	public boolean equals (Object obj) {
		if (obj instanceof Contact) {
			Contact localObj = (Contact)obj;
			return ( this.compareTo ( localObj ) == 1 ) ? true : false ; 
		}	
		return false;
	}
}