class KruznaLista {
	
	class Element {
		
		int info;
		Element veza;
		
		
		public Element(int info) {
			this.info = info;
			this.veza = null;
		}
		
		
		public String toString() {
			return "" + info;
		}
	}
	
	
	Element posl;
	
	
	public void dodajNaPocetak(int info) {
		
		Element novi = new Element(info);
		
		if (posl == null) {
			
			novi.veza = novi;
			posl = novi;
		} else {
			
			novi.veza = posl.veza;
			posl.veza = novi;
		}
	}
	
	
	public void dodajNaKraj(int info) {
		
		Element novi = new Element(info);
		
		if (posl == null) {
			
			novi.veza = novi;
			posl = novi;
		} else {
			
			novi.veza = posl.veza;
			posl.veza = novi;
			posl = novi;
		}
	}
	
	
	public void obrisiPrvi() {
		
		if (posl == null)
			return;
			
		if (posl == posl.veza)
			posl = null;
		else
			posl.veza = posl.veza.veza;
	}
	
	
	public void obrisiPoslednji() {
		
		if (posl == null)
			return;
			
		if (posl == posl.veza) {
			
			posl = null;
		} else {
			
			Element pret = posl.veza;
			
			while (pret.veza != posl)
				pret = pret.veza;
				
			pret.veza = pret.veza.veza;
			posl = pret;
		} 
	}
	
	
	public void obrisiElement(int info) {
		
		if (posl == null)
			return;
			
		if (posl.veza.info == info) {
			
			if (posl == posl.veza)
				posl = null;
			else
				posl.veza = posl.veza.veza;
			
			return;
		}
		
		Element pret = posl.veza;
		
		while (pret.veza != posl && pret.veza.info != info)
			pret = pret.veza;
			
		if (pret.veza.info == info) {
			
			if (pret.veza != posl) {
				
				pret.veza = pret.veza.veza;
			} else {
				
				pret.veza = pret.veza.veza;
				posl = pret;
			}
		}
	}
	
	
	public Element nadjiElement(int info) {
		
		if (posl == null)
			return null;
			
		Element tek = posl.veza;
		
		while (tek != posl && tek.info != info)
			tek = tek.veza;
			
		if (tek.info == info)
			return tek;
		else
			return null;
	}
	
	
	public void stampajOdPrvog() {
		System.out.println(this);
	}
	
	
	public void stampajOdPoslednjeg() {
		
		String output = "[ Lista (unazad) : ";
		
		if (posl == null) {
			
			System.out.println(output + "]");
			return;
		}
		
		Element pret = posl, tek = posl.veza, sled = null;
		
		while (tek != posl) {
			
			sled = tek.veza;
			tek.veza = pret;
			pret = tek;
			tek = sled;
		}
		
		output += posl + " ";
		tek = pret;
		pret = posl;
		
		while (tek != posl) {
			
			output += tek + " ";
			
			sled = tek.veza;
			tek.veza = pret;
			pret = tek;
			tek = sled;
		}
		
		System.out.println(output + "]");
	}
	
	
	public String toString() {
		
		String output = "[ Lista : ";
		
		if (posl == null)
			return output + "]";
			
		Element tek = posl.veza;
		
		while (tek != posl) {
			
			output += tek + " ";
			tek = tek.veza;
		}
		
		return output + posl + " ]";
	}
}
