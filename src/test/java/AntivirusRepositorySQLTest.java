import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.generation.entities.Antivirus;

public class AntivirusRepositorySQLTest 
{
   
    @BeforeEach
    private void setup() throws ClassNotFoundException, SQLException
    {
        String versione = "com.mysql.cj.jdbc.Driver";
        Class.forName(versione);   
        String dbInformations = "jdbc:mysql://localhost:3306/itshop?user=jaita&password=jaita107";
        Connection con =  DriverManager.getConnection(dbInformations);
        Statement s = con.createStatement();
        String queryDatabase =  "DROP TABLE IF  EXISTS antivirus ;";
        s.execute(queryDatabase);
        String queryDatabase2 = "CREATE TABLE antivirus (id INT PRIMARY KEY,name VARCHAR(255),price INT,percentageSlow INT)";;
        s.execute(queryDatabase2);   
        String queryDatabase3 =  
        "INSERT INTO antivirus (id, name, price, percentageSlow) VALUES"+
        "(1, 'Norton AntiVirus', 59, 4),"+
        "(2, 'McAfee Total Protection', 79, 6),"+
        "(3, 'Bitdefender Antivirus Plus', 49, 2),"+
        "(4, 'Kaspersky Internet Security', 69, 3),"+
        "(5, 'Avast Premium Security', 39, 8),"+
        "(6, 'AVG Internet Security', 54, 5),"+
        "(7, 'ESET NOD32 Antivirus', 45, 7),"+
        "(8, 'Trend Micro Maximum Security', 59, 1),"+
       " (9, 'Sophos Home Premium', 59, 4),"+
        "(10, 'Malwarebytes Premium', 39, 9)";
        s.execute(queryDatabase3);
        
        s.close();
    }


    @Test
    void testReadAll() 
    {
        AntivirusRepository repo = AntivirusRepositoryFactory.make("sql");

        for(Antivirus n : repo.readAll())
            assertTrue(n.getId()>0);
    }

    @Test
    void testReadOne() 
    {
        AntivirusRepository repo = AntivirusRepositoryFactory.make("sql");

        Antivirus n = repo.readById(4);
        assertTrue(n!=null);

        Antivirus nonDeveEsistere = repo.readById(19043);
        assertTrue(nonDeveEsistere==null);
    }

    @Test
    void testInsert() 
    {
        AntivirusRepository repo = AntivirusRepositoryFactory.make("sql");

        int initial = repo.readAll().size();

        repo.insert(new Antivirus(11,"MALWARE VERO",50,80));
        
        int endingSize = repo.readAll().size();

        assertEquals(initial+1, endingSize);
    }

    @Test
    void testUpdate()
    {
        AntivirusRepository repo = AntivirusRepositoryFactory.make("sql");

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
        AntivirusRepository repo = AntivirusRepositoryFactory.make("sql");

        int initial = repo.readAll().size();

        repo.delete(new Antivirus(3, "Bitdefender Antivirus Plus", 49, 2));

        int endingSize = repo.readAll().size();

        assertEquals(initial-1, endingSize);
    }

    @Test
    void testDelete2() 
    {
        AntivirusRepository repo = AntivirusRepositoryFactory.make("sql");

        int initial = repo.readAll().size();

        repo.delete(2);

        int endingSize = repo.readAll().size();

        assertEquals(initial-1, endingSize);
    }

}
