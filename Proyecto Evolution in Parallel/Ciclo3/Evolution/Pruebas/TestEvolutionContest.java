package Pruebas;


import evolutionContest.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TestEvolutionContest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TestEvolutionContest
{
    @Test
    public void segunLSdeberiaDarVacio()
    {
        String[][] res={{},{}};
        EvolutionContest ec= new EvolutionContest();
        String[] fos= {"AAAAA","A","CC","AC","M","MA"};
        String[][] ans;
        ans=ec.solve("AACCMM",fos);
        assertArrayEquals(ans,res);
    }
    @Test
    public void segunLSdeberiaDarUnoLLeno(){
        String[][] res={{},{"A","AA","AAA","AAAAA"}};
        EvolutionContest ec= new EvolutionContest();
        String[] fos= {"AA","AAA","A","AAAAA",};
        String[][] ans;
        ans=ec.solve("AAAAAA",fos);
        assertArrayEquals(ans,res); 
    }
    @Test
    public void segunLSdeberiaDarLosDos(){
        String[][] res={{"MM"},{"A","AA","ACA","ACMAA"}};
        EvolutionContest ec= new EvolutionContest();
        String[] fos= {"ACA","MM","ACMAA","AA","A"};
        String[][] ans;
        ans=ec.solve("AACCMMAA",fos);
        assertArrayEquals(ans,res); 
    }
    @Test
    public void segunLSdeberiaDarImpossible(){
        String[][] res={{},{}};
        EvolutionContest ec= new EvolutionContest();
        String[] fos= {"M","MC","MMC","ACMAA"};
        String[][] ans;
        ans=ec.solve("MMC",fos);
        assertArrayEquals(ans,res); 
    }
    @Test
    public void segunADdeberíaSituar(){
        String[][] res={{"AM"},{"MA"}};
        EvolutionContest ec= new EvolutionContest();
        String[] fos= {"MA","AM"};
        String[][] ans;
        ans=ec.solve("AMA",fos);
        assertArrayEquals(ans,res); 
    }
    @Test
    public void segunADnoDeberíaSituar(){
        String[][] res={{},{}};
        EvolutionContest ec= new EvolutionContest();
        String[] fos= {"A"};
        String[][] ans;
        ans=ec.solve("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCMCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC",fos);
        assertArrayEquals(ans,res); 
    }
    @Test
    public void segunADnoDeberíaSituarImposible(){
        String[][] res={{},{}};
        EvolutionContest ec= new EvolutionContest();
        String[] fos= {"MMMACA","MMMAMCMC","CAMCCCMCAM","CMCCACMMAAMC","MAACCCCAACMACC","CMCCAMCCMACCAMCC","MCCMMMAMCACCMCACAA","CAMCAAMCMAMAACAACAAM","CACCCCCAAAMCMMCACCMAAM","CMCMCMMACACAMMMAAAMCCMAM"};
        String[][] ans;
        ans=ec.solve("MAACCMCMACMAAMCMMMAACMAAMAMCCAAAMAMMMMMMAMMCAMAMMCMAMAMCAMMAMCAAACCCCAMAACMCCCCACAACAACCCCCMAAACACAA",fos);
        assertArrayEquals(ans,res); 
    }
    @Test
    public void segunADdeberiaSituarBien(){
        String[][] res={{"ACAMCAM","ACAMACAM","ACAMACAMA","ACMCAMACAMA"},{"A","AC","ACM","ACAM","AMCAM","ACMCAM","ACMCAMA","ACMCAMMA","ACMCAMMAC","ACMCAMMACAMA","ACMCAMMACMAMA","ACMACAMMACMAMA","ACMAACAMMACMAMA"}};
        EvolutionContest ec= new EvolutionContest();
        String[] fos= {"A","ACMCAMMAC","ACAMACAM","ACAMACAMA","AC","ACMCAMA","ACMACAMMACMAMA","ACMCAMMA","ACM","AMCAM","ACMCAMACAMA","ACMCAM","ACAM","ACMCAMMACAMA","ACMAACAMMACMAMA","ACMCAMMACMAMA","ACAMCAM"};
        String[][] ans;
        ans=ec.solve("ACMAACAMMACAMAMA",fos);
        assertArrayEquals(ans,res); 
    }
    @Test
    public void segunCTdeberiaSituarBien(){
        String[][] res={{"CCCCCCC","ACMACMACMCMAMMACAMCMAC","ACMACMACMCMAMMACAMCMACM","ACMACMACMCMAMMACAMCMACMC","AAAAAAAAAMACMACMACMCMAMMACAMCMACMC"},{"ACM","ACMACMACM","ACMACMACMC","ACMACMACMCM","ACMACMACMCMA","ACMACMACMCMAM","ACMACMACMCMAMM","ACMACMACMCMAMMA","ACMACMACMCMAMMAC","ACMACMACMCMAMMACA","ACMACMACMCMAMMACAM","ACMACMACMCMAMMACAMC","ACMACMACMCMAMMACAMCM","ACMACMACMCMAMMACAMCMA","CCCCCCCCCMACMACMACMCMAMMACAMCMACMC"}};
        EvolutionContest ec= new EvolutionContest();
        String[] fos= {"CCCCCCC","ACMACMACMCMAMMACAMCMACM","ACMACMACM","ACMACMACMCMAMM","ACMACMACMCMAMMACAMCMA","ACMACMACMCMAMMACAM","ACMACMACMCMAMMACAMCM","ACMACMACMCM","ACMACMACMCMAMMACAMCMACMC","AAAAAAAAAMACMACMACMCMAMMACAMCMACMC","ACMACMACMCMAMMACAMC","CCCCCCCCCMACMACMACMCMAMMACAMCMACMC","ACMACMACMCMAMMA","ACMACMACMCMAMMAC","ACM","ACMACMACMCMAMMACAMCMAC","ACMACMACMCMAM","ACMACMACMCMAMMACA","ACMACMACMC","ACMACMACMCMA"};
        String[][] ans;
        ans=ec.solve("CCCCCCCCCMAAAAAAAAAMACMACMACMCMAMMACAMCMACMC",fos);
        assertArrayEquals(ans,res); 
    }
    @Test
    public void segunCTDeberiaDarImposible(){
        String[][] res={{},{}};
        EvolutionContest ec= new EvolutionContest();
        String[] fos= {"M","C","A"};
        String[][] ans;
        ans=ec.solve("MC",fos);
        assertArrayEquals(ans,res); 
    }
    @Test
    public void segunCTDeberiaDarImposibleUnaSolaLetra(){
        String[][] res={{},{}};
        EvolutionContest ec= new EvolutionContest();
        String[] fos= {"M"};
        String[][] ans;
        ans=ec.solve("M",fos);
        assertArrayEquals(ans,res); 
    }
    @Test
    public void segunCTDeberiaSituar(){
        String[][] res={{"M","MA"},{"C","AC","AAAAAAC"}};
        EvolutionContest ec= new EvolutionContest();
        String[] fos= {"M","C","AC","MA","AAAAAAC"};
        String[][] ans;
        ans=ec.solve("MAAAAAAC",fos);
        assertArrayEquals(ans,res); 
    }
    
    @Test 
    public void segunSSDeberiaSituar(){
        String [][] res = {{"ACC","CAACC","CAAACC","CAMAACMC","CAMACCACCMC","CMAMCAACCACCMC","CMAAMCACACCCACCMC","CMAAMCACMAACCCACCMC","CMAACMCACMAACCCACCMC","CMCAACMCAACMAACCCACCMCC"},{"MMM","MMMA","MMMMA","MMMMAM","MMMMAMA","AMMAMMAMA","AMMAMMAMACA","AAMMAMAMAMACA","MAACMMAAMAMAMACA","MAACMMMAAMAMAMAACA"}};
        EvolutionContest simulador= new EvolutionContest();
        String[] fossils= {"MMMA","AMMAMMAMACA","CAACC","MAACMMAAMAMAMACA","MMMMAM","CMAAMCACACCCACCMC","MMMMA","MMMMAMA","CAMAACMC","CMAMCAACCACCMC","CMAAMCACMAACCCACCMC","AAMMAMAMAMACA","ACC","MAACMMMAAMAMAMAACA","CMAACMCACMAACCCACCMC","CAAACC","AMMAMMAMA","CAMACCACCMC","MMM","CMCAACMCAACMAACCCACCMCC"};
        String [][] ans = simulador.solve("MMAMCAAAMACAMCCCMMCMMCACAAMAMCCCCCMACCMAAAMAMMMAMCCMCACCCMMMMCMAMCCAMACMCCMCCACACCAMMMCAACMCMMMCACAC",fossils);
        assertArrayEquals(ans,res); 
    }
    
    @Test
    public void segunSSDeberiaSituar2(){
        String[][] res = {{"A","AAAAAAAAAAAAAAAAAAAA"},{"C","ACM","ACMC","ACMCA","ACMCAA","ACMCAAA","ACMCAAAM","ACMCAAAMC","ACMCAAAMCA","ACMCAAAMCAM","ACMCAAAMCAMM","ACMCAAAMCAMMC","ACMCAAAMCAMMCC","ACMCAAAMCAMMCCA","ACMCAAAMCAMMCCAC","ACMCAAAMCAMMCCACM","ACMCAAAMCAMMCCACMM","ACAMMMCAAACAMCAMMCMCCMACMAMM"}};
        EvolutionContest simulador= new EvolutionContest();
        String[] fossils= {"A","C","AAAAAAAAAAAAAAAAAAAA","ACMC","ACAMMMCAAACAMCAMMCMCCMACMAMM","ACMCAAAMCAMMCCACMM","ACMCAAAMCAMMCC","ACMCAAAMCAMMCCACM","ACMCAAAMCAM","ACMCA","ACMCAAAMCA","ACM","ACMCAAA","ACMCAAAMCAMMCCAC","ACMCAAAMCAMMCCA","ACMCAA","ACMCAAAM","ACMCAAAMCAMMC","ACMCAAAMCAMM","ACMCAAAMC"};
        String[][] ans;
        ans=simulador.solve("ACAMMMCAAACAAMAAAAAAACAAMMCMCCAMAMCAAMAAAAAMM",fossils);
        assertArrayEquals(ans,res); 
    }
    
    @Test
    public void segunSSNoDeberiaSituar(){
        String[][] res = {{},{}};
        EvolutionContest ec= new EvolutionContest();
        String[] fossils={"MMMACA","MMMAMCMC","CAMCCCMCAM","CMCCACMMAAMC","MAACCCCAACMACC","CMCCAMCCMACCAMCC","MCCMMMAMCACCMCACAA","CAMCAAMCMAMAACAACAAM","CACCCCCAAAMCMMCACCMAAM","CMCMCMMACACAMMMAAAMCCMAM"} ;
        String[][] ans;
        ans=ec.solve("MAACCMCMACMAAMCMMMAACMAAMAMCCAAAMAMMMMMMAMMCAMAMMCMAMAMCAMMAMCAAACCCCAMAACMCCCCACAACAACCCCCMAAACACAA",fossils);
        assertArrayEquals(ans,res); 
    }
}
