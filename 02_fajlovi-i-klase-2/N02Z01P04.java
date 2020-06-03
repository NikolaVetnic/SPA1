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

class N02Z01P04 {
	
	public static void main(String[] args) {
		
		String f, t, b;
		double c;
		
		f = Svetovid.in.readLine("Ucitavanje, fajl: ");
		SpisakIgracaka spisak = new SpisakIgracaka(f);
		
		System.out.println(spisak);
		
		t = Svetovid.in.readLine("Unos, tip: ");
		b = Svetovid.in.readLine("Unos, boja: ");
		c = Svetovid.in.readDouble("Unos, cena: ");
		spisak.dodajIgracku(t, b, c);
		
		System.out.println(spisak);
		
		f = Svetovid.in.readLine("Snimanje, fajl: ");
		spisak.snimiFajl(f);
		
		b = Svetovid.in.readLine("Stampanje, boja: ");
		spisak.stampajBoja(b);
		
		c = Svetovid.in.readDouble("Stampanje, skuplje od: ");
		spisak.stampaSkuplje(c);
	}
}

class Igracka {
	
	String t, b;
	double c;
	
	
	public Igracka(String t, String b, double c) {
		this.t = t;
		this.b = b;
		this.c = c;
	}
	
	
	public String toString() {
		return t + " " + b + " " + c;
	}
}

class SpisakIgracaka {
	
	static final int MAX_IGRACAKA = 100;
	
	
	Igracka[] spisak;
	int brojIgracaka;
	
	
	public SpisakIgracaka(String f) {
		
		
		this.spisak = new Igracka[MAX_IGRACAKA];
		this.brojIgracaka = 0;
		
		
		if (Svetovid.testIn(f)) {
			
			while (Svetovid.in(f).hasMore()) {
				
				String t = Svetovid.in(f).readToken();
				String b = Svetovid.in(f).readToken();
				double c = Svetovid.in(f).readDouble();
				
				dodajIgracku(t, b, c);
			}
			
			if (Svetovid.in(f).hasMore())
				System.out.println("Spisak je popunjen pre kraja fajla.");
			
			Svetovid.in(f).close();
		}
	}
	
	
	public void snimiFajl(String f) {
		
		for (int i = 0; i < brojIgracaka; i++)
			Svetovid.out(f).println(spisak[i]);
			
		Svetovid.in(f).close();
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
			if (spisak[i].t.equals(t) && spisak[i].b.equals(b) && spisak[i].c == c)
				return spisak[i];
				
		return null;
	}
	
	
	public String stampaSkuplje(double c) {
		
		String output = "SPISAK IGRACAKA, skuplje od " + c + " : ";
		
		for (int i = 0; i < brojIgracaka; i++)
			if (spisak[i].c > c)
				output += spisak[i] + " ";
		
		System.out.println(output);
		return output;
	}
	
	
	public String stampajBoja(String b) {
		
		String output = "SPISAK IGRACAKA, boja - " + b + " : ";
		
		for (int i = 0; i < brojIgracaka; i++)
			if (spisak[i].b.equals(b))
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
