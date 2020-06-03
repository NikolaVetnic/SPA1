/**
 * U fajlu je spisak čestih imena, učitati ih i smestiti ih u listu st-
 * ringova; proveriti da li je uneto ime u listi; dodati ako nije; sni-
 * miti promenjenu listu u novi fajl. Ovaj zadatak je već bio dat na p-
 * rvim vežbama, samo se sada kao struktura podataka za smeštanje imena
 * koristi jednostruko povezana lista.
 */

class N03Z04P05 {
	
	public static void main(String[] args) {
		
		String f, s;
		
		f = Svetovid.in.readLine("Fajl, ucitavanje: ");
		Lista lista = new Lista(f);
		
		System.out.println(lista);
		
		s = Svetovid.in.readLine("Ime za pretragu: ");
		
		if (lista.nadjiElement(s) != null)
			System.out.println("Ime je vec u listi.");
		else
			lista.dodajNaPocetak(s);
			
		f = Svetovid.in.readLine("Fajl, snimanje: ");
		lista.snimiFajl(f);
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
	
	
	public Lista(String f) {
		
		if (Svetovid.testIn(f)) {
			
			while (Svetovid.in(f).hasMore()) {
				
				String s = Svetovid.in(f).readLine();
				dodajNaPocetak(s);
			}
			
			Svetovid.in(f).close();
		}
	}
	
	
	public void snimiFajl(String f) {
		
		Element tek = prvi;
		
		while (tek != null) {
			
			Svetovid.out(f).println(tek);
			tek = tek.veza;
		}
		
		Svetovid.in(f).close();
	}
	
	
	public void dodajNaPocetak(String info) {
		
		Element novi = new Element(info);
		
		novi.veza = prvi;
		prvi = novi;
	}
	
	
	public Element nadjiElement(String info) {
		
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
