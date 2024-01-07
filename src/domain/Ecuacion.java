package domain;

import java.util.ArrayList;

/**
 * @author carlos david
 */
public class Ecuacion {

    //Esta clase almacena la ecuacion dividida en cada termino
    ArrayList<Termino> ecuacion = new ArrayList();
    String txtEcuacion;
    private final double resultado;

    public Ecuacion(String ecuacion) {
        this.txtEcuacion = ecuacion;

        //dividimos cada parte en terminos
        dividirEcuacion(ecuacion);
        this.resultado = calcularResultado();
    }

    private void dividirEcuacion(String ecuacion) {
        //cada termino empieza y termina donde hay signos + ó -
        int posI = 0, posF = 0;
        for (int i = 1; i < ecuacion.length(); i++) {

            if (ecuacion.charAt(i) == '+' || ecuacion.charAt(i) == '-') {
                posF = i;
                //creamos el termino 
                String t = ecuacion.substring(posI, posF);
                agregarTermino(t);

                //ya que creamos el terminos colocamos la posicion inicial donde
                //                      termina el termino
                posI = i;
            }
        }
        //solucion para que no falte el ultimo termino
        if (posI == posF) {
            String t = ecuacion.substring(posI);
            agregarTermino(t);
        }

    }

    public void recorrer() {
        for (Termino t : ecuacion) {
            if (t instanceof Sumando s) {
                System.out.println(s);
            } else if (t instanceof Operacion op) {
                System.out.println(op.getText());
            }
        }
    }

    private void agregarTermino(String txtTermino) {
        //si tiene * o /  es de tipo operacion sino es solo un sumando
        if (txtTermino.contains("*") || txtTermino.contains("/")) {
            //crearemos un termino de tipo operacion
            Operacion op;
            //Miramos que signo tiene
            if (txtTermino.charAt(0) == '-') {
                op = new Operacion(txtTermino, Signo.NEGATIVO);
            } else {
                op = new Operacion(txtTermino, Signo.POSITIVO);
            }
            //lo agregamos a la lista
            this.ecuacion.add(op);

        } else {
            //crearemos un termino de tipo sumando
            Sumando s;
            if (txtTermino.charAt(0) == '-') {
                s = new Sumando(txtTermino, Signo.NEGATIVO);
            } else {
                
                if(txtTermino.charAt(0) == '+'){
                    //lo agregamos sin el signo +
                    txtTermino = txtTermino.substring(1);                   
                }

                s = new Sumando(txtTermino, Signo.POSITIVO);
            }
            this.ecuacion.add(s);
        }

    }
    
    private double calcularResultado(){
        /*
        JERARQUIA DE OPERACIONES:
            PARENTESIS
            POTENCIAS Y RAICES
            MULTIPLICACIONES Y DIVISIONES  
            SUMAS Y RESTAS
        
        **PRIMERO DE IZQUIERDA A DERECHA    */
        
        //creamos un array de subresultados
        double[] subResultados = new double[this.ecuacion.size()];
        
        //Primero buscamos si hay multiplicaciones o divisiones
        double subResultado;
        for(int i=0; i<this.ecuacion.size(); i++){
            Termino t = this.ecuacion.get(i);
            if(t instanceof Operacion op){
                //Obtenemos el resultado de esta operacion
                subResultado = op.getResultado();
                //Lo colocamos en la misma posicion en el vector de subresulatados
                subResultados[i] = subResultado;
            }else if(t instanceof Sumando s){
                subResultado = s.getResultado();                
                subResultados[i] = subResultado;
            }
        }
        
        //Ahora ya podemos realizar la suma de todos y dar el resultado
        double calculo=0;
        for (int i = 0; i < subResultados.length; i++) {
            calculo += subResultados[i];
        }
        
        return calculo;
    }
    
    public double getResultado(){
        return this.resultado;
    }
}
