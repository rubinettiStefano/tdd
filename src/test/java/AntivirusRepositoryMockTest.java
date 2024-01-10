import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.generation.entities.Antivirus;

public class AntivirusRepositoryMockTest 
{
   
    @BeforeEach
    private void setup() 
    {
        List<Antivirus> originale = new ArrayList<Antivirus>();
        originale.add(new Antivirus(1, "Norton AntiVirus", 59, 4));
        originale.add(new Antivirus(2, "McAfee Total Protection", 79, 6));
        originale.add(new Antivirus(3, "Bitdefender Antivirus Plus", 49, 2));
        originale.add(new Antivirus(4, "Kaspersky Internet Security", 69, 3));
        originale.add(new Antivirus(5, "Avast Premium Security", 39, 8));
        originale.add(new Antivirus(6, "AVG Internet Security", 54, 5));
        originale.add(new Antivirus(7, "ESET NOD32 Antivirus", 45, 7));
        originale.add(new Antivirus(8, "Trend Micro Maximum Security", 59, 1));
        originale.add(new Antivirus(9, "Sophos Home Premium", 59, 4));
        originale.add(new Antivirus(10, "Malwarebytes Premium", 39, 9));
       
        AntivirusRepositoryFactory.make("mock").readAll().clear();
        AntivirusRepositoryFactory.make("mock").readAll().addAll(originale);
    }


    @Test
    void testReadAll() 
    {
        AntivirusRepository repo = AntivirusRepositoryFactory.make("mock");

        for(Antivirus n : repo.readAll())
            assertTrue(n.getId()>0);
    }

    @Test
    void testReadOne() 
    {
        AntivirusRepository repo = AntivirusRepositoryFactory.make("mock");

        Antivirus n = repo.readById(4);
        assertTrue(n!=null);

        Antivirus nonDeveEsistere = repo.readById(19043);
        assertTrue(nonDeveEsistere==null);
    }

    @Test
    void testInsert() 
    {
        AntivirusRepository repo = AntivirusRepositoryFactory.make("mock");

        int initial = repo.readAll().size();

        repo.insert(new Antivirus(11,"MALWARE VERO",50,80));
        
        int endingSize = repo.readAll().size();

        assertEquals(initial+1, endingSize);
    }

    @Test
    void testUpdate()
    {
        AntivirusRepository repo = AntivirusRepositoryFactory.make("mock");

        Antivirus newVersion = new Antivirus(1, "MyCaffè", 220,42);

        repo.update(newVersion);

        Antivirus riletto = repo.readById(1);

        assertTrue
        (
            newVersion.getId()==riletto.getId()                         && 
            newVersion.getName().equals(riletto.getName())              &&
            newVersion.getPrice()==riletto.getPrice()                   && 
            newVersion.getPercentageSlow()==riletto.getPercentageSlow()
        );
    }

    @Test
    void testDelete1() 
    {
        AntivirusRepository repo = AntivirusRepositoryFactory.make("mock");

        int initial = repo.readAll().size();

        repo.delete(new Antivirus(3, "Bitdefender Antivirus Plus", 49, 2));

        int endingSize = repo.readAll().size();

        assertEquals(initial-1, endingSize);
    }

    @Test
    void testDelete2() 
    {
        AntivirusRepository repo = AntivirusRepositoryFactory.make("mock");

        int initial = repo.readAll().size();

        repo.delete(2);

        int endingSize = repo.readAll().size();

        assertEquals(initial-1, endingSize);
    }

}
