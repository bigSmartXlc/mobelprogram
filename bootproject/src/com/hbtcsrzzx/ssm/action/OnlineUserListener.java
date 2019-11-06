package com.hbtcsrzzx.ssm.action;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.hbtcsrzzx.ssm.po.Admin;
import com.hbtcsrzzx.ssm.po.ExpertWithBLOBs;
import com.hbtcsrzzx.ssm.po.TeacherWithBLOBs;
import com.hbtcsrzzx.ssm.po.UserWithBLOBs;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

public class OnlineUserListener implements HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		HttpSession session = event.getSession();
		String id = session.getId();
		Constants.map.put(id,null);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		HttpSession session = event.getSession();
		String id = session.getId();
		Object object = Constants.map.get(id);
		if(object!=null) {
			if(object instanceof Admin){
				Admin admin = (Admin)object;
				admin.setIsonline(Constants.OUTLINE);
			}else if(object instanceof UserWithBLOBs){
				UserWithBLOBs user = (UserWithBLOBs)object;
				user.setIsonline(Constants.OUTLINE);
			}else if(object instanceof TeacherWithBLOBs) {
				TeacherWithBLOBs teacher = (TeacherWithBLOBs)object;
				teacher.setIsonline(Constants.OUTLINE);
			}else if(object instanceof ExpertWithBLOBs) {
				ExpertWithBLOBs expert = (ExpertWithBLOBs)object;
				expert.setIsonline(Constants.OUTLINE);
			}	
		}
		Constants.map.remove(id);
	}
	
}
