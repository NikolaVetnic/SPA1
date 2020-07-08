class KruznaListaSaGranicnikom {
	
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
	
	
	Element gran;
	
	
	public KruznaListaSaGranicnikom() {
		this.gran = new Element(-1);
		this.gran.veza = this.gran;
	}
	
	
	public void dodajNaPocetak(int info) {
		
		Element novi = new Element(info);
		
		novi.veza = gran.veza;
		gran.veza = novi;
	}
	
	
	public void dodajNaKraj(int info) {
		
		Element novi = new Element(-1);
		
		novi.veza = gran.veza;
		gran.veza = novi;
		gran.info = info;
		gran = novi;
	}
	
	
	public void obrisiPrvi() {
		
		if (gran == null)
			return;
			
		if (gran.veza == gran.veza.veza) {
			
			gran = null;
			return;
		}
			
		gran.veza = gran.veza.veza;
	}
	
	
	public void obrisiPoslednji() {
		
		if (gran == null)
			return;
			
		if (gran.veza == gran.veza.veza) {
			
			gran = null;
			return;
		}
		
		Element pret = gran.veza;
		
		while (pret.veza.veza != gran)
			pret = pret.veza;
			
		pret.veza = gran;
	}
	
	
	public void obrisiElement(int info) {
		
		if (gran == null)
			return;
			
		if (gran.veza.info == info) {
			
			gran.veza = gran.veza.veza;
			return;
		}
		
		Element pret = gran.veza;
		
		while (pret.veza != gran && pret.veza.info != info)
			pret = pret.veza;
			
		if (pret.veza.info == info) {
			
			if (pret.veza != gran) {
				
				pret.veza = pret.veza.veza;
			} else {
				
				pret.veza = pret.veza.veza;
				gran = pret;
			}
		}
	}
	
	
	public Element nadjiElement(int info) {
		
		if (gran == gran.veza)
			return null;
			
		Element tek = gran.veza;
		
		while (tek != gran && tek.info != info)
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
		
		if (gran == gran.veza) {
			
			System.out.println(output + "]");
			return;
		}
		
		Element pret = gran, tek = gran.veza, sled = null;
		
		while (tek != gran) {
			
			sled = tek.veza;
			tek.veza = pret;
			pret = tek;
			tek = sled;
		}
		
		tek = pret;
		pret = gran;
		
		while (tek != gran) {
			
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
		
		if (gran == null)
			return output + "]";
			
		Element tek = gran.veza;
		
		while (tek != gran) {
			
			output += tek + " ";
			tek = tek.veza;
		}
		
		return output + "]";
	}
}
