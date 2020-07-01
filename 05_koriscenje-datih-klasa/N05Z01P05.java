/**
 * Napisati program koji pravi ArrayList stringova i u njega ubacuje n-
 * eka imena (bar 4). Odštampati listu i štampati je nakon svake nakna-
 * dne promene.
 * 
 * 1) Dodati još neko ime na mesto 2.
 * 2) Promeniti ime na poziciji 4 i ispisati šta je bilo na toj pozici-
 *    ji.
 * 3) Ispisati na ekran elemente na parnim pozicijama.
 * 4) Ispisati elemente koji počinju na slovo “S”.
 * 5) Izbaciti element sa pozicije 3.
 * 6) Izbaciti element koji je jednak unetom.
 * 7) Učitati imena (kao Stringove, svaki u novom redu) iz fajla i uba-
 *    citi u LinkedList<String>. Dodati sva imena iz ArrayList-a u prv-
 *    om zadatku. Ispisati sva imena u novi fajl.
 */

import java.util.ArrayList;
import java.util.LinkedList;

class N05Z01P05 {
	
	static String checkExt(String imeFajla) {
		
		if (imeFajla.substring(imeFajla.length() - 4, imeFajla.length()).equals(".txt"))
			return imeFajla;
		else
			return imeFajla += ".txt";
	}
	
	
	static LinkedList<String> listaIzFajla() {
		
		String imeFajla = Svetovid.in.readLine("Ime fajla: ");
		
		imeFajla = checkExt(imeFajla);
		
		LinkedList<String> lista = new LinkedList<>();
		
		if (Svetovid.testIn(imeFajla)) {
			
			while (Svetovid.in(imeFajla).hasMore()) {
				
				String s = Svetovid.in(imeFajla).readToken();
				lista.add(s);
			}
			Svetovid.in(imeFajla).close();
		} else {
			
			System.out.println("Fajl ne sadrzi podatke - vracena je prazna lista!");
		}
		
		System.out.println();
		
		return lista;
	}
	
	
	static void listaUFajl(LinkedList<String> lista) {
		
		String imeFajla = Svetovid.in.readLine("Ime fajla za cuvanje liste: ");
		imeFajla = checkExt(imeFajla);
		
		for (int i = 0; i < lista.size(); i++)
			Svetovid.out(imeFajla).println(lista.get(i));
		
		System.out.println("Trenutno stanje liste sacuvano u fajl " + imeFajla);
		
		Svetovid.out(imeFajla).close();
		System.out.println();
	}

	
	public static void main(String[] args) {
		
		ArrayList<String> imena = new ArrayList<>();
		
		// dodavanje cetiri imena
		int n = Svetovid.in.readInt("Koliko imena unosimo: ");
		for (int i = 0; i < n; i++) {
			String s = Svetovid.in.readLine("Ime #" + i + ": ");
			imena.add(s);
		}
		System.out.println(imena);
		System.out.println();
		
		// dodavanje imena na mesto 2
		String s = Svetovid.in.readLine("Na mesto 2 dodajemo: ");
		imena.add(1, s);
		System.out.println(imena);
		System.out.println();
		
		// promena elementa na poziciji 4
		s = Svetovid.in.readLine("Element na pozicji 4 menjamo u: ");
		String el4 = imena.set(4, s);
		System.out.println("Stara vrednost elementa na poziciji 4: " + el4);
		System.out.println();
		System.out.println(imena);
		System.out.println();
		
		// ispisivanje elemenata na parnim pozicijama
		for (int i = 0; i < imena.size(); i++)
			if (i % 2 == 0)
				System.out.println("Element na poziciji " + i + ": " + imena.get(i));
		System.out.println();
				
		// ispisivanje elemenata koji pocinju na "V" - radi testiranja
		System.out.println("Elementi koj pocinju na 'V': ");
		for (int i = 0; i < imena.size(); i++)
			if (imena.get(i).charAt(0) == 'V')
				System.out.println(imena.get(i));
		System.out.println();
		
		// izbacivanje elementa sa pozicije 3
		s = imena.remove(3);
		System.out.println("Izbacen element: "  + s);
		System.out.println(imena);
		System.out.println();
		
		// izbacivanje elementa koji je jednak unetom
		s = Svetovid.in.readLine("Unesite element koji se izbacuje: ");
		for (int i = 0; i < imena.size(); i++)
			if (imena.get(i).equals(s))
				imena.remove(i);
		System.out.println(imena);
		System.out.println();
		
		// ucitavanje imena iz fajla
		LinkedList<String> lista = listaIzFajla();
		System.out.println(lista);
		System.out.println();
		
		// dodavanje cele ArrayListe u novu
		lista.addAll(imena);
		System.out.println(lista);
		System.out.println();
		
		// cuvanje LinkedListe u novom fajlu
		listaUFajl(lista);
	}
}
