package Dao;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

import Context.DBConnector;
import Model.Foundation;

public class FoundationDAO implements IDAO<Foundation> {
	public final int TOTAL_ITEMS_PER_PAGE = 8;
	public static final String STATUS_ENABLE = "Enable";
	public static final String STATUS_DISABLE = "Disable";

	@Override
	public List<Foundation> search(String foundationName) {
		try {
			Connection con = DBConnector.getMySQLConnection();
			String sql = "select * from foundation where name like ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + foundationName + "%");
			ResultSet rs = ps.executeQuery();
			List<Foundation> foundationList = new ArrayList<>();
			while (rs.next()) {
				Foundation f = getResultSetValue(rs);
				foundationList.add(f);
			}
			return foundationList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Foundation> getPage(int page, String id, String name, String email) {
		try {
			String sql = "select * from foundation where (id like ? and name like? and email like ?) order by name asc LIMIT ? OFFSET  ?";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);

			int offset = (page - 1) * TOTAL_ITEMS_PER_PAGE;
			ps.setString(1, "%" + id + "%");
			ps.setString(2, "%" + name + "%");
			ps.setString(3, "%" + email + "%");
			ps.setInt(4, TOTAL_ITEMS_PER_PAGE);
			ps.setInt(5, offset);
			ResultSet rs = ps.executeQuery();
			List<Foundation> foundationList = new ArrayList<>();
			while (rs.next()) {
				Foundation f = getResultSetValue(rs);
				foundationList.add(f);
			}
			return foundationList;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public int getTotalItems(String id, String name, String email) {

		try {
			String sql = "select count(*) as totalItems from foundation where id like ? and name like ? and email like ?";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + id + "%");
			ps.setString(2, "%" + name + "%");
			ps.setString(3, "%" + email + "%");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("totalItems");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	public Foundation findById(int id) {

		try {
			Connection con = DBConnector.getMySQLConnection();
			String sql = "select * from foundation where id= ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Foundation f = getResultSetValue(rs);
				return f;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Foundation findByName(String foundationName) {
		try {
			String sql = "select * from foundation where name = ?";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, foundationName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Foundation f = getResultSetValue(rs);
				return f;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Foundation save(Foundation item) {
		try {
			String sql = "insert into foundation(name,description,email,status) values (?,?,?,?)";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, item.getName());
			ps.setString(2, item.getDescription());
			ps.setString(3, item.getEmail());
			ps.setString(4, item.getStatus());
			int rows = ps.executeUpdate();
			if (rows == 1) {
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
	public boolean update(Foundation f) {
		try {
			String sql = "update foundation set name=?,description=?,email=?,status=? where id=?";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, f.getName());
			ps.setString(2, f.getDescription());
			ps.setString(3, f.getEmail());
			ps.setString(4, f.getStatus());
			ps.setInt(5, f.getId());
			int row = ps.executeUpdate();
			if (row != 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateStatus(int foundationId, String status) {
		try {
			String sql = "update foundation set status =? where id =?";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, foundationId);
			int row = ps.executeUpdate();
			if (row != 0)
				return true;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int foundationId) {

		try {
			String sql = "delete from foundation where id =?";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, foundationId);
			int row = ps.executeUpdate();
			if (row != 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean multipleDelete(String listId[]) throws SQLException, ClassNotFoundException {
		Connection con = DBConnector.getMySQLConnection();
		con.setAutoCommit(false);
		System.out.println(listId.length);
		try {
			for (String foundationId : listId) {
				String sql = "delete from foundation where id =?";
				PreparedStatement ps = con.prepareStatement(sql);
				System.out.println("item" + foundationId);
				ps.setString(1, foundationId);
				int row = ps.executeUpdate();
				if (row == 0) {
					con.rollback();
					return false;
				}
			}
			con.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
			return false;
		}
	}

	public Foundation getResultSetValue(ResultSet rs) throws SQLException {
		Foundation f = new Foundation();
		f.setId(rs.getInt("id"));
		f.setName(rs.getString("name"));
		f.setDescription(rs.getString("description"));
		f.setEmail(rs.getString("email"));
		f.setStatus(rs.getString("status"));
		return f;
	}
}
