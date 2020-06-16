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

class N02Z02P01 {
	
	public static void main(String[] args) {
		
		String imeFajla, prezime, ime;
		int god;
		SpisakStudenata spisakStudenata = new SpisakStudenata();
		
		imeFajla = Svetovid.in.readLine("Ime fajla za ucitavanje: ");
		spisakStudenata.ucitajFajl(imeFajla);
		spisakStudenata.stampajSpisak();
		System.out.println();
		
		prezime = Svetovid.in.readLine("Unos novog studenta, prezime: ");
		ime = Svetovid.in.readLine("Unos novog studenta, ime: ");
		god = Svetovid.in.readInt("Unos novog studenta, godiste: ");
		spisakStudenata.dodajStudenta(prezime, ime, god);
		imeFajla = Svetovid.in.readLine("Ime fajla za cuvanje: ");
		spisakStudenata.snimiFajl(imeFajla);
		System.out.println();
		
		god = Svetovid.in.readInt("Stampaj podatke o studentima prema godini rodjenja: ");
		spisakStudenata.stampajSpisakGod(god);
		System.out.println();
		
		god = Svetovid.in.readInt("Prebroj studente rodjene pre: ");
		spisakStudenata.stampajStarijeOd(god);
		System.out.println();
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
		return prezime + " " + ime + " " + god;
	}
	
}

class SpisakStudenata {
	
	static final int MAX_STUDENATA = 100;
	
	Student[] spisak;
	int count;
	
	public SpisakStudenata() {
		this.spisak = new Student[MAX_STUDENATA];
		this.count = 0;
	}
	
	
	public boolean dodajStudenta(String prezime, String ime, int god) {
		
		if (count >= MAX_STUDENATA) {
			System.out.println("Spisak je pun i novi studenti se ne mogu dodati.");
			return false;
		}
		
		boolean pronadjen = false;
		
		for (int i = 0; i < count && !pronadjen; i++)
			if (spisak[i].prezime.equals(prezime) && spisak[i].ime.equals(ime) && spisak[i].god == god)
				pronadjen = true;
		
		if (pronadjen) {
			System.out.println("Uneti student vec postoji u spisku.");
			return false;
		} else {
			spisak[count] = new Student(prezime, ime, god);
			count++;
			return true;
		}
	}
	
	
	public void ucitajFajl(String imeFajla) {
		
		if (Svetovid.testIn(imeFajla)) {
			
			while (Svetovid.in(imeFajla).hasMore() && count < MAX_STUDENATA) {
				String prezime = Svetovid.in(imeFajla).readToken();
				String ime = Svetovid.in(imeFajla).readToken();
				int god = Svetovid.in(imeFajla).readInt();
				
				dodajStudenta(prezime, ime, god);
			}
			
			if (Svetovid.in(imeFajla).hasMore())
				System.out.println("Kapacitet tipa je premali da primi ceo sadrzaj fajla. Ucitano je prvih " + MAX_STUDENATA + " studenata.");
			
			Svetovid.in(imeFajla).close();
			
		} else {
			System.out.println("Fajl sa unetim nazivom ne postoji.");
		}
	}
	
	
	public void snimiFajl(String imeFajla) {
		
		for (int i = 0; i < count; i++)
			Svetovid.out(imeFajla).println(spisak[i]);
		
		Svetovid.in(imeFajla).close();
	}
	
	
	public void stampajSpisakGod(int god) {
		for (int i = 0; i < count; i++)
			if (spisak[i].god == god)
				System.out.println(spisak[i]);
	}
	
	
	public void stampajStarijeOd(int god) {
		for (int i = 0; i < count; i++)
			if (spisak[i].god < god)
				System.out.println(spisak[i]);
	}
	
	
	public void stampajSpisak() {
		for (int i = 0; i < count; i++) System.out.println(spisak[i]);
	}
}
