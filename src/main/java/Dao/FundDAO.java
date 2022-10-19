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
import Model.Foundation;
import Model.Fund;

public class FundDAO implements IDAO<Fund> {
	public final int TOTAL_ITEMS_PER_PAGE = 8;
	public static final int TOTAL_ITEMS_PER_CLIENTPAGE=6;
	public static final String STATUS_OPENING = "Opening";
	public static final String STATUS_WAITING = "Waiting";
	public static final String STATUS_CLOSE = "Close";
	public static final String STATUS_FINISH = "Finish";
	public static final String STATUS_DISABLE ="Disable";

	public List<Fund> getPage(int page, String id, String name, String foundation_name, String category_name) {
		try {
			String sql = "select * from fund " + "where (id like ? and name like ?"
					+ " and foundation_id in ( select id from foundation where name like ?)"
					+ " and category_id in ( select id from category where name like ?)"
					+ " ) order by name asc limit ? offset ?";

			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			int offset = (page - 1) * TOTAL_ITEMS_PER_PAGE;
			ps.setString(1, "%" + id + "%");
			ps.setString(2, "%" + name + "%");
			ps.setString(3, "%" + foundation_name + "%");
			ps.setString(4, "%" + category_name + "%");
			ps.setInt(5, TOTAL_ITEMS_PER_PAGE);
			ps.setInt(6, offset);

			ResultSet rs = ps.executeQuery();
			List<Fund> fundList = new ArrayList<>();
			while (rs.next()) {
				Fund f = new Fund();
				f.setId(rs.getInt("fund.id"));
				f.setName(rs.getString("fund.name"));
				f.setDescription(rs.getString("fund.description"));
				f.setContent(rs.getString("fund.content"));
				f.setImage_url(rs.getString("fund.image_url"));
				f.setExpectedResult(rs.getInt("fund.expected_result"));
				f.setStatus(rs.getString("fund.status"));
				f.setCreatedDate(rs.getDate("created_date"));
				f.setEndDate(rs.getDate("end_date"));
				// foundation
				int foundationId = rs.getInt("foundation_id");
				Foundation foundation = new FoundationDAO().findById(foundationId);
				f.setFoundation(foundation);
				// cateogry
				int categoryId = rs.getInt("category_id");
				;
				Category category = new CategoryDAO().findById(categoryId);
				f.setCategory(category);
				fundList.add(f);
			}
			return fundList;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	public List<Fund> getClientPage(int page,String status){
		try {
			Connection con=DBConnector.getMySQLConnection();
			String sql="select * from fund where status = ? order by created_date desc limit ? offset ?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, TOTAL_ITEMS_PER_CLIENTPAGE);
			int offset=(page-1)*TOTAL_ITEMS_PER_CLIENTPAGE;
			ps.setInt(3, offset);
			ResultSet rs=ps.executeQuery();
			List<Fund> fundList=new ArrayList<>();
			while(rs.next()) {
				Fund f=getResultSetValue(rs);
				fundList.add(f);
			}
			return fundList;	
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	public int getTotalItems(String status) {
		try {
			String sql="select count(*) from fund where status = ?";
			Connection con=DBConnector.getMySQLConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, status);
			ResultSet rs=ps.executeQuery();
			if(rs.next())return rs.getInt(1);
			return 0;
		}
		catch(Exception e) {
			e.printStackTrace();	
			return 0;
		}
	}

	public int getTotalItems(String id, String name, String foundation_name, String category_name) {

		try {
			String sql = "select count(*) from fund " + "where (id like ? and name like ?"
					+ " and foundation_id in ( select id from foundation where name like ?)"
					+ " and category_id in ( select id from category where name like ?)" + " )";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + id + "%");
			ps.setString(2, "%" + name + "%");
			ps.setString(3, "%" + foundation_name + "%");
			ps.setString(4, "%" + category_name + "%");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Fund findById(int fundId) {
		try {
			String sql = "select * from fund where id=?";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, fundId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Fund f = new Fund();
				f.setId(rs.getInt("id"));
				f.setName(rs.getString("name"));
				f.setDescription(rs.getString("description"));
				f.setStatus(rs.getString("status"));
				f.setCreatedDate(rs.getDate("created_date"));
				f.setEndDate(rs.getDate("end_date"));
				f.setExpectedResult(rs.getInt("expected_result"));
				f.setContent(rs.getString("content"));
				f.setImage_url(rs.getString("image_url"));
				// category
				int categoryId = rs.getInt("category_id");
				Category category = new CategoryDAO().findById(categoryId);
				f.setCategory(category);
				// foundation
				int foundationId = rs.getInt("foundation_id");
				Foundation foundation = new FoundationDAO().findById(foundationId);
				f.setFoundation(foundation);
				return f;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Fund save(Fund item) {
		try {
			String sql = "insert into fund(name,description,content,image_url,expected_result,status,created_date,end_date,category_id,foundation_id)"
					+ "values (?,?,?,?,?,?,?,?,?,?)";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, item.getName());
			ps.setString(2, item.getDescription());
			ps.setString(3, item.getContent());
			ps.setString(4, item.getImage_url());
			ps.setInt(5, item.getExpectedResult());
			ps.setString(6, item.getStatus());
			ps.setDate(7, item.getCreatedDate());
			System.out.println(item.getCreatedDate());
			ps.setDate(8, item.getEndDate());
			ps.setInt(9, item.getCategory().getId());
			ps.setInt(10, item.getFoundation().getId());
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
	public boolean update(Fund f) {
		try {
			String sql = "update fund set name=?,description=?,content=?,image_url=?,expected_result=?,status=?,created_date=?,end_date=?,category_id=?,foundation_id=? where id=?";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, f.getName());
			ps.setString(2, f.getDescription());
			ps.setString(3, f.getContent());
			ps.setString(4, f.getImage_url());
			ps.setInt(5, f.getExpectedResult());
			ps.setString(6, f.getStatus());
			ps.setDate(7, f.getCreatedDate());
			ps.setDate(8, f.getEndDate());
			ps.setInt(9, f.getCategory().getId());
			ps.setInt(10, f.getFoundation().getId());
			ps.setInt(11, f.getId());
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
	public boolean updateStatus(int fundId,String status) {
		try {
			String sql="update fund set status =? where id =?";
			Connection con=DBConnector.getMySQLConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, fundId);
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
	public boolean delete(int fundId) {
		try {
			String sql = "delete from fund where id =?";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, fundId);
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
		try {
			for (String fundId : listId) {
				String sql = "delete from fund where id =?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, fundId);
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
	public boolean isExistByCategory(int categoryId) {
		try {
			String sql="select count(*) from fund where category_id =?";
			Connection con=DBConnector.getMySQLConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, categoryId);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				int count=rs.getInt(1);
				if(count>0)return true;
			}
			return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean isExistByFoundation(int foundationId) {
		try {
			String sql="select count(*) from fund where foundation_id=?";
			Connection con=DBConnector.getMySQLConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, foundationId);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				int count=rs.getInt(1);
				if(count>0)return true;
			}
			return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Fund> search(String name) {
		// TODO Auto-generated method stub
		System.out.println("FundDAO fundName :"+name);
		try {
			String sql = "select * from fund where name like ?";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + name + "%");
			ResultSet rs = ps.executeQuery();
			List<Fund> fundList = new ArrayList<>();
			while (rs.next()) {
				Fund f = new Fund();
				f.setId(rs.getInt("id"));
				f.setName(rs.getString("name"));
				f.setDescription(rs.getString("description"));
				f.setStatus(rs.getString("status"));
				f.setCreatedDate(rs.getDate("created_date"));
				f.setEndDate(rs.getDate("end_date"));
				f.setExpectedResult(rs.getInt("expected_result"));
				f.setContent(rs.getString("content"));
				f.setImage_url(rs.getString("image_url"));
				// category
				int categoryId = rs.getInt("category_id");
				Category category = new CategoryDAO().findById(categoryId);
				f.setCategory(category);
				// foundation
				int foundationId = rs.getInt("foundation_id");
				Foundation foundation = new FoundationDAO().findById(foundationId);
				f.setFoundation(foundation);
				fundList.add(f);
			}
			return fundList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Fund> getOpeningFund(int limit) {
		try {
			String sql = "";
			if (limit > 0) {
				sql = "select * from fund where (status = ? and end_date > ?) order by created_date desc limit "
						+ limit;
			} else {
				sql = "select * from fund where (status = ? and end_date > ?) order by created_date desc";
			}
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, STATUS_OPENING);
			ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
			ResultSet rs = ps.executeQuery();
			List<Fund> fundList = new ArrayList<>();
			while (rs.next()) {
				Fund f = getResultSetValue(rs);
				fundList.add(f);
			}
			return fundList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Fund> getFinishFund(int limit) {
		try {
			String sql = "select * from fund where (status = ?) order by created_date desc";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, STATUS_FINISH);
			ResultSet rs = ps.executeQuery();
			List<Fund> fundList = new ArrayList<>();
			while (rs.next()) {
				Fund f = getResultSetValue(rs);
				fundList.add(f);
			}
			return fundList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Fund> getFundListByCategory(int categoryId) {
		try {
			List<Fund> fundList = new ArrayList<>();
			Connection con = DBConnector.getMySQLConnection();
			String sql = "select * from fund where category_id =" + categoryId;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Fund f = getResultSetValue(rs);
				fundList.add(f);
			}

			return fundList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Fund findByName(String name) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from fund where name=?";
			Connection con = DBConnector.getMySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Fund f = getResultSetValue(rs);
				return f;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Fund getResultSetValue(ResultSet rs) throws SQLException {
		Fund f = new Fund();
		f.setId(rs.getInt("id"));
		f.setName(rs.getString("name"));
		f.setDescription(rs.getString("description"));
		f.setStatus(rs.getString("status"));
		f.setCreatedDate(rs.getDate("created_date"));
		f.setEndDate(rs.getDate("end_date"));
		f.setExpectedResult(rs.getInt("expected_result"));
		f.setContent(rs.getString("content"));
		f.setImage_url(rs.getString("image_url"));
		// category
		int categoryId = rs.getInt("category_id");
		Category category = new CategoryDAO().findById(categoryId);
		f.setCategory(category);
		// foundation
		int foundationId = rs.getInt("foundation_id");
		Foundation foundation = new FoundationDAO().findById(foundationId);
		f.setFoundation(foundation);
		return f;
	}
}
