package pruebas;
import evolutions.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TestEvolution.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TestEvolution
{

    
    /**
     * Este test determina si agrega bien los fosiles
     *
     */
    @Test
    public void segunADDeberiaAgregarBienFosiles()
    {
        Evolution e = new Evolution("AAAAAAAAAAAAACCCCCCCCCCCCCCCMMMMMMMMMMMMMM");
        e.addFossil("A");
        assertTrue(e.ok());
        e.addFossil("AA");
        assertTrue(e.ok());
        e.addFossil("AAA");
        assertTrue(e.ok());
        e.addFossil("AAAA");
        assertTrue(e.ok());
        e.addFossil("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        assertTrue(e.ok());
        e.addFossil("BBBBBBBBBBBBBBBDDDDDDDDDDD");
        assertFalse(e.ok());
        e.addFossil("SDAA");
        assertFalse(e.ok());
        e.addFossil("CASD");
        assertTrue(e.ok());
        e.addFossil("GATTACCA");
        assertTrue(e.ok());
        e.addFossil("GATTACCA");
        assertFalse(e.ok());
    }

    /**
     * Este test determina si agrega bien los paths
     *
     */
    @Test
    public void segunADDeberiaAgregarBienPaths()
    {
       Evolution e = new Evolution("AAAAAAAAAAAAACCCCCCCCCCCCCCCMMMMMMMMMMMMMM");
       e.addPath();
       assertTrue(e.ok());
       e.addPath();
       assertTrue(e.ok());
       e.addPath();
       assertTrue(e.ok());
       e.addPath();
       assertTrue(e.ok());
       e.addPath();
       assertTrue(e.ok());
       e.addPath();
       assertTrue(e.ok());
       e.addPath();
       assertTrue(e.ok());
       e.addPath();
       assertTrue(e.ok());
    }
    /**
     * Este test determina si hace bien el situate 1(fossil,path,position)
     *
     */
    @Test
    public void segunADDeberiaAgregarBienSituate1()
    {
       Evolution e = new Evolution("AAAAAAAAAAAAACCCCCCCCCCCCCCCMMMMMMMMMMMMMM");
       e.addPath();
       e.addPath();
       e.addPath();
       e.addFossil("A");
       e.addFossil("AA");
       e.addFossil("AAA");
       e.addFossil("AAAA");
       e.addFossil("C");
       e.addFossil("CC");
       e.addFossil("CCC");
       e.addFossil("CCCC");
       e.addFossil("M");
       e.addFossil("MM");
       e.addFossil("MMM");
       e.addFossil("MMMM");
       e.situate(1, 1, 1);
       assertTrue(e.ok());
       e.situate(1, 1, 2);
       assertTrue(e.ok());
       e.situate(1, 1, 2);
       assertFalse(e.ok());
       e.situate(1, 1, 3);
       assertTrue(e.ok());
       e.situate(1, 1, 4);
       assertTrue(e.ok());
       e.situate(1, 1, 1);
       assertFalse(e.ok());
       e.situate(1, 2, 1);
       assertTrue(e.ok());
       e.situate(1, 2, 2);
       assertTrue(e.ok());
       e.situate(1, 4, 1);
       assertFalse(e.ok());
       e.situate(1, 2, 3);
       assertTrue(e.ok());
       e.situate(7, 2, 4);
       assertFalse(e.ok());
       e.situate(1, 2, 4);
       assertTrue(e.ok());
       e.situate(1, 3, 1);
       assertTrue(e.ok());
       e.situate(1, 3, 5);
       assertFalse(e.ok());
       e.situate(1, 3, 2);
       assertTrue(e.ok());
       e.situate(1, 3, 3);
       assertTrue(e.ok());
    }
    
    @Test
    public void segunSSdebeCrearElPlanetaConLosNucleotidosQueSirvan(){
        Evolution e = new Evolution("AAXXXXXXMDDFSSAAA");
        assertTrue(e.ok());
    }

    @Test
    public void segunSSdebeAgregarLosFosilesQueSirvan(){
        Evolution e = new Evolution("AAAAAAAAAAAAA");
        e.addFossil("AAAA");
        assertTrue(e.ok());
        e.addFossil("ACGGGMFFF");
        assertTrue(e.ok());
        e.addFossil("SSS");
        assertFalse(e.ok());
    }

    @Test
    public void segunSSnoDeberiaSituarElFosil(){
        Evolution e = new Evolution("AAAAAAAAAAAAA");
        e.addFossil("AAAA");
        e.situate(1);
        assertFalse(e.ok());
    }

    @Test
    public void segunSSnoSeDeberiaDejarUbicarUnFosilEnUnPathSiElNoTienePosibilidadDeEvolucionarAlGenActual(){
        Evolution e = new Evolution("AAAAAAAAAAAAAMMMMMM");
        e.addFossil("CC");
        e.addPath();
        e.situate(1);
        assertFalse(e.ok());
    }

    @Test
    public void segunSSnoSeDeberiaRepetirUnFosilEnElSimulador(){
        Evolution e = new Evolution("ACMAMAMC");
        e.addFossil("A");
        assertTrue(e.ok());
        e.addPath();
        e.situate(1);
        e.addFossil("A");
        assertFalse(e.ok());
    }

    /*@Test
    public void segunSSnoSeDeberiaPoderAgregarUnFosilDeLaMismaLongitudDelPlaneta(){
        Evolution e = new Evolution("AAAAAAC");
        e.addFossil("AAAAAAA");
        assertFalse(e.ok());
    }*/

    @Test
    public void segunSSnoSeDeberiaPoderMezclarDosPathsInexistentes(){
        Evolution e = new Evolution("AAAAAA");
        e.mergePaths(1,2);
        assertFalse(e.ok());
    }

    @Test
    public void segunSSnoSeDeberiaPoderMezclarUnPathInexistenteConUnoExistente(){
        Evolution e = new Evolution("AACMA");
        e.addPath();
        e.mergePaths(1,8);
        assertFalse(e.ok());
    }

    @Test
    public void segunSSnoSeDeberiaPoderAgregarUnFosilEnUnPathQueNoExista(){
        Evolution e = new Evolution("MMCAAMA");
        e.addFossil("MC");
        assertTrue(e.ok());
        e.addPath();
        e.situate(1,2,1);
        assertFalse(e.ok());
    }

    @Test
    public void segunSSdeberiHacerUnCorrectoeIncorrectoMergeYverificarQueSeElimineLuegoDeCadaMergeUnoDeLosCaminos(){
        Evolution e = new Evolution("AAAAA");
        e.addFossil("A");
        e.addFossil("AAA");
        e.addPath();
        e.situate(1,1,1);
        e.situate(1,1,2);
        e.addFossil("AA");
        e.addPath();
        e.situate(1,2,1);
        e.mergePaths(1,2);
        assertTrue(e.ok());
        e.mergePaths(1,2);
        assertFalse(e.ok());
    }

    @Test
    public void segunSSdespuesDeSerInvisibleACausaDelZoomNoDebeSerPermitidoHacerElMetodoMakeVisible(){
        Evolution e = new Evolution("AAAAAAAA");
        for (int i=0;i<1000;i++){
            e.zoom('-');}
        e.makeVisible();
        assertFalse(e.ok());
        e.makeInvisible();
        for (int i=0;i<1000;i++){
            e.zoom('+');}
        e.makeVisible();
        assertTrue(e.ok());
        e.makeInvisible();
        for (int i=0;i<1000;i++){
            e.zoom('+');}
        e.makeVisible();
        assertFalse(e.ok());
    }

    @Test
    public void segunSSelConsultDebeSerCorrecto(){
        Evolution e = new Evolution("AAAAA");
        e.addFossil("A");
        e.addPath();
        e.situate(1);
        Evolution a = new Evolution("AAAAA");
        a.addFossil("A");
        a.addPath();
        a.situate(1);
        assertArrayEquals(e.consult(),a.consult());
    }

    @Test
    public void segunSSelSortDebeSerCorrecto(){
        Evolution e = new Evolution("AAAAAMMMMMMMMMMCCCCC");
        Evolution a = new Evolution("CCCCCMMMMMMMMAAAAA");
        e.addFossil("C");
        e.addFossil("M");
        e.addFossil("A");
        for (int i=0;i<=3;i++){
            e.addPath();
            e.situate(1);}
        a.addFossil("M");
        a.addFossil("A");
        a.addFossil("C");
        for (int i=0;i<=3;i++){
            a.addPath();
            a.situate(1);}
        e.sort();
        a.sort();
        assertArrayEquals(e.consult(),a.consult());
        Evolution c = new Evolution("MMMMMMMMCCCAAAA");
        c.sort();
        assertFalse(c.ok());
    }

    @Test
    public void segunSSdeberiaSituarCorrectamente(){
        Evolution e = new Evolution("AAAAAMMMMMM");
        e.addFossil("AM");
        e.addFossil("AAAMMM");
        e.addPath();
        e.situate(1,1);
        e.situate(1,1);
        e.addFossil("AAMM");
        e.situate(1);
        Evolution a = new Evolution("AAAAAMMMMMM");
        a.addFossil("AM");
        a.addFossil("AAMM");
        a.addFossil("AAAMMM");
        a.addPath();
        a.situate(1,1,1);
        a.situate(1,1,2);
        a.situate(1,1,3);
        assertArrayEquals(e.consult(),a.consult());
    }

    @Test
    public void segunSSdeberiaCrearElPlanetaConLosFosilesAdecuados(){
        String[] ameter = {"AXXDGA","AAAAACC","M"};
        Evolution e = new Evolution("AAAAACC",ameter);
        e.addPath();
        e.situate(1);
        Evolution a = new Evolution("AAAAACC");
        a.addFossil("AA");
        a.addPath();
        a.situate(1);
        assertArrayEquals(e.consult(),a.consult());
    }

    //Comprueba que el constructor cree el planeta con sus fosiles
    @Test
    public void segunLSdeberiaCrearPlanetaConFosiles(){
        String [] lol={"45fa","ACMCACMCVA","%$&$&%$6c"};
        Evolution planeta=new Evolution("AUAUIAG/AFIAFA/(IGA21354",lol);
        planeta.addFossil("AAASSAAMMBB");
        assertTrue(planeta.ok());
        String [] lol2={"45fa","666a","%$mm&$&%$6"};
        Evolution planeta2=new Evolution("AUAUIAG/AFIAFA/(IGA21354",lol2);
        planeta2.addFossil("5555ñññ");
        assertTrue(!planeta2.ok());
    }
    //Comprueba la creacion de un planeta dado su codigo
    @Test
    public void segunLSdeberiaCrearPlaneta()
    {
        String planeta1="16168491468grshdfgaacc tuydru";
        assertTrue(new Evolution(planeta1).ok());
        String planeta2="DATYAUAvxcbcnx54545mmxdsfgyCC";
        assertTrue(new Evolution(planeta2).ok());
        String planeta3="3742a7&(/%&/%/&nbfjio";
        assertTrue(new Evolution(planeta3).ok());
    }
    //Comprueba si se pueden agregar lineas de evolución
    @Test
    public void segunLSdeberiaAdherirPath(){
        String [] lol={"45fa","ACMCACMCVA","%$&$&%$6c"};
        Evolution planeta=new Evolution("AUAUIAG/AFIAFA/(IGA21354",lol);
        planeta.addFossil("AAASSAAMMBB");
        planeta.addPath();
        assertTrue(planeta.ok());
        planeta.addPath();
        assertTrue(planeta.ok());
        planeta.addPath();
        assertTrue(planeta.ok());
    }
    //Comprueba que puede situar un fosil usando el método de 3 párametros
    @Test
    public void segunLSdeberiaSituarFosil3(){
        Evolution planeta=new Evolution("aaaaammmmcccc");
        planeta.addFossil("a");
        planeta.addFossil("amc");
        planeta.addFossil("aacc");
        planeta.addFossil("mm");
        planeta.addPath();
        planeta.addPath();
        planeta.situate(1,1,1);
        assertTrue(planeta.ok());
        planeta.situate(2,1,2);
        assertTrue(planeta.ok());
        planeta.situate(2,2,1);
        assertTrue(planeta.ok());
        planeta.situate(1,2,2);
        assertFalse(planeta.ok());
    }
    //Comprueba que puede situar un fosil usando el método de 2 párametros
    @Test
    public void segunLSdeberiaSituarFosil2(){
        Evolution planeta=new Evolution("aaaaammmmcccc");
        planeta.addFossil("a");
        planeta.addFossil("amc");
        planeta.addFossil("aacc");
        planeta.addFossil("mm");
        planeta.addPath();
        planeta.addPath();
        planeta.situate(1,1);
        assertTrue(planeta.ok());
        planeta.situate(2,1);
        assertTrue(planeta.ok());
        planeta.situate(2,2);
        assertTrue(planeta.ok());
        planeta.situate(1,2);
        assertFalse(planeta.ok());
    }
    //Comprueba que puede situar un fosil usando el método de 1 párametros
    @Test
    public void segunLSdeberiaSituarFosil1(){
        Evolution planeta=new Evolution("aaaaammmmcccc");
        planeta.addFossil("a");
        planeta.addFossil("amc");
        planeta.addFossil("aacc");
        planeta.addFossil("mm");
        planeta.addPath();
        planeta.addPath();
        planeta.situate(1);
        assertTrue(planeta.ok());
        planeta.situate(2);
        assertTrue(planeta.ok());
        planeta.situate(2);
        assertTrue(planeta.ok());
        planeta.situate(1);
        assertFalse(planeta.ok());
    }
    //Comprueba que las lineas de evolucion se puedan mezclar
    @Test
    public void segunLSdeberiaHacerMerge(){
        Evolution planeta=new Evolution("aaaaammmmcccc");
        planeta.addFossil("a");
        planeta.addFossil("aa");
        planeta.addFossil("aaa");
        planeta.addFossil("mc");
        planeta.addFossil("mcc");
        planeta.addFossil("mccc");
        planeta.addFossil("amc");
        planeta.addPath();planeta.addPath();planeta.addPath();planeta.addPath();
        planeta.situate(1);planeta.situate(1);planeta.situate(1,2);planeta.mergePaths(1,2);assertTrue(planeta.ok());
        planeta.addPath();
        planeta.situate(1);planeta.situate(1);planeta.situate(1,3);planeta.mergePaths(2,3);assertTrue(planeta.ok());
        planeta.situate(1);planeta.mergePaths(1,2);assertFalse(planeta.ok());
        planeta.mergePaths(1,3);assertFalse(planeta.ok());
    }
//Comprueba que se ordenen las lineas de evolucion
    @Test
    public void segunLSdeberiaHacerSort(){
        Evolution planeta=new Evolution("aaaaammmmcccc");
        planeta.addFossil("a");
        planeta.addFossil("aa");
        planeta.addFossil("aaa");
        planeta.addFossil("mc");
        planeta.addFossil("mcc");
        planeta.addFossil("mccc");
        planeta.addFossil("amc");
        planeta.addPath();planeta.addPath();planeta.addPath();planeta.addPath();
        planeta.situate(1);planeta.situate(1);planeta.situate(1,2);planeta.mergePaths(1,2);assertTrue(planeta.ok());
        planeta.addPath();
        planeta.situate(1);planeta.situate(1);planeta.situate(1,3);planeta.mergePaths(2,3);assertTrue(planeta.ok());
        planeta.situate(1);planeta.mergePaths(1,2);assertFalse(planeta.ok());
        planeta.mergePaths(1,3);assertFalse(planeta.ok());
        planeta.sort();assertTrue(planeta.ok());
    }
    //Comprueba que el simulador aumente de tamaño
    @Test
    public void segunLSdeberiaHacerZoom(){
        Evolution planeta=new Evolution("aaaaammmmcccc");
        planeta.addFossil("a");
        planeta.addFossil("aa");
        planeta.addFossil("aaa");
        planeta.addFossil("mc");
        planeta.addFossil("mcc");
        planeta.addFossil("mccc");
        planeta.addFossil("amc");
        planeta.addPath();planeta.addPath();planeta.addPath();planeta.addPath();
        planeta.situate(1);planeta.situate(1);planeta.situate(1,2);planeta.mergePaths(1,2);assertTrue(planeta.ok());
        planeta.addPath();
        planeta.situate(1);planeta.situate(1);planeta.situate(1,3);planeta.mergePaths(2,3);assertTrue(planeta.ok());
        planeta.situate(1);planeta.mergePaths(1,2);assertFalse(planeta.ok());
        planeta.mergePaths(1,3);assertFalse(planeta.ok());
        planeta.sort();assertTrue(planeta.ok());
        planeta.zoom('+');assertTrue(planeta.ok());
        planeta.zoom('-');assertTrue(planeta.ok());
    }
    //Comprueba que el consultar retorne lo que es
    @Test
    public void segunLSdeberiaConsultar(){
        String [][] cons={{"A","AA","AAA"},{"MC","MCC","MCCC"},{"AMC"}};
        Evolution planeta=new Evolution("aaaaammmmcccc");
        planeta.addFossil("a");
        planeta.addFossil("aa");
        planeta.addFossil("aaa");
        planeta.addFossil("mc");
        planeta.addFossil("mcc");
        planeta.addFossil("mccc");
        planeta.addFossil("amc");
        planeta.addPath();planeta.addPath();planeta.addPath();planeta.addPath();
        planeta.situate(1);planeta.situate(1);planeta.situate(1,2);planeta.mergePaths(1,2);assertTrue(planeta.ok());
        planeta.addPath();
        planeta.situate(1);planeta.situate(1);planeta.situate(1,3);planeta.mergePaths(2,3);assertTrue(planeta.ok());
        planeta.situate(1);planeta.mergePaths(1,2);assertFalse(planeta.ok());
        planeta.mergePaths(1,3);assertFalse(planeta.ok());
        assertArrayEquals(planeta.consult(),cons);
    }
    
    @Test
    public void segunCTdeberiaCrearElPlanetaConLosFosilesAdecuados(){
        String[] ameter = {"MMMMMMCCCCCCCAAaa","mmmmmCcc","MmMm"};
        Evolution e = new Evolution("MMMMMMCCCCCCCAAAAA",ameter);
        e.addPath();
        e.situate(1);
        e.situate(1);
        e.situate(1);
        Evolution a = new Evolution("MMMMMMCCCCCCCAAAAA");
        a.addFossil("MMMMMMCCCCCCCAAAA");
        a.addFossil("MMMMMCCC");
        a.addFossil("MMMM");
        a.addPath();
        a.situate(1);
        a.situate(1);
        a.situate(1);
        assertArrayEquals(e.consult(),a.consult());
    }
    
    @Test
    public void segunCTelConsultDebeSerCorrecto(){
        Evolution e = new Evolution("AAAAAA");
        e.addFossil("A");
        e.addFossil("AAA");
        e.addFossil("AAAAA");
        e.addPath();
        e.addPath();
        e.addPath();
        e.situate(1);
        e.situate(1,2);
        e.situate(1,3);
        String[][] acomparar={{"A"},{"AAA"},{"AAAAA"}};
        assertArrayEquals(e.consult(),acomparar);
    }
    
    @Test
    public void segunADdeberiaTransformarMagical(){
        String[] fossils={"aa","aaaa","mmm"},types={"normal","normal","magical"};
        Evolution e=new Evolution("AAAAAA",fossils,types);
        e.addPath();
        e.situate(1);
        e.situate(1);
        e.situate(1,1,2);
        String[][] check={{"AA","AAA","AAAA"}};
        assertArrayEquals(e.consult(),check);
    }
    
    @Test
    public void segunADdestroyerDeberiaDestruir(){
        String[] fossils={"aa","aaa","a","am"},types={"normal","normal","normal","destroyer"};
        Evolution e=new Evolution("AAAAAAM",fossils,types);
        e.addPath();
        e.situate(1);
        e.situate(1);
        e.situate(1);
        e.situate(1);
        String[][] check={{"A","AM"}};
        assertArrayEquals(e.consult(),check);
    }
    
    @Test
    public void segunADtopDeberianBloquear(){
        String[] fossils={"aa","aaa","a","am"},types={"top","normal","normal","normal"};
        Evolution e=new Evolution("AAAAAAM",fossils,types);
        e.addPath();
        e.situate(1);
        e.situate(1);
        e.situate(2);
        e.situate(2);
        String[][] check={{"A","AA"}};
        assertArrayEquals(e.consult(),check);
    }
    
    @Test
    public void segunADdeberiaSituarColonizador(){
        String[] fossils={"aa","aaa","a","am"},types={"colonizador","normal","normal","normal"};
        Evolution e=new Evolution("AAAAAAM",fossils,types);
        e.addPath();
        e.situate(1);
        e.situate(1);
        e.situate(1);
        e.situate(1);
        String[][] check={{"A","AA","AAA"}};
        assertArrayEquals(e.consult(),check);
    }
    
    @Test
    public void segunADdestroyerDeberiaDestruirTop(){
        String[] fossils={"aa","aaa","a","am"},types={"normal","Top","normal","destroyer"};
        Evolution e=new Evolution("AAAAAAM",fossils,types);
        e.addPath();
        e.situate(1);
        e.situate(1);
        e.situate(1);
        e.situate(1);
        String[][] check={{"A","AM"}};
        assertArrayEquals(e.consult(),check);
    }
    
    
}
