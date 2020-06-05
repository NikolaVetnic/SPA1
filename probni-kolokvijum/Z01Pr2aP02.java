/**
 * Napisati program koji:
 * a) Iz fajla učitava podatke tipa int u instancu klase LinkedList.
 * b) nalazi poslednju poziciju na kojoj se element nalazi u listi i u-
 *    klanja ga ako se nalazi pre polovine liste, inače briše sve elem-
 *    ente iz liste
 * c) ispisuje rezultujuću listu u novi fajl.
 */
 
import java.util.ArrayList;
import java.util.LinkedList;
 
class Z01Pr2aP02 {
	
	static public LinkedList<Integer> ucitajListu(String f) {
		
		LinkedList<Integer> lista = new LinkedList<>();
		
		if (Svetovid.testIn(f)) {
			
			while (Svetovid.in(f).hasMore()) {
				
				int e = Svetovid.in(f).readInt();
				lista.add(e);
			}
			
			Svetovid.in(f).close();
		}
		
		return lista;
	}
	
	
	static public void snimiListu(String f, LinkedList<Integer> lista) {
		
		for (int i = 0; i < lista.size(); i++)
			Svetovid.out(f).println(lista.get(i));
			
		Svetovid.in(f).close();
	}
	
	
	static public void metodZadatka(LinkedList<Integer> lista, int e) {
		
		
		
		ArrayList<Integer> pom = new ArrayList<>();
		pom.add(e);
		
		if (lista.lastIndexOf(e) < lista.size() / 2)
			lista.remove(lista.lastIndexOf(e));
		else
			lista.removeAll(pom);
	}
	
	
	public static void main(String[] args) {
		
		LinkedList<Integer> lista = ucitajListu("brojevi.txt");
		
		metodZadatka(lista, 6);
		
		snimiListu("brojevimod.txt", lista);
	}
}
