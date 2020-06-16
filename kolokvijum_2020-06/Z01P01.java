/**
 * Jedan student je predstavljen klasom Student koja sadrži sledeća po-
 * lja: ime studenta, ocena na predmetu UuP, ocena na predmetu SPA1. K-
 * lasa takođe definiše konstruktor i toString() metod. Klasom Generac-
 * ija je predstavljena jedna generacija studenata kojih može biti mak-
 * simalno 100. Pored toga klasa definiše sledeće metode:
 * 1. Metodu koja čita podatke o studentima iz proizvoljnog fajla - ovu
 *    funkcionalnost je moguće realizovati i u konstruktoru.
 * 2. Metodu koja dodaje novog studenta u generaciju.
 * 3. Metodu koja proverava da li ima više studenata kojima je ocena na
 *    SPA1 veća ili jednaka od ocene na UuP nego obratno.
 * 4. Metodu koja snima podatke o studentima u proizvoljni fajl.
 * 
 * Napisati program koji kreira generaciju studenata na osnovu fajla u-
 * netog na ulazu (jedna linija fajla sadrži podatke o jednom studentu,
 * i to ime, UuP ocena i SPA1 ocena razdvojeni razmacima) i poziva met-
 * ode definisane u klasi Generacija u gore datom redosledu).
 */
 
class Z01P01 {
	
	public static void main(String[] args) {
		
		Generacija gen = new Generacija("generacija.txt");
		System.out.println(gen);
		
		String ime = Svetovid.in.readLine("Unos, ime: ");
		int uup = Svetovid.in.readInt("Unos, ocena iz UuP: ");
		int spa = Svetovid.in.readInt("Unos, ocena iz SPA1: ");
		
		gen.dodajStudenta(ime, uup, spa);
		System.out.println(gen);
		
		if (gen.viseStudenata())
			System.out.println("Vise je studenata sa ocenom iz SPA1 vecom ili jednakom oceni iz UuP.");
		else
			System.out.println("Vise je studenata sa ocenom iz UuP vecom od ocene iz SPA1.");
			
		gen.sacuvajFajl("genmod.txt");
	}
}

class Student {
	
	String ime;
	int uup, spa;
	
	
	public Student(String ime, int uup, int spa) {
		this.ime = ime;
		this.uup = uup;
		this.spa = spa;
	}
	
	
	public String toString() {
		return ime + " " + uup + " " + spa;
	}
}

class Generacija {
	
	final static int MAX_STUDENATA = 100;
	
	
	Student[] spisak;
	int br;
	
	
	public Generacija(String f) {
		
		this.spisak = new Student[MAX_STUDENATA];
		this.br = 0;
		
		if (Svetovid.testIn(f)) {
			
			while (Svetovid.in(f).hasMore()) {
				
				String ime = Svetovid.in(f).readToken();
				int uup = Svetovid.in(f).readInt();
				int spa = Svetovid.in(f).readInt();
				
				dodajStudenta(ime, uup, spa);
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
	
	
	public boolean dodajStudenta(String ime, int uup, int spa) {
		
		if (br >= MAX_STUDENATA)
			return false;
			
		spisak[br] = new Student(ime, uup, spa);
		br++;
		
		return true;
	}
	
	
	public boolean viseStudenata() {
		
		int count = 0;
		
		for (int i = 0; i < br; i++)
			if (spisak[i].spa >= spisak[i].uup)
				count++;
				
		return count > br - count;
	}
	
	
	public String toString() {
		
		String output = "GENERACIJA : ";
		
		for (int i = 0; i < br; i++)
			output += spisak[i] + " ";
			
		return output;
	}
}
