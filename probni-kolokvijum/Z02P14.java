/**
 * Napisati klasu LanacHotela koja predstavlja jednostruko-povezanu li-
 * stu hotela u nekom lancu hotela. Svaki hotel ima ime, te grad u koj-
 * em je lociran i listu zaposlenih radnika. Svaki radnik ima ime, pla-
 * tu i godine radnog staža.
 * 
 * U listi ne smeju postojati hoteli sa istim imenom i lista mora u sv-
 * akom trenutku biti sortirana po imenu grada. Napraviti dodavanje ho-
 * tela. Napraviti dodavanje radnika u hotel. Dodati metode koji:
 * a) za hotele iz datog grada računa prosek plata svih zaposlenih rad-
 *    nika.
 * b) zatvara hotel (briše ga iz liste hotela). Radnici ovog hotela ko-
 *    ji imaju jednocifrene godine radnog staža se otpuštaju, a preost-
 *    ali se prebacuju u poslednji hotel u listi (ako je moguće). Real-
 *    izovati bez ponovnog zauzimanja memorije. 
 * 
 * Klase treba da imaju odgovarajuće toString() metode. Napisati progr-
 * am koji demonstrira napravljene metode.
 */
 
class Z02P14 {
	
	public static void main(String[] args) {
		
		LanacHotela lanac = new LanacHotela();
		
		lanac.dodajHotel("Sheraton", "Novi Sad");
			lanac.dodajRadnika("Mikan", 4.50, 15, "Sheraton");
			lanac.dodajRadnika("Perhan", 3.50, 25, "Sheraton");
		lanac.dodajHotel("Sloboda", "Xabac");
			lanac.dodajRadnika("Ivica", 4.50, 25, "Sloboda");
		lanac.dodajHotel("Hilton", "Beograd");
			lanac.dodajRadnika("Dragoslav", 5.50, 35, "Hilton");
		lanac.dodajHotel("Sheraton", "Beograd");
		lanac.dodajHotel("Centar", "Novi Sad");
			lanac.dodajRadnika("Ljuba", 2.50, 25, "Centar");
		
		System.out.println(lanac);
		
		lanac.prosekPlata("Novi Sad");
		
		lanac.zatvoriHotel("Sheraton");
		System.out.println(lanac);
		
		lanac.zatvoriHotel("Sloboda");
		System.out.println(lanac);
		
		lanac.zatvoriHotel("Hilton");
		System.out.println(lanac);
	}
}

class LanacHotela {
	
	class Radnik {
		
		String m;
		double p;
		int s;
		Radnik veza;
		
		
		public Radnik(String m, double p, int s) {
			this.m = m;
			this.p = p;
			this.s = s;
			this.veza = null;
		}
		
		
		public String toString() {
			return "(" + m + ", plata " + p + ", staz " + s + ")";
		}
	}
	
	
	public boolean dodajRadnika(String m, double p, int s, String n) {
		
		Hotel cilj = nadjiHotel(n);
		
		if (cilj == null)
			return false;
			
		Radnik novi = new Radnik(m, p, s);
		
		novi.veza = cilj.prviRadnik;
		cilj.prviRadnik = novi;
		
		return true;
	}
	
	
	class Hotel {
		
		String n, g;
		Radnik prviRadnik;
		Hotel veza;
		
		
		public Hotel(String n, String g) {
			this.n = n;
			this.g = g;
			this.prviRadnik = null;
			this.veza = null;
		}
		
		
		public String toString() {
			
			String output = "[ '" + n + "' " + g + " : ";
			
			Radnik tek = prviRadnik;
			
			while (tek != null) {
				
				output += tek + " ";
				tek = tek.veza;
			}
			
			return output + "]";
		}
	}
	
	
	Hotel prviHotel, poslHotel;
	
	
	public boolean dodajHotel(String n, String g) {
		
		if (nadjiHotel(n) != null)
			return false;
			
		Hotel novi = new Hotel(n, g);
		
		if (prviHotel == null || g.compareTo(prviHotel.g) < 0) {
			
			novi.veza = prviHotel;
			prviHotel = novi;
			
			if (novi.veza == null)
				poslHotel = novi;
		} else {
			
			Hotel pret = prviHotel;
			
			while (pret.veza != null && g.compareTo(pret.veza.g) > 0)
				pret = pret.veza;
				
			novi.veza = pret.veza;
			pret.veza = novi;
			
			if (novi.veza == null)
				poslHotel = novi;
		}
		
		return true;
	}
	
	
	private Hotel nadjiHotel(String n) {
		
		if (prviHotel == null)
			return null;
			
		Hotel tek = prviHotel;
		
		while (tek != null) {
			
			if (tek.n.equals(n))
				return tek;
			
			tek = tek.veza;
		}
		
		return null;
	}
	
	
	private Hotel obrisiHotel(String n) {
		
		if (prviHotel == null)
			return null;
		
		Hotel pom = null;	
		
		if (prviHotel.n.equals(n)) {
			
			pom = prviHotel;
			prviHotel = prviHotel.veza;
			
			return pom;
		}
		
		Hotel pret = prviHotel;
		
		while (pret.veza != null && !pret.veza.n.equals(n))
			pret = pret.veza;
		
		if (pret.veza != null) {
			
			pom = pret.veza;
			pret.veza = pret.veza.veza;
		}
		
		return pom;
	}
	
	
	public void zatvoriHotel(String n) {
		
		if (prviHotel == null)
			return;
			
		Hotel start = obrisiHotel(n);
		
		if (start == null || start.prviRadnik == null || start == poslHotel)
			return;
		
		Radnik pom;
		
		while (start.prviRadnik != null) {
			
			if (start.prviRadnik.s > 9) {
				
				pom = start.prviRadnik.veza;
				
				start.prviRadnik.veza = poslHotel.prviRadnik;
				poslHotel.prviRadnik = start.prviRadnik;
				
				start.prviRadnik = pom;
			} else {
				
				start.prviRadnik = start.prviRadnik.veza;
			}
		}
	}
	
	
	public double prosekPlata(String g) {
		
		if (prviHotel == null)
			return -1.0;
			
		Hotel tek = prviHotel;
		
		double sum = 0.0;
		int count = 0;
		
		while (tek != null) {
			
			if (tek.g.equals(g)) {
				
				Radnik rad = tek.prviRadnik;
				
				while (rad != null) {
				
					sum += rad.p;
					count++;
					
					rad = rad.veza;
				}
			}
			
			tek = tek.veza;
		}
		
		System.out.println("Prosek plata za " + g + ": " + (sum / count));
		return sum / count;
	}
	
	
	public String toString() {
		
		String output = "LANAC HOTELA ";
		
		Hotel tek = prviHotel;
		
		while (tek != null) {
			
			output += tek + " ";
			tek = tek.veza;
		}
		
		return output;
	}
}
