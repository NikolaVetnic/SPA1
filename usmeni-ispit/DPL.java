class DPL {
	
	class Element {
		
		int info;
		Element pret, sled;
		
		
		public Element(int info) {
			this.info = info;
			this.pret = null;
			this.sled = null;
		}
		
		
		public String toString() {
			return "" + info;
		}
	}
	
	
	Element prvi, posl;
	
	
	public void dodajNaPocetak(int info) {
		
		Element novi = new Element(info);
		
		if (prvi == null) {
			
			prvi = novi;
			posl = novi;
		} else {
			
			novi.sled = prvi;
			prvi.pret = novi;
			prvi = novi;
		}
	}
	
	
	public void dodajNaKraj(int info) {
		
		Element novi = new Element(info);
		
		if (posl == null) {
			
			prvi = novi;
			posl = novi;
		} else {
			
			novi.pret = posl;
			posl.sled = novi;
			posl = novi;
		}
	}
	
	
	public void obrisiPrvi() {
		
		if (prvi == null)
			return;
			
		if (prvi == posl) {
			
			prvi = null;
			posl = null;
		} else {
			
			prvi = prvi.sled;
			prvi.pret = null;
			return;
		}
	}
	
	
	public void obrisiPoslednji() {
		
		if (posl == null)
			return;
			
		if (prvi == posl) {
			
			prvi = null;
			posl = null;
		} else {
			
			posl = posl.pret;
			posl.sled = null;
			return;
		}
	}
	
	
	public void obrisiElement(int info) {
		
		if (prvi == null)
			return;
			
		if (prvi.info == info) {
			
			prvi = prvi.sled;
			prvi.pret = null;
			return;
		}
		
		Element pret = prvi;
		
		while (pret.sled != null && pret.info != info)
			pret = pret.sled;
			
		if (pret.sled != null && pret.info == info) {
			
			Element pom = pret.sled.sled;
			pret.sled = pom;
			
			if (pom == null)
				posl = pret;
			else
				pom.pret = pret;
		}
	}
	

	public Element nadjiElement(int info) {
		
		if (prvi == null)
			return null;
			
		Element tek = prvi;
		
		while (tek != posl && tek.info != info)
			tek = tek.sled;
			
		if (tek.info == info)
			return tek;
		else
			return null;
	}


	public void stampajOdPoslednjeg() {
		
		String output = "[ Lista (unazad) : ";
		
		if (posl == null)
			System.out.println(output + "]");
		
		Element tek = posl;
		
		while (tek != null) {
			
			output += tek + " ";
			tek = tek.pret;
		}
		
		System.out.println(output + "]");
	}
	
	
	public void stampajOdPrvog() {
		System.out.println(this);
	}
	
	
	public String toString() {
		
		String output = "[ Lista : ";
		
		if (prvi == null)
			return output + "]";
			
		Element tek = prvi;
		
		while (tek != null) {
			
			output += tek + " ";
			tek = tek.sled;
		}
		
		return output + "]";
	}
}
