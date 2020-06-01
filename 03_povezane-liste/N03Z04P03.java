/**
 * U fajlu je spisak čestih imena, učitati ih i smestiti ih u listu st-
 * ringova; proveriti da li je uneto ime u listi; dodati ako nije; sni-
 * miti promenjenu listu u novi fajl. Ovaj zadatak je već bio dat na p-
 * rvim vežbama, samo se sada kao struktura podataka za smeštanje imena
 * koristi jednostruko povezana lista.
 */

class N03Z04P03 {
	
	public static void main(String[] args) {
		
		Lista lista = new Lista();
		
		lista.ucitajFajl("imena.txt");
		System.out.println(lista);
		
		String ime = Svetovid.in.readLine("Unesite ime: ");
		if (!lista.postojiIme(ime))
			lista.dodajNaPocetak(ime);
			
		lista.snimiFajl("imenamod.txt");
	}
}

class Lista {
	
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
	
	
	public void dodajNaPocetak(String info) {
		
		Element novi = new Element(info);
		
		novi.veza = prvi;
		prvi = novi;
	}
	
	
	public void ucitajFajl(String imeFajla) {
		
		if (Svetovid.testIn(imeFajla)) {
			
			while (Svetovid.in(imeFajla).hasMore()) {
				
				String s = Svetovid.in(imeFajla).readLine();
				dodajNaPocetak(s);
			}
			
			Svetovid.in(imeFajla).close();
		}
	}
	
	
	public void snimiFajl(String imeFajla) {
		
		if (prvi == null)
			return;
			
		Element tek = prvi;
		
		while (tek != null) {
			
			Svetovid.out(imeFajla).println(tek);
			tek = tek.veza;
		}
		
		Svetovid.in(imeFajla).close();
	}
	
	
	public boolean postojiIme(String info) {
		
		if (prvi == null)
			return false;
		
		Element tek = prvi;
		
		while (tek != null) {
			
			if (tek.info.equals(info))
				return true;
			
			tek = tek.veza;
		}
		
		return false;
	}
	
	
	public String toString() {
		
		String output = "[ Lista : ";
		
		Element tek = prvi;
		
		while (tek != null) {
			
			output += tek + " ";
			tek = tek.veza;
		}
		
		return output + "]";
	}
}
