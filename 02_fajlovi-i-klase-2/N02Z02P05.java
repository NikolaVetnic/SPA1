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

class N02Z02P05 {
	
	public static void main(String[] args) {
		
		String f, m, p;
		int g;
		
		f = Svetovid.in.readLine("Ucitavanje, fajl: ");
		SpisakStudenata spisak = new SpisakStudenata(f);
		System.out.println(spisak);
		
		m = Svetovid.in.readLine("Unos, ime: ");
		p = Svetovid.in.readLine("Unos, prezime: ");
		g = Svetovid.in.readInt("Unos, godiste: ");
		
		spisak.dodajStudenta(m, p, g);
		System.out.println(spisak);
		
		g = Svetovid.in.readInt("Pretraga, godina rodjenja: ");
		spisak.stampajGodina(g);
		
		g = Svetovid.in.readInt("Pretraga, rodjeni pre: ");
		spisak.stampajStarije(g);
		
		f = Svetovid.in.readLine("Snimanje, fajl: ");
		spisak.snimiFajl(f);
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
	
	static final int MAX_STUDENATA = 100;
	
	
	Student[] spisak;
	int brojElemenata;
	
	
	public SpisakStudenata(String f) {
		
		this.spisak = new Student[MAX_STUDENATA];
		this.brojElemenata = 0;
		
		if (Svetovid.testIn(f)) {
			
			while (Svetovid.in(f).hasMore()) {
				
				String m = Svetovid.in.readToken();
				String p = Svetovid.in.readToken();
				int g = Svetovid.in.readInt();
				
				dodajStudenta(m, p, g);
			}
			
			if (Svetovid.in(f).hasMore())
				System.out.println("Spisak je popunjen pre kraja fajla.");
				
			Svetovid.in(f).close();
		}
	}
	
	
	public boolean dodajStudenta(String m, String p, int g) {
		
		if (brojElemenata >= MAX_STUDENATA)
			return false;
			
		spisak[brojElemenata] = new Student(m, p, g);
		brojElemenata++;
		
		return true;
	}
	
	
	public void snimiFajl(String f) {
		
		for (int i = 0; i < brojElemenata; i++)
			Svetovid.out(f).println(spisak[i]);
			
		Svetovid.in(f).close();
	}


	public String stampajStarije(int g) {
		
		String output = "[ Spisak rodjenih pre godine " + g + " : ";
		
		for (int i = 0; i < brojElemenata; i++)
			if (spisak[i].g < g)
				output += spisak[i];
			
		output += "]";
		
		System.out.println(output);
		return output;
	}	
	
	
	public String stampajGodina(int g) {
		
		String output = "[ Spisak rodjenih godine " + g + " : ";
		
		for (int i = 0; i < brojElemenata; i++)
			if (spisak[i].g == g)
				output += spisak[i];
		
		output += "]";
		
		System.out.println(output);
		return output;
	}
	
	
	public String toString() {
		
		String output = "[ Spisak : ";
		
		for (int i = 0; i < brojElemenata; i++)
			output += spisak[i];
			
		return output + "]";
	}
}
