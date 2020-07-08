class RedJPL {
	
	SLLNode prvi, posl;
	
	
	public Red() {
		prvi = null;
		posl = null;
	}
	
	
	public boolean jePrazan() {
		return prvi == null;
	}
	
	
	public Object prvi() {
		
		if (jePrazan())
			return null;
			
		return prvi.element;
	}
	
	
	public void izbaciPrvi() {
		
		if (jePrazan())
			return;
			
		if (prvi == posl) {
			
			prvi = null;
			posl = null;
		} else {
			
			prvi = prvi.veza;
		}
	}
	
	
	public void naKraj(Object x) {
		
		SLLNode novi = new SLLNode(x, null);
		
		if (jePrazan())
			prvi = novi;
		else
			posl.veza = novi;
		
		posl = novi;
	}
}
