/**
 * Napisati program koji radi sa spiskom igracaka. U fajlu su u svakom
 * redu podaci o jednoj igracki, redom tip (zeka, meda,...), boja 
 * (plavo, zeleno...) i cena sa dve decimale.
 * 
 * 1. Ucitati podatke iz fajla cije se ime unosi sa tastature. Podatke 
 *    ispisati na ekran.
 * 2. Napraviti klasu koja predstavlja jednu igracku. Svaka igracka je 
 *    predstavljena svojim tipom, bojom i cenom.
 * 3. Podatke o igrackama predstaviti novom klasom koja podatke cuva u
 *    nizu objekata.
 * 4. Omoguciti unos nove igracke u spisak i snimiti tako promenjene
 *    podatke u fajl.
 * 5. Ispisati podatke o igrackama sa unetom bojom
 * 6. Prebrojati koliko igracaka je skuplje od unete cene
 */

class N02Z01P01 {

	public static void main(String[] args) {
		
		String imeFajla, tip, boja;
		double cena;
		
		imeFajla = Svetovid.in.readLine("Ime fajla za ucitavanje: ");
		SpisakIgracaka spisakIgracaka = new SpisakIgracaka();
		spisakIgracaka.ucitajFajl(imeFajla);
		spisakIgracaka.stampajSpisak();
		System.out.println();
		
		tip = Svetovid.in.readLine("Dodavanje nove igracke, tip: ");
		boja = Svetovid.in.readLine("Dodavanje nove igracke, boja: ");
		cena = Svetovid.in.readDouble("Dodavanje nove igracke, cena: ");
		spisakIgracaka.dodajIgracku(tip, boja, cena);
		System.out.println();
		
		imeFajla = Svetovid.in.readLine("Ime fajla za cuvanje spiska: ");
		spisakIgracaka.snimiFajl(imeFajla);
		System.out.println();
		
		boja = Svetovid.in.readLine("Boja za pretragu: ");
		spisakIgracaka.stampajSpisakBoja(boja);
		System.out.println();
		
		cena = Svetovid.in.readDouble("Cena za pretragu: ");
		System.out.println("Broj igracaka skupljih od " + cena + ": " + spisakIgracaka.skupljeOd(cena));
		System.out.println();
		
		
	}
}

class Igracka {
	
	String tip;
	String boja;
	double cena;
	
	
	public Igracka(String tip, String boja, double cena) {
		this.tip = tip;
		this.boja = boja;
		this.cena = cena;
	}
	
	
	public String toString() {
		return tip + " " + boja + " " + cena;
	}
}

class SpisakIgracaka {
	
	static final int MAX_IGRACAKA = 100;
	
	
	Igracka[] spisak;
	int count;
	
	
	public SpisakIgracaka() {
		this.spisak = new Igracka[MAX_IGRACAKA];
		this.count = 0;
	}
	
	
	public boolean dodajIgracku(String tip, String boja, double cena) {
		
		/**
		 * ideja je da se proveri da li je spisak popunjen, pa ako nije
		 * da se proveri da li takva igracka vec postoji u spisku, i a-
		 * ko ne da se tek onda unese nova i poveca brojac
		 */
		 
		if (count >= MAX_IGRACAKA) {
			System.out.println("Spisak je pun i nove igracke se ne mogu dodati.");
			return false;
		}
		
		boolean postoji = false;
		
		for (int i = 0; i < count && !postoji; i++) {
			String iTip = spisak[i].tip;
			String iBoja = spisak[i].boja;
			double iCena = spisak[i].cena;
			
			if (iTip.equals(tip) && iBoja.equals(boja) && iCena == cena)
				postoji = true;
		}
		
		if (postoji) {
			System.out.println("Igracka koju ste pokusali da dodate vec postoji.");
			return false;
		} else {
			spisak[count] = new Igracka(tip, boja, cena);
			count++;
			return true;
		}
	}
	
	
	public void stampajSpisakBoja(String boja) {
		for (int i = 0; i < count; i++)
			if (spisak[i].boja.equals(boja))
				System.out.println(spisak[i]);
	}
	
	
	public int skupljeOd(double cena) {
		
		int count = 0;
		
		for (int i = 0; i < this.count; i++)
			if (spisak[i].cena > cena)
				count++;

		return count;
	}
	
	
	public void ucitajFajl(String imeFajla) {
		
		/**
		 * prvo se proverava da li uopste postoji fajl, zatim se prove-
		 * rava da li ima jos linija u fajlu i da li je broj vec unetih
		 * manji od maksimuma niza, i dodaje se ako nije; na kraju pro-
		 * veriti da li postoji jos necega u fajlu sto nije uneto
		 */
		
		if (Svetovid.testIn(imeFajla)) {
			
			while (Svetovid.in(imeFajla).hasMore() && count < MAX_IGRACAKA) {
				String tip = Svetovid.in(imeFajla).readToken();
				String boja = Svetovid.in(imeFajla).readToken();
				double cena = Svetovid.in(imeFajla).readDouble();
				
				dodajIgracku(tip, boja, cena);
			}
			
			if (Svetovid.in(imeFajla).hasMore())
				System.out.println("Kapacitet tipa je premali da primi ceo sadrzaj fajla. Ucitano je prvih " + MAX_IGRACAKA + " igracaka.");
				
			Svetovid.in(imeFajla).close();
			
		} else {
			System.out.println("Fajl sa unetim nazivom ne postoji.");
		}
	}
	
	
	public void snimiFajl(String imeFajla) {
		
		for (int i = 0; i < count; i++)
			Svetovid.out(imeFajla).println(spisak[i].tip + " " + spisak[i].boja + " " + spisak[i].cena);
		
		Svetovid.out(imeFajla).close();
	}
	
	
	public void stampajSpisak() {
		for (int i = 0; i < count; i++) System.out.println(spisak[i]);
	}
	
}
