package com.nus.duke.dao;

public class DAOFactory {
    private static DAOInterface dataObj = null;
    private DAOFactory() {}

    /**
     Factory method that returns a singleton object for a DAOInterface.
     This ensures that we only have a single point of access to the data.
     This method is thread safe
     */
    private static synchronized  <T extends DAOInterface> DAOInterface getOrCreate(Class<T> DAOTypeClass) {
        try{
            if (dataObj == null) dataObj = DAOTypeClass.newInstance();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return dataObj;
    }

    public static synchronized <T extends DAOInterface> DAOInterface getOrCreate() {
        return getOrCreate(InMemDAO.class);
    }
}
