/**
 * Klasa Ulica pamti listu kuća koje se nalaze u ulici. Za kuću se pam-
 * ti redni broj i vezuje se lista osoba koje žive u njoj, a lista kuća
 * je sortirana po rednim brojevima i redni brojevi su naravno jedinst-
 * veni. Za osobu se pamti lično ime kao jedan String, a osobe sa istim
 * imenom mogu živeti u istoj kući. Napraviti i testirati metode koji:
 * 1) Dodaje novu kuću sa datim brojem u listu.
 * 2) Dodaje osobu u kuću sa datim brojem. Osoba se ne može dodati u k-
 *    uću koja ne postoji, tj treba odustati od dodavanja osobe ako ku-
 *    ća ne postoji.
 * 3) Vraća broj kuće u kojoj ima najviše stanovnika sa imenom dužim od
 *    zadatog N.
 * 4) Prebacuje poslednju osobu iz date kuće A u kuću sa brojem B. Kuća
 *    može biti prazna, to jest ne treba je ukloniti ako nema više oso-
 *    ba u njoj. Ako kuća sa brojem B ne postoji izbaciti osobu iz lis-
 *    ti načisto.
 */
 
class N04Z02P04 {
	
	public static void main(String[] args) {
		
		Ulica ulica = new Ulica();
		
		ulica.dodajKucu(5);
			ulica.dodajOsobu("Dragoljub", 5);
			ulica.dodajOsobu("Mile", 5);
		ulica.dodajKucu(6);
			ulica.dodajOsobu("Jan", 6);
			ulica.dodajOsobu("Borislav", 6);
		ulica.dodajKucu(5);
		ulica.dodajKucu(9);
			ulica.dodajOsobu("Milan", 9);
			ulica.dodajOsobu("Zoltan", 9);
		
		System.out.println(ulica);
		
		ulica.prebrojOsobe(4);
		
		ulica.preseliPoslednjuOsobu(5, 9);
		System.out.println(ulica);
		
		ulica.preseliPoslednjuOsobu(5, 9);
		System.out.println(ulica);
	}
}

class Ulica {
	
	class Osoba {
		
		String ime;
		Osoba veza;
		
		
		public Osoba(String ime) {
			this.ime = ime;
			this.veza = null;
		}
		
		
		public String toString() {
			return ime;
		}
	}
	
	
	public boolean dodajOsobu(String ime, int broj) {
		
		Kuca cilj = nadjiKucu(broj);
		
		if (cilj == null)
			return false;
			
		Osoba nova = new Osoba(ime);
		
		nova.veza = cilj.ukucani;
		cilj.ukucani = nova;
		
		return true;
	}
	
	
	public int prebrojOsobe(int duz) {
		
		if (prvaKuca == null)
			return -1;
			
		Kuca tek = prvaKuca;
		
		int count = 0;
		int ind = 0;
		int max = 0;
		int maxInd = 0;
		
		while (tek != null) {
			
			count = 0;
			
			Osoba osb = tek.ukucani;
			
			while (osb != null) {
				
				if (osb.ime.length() > duz)
					count++;
				
				osb = osb.veza;
			}
			
			if (count > max)
				maxInd = ind;
			
			ind++;
			
			tek = tek.veza;
		}
		
		System.out.println(
			"Najvise ljudi imena duzeg od " + duz + " ima kuca: " + poRedu(maxInd).broj + "\n");
			
		return poRedu(maxInd).broj;
	}
	
	
	public Osoba nadjiPoslednjuOsobu(int broj) {
		
		Kuca cilj = nadjiKucu(broj);
		
		if (cilj == null || cilj.ukucani == null)
			return null;
			
		Osoba tek = cilj.ukucani;
		
		while (tek == null)
			tek = tek.veza;
			
		return tek;
	}
	
	
	public boolean obrisiOsobu(String ime, int broj) {
		
		Kuca cilj = nadjiKucu(broj);
		
		if (cilj == null || cilj.ukucani == null)
			return false;
			
		if (cilj.ukucani.ime.equals(ime)) {
			
			cilj.ukucani = cilj.ukucani.veza;
			return true;
		}
			
		Osoba pret = cilj.ukucani;
		
		while (pret.veza != null) {
			
			if (pret.veza.ime.equals(ime)) {
				pret.veza = pret.veza.veza;
				return true;
			}
			
			pret = pret.veza;
		}
		return false;
	}
	
	
	public boolean preseliPoslednjuOsobu(int broj1, int broj2) {
		
		Kuca start = nadjiKucu(broj1);
		Kuca cilj = nadjiKucu(broj2);
		
		if (start == null || start.ukucani == null)
			return false;
			
		Osoba tek = nadjiPoslednjuOsobu(broj1);
		obrisiOsobu(tek.ime, broj1);
		
		tek.veza = cilj.ukucani;
		cilj.ukucani = tek;
		return true;
	}
	
	
	class Kuca {
		
		int broj;
		Osoba ukucani;
		Kuca veza;
		
		
		public Kuca(int broj) {
			this.broj = broj;
			this.ukucani = null;
			this.veza = null;
		}
		
		
		public String toString() {
			
			String output = "[ Kuca br. " + broj + ": ";
			
			Osoba tek = ukucani;
			
			while (tek != null) {
				
				output += tek.ime + " ";
				tek = tek.veza;
			}
			
			return output + "]";
		}
	}
	
	
	Kuca prvaKuca;
	
	
	public boolean dodajKucu(int broj) {
		
		if (nadjiKucu(broj) != null)
			return false;
			
		Kuca nova = new Kuca(broj);
		
		if (prvaKuca == null || broj <= prvaKuca.broj) {
			
			nova.veza = prvaKuca;
			prvaKuca = nova;
			return false;
		}
		
		Kuca pret = prvaKuca;
		
		while (pret.veza != null && broj > pret.veza.broj)
			pret = pret.veza;
			
		nova.veza = pret.veza;
		pret.veza = nova;
		return true;
	}
	
	
	public Kuca nadjiKucu(int broj) {
		
		if (prvaKuca == null)
			return null;
			
		Kuca tek = prvaKuca;
		
		while (tek != null) {
			
			if (tek.broj == broj)
				return tek;
			
			tek = tek.veza;
		}
		
		return null;
	}
	
	
	public Kuca poRedu(int broj) {
		
		if (prvaKuca == null)
			return null;
			
		Kuca tek = prvaKuca;
		int count = 0;
		
		while (tek != null) {
			
			if (broj == count)
				return tek;
			
			count++;
			tek = tek.veza;
		}
		
		return null;
	}
	
	
	public String toString() {
		
		String output = "ULICA ";
		
		Kuca tek = prvaKuca;
		
		while (tek != null) {
			
			output += tek + " ";
			tek = tek.veza;
		}
		
		return output + "\n";
	}
}
