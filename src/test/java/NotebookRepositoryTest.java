import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.generation.entities.Notebook;
import com.generation.interfaces.NotebookRepository;
import com.generation.repositories.NotebookRepositoryMock;

public class NotebookRepositoryTest 
{
    @Test
    void testReadAll()
    {
        NotebookRepository repo = new NotebookRepositoryMock();

        for(Notebook n : repo.readAll())
            assertTrue(n.getId()>0);
    }

    @Test
    void testReadOne()
    {
        NotebookRepository repo = new NotebookRepositoryMock();

        Notebook n = repo.readById(1);
        assertTrue(n!=null);

        Notebook nonDeveEsistere = repo.readById(19043);
        assertTrue(nonDeveEsistere==null);
    }

    @Test
    void testInsert()
    {
        NotebookRepository repo = new NotebookRepositoryMock();

        int initial = repo.readAll().size();

        repo.insert(new Notebook(8,"Ciofecone",50));

        int endingSize = repo.readAll().size();

        assertEquals(initial+1, endingSize);
    }

    @Test
    void testUpdate()
    {
        NotebookRepository repo = new NotebookRepositoryMock();

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
        NotebookRepository repo = new NotebookRepositoryMock();

        int initial = repo.readAll().size();

        repo.delete(new Notebook(1,"Ideapad",300));

        int endingSize = repo.readAll().size();

        assertEquals(initial-1, endingSize);
    }

    @Test
    void testDelete2()
    {
        NotebookRepository repo = new NotebookRepositoryMock();

        int initial = repo.readAll().size();

        repo.delete(1);

        int endingSize = repo.readAll().size();

        assertEquals(initial-1, endingSize);
    }

}
