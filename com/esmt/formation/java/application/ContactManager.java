package com.esmt.formation.java.application;
import com.esmt.formation.java.objets.Console ;
import java.io.*;
import java.util.ArrayList;

import com.esmt.formation.java.objets.Contact;

public class ContactManager {
	
	public static final String CONTACT_FILE_NAME = "contacts.ser" ;
	private static ArrayList <Contact> contacts = new ArrayList <> () ;
	
	public static void main ( String [] args ) {
		
		ContactManager cm = new ContactManager () ;
		cm.run();
	}
	
	public ContactManager () {
		File file = new File ( CONTACT_FILE_NAME ) ;
		// Si le fichier contacts n'existe pas on initialise la liste de contacts ...
		if ( ! file.exists () ) {
			initialize () ;
		} else {
			// S'il existe on d�s�rialise la liste de contact ...
			deserialiseAllContacts () ;
		}
	}
	
	public void addContact () {

		Contact contact = createContact () ;
		contacts.add (contact) ;
		// Sauvegarde la nouvelle liste de contacts dans le fichier. 
		serialiseAllContacts();
	}
	
	public void serialiseAllContacts () {
		try (ObjectOutputStream ois = new ObjectOutputStream ( new FileOutputStream ( CONTACT_FILE_NAME ) ) ) {
			ois.writeObject(contacts) ;
		} catch (IOException e) {
			System.out.println ("An error occured during deserialization processing : " + e.getMessage() );
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deserialiseAllContacts () {
		try (ObjectInputStream ois = new ObjectInputStream ( new FileInputStream ( CONTACT_FILE_NAME ) ) ) {
			contacts = (ArrayList<Contact>) ois.readObject() ;
		} catch (IOException | ClassNotFoundException e) {
			System.out.println ("An error occured during deserialization processing : " + e.getMessage() );
		}
	}
	
	public void updateContact (Contact contactToBeUpdated) {
		
		try {
			/*
			 * Il faut au pr�alable red�finir la m�thode equals de Object dans Contact
			 * pour permettre � la JVM de savoir quand est-ce que deux contacts sont �gaux ...
			 */
			if ( contacts.contains(contactToBeUpdated) ) {
				Contact contact = (Contact) contacts.get(contacts.indexOf(contactToBeUpdated));
				System.out.println ( contactToBeUpdated + " trouv�.\nQuelle information voulez-vous mettre � jour ? " );
				menu () ;
				switch ( Console.lireInt() ) {
					case 1 : 
						System.out.print("Nouveau nom : ");
						String nom = Console.lireString() ;
						contact.setNom ( nom );
						break ;
					case 2 : 
						System.out.print("Nouveau pr�nom : ");
						contact.setPrenom ( Console.lireString() );
						break ;
					case 3 : 
						System.out.print("Nouveau num�ro de t�l�phone : ");
						contact.setNumeroDeTelephone( Console.lireString() );
						break ;	
					case 4 : 
						System.out.print("Nouvel �mail : ");
						contact.setEmail( Console.lireString() );
						break ;
					default :
						throw new IllegalArgumentException ( "Le choix effectu� n'est pas valide !\nInterruption du traitement !" ) ;
				}
			}				
			else {
				System.out.println ( contactToBeUpdated + " non trouv� !\n" );
			}
		} catch ( IllegalArgumentException iae ) {
			System.out.println ( iae.getMessage() ) ;
		}
	}
	
	public Contact createContact () {
		
		Contact contact = new Contact ();
		
		System.out.print("Entrez les informations du contact \nNom : ");
		contact.setNom ( Console.lireString() );
				
		System.out.print ("Pr�nom : ");
		contact.setPrenom ( Console.lireString() );
		
		System.out.print ("Num�ro de t�l�phone : ");
		contact.setNumeroDeTelephone ( Console.lireString() );
		
		System.out.print ("Email : ");
		contact.setEmail ( Console.lireString() );
		
		return contact ;
		
	}
	
	public void initialize ( ) {
		// Initialise la liste des  ...
		contacts.add ( new Contact ( "Ngom", "Abdoul Kader", "771457896", "aka@ngkader.com") ) ;		
	}
	
	public void affiche ( ) {
		for ( Contact contact : contacts ) {
			System.out.println ( contact );
		}
	}

	public void run () {	

		addContact ();
		affiche ();

		// Modifier un contact dans le fichier en changeant son num�ro de t�l�phone par exemple ...
		System.out.println ( "-- Update --" );
		Contact contact = createContact () ;
		updateContact ( contact );

		affiche();
	}
	
	private void menu () {
		System.out.println ( "1 > Nom ");
		System.out.println ( "2 > Pr�nom ");
		System.out.println ( "3 > Num�ro de t�l�phone ");
		System.out.println ( "4 > Email ");
	}
}