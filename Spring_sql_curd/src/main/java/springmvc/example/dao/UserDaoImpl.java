package springmvc.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import springmvc.example.model.User;

@Repository
public class UserDaoImpl implements UserDao{

	NamedParameterJdbcTemplate namedparameterjdbctemplate;
	
	@Autowired
	public void setNamedparameterjdbctemplate(NamedParameterJdbcTemplate namedparameterjdbctemplate) {
		this.namedparameterjdbctemplate = namedparameterjdbctemplate;
	}

	public List<User> listAllUsers() {
		//video add select id,firstname...
		String sql = "select * from users";
		List<User> list = namedparameterjdbctemplate.query(sql,getSqlParameterByModel(null),new UserMapper());
		return null;
	}

	private SqlParameterSource getSqlParameterByModel(User user){
		MapSqlParameterSource parametersourse = new MapSqlParameterSource();
		if(user!=null){
			parametersourse.addValue("id",user.getId());
			parametersourse.addValue("firstname",user.getFirstname());
			parametersourse.addValue("lastname",user.getLastname());
			parametersourse.addValue("address",user.getAddress());
		}
		return parametersourse;	
	}
	
	private static final class UserMapper implements RowMapper<User>{

		public User mapRow(ResultSet rs, int rownum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setFirstname(rs.getString("firstname"));
			user.setLastname(rs.getString("lastname"));
			user.setAddress(rs.getString("address"));
			
			return user;
		}
		
	}
	
	public void addUser(User user) {
		String sql = "insert into users(firstname,lastname,address)values(:firstname, :lastname, :address)";
		namedparameterjdbctemplate.update(sql, getSqlParameterByModel(user));
	}

	public void updateUser(User user) {
		String sql = "update users set firstname = :firstname, lastname = :lastname, address = :address where id = :id";
		namedparameterjdbctemplate.update(sql, getSqlParameterByModel(user));
		
	}

	public void deleteUser(int id) {
		String sql = "delete from users where id = :id";
		namedparameterjdbctemplate.update(sql, getSqlParameterByModel(new User(id)));
		
	}

	public User findUserById(int id) {
		String sql = "select * from users where id = :id";
		return namedparameterjdbctemplate.queryForObject(sql, getSqlParameterByModel(new User(id)), new UserMapper());
	}

}
