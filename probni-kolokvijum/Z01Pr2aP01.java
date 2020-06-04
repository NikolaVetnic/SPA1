/**
 * Napisati program koji:
 * a) Iz fajla učitava podatke tipa int u instancu klase LinkedList.
 * b) nalazi poslednju poziciju na kojoj se element nalazi u listi i u-
 *    klanja ga ako se nalazi pre polovine liste, inače briše sve elem-
 * ente iz liste
 * c) ispisuje rezultujuću listu u novi fajl.
 */
 
import java.util.LinkedList;
import java.util.ArrayList;

class Z01Pr2aP01 {
	
	static LinkedList<Integer> ucitajFajl(String f) {
		
		LinkedList<Integer> lista = new LinkedList<>();
		
		if (Svetovid.testIn(f)) {
			
			while (Svetovid.in(f).hasMore()) {
				
				int s = Svetovid.in(f).readInt();
				lista.add(s);
			}
			
			Svetovid.in(f).close();
		}
		
		return lista;
	}
	
	
	static void snimiFajl(String f, LinkedList<Integer> lista) {
		
		for (int i = 0; i < lista.size(); i++)
			Svetovid.out(f).println(lista.get(i));
			
		Svetovid.in(f).close();
	}
	
	
	public static void main(String[] args) {
		
		String f;
		int e;
		
		f = Svetovid.in.readLine("Ucitavanje, fajl: ");
		LinkedList<Integer> lista = ucitajFajl(f);
		System.out.println(lista);
		
		e = Svetovid.in.readInt("Element za pretragu: ");
		
		ArrayList<Integer> arr = new ArrayList<>();
		arr.add(e);
		
		if (lista.lastIndexOf(e) < (lista.size() / 2))
			lista.remove(lista.lastIndexOf(e));
		else
			lista.removeAll(arr);
			
		System.out.println(lista);
		
		f = Svetovid.in.readLine("Snimanje, fajl: ");
		snimiFajl(f, lista);
	}
}
