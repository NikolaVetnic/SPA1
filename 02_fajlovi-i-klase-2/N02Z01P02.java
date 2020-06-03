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

class N02Z01P02 {
	
	public static void main(String[] args) {
		
		String imeFajla, t, b;
		double c;
		
		SpisakIgracaka spisak = new SpisakIgracaka();
		
		imeFajla = Svetovid.in.readLine("Ime fajla za ucitavanje: ");
		spisak.ucitajFajl(imeFajla);
		System.out.println(spisak);
		
		t = Svetovid.in.readLine("Unos, tip: ");
		b = Svetovid.in.readLine("Unos, boja: ");
		c = Svetovid.in.readDouble("Unos, cena: ");
		spisak.dodajIgracku(t, b, c);
		
		imeFajla = Svetovid.in.readLine("Ime fajla za snimanje: ");
		spisak.snimiFajl(imeFajla);
		
		b = Svetovid.in.readLine("Pretraga, boja: ");
		System.out.println(spisak.stampajSpisakBoja(b));
		
		c = Svetovid.in.readDouble("Skuplje od: ");
		spisak.skupljeOd(c);
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
		return "(" + tip + " " + boja + " " + cena + ")";
	}
}

class SpisakIgracaka {
	
	static final int MAX_IGRACAKA = 100;
	
	
	Igracka[] spisak;
	int brojElemenata;
	
	
	public SpisakIgracaka() {
		this.spisak = new Igracka[MAX_IGRACAKA];
		this.brojElemenata = 0;
	}
	
	
	public boolean dodajIgracku(String tip, String boja, double cena) {
		
		if (brojElemenata >= MAX_IGRACAKA || nadjiIgracku(tip, boja, cena) != null)
			return false;
			
		spisak[brojElemenata] = new Igracka(tip, boja, cena);
		brojElemenata++;
		
		return true;
	}
	
	
	public void ucitajFajl(String imeFajla) {
		
		if (Svetovid.testIn(imeFajla)) {
			
			while (Svetovid.in(imeFajla).hasMore()) {
				
				String t = Svetovid.in(imeFajla).readToken();
				String b = Svetovid.in(imeFajla).readToken();
				double c = Svetovid.in(imeFajla).readDouble();
				
				dodajIgracku(t, b, c);
			}
			
			if (Svetovid.in(imeFajla).hasMore())
				System.out.println("Spisak je pun.");
			
			Svetovid.in(imeFajla).close();
		}
	}
	
	
	public void snimiFajl(String imeFajla) {
		
		for (int i = 0; i < brojElemenata; i++) {
			
			String s = spisak[i].tip + " " + spisak[i].boja + " " + spisak[i].cena;
			Svetovid.out(imeFajla).println(s);
		}
		
		Svetovid.in(imeFajla).close();
	}
	
	
	private Igracka nadjiIgracku(String tip, String boja, double cena) {
		
		if (brojElemenata == 0)
			return null;
			
		for (int i = 0; i < brojElemenata; i++)
			if (spisak[i].tip.equals(tip) && spisak[i].boja.equals(boja) && spisak[i].cena == cena)
				return spisak[i];
		
		return null;
	}
	
	
	public int skupljeOd(double c) {
		
		int count = 0;
		
		for (int i = 0; i < brojElemenata; i++)
			if (spisak[i].cena > c)
				count++;
				
		System.out.println("Igracaka skupljih od " + c + " ima: " + count);
		return count;
	}
	
	
	public String stampajSpisakBoja(String boja) {
		
		String output = "SPISAK PO BOJI, " + boja + " : ";
		
		for (int i = 0; i < brojElemenata; i++)
			if (spisak[i].boja.equals(boja))
				output += spisak[i];
		
		return output + "]";
	}
	
	
	public String toString() {
		
		String output = "[ SPISAK IGRACAKA : ";
		
		for (int i = 0; i < brojElemenata; i++)
			output += spisak[i] + " ";
			
		return output + "]";
	}
}
