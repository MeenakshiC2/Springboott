package com.example.demo2.crud;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
@Service
public class Task_service {
	@Autowired
	JdbcTemplate jt;
	public String projecttask(Taskpojo tl) {
		String username=tl.getCheckuser();
		String password=tl.getCheckpwd();
		String check="select * from Spring where username=? and password=? and role='p'";
		List<Map<String,Object>> tasklists = new ArrayList<Map<String,Object>>();
		tasklists=jt.queryForList(check,username,password);
		System.out.println(password);
		if(tasklists.isEmpty()) {
			return "invalid credentials";
		}
		else {
			String project_id=tl.getProject_id();
			String task_name=tl.getTaskname();
			String description=tl.getDescription();
			String duration=tl.getDuration();
			String status="c";
			String created_by= username;
			DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd/mm/yyyy");
			LocalDateTime ldt= LocalDateTime.now();
			String ldt1=dtf.format(ldt);
			String task_id=task_name + ldt1;
			String checkid="select * from project_details where project_manager=? and project_id=?";
			List<Map<String,Object>> taskid = new ArrayList<Map<String,Object>>();
			taskid=jt.queryForList(checkid,username,project_id);
			System.out.println(taskid);
	
			if(taskid.isEmpty()) {
				return "its not your project";
			}
			String insert="insert into project_tasklist values(?,?,?,?,?,?,?)";
			jt.update(insert,project_id,task_id,task_name,description,duration,status,created_by);
		}
		return "inserted";
	}
	public String updatetask(Taskpojo tls) {
		String username=tls.getCheckuser();
		String password=tls.getCheckpwd();
		String update="select * from Spring where username=? and password=? and role='p'";
		List<Map<String,Object>> updatedate= new ArrayList<Map<String,Object>>();
		updatedate=jt.queryForList(update,username,password);
		if(updatedate.isEmpty()) {
			return "invalid credentials";
		}
		else {
			String checktask_id=tls.getTaskid();
			String checkupdate="select * from project_tasklist where taskid=? and createdby=?";
			List<Map<String,Object>> updatecheckdata= new ArrayList<Map<String,Object>>();
			updatecheckdata=jt.queryForList(checkupdate,checktask_id,username);
			if(updatecheckdata.isEmpty()) {
				return "invalid task id or project";
			}
			else {
				String description=tls.getDescription();
				String duration=tls.getDuration();
				String task_name=tls.getTaskname();
				String status="";
				String updatequery="update project_tasklist set description=?,duration=?, taskname=?,status='c' where taskid=?";
				jt.update(updatequery,description,duration,task_name,checktask_id);
				//System.out.printf(" "+description,duration,task_name);
				return "updated";
			}
		}
	}
	public String deletetask(Taskpojo dtl) {
		String username=dtl.getCheckuser();
		String password=dtl.getCheckpwd();
		String delete="select * from Spring where username=? and password=? and role='p'";
		List<Map<String,Object>> deletedata= new ArrayList<Map<String,Object>>();
		deletedata=jt.queryForList(delete,username,password);
		if(deletedata.isEmpty()) {
			return "invalid credentials";
		}
		else {
			String checkdelete="select * from project_task_list where created_by=?";
			List<Map<String,Object>> checkdelete1=new ArrayList<Map<String,Object>>();
			checkdelete1=jt.queryForList(checkdelete,username);
			if(checkdelete1.isEmpty()) {
				return "not assigned to any project";
			}
			else {
				String check_task_id=dtl.getTaskid();
				String deletequery="delete from project_task_list where task_id=?";
				int deletequerydata=jt.update(deletequery,check_task_id);
				return "deleted";
			}
		}
	}
}



