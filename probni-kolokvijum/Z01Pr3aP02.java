/**
 * Napisati program koji:
 * a) Iz fajla učitava podatke tipa String u instancu klase ArrayList.
 * b) učitava podatke tipa String iz drugog fajla u drugu strukturu Ar-
 *    rayList, ali tako da se na kraju vrati lista u kojoj nema elemen-
 *    ata koji su već bili u prvoj ArrayList.
 * c) ispisuje rezultujuću listu u novi fajl
 */
 
import java.util.ArrayList;

class Z01Pr3aP02 {
	
	public static ArrayList<String> ucitajListu(String f) {
		
		ArrayList<String> lista = new ArrayList<>();
		
		if (Svetovid.testIn(f)) {
			
			while (Svetovid.in(f).hasMore()) {
				
				String s = Svetovid.in(f).readLine();
				lista.add(s);
			}
			
			Svetovid.in(f).close();
		}
		
		return lista;
	}
	
	
	public static void snimiListu(String f, ArrayList<String> lista) {
		
		for (int i = 0; i < lista.size(); i++)
			Svetovid.out(f).println(lista.get(i));
			
		Svetovid.in(f).close();
	}
	
	
	public static void main(String[] args) {
		
		ArrayList<String> l1 = ucitajListu("arr1.txt");
		ArrayList<String> l2 = ucitajListu("arr2.txt");
		
		l2.removeAll(l1);
		
		snimiListu("arrmod.txt", l2);
	}
}
