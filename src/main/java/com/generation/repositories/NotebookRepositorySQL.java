package com.generation.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.generation.entities.Notebook;
import com.generation.interfaces.NotebookRepository;

public class NotebookRepositorySQL implements NotebookRepository
{

    private Connection con;

    public NotebookRepositorySQL() throws ClassNotFoundException, SQLException
    {
        String versione = "com.mysql.cj.jdbc.Driver";
        Class.forName(versione);   
        String dbInformations = "jdbc:mysql://localhost:3306/itshop?user=jaita&password=jaita107";
        con =  DriverManager.getConnection(dbInformations);
    }

    @Override
    public List<Notebook> readAll() 
    {
        try
        {
            List<Notebook> res = new ArrayList<Notebook>();
            //Creo le query direttamente qui
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM notebook");

            while(rs.next())
                res.add(_rsToNotebook(rs));

            return res;
        }
        catch(SQLException e)
        {
            return null;
        }
    }

    private Notebook _rsToNotebook(ResultSet rs) throws SQLException 
    {
        Notebook res = new Notebook();
        res.setId(rs.getInt("id"));
        res.setModel(rs.getString("model"));
        res.setPrice(rs.getInt("price"));
        return res;
    }

    @Override
    public Notebook readById(int id) 
    {
        try
        {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM notebook WHERE id="+id);

            if(rs.next())
                return _rsToNotebook(rs);
            
            return null;
        }
        catch(SQLException e)
        {
            return null;
        }
    }

    @Override
    public void insert(Notebook n) 
    {
        try
        {
            Statement s = con.createStatement();
            String query =  "INSERT INTO notebook (id,model,price) values ("+
                            n.getId() + "," +
                            "'"+n.getModel() + "'," +
                            n.getPrice() + 
                            ")";

            
            s.execute(query);
    
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Notebook n) 
    {
        try
        {
            Statement s = con.createStatement();
            String query =  "UPDATE  notebook set model='"+n.getModel() + "',price=" +n.getPrice() + 
                            " WHERE id="+n.getId();

            s.execute(query);
    
        }
        catch(SQLException e)
        {
            return;
        }
    }

    @Override
    public void delete(Notebook n) 
    {
        delete(n.getId());
    }

    @Override
    public void delete(int id) 
    {
        try
        {
            Statement s = con.createStatement();
            String query =  "DELETE FROM notebook where id="+id;

            s.execute(query);
    
        }
        catch(SQLException e)
        {
            return;
        }
    }

}
