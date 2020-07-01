/**
 * Napisati program koji učitava n String-ova (n unosi korisnik), smeš-
 * ta ih u jednostruko povezanu listu, i štampa sadržaj liste. Listu f-
 * ormirati počevši od prazne liste, dodajući elemente s početka liste.
 * Program potom:
 * 
 * a) štampa sve elemente liste koji su duzi od tri slova,
 * b) utvrđuje broj pojava u listi elementa jednakog String-u b (kog u-
 *    nosi korisnik),
 * c) štampa poslednji element liste.
 */

class ListaStringova {
	
	public static void main(String[] args) {
		
		// unos elemenata liste
		int n;
		do {
			n = Svetovid.in.readInt("Unesite broj elemenata liste: ");
			if (n <= 0) System.out.println("Greska - morate uneti nenegativan prirodni broj!");
		} while (n <= 0);
		
		ListaStringova lista = new ListaStringova();
		
		for (int i = 0; i < n; i++) {
			String s = Svetovid.in.readLine("Unesite string: ");
			lista.dodajNaPocetak(s);
		}
		System.out.println();
		
		// ispisivanje svih stringova duzih od unetog broja
		int len;
			do {
				len = Svetovid.in.readInt("Unesite minimalnu duzinu stringa: ");
				if (len <= 0) System.out.println("Greska - morate uneti nenegativan prirodni broj!");
			} while (len <= 0);
		lista.stampajDuzeOdNSlova(len);
		
		// prebrojavanje pojava stringa u listi
		String b = Svetovid.in.readLine("Unesite string za pretragu: ");
		int count1 = lista.brojPojava(b);
		System.out.println("Broj pojava stringa '" + b + "' u listi: " + count1 + "\n");
		
		// stampa poslednjeg elementa stringa
		lista.stampajPoslednji();
		System.out.println();
		
		// izbacivanje elementa
		String s1 = Svetovid.in.readLine("Unesite string za izbacivanje: ");
		lista.izbaciIzListe(s1);
		lista.stampajNaEkran();
		
		// izbacivanje svih elemenata koji odgovaraju unetom
		String s2 = Svetovid.in.readLine("Unesite string za izbacivanje (sve pojave): ");
		int count2 = lista.izbaciIzListeSve(s2);
		System.out.println("Izbaceno je elemenata: " + count2);
		lista.stampajNaEkran();
		System.out.println();
		
		// izbacivanje elementa na poziciji
		int k = Svetovid.in.readInt("Unesite poziciju za brisanje: ");
		lista.izbaciNaPoziciji(k);
		lista.stampajNaEkran();
		System.out.println();
	}
	

	class Element {
		
		String info;
		Element veza;
		
		public Element(String input) {
			
			this.info = input;
			veza = null;							// [PROF] dodato po sugestiji
		}
		
		public String toString() {
			return info;
		}
	}
	
	
	Element prvi;
	int brojElemenata;								// spa1-liste2.pdf, vezbanje
	
	
	public ListaStringova() {
		
		this.prvi = null;
		this.brojElemenata = 0;						// spa1-liste2.pdf, vezbanje
	}
	
	public void dodajNaPocetak(String input) {
		
		Element novi = new Element(input);
		novi.veza = prvi;
		prvi = novi;
		brojElemenata++;							// spa1-liste2.pdf, vezbanje
	}
	
	public boolean izbaciIzListe(String input) {	// spa1-liste2.pdf, vezbanje
		if (prvi != null && prvi.info.equals(input)) {
			
			prvi = prvi.veza;
			return true;
		} else {
			
			Element tekuci = prvi;
			Element prethodni = null;
			
			while (tekuci != null && !tekuci.info.equals(input)) {
				
				prethodni = tekuci;
				tekuci = tekuci.veza;
			}
			
			if (tekuci != null) {
				
				prethodni.veza = tekuci.veza;
				return true;
			} else {
				
				System.out.println("Uneti string se ne nalazi u listi.");
				return false;
			}
		}
	}	// izbaciIzListe
	
	
	public int izbaciIzListeSve(String input) {		// spa1-liste2.pdf, vezbanje
		
		int count = 0;
		
		while (prvi != null && prvi.info.equals(input)) {
			
			prvi = prvi.veza;
			count++;
		} 
		
		if (prvi != null) {
			
			Element tekuci = prvi;
			Element prethodni = null;
			
			while (tekuci.veza != null) {
				
				prethodni = tekuci;
				tekuci = tekuci.veza;
				
				if (tekuci.info.equals(input)) {
					
					prethodni.veza = tekuci.veza;	// prevezujemo listu oko elementa
					count++;
					tekuci = prethodni;				// vracamo se jedan korak da bi se proverio i ovaj element
				}
			}
		}
		
		return count;
	}	// izbaciIzListeSve
	
	public boolean izbaciNaPoziciji(int k) {		// spa1-liste2.pdf, vezbanje
		if (k >= brojElemenata) {
			
			System.out.println("Lista sadrzi manje elemanata od: " + k);
			return false;
		}
		
		if (k < 0) {
			
			System.out.println("Ne postoje elementi na negativnim pozicijama.");
			return false;
		}
		
		if (k == 0) {
			
			prvi = prvi.veza;
		} else {
			
			Element prethodni = prvi;
			int count = 0;
			
			while (count < k - 1) {
				
				prethodni = prethodni.veza;
				count++;
			}
			
			prethodni = prethodni.veza.veza;
		}
		
		brojElemenata--;
		return true;
	}
	
	
	public boolean jePrazna() {
		return prvi == null;
	}
	
	
	public void stampajNaEkran() {
		
		if (prvi == null) {
			
			System.out.println("Lista je prazna.");
		} else {
			
			System.out.println("Sadrzaj liste: ");
			Element tekuci = prvi;
			
			while (tekuci != null) {
				
				System.out.println(tekuci.info);
				tekuci = tekuci.veza;
			}
			System.out.println();
		}
	}
	
	
	public void stampajDuzeOdNSlova(int n) {		// [PROF] unos minimalne duzine izmesten u main
		if (prvi == null) {
			
			System.out.println("Lista je prazna.");
		} else {
			
			System.out.println("Elementi liste duzi od " + n + " slova: ");
			Element tekuci = prvi;
			
			while (tekuci != null) {
				
				if (tekuci.info.length() > n) 
					System.out.println(tekuci.info);
				tekuci = tekuci.veza;
			}
			System.out.println("Kraj ispisa.\n");
		}
	}
	
	
	public void stampajPoslednji() {
		if (prvi == null) {
			
			System.out.println("Lista je prazna.");
		} else {
			
			Element tekuci = prvi;
			while (tekuci.veza != null) tekuci = tekuci.veza;
			System.out.println("Poslednji element liste: " + tekuci.info);
		}
	}
	
	
	public int brojPojava(String b) {				// [PROF] unos Stringa za pretragu izmesten u main
		
		if (prvi == null) {							// [PROF] dodata provera da li je lista prazna
			
			System.out.println("Lista je prazna.");	// [PROF] metod vraca int
			return -1;
		}
		else {
			
			Element tekuci = prvi;
			int count = 0;
			
			while (tekuci != null) {
				
				if (tekuci.info.equals(b))
					count++;
					
				tekuci = tekuci.veza;
			}
			return count;
		}
	}
	
	
	public String toString() {
		
		String output = "Lista [ ";
		Element tekuci = prvi;
		
		while (tekuci != null) {
			output += tekuci.info + " ";
			tekuci = tekuci.veza;
		}
		
		return output + " ]";
	}
}
