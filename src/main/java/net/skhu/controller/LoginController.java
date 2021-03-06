package net.skhu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import net.skhu.domain.entity.User;
import net.skhu.service.LoginService;

@SessionAttributes("user")
@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;

	@GetMapping("/login")
	public String loginView() {
		return "board/login.html";
	}

	@PostMapping("/login")
	public String login(User user,Model model) {
		User findUser = loginService.getUser(user);

		if(findUser != null
				&& findUser.getUser_password().equals(user.getUser_password())) {
			model.addAttribute("user", findUser);
			return "board/home.html";
		}else {
			String loginErrorMsg = "아이디나 비밀번호가 일치하지 않습니다";
			model.addAttribute("loginErrorMsg", loginErrorMsg);
			return "board/index.html";
		}
	}

	@GetMapping("/index")
	public String indexView(Model model) {
		return "board/index.html";
	}
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:index";
	}
}
