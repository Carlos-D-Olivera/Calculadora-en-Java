/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.ArrayList;

/**
 * @author carlos david
 */
public class Operacion extends Termino {

    //llamaremos operacion a cada termino que contenga multiplicacion o division
    private final String operacion;
    private final ArrayList<String> partes;
    private double resultado;

    public Operacion(String operacion, Signo signo) {
        super(signo);
        this.operacion = operacion;
        this.partes = new ArrayList();
        dividirOperacion(this.operacion);   //Dividimos la operacion en partes
        this.resultado = calcularResultado();

    }

    public String getText() {
        return this.operacion + " Operacion: " + this.signo + "Resultado: " + this.getResultado();
    }

    private double calcularResultado() {
        double result;
        double a, b;
        int numOps = 0;
        //Ya teniendola dividida por partes vamos a resolver.
        //empezamos de izq a der

        //Contamos cuanto signos hay para saber cuantas operaciones hacer
        for (String parte : this.partes) {
            if (parte.equals("/") || parte.equals("*")) {
                numOps++;
            }
        }


        //Creamos el vector de subresultados copiando el array partes
        ArrayList<String> subResultados = new ArrayList(this.partes);

        for (int i = 2; i <= numOps*2; i = i + 2) {

            if (this.partes.get(i-1).equals("*")) {
                a = Double.parseDouble(subResultados.get(i - 2));
                b = Double.parseDouble(subResultados.get(i));
                double subResultado = a * b;

                //Lo movemos a donde estaba b
                subResultados.set(i , String.valueOf(subResultado));

            } else if (this.partes.get(i-1).equals("/")) {
                a = Double.parseDouble(subResultados.get(i - 2));
                b = Double.parseDouble(subResultados.get(i));
                double subResultado = a / b;

                //Lo movemos a donde estaba b
                subResultados.set(i, String.valueOf(subResultado));
            } else {
                return -0.0001000100010001000;
            }
        }        
        
//
//        //Creamos el vector de subresultados copiando el array partes
//        ArrayList<String> subResultados = new ArrayList(this.partes);
//
//        for (int i = 1; i <= numOps + 1; i = i + 2) {
//
//            if (this.partes.get(i).equals("*")) {
//                a = Double.parseDouble(subResultados.get(i - 1));
//                b = Double.parseDouble(subResultados.get(i + 1));
//                double subResultado = a * b;
//
//                //Lo movemos a donde estaba b
//                subResultados.set(i + 1, String.valueOf(subResultado));
//
//            } else if (this.partes.get(i).equals("/")) {
//                a = Double.parseDouble(subResultados.get(i - 1));
//                b = Double.parseDouble(subResultados.get(i + 1));
//                double subResultado = a / b;
//
//                //Lo movemos a donde estaba b
//                subResultados.set(i + 1, String.valueOf(subResultado));
//            } else {
//                return -0.0001000100010001000;
//            }
//        }
        //La ultima operacion
//        if (numOps > 1) {
//            a = Double.parseDouble(subResultados.get(subResultados.size() - 3));//antepenultimo
//            b = Double.parseDouble(subResultados.get(subResultados.size() - 1));//ultimo
//            String sig = this.partes.get(this.partes.size() - 2); // el signo entre ellos
//            if (sig.equals("*")) {
//                result = a * b;
//            } else if (sig.equals("/")) {
//                result = a / b;
//            } else {
//                result = -0.0001000100010001000;
//            }
//        }else{
            result = Double.parseDouble(subResultados.get(subResultados.size()-1));
//        }
        return result;

    }

    private void dividirOperacion(String operacion) {
        int inicio = 0, fin = 0;
        //Iremos dividiendo hasta donde se encuentre un signo * o /
        for (int pos = 0; pos < operacion.length(); pos++) {

            fin = pos;

            if (operacion.charAt(pos) == '*' || operacion.charAt(pos) == '/') {
                String parte = operacion.substring(inicio, fin);
                this.partes.add(parte); //Agregamos la parte

                String signo = String.valueOf(operacion.charAt(pos));
                this.partes.add(signo);//Agregamos el signo tambien

                inicio = pos + 1; //La siguiente parte comenzara despues del signo   
            }
        }
        //Agregamos el ultimo
        this.partes.add(operacion.substring(inicio));
    }

    public double getResultado() {
        return this.resultado;
    }

}
