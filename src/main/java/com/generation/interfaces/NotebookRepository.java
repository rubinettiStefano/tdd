package com.generation.interfaces;

import java.util.List;

import com.generation.entities.Notebook;

public interface NotebookRepository 
{
    List<Notebook> readAll();
    Notebook readById(int id);
    void insert(Notebook n);
    void update(Notebook n);
    /**
     * Cancella il Notebook passato come parametro
     * @param n
     */
    void delete(Notebook n);

    /**
     * Cancella il Notebook con l'id passato come parametro
     * @param id
     */
    void delete(int id);
}
