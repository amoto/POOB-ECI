import java.util.Stack;

public class Calculadora{
    private Stack<Polinomio> operandos;

    /**
     * Crea la calculadora
     */
    public Calculadora() {
        operandos = new Stack<Polinomio>();
    }
    
    /**
     * Elimina todos los elementos de la calculadora
     */
    public void borre () {
        operandos.clear();
    }
  
    /**
     * Adiciona un nuevo operando, si es valido
     * Los coeficientes son arreglos de enteros: {entero}, {numerador, denominador}, {entero, numerador, denominador}
     * 
     * @param coeficientes, todos los coeficientes del polinomio que se adicionará a la calculadora
     */ 
    public void adicione(int[][] coeficientes) {
        operandos.push(new Polinomio(coeficientes));
    }
    
    /**
     * Retorna el operando del tope, si existe. Cadena vacia, D.L.C.
     * 
     * @return ans, el polinomio que está en el tope de la calculadora
     */
    public String consulte() {
        String ans;
        if(operandos.size()>0){
            ans=operandos.peek().toString();
        }else{
            ans="";
        }
        return ans;
    }

    /**
     * Suma, si es posible
     */
    public void sume() {
        if(operandos.size()>1){
            Polinomio p = operandos.pop();
            Polinomio q = operandos.pop();
            operandos.push(p.sume(q));
            
        }
    }

    /**
     * Resta, si es posible
     */
    public void reste() {
        if(operandos.size()>1){
            Polinomio p = operandos.pop();
            Polinomio q = operandos.pop();
            operandos.push(p.reste(q));
        }
    }
    
    /**Multiplica, si es posible
     */
    public void multiplique() {
        if(operandos.size()>1){
            Polinomio p = operandos.pop();
            Polinomio q = operandos.pop();
            operandos.push(p.multiplique(q));
        }
    }
    
    /**Divide, si es posible
     */
    public void divide() {
        if(operandos.size()>1){
            Polinomio p = operandos.pop();
            Polinomio q = operandos.pop();
            if(p.getGrado() > q.getGrado()){
                operandos.add(p.divida(q));
            }
            else{
                operandos.add(q.divida(p));
            }
        }
    }
    
    /**
     * Deriva, si es posible
     */
    public void derive(){
        if(operandos.size()>0){
            Polinomio p = operandos.pop();
            int[][] q = new int[p.getGrado()][2];
            for(int i = 1; i < p.getGrado()+1;i++){
                q[i-1][0] = p.getCoeficiente(i).multiplique(new Fraccionario(i)).numeradorSimplificado();
                q[i-1][1] = p.getCoeficiente(i).multiplique(new Fraccionario(i)).denominadorSimplificado();
            }
            operandos.push(new Polinomio(q));
        }
    }
    
     /**
     * Integra, si es posible
     */
    public void integre(){
        if(operandos.size()>0){
            Polinomio p = operandos.pop();
            int[][] q = new int[p.getGrado()+2][2];
            q[0][0] = 0;
            q[0][1] = 1;
            for(int i = 0; i < p.getGrado()+1;i++){
                q[i+1][0] = p.getCoeficiente(i).multiplique(new Fraccionario(i+1).inverso()).numeradorSimplificado();
                q[i+1][1] = p.getCoeficiente(i).multiplique(new Fraccionario(i+1).inverso()).denominadorSimplificado();
            }
            operandos.push(new Polinomio(q));
        }
    }

}
