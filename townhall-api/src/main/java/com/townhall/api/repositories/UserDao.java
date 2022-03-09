//package com.townhall.api.repositories;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Repository;
//
//import com.springsecurityexample.domain.Authorities;
//import com.springsecurityexample.domain.Users;
//
//@Repository
//public class UserDao implements UserDetailsService
//{
//	@Autowired
//	private DataSource dataSource;
//	private JdbcTemplate template;
//
//	public UserDetails loadUserByUsername(String username)
//	    throws UsernameNotFoundException
//	{
//		String sql = "select * from users where username = ?";
//		Users user = null;
//		List<Users> query = getJdbcTemplate().query(sql,
//		    new Object[] { username }, new RowMapper<Users>()
//		    {
//			    public Users mapRow(ResultSet rs, int rowNum) throws SQLException
//			    {
//				    Users user = new Users();
//				    if (user.getUsername() == null)
//					    user.setUsername(rs.getString("username"));
//				    if (user.getPassword() == null)
//					    user.setPassword(rs.getString("password"));
//				    return user;
//			    }
//		    });
//    if (query.size() == 0)
//    	throw new UsernameNotFoundException("No user found with that username");
//    else
//    	user = query.get(0);
//    
//		sql = "select authority from authorities where username = ?";
//		List<String> authorities = getJdbcTemplate().queryForList(sql,
//		    new Object[] { username }, String.class);
//		Set<Authorities> userAuths = new HashSet<Authorities>();
//		for (String authority : authorities)
//		{
//			userAuths.add(new Authorities(username, authority));
//		}
//		user.setAuthorities(userAuths);
//		return user;
//	}
//
//	private JdbcTemplate getJdbcTemplate()
//	{
//		if (template == null)
//			return new JdbcTemplate(dataSource);
//		else
//			return this.template;
//	}
//}
