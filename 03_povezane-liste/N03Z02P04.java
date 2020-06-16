/** 
 * Napraviti klasu koja predstavlja jednostruko povezanu listu znakova
 * (char). Koristiti najjednostavnije dodavanje na početak. Napisati p-
 * rogram koji učitava n znakova i smešta ih u instancu napravljene kl-
 * ase. U klasu dodati metode koji omogućavaju sledeće operacije i poz-
 * vati ih iz glavnog programa:
 * 
 * a) štampanje samo znakova koji su velika slova
 * b) izbacivanje svih znakova koji su mala slova
 * c) izdvajanje u novu listu svih znakova koji su cifre. Rezultat - n-
 *    ov objekat istog tipa lista, koristi se ista memorija za elemente
 *    a elementi u novoj listi treba da su u istom redosledu kao u ori-
 *    ginalnoj listi.
 */

class N03Z02P04 {
	
	public static void main(String[] args) {
		
		Lista lista = new Lista();
		
		int n = Svetovid.in.readInt("Elemenata: ");
		
		for (int i = 0; i < n; i++) {
			
			char c = Svetovid.in.readChar("Element #" + i + ": ");
			lista.dodajNaPocetak(c);
		}
		
		System.out.println(lista);
		
		System.out.println(lista.stampajVelika());
		
		lista.izbaciMala();
		System.out.println(lista);
		
		Lista cifre = lista.izdvojCifre();
		
		System.out.println(cifre);
	}
}

class Lista {
	
	class Element {
		
		char info;
		Element veza;
		
		
		public Element(char info) {
			this.info = info;
			this.veza = null;
		}
		
		
		public String toString() {
			return info + "";
		}
	}
	
	
	Element prvi;
	
	
	public void dodajNaPocetak(char info) {
		
		Element novi = new Element(info);
		
		novi.veza = prvi;
		prvi = novi;
	}
	
	
	public void izbaciMala() {
		
		if (prvi == null)
			return;
			
		while ('a' <= prvi.info && prvi.info <= 'z')
			prvi = prvi.veza;
			
		Element pret = prvi;
		Element pom;
		
		while (pret.veza != null) {
			
			if ('a' <= pret.veza.info && pret.veza.info <= 'z')
				pret.veza = pret.veza.veza;
			else
				pret = pret.veza;
		}
	}
	
	
	public Lista izdvojCifre() {
		
		if (prvi == null)
			return null;
			
		Lista cifre = new Lista();	
		
		Element tek = prvi;
		Element pom;
		
		while (tek != null) {
			
			if (Character.isDigit(tek.info)) {
				
				pom = tek.veza;
				
				tek.veza = cifre.prvi;
				cifre.prvi = tek;
				
				tek = pom;
			} else {
				
				tek = tek.veza;
			}
		}
		
		return cifre;
	}
	
	
	public String stampajVelika() {
		
		String output = "[ Lista (samo velika slova) : ";
		
		Element tek = prvi;
		
		while (tek != null) {
			
			if ('A' <= tek.info && tek.info <= 'Z')
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
