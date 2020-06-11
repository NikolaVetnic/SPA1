/**
 * Jedan predmet je predstavljen klasom Predmet koja sadrži sledeća po-
 * lja: ime predmeta, ime profesora koji drži predmet, i broj studenata
 * koji slušaju predmet. Klasa takođe mora da defniše konstruktor i to-
 * String() metod. Klasom SpisakPredmeta su predstavljeni predmeti u t-
 * ekućoj školskoj godini kojih može biti maksimalno 50. Pored toga kl-
 * asa defniše sledeće metode:
 * 1. Metodu koja čita podatke o predmetima iz proizvoljnog fajla - ovu
 * 	  funkcionalnost je moguće realizovati i u konstruktoru).
 * 2. Metodu koja dodaje novi predmet u spisak predmeta.
 * 3. Metodu koja ispisuje sve predmete sa datim imenom, koji imaju ma-
 * 	  nje od k evidentiranih studenata, gde su ime i k parametri metod-
 *    a. Ispisati i odgovarajuće poruke ukoliko profesor ne postoji ili
 * 	  nema predmeta koji zadovoljavaju kriterijume ispisa.
 * 4. Metodu koja snima podatke o predmetima u proizvoljan fajl.
 * 
 * Napisati program koji kreira spisak predmeta na osnovu ulaznog fajla
 * (jedna linija fajla sadrži podatke o jednom predmetu i to ime predm-
 * eta, ime profesora, broj studenata na predmetu razdvojeni razmacima)
 * i poziva sve metode defnisane u klasi SpisakPredmeta u gore datom r-
 * edosledu, npr. 
 * 	  Matematika Takaci 35
 * 	  Modeliranje Rakic 12
 * 	  Fizika Mrdja 15
 */

class Z01Pr1P05 {
	
	public static void main(String[] args) {
		
		String f, m, p;
		int b;
		
		f = Svetovid.in.readLine("Ucitavanje, fajl: ");
		SpisakPredmeta spisak = new SpisakPredmeta(f);
		System.out.println(spisak);
		
		m = Svetovid.in.readLine("Unos, ime: ");
		p = Svetovid.in.readLine("Unos, profesor: ");
		b = Svetovid.in.readInt("Unos, broj studenata: ");
		
		spisak.dodajPredmet(m, p, b);
		System.out.println(spisak);
		
		m = Svetovid.in.readLine("Pretraga, ime: ");
		b = Svetovid.in.readInt("Pretraga, minimalno: ");
		spisak.metodZadatka(m, b);
		
		f = Svetovid.in.readLine("Snimanje, fajl: ");
		spisak.snimiFajl(f);
	}
}

class Predmet {
	
	String m, p;
	int b;
	
	
	public Predmet(String m, String p, int b) {
		this.m = m;
		this.p = p;
		this.b = b;
	}
	
	
	public String toString() {
		return m + " " + p + " " + b;
	}
}

class SpisakPredmeta {
	
	static final int MAX_PREDMETA = 50;
	
	
	Predmet[] spisak;
	int br;
	
	
	public SpisakPredmeta(String f) {
		
		this.spisak = new Predmet[MAX_PREDMETA];
		this.br = 0;
		
		if (Svetovid.testIn(f)) {
			
			while (Svetovid.in(f).hasMore()) {
				
				String m = Svetovid.in(f).readToken();
				String p = Svetovid.in(f).readToken();
				int b = Svetovid.in(f).readInt();
				
				dodajPredmet(m, p, b);
			}
			
			if (Svetovid.in(f).hasMore())
				System.out.println("Spisak popunjen pre kraja fajla.");
				
			Svetovid.in(f).close();
		}
	}
	
	
	public void snimiFajl(String f) {
		
		for (int i = 0; i < br; i++)
			Svetovid.out(f).println(spisak[i]);
			
		Svetovid.in(f).close();
	}
	
	
	public boolean dodajPredmet(String m, String p, int b) {
		
		if (br >= MAX_PREDMETA)
			return false;
			
		spisak[br] = new Predmet(m, p, b);
		br++;
		
		return true;
	}
	
	
	public void metodZadatka(String m, int k) {
		
		System.out.print("SPISAK PREDMETA ('" + m + "', minimalno " + k + ") : ");
		
		for (int i = 0; i < br; i++) {
			
			if (spisak[i].m.equals(m) && spisak[i].b < k)
				System.out.print(spisak[i] + " ");
				
			if (spisak[i].p.equals(""))
				System.out.print("(predmet nema profesora) ");
		}
		
		System.out.println();
	}
	
	
	public String toString() {
		
		String output = "SPISAK PREDMETA : ";
		
		for (int i = 0; i < br; i++)
			output += spisak[i] + " ";
			
		return output;
	}
}
