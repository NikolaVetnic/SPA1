class StekJPL {
	
	SLLNode prvi;
	
	
	public Stek() {
		this.prvi = null;
	}
	
	
	public boolean jePrazan() {
		return prvi == null;
	}
	
	
	public Object vrh() {
		
		if (jePrazan())
			return null;
			
		return prvi.element;
	}
	
	
	public void skiniVrh() {
		
		if (jePrazan())
			return null;
			
		prvi = prvi.veza;
	}
	
	
	public void stavi(Object x) {
		
		SLLNode novi = new SLLNode(x, null);
		
		novi.veza = prvi;
		prvi = novi;
	}
}
