/**
 * U fajlu je spisak čestih imena, učitati ih i smestiti ih u listu st-
 * ringova; proveriti da li je uneto ime u listi; dodati ako nije; sni-
 * miti promenjenu listu u novi fajl. Ovaj zadatak je već bio dat na p-
 * rvim vežbama, samo se sada kao struktura podataka za smeštanje imena
 * koristi jednostruko povezana lista.
 */

class N03Z04P01 {
	
	public static void main(String[] args) {
		
		String imeFajla = "imena.txt";
		
		ListaImena listaImena = new ListaImena();
		listaImena.procitajFajl(imeFajla);
		
		String ime = Svetovid.in.readLine("Ime za pretragu: ");
		if (!listaImena.pronadjiIme(ime)) 
			listaImena.dodajNaPocetak(ime);
		
		imeFajla = "imenamod.txt";
		listaImena.snimiFajl(imeFajla);
		
	}
	
}

class ListaImena {
	
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
	
	
	public ListaImena() {
		this.prvi = null;
		this.brojElemenata = 0;
	}
	
	
	public void dodajNaPocetak(String info) {
		
		Element novi = new Element(info);
		
		novi.veza = prvi;
		prvi = novi;
		brojElemenata++;
	}
	
	
	public void procitajFajl(String imeFajla) {
		
		if (Svetovid.testIn(imeFajla)) {
			
			while (Svetovid.in(imeFajla).hasMore()) {
				String s = Svetovid.in(imeFajla).readLine();
				dodajNaPocetak(s);
			}
			
			Svetovid.in(imeFajla).close();
			
		} else {
			System.out.println("Uneti fajl ne postoji.");
		}
	}
	
	
	public void snimiFajl(String imeFajla) {
		
		if (prvi != null) {
			
			Element tekuci = prvi;
			
			while (tekuci != null) {
				Svetovid.out(imeFajla).println(tekuci);
				tekuci = tekuci.veza;
			}
			
		} else {
			
			System.out.println("Lista je prazna.");
		}
	}
	
	
	public boolean pronadjiIme(String ime) {
		
		if (prvi != null) {
			
			Element tekuci = prvi;
			
			boolean pronadjen = false;
			
			while (tekuci != null && !pronadjen) {
				if (tekuci.info.equals(ime)) pronadjen = true;
				tekuci = tekuci.veza;
			}
			
			String s = pronadjen ? "Ime je u spisku." : "Ime nije u spisku.";
			System.out.println(s);
			
			return pronadjen;
			
		} else {
			
			System.out.println("Lista je prazna.");
			return false;
		}
	}
	
	
	public void stampajListu() {
		
		if (prvi != null) {
		
			System.out.println("Lista imena: ");
			
			Element tekuci = prvi;
			
			while (tekuci != null) {
				System.out.println(tekuci);
				tekuci = tekuci.veza;
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
		}
		
		return output + "]";
	}
	
}
