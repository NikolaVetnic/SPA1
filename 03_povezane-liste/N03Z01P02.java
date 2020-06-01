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

class N03Z01P02 {
	
	public static void main(String[] args) {
		
		Lista lista = new Lista();
		
		lista.dodajNaPocetak("ananas");
		lista.dodajNaPocetak("caj");
		lista.dodajNaPocetak("kokos");
		lista.dodajNaPocetak("banana");
		
		System.out.println(lista);
		
		System.out.println(lista.duziOdSlova(3));
		
		System.out.println(lista.nadjiPoslednji());
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
	
	
	public Element nadjiPoslednji() {
		
		if (prvi == null)
			return null;
			
		Element pret = prvi;
		
		while (pret.veza != null)
			pret = pret.veza;
			
		return pret;
	}
	
	
	public String duziOdSlova(int n) {
		
		String output = "[ Duzi od " + n + " slova : ";
		
		Element tek = prvi;
		
		while (tek != null) {
			
			if (tek.info.length() > n)
				output += tek + " ";
				
			tek = tek.veza;
		}
		
		return output + "]";
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
