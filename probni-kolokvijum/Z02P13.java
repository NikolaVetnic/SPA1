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
 
class Z02P13 {
	
	public static void main(String[] args) {
		
		LanacHotela lanac = new LanacHotela();
		
		lanac.dodajHotel("Sheraton", "Novi Sad");
			lanac.dodajRadnika("Mikan", 4.50, 15, "Sheraton");
			lanac.dodajRadnika("Perhan", 3.50, 25, "Sheraton");
		lanac.dodajHotel("Sloboda", "Xabac");
			lanac.dodajRadnika("Ivica", 4.50, 25, "Sloboda");
		lanac.dodajHotel("Hilton", "Beograd");
		lanac.dodajHotel("Sheraton", "Beograd");
		lanac.dodajHotel("Centar", "Novi Sad");
			lanac.dodajRadnika("Ljuba", 2.50, 25, "Centar");
		
		System.out.println(lanac);
		
		lanac.prosekPlata("Novi Sad");
		
		lanac.zatvoriHotel("Sheraton");
		System.out.println(lanac);
		
		lanac.zatvoriHotel("Sloboda");
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
	
	
	Hotel prviHotel;
	
	
	public boolean dodajHotel(String n, String g) {
		
		if (nadjiHotel(n) != null)
			return false;
			
		Hotel novi = new Hotel(n, g);
		
		if (prviHotel == null || g.compareTo(prviHotel.g) < 0) {
			
			novi.veza = prviHotel;
			prviHotel = novi;
			
			return true;
		}
		
		Hotel pret = prviHotel;
		
		while (pret.veza != null && g.compareTo(pret.veza.g) > 0)
			pret = pret.veza;
			
		novi.veza = pret.veza;
		pret.veza = novi;
		
		return true;
	}
	
	
	private Hotel nadjiHotel(String n) {
		
		Hotel tek = prviHotel;
		
		while (tek != null) {
			
			if (tek.n.equals(n))
				return tek;
			
			tek = tek.veza;
		}
		
		return null;
	}
	
	
	private Hotel nadjiPoslednjiHotel() {
		
		if (prviHotel == null)
			return null;
			
		Hotel pret = prviHotel;
		
		while (pret.veza != null)
			pret = pret.veza;
			
		return pret;
	}
	
	
	public double prosekPlata(String g) {
		
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
	
	
	private boolean obrisiHotel(String n) {
		
		if (prviHotel == null)
			return false;
			
		if (prviHotel.n.equals(n)) {
			
			prviHotel = prviHotel.veza;
			return true;
		}
		
		Hotel pret = prviHotel;
		
		while (pret.veza != null && !pret.veza.n.equals(n))
			pret = pret.veza;
			
		if (pret.veza != null) {
			
			pret.veza = pret.veza.veza;
			return true;
		} else {
			
			return false;
		}
	}
	
	
	public void zatvoriHotel(String n) {
		
		Hotel start = nadjiHotel(n);
		
		if (start == null)
			return;
			
		Hotel cilj = nadjiPoslednjiHotel();
		
		if (start.prviRadnik == null || start == cilj) {
			
			obrisiHotel(n);
			return;
		}
		
		Radnik pom;
		
		while (start.prviRadnik.veza != null) {
			
			if (start.prviRadnik.s > 9) {
				
				pom = start.prviRadnik.veza;
				
				start.prviRadnik.veza = cilj.prviRadnik;
				cilj.prviRadnik = start.prviRadnik;
				
				start.prviRadnik = pom;
			} else {
				
				start.prviRadnik = start.prviRadnik.veza;
			}
		}
		
		obrisiHotel(n);
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
