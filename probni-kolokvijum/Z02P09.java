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

class Z02P09 {
	
	public static void main(String[] args) {
		
		LanacHotela lanac = new LanacHotela();
		
		lanac.dodajHotel("Sheraton", "Novi Sad");
			lanac.dodajRadnika("Redzep", 4.50, 25, "Sheraton");
		lanac.dodajHotel("Sloboda", "Xabac");
			lanac.dodajRadnika("Julija", 3.50, 15, "Sloboda");
		lanac.dodajHotel("Centar", "Novi Sad");
			lanac.dodajRadnika("Istvan", 3.50, 15, "Centar");
			lanac.dodajRadnika("Milenko", 2.50, 5, "Centar");
		lanac.dodajHotel("Hilton", "Beograd");
		lanac.dodajHotel("Sheraton", "Novi Sad");
		
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
		
		String ime;
		double plata;
		int staz;
		Radnik veza;
		
		
		public Radnik(String ime, double plata, int staz) {
			this.ime = ime;
			this.plata = plata;
			this.staz = staz;
		}
		
		
		public String toString() {
			return "(" + ime + ", plata " + plata + ", staz " + staz + ")";
		}
	}
	
	
	public boolean dodajRadnika(String ime, double plata, int staz, String naziv) {
		
		Hotel cilj = nadjiHotel(naziv);
		
		if (cilj == null)
			return false;
			
		Radnik novi = new Radnik(ime, plata, staz);
		
		novi.veza = cilj.prviRadnik;
		cilj.prviRadnik = novi;
		
		return true;
	}
	
	
	public Radnik izbaciRadnika(String ime, String naziv) {
		
		Hotel cilj = nadjiHotel(naziv);
		
		if (cilj == null || cilj.prviRadnik == null)
			return null;
		
		Radnik pom;	
		
		if (cilj.prviRadnik.ime.equals(ime)) {
			
			pom = cilj.prviRadnik;
			cilj.prviRadnik = cilj.prviRadnik.veza;
			
			return pom;
		}
			
		Radnik pret = cilj.prviRadnik;
		
		while (pret.veza != null) {
			
			if (pret.veza.ime.equals(ime)) {
				
				pom = pret.veza;
				pret.veza = pret.veza.veza;
				
				return pom;
			}
			
			pret = pret.veza;
		}
		
		return null;
	}
	
	
	class Hotel {
		
		String naziv, grad;
		Radnik prviRadnik;
		Hotel veza;
		
		
		public Hotel(String naziv, String grad) {
			this.naziv = naziv;
			this.grad = grad;
			this.prviRadnik = null;
			this.veza = null;
		}
		
		
		public String toString() {
			
			String output = "[ '" + naziv + "' " + grad + " : ";
			
			Radnik tek = prviRadnik;
			
			while (tek != null) {
				
				output += tek + " ";
				tek = tek.veza;
			}
			
			return output + "]";
		}
	}
	
	
	Hotel prviHotel;
	
	
	public boolean dodajHotel(String naziv, String grad) {
		
		if (nadjiHotel(naziv) != null)
			return false;
			
		Hotel novi = new Hotel(naziv, grad);
		
		if (prviHotel == null || grad.compareTo(prviHotel.naziv) < 0) {
			
			novi.veza = prviHotel;
			prviHotel = novi;
			
			return true;
		}
		
		Hotel pret = prviHotel;
		
		while (pret.veza != null && grad.compareTo(pret.veza.naziv) >= 0)
			pret = pret.veza;
			
		novi.veza = pret.veza;
		pret.veza = novi;
		
		return true;
	}
	
	
	private Hotel nadjiHotel(String naziv) {
		
		if (prviHotel == null)
			return null;
			
		Hotel tek = prviHotel;
		
		while (tek != null) {
			
			if (tek.naziv.equals(naziv))
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
	
	
	public double prosekPlata(String grad) {
		
		if (prviHotel == null)
			return -1.0;
			
		double sum = 0.0;
		int count = 0;
		
		Hotel tek = prviHotel;
		
		while (tek != null) {
			
			if (tek.grad.equals(grad)) {
				
				Radnik rad = tek.prviRadnik;
				
				while (rad != null) {
					sum += rad.plata;
					count++;
					
					rad = rad.veza;
				}
			}
			
			tek = tek.veza;
		}
		
		System.out.println("Prosek za " + grad + ": " + (sum / count));
		return sum / count;
	}
	
	
	public Hotel obrisiHotel(String naziv) {
		
		if (prviHotel == null)
			return null;
			
		Hotel pom;
			
		if (prviHotel.naziv.equals(naziv)) {
			
			pom = prviHotel;
			prviHotel = prviHotel.veza;
			
			return pom;
		}
		
		Hotel pret = prviHotel;
		
		while (pret.veza != null) {
			
			if (pret.veza.naziv.equals(naziv)) {
				
				pom = pret.veza;
				pret.veza = pret.veza.veza;
				
				return pom;
			}
			
			pret = pret.veza;
		}
		
		return null;
	}
	
	
	public void zatvoriHotel(String naziv) {
		
		Hotel start = nadjiHotel(naziv);
		Hotel cilj = nadjiPoslednjiHotel();
		
		if (start == null)
			return;
			
		if (start.prviRadnik == null || start == cilj) {
			
			obrisiHotel(naziv);
			return;
		}
		
		Radnik tek = start.prviRadnik;
		Radnik pom;
		
		while (tek != null) {
			
			if (tek.staz > 9) {
				
				pom = tek.veza;
				
				tek.veza = cilj.prviRadnik;
				cilj.prviRadnik = tek;
				
				tek = pom;
			} else {
				
				tek = tek.veza;
			}
		}
		
		obrisiHotel(naziv);
	}
	
	
	public String toString() {
		
		String output = "LANAC HOTELA ";
		
		Hotel tek = prviHotel;
		
		while (tek != null) {
			
			output += tek + " ";
			tek = tek.veza;
		}
		
		return output + "\n";
	}
}
