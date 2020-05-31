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

class Z02P05 {
	
	public static void main(String[] args) {
		
		LanacHotela lanac = new LanacHotela();
		
		lanac.dodajHotel("Sloboda", "Xabac");
		lanac.dodajHotel("Hilton", "Beograd");
			lanac.dodajRadnika("Milenko", 4.50, 35, "Hilton");
			lanac.dodajRadnika("Jan", 3.50, 25, "Hilton");
			lanac.dodajRadnika("Krstivojko", 2.50, 5, "Hilton");
		lanac.dodajHotel("Sheraton", "Novi Sad");
			lanac.dodajRadnika("Istvan", 2.50, 25, "Sheraton");
			lanac.dodajRadnika("Zoli", 1.50, 15, "Sheraton");
			lanac.dodajRadnika("Gugi", 0.50, 5, "Sheraton");
		lanac.dodajHotel("Hilton", "Beograd");
		lanac.dodajHotel("Centar", "Novi Sad");
			lanac.dodajRadnika("Alfred", 3.50, 15, "Centar");
			lanac.dodajRadnika("Franc", 4.50, 5, "Centar");
		
		System.out.println(lanac);
		
		lanac.prosekPlate("Novi Sad");
		
		lanac.zatvoriHotel("Sheraton");
		System.out.println(lanac);
	}
	
}

class LanacHotela{
	
	class Radnik {
		
		String ime;
		double plata;
		int staz;
		Radnik veza;
		
		
		public Radnik(String ime, double plata, int staz) {
			this.ime = ime;
			this.plata = plata;
			this.staz = staz;
			this.veza = null;
		}
		
		
		public String toString() {
			return "( " + ime + ", plata: " + plata + ", staz: " + staz + " )";
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
	
	
	public double prosekPlate(String grad) {
		
		if (prviHotel == null)
			return -1.0;
			
		Hotel tek = prviHotel;
		
		double sum = 0.0;
		int count = 0;
		
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
		
		System.out.println("Prosek za grad " + grad + ": " + (sum/count) + "\n");
		return sum / count;
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
			
			String output = "[ '" + naziv + "', " + grad + " : ";
			
			Radnik rad = prviRadnik;
			
			while (rad != null) {
				
				output += rad + " ";
				rad = rad.veza;
			}
			
			return output + "]";
		}
	}
	
	
	Hotel prviHotel;
	
	
	public boolean dodajHotel(String naziv, String grad) {
		
		if (nadjiHotel(naziv) != null)
			return false;
		
		Hotel novi = new Hotel(naziv, grad);
			
		if (prviHotel == null || grad.compareTo(prviHotel.grad) < 0) {
			
			novi.veza = prviHotel;
			prviHotel = novi;
			return true;
		}
		
		Hotel pret = prviHotel;
		
		while (pret.veza != null && grad.compareTo(pret.veza.grad) >= 0)
			pret = pret.veza;
			
		novi.veza = pret.veza;
		pret.veza = novi;
		return true;
	}
	
	
	public Hotel nadjiHotel(String naziv) {
		
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
	
	
	public Hotel nadjiPoslednjiHotel() {
		
		if (prviHotel == null)
			return null;
			
		Hotel pret = prviHotel;
		
		while (pret.veza != null)
			pret = pret.veza;
			
		return pret;
	}
	
	
	public boolean obrisiHotel(String naziv) {
		
		if (prviHotel == null)
			return false;
			
		if (prviHotel.naziv.equals(naziv)) {
			
			prviHotel = prviHotel.veza;
			return true;
		}
		
		Hotel pret = prviHotel;
		
		while (pret.veza != null) {
			
			if (pret.veza.naziv.equals(naziv)) {
				
				pret.veza = pret.veza.veza;
				return true;
			}
			pret = pret.veza;
		}
		
		return false;
	}
	
	
	public void zatvoriHotel(String naziv) {
		
		Hotel cilj = nadjiHotel(naziv);
		Hotel posl = nadjiPoslednjiHotel();
		
		if (cilj == null)
			return;
			
		if (cilj == posl || cilj.prviRadnik == null) {
			
			zatvoriHotel(cilj.naziv);
			return;
		}
		
		Radnik tek = cilj.prviRadnik;
		Radnik pom;
		
		while (tek != null) {
			
			if (tek.staz > 9) {
				
				pom = tek.veza;
				
				tek.veza = posl.prviRadnik;
				posl.prviRadnik = tek;
				
				tek = pom;
			} else {
				tek = tek.veza;
			}
		}
		
		obrisiHotel(cilj.naziv);
			
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
