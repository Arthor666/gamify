package com.gamificacion.demo.Enums;

public enum ConstantsEnum {
	PATHROOT ("/home/arturobriones115/gamify/"),
	STATUSPORCOBRAR ("Por Cobrar"),
	STATUSFINALIZADO ("Finalizada");
	
    private final String name;       

    private ConstantsEnum(String s) {
        name = s;
    }
    
    public String toString() {
        return this.name;
     }
	
}
