/**
 * Napraviti klasu koja predstavlja jednostruko povezanu listu tipa St-
 * ring. U klasu dodati metod koji:
 * a) prebrojava koliko u listi ima elemenata koji počinju velikim slo-
 *    vom.
 * b) Računa prosečnu vrednost dužine Stringova u listi.
 * c) Iz liste briše elemente koji su veći od prethodnog elementa (duž-
 *    ina Stringa trenutnog elementa je veća od dužine Stringa njegovog
 *    prethodnika), tako da na kraju imamo listu sa nerastućim element-
 *    ima.
 * 
 * Napisati program koji učitava n String-ova i smešta ih u instancu n-
 * apravljene klase. Nakon toga pozvati dodatne metode i štampati stan-
 * je pre i posle poziva po potrebi.
 */


class N03Z03P07 {
	
	public static void main(String[] args) {
		
		int brojElemenata = Svetovid.in.readInt("Broj elemenata: ");
		
		ListaStringova lista = new ListaStringova();
		
		for (int i = 0; i < brojElemenata; i++) {
			
			String s = Svetovid.in.readLine("String #" + i + ": ");
			lista.dodajNaPocetak(s);
		}
		System.out.println(lista + "\n");
		
		// a)
		System.out.println("Broj stringova koji pocinju velikim slovom: " + lista.prebroj());
		
		// b)
		System.out.println("Prosecna duzina stringova: " + String.format("%.2f", lista.prosecnaDuzina()));
		
		// c)
		lista.brisiVece();
		System.out.println(lista + "\n");
	}
	
	
	class Element {
		
		String info;
		Element veza;
		
		
		public Element(String info) {
			this.info = info;
			this.veza = null;
		}
		
		
		public String toString() {
			return info;
		}
	}	// unutrasnja klasa
	

	Element prvi;
	int brojElemenata;
	

	public ListaStringova() {
		
		prvi = null;
		brojElemenata = 0;
	}	// konstruktor
	
	
	public void dodajNaPocetak(String info) {
		
		Element novi = new Element(info);
		
		novi.veza = prvi;
		prvi = novi;
		brojElemenata++;
	}	// dodajNaPocetak
	

	public int prebroj() {
		
		if (prvi == null) {
			
			return 0;
		} else {
			
			Element tekuci = prvi;
			int count = 0;
			
			while (tekuci != null) {
				
				if ('A' <= tekuci.info.charAt(0) && tekuci.info.charAt(0) <= 'Z')
					count++;
					
				tekuci = tekuci.veza;
			}
			
			return count;
		}
	}	// prebrojava stringove koji pocinju velikim slovom
	

	public double prosecnaDuzina() {
		if (prvi == null) {
			
			return 0;
		} else {
			
			Element tekuci = prvi;
			double sum = 0.0;
			
			while (tekuci != null) {
				
				sum += tekuci.info.length();
				tekuci = tekuci.veza;
			}
			
			return sum / brojElemenata;
		}
	}	// racuna prosecnu duzinu stringova u listi
	

	public void brisiVece() {
		
		if (prvi != null) {
			
			Element prethodni = prvi;
			
			while (prethodni.veza != null) {
				
				if (prethodni.veza.info.length() > prethodni.info.length())
					prethodni.veza = prethodni.veza.veza;
				else
					prethodni = prethodni.veza;
			}
		} else {
			
			System.out.println("Lista je prazna.");
		}
	}	// brise elemente iz liste koji su veci od svog prethodnika
	

	public String toString() {
		
		String output = "[ ";
		Element tekuci = prvi;
		
		while (tekuci != null) {
			
			output += tekuci.info + " ";
			tekuci = tekuci.veza;
		}
		
		return output + "]";
	}	// toString
}
