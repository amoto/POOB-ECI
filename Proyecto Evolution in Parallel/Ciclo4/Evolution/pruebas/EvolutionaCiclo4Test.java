package pruebas;
import evolutions.*;

 

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
* The test class EvolutionaCiclo4Test.
*
* @author (your name)
* @version (a version number or a date)
*/
public class EvolutionaCiclo4Test{

@Test
public void segunCTnoDeberiaAgregarFosilesEncimaDeUnFosilTop(){
String[] f = {"AACCM","AACM","ACM","AC","A"};
String[] t = {"normal","top","normal","normal","normal"};
Evolution a = new Evolution("AACCMM",f,t);
a.addPath();
a.situate(2);
a.situate(1);
a.situate(2);
a.addPath();
a.situate(1,2);
a.situate(1,2);
a.situate(1,2);
a.mergePaths(1,2);
String[][] s = {{"ACM","AACM"},{"A","AC","AACCM"}};
assertArrayEquals(a.consult(), s);
}

@Test
public void segunCTdeberiaAgregarFosilDestroyer(){
String[] f = {"AAAAA","AAAA","AAA","AA","A","C"};
String[] t = {"normal","normal","normal","normal","normal","destroyer"};
Evolution a = new Evolution("AAAAAC",f,t);
a.addPath();
a.addPath();
a.situate(1);
a.situate(1);
a.situate(1);
a.situate(1,2);
a.situate(1,2);
a.situate(1,1);
a.mergePaths(2,1);
String[][] s = {{"C"}};
assertArrayEquals(a.consult(), s);
}

@Test
public void segunCTdeberiaEliminarUnFosilYSituarse(){
String[] f = {"AAAAAC","AAAAC","AAAC","AAC","A","C"};
String[] t = {"normal","normal","normal","normal","normal","destroyer"};
Evolution a = new Evolution("AAAAAAC",f,t);
a.addPath();
a.situate(1);
a.situate(1);
a.situate(1);
a.situate(1);
a.situate(1);
a.situate(1);
String[][] s = {{"C","AAC","AAAC","AAAAC","AAAAAC"}};
assertArrayEquals(a.consult(), s);
}

@Test
public void segunCTdeberiaSituarYMezclarCorrectamente(){
String[] f = {"AAAAAC","AAAAC","AAA","AA","A","AAAAA"};
String[] t = {"normal","top","normal","normal","normal","destroyer"};
Evolution a = new Evolution("AAAAAAC",f,t);
a.addPath();
a.situate(2);
a.situate(2);
a.situate(2);
a.situate(2);
a.situate(1);
a.addPath();
a.situate(2,2);
a.mergePaths(1,2);
a.situate(1);
String[][] s = {{"A","AA","AAA","AAAAA","AAAAAC"}};
assertArrayEquals(a.consult(), s);
}

@Test
public void segunLSNodeberiaHacerMergeTop(){
String[] fossils={"C","AAC"};
String[] types={"top","top"};
Evolution e =new Evolution("AAACCC",fossils,types);
String[][] res={{"C"},{"AAC"}};
e.addPath();
e.addPath();
e.situate(1);
e.situate(1);
e.mergePaths(1,2);
assertArrayEquals(res,e.consult());
}

@Test
public void segunLSdeberiaHacerMergeDestroyer(){
String[] fossils={"C","AAC","AAAC","M","MM"};
String[] types={"destroyer","normal","normal","magical","magical"};
Evolution e =new Evolution("AAAACCCCMMM",fossils,types);
String[][] res={{"C","AAC","AAAC"}};
e.addPath();
e.situate(1);
e.situate(1);
e.situate(1);
e.addPath();
e.situate(1,2);
e.situate(1,2);
e.mergePaths(2,1);
assertArrayEquals(res,e.consult());
}

@Test
public void segunLSdeberiaHacerMagical(){
String[] fosiles={"cc","ccc","mmm","aaacccm"};
String[] tipos={"normal","magical","magical","magical"};
String[][] res={{"C","CC","CCC","AAACCCM"}};
Evolution e=new Evolution("aaaacccmmmmm",fosiles,tipos);
e.addPath();
e.situate(1);
e.situate(1,1,2);
e.situate(1,1,1);
e.situate(1,1,4);
assertArrayEquals(e.consult(),res);
}

@Test
public void segunLSnoPermiteQueUbiqueUnaArribaDeTop(){
String[] fosiles={"a","aa","aaam","m"};
String[] tipos={"normal","top","destroyer","magical"};
String[][] res={{"A","AA"}};
Evolution e=new Evolution("aaammmccc",fosiles,tipos);
e.addPath();
e.situate(1);
e.situate(1);
e.situate(1,1,3);
e.situate(2);
assertArrayEquals(e.consult(),res);
}

@Test
public void segunLSdeberiaCrearTodosLosTiposDeFosiles(){
String[] fosiles={"aa","amc","mmcc","m"};
String[] tipos={"normal","top","destroyer","magical"};
Evolution e=new Evolution("aaammmccc",fosiles,tipos);
assertTrue(e.ok());
}

@Test
public void segunSSnoDeberiaDejarSituarUnoArribaDeUnTop(){
Evolution e=new Evolution("AAAAAACCC");
e.addPath();
e.addFossil("aaaa","top");
e.addFossil("aaaaa","top");
e.situate(1);
e.situate(1);
assertFalse(e.ok());
}

@Test
public void segunSSMagicalDebeCambiarAlSituar(){
Evolution e=new Evolution("AAAAAACCC");
e.addPath();
e.addFossil("aaaa","normal");
e.addFossil("m","magical");
e.situate(1);
e.situate(1);
assertTrue(e.ok());
}

@Test
public void segunSSMagicalDebeCambiarAlMezclar(){
String[] fosiles={"a","m"};
String[] tipos={"normal","magical"};
Evolution e=new Evolution("AAAAAACCC",fosiles,tipos);
e.addPath();
e.addPath();
e.situate(1);
e.situate(1,2);
e.mergePaths(1,2);
assertTrue(e.ok());
}

@Test
public void segunSSMagicalNoPuedeCambiar(){
String[] fosiles={"a","m","aa"};
String[] tipos={"normal","magical","normal"};
Evolution e=new Evolution("AAAAAACCC",fosiles,tipos);
e.addPath();
e.situate(1);
e.situate(2);
e.situate(1,1,2);
assertFalse(e.ok());
}

@Test
public void segunSSTopNoDeberiaMezclarCaminos(){
String[] fosiles={"aaa","a"};
String[] tipos={"normal","top"};
Evolution e=new Evolution("AAAAAACCC",fosiles,tipos);
e.addPath();
e.addPath();
e.situate(1);
e.situate(1,2);
e.mergePaths(1,2);
assertFalse(e.ok()); 
}

@Test
public void segunSSDestroyerDeberiaSituar(){
String[] fosiles={"a","aa"};
String[] tipos={"top","destroyer"};
Evolution e=new Evolution("AAAAAACCC",fosiles,tipos);
e.addPath();
e.situate(1);
e.situate(1);
assertTrue(e.ok());
}

@Test
public void segunSSNodeberiaSituarMagical(){
String[] fosiles={"m"};
String[] tipos={"magical"};
Evolution e=new Evolution("A",fosiles,tipos);
e.addPath();
e.situate(1);
assertFalse(e.ok());

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

 

}