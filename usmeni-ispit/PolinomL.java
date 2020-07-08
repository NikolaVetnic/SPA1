class PolinomL {
	
/**
 * Sve metode osim ubaciMonom(), postaviKoeficijent() i kolicnik() raz-
 * likuju se od profesorove klase, pre svega u tome da ne koriste izuz-
 * etke (koji u momentu nastave na SPA1 jos nisu obradjeni) ali i u to-
 * me da je ovaj nacin resavanja mozda jasniji studentu prve godine ia-
 * ko mozda malo manje optimizovano (sto je u redu, jer profesor oko t-
 * oga ne pravi problem).
 */
	
	class Monom {
		
		double k;
		int st;
		Monom veza;
		
		
		public Monom(double k, int st) {
			this.k = k;
			this.st = st;
			this.veza = null;
		}
		
		
		public Monom() {
			this.k = 1.0;
			this.st = 0;
			this.veza = null;
		}
		
		
		public String toString() {
			
			if (k == 0.0)
				return "0.0";
				
			String output = "";
			
			if (k < 0.0)
				output += "-";
			
			output += Math.abs(k);
			
			if (st == 0)
				return output;
			else if (st == 1)
				return output + "x";
			else
				return output + "x^" + st;
		}
	}
	
	
	Monom prvi;
	
	
	public PolinomL() {
		this.prvi = null;
	}
		
	
	public String toString() {
		
		if (prvi == null)
			return "0.0";
			
		String output = "" + prvi;
		
		Monom tek = prvi.veza;
		
		while (tek != null) {
			
			if (tek.k > 0.0)
				output += "+" + tek;
			else
				output += tek;
			
			tek = tek.veza;
		}
		
		return output;
	}

	
	public PolinomL kopija() {
		
		PolinomL kop = new PolinomL();
		
		if (prvi == null)
			return kop;
			
		kop.prvi = new Monom(prvi.k, prvi.st);
		
		Monom tek = kop.prvi;
		Monom pom = prvi.veza;
		
		while (pom != null) {
			
			tek.veza = new Monom(pom.k, pom.st);
			
			tek = tek.veza;
			pom = pom.veza;
		}
		
		return kop;
	}
	
	
	public void ubaciMonom(Monom mon) {
		
		if (mon == null)
			return;
			
		if (prvi == null) {
			
			prvi = new Monom(mon.k, mon.st);
			return;
		}
		
		Monom pret = null;
		Monom tek = prvi;
		
		while (tek != null && tek.st > mon.st) {
			
			pret = tek;
			tek = tek.veza;
		}
		
		if (tek != null && tek.st == mon.st) {
			
			tek.k += mon.k;
			
			if (tek.k == 0) {
				
				if (tek == prvi)
					prvi = prvi.veza;
				else
					pret.veza = tek.veza;
			}
		} else {
			
			Monom novi = new Monom(mon.k, mon.st);
			novi.veza = tek;
			
			if (tek == prvi)
				prvi = novi;
			else
				pret.veza = novi;
		}
	}
	
	
	public void postaviClan(double k, int st) {
		
		Monom pret = null;
		Monom tek = prvi;
		
		while (tek != null && tek.st > st) {
			
			pret = tek;
			tek = tek.veza;
		}
		
		
		if (k == 0.0) {
			
			if (tek != null && tek.st == st) {
				
				if (tek == prvi)
					prvi = prvi.veza;
				else
					pret.veza = tek.veza;
			}
		} else {
			
			if (tek != null && tek.st == st) {
				
				tek.k = k;
			} else {
			
				Monom novi = new Monom(k, st);
				
				if (tek == prvi) {
					
					novi.veza = prvi;
					prvi = novi;
				} else {
					
					novi.veza = tek;
					pret.veza = novi;
				}
			}
		}
	}

	
	public double koeficijentUz(int st) {
		
		if (prvi == null)
			return 0.0;
			
		Monom tek = prvi;
		
		while (tek != null && tek.st > st)
			tek = tek.veza;
			
		if (tek != null)
			return tek.k;
		else
			return 0.0;
	}

	
	public int maksimalniStepen() {
		
		if (prvi == null)
			return 0;
		else
			return prvi.st;
	}

	
	public void unos() {
		
		int n;
		do {
			n = Svetovid.in.readInt("Koliko monoma: ");
		} while (n <= 0);
		
		for (int i = 0; i < n; i++) {
			
			double k;
			do {
				k = Svetovid.in.readDouble("Monom #" + i + ", k: ");
			} while (k == 0.0);
			
			int st;
			do {
				st = Svetovid.in.readInt("Monom #" + i + ", st: ");
			} while (st < 0);
			
			ubaciMonom(new Monom(k, st));
		}
	}


	public PolinomL saberi(PolinomL sab) {
		
		PolinomL rez = kopija();
		
		if (sab == null)
			return rez;
			
		Monom tek = sab.prvi;
		
		while (tek != null) {
			
			rez.ubaciMonom(tek);
			tek = tek.veza;
		}
		
		return rez;
	}
	
	
	public PolinomL oduzmi(PolinomL sab) {
		
		PolinomL rez = kopija();
		
		if (sab == null)
			return rez;
			
		Monom tek = sab.prvi;
		
		while (tek != null) {
			
			tek.k = -tek.k;
			
			rez.ubaciMonom(tek);
			tek = tek.veza;
		}
		
		return rez;
	}
	
	
	public void saberiNa(PolinomL sab) {
		
		if (sab == null)
			return;
			
		Monom tek = sab.prvi;
		
		while (tek != null) {
			
			ubaciMonom(tek);
			tek = tek.veza;
		}
	}
	
	
	public PolinomL monomPuta(Monom mon) {
		
		if (prvi == null)
			return null;
		
		if (mon == null) {
			
			PolinomL rez = new PolinomL();
			rez.prvi = new Monom();
			
			return rez;
		}
		
		PolinomL rez = kopija();
		Monom tek = rez.prvi;
		
		while (tek != null) {
			
			tek.k *= mon.k;
			tek.st += mon.st;
			
			tek = tek.veza;
		}
		
		return rez;
	}

	
	public PolinomL puta(PolinomL pol) {
		
		if (prvi == null)
			return null;
			
		if (pol == null || pol.prvi == null) {
			
			PolinomL rez = new PolinomL();
			rez.prvi = new Monom();
			
			return rez;
		}
		
		PolinomL rez = new PolinomL();
		
		Monom tek = pol.prvi;
		
		while (tek != null) {
			
			Monom pom = prvi;
			
			while (pom != null) {
				
				rez.ubaciMonom(new Monom(tek.k * pom.k, tek.st + pom.st));
				pom = pom.veza;
			}
			
			tek = tek.veza;
		}
		
		return rez;
	}

	
	public PolinomL[] kolicnik(PolinomL del) {
		
		if (del == null)
			return null;
			
		PolinomL ost = kopija();
		PolinomL kol = new PolinomL();
		
		while (ost.prvi != null && ost.prvi.st > del.prvi.st) {
			
			Monom novi = new Monom();
			novi.k = -ost.prvi.k / del.prvi.k;
			novi.st = ost.prvi.st - del.prvi.st;
			
			PolinomL pom = del.monomPuta(novi);
			ost.saberiNa(pom);
			
			novi.k = -novi.k;
			kol.ubaciMonom(novi);
		}
		
		return new PolinomL[] { kol, ost };
	}

	
	public PolinomL naN(int n) {
		
		if (prvi == null)
			return null;
		
		if (n == 0) {
			
			PolinomL rez = new PolinomL();
			rez.prvi = new Monom();
			
			return rez;
		}
		
		PolinomL rez = kopija();
		
		for (int i = 2; i <= n; i++) {
			
			rez = rez.puta(this);
		}
		
		return rez;
	}
}
