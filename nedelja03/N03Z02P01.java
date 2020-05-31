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
 * 	  ov objekat istog tipa lista, koristi se ista memorija za elemente
 *    a elementi u novoj listi treba da su u istom redosledu kao u ori-
 * 	  ginalnoj listi.
 */

class N03Z02 {
	
	public static void main(String[] args) {
		ListaKaraktera listaKaraktera = new ListaKaraktera();
		
		String info;
		int n;
		
		n = Svetovid.in.readInt("Broj elemenata liste: ");
		for (int i = 0; i < n; i++) {
			char c = Svetovid.in.readChar("Karakter #" + i + ": ");
			listaKaraktera.dodajNaPocetak(c);
		}
		
		System.out.println(listaKaraktera);
		
		listaKaraktera.stampajVelikaSlova();
		
		listaKaraktera.izbaciMalaSlova();
		System.out.println(listaKaraktera);

		ListaKaraktera listaCifara = listaKaraktera.izdvojCifre();
		System.out.println(listaCifara);
		
	}
}

class ListaKaraktera {
	
	class Element {
			
		char info;
		Element veza;
		
		
		public Element(char info) {
			this.info = info;
			this.veza = null;
		}
		
		
		public String toString() {
			return info + "";
		}
	}	// unutrasnja klasa
	
	
	Element prvi;
	int brojElemenata;
	
	
	public void dodajNaPocetak(char info) {
		
		Element novi = new Element(info);
		novi.veza = prvi;
		prvi = novi;
		brojElemenata++;
	}
	
	
	public void stampajVelikaSlova() {
		
		if (prvi != null) {
			Element tekuci = prvi;
			
			System.out.print("Velika slova u listi: ");
			
			while (tekuci != null) {
				if ('A' <= tekuci.info && tekuci.info <= 'Z')
					System.out.print(tekuci + " ");
				tekuci = tekuci.veza;
			}
			System.out.println();
			
		} else {
			
			System.out.println("Lista je prazna.");
			
		}
	}
	
	
	public void izbaciMalaSlova() {
		
		if (prvi != null) {
		
			while ('a' <= prvi.info && prvi.info <= 'z')
				prvi = prvi.veza;
			
			if (prvi != null) {
				Element prethodni = prvi;
				
				while (prethodni.veza != null) {
					
					if ('a' <= prethodni.veza.info && prethodni.veza.info <= 'z') {
						prethodni.veza = prethodni.veza.veza;
					} else {
						prethodni = prethodni.veza;
					}
				}
			}
			
		} else {
			
			System.out.println("Lista je prazna.");
			
		}
	}
	
	
	public ListaKaraktera izdvojCifre() {
		
		if (prvi != null) {
		
			ListaKaraktera cifre = new ListaKaraktera();
			
			Element cifreKraj = null;
			
			Element tekuci, prethodni;
			
			/**
			 * prva provera - da li je na pocetku ulazne liste niz cifara
			 */
			
			while (prvi != null && Character.isDigit(prvi.info)) {
				
				tekuci = prvi;
				prvi = prvi.veza;
				
				if (cifre.prvi == null) {
					
					/**
					 * na prvo mesto dolazi tekuci, kraj dolazi takodje is-
					 * to na prvo mesto (buduci da postoji samo jedan elem-
					 * ent u listi trenutno), poslednji element pokazuje na
					 * null
					 */
					
					cifre.prvi = tekuci;
					cifreKraj = tekuci;
					tekuci.veza = null;
				} else {
					
					/**
					 * ako je prvo mesto popunjeno kraj liste cifara pokaz-
					 * ivace ka tekucem, tekuci ce pokazivati ka null a kr-
					 * aj cifara ce se pomeriti na tekuci
					 */
					
					cifreKraj.veza = tekuci;
					tekuci.veza = null;
					cifreKraj = tekuci;
				}
			}
			
			/**
			 * proveravamo da li smo stigli do kraja ulazne liste i ako ni-
			 * smo trazimo cifre dalje u listi
			 */
			
			if (prvi != null) {
				
				tekuci = prvi;
				
				while (tekuci.veza != null) {
					
					prethodni = tekuci;
					tekuci = tekuci.veza;
					
					if (Character.isDigit(tekuci.info)) {
						
						/**
						 * ponovo imamo dve varijante gde su cifre ili pra-
						 * zne ili nisu
						 */
						
						prethodni.veza = tekuci.veza;
						
						if (cifre.prvi == null) {
							
							cifre.prvi = tekuci;
							cifreKraj = tekuci;
							tekuci.veza = null;
						} else {
							
							cifreKraj.veza = tekuci;
							tekuci.veza = null;
							cifreKraj = tekuci;
						}
						
						/**
						 * bitan momenat - vracamo tekuci na prethodni zato
						 * sto tako proveravamo da li imamo uzastopne cifre
						 * u ulaznoj listi
						 */
						
						tekuci = prethodni;
					}
				}
			}
			
			return cifre;
			
		} else {
			
			System.out.println("Lista je prazna.");
			
			return null;
			
		}
	}	// izdvaja cifre iz originalne liste tako da se koristi ista memorija
	
	
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
