package in.counsellor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.counsellor.dto.DashboardResponseDto;
import in.counsellor.entity.Counsellor;
import in.counsellor.service.CounsellorService;
import in.counsellor.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	
	@Autowired
	private CounsellorService counsellorService;
	
	@Autowired
	private EnquiryService enquiryService;
	
	@GetMapping("/register")
	public String registerForm(Model model) {
		
		model.addAttribute("counsellor", new in.counsellor.entity.Counsellor());
		
		return "register_form";
	}
	
	@PostMapping("/register")
	public String handleRegistation( Counsellor counsellor, Model model) {
		
		boolean isSaved = counsellorService.saveCounsellor(counsellor);
		
		if(isSaved) {
			model.addAttribute("smsg", "Registration successful. Please login to continue");
		}else {
			model.addAttribute("emsg", "Registration failed. Please try again");
		}
		
		return "register_form";
	}
	
	@GetMapping("/")
	public String loginForm(Model model) {
		model.addAttribute("counsellor", new in.counsellor.entity.Counsellor());
		
		return "login_form";
	}
	
	@PostMapping("/login")
	public String handleLogin( Counsellor counsellor, Model model, HttpServletRequest req) {
		
		Counsellor loggedInCounsellor = counsellorService.login(counsellor.getEmail(), counsellor.getPassword());
		
		if(loggedInCounsellor != null) {
			HttpSession session = req.getSession(true);
			session.setAttribute("counsellorId", loggedInCounsellor.getCounsellorId());
			return "redirect:/dashboard";
		}else {
			model.addAttribute("msg", "Invalid credentials. Please try again");
			return "login_form";
		}
		
	}
	
	@GetMapping("/dashboard")
	public String buildDashboard(Model model, HttpServletRequest req) {
	
		
		HttpSession session = req.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");
		
		DashboardResponseDto dashboardInfo = counsellorService.getDashboardInfo(counsellorId);
		
		model.addAttribute("dashboardInfo", dashboardInfo);
		
		return "dashboard";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		
		return "redirect:/";
	}
}
