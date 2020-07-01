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

import java.text.DecimalFormat;

class N02Z01P06 {
	
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	public static void main(String[] args) {
		
		SpisakIgracaka spisak = new SpisakIgracaka();
		
		// ucitavanje podataka iz fajla cije se ime unosi
		String imeFajla = Svetovid.in.readLine("Ime fajla: ");
		spisak.ucitajFajl(SpisakIgracaka.checkExt(imeFajla));
		System.out.println(spisak);
		System.out.println();
		
		// unos nove igracke
		System.out.println("Unos nove igracke");
		String tip 		= Svetovid.in.readToken("Unesite tip: ");
		String boja 	= Svetovid.in.readToken("Unesite boju: ");
		double cena 	= Svetovid.in.readDouble("Unesite cenu: ");
		spisak.dodajIgracku(tip, boja, cena);
		System.out.println();
		
		// cuvanje novog stanja
		String output 	= Svetovid.in.readLine("Ime fajla: ");
		spisak.snimiFajl(SpisakIgracaka.checkExt(output));
		System.out.println();
		
		// ispisivanje prema unetoj boji
		String inputB 	= Svetovid.in.readLine("Unesite boju za pretragu: ");
		for (int i = 0; i < spisak.count; i++)
			if (spisak.spisak[i].boja.equals(inputB))
				System.out.println(spisak.spisak[i]);
		System.out.println();
		
		// prebrojavanje igracaka skupljih od cene
		double inputC	= Svetovid.in.readDouble("Unesite granicnu cenu: ");
		for (int i = 0; i < spisak.count; i++)
			if (spisak.spisak[i].cena >= inputC)
				System.out.println(spisak.spisak[i]);
		System.out.println();
		
		System.out.println("Kraj rada.");
	}
}

class Igracka {
	
	String tip, boja;
	double cena;
	
	
	public Igracka() {
		
		this.tip 	= null;
		this.boja	= null;
		this.cena	= -1.0;
	}
	
	
	public Igracka(String tip, String boja, double cena) {
		
		this.tip 	= tip;
		this.boja 	= boja;
		this.cena 	= cena;
	}
	
	
	public String toString() {
		return tip + " (" + boja + "), " + String.format("%.2f", cena);
	}
}

class SpisakIgracaka {
	
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	static final int MAX_IGRACAKA = 100;
	
	Igracka[] spisak;
	int count;
	
	public SpisakIgracaka() {
		
		spisak 	= new Igracka[MAX_IGRACAKA];
		count 	= 0;
	}
	
	
	public boolean dodajIgracku(String tip, String boja, double cena) {
		
		if (count >= MAX_IGRACAKA) {
			
			System.out.println("Greska! Vec ima " + MAX_IGRACAKA + " igracaka i nove se ne mogu dodati.");
			return false;
		}
		
		boolean postoji = false;
		for (int i = 0; i < count && !postoji; i++)
			if (spisak[i].tip == tip && spisak[i].boja == boja && spisak[i].cena == cena)
				postoji = true;
		
		if (postoji) {
			
			System.out.println("Greska! Igracka koju ste pokusali da unesete vec se nalazi u spisku.");
			return false;
		} else {
			
			spisak[count] = new Igracka(tip, boja, cena);
			count++;
			return true;
		}
	}
	
	
	public void ucitajFajl(String imeFajla) {
		
		if (Svetovid.testIn(imeFajla)) {
			while (Svetovid.in(imeFajla).hasMore() && count < MAX_IGRACAKA)
				dodajIgracku(Svetovid.in(imeFajla).readToken(), Svetovid.in(imeFajla).readToken(), Svetovid.in(imeFajla).readDouble());
			
			if (Svetovid.in(imeFajla).hasMore())
				System.out.println("Greska! Sadrzaj fajla je prevelik za spisak; ucitano je prvih " + MAX_IGRACAKA + " igracaka.");
		}
		
		Svetovid.in(imeFajla).close();
	}
	
	
	public void snimiFajl(String imeFajla) {
		
		for (int i = 0; i < count; i++) 
			Svetovid.out(imeFajla).println(spisak[i].tip + " " + spisak[i].boja + " " + df2.format(spisak[i].cena));
		
		System.out.println("Trenutno stanje spiska sacuvano u fajl " + imeFajla);
		
		Svetovid.out(imeFajla).close();
	}
	
	
	static String checkExt(String imeFajla) {
		if (imeFajla.substring(imeFajla.length() - 4, imeFajla.length()).equals(".txt"))
			return imeFajla;
		else
			return imeFajla += ".txt";
	}
	
	
	public String toString() {
		
		String output = "SPISAK IGRACAKA:\n\n";
		
		if (count > 0) {
			output += "001\t" + spisak[0] + "\n";
			
			for (int i = 1; i < count; i++)
				output += String.format("%03d", (i + 1)) + "\t" + spisak[i] + "\n";
		}
		
		output += "\nKRAJ SPISKA.";
		
		return output;
	}
}
