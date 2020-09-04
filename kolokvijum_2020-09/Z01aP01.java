/**
 * Napisati program koji:
 * 1) iz fajla učitava podatke tipa int u instancu klase LinkedList;
 * 2) nalazi poslednju poziciju na kojoj se element nalazi u listi i u-
 *    klanja ga ako se nalazi pre polovine liste, inače briše sve elem-
 *    ente iz liste;
 * 3) ispisuje rezultujuću listu u novi fajl.
 */ 

import java.util.ArrayList;
import java.util.LinkedList;

class Z01aP01 {
	
	public static void main(String[] args) {
		
		String imeFajla = Svetovid.in.readLine("Ime fajla: ");
		imeFajla = checkExt(imeFajla);
		
		LinkedList<Integer> lista = listaIzFajla(imeFajla);
		
		System.out.println(lista);
		
		int br = Svetovid.in.readInt("Unesite vrednost: ");
		metodZadatka(lista, br);
		
		System.out.println(lista);
		
		imeFajla = Svetovid.in.readLine("Ime fajla: ");
		imeFajla = checkExt(imeFajla);
		
		listaUFajl(lista, imeFajla);
	}
	
	
	static void metodZadatka(LinkedList<Integer> lista, int br) {
		
		int i = 0;
		
		while (lista.get(i) != br)
			i++;
			
		if (i < lista.size() / 2)
			lista.remove(i);
		else
			lista.clear();
	}
	
	
	static String checkExt(String imeFajla) {
		
		if (imeFajla.substring(imeFajla.length() - 4, imeFajla.length()).equals(".txt"))
			return imeFajla;
		else
			return imeFajla += ".txt";
	}
	
	
	static LinkedList<Integer> listaIzFajla(String imeFajla) {
		
		LinkedList<Integer> lista = new LinkedList<>();
		
		if (Svetovid.testIn(imeFajla)) {
			
			while (Svetovid.in(imeFajla).hasMore()) {
				
				int br = Svetovid.in(imeFajla).readInt();
				lista.add(br);
			}
			Svetovid.in(imeFajla).close();
		} else {
			
			System.out.println("Fajl ne sadrzi podatke - vracena je prazna lista!");
		}
		
		System.out.println();
		
		return lista;
	}
	
	
	static void listaUFajl(LinkedList<Integer> lista, String imeFajla) {
		
		for (int i = 0; i < lista.size(); i++)
			Svetovid.out(imeFajla).println(lista.get(i));
		
		System.out.println("Trenutno stanje liste sacuvano u fajl " + imeFajla);
		
		Svetovid.out(imeFajla).close();
		System.out.println();
	}
}
