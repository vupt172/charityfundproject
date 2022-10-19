package Dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Api.StringAPI;
import Api.UserOperator;
import Context.DBConnector;
import Model.User;

public class UserDAO implements IDAO<User> {
	public static final int TOTAL_ITEMS_PER_PAGE = 8;
	public static final int ALL_ROLE = 0;
	public static final int ADMIN_ROLE = 1;
	public static final int USER_ROLE = 2;
	public static final int UNKNOWN_ROLE = -1;
	public static final String STATUS_NOTACTIVATED = "NotActivated";
	public static final String STATUS_ACTIVE = "Active";
	public static final String STATUS_INACTIVE = "Inactive";
	public static final String STATUS_BANNED = "Banned";

	public List<User> getPage(int page, String username, String email, String sdt, String roleName) {
		try {
			String sql = "select * from user where ( username like ? and email like ? and sdt like ? and role like?) limit ? offset ?";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + username + "%");
			ps.setString(2, "%" + email + "%");
			ps.setString(3, "%" + sdt + "%");
			int role = getRoleByName(roleName);
			if (role == ALL_ROLE) {
				ps.setString(4, "%" + "%");
			} else {
				ps.setString(4, "%" + role + "%");
			}

			ps.setInt(5, TOTAL_ITEMS_PER_PAGE);
			int offset = (page - 1) * TOTAL_ITEMS_PER_PAGE;
			ps.setInt(6, offset);
			ResultSet rs = ps.executeQuery();
			List<User> userList = new ArrayList<>();
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setRole(rs.getInt("role"));
				u.setFullName(rs.getString("fullname"));
				u.setSdt(rs.getString("sdt"));
				u.setEmail(rs.getString("email"));
				u.setDiachi(rs.getString("diachi"));
				u.setStatus(rs.getString("status"));
				userList.add(u);
			}
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getTotalItems(String username, String email, String sdt, String roleName) {
		try {
			String sql = "select count(*) from user where (username like ? and email like ? and sdt like ? and role like ?)";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + username + "%");
			ps.setString(2, "%" + email + "%");
			ps.setString(3, "%" + sdt + "%");
			int role = getRoleByName(roleName);
			if (role == ALL_ROLE) {
				ps.setString(4, "%" + "%");
			} else {
				ps.setString(4, "%" + role + "%");
			}
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return -1;
	}

