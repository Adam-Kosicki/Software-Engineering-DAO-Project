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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cs4347.jdbcGame.dao.GameDAO;
import cs4347.jdbcGame.entity.Game;
import cs4347.jdbcGame.util.DAOException;

public class GameDAOImpl implements GameDAO
{
	private static final String insertSQL = "INSERT INTO game (title, description, releaseDate, version) VALUES ( ?, ?, ?, ?);";
    private static final String retrieveSQL = "SELECT * FROM game WHERE id = ?;";
    private static final String updateSQL = "UPDATE game SET title = ?, description = ?, releaseDate =?, version = ? WHERE id = ?;";
    private static final String deleteSQL = "DELETE FROM game WHERE id = ?;";
    private static final String countSQL = "SELECT COUNT(*) FROM game;";
    private static final String listSQL = "SELECT * FROM game WHERE releaseDate BETWEEN ? AND ?;";
    private static final String retrieve2SQL = "SELECT * FROM game WHERE title = ?;";

    @Override
    public Game create(Connection connection, Game game) throws SQLException, DAOException
    {
    	if (game.getId() != null) {
            throw new DAOException("Trying to insert Game with NON-NULL ID");
        }

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, game.getTitle());
            ps.setString(2, game.getDescription());
            ps.setDate(3, new java.sql.Date(game.getReleaseDate().getTime()));
            ps.setString(4, game.getVersion());
            ps.executeUpdate();

            // Copy the assigned ID to the customer instance.
            ResultSet keyRS = ps.getGeneratedKeys();
            keyRS.next();
            int lastKey = keyRS.getInt(1);
            game.setId((long) lastKey);
            return game;
        }
        finally {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        }
    }

    @Override
    public Game retrieve(Connection connection, Long gameID) throws SQLException, DAOException
    {
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	Game g1 = new Game();
    	if(!(gameID instanceof Long)) {
    		g1 = null;
    		throw new DAOException("gameID is not a long type variable");
    	}
        try {
            ps = connection.prepareStatement(retrieveSQL);
            ps.setLong(1, gameID);

            rs = ps.executeQuery();
            //return null if no row found (for test retrieve fail unit test)
            if(!rs.first()) {
            	return null;
            }
            rs.first();
            
            g1.setTitle(rs.getString("title"));
            g1.setDescription(rs.getString("description"));
            g1.setVersion(rs.getString("version"));
            g1.setId(gameID);
            java.util.Date utilDate = rs.getDate("releaseDate");
    		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); 
            g1.setReleaseDate(sqlDate);
            return g1;
        }
        finally {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        }
    }

    @Override
    public int update(Connection connection, Game game) throws SQLException, DAOException
    {
    	PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(updateSQL,Statement.RETURN_GENERATED_KEYS);
            
            
            ps.setString(1, game.getTitle());
            ps.setString(2, game.getDescription());
            ps.setDate(3, new java.sql.Date(game.getReleaseDate().getTime()));
            ps.setString(4, game.getVersion());
            ps.setLong(5, game.getId());

            int rows = ps.executeUpdate();

            // Copy the assigned ID to the customer instance.
            
            return rows;
        }
        finally {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        }
    }

    @Override
    public int delete(Connection connection, Long id) throws SQLException, DAOException
    {
    	PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(deleteSQL);
            ps.setLong(1, id);
            int rows = ps.executeUpdate();

            
            return rows;
        }
        finally {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        }
    }

    @Override
    public int count(Connection connection) throws SQLException, DAOException
    {
    	int rows = 0;
    	PreparedStatement ps = null;
    	
        try {
            ps = connection.prepareStatement(countSQL);
           
            ResultSet rs = ps.executeQuery();
            rs.next();
            rows = rs.getInt("COUNT(*)");
            return rows;
        }
        finally {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }            
        }
    }


    @Override
    public List<Game> retrieveByTitle(Connection connection, String title) throws SQLException, DAOException
    {
    	
    	PreparedStatement ps = null;
    	try {
    		ps = connection.prepareStatement(retrieve2SQL);
    		ps.setString(1, title);
    		ResultSet rs = ps.executeQuery();
    		
    		List<Game> titles = new ArrayList<Game>();
    		while (rs.next()) {
    			Game g1 = new Game();
    			g1.setTitle(rs.getString("title"));
                g1.setDescription(rs.getString("description"));
                g1.setVersion(rs.getString("version"));
                g1.setId(rs.getLong("id"));
                java.util.Date utilDate = rs.getDate("releaseDate");
        		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                g1.setReleaseDate(sqlDate);
                
        		titles.add(g1);
    		}	
    		return titles;
    	}
    	
    	finally {
    		if (ps != null && !ps.isClosed())
    			ps.close();
    	}
    }

    @Override
    public List<Game> retrieveByReleaseDate(Connection connection, Date start, Date end)
            throws SQLException, DAOException
    {
    	PreparedStatement ps = null;
    	Game g1 = new Game();
    	
        try {
            ps = connection.prepareStatement(listSQL);
            
            java.sql.Date sqlStart = new java.sql.Date(start.getTime());
            java.sql.Date sqlEnd = new java.sql.Date(end.getTime());
            
            ps.setDate(1,  sqlStart);
            ps.setDate(2,  sqlEnd);
            
            ResultSet rs = ps.executeQuery();
            ArrayList<Game> games = new ArrayList<Game>();
            int i =1;
            if(!rs.first()){
            	return null;
            }
            while(rs.next()) {
            	
            	g1.setTitle(rs.getString("title"));
                g1.setDescription(rs.getString("description"));
                g1.setVersion(rs.getString("version"));
                g1.setId(rs.getLong("id"));
                java.util.Date utilDate = rs.getDate("releaseDate");
        		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); 
                g1.setReleaseDate(sqlDate);
         
            	games.add(g1);
            	i++;
            }
            return games;
        }
        finally {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
        }
    }

    private Game extractFromRS(ResultSet rs) throws SQLException
    {
        Game game = new Game();
        game.setId(rs.getLong("id"));
        game.setTitle(rs.getString("title"));
        game.setDescription(rs.getString("description"));
        game.setReleaseDate(rs.getDate("releaseDate"));
        game.setVersion(rs.getString("version"));
        return game;
    }
}
