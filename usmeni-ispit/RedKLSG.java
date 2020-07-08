class RedKLSG {
	
	SLLNode header;
	
	
	public Red() {
		this.header = new SLLNode("", null);
		this.header.veza = this.header;
	}
	
	
	public boolean jePrazan() {
		return header == header.veza;
	}
	
	
	public Object prvi() {
		
		if (jePrazan())
			return null;
			
		return header.veza.element;
	}
	
	
	public void izbaciPrvi() {
		
		if (jePrazan())
			return;
			
		header.veza = header.veza.veza;
	}
	
	
	public void naKraj(Object x) {
		
		header.element = x;
				
		SLLNode novi = new SLLNode("", null);
		
		novi.veza = header.veza;
		header.veza = novi;
		
		header = novi;
	}
}
