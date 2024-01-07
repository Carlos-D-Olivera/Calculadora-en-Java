/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**@author carlos david*/

public class Termino {
    protected Signo signo;
    
    protected Termino(Signo signo){
        this.signo = signo;
    }
    
    public Signo getSigno(){
        return this.signo;
    }
    
    
//    
//    public double resolver(){
//        //primero se busca si hay multiplicaciones o divisiones
//        for (int i = 0; i<this.numOperaciones; i++) {
//            
//        }
//        return 
//    }
}
