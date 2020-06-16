/**
 * Napisati program koji radi sa spiskom studenata. U fajlu su u svak-
 * om redu podaci o jednom studentu, redom ime, prezime i godina rodj-
 * enja razdvojeni razmacima. 
 * 
 * 1. Ucitati podatke iz fajla cije se ime unosi sa tastature. Podatke 
 *    ispisati na ekran.
 * 2. Napraviti klasu koja predstavlja jednog studenta. Za svakog stu-
 *    denta se pamti ime, prezime i godina rodjenja. 
 * 3. Podatke o studentima predstaviti novom klasom koja podatke cuva
 *    u nizu objekata.
 * 4. Omoguciti unos novog studenta u spisak i snimiti tako promenjene 
 *    podatke u fajl.
 * 5. Ispisati podatke o studentima sa unetom godinom rodjenja
 * 6. Prebrojati koliko studenata je rodjeno pre neke unete godine
 */

class N02Z02P03 {
	
	public static void main(String[] args) {
		
		String fajl, i, p;
		int g;
		
		fajl = Svetovid.in.readLine("Ucitati fajl: ");
		SpisakStudenata spisak = new SpisakStudenata(fajl);
		
		System.out.println(spisak);
		
		i = Svetovid.in.readLine("Unos, ime: ");
		p = Svetovid.in.readLine("Unos, prezime: ");
		g = Svetovid.in.readInt("Unos, godina: ");
		spisak.dodajStudenta(i, p, g);
		
		System.out.println(spisak);
		
		fajl = Svetovid.in.readLine("Snimiti fajl: ");
		spisak.snimiFajl(fajl);
		
		g = Svetovid.in.readInt("Pretraga, godina rodjenja: ");
		spisak.stampajGod(g);
		
		g = Svetovid.in.readInt("Pretraga, rodjeni pre: ");
		spisak.stampajStarije(g);
	}
}

class Student {
	
	String i, p;
	int g;
	
	
	public Student(String i, String p, int g) {
		this.i = i;
		this.p = p;
		this.g = g;
	}
	
	
	public String toString() {
		return i + " " + p + " " + g;
	}
}

class SpisakStudenata {
	
	static final int MAX_STUDENATA = 100;
	
	
	Student[] spisak;
	int brojStudenata;
	
	
	public SpisakStudenata(String fajl) {
		
		this.spisak = new Student[MAX_STUDENATA];
		this.brojStudenata = 0;
		
		if (Svetovid.testIn(fajl)) {
			
			while (Svetovid.in(fajl).hasMore()) {
				
				String i = Svetovid.in(fajl).readToken();
				String p = Svetovid.in(fajl).readToken();
				int g = Svetovid.in(fajl).readInt();
				
				dodajStudenta(i, p, g);
			}
			
			if (Svetovid.in(fajl).hasMore())
				System.out.println("Spisak je popunjen.");
				
			Svetovid.in(fajl).close();
		}
	}
	
	
	public void snimiFajl(String fajl) {
		
		for (int i = 0; i < brojStudenata; i++)
			Svetovid.out(fajl).println(spisak[i]);
			
		Svetovid.in(fajl).close();
	}
	
	
	public boolean dodajStudenta(String i, String p, int g) {
		
		if (brojStudenata >= MAX_STUDENATA || nadjiStudenta(i, p, g) != null)
			return false;
			
		spisak[brojStudenata] = new Student(i, p, g);
		brojStudenata++;
		
		return true;
	}
	
	
	private Student nadjiStudenta(String im, String p, int g) {
		
		if (brojStudenata == 0)
			return null;
			
		for (int i = 0; i < brojStudenata; i++)
			if (spisak[i].i.equals(im) && spisak[i].p.equals(p) && spisak[i].g == g)
				return spisak[i];
				
		return null;
	}
	
	
	public String stampajGod(int g) {
		
		String output = "SPISAK STUDENATA rodjenih " + g + " : ";
		
		for (int i = 0; i < brojStudenata; i++)
			if (spisak[i].g == g)
				output += spisak[i] + " ";
				
		System.out.println(output);
		
		return output;
	}
	
	
	public String stampajStarije(int g) {
		
		String output = "SPISAK STUDENATA rodjenih pre " + g + " : ";
		
		for (int i = 0; i < brojStudenata; i++)
			if (spisak[i].g < g)
				output += spisak[i] + " ";
				
		System.out.println(output);
		
		return output;
	}
	
	
	public String toString() {
		
		String output = "SPISAK STUDENATA : ";
		
		for (int i = 0; i < brojStudenata; i++)
			output += spisak[i] + " ";
			
		return output;
	}
}
