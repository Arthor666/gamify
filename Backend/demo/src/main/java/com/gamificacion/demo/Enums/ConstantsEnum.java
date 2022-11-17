package com.gamificacion.demo.Enums;

public enum ConstantsEnum {
	PATHROOT ("D:/gamify/uploads/"),
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
