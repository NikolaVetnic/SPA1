/**
 * Napisati program koji:
 * a) Iz fajla učitava podatke tipa String u instancu klase ArrayList.
 * b) učitava podatke tipa String iz drugog fajla u drugu strukturu Ar-
 *    rayList, ali tako da se na kraju vrati lista u kojoj nema elemen-
 *    ata koji su već bili u prvoj ArrayList.
 * c) ispisuje rezultujuću listu u novi fajl
 */
 
import java.util.ArrayList;
 
class Z01Pr3aP01 {
	
	static ArrayList<String> ucitajFajl(String f) {
		
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
	
	
	static void snimiFajl(String f, ArrayList<String> lista) {
		
		for (int i = 0; i < lista.size(); i++)
			Svetovid.out(f).println(lista.get(i));
			
		Svetovid.in(f).close();
	}
	
	
	public static void main(String[] args) {
		
		String f;
		
		ArrayList<String> l1 = new ArrayList<>();
		ArrayList<String> l2 = new ArrayList<>();
		
		f = Svetovid.in.readLine("Ucitavanje, prvi fajl: ");
		l1 = ucitajFajl(f);
		System.out.println(l1);
		
		f = Svetovid.in.readLine("Ucitavanje, drugi fajl: ");
		l2 = ucitajFajl(f);
		l2.removeAll(l1);
		System.out.println(l2);
		
		f = Svetovid.in.readLine("Snimanje, fajl: ");
		snimiFajl(f, l2);
	}
}
