
import java.util.*;
import java.math.*;


public class Polinomio{

  private ArrayList<Fraccionario> coeficientes;

  /**
   * Construye un polinomio dado sus coeficientes
   * 
   * @param coeficientes, los coeficientes del polinomio
   */
  public Polinomio(int[][] coeficients) {
      coeficientes=new ArrayList<Fraccionario>();
      for(int i=0;i<coeficients.length;i++){
          if(coeficients[i].length==1){
              coeficientes.add(new Fraccionario(coeficients[i][0]));
          }else if (coeficients[i].length==2 && coeficients[i][1]!=0){
              coeficientes.add(new Fraccionario(coeficients[i][0],coeficients[i][1]));
          }else if (coeficients[i].length==3 && coeficients[i][2]!=0){
              coeficientes.add(new Fraccionario(coeficients[i][0],coeficients[i][1],coeficients[i][2]));
          }
      }              
  }

  /**
   *Retorna el grado del polinomio
   *
   *@return i, el grado del polinomio, es decir,el ultimo exponente con un coeficiente diferente de 0
   */
  public int getGrado(){
      int i=coeficientes.size()-1;
      while(coeficientes.get(i).numeradorSimplificado()==0 && i>0){
          i--;
      }
      return i;
  }

  /**
   *Retorna el coeficiente correspondiente a x**n
   *
   *@param n, el exponente del que se desea conocer el coeficiente, 0<=n<=grado del polinomio
   *@return el coeficiente solicitado
   */
  public Fraccionario getCoeficiente(int n){
    return coeficientes.get(n);
  }

  /**
  * Retorna el valor del polinomio evaluado en el valor x
  * 
  * @param x, el valor con el que se evaluará el polinomio
  * @return el valor de evaluar el polinomio con x
  */
  public Fraccionario demeValor(int x){
      Fraccionario suma=new Fraccionario(0);
      for(int i=0;i<coeficientes.size();i++){
          suma= suma.sume(coeficientes.get(i).multiplique(new Fraccionario((int)Math.pow(x,i))));
      }
      return suma;
  }

  /**
  * Verifica si el polinomio es cero
  * 
  * @return true si el valor del polinomio es 0, d.l.c. false
  */
  public boolean esCero(){
      boolean todosCeros = coeficientes.size()>0;
      for(int i = 0; i < coeficientes.size() && todosCeros; i++){
          todosCeros = todosCeros && coeficientes.get(i).numeradorSimplificado()==0;
      }
      return todosCeros || coeficientes.size()<1;
  }

  /**
  * Calcula el resultado de sumar a <b>este</b> polimonio el polinomio <b>p</b>
  * 
  * @param p, el polinomio con el cual se sumara este
  * @return el polinomio resultante de sumar este y p
  */

   public Polinomio sume(Polinomio p) {
       Polinomio menor;
       Polinomio mayor;
       int[][] coeficientesSuma;
       if(getGrado()<p.getGrado()){
           menor=this;
           mayor=p;
       }else{
           menor=p;
           mayor=this;
       }
       coeficientesSuma=new int[mayor.getGrado()+1][2];
       for(int i=0;i<menor.getGrado()+1;i++){
           coeficientesSuma[i][0]=menor.getCoeficiente(i).sume(mayor.getCoeficiente(i)).numeradorSimplificado();
           coeficientesSuma[i][1]=menor.getCoeficiente(i).sume(mayor.getCoeficiente(i)).denominadorSimplificado();
       }
       for(int i=menor.getGrado()+1;i<mayor.getGrado()+1;i++){
           coeficientesSuma[i][0]=mayor.getCoeficiente(i).numeradorSimplificado();
           coeficientesSuma[i][1]=mayor.getCoeficiente(i).denominadorSimplificado();
       }
       return new Polinomio(coeficientesSuma);
   }

  /**
  * Calcula el resultado de restar a <b>este</b> polimonio el polinomio <b>p</b>
  * 
  * @param p, el polinomio que se le restara a este
  * @return el polinomio resultante de restarle p a este
  */
   public Polinomio reste(Polinomio p){
      Polinomio menor;
      Polinomio mayor;
      if(getGrado()<p.getGrado()){
          menor=this;
          mayor=p;
      }else{
          menor=p;
          mayor=this;
      }
      int[][] coefic=new int[mayor.getGrado()+1][2];
      if(mayor==this){
          for(int i=0;i<menor.getGrado()+1;i++){
              coefic[i][0]=mayor.getCoeficiente(i).sume(menor.getCoeficiente(i).reciproco()).numeradorSimplificado();
              coefic[i][1]=mayor.getCoeficiente(i).sume(menor.getCoeficiente(i).reciproco()).denominadorSimplificado();
          }
          for(int i=menor.getGrado()+1;i<mayor.getGrado()+1;i++){
              coefic[i][0]=mayor.getCoeficiente(i).numeradorSimplificado();
              coefic[i][1]=mayor.getCoeficiente(i).denominadorSimplificado();
          }
      }
      else{
          for(int i=0;i<menor.getGrado()+1;i++){
              coefic[i][0]=menor.getCoeficiente(i).sume(mayor.getCoeficiente(i).reciproco()).numeradorSimplificado();
              coefic[i][1]=menor.getCoeficiente(i).sume(mayor.getCoeficiente(i).reciproco()).denominadorSimplificado();
          }
          for(int i=menor.getGrado()+1;i<mayor.getGrado()+1;i++){
              coefic[i][0]=mayor.getCoeficiente(i).reciproco().numeradorSimplificado();
              coefic[i][1]=mayor.getCoeficiente(i).reciproco().denominadorSimplificado();
          }
      }
      return new Polinomio(coefic);
  }

