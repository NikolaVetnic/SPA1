class StekKLSG {
	
	SLLNode header;
	
	
	public Stek() {
		this.header = new SLLNode("", null);
		this.header.veza = this.header;
	}
	
	
	public boolean jePrazan() {
		return header == header.veza;
	}
	
	
	public Object vrh() {
		
		if (jePrazan())
			return null;
			
		return header.veza.element;
	}
	
	
	public void skiniVrh() {
		
		if (jePrazan())
			return;
			
		header.veza = header.veza.veza;
	}
	
	
	public void stavi(Object x) {
		
		SLLNode novi = new SLLNode(x, null);
		
		novi.veza = header.veza;
		header.veza = novi;
	}
}
