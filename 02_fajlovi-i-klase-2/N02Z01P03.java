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

class N02Z01P03 {
	
	public static void main(String[] args) {
		
		String imeFajla, t, b;
		double c;
		
		imeFajla = Svetovid.in.readLine("Ime fajla za ucitavanje: ");
		SpisakIgracaka spisak = new SpisakIgracaka(imeFajla);
		
		System.out.println(spisak);
		
		t = Svetovid.in.readLine("Unos, tip: ");
		b = Svetovid.in.readLine("Unos, boja: ");
		c = Svetovid.in.readDouble("Unos, cena: ");
		spisak.dodajIgracku(t, b, c);
		
		System.out.println(spisak);
		
		imeFajla = Svetovid.in.readLine("Ime fajla za snimanje: ");
		spisak.snimiFajl(imeFajla);
		
		b = Svetovid.in.readLine("Pretraga, boja: ");
		spisak.stampajBoja(b);
		
		c = Svetovid.in.readDouble("Pretraga, skuplje od: ");
		spisak.stampajSkuplje(c);
	}
}

class Igracka {
	
	String tip, boja;
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
	int brojIgracaka;
	
	
	public SpisakIgracaka(String imeFajla) {
		
		this.spisak = new Igracka[MAX_IGRACAKA];
		this.brojIgracaka = 0;
		
		if (Svetovid.testIn(imeFajla)) {
			
			while (Svetovid.in(imeFajla).hasMore()) {
				
				String t = Svetovid.in(imeFajla).readToken();
				String b = Svetovid.in(imeFajla).readToken();
				double c = Svetovid.in(imeFajla).readDouble();
				
				dodajIgracku(t, b, c);
			}
			
			if (Svetovid.in(imeFajla).hasMore())
				System.out.println("Spisak je popunjen.");
			
			Svetovid.in(imeFajla).close();
		}
	}
	
	
	public void snimiFajl(String imeFajla) {
		
		for (int i = 0; i < brojIgracaka; i++)
			Svetovid.out(imeFajla).println(spisak[i]);
		
		Svetovid.in(imeFajla).close();
	}
	
	
	public boolean dodajIgracku(String t, String b, double c) {
		
		if (brojIgracaka >= MAX_IGRACAKA || nadjiIgracku(t, b, c) != null)
			return false;
			
		spisak[brojIgracaka] = new Igracka(t, b, c);
		brojIgracaka++;
		
		return true;
	}
	
	
	private Igracka nadjiIgracku(String t, String b, double c) {
		
		if (brojIgracaka == 0)
			return null;
			
		for (int i = 0; i < brojIgracaka; i++)
			if (spisak[i].tip.equals(t) && spisak[i].boja.equals(b) && spisak[i].cena == c)
				return spisak[i];
				
		return null;
	}
	
	
	public String stampajSkuplje(double c) {
		
		String output = "SPISAK IGRACAKA, skuplje od " + c + " : ";
		
		for (int i = 0; i < brojIgracaka; i++)
			if (spisak[i].cena > c)
				output += spisak[i] + " ";
				
		System.out.println(output);
		
		return output;
	}
	
	
	public String stampajBoja(String b) {
		
		String output = "SPISAK IGRACAKA, boja " + b + " : ";
		
		for (int i = 0; i < brojIgracaka; i++)
			if (spisak[i].boja.equals(b))
				output += spisak[i] + " ";
				
		System.out.println(output);
		
		return output;
	}
	
	
	public String toString() {
		
		String output = "SPISAK IGRACAKA : ";
		
		for (int i = 0; i < brojIgracaka; i++)
			output += spisak[i] + " ";
			
		return output;
	}
}