  /**
  * Calcula el resultado de multiplicar a <b>este</b> polimonio con el polinomio <b>p</b>
  * 
  * @param p, el polinomio que se multiplicara con este
  * @return el polinomio resultante de multiplicar este y p
  */
   public Polinomio multiplique(Polinomio p){
       Fraccionario[] producto=new  Fraccionario[getGrado()+p.getGrado()+1];
       Arrays.fill(producto,new Fraccionario(0));
       for(int i=0;i<getGrado()+1;i++){
           for(int j=0;j<p.getGrado()+1;j++){
               producto[i+j]=producto[i+j].sume(coeficientes.get(i).multiplique(p.getCoeficiente(j)));
           }
       }
       int[][] coefProducto=new int[producto.length][2];
       for(int i=0;i<producto.length;i++){
           coefProducto[i][0]=producto[i].numeradorSimplificado();
           coefProducto[i][1]=producto[i].denominadorSimplificado();
       }
       return new Polinomio(coefProducto);
   }
    
  /**
  * Calcula el resultado de dividir <b>este</b> polimonio entre el polinomio <b>p</b>  este/p
  * 
  * @param p, el polinomio entre el que se va a dividir este, 0<p.getGrado()<=este.getGrado()
  * @return el cociente de dividir este polinomio entre p
  */
   public Polinomio divida(Polinomio p){
      int respuesta[][];
      if(getGrado()>p.getGrado() && !p.esCero()){
          int matriz[][] = new int [getGrado()+1][2];
          for(int i = 0; i < getGrado()+1; i++){
              matriz[i][0] = getCoeficiente(i).numeradorSimplificado();
              matriz[i][1] = getCoeficiente(i).denominadorSimplificado();
          }
          Polinomio copia = new Polinomio(matriz);
          int k = copia.getGrado()-p.getGrado();
          int temporal[][] = new int [copia.getGrado()-p.getGrado()+1][2];
          respuesta = new int [copia.getGrado()-p.getGrado()+1][2];
          for(int i = 0; i < matriz.length ; i++){
              Arrays.fill(matriz[i],0);
          }
          while(copia.getGrado() >= p.getGrado()){
              for(int i = 0; i < temporal.length; i++){
                  temporal[i][0]=0;
                  temporal[i][1]=1;
              }
              Fraccionario cociente = p.getCoeficiente(p.getGrado()).inverso().multiplique(copia.getCoeficiente(copia.getGrado()));
              temporal[k][0] = cociente.numeradorSimplificado();
              temporal[k][1] = cociente.denominadorSimplificado();
              respuesta[k][0] = cociente.numeradorSimplificado();
              respuesta[k--][1] = cociente.denominadorSimplificado();
              Polinomio q = new Polinomio(temporal);
              q = q.multiplique(p);
              copia = copia.reste(q);
          }
      }
      else{
          respuesta = new int[1][2];
          respuesta[0][0] = 0;
          respuesta[0][1] = 1;
      }
      return new Polinomio(respuesta);
   }    
   /**
    * Retorna la representacion como cadena de este polinomio
    * 
    * @return la cadena que representa a este polinomio
    */
  public String toString(){
      String ans = "";
      if(!esCero()){
          int k=0;
          while(coeficientes.get(k).numeradorSimplificado() == 0){
              k++;
          }
          ans = coeficientes.get(k).toString();
          if(k==1){
              ans+=" x";
          }
          else if(k>1){
              ans += " x**"+k;
          }
          for(int i = k+1; i < coeficientes.size(); i++){
               String ck = coeficientes.get(i).toString();
               if(ck.charAt(0)=='-' && coeficientes.get(i).numeradorSimplificado() != 0){
                   ans+=' '+ck;
               }
               else if(coeficientes.get(i).numeradorSimplificado() != 0){
                   ans +=" +"+ck;
               }
               if(i==1 && coeficientes.get(i).numeradorSimplificado() != 0){
                  ans+=" x";
               }
               else if(coeficientes.get(i).numeradorSimplificado() != 0){
                  ans += " x**"+i;
               }
          }
      }
       
      return ans;
  }


}