package com.generation.repositories;

import java.util.ArrayList;
import java.util.List;

import com.generation.entities.Notebook;
import com.generation.interfaces.NotebookRepository;

public class NotebookRepositoryMock implements NotebookRepository
{

    //Mock una finta, una demo, un qualcosa che funziona ma che non è REALE
    //è qualcosa che mettete per far funzionare il resto del programma fino a quando
    //non vi arriva una versione reale
    private List<Notebook> content = new ArrayList<Notebook>();

    public NotebookRepositoryMock()
    {
        content.add(new Notebook(1,"Ideapad",300));
        content.add(new Notebook(2,"Vaio",800));
        content.add(new Notebook(3,"Macbook",1600));
        content.add(new Notebook(4,"EEpc",100));
        content.add(new Notebook(5,"Razer",1500));
        content.add(new Notebook(6,"Samsug galaxy notebook",600));
        content.add(new Notebook(7,"Alienware",2800));
    }


    @Override
    public List<Notebook> readAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readAll'");
    }

    @Override
    public Notebook readById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readById'");
    }

    @Override
    public void insert(Notebook n) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void update(Notebook n) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Notebook n) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
