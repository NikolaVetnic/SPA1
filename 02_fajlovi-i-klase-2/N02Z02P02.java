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

class N02Z02P02 {
	
	public static void main(String[] args) {
		
		String imeFajla, i, p;
		int g;
		
		SpisakStudenata spisak = new SpisakStudenata();
		
		imeFajla = Svetovid.in.readLine("Ime fajla za ucitavanje: ");
		spisak.ucitajFajl(imeFajla);
		System.out.println(spisak);
		
		i = Svetovid.in.readLine("Unos, ime: ");
		p = Svetovid.in.readLine("Unos, prezime: ");
		g = Svetovid.in.readInt("Unos, godiste: ");
		spisak.dodajStudenta(i, p, g);
		System.out.println(spisak);
		
		imeFajla = Svetovid.in.readLine("Ime fajla za snimanje: ");
		spisak.snimiFajl(imeFajla);
		
		g = Svetovid.in.readInt("Ispis po godistu: ");
		System.out.println(spisak.stampajSpisakGod(g));
		
		g = Svetovid.in.readInt("Ispis po godistu: ");
		spisak.rodjenoPre(g);
	}
}

class Student {
	
	String ime, prezime;
	int god;
	
	
	public Student(String ime, String prezime, int god) {
		this.ime = ime;
		this.prezime = prezime;
		this.god = god;
	}
	
	
	public String toString() {
		return ime + " " + prezime + " " + god;
	}
}

class SpisakStudenata {
	
	static final int MAX_STUDENATA = 100;
	
	
	Student[] spisak;
	int brojElemenata;
	
	
	public SpisakStudenata() {
		this.spisak = new Student[MAX_STUDENATA];
		this.brojElemenata = 0;
	}
	
	
	public boolean dodajStudenta(String ime, String prezime, int god) {
		
		if (brojElemenata >= MAX_STUDENATA || nadjiStudenta(ime, prezime, god) != null)
			return false;
			
		spisak[brojElemenata] = new Student(ime, prezime, god);
		brojElemenata++;
		
		return true;
	}
	
	
	public void ucitajFajl(String imeFajla) {
		
		if (Svetovid.testIn(imeFajla)) {
			
			while (Svetovid.in(imeFajla).hasMore()) {
				
				String i = Svetovid.in(imeFajla).readToken();
				String p = Svetovid.in(imeFajla).readToken();
				int g = Svetovid.in(imeFajla).readInt();
				
				dodajStudenta(i, p, g);
			}
			
			if (Svetovid.in(imeFajla).hasMore())
				System.out.println("Spisak je popunjen.");
				
			Svetovid.in(imeFajla).close();
		}
	}
	
	
	public void snimiFajl(String imeFajla) {
		
		for (int i = 0; i < brojElemenata; i++)
			Svetovid.out(imeFajla).println(spisak[i]);
			
		Svetovid.in(imeFajla).close();
	}
	
	
	private Student nadjiStudenta(String ime, String prezime, int god) {
		
		for (int i = 0; i < brojElemenata; i++)
			if (spisak[i].ime.equals(ime) && spisak[i].prezime.equals(prezime) && spisak[i].god == god)
				return spisak[i];
				
		return null;
	}
	
	
	public int rodjenoPre(int g) {
		
		int count = 0;
		
		for (int i = 0; i < brojElemenata; i++)
			if (spisak[i].god > g)
				count++;
		
		System.out.println("Studenata rodjeno pre " + g + ": " + count);
		return count;
	}
	
	
	public String stampajSpisakGod(int g) {
		
		String output = "[ SPISAK STUDENATA (po godistu) : ";
		
		for (int i = 0; i < brojElemenata; i++)
			if (spisak[i].god == g)
				output += spisak[i] + " ";
		
		return output + "]";
	}
	
	
	public String toString() {
		
		String output = "[ SPISAK STUDENATA : ";
		
		for (int i = 0; i < brojElemenata; i++)
			output += spisak[i] + " ";
		
		return output + "]";
	}
}