	@Override
	public List<User> search(String name) {
		// TODO Auto-generated method stub

		try {
			String sql = "select * from user where username like ?";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + name + "%");
			ResultSet rs = ps.executeQuery();
			List<User> userList = new ArrayList<>();
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setRole(rs.getInt("role"));
				u.setFullName(rs.getString("fullname"));
				u.setSdt(rs.getString("sdt"));
				u.setEmail(rs.getString("email"));
				u.setDiachi(rs.getString("diachi"));
				u.setStatus(rs.getString("status"));

				userList.add(u);
			}
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
   public List<User> getByRole(int role){
	   try {
		   String sql="select * from user where role = ?";
		   Connection con=DBConnector.getMySQLConnection();
		   PreparedStatement ps=con.prepareStatement(sql);
		   ps.setInt(1, role);
		   ResultSet rs=ps.executeQuery();
		   List<User> userList=new ArrayList<>();
		   while(rs.next()) {
			   User u=getResultsetValue(rs);
			   userList.add(u);
		   }
		   return userList;
	   }
	   catch(Exception e) {
		   e.printStackTrace();
		   return null;
	   }
   }
	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from user where id = ?";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setRole(rs.getInt("role"));
				u.setFullName(rs.getString("fullname"));
				u.setSdt(rs.getString("sdt"));
				u.setEmail(rs.getString("email"));
				u.setDiachi(rs.getString("diachi"));
				u.setStatus(rs.getString("status"));
				return u;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findByName(String name) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from user where username = ?";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setRole(rs.getInt("role"));
				u.setFullName(rs.getString("fullname"));
				u.setSdt(rs.getString("sdt"));
				u.setEmail(rs.getString("email"));
				u.setDiachi(rs.getString("diachi"));
				u.setStatus(rs.getString("status"));
				return u;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
  public User findByEmail(String email) {
			try {
				String sql = "select * from user where email = ?";
				Connection con = DBConnector.getMySQLConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, email);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					User u = new User();
					u.setId(rs.getInt("id"));
					u.setUsername(rs.getString("username"));
					u.setPassword(rs.getString("password"));
					u.setRole(rs.getInt("role"));
					u.setFullName(rs.getString("fullname"));
					u.setSdt(rs.getString("sdt"));
					u.setEmail(rs.getString("email"));
					u.setDiachi(rs.getString("diachi"));
					u.setStatus(rs.getString("status"));
					return u;
				}
				return null;
	  }
	  catch(Exception e) {
		  e.printStackTrace();
		  return null;
	  }
  }
	@Override
	public User save(User item) {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into user(username,password,role,fullname,sdt,email,diachi,status) values (?,?,?,?,?,?,?,?)";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, item.getUsername());
			ps.setString(2, item.getPassword());
			ps.setInt(3, item.getRole());
			ps.setString(4, item.getFullName());
			ps.setString(5, item.getSdt());
			ps.setString(6, item.getEmail());
			ps.setString(7, item.getDiachi());
			ps.setString(8, item.getStatus());
			int row = ps.executeUpdate();
			if (row != 0) {
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
	public boolean update(User item) {
		// TODO Auto-generated method stub
		try {
			String sql = "update user set role=?,fullname=?,sdt=?,email=?,diachi=?,status=? where id =?";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, item.getRole());
			ps.setString(2, item.getFullName());
			ps.setString(3, item.getSdt());
			ps.setString(4, item.getEmail());
			ps.setString(5, item.getDiachi());
			ps.setString(6, item.getStatus());
			ps.setInt(7, item.getId());
			int row = ps.executeUpdate();
			if (row != 0)
				return true;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean updateUserInfo(String username,String fullname,String email,String sdt,String diachi) {
		try {
			String sql="update user set fullname=?,email=?,sdt=?,diachi=? where username=?";
			Connection con=DBConnector.getMySQLConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, fullname);
			ps.setString(2, email);
			ps.setString(3, sdt);
			ps.setString(4, diachi);
			ps.setString(5, username);
			int row=ps.executeUpdate();
			if(row!=0)return true;
			return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean updatePassword(String username,String password) {
		try {
			String sql="update user set password = ? where username= ?";
			Connection con=DBConnector.getMySQLConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, StringAPI.encodePassword(password));
			ps.setString(2, username);
			int row=ps.executeUpdate();
			if(row!=0)return true;
			return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateStatusAfterActivated(int userId) {
		try {
			String sql = "update user set status =? where id=?";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, UserDAO.STATUS_ACTIVE);
			ps.setInt(2, userId);
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
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		try {
			String sql = "delete from user where id = ?";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int row = ps.executeUpdate();
			if (row != 0)
				return true;
			return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean isExist(String username) throws SQLException,ClassNotFoundException {
		Connection con=DBConnector.getMySQLConnection();
		String sql="select count(*) from user where username=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			int count=rs.getInt(1);
			if(count>0)return true;
		}
		return false;
	}

	public boolean checkIfUserHasData(int userId) {
		try {
			System.out.println("checkifuserhasData");
			String sql = "select count(*) from donation where user_id = " + userId;
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				if (count != 0)
					return true;
				return false;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateStatus(String status, int id) {
		try {
			String sql = "update user set status =? where id=?";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, id);
			int row = ps.executeUpdate();
			if (row != 0)
				return true;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static int getRoleByName(String roleName) {
		if (roleName == "")
			return ALL_ROLE;
		else if (roleName.equalsIgnoreCase("admin"))
			return ADMIN_ROLE;
		else if (roleName.equalsIgnoreCase("user"))
			return USER_ROLE;
		else
			return UNKNOWN_ROLE;
	}

	public boolean activate(String username, String key) {
		try {
			String sql = "select count(*) from user where (username = ? and id = ?)";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				if (count == 1) {
					boolean updateResult = updateStatusAfterActivated(Integer.parseInt(key));
					if (updateResult)
						return true;
					return false;
				}
			}
			return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean check(String username, String password) throws ClassNotFoundException, SQLException ,NoSuchAlgorithmException{
			Connection con = DBConnector.getMySQLConnection();
			String sql = "select count(*) from user where (username = ? and password = ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, StringAPI.encodePassword(password));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				if (count > 0)
					return true;
			}
			return false;

	}

	public boolean updatePassword(String password, int userId) {
		try {
			String sql = "update user set password = ? where id =?";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, StringAPI.encodePassword(password));
			ps.setInt(2, userId);
			int row = ps.executeUpdate();
			if (row != 0)
				return true;
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public HashMap<String, Object> validate(String username, String password)
			throws SQLException, ClassNotFoundException,NoSuchAlgorithmException{
		HashMap<String, Object> bindingResults = new HashMap<>();
		boolean isExist = check(username, password);
		// check if user not exist
		if (!isExist) {
			bindingResults.put("isValidate", false);
			bindingResults.put("message", "Username or password is incorrect.");
			return bindingResults;
		}
		// check if user not active
		User user = this.findByName(username);
		String userStatus = user.getStatus();
		if (userStatus.equalsIgnoreCase(UserDAO.STATUS_NOTACTIVATED)) {
			bindingResults.put("isValidate", false);
			bindingResults.put("message", "Account is not activated yet.");
			return bindingResults;
		} else if (userStatus.equals(UserDAO.STATUS_BANNED)) {
			bindingResults.put("isValidate", false);
			bindingResults.put("message", "Account is banned");
			return bindingResults;
		} else if (userStatus.equals(UserDAO.STATUS_INACTIVE)) {
			bindingResults.put("isValidate", false);
			bindingResults.put("message", "Account is inactive");
			return bindingResults;
		}
		// ok
		bindingResults.put("isValidate", true);
		bindingResults.put("user", user);
		return bindingResults;
	}
	public User getResultsetValue(ResultSet rs) throws SQLException {
		User u = new User();
		u.setId(rs.getInt("id"));
		u.setUsername(rs.getString("username"));
		u.setPassword(rs.getString("password"));
		u.setRole(rs.getInt("role"));
		u.setFullName(rs.getString("fullname"));
		u.setSdt(rs.getString("sdt"));
		u.setEmail(rs.getString("email"));
		u.setDiachi(rs.getString("diachi"));
		u.setStatus(rs.getString("status"));
		return u;
	}
}
