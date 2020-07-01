/** 
 * Napraviti klasu koja predstavlja jednostruko povezanu listu znakova
 * (char). Koristiti najjednostavnije dodavanje na početak. Napisati p-
 * rogram koji učitava n znakova i smešta ih u instancu napravljene kl-
 * ase. U klasu dodati metode koji omogućavaju sledeće operacije i poz-
 * vati ih iz glavnog programa:
 * 
 * a) štampanje samo znakova koji su velika slova
 * b) izbacivanje svih znakova koji su mala slova
 * c) izdvajanje u novu listu svih znakova koji su cifre. Rezultat - n-
 *    ov objekat istog tipa lista, koristi se ista memorija za elemente
 *    a elementi u novoj listi treba da su u istom redosledu kao u ori-
 *    ginalnoj listi.
 */

class N03Z02P07 {
	
	public static void main(String[] args) {
		
		// unos elemenata liste
		int n;
		do {
			n = Svetovid.in.readInt("Unesite broj elemenata liste: ");
			if (n <= 0) System.out.println("Greska - morate uneti nenegativan prirodni broj!");
		} while (n <= 0);
		
		ListaKaraktera lista = new ListaKaraktera();
		
		for (int i = 0; i < n; i++) {
			char c = Svetovid.in.readChar("Unesite karakter: ");
			lista.dodajNaPocetak(c);
		}
		//~ lista.stampajNaEkran();
		System.out.println();
		
		// stampanje velikih slova
		lista.stampajVelikaSlova();
		System.out.println();
		
		// izbacivanje malih slova
		int count = lista.izbaciMalaSlova();
		System.out.println("Broj izbacenih malih slova: " + count);
		System.out.println();
		
		// izdvajanje cifara
		ListaKaraktera cifre = lista.izdvojCifre();
		System.out.println(cifre);
	}
	
	
	class Element {
		
		char info;
		Element veza;
		
		
		public Element(char info) {
			this.info = info;
		}
		
		
		public String toString() {
			return info + "";
		}
	}
	
	
	Element prvi;
	int brojElemenata;
	
	
	public ListaKaraktera() {
		
		this.prvi = null;
		this.brojElemenata = 0;
	}
	
	
	public void dodajNaPocetak(char input) {
		
		Element novi = new Element(input);
		novi.veza = prvi;
		prvi = novi;
		brojElemenata++;
	}
	
	
	public void stampajVelikaSlova() {
		
		if (prvi == null) {
		
			System.out.println("Lista je prazna.");
		} else {
			
			System.out.println("Velika slova u okviru liste: ");
			
			Element tekuci = prvi;
			boolean barJedan = false;
			
			while (tekuci != null) {
				
				if ('A' <= tekuci.info && tekuci.info <= 'Z') {
					
					System.out.println(tekuci.info);
					barJedan = true;
				}
				
				tekuci = tekuci.veza;
			}
			
			if (!barJedan) System.out.println("Nema velikih slova u listi.");
		}
	}
	
	
	public int izbaciMalaSlova() {
		
		int count = 0;
		
		while ('a' <= prvi.info && prvi.info <= 'z') {
			
			prvi = prvi.veza;
			count++;
		}
		
		if (prvi != null) {
			
			Element prethodni = null;
			Element tekuci = prvi;
			
			while (tekuci.veza != null) {
				
				prethodni = tekuci;
				tekuci = tekuci.veza;
				
				if ('a' <= tekuci.info && tekuci.info <= 'z') {
					
					prethodni.veza = tekuci.veza;
					count++;
					tekuci = prethodni;
				}
			}
		}
		
		return count;
	}
	
	
	public ListaKaraktera izdvojCifre() {
		
		ListaKaraktera cifre = new ListaKaraktera();
		
		Element cifreKraj = null;
		
		Element tek, pret;
		
		while (prvi != null && Character.isDigit(prvi.info)) {
			
			tek = prvi;
			prvi = prvi.veza;
			
			if (cifre.prvi == null) {
				
				cifre.prvi = tek;
				cifreKraj = tek;
				tek.veza = null;
			} else {
				
				cifreKraj.veza = tek;
				tek.veza = null;
				cifreKraj = tek;
			}
		}
		
		if (prvi != null) {
			
			tek = prvi;
			
			while (tek.veza != null) {
				
				pret = tek;
				tek = tek.veza;
				
				if (Character.isDigit(tek.info)) {
					
					pret.veza = tek.veza;
					
					if (cifre.prvi == null) {
						
						cifre.prvi = tek;
						tek.veza = null;
						cifreKraj = tek;
					} else {
						
						cifreKraj.veza = tek;
						tek.veza = null;
						cifreKraj = tek;
					}
					
					tek = pret;
				}	// Character.isDigit()
			}	// while
		}	// if
		
		return cifre;
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
	
	
	public String toString() {
		
		String output = "Lista [ ";
		Element tekuci = prvi;
		
		while (tekuci != null) {
			
			output += tekuci.info + " ";
			tekuci = tekuci.veza;
		}
		
		return output + "]";
	}
}
