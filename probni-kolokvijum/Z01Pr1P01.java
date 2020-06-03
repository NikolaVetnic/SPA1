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

class Z01Pr1P01 {
	
	public static void main(String[] args) {
		
		String imeFajla, i, p;
		int b;
		
		imeFajla = Svetovid.in.readLine("Ime fajla za ucitavanje: ");
		
		SpisakPredmeta spisak = new SpisakPredmeta(imeFajla);
		System.out.println(spisak);
		
		i = Svetovid.in.readLine("Unos, predmet: ");
		p = Svetovid.in.readLine("Unos, profesor: ");
		b = Svetovid.in.readInt("Unos, broj studenata: ");
		spisak.dodajPredmet(i, p, b);
		
		System.out.println(spisak);
		
		i = Svetovid.in.readLine("Pretraga, predmet: ");
		b = Svetovid.in.readInt("Pretraga, minimalan broj studenata: ");
		spisak.stampajPredmete(i, b);
		
		imeFajla = Svetovid.in.readLine("Ime fajla za snimanje: ");
		spisak.snimiFajl(imeFajla);
	}
}

class Predmet {
	
	String predmet, profesor;
	int brojSt;
	
	
	public Predmet(String predmet, String profesor, int brojSt) {
		this.predmet = predmet;
		this.profesor = profesor;
		this.brojSt = brojSt;
	}
	
	
	public String toString() {
		return predmet + " " + profesor + " " + brojSt;
	}
}

class SpisakPredmeta {
	
	static final int MAX_PREDMETA = 50;
	
	
	Predmet[] spisak;
	int brojPredmeta;
	
	
	public SpisakPredmeta(String imeFajla) {
		
		this.spisak = new Predmet[MAX_PREDMETA];
		this.brojPredmeta = 0;
		
		if (Svetovid.testIn(imeFajla)) {
			
			while (Svetovid.in(imeFajla).hasMore()) {
				
				String i = Svetovid.in(imeFajla).readToken();
				String p = Svetovid.in(imeFajla).readToken();
				int brojSt = Svetovid.in(imeFajla).readInt();
				
				dodajPredmet(i, p, brojSt);
			}
			
			if (Svetovid.in(imeFajla).hasMore())
				System.out.println("Spisak je popunjen.");
			
			Svetovid.in(imeFajla).close();
		}
	}
	
	
	public void snimiFajl(String imeFajla) {
		
		for (int i = 0; i < brojPredmeta; i++)
			Svetovid.out(imeFajla).println(spisak[i]);
		
		Svetovid.in(imeFajla).close();
	}
	
	
	public boolean dodajPredmet(String predmet, String profesor, int brojSt) {
		
		if (brojPredmeta >= MAX_PREDMETA || nadjiPredmet(predmet, profesor, brojSt) != null)
			return false;
			
		spisak[brojPredmeta] = new Predmet(predmet, profesor, brojSt);
		brojPredmeta++;
		
		return true;
	}
	
	
	private Predmet nadjiPredmet(String predmet, String profesor, int brojSt) {
		
		if (brojPredmeta == 0)
			return null;
			
		for (int i = 0; i < brojPredmeta; i++)
			if (spisak[i].predmet.equals(predmet) && spisak[i].profesor.equals(profesor) && spisak[i].brojSt == brojSt)
				return spisak[i];
		
		return null;
	}
	
	
	public void stampajPredmete(String predmet, int k) {
		
		if (brojPredmeta == 0) {
			System.out.println("Spisak je prazan.");
			return;
		}
		
		boolean barJedan = false;
		
		for (int i = 0; i < brojPredmeta; i++) {
			
			if (spisak[i].predmet.equals(predmet) && spisak[i].brojSt < k) {
				
				if (spisak[i].profesor.equals(""))
					System.out.println(spisak[i] + " - za ovaj predmet ne postoji profesor.");
				else
					System.out.println(spisak[i]);
				
				barJedan = true;
			}
		}
		
		if (!barJedan)
			System.out.println("Nijedan predmet ne zadovoljava kriterijume.");
	}
	
	
	public String toString() {
		
		String output = "SPISAK PREDMETA : ";
		
		for (int i = 0; i < brojPredmeta; i++)
			output += spisak[i] + " ";
		
		return output;
	}
}
