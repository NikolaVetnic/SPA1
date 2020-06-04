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

class N05Z01P03 {
	
	public static LinkedList<String> ucitajListu(String f) {
		
		LinkedList<String> pLista = new LinkedList<>();
		
		if (Svetovid.testIn(f)) {
			
			while (Svetovid.in(f).hasMore())
				pLista.add(Svetovid.in(f).readLine());
				
			Svetovid.in(f).close();
		}
		
		return pLista;
	}
	
	
	public static void snimiListu(String f, LinkedList<String> pLista) {
		
		for (int i = 0; i < pLista.size(); i++)
			Svetovid.out(f).println(pLista.get(i));
			
		Svetovid.in(f).close();
	}
	
	
	public static void main(String[] args) {
		
		String f, s, t;
		int n;
		
		ArrayList<String> lista = new ArrayList<>();
		
		n = Svetovid.in.readInt("Broj elemenata: ");
		
		for (int i = 0; i < n; i++) {
			
			s = Svetovid.in.readLine("Element #" + i + ": ");
			lista.add(s);
		}
		
		System.out.println(lista);
		
		s = Svetovid.in.readLine("Ubaci na mesto #2: ");
		lista.add(1, s);
		System.out.println(lista);
		
		s = Svetovid.in.readLine("Promeniti poziciju #4: ");
		t = lista.set(4, s);
		System.out.println("Stara vrednost na poziciji #4: " + t);
		System.out.println(lista);
		
		for (int i = 0; i < lista.size(); i += 2)
			System.out.print(lista.get(i) + " ");
		System.out.println();
		
		for (int i = 0; i < lista.size(); i++)
			if (lista.get(i).charAt(0) == 'V')
				System.out.print(lista.get(i) + " ");
		System.out.println();
		
		lista.remove(3);
		System.out.println(lista);
		
		s = Svetovid.in.readLine("Izbaci element: ");
		
		for (int i = 0; i < lista.size(); i++)
			if (lista.get(i).equals(s))
				lista.remove(i);
		System.out.println(lista);
		
		LinkedList<String> pLista = ucitajListu("imena.txt");
		pLista.addAll(lista);
		snimiListu("imenamod.txt", pLista);
	}
}
