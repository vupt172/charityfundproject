package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Context.DBConnector;
import Model.Donation;

public class DonationDAO implements IDAO<Donation>{
	public static final int TOTAL_ITEMS_PER_PAGE =8;
    public static final int ORDERBY_ASC =1;
    public static final int ORDERBY_DESC=2;
    public static final int ORDERBY_NONE=0;
	public List<Donation> getPage(int page,String id,String username,String fundName){
		try {
			String sql="select * from donation where (id like ? "
					+ "and user_id in (select id from user where username like ?) "
					+ "and fund_id in (select id from fund where name like ?) )"
					+ "limit ? offset ?";
			Connection con=DBConnector.getMySQLConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,"%"+id+"%");
			ps.setString(2,"%"+username+"%");
			ps.setString(3,"%"+fundName+"%");
			ps.setInt(4, TOTAL_ITEMS_PER_PAGE);
			int offset=(page-1)*TOTAL_ITEMS_PER_PAGE;
			ps.setInt(5,offset);
			ResultSet rs=ps.executeQuery();
			List<Donation> donationList=new ArrayList<>();
			while(rs.next()) {
				System.out.println("ok");
				Donation d=getResultSetValue(rs);
				donationList.add(d);
			}
			return donationList;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public int getTotalItems(String id,String username,String fundName) {
		try {
			String sql="select count(*) from donation where (id like ?"
					+ "and user_id in (select id from user where username like ?)"
					+ "and fund_id in(select id from fund where name like ?) )";
			Connection con=DBConnector.getMySQLConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,"%"+id+"%");
			ps.setString(2,"%"+username+"%");
			ps.setString(3,"%"+fundName+"%");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public List<Donation> search(String name) {
		// TODO Auto-generated method stub
	//Connection con=DBConnector.getMySQLConnection();
	
		return null;
	}
   public List<Donation> getDonationList(){
	   try {
		   String sql="select * from donation";
		   Connection con=DBConnector.getMySQLConnection();
		   PreparedStatement ps=con.prepareStatement(sql);
		   ResultSet rs=ps.executeQuery();
		   List<Donation> donationList=new ArrayList<>();
		   if(rs.next()) {
			 Donation donation=getResultSetValue(rs);
			 donationList.add(donation);
		   }
		   return donationList;
	   }
	   catch(Exception e) {
		  e.printStackTrace();
		  return null;
	   }
   }
	@Override
	public Donation findById(int id) {
		// TODO Auto-generated method stub
	try {
		String sql="select * from donation where id = ?";
		Connection con=DBConnector.getMySQLConnection();
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			return getResultSetValue(rs);
		}
		return null;
	}
	catch(Exception e) {
		e.printStackTrace();
		return null;
	}
	}

	@Override
	public Donation findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Donation save(Donation item) {
		// TODO Auto-generated method stub
		try {
			String sql="insert into donation(donation_amount,donation_message,created_date,user_id,fund_id) values(?,?,?,?,?)";
			Connection con=DBConnector.getMySQLConnection();
			PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			System.out.println(item.getDonationAmount());
			ps.setInt(1,item.getDonationAmount());
			ps.setString(2, item.getDonationMessage());
			ps.setDate(3, item.getCreatedDate());
			ps.setInt(4, item.getUser().getId());
			ps.setInt(5, item.getFund().getId());
			int row=ps.executeUpdate();
			if(row!=0) {
				System.out.println("add donate success");
				ResultSet generateKeys=ps.getGeneratedKeys();
				if(generateKeys.next()) {
					item.setId(generateKeys.getInt(1));
					return item;
				}
			}
			return null;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(Donation item) {
		// TODO Auto-generated method stub
		try {
			String sql="update donation set donation_amount=?,donation_message=?,user_id=?,fund_id=? where id=?";
			Connection con=DBConnector.getMySQLConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, item.getDonationAmount());
			ps.setString(2, item.getDonationMessage());
			ps.setInt(3, item.getUser().getId());
			ps.setInt(4,item.getFund().getId());
			ps.setInt(5, item.getId());
			int row=ps.executeUpdate();
			if(row!=0) {
				return true;
			}
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
			String sql="delete from donation where id =?";
			Connection con=DBConnector.getMySQLConnection();
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			int row=ps.executeUpdate();
			if(row!=0) {
				return true;
			}
			return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean multipledelete(String listId[]) throws SQLException,ClassNotFoundException{
		Connection con = DBConnector.getMySQLConnection();
		con.setAutoCommit(false);
		System.out.println(listId.length);
		try {
			for (String donationId : listId) {
				String sql = "delete from donation where id =?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, donationId);
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
    public int getTotalDonations(int fundId)  {
    	try {
    		String sql="select sum(donation_amount) from donation where fund_id = "+fundId;
    		Connection con=DBConnector.getMySQLConnection();
    		PreparedStatement ps=con.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		if(rs.next()) {
    			return rs.getInt(1);
    		}
    		return 0;
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return 0;
    	}
    }
    public int getTotalDonationTimes(int fundId) {
    	try {
    		String sql="select count(*) from donation where fund_id = "+fundId;
    		Connection con=DBConnector.getMySQLConnection();
    		PreparedStatement ps=con.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		if(rs.next()) {
    			return rs.getInt(1);
    		}
    		return 0;
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return 0;
    	}
    }
    public Donation getResultSetValue(ResultSet rs) throws SQLException{
    	Donation d=new Donation();
    	d.setId(rs.getInt("id"));
    	d.setDonationAmount(rs.getInt("donation_amount"));
    	d.setDonationMessage(rs.getString("donation_message"));
    	d.setCreatedDate(rs.getDate("created_date"));
    	// fund
    	int fundId=rs.getInt("fund_id");
    	FundDAO fundDAO=new FundDAO();
    	d.setFund(fundDAO.findById(fundId));
    	// user
    	int userId=rs.getInt("user_id");
    	UserDAO userDAO=new UserDAO();
    	d.setUser(userDAO.findById(userId));
    	return d;
    }
    public List<Donation> getPageDonationListByUser(int page,int userId){
    	try {
    		String sql="select * from donation where user_id =? order by created_date desc limit ? offset ?";
    		Connection con=DBConnector.getMySQLConnection();
    		PreparedStatement ps=con.prepareStatement(sql);
    		ps.setInt(1, userId);
    		ps.setInt(2, DonationDAO.TOTAL_ITEMS_PER_PAGE);
    		int offset=(page-1)*TOTAL_ITEMS_PER_PAGE;
    		ps.setInt(3, offset);
    		ResultSet rs=ps.executeQuery();
    		List<Donation> donationList=new ArrayList<>();
    		while(rs.next()) {
    			Donation d=getResultSetValue(rs);
    			donationList.add(d);
    		}
    		return donationList;
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    public int getTotalDonationByUser(int userId) {
    	try {
    		String sql="select count(*) from donation where user_id = "+userId;
    		Connection con=DBConnector.getMySQLConnection();
    		PreparedStatement ps=con.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		if(rs.next())return rs.getInt(1);
    		return 0;
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return 0;
    	}
    }
    public boolean isExistByFund(int fundId) {
    	try {
    		String sql="select count(*) from donation where fund_id = ?";
    		Connection con=DBConnector.getMySQLConnection();
    		PreparedStatement ps=con.prepareStatement(sql);
    		ps.setInt(1, fundId);
    		ResultSet rs=ps.executeQuery();
    		if(rs.next()) {
    			int row=rs.getInt(1);
    			if(row>0)return true;
    		}
    		return false;
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    public List<Donation> getDonationListByFund(int fundId,int limit){
     try {
    	 String sql="select * from donation where fund_id =? order by created_date desc limit ?";
    	 Connection con=DBConnector.getMySQLConnection();
    	 PreparedStatement ps=con.prepareStatement(sql);
    	 ps.setInt(1, fundId);
    	 ps.setInt(2, limit);
    	 List<Donation> donationList=new ArrayList<>();
    	 ResultSet rs=ps.executeQuery();
    	 while(rs.next()) {
    		 Donation d=getResultSetValue(rs);
    		donationList.add(d);
    	 }
    	 return donationList;
     }
     catch(Exception e) {
    	 e.printStackTrace();
    	 return null;
     }
    }
    public List<Donation> getDonationListSortByTime(int order,int limit){
    	try {
    		String sql="";
    		if(order==ORDERBY_ASC) {
    			sql+="select * from donation order by id asc";
    		}
    		else if(order==ORDERBY_DESC) {
    			sql+="select * from donation order by id desc";
    		}
    		else if(order==ORDERBY_NONE) {
    			sql+="select * from donation";
    		}
    		if(limit>0) {
    			sql+=" limit "+limit;
    		}
         Connection con=DBConnector.getMySQLConnection();
         PreparedStatement ps=con.prepareStatement(sql);
         ResultSet rs=ps.executeQuery();
         List<Donation> donationList=new ArrayList<>();
         while(rs.next()) {
        	 Donation d=getResultSetValue(rs);
        	 donationList.add(d);
         }
         return donationList;
    	}    	
    	catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    public List<Donation> getDonationListByCategory(int categoryId,int limit){
    	try {
    		String sql="select donation.* from donation"
    				+ "inner join fund on donation.fund_id=fund.id"
    				+ "inner join category on fund.category_id=category.id"
    				+ "order by donation.created-date desc limit ?";
    		Connection con=DBConnector.getMySQLConnection();
    		PreparedStatement ps=con.prepareStatement(sql);
    		ps.setInt(1, limit);
    		ResultSet rs=ps.executeQuery();
    	    List<Donation> donationList=new ArrayList<>();
    	    while(rs.next()) {
    	    	Donation d=getResultSetValue(rs);
    	    	donationList.add(d);
    	    }
    	    return donationList;
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
   public String test() {return "hello";}
}
