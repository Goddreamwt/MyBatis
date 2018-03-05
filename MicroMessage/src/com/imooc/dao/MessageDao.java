package com.imooc.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.imooc.bean.Message;
import com.imooc.db.DBAccess;
import com.sun.istack.internal.logging.Logger;

/*
 * 和message表相关的数据库操作
 */
public class MessageDao {
	/*
	 * 根据查询条件查询消息列表
	 */
	public List<Message> queryMessageList(String command, String description) {
		DBAccess dbAccess = new DBAccess();
		List<Message> messageList = new ArrayList<Message>();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			Message message = new Message();
			message.setCommand(command);
			message.setDescription(description);
			
			//通过sqlSeesion执行SQL语句
			messageList = sqlSession.selectList("Message.queryMessageList",message);//这里的Message是配置文件中的namespace
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(sqlSession !=null){
				sqlSession.close();
			}
		}
		return messageList;
	}
	
	
	/*
	 * 单条删除
	 */
	public void deleteOne(int id) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			//通过sqlSeesion执行SQL语句
			sqlSession.delete("Message.deleteOne",id);//这里的Message是配置文件中的namespace
			sqlSession.commit();//主动提交
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(sqlSession !=null){
				sqlSession.close();
			}
		}
	}
	/*
	 * 批量删除
	 */
	public void deleteBatch(List<Integer> ids) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			//通过sqlSeesion执行SQL语句
			sqlSession.delete("Message.deleteBatch",ids);//这里的Message是配置文件中的namespace
			sqlSession.commit();//主动提交
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(sqlSession !=null){
				sqlSession.close();
			}
		}
	}
	
//    public static void main(String[] args) {
//		MessageDao messageDao = new MessageDao();
//		messageDao.queryMessageList("", "");
//		Logger log;
		//级别由低到高
//		log.debug("adf");
//		log.info(message);
//		log.warn(message);
//		log.error(message);
//	}
	/*
	 * 根据查询条件查询消息列表
	 */
//	public List<Message> queryMessageList(String command, String description) {
//		List<Message> messageList = new ArrayList<Message>();
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/micro_message", "root", "root");
//			StringBuilder sql = new StringBuilder("SELECT ID,COMMAND,DESCRIPTION,CONTENT FROM MESSAGE WHERE 1=1");
//			List<String> paramList = new ArrayList<String>();
//			if (command != null && !"".equals(command.trim())) {
//				sql.append(" AND COMMAND=?");
//				paramList.add(command);
//			}
//			if (description != null && !"".equals(description.trim())) {
//				sql.append(" AND DESCRIPTION LIKE '%' ? '%'");
//				paramList.add(description);
//			}
//			PreparedStatement statement = conn.prepareStatement(sql.toString());
//			for (int i = 0; i < paramList.size(); i++) {
//				statement.setString(i + 1, paramList.get(i));
//			}
//
//			ResultSet rs = statement.executeQuery();
//
//			while (rs.next()) {
//				Message message = new Message();
//				messageList.add(message);
//				message.setId(rs.getString("ID"));
//				message.setCommand(rs.getString("COMMAND"));
//				message.setDescription(rs.getString("DESCRIPTION"));
//				message.setContent(rs.getString("CONTENT"));
//			}
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return messageList;
//	}
}
