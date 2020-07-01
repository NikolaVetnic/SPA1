/**
 * U fajlu je spisak čestih imena, učitati ih i smestiti ih u listu st-
 * ringova; proveriti da li je uneto ime u listi; dodati ako nije; sni-
 * miti promenjenu listu u novi fajl. Ovaj zadatak je već bio dat na p-
 * rvim vežbama, samo se sada kao struktura podataka za smeštanje imena
 * koristi jednostruko povezana lista.
 */

class N03Z04P06 {
	
	public static void main(String[] args) {
		
		// ucitavanje imena iz fajla u listu
		ListaStringova lista = listaIzFajla();
		
		// provera prisustva imena u listi i dodavanje ako nije
		String ime = Svetovid.in.readLine("Unesite ime za pretragu: ");
		if (!lista.sadrziIme(ime)) {
			
			System.out.println("Ime nije pronadjeno na listi i bice dodato na pocetak.\n");
			lista.dodajNaPocetak(ime);
		}
		
		// cuvanje liste u (novi) fajl
		lista.listaUFajl();
	}
	
	
	class Element {
		
		String info;
		Element veza;
		
		
		public Element(String input) {
			this.info = input;
		}
		
		
		public String toString() {
			return this.info;
		}
	}
	
	
	Element prvi;
	
	
	public ListaStringova() {
		this.prvi = null;
	}
	
	
	public void dodajNaPocetak(String input) {
		
		Element novi = new Element(input);
		novi.veza = prvi;
		prvi = novi;
	}
	
	
	public boolean jePrazna() {
		
		return prvi == null;
	}
	
	
	static ListaStringova listaIzFajla() {
		
		String imeFajla = Svetovid.in.readLine("Ime fajla: ");
		
		imeFajla = checkExt(imeFajla);
		
		ListaStringova lista = new ListaStringova();
		
		if (Svetovid.testIn(imeFajla)) {
			
			while (Svetovid.in(imeFajla).hasMore()) {
				
				String s = Svetovid.in(imeFajla).readToken();
				lista.dodajNaPocetak(s);
			}
			
			Svetovid.in(imeFajla).close();			// [PROF] zatvaranje fajla mora biti u okviru if grananja
		} else {
			
			System.out.println("Fajl ne sadrzi podatke - vracena je prazna lista!");
		}
		
		System.out.println();
		
		return lista;
	}
	
	
	public void listaUFajl() {
		
		String imeFajla = Svetovid.in.readLine("Ime fajla za cuvanje liste: ");
		imeFajla = checkExt(imeFajla);
		
		Element tekuci = prvi;
		
		while (tekuci != null) {
			
			Svetovid.out(imeFajla).println(tekuci.info);
			tekuci = tekuci.veza;
		}
		
		System.out.println("Trenutno stanje liste sacuvano u fajl " + imeFajla);
		
		Svetovid.out(imeFajla).close();
		System.out.println();
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
	
	
	public boolean sadrziIme(String ime) {
		
		if (prvi == null) {							// [PROF] provera da li je lista prazna
			
			System.out.println("Lista je prazna.");
			return false;
		} else {
			
			Element tekuci = prvi;
			int count = 0;
			
			while (tekuci != null) {
				
				if (tekuci.info.equals(ime))
					count++;
				tekuci = tekuci.veza;
			}
			
			return count != 0;
		}
	}
	
	
	static String checkExt(String imeFajla) {
		
		if (imeFajla.substring(imeFajla.length() - 4, imeFajla.length()).equals(".txt"))
			return imeFajla;
		else
			return imeFajla += ".txt";
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
