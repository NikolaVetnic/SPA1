/**
 * U fajlu je spisak čestih imena, učitati ih i smestiti ih u listu st-
 * ringova; proveriti da li je uneto ime u listi; dodati ako nije; sni-
 * miti promenjenu listu u novi fajl. Ovaj zadatak je već bio dat na p-
 * rvim vežbama, samo se sada kao struktura podataka za smeštanje imena
 * koristi jednostruko povezana lista.
 */

class N03Z04P04 {
	
	public static void main(String[] args) {
		
		String imeFajla, ime;
		
		Lista lista = new Lista();
		
		imeFajla = Svetovid.in.readLine("Ucitaj fajl: ");		
		lista.ucitajFajl(imeFajla);
		System.out.println(lista);
		
		ime = Svetovid.in.readLine("Ime za pretragu: ");
		
		if (lista.nadjiIme(ime) != null)
			System.out.println("Ime je u listi.");
		else
			lista.dodajNaPocetak(ime);
			
		imeFajla = Svetovid.in.readLine("Sacuvaj fajl: ");
		lista.snimiFajl(imeFajla);
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
		} else {
			
			return;
		}
	}
	
	
	public void snimiFajl(String imeFajla) {
		
		Element tek = prvi;
		
		while (tek != null) {
			
			Svetovid.out(imeFajla).println(tek);
			tek = tek.veza;
		}
		
		Svetovid.in(imeFajla).close();
	}
	
	
	public Element nadjiIme(String info) {
		
		if (prvi == null)
			return null;
			
		Element tek = prvi;
		
		while (tek != null) {
			
			if (tek.info.equals(info))
				return tek;
				
			tek = tek.veza;
		}
		
		return null;
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
