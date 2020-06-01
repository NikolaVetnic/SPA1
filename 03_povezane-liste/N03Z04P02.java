/**
 * U fajlu je spisak čestih imena, učitati ih i smestiti ih u listu st-
 * ringova; proveriti da li je uneto ime u listi; dodati ako nije; sni-
 * miti promenjenu listu u novi fajl. Ovaj zadatak je već bio dat na p-
 * rvim vežbama, samo se sada kao struktura podataka za smeštanje imena
 * koristi jednostruko povezana lista.
 */

class N03Z04P02 {
	
	public static void main(String[] args) {
		
		String imeFajla, ime;
		
		imeFajla = Svetovid.in.readLine("Fajl za ucitavanje: ");
		
		Lista lista = new Lista();
		lista.ucitajFajl(imeFajla);
		
		ime = Svetovid.in.readLine("Ime za proveru: ");
		
		if (lista.nadjiIme(ime) != null)
			System.out.println("Ime je na listi.");
		else
			lista.dodajNaPocetak(ime);
		
		imeFajla = Svetovid.in.readLine("Fajl za cuvanje: ");
		
		lista.snimiFajl(imeFajla);
	}
	
}

class Lista {
	
	class Element {
		
		String ime;
		Element veza;
		
		
		public Element(String ime) {
			this.ime = ime;
			this.veza = null;
		}
		
		
		public String toString() {
			return ime;
		}
	}
	
	
	Element prvi;
	
	
	public Lista() {
		this.prvi = null;
	}
	
	
	public void dodajNaPocetak(String ime) {
		
		Element novi = new Element(ime);
		
		novi.veza = prvi;
		prvi = novi;
	}
	
	
	public Element nadjiIme(String ime) {
		
		if (prvi == null)
			return null;
			
		Element tek = prvi;
		
		while (tek != null) {
			
			if (tek.ime.equals(ime))
				return tek;
			
			tek = tek.veza;
		}
		
		return null;
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
