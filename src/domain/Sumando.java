/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author carlos david
 */
public class Sumando extends Termino{
    private String valor;
            
    public Sumando(String valor, Signo signo){
        super(signo);
        this.valor = valor;
    }
    
    @Override
    public String toString(){
        return this.valor + "Sumando: "+this.getSigno() + "resultado: "+this.getResultado();
    }
    
    public double getResultado(){
        return Double.parseDouble(valor);
    }
    
    
    
}
