import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.generation.entities.Notebook;
import com.generation.factories.NotebookRepositoryFactory;
import com.generation.interfaces.NotebookRepository;

public class NotebookRepositoryTest 
{
   
    @BeforeEach
    private void setup() throws ClassNotFoundException, SQLException
    {
        String versione = "com.mysql.cj.jdbc.Driver";
        Class.forName(versione);   
        String dbInformations = "jdbc:mysql://localhost:3306/itshop?user=jaita&password=jaita107";
        Connection con =  DriverManager.getConnection(dbInformations);
        Statement s = con.createStatement();
        String queryDatabase =  "DROP TABLE notebook;";
        s.execute(queryDatabase);
        String queryDatabase2 = "CREATE TABLE  notebook (id INT PRIMARY KEY,model VARCHAR(255) NOT NULL,price INT NOT NULL);";
        s.execute(queryDatabase2);   
        String queryDatabase3 =  
            "INSERT INTO notebook (id, model, price) VALUES (1, 'Ideapad', 300),"
            +" (2, 'Vaio', 800),"+
            " (3, 'Macbook', 1600),"+
            " (4, 'EEpc', 100),"+
            " (5, 'Razer', 1500),"+
            " (6, 'Samsung Galaxy Notebook', 600),"+
            " (7, 'Alienware', 2800);";
        s.execute(queryDatabase3);
        
        s.close();
    }


    @Test
    void testReadAll() 
    {
        NotebookRepository repo = NotebookRepositoryFactory.make("sql");

        for(Notebook n : repo.readAll())
            assertTrue(n.getId()>0);
    }

    @Test
    void testReadOne() 
    {
        NotebookRepository repo = NotebookRepositoryFactory.make("sql");

        Notebook n = repo.readById(4);
        assertTrue(n!=null);

        Notebook nonDeveEsistere = repo.readById(19043);
        assertTrue(nonDeveEsistere==null);
    }

    @Test
    void testInsert() 
    {
        NotebookRepository repo = NotebookRepositoryFactory.make("sql");

        int initial = repo.readAll().size();

        repo.insert(new Notebook(9,"Ciofecone",50));
        
        int endingSize = repo.readAll().size();

        assertEquals(initial+1, endingSize);
    }

    @Test
    void testUpdate()
    {
        NotebookRepository repo = NotebookRepositoryFactory.make("sql");

        Notebook newVersion = new Notebook(1, "NoIdeaPad", 220);

        repo.update(newVersion);

        Notebook riletto = repo.readById(1);

        assertTrue
        (
            newVersion.getId()==riletto.getId()                 && 
            newVersion.getModel().equals(riletto.getModel())    &&
            newVersion.getPrice()==riletto.getPrice() 
        );
    }

    @Test
    void testDelete1() 
    {
        NotebookRepository repo = NotebookRepositoryFactory.make("sql");

        int initial = repo.readAll().size();

        repo.delete(new Notebook(3,"Ideapad",300));

        int endingSize = repo.readAll().size();

        assertEquals(initial-1, endingSize);
    }

    @Test
    void testDelete2() 
    {
        NotebookRepository repo = NotebookRepositoryFactory.make("sql");

        int initial = repo.readAll().size();

        repo.delete(2);

        int endingSize = repo.readAll().size();

        assertEquals(initial-1, endingSize);
    }

}
