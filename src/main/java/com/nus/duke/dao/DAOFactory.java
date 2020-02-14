package com.nus.duke.dao;

public class DAOFactory {
    private static DAOInterface dataObj = null;
    private DAOFactory() {}

    /**
     Factory method that returns a singleton object for a DAOInterface.
     This ensures that we only have a single point of access to the data.
     This method is thread safe

     @param     daoInterface
                DAO interface that we are using to store the data

     @return    DAOInterface singleton object
     */
    private static synchronized  <T extends DAOInterface> DAOInterface getOrCreate(Class<T> daoInterface) {
        try{
            if (dataObj == null) dataObj = daoInterface.newInstance();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dataObj;
    }

    /**
     Method controls the dispatch of the DAO Object.
     There should only be 1 type of the object for data consistency.

     @return    DAOInterface singleton object
     */
    public static synchronized <T extends DAOInterface> DAOInterface getOrCreate() {
        return getOrCreate(InMemDAO.class);
    }
}
