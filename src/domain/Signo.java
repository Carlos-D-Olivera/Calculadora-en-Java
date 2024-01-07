package domain;

/** @author carlos david*/

public enum Signo {
    
    POSITIVO('+'), 
    NEGATIVO('-');
    
    private final char simbolo;
    
    private Signo(char simbolo){
        this.simbolo = simbolo;
    }
    
    public char getSigno(){
        return this.simbolo;
    }
    
}
