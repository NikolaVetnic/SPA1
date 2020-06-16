/**
 * Sportski klub je predstavljen klasom Klub koja sadrži sledeća polja:
 * naziv kluba, vrsta sporta (može biti "odbojka", "košarka" ili "fudb-
 * al") i godinu osnivanja. Spisak klubova u opštini je predstavljen k-
 * lasom Opstina i može sadržati maksimalno 20 klubova. Klasa takođe d-
 * efiniše konstruktor i toString() metod. Pored toga klasa definiše s-
 * ledeće metode:
 * 1. Metodu koja čita podatke o klubovima iz proizvoljnog fajla (funk-
 *    cionalnost je moguće realizovati i u konstruktoru).
 * 2. Metodu koja dodaje novi klub u opštinu.
 * 3. Metodu koja prebrojava koliko ima u zbiru klubova za zadatu vrstu
 *    sporta koji su osnovani pre neke godine koja je zadata kao param-
 *    etar metode. 
 * 4. Metodu koja snima podatke o klubovima u proizvoljan fajl.
 * 
 * Napisati program koji kreira opštinski spisak na osnovu ulaznog faj-
 * la (jedna linija sadrži podatke o jednom klubu i to naziv kluba, tip
 * sporta i godinu osnovanja razdvojeni razmacima), i poziva sve metode
 * definisane u klasi Opstina u gore datom redosledu.
 */
 
class Z03P01 {
	
	public static void main(String[] args) {
		
		Opstina op = new Opstina("opstina.txt");
		System.out.println(op);
		
		String naziv = Svetovid.in.readLine("Unos, naziv: ");
		String vrsta = Svetovid.in.readLine("Unos, vrsta: ");
		int god = Svetovid.in.readInt("Unos, godina: ");
		op.dodajKlub(naziv, vrsta, god);
		System.out.println(op);
		
		vrsta = Svetovid.in.readLine("Prebrojavanje, vrsta: ");
		god = Svetovid.in.readInt("Prebrojavanje, osnovani pre: ");
		op.prebrojKlubove(vrsta, god);
		
		op.sacuvajFajl("opmod.txt");
	}
} 

class Klub {
	
	String naziv, vrsta;
	int god;
	
	
	public Klub(String naziv, String vrsta, int god) {
		this.naziv = naziv;
		this.vrsta = vrsta;
		this.god = god;
	}
	
	
	public String toString() {
		return naziv + " " + vrsta + " " + god;
	}
}

class Opstina {
	
	static final int MAX_KLUBOVA = 20;
	
	
	Klub[] spisak;
	int br;
	
	
	public Opstina(String f) {
		
		this.spisak = new Klub[MAX_KLUBOVA];
		this.br = 0;
		
		if (Svetovid.testIn(f)) {
			
			while (Svetovid.in(f).hasMore()) {
				
				String naziv = Svetovid.in(f).readToken();
				String vrsta = Svetovid.in(f).readToken();
				int god = Svetovid.in(f).readInt();
				
				dodajKlub(naziv, vrsta, god);
			}
			
			if (Svetovid.in(f).hasMore())
				System.out.println("Spisak je popunjen pre kraja fajla.");
				
			Svetovid.in(f).close();
		}
	}
	
	
	public void sacuvajFajl(String f) {
		
		for (int i = 0; i < br; i++)
			Svetovid.out(f).println(spisak[i]);
			
		Svetovid.out(f).close();
	}
	
	
	public boolean dodajKlub(String naziv, String vrsta, int god) {
		
		if (br >= MAX_KLUBOVA)
			return false;
			
		spisak[br] = new Klub(naziv, vrsta, god);
		br++;
		
		return true;
	}
	
	
	public int prebrojKlubove(String vrsta, int god) {
		
		int count = 0;
		
		for (int i = 0; i < br; i++)
			if (spisak[i].vrsta.equals(vrsta) && spisak[i].god < god)
				count++;
		
		System.out.println("Klubova osnovanih pre " + god + ". (vrsta - " + vrsta + ") : " + count);
		return count;
	}
	
	
	public String toString() {
		
		String output = "OPSTINA : ";
		
		for (int i = 0; i < br; i++)
			output += spisak[i] + " ";
			
		return output;
	}
}
