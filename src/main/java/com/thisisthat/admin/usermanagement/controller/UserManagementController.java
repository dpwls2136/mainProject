package com.thisisthat.admin.usermanagement.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thisisthat.admin.usermanagement.service.UserManagementService;
import com.thisisthat.admin.usermanagement.vo.UserVO;

@Controller
@RequestMapping("/admin")
public class UserManagementController {

	@Autowired
	private UserManagementService userService;

	@GetMapping("/userList.mdo")
	public String getUserList(Model model, UserVO vo) {
		System.out.println("1 :"+ vo.getSelect());
		System.out.println("2 :"+ vo.getSearch());
		List<UserVO> getUserList = userService.getUserList(vo);
		List<UserVO> userList = new ArrayList<UserVO>();
		for (UserVO userTemp : getUserList) {
			StringBuffer temp = new StringBuffer();
			if(userTemp.getUserPhone() !=null) {
				temp.append(userTemp.getUserPhone().substring(0, 3));
				temp.append("-****-");
				temp.append(userTemp.getUserPhone().substring(userTemp.getUserPhone().length()-6, userTemp.getUserPhone().length()-2));
				userTemp.setUserPhone(temp.toString());
			}
			temp = new StringBuffer();
			if (userTemp.getUserName().length() == 2) {
				temp.append(userTemp.getUserName().substring(0, 1) + "*");
				userTemp.setUserName(temp.toString());
			}else if(userTemp.getUserName().length()>2){
				temp.append(userTemp.getUserName().substring(0,1));
				for(int i =0; i<userTemp.getUserName().length()-2;i++) {
					temp.append("*");
				}
				temp.append(userTemp.getUserName().substring(userTemp.getUserName().length() - 1,
						userTemp.getUserName().length()));
				userTemp.setUserName(temp.toString());
			}
			userList.add(userTemp);
		}
		model.addAttribute("userInfo", userList);
		return "/admin/userList";
	}
	

	@GetMapping("/getUser.mdo")
	public String getUser(Model model, @RequestParam(value = "userId") String id){
		UserVO uservo = userService.userManagement(id);
		if (uservo.getUserPhone() != null) {
			StringBuffer temp = new StringBuffer();
			temp.append(uservo.getUserPhone().substring(0, 3));
			temp.append("-");
			temp.append(uservo.getUserPhone().substring(3, 7));
			temp.append("-");
			temp.append(uservo.getUserPhone().substring(7, 11));
			uservo.setUserPhone(temp.toString());
		}
		model.addAttribute("user", uservo);
		return "/admin/getUser";
	}

	@PostMapping("/pwCheck.mdo")
	public String pwCheck(@RequestParam("userId")String userId,
						  @RequestParam("userPw")String userPw, 
						  HttpSession session,
						  RedirectAttributes model) {
		System.out.println(userId);
		System.out.println(userPw);
		UserVO sessionUser = (UserVO)session.getAttribute("adminId");
		if(BCrypt.checkpw(userPw, sessionUser.getUserPw())) {
			return "redirect:/admin/getUser.mdo?userId="+userId;
		}else {
			model.addFlashAttribute("msg","fail");
			model.addFlashAttribute("failId",userId);
			return "redirect:/admin/userList.mdo";
		}

		
	}
	@GetMapping("staffList.mdo")
	public String getStaffList(UserVO vo, Model model) {

		List<UserVO> userList = userService.staffList(vo);
		List<UserVO> newUserList = new ArrayList<UserVO>();
		for (UserVO user : userList) {
			if (user.getUserPhone() != null) {
				String phone1 = user.getUserPhone().substring(0, 3);
				String phone2 = "-****-";
				String phone3 = user.getUserPhone().substring(7);
				user.setUserPhone(phone1 + phone2 + phone3);
			}
			String Name[] = user.getUserName().split("");
			String newName = "";
			for (int i = 0; i < Name.length; i++)	 {
				if (i != 0 || i == Name.length) {
					newName += "*";
				} else {
					newName += Name[i];
				}
			}
			user.setUserName(newName);
			newUserList.add(user);
		}
		model.addAttribute("staffInfo", newUserList);
		return "/admin/staffList";
	}

	@GetMapping("/getStaff.mdo")
	public String getStaff(Model model, @RequestParam(value = "userId") String id) {
		UserVO uservo = userService.staffManagement(id);
		String newPhone = "";
		if (uservo.getUserPhone() != null) {
			String phone1 = uservo.getUserPhone().substring(0, 3);
			String phone2 = uservo.getUserPhone().substring(3, 7);
			String phone3 = uservo.getUserPhone().substring(7, 11);
			String bar = "-";
			newPhone = phone1 + bar + phone2 + bar + phone3;
			uservo.setUserPhone(newPhone);
		}
		model.addAttribute("user", uservo);
		return "/admin/getStaff";
	}

	@GetMapping("/getUserUpdate.mdo")
	public String getUserUpdate(UserVO vo, @RequestParam(value="userId") String id, @RequestParam("state") int state){
		vo.setUserId(id);
		vo.setUserRole(state);
		userService.userUpdate(vo);
		
		return "redirect:/getUser.mdo?userId="+id;
	}
	
	
	
	
	
	

}
