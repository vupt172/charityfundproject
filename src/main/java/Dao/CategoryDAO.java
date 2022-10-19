package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

import Context.DBConnector;
import Model.Category;

public class CategoryDAO implements IDAO<Category> {
	public static final int TOTAL_ITEMS_PER_PAGE = 8;
	public static final String STATUS_ENABLE ="Enable";
	public static final String STATUS_DISABLE="Disable";

	public List<Category> getPage(int page, String id, String name) {
		try {
			String sql = "select * from category where (id like ? and name like ?) limit ? offset ?";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + id + "%");
			ps.setString(2, "%" + name + "%");
			ps.setInt(3, TOTAL_ITEMS_PER_PAGE);
			int offset = (page - 1) * TOTAL_ITEMS_PER_PAGE;
			ps.setInt(4, offset);
			ResultSet rs = ps.executeQuery();
			List<Category> categoryList = new ArrayList<>();
			while (rs.next()) {
				Category c=getResultSetValue(rs);
				categoryList.add(c);
			}
			return categoryList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getTotalItems(String id, String name) {
		try {
			String sql = "select count(*) from category where (id like ? and name like ?)";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + id + "%");
			ps.setString(2, "%" + name + "%");
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return rs.getInt(1);
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public List<Category> search(String categoryName) {
		try {
			Connection con = DBConnector.getMySQLConnection();
			String sql = "select * from category where name like ? and status = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + categoryName + "%");
			ps.setString(2,this.STATUS_ENABLE);
			ResultSet rs = ps.executeQuery();
			List<Category> categoryList = new ArrayList<>();
			while (rs.next()) {
				Category c=getResultSetValue(rs);
				categoryList.add(c);
			}
			return categoryList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Category findById(int categoryId) {
		try {
			Connection con = DBConnector.getMySQLConnection();
			String sql = "select * from category where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, categoryId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Category c=getResultSetValue(rs);
				return c;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Category findByName(String categoryName) {
		try {
			Connection con = DBConnector.getMySQLConnection();
			String sql = "select * from category where name =?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, categoryName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Category c =getResultSetValue(rs);
				return c;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Category save(Category item) {
		// TODO Auto-generated method stub
		try {
			String sql="insert into category(name,description,status) values(?,?,?)";
			Connection con=DBConnector.getMySQLConnection();
			PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,item.getName());
			ps.setString(2,item.getDescription());
			ps.setString(3, item.getStatus());
			int row=ps.executeUpdate();
			if(row!=0) {
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
					item.setId(generatedKeys.getInt(1));
					return item;
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(Category item) {
		// TODO Auto-generated method stub
		try {
		String sql="update category set name =?,description=?,status=? where id =?";
		Connection con=DBConnector.getMySQLConnection();
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, item.getName());
		ps.setString(2, item.getDescription());
		ps.setString(3, item.getStatus());
		ps.setInt(4, item.getId());
		int row=ps.executeUpdate();
		if(row!=0)return true;
		else return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean updateStatus(int categoryId,String status) {
		try {
			String sql="update category set status =? where id =?";
			Connection con=DBConnector.getMySQLConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, categoryId);
			int row=ps.executeUpdate();
			if(row!=0)return true;
			return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		try {
			String sql="delete from category where id =?";
			Connection con=DBConnector.getMySQLConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			int row=ps.executeUpdate();
			if(row!=0)return true;
			return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean multipleDelete(String listId[]) throws SQLException, ClassNotFoundException{
		Connection con=DBConnector.getMySQLConnection();
		con.setAutoCommit(false);
		try {
		for(String categoryId:listId) {
			String sql="delete from category where id = ? ";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, categoryId);
			int row=ps.executeUpdate();
			if(row==0) {
				con.rollback();
				return false;
			}
		}
		con.commit();
		return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			con.rollback();
			return false;
		}
	}
	public Category getResultSetValue(ResultSet rs) throws SQLException {
		Category c = new Category();
		c.setId(rs.getInt("id"));
		c.setName(rs.getString("name"));
		c.setDescription(rs.getString("description"));
		c.setStatus(rs.getString("status"));
		return c;
	}
}
