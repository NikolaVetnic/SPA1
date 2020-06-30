/**
 * Napisati program koji:
 * 1) iz fajla učitava podatke tipa double u instancu klase ArrayList.
 * 2) nudi korisniku da se novi element postavi na neku poziciju. Elem-
 *    ent sa te pozicije se dodaje na početak osim ako je lista duža od
 *    10 elemenata.
 * 3) ispisuje rezultujuću listu u novi fajl.
 */

import java.util.LinkedList;
import java.util.ArrayList;

class Z01aP01 {
	
	static ArrayList<Double> ucitajListu(String f) {
		
		ArrayList<Double> lista = new ArrayList<>();
		
		if (Svetovid.testIn(f)) {
			
			while (Svetovid.in(f).hasMore()) {
				
				double d = Svetovid.in(f).readDouble();
				lista.add(d);
			}
			
			Svetovid.in(f).close();
		}
		
		return lista;
	}
	
	
	static void metodZadatka(ArrayList<Double> lista, int p, double e) {
		
		double d = lista.set(p, e);
		
		if (lista.size() <= 10)
			lista.add(lista.size(), d);
	}
	
	
	static void snimiListu(String f, ArrayList<Double> lista) {
		
		for (int i = 0; i < lista.size(); i++)
			Svetovid.out(f).println(lista.get(i));
			
		Svetovid.out(f).close();
	}
	
	
	public static void main(String[] args) {
		
		ArrayList<Double> lista = ucitajListu("brojevi.txt");
		System.out.println(lista);
		
		int p 		= Svetovid.in.readInt("Pozicija: ");
		double e 	= Svetovid.in.readDouble("Vrednost: ");
		metodZadatka(lista, p, e);
		
		System.out.println(lista);
		
		snimiListu("brojevimod.txt", lista);
	}
}
