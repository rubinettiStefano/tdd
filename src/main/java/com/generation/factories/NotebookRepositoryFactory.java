package com.generation.factories;

import java.util.HashMap;
import java.util.Map;

import com.generation.interfaces.NotebookRepository;
import com.generation.repositories.NotebookRepositoryMock;
import com.generation.repositories.NotebookRepositorySQL;

public class NotebookRepositoryFactory 
{
    //Fare caching significa salvarsi da qualche parte qualcosa
    private static Map<String,NotebookRepository> cache = new HashMap<String,NotebookRepository>();

    //Prima prova a produrre l'implementazione SQL
    //se fallisce, perchè magari il db non è ancora pronto
    //produci la Mock
    //il make best in slot, quelli senza parametri, quello che da l'implementazione che
    //consideriamo migliore
    public static NotebookRepository make()
    {
        try
        {
            if(cache.containsKey("sql"))//cpntrollo se la ho già in cache
                return cache.get("sql");//se si la restituisco

            //se no la creo, la metto in cache e poi la restituisco
            NotebookRepository res = new NotebookRepositorySQL();
            cache.put("sql", res);
            return res;
        }
        catch(Exception e)
        {
            if(cache.containsKey("mock"))
                return cache.get("mock");
            NotebookRepository res = new NotebookRepositoryMock();
            cache.put("mock", res);
            return res;
        }
    }

    public static NotebookRepository make(String s)
    {
        try
        {
            switch (s) {
                case "sql":
                {
                    if(cache.containsKey("sql"))//cpntrollo se la ho già in cache
                        return cache.get("sql");//se si la restituisco

                    //se no la creo, la metto in cache e poi la restituisco
                    NotebookRepository res = new NotebookRepositorySQL();
                    cache.put("sql", res);
                    return res;
                }    
                case "mock":
                {
                    if(cache.containsKey("mock"))
                        return cache.get("mock");
                    NotebookRepository res = new NotebookRepositoryMock();
                    cache.put("mock", res);
                    return res;
                }
                default:
                    return null;
            }
        }
        catch(Exception e)
        {
            if(cache.containsKey("mock"))
                return cache.get("mock");
            NotebookRepository res = new NotebookRepositoryMock();
            cache.put("mock", res);
            return res;
        }
    }
}
