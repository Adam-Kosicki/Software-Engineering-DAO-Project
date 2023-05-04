/* NOTICE: All materials provided by this project, and materials derived 
 * from the project, are the property of the University of Texas. 
 * Project materials, or those derived from the materials, cannot be placed 
 * into publicly accessible locations on the web. Project materials cannot 
 * be shared with other project teams. Making project materials publicly 
 * accessible, or sharing with other project teams will result in the 
 * failure of the team responsible and any team that uses the shared materials. 
 * Sharing project materials or using shared materials will also result 
 * in the reporting of all team members for academic dishonesty. 
 */
package cs4347.jdbcGame.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cs4347.jdbcGame.dao.CreditCardDAO;
import cs4347.jdbcGame.entity.CreditCard;
import cs4347.jdbcGame.util.DAOException;

public class CreditCardDAOImpl implements CreditCardDAO
{

    @Override
    public CreditCard create(Connection connection, CreditCard creditCard, Long playerID)
            throws SQLException, DAOException
    {
        throw new RuntimeException("Must Be Implemented");
        //return null;
    }

    @Override
    public CreditCard retrieve(Connection connection, Long ccID) throws SQLException, DAOException
    {
        throw new RuntimeException("Must Be Implemented");
        //return null;
    }

    @Override
    public List<CreditCard> retrieveCreditCardsForPlayer(Connection connection, Long playerID)
            throws SQLException, DAOException
    {
        throw new RuntimeException("Must Be Implemented");
        //return null;
    }

    @Override
    public int update(Connection connection, CreditCard creditCard) throws SQLException, DAOException
    {
        throw new RuntimeException("Must Be Implemented");
        //return 0;
    }

    @Override
    public int delete(Connection connection, Long ccID) throws SQLException, DAOException
    {
        throw new RuntimeException("Must Be Implemented");
        //return 0;
    }

    @Override
    public int deleteForPlayer(Connection connection, Long playerID) throws SQLException, DAOException
    {
        throw new RuntimeException("Must Be Implemented");
        //return 0;
    }

    @Override
    public int count(Connection connection) throws SQLException, DAOException
    {
        throw new RuntimeException("Must Be Implemented");
        //return 0;
    }

}
