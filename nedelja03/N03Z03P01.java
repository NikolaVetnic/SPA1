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

class N03Z03P01 {
	
	public static void main(String[] args) {
		
		int n;
		
		ListaStringova listaStringova = new ListaStringova();
		
		n = Svetovid.in.readInt("Broj elemenata: ");
		for (int i = 0; i < n; i++) {
			String s = Svetovid.in.readLine("Element " + i + ": ");
			listaStringova.dodajNaPocetak(s);
		}
		
		System.out.println(listaStringova);
		
		listaStringova.prebrojVelike();
		System.out.println();
		
		listaStringova.prosecnaDuzina();
		System.out.println();
		
		listaStringova.brisiKrace();
		System.out.println(listaStringova);
	}
}

class ListaStringova {
	
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
	}
	
	
	Element prvi;
	int brojElemenata;
	
	
	public void dodajNaPocetak(String info) {
		
		Element novi = new Element(info);
		
		novi.veza = prvi;
		prvi = novi;
		brojElemenata++;
	}
	
	
	public void prebrojVelike() {
		
		if (prvi != null) {
			
			Element tekuci = prvi;
			
			int count = 0;
			
			while (tekuci != null) {
				char c = tekuci.info.charAt(0);
				
				if ('A' <= c && c <= 'Z') count++;
				
				tekuci = tekuci.veza;
			}
			
			System.out.println(
				"Broj elemenata koji pocinju velikim slovom: " + count);
		} else {
			
			System.out.println("Lista je prazna.");
			
		}
	}
	
	
	public void prosecnaDuzina() {
		
		if (prvi != null) {
			
			double sum = 0;
			
			Element tekuci = prvi;
			
			while (tekuci != null) {
				sum += tekuci.info.length();
				tekuci = tekuci.veza;
			}
			
			System.out.println(
				"Prosecna duzina stringova: " + (sum / brojElemenata));
		} else {
			
			System.out.println("Lista je prazna.");
			
		}
	}
	
	
	public void brisiKrace() {
		
		/**
		 * ideja je da se krecemo kroz listu sve do kraja, a posto dol-
		 * azi do izbacivanja elemenata trebace nam prethodni kao i te-
		 * kuci element; ukoliko je uslov zadovoljen prespajamo vezu p-
		 * rethodnog, u suprotnom se krecemo dalje;
		 */
		
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
