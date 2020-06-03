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
 * 	  u nizu objekata.
 * 4. Omoguciti unos novog studenta u spisak i snimiti tako promenjene 
 *    podatke u fajl.
 * 5. Ispisati podatke o studentima sa unetom godinom rodjenja
 * 6. Prebrojati koliko studenata je rodjeno pre neke unete godine
 */

class N02Z02P04 {
	
	public static void main(String[] args) {
		
		String f, m, p;
		int g;
		
		f = Svetovid.in.readLine("Fajl, ucitavanje: ");
		SpisakStudenata spisak = new SpisakStudenata(f);
		
		System.out.println(spisak);
		
		m = Svetovid.in.readLine("Unos, ime: ");
		p = Svetovid.in.readLine("Unos, prezime: ");
		g = Svetovid.in.readInt("Unos, godina rodjenja: ");
		spisak.dodajStudenta(m, p, g);
		
		System.out.println(spisak);
		
		f = Svetovid.in.readLine("Fajl, snimanje: ");
		spisak.snimiFajl(f);
		
		g = Svetovid.in.readInt("Stampaj sve rodjene godine: ");
		spisak.stampajGod(g);
		
		g = Svetovid.in.readInt("Prebroj rodjene pre godine: ");
		spisak.prebrojStarije(g);
	}
}

class Student {
	
	String m, p;
	int g;
	
	
	public Student(String m, String p, int g) {
		this.m = m;
		this.p = p;
		this.g = g;
	}
	
	
	public String toString() {
		return m + " " + p + " " + g;
	}
}

class SpisakStudenata {
	
	final static int MAX_STUDENATA = 100;
	
	
	Student[] spisak;
	int brojStudenata;
	
	
	public SpisakStudenata(String f) {
		
		
		this.spisak = new Student[MAX_STUDENATA];
		this.brojStudenata = 0;
		
		
		if (Svetovid.testIn(f)) {
			
			while (Svetovid.in(f).hasMore()) {
				
				String m = Svetovid.in(f).readToken();
				String p = Svetovid.in(f).readToken();
				int g = Svetovid.in(f).readInt();
				
				dodajStudenta(m, p, g);
			}
			
			if (Svetovid.in(f).hasMore())
				System.out.println("Spisak je popunjen pre kraja fajla.");
			
			Svetovid.in(f).close();
		}
	}
	
	
	public void snimiFajl(String f) {
		
		for (int i = 0; i < brojStudenata; i++)
			Svetovid.out(f).println(spisak[i]);
		
		Svetovid.in(f).close();
	}
	
	
	public boolean dodajStudenta(String m, String p, int g) {
		
		if (brojStudenata >= MAX_STUDENATA || nadjiStudenta(m, p, g) != null)
			return false;
			
		spisak[brojStudenata] = new Student(m, p, g);
		brojStudenata++;
		
		return true;
	}
	
	
	private Student nadjiStudenta(String m, String p, int g) {
		
		if (brojStudenata == 0)
			return null;
			
		for (int i = 0; i < brojStudenata; i++)
			if (spisak[i].m.equals(m) && spisak[i].p.equals(p) && spisak[i].g == g)
				return spisak[i];
				
		return null;
	}
	
	
	public int prebrojStarije(int g) {
		
		if (brojStudenata == 0)
			return -1;
			
		int count = 0;
		
		for (int i = 0; i < brojStudenata; i++)
			if (spisak[i].g < g)
				count++;
				
		System.out.println("Studenata rodjenih pre " + g + ": " + count);
		return count;
	}
	
	
	public String stampajGod(int g) {
		
		String output = "SPISAK STUDENATA rodjenih " + g + " : ";
		
		for (int i = 0; i < brojStudenata; i++)
			if (spisak[i].g == g)
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
