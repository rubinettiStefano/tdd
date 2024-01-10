package com.generation;


import com.generation.entities.Notebook;
import com.generation.factories.NotebookRepositoryFactory;
import com.generation.interfaces.NotebookRepository;

public class Main 
{

    
    public static void main(String[] args) 
    {
        //Non abbiamo assolutamente idea su che tipo concreto stiamo lavorando
        //NE CI INTERESSA
        //il nostro programma è in grado di lavorare in maniera completamente indefferente
        NotebookRepository repo = NotebookRepositoryFactory.make();
        
        //programma che stampa notebook con id 5
        Notebook n = repo.readById(5);

        System.out.println(n);
        metodinoACaso();
    }

    public static void metodinoACaso()
    {
        NotebookRepository repo = NotebookRepositoryFactory.make();

        Notebook n = repo.readById(6);

        System.out.println(n);
    }
}