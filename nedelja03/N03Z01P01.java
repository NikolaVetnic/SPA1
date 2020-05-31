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

class N03Z01P01 {
	
	public static void main(String[] args) {
		
		ListaStringova listaStringova = new ListaStringova();
		
		int n = Svetovid.in.readInt("Broj elemenata liste: ");
		
		for (int i = 0; i < n; i++) {
			String input = Svetovid.in.readLine("Element #" + i + ": ");
			listaStringova.dodajNaPocetak(input);
		}
		
		System.out.println(listaStringova);
		
		listaStringova.stampajDuzeOdNSlova(3);
		
		String s = Svetovid.in.readLine("String za prebrojavanje: ");
		System.out.println("String " + s + " se u listi javlja puta: " + listaStringova.brojPojava(s));
		
		listaStringova.stampajPoslednji();
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
	}	// unutrasnja klasa
	
	
	Element prvi;
	int brojElemenata;
	
	
	public ListaStringova() {
		this.prvi = null;
		this.brojElemenata = 0;
	}
	
	
	public void dodajNaPocetak(String info) {
		
		Element novi = new Element(info);
		
		novi.veza = prvi;
		prvi = novi;
		brojElemenata++;
	}	// dodavanje novog elementa na pocetak
	
	
	public void stampajDuzeOdNSlova(int n) {
		
		/** 
		 * mislim da provera da li je lista prazna nije obavezna, doda-
		 * o sam je radi udobnosti
		 */
		
		if (prvi == null) {
			System.out.println("Lista je prazna.");
		} else {
			
			System.out.println("Elementi liste duzi od " + n + " elemenata: ");
			
			Element tekuci = prvi;
			
			while (tekuci != null) {
				if (tekuci.info.length() > 3)
					System.out.println(tekuci);
				
				tekuci = tekuci.veza;
			}
		}
	}
	
	
	public int brojPojava(String s) {
		
		if (prvi != null) {
		
			int count = 0;
			
			Element tekuci = prvi;
			
			while (tekuci != null) {
				if (tekuci.info.equals(s))
					count++;
				tekuci = tekuci.veza;
			}
			
			return count;
			
		} else {
			
			System.out.println("Lista je prazna.");
			
			return -1;
		}
	}
	
	
	public void stampajPoslednji() {
		
		if (prvi != null) {
		
			Element tekuci = prvi;
			
			while (tekuci.veza != null) tekuci = tekuci.veza;
			
			System.out.println("Poslednji element liste: " + tekuci);
			
		} else {
			
			System.out.println("Lista je prazna.");
			
		}
	}
	
	
	public String toString() {
		
		String output = "Lista [ ";
		
		Element tekuci = prvi;
		
		while (tekuci != null) {
			output += tekuci + " ";
			tekuci = tekuci.veza;
		}
		
		return output + "]";
	}

}
