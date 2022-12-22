package com.esmt.formation.java.objets ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
	public static String lireString () {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String ligneLue = null ;
		try {
			ligneLue = in.readLine() ;
		} catch (IOException ioe) {}
		return ligneLue ;
	}
	
	public static int lireInt () {
		int valeur = 0 ;
		try {
			String ligneLue = lireString() ;
			valeur = Integer.parseInt(ligneLue) ;
		} catch ( NumberFormatException nfe ) { System.out.println ("Valeur incorrecte !");}
		return valeur ;
	}
}
