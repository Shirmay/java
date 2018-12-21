package com.zlb.sso.server.dao;

import com.zlb.sso.server.bean.UserBean;
import com.zlb.sso.server.spring.SpringContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class LoginDao {

    private JdbcTemplate jdbcTemplate = SpringContextHolder.getBean(JdbcTemplate.class);

    public UserBean getUserByLoginName(String loginName){
        final String sql = "select id, login_name, password, salt from sys_user t where t.login_name = ?";
        return jdbcTemplate.execute(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, loginName);
                return pst;
            }
        }, new PreparedStatementCallback<UserBean>() {
            @Override
            public UserBean doInPreparedStatement(PreparedStatement pst) throws SQLException, DataAccessException {
                ResultSet rs = pst.executeQuery();
                UserBean user = null;
                if (rs.next()){
                    user = new UserBean();
                    user.setId(rs.getLong("id"));
                    user.setPassword(rs.getString("password"));
                    user.setUsername(rs.getString("user_name"));
                    user.setLoginName(rs.getString("login_name"));
                }
                return user;
            }
        });
    }
}
