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

class N03Z01P05 {
	
	public static void main(String[] args) {
		
		Lista lista = new Lista();
		
		int n = Svetovid.in.readInt("Broj elemenata: ");
		
		for (int i = 0; i < n; i++) {
			String s = Svetovid.in.readLine("Element #" + i + ": ");
			lista.dodajNaPocetak(s);
		}
		
		System.out.println(lista);
		
		lista.stampajDuzeOd(3);
		
		String s = Svetovid.in.readLine("Element za pretragu: ");
		lista.prebrojPojave(s);
		
		lista.stampajPoslednji();
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
	
	
	private Element nadjiElement(String info) {
		
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
	
	
	public int prebrojPojave(String input) {
		
		if (prvi == null)
			return -1;
			
		Element tek = prvi;
		int count = 0;
		
		while (tek != null) {
			
			if (tek.info.equals(input))
				count++;
				
			tek = tek.veza;
		}
		
		System.out.println("'" + input + "' se pojavljuje puta: " + count);
		return count;
	}
	
	
	public String stampajDuzeOd(int n) {
		
		if (prvi == null)
			return null;
		
		String output = "[ Lista duzih od " + n + " : ";
		
		Element tek = prvi;
		
		while (tek != null) {
			
			if (tek.info.length() > n)
				output += tek + " ";
			
			tek = tek.veza;
		}
		
		System.out.println(output + "]");
		return output + "]";
	}
	
	
	public void stampajPoslednji() {
		
		if (prvi == null)
			return;
			
		Element pret = prvi;
		
		while (pret.veza != null)
			pret = pret.veza;
			
		System.out.println(pret);
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
