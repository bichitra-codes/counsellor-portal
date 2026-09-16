package in.counsellor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.counsellor.dto.EnqFilterRequestDto;
import in.counsellor.dto.EnquiryDto;
import in.counsellor.service.CourseService;
import in.counsellor.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {

	@Autowired
	private EnquiryService enqService;
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/add-enquiry")
	public String loadEnqForm(Model model) {
		
		model.addAttribute("enqDto",new EnquiryDto());
		model.addAttribute("courses", courseService.getCourse());
		
		return "add_enq";
	}
	
	@PostMapping("/add-enquiry")
	public String addEnquiry(EnquiryDto enqDto, Model model, HttpServletRequest req) {
		
		String smsg = null;
		
		HttpSession session = req.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");
		
		boolean isAdded = enqService.addEnquiry(enqDto, counsellorId);
		
		if(enqDto.getEnqId() != null) {
			smsg = "Enquiry updated successfully";
		} else {
			smsg = "Enquiry added successfully";
		}
		
		if(isAdded) {
			model.addAttribute("smsg", "Enquiry added successfully");
		}else {
			model.addAttribute("emsg", "Failed to add enquiry. Please try again");
		}
		
		model.addAttribute("courses", courseService.getCourse());
		model.addAttribute("enqDto", new EnquiryDto());
		
		return "add_enq";
	}
	
	@GetMapping("/view-enquiries")
	public String viewEnquiries(Model model, HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");
		
		model.addAttribute("enquiries", enqService.getAllEnquiries(counsellorId));
		
	model.addAttribute("filter", new EnqFilterRequestDto());
	model.addAttribute("courses", courseService.getCourse());
		
		return "view_enqs";
	}
	
	
	@PostMapping("/filter_enq")
	public String filterEnquiries(EnqFilterRequestDto filterDto, Model model, HttpServletRequest req) {
		
		HttpSession session = req.getSession(true);
		Integer counsellorId = (Integer) session.getAttribute("counsellorId");
		
		model.addAttribute("enquiries", enqService.getEnquiriesByFilter(filterDto, counsellorId));
		
		model.addAttribute("filter", filterDto);
		model.addAttribute("courses", courseService.getCourse());
		
		return "view_enqs";
	}
	
	@GetMapping("/edit-enq")
	public String editEnquiry( @RequestParam Integer enqId, Model model) {
		
		 EnquiryDto enqDto = enqService.editEnquiry(enqId);
		 
		 model.addAttribute("enqDto", enqDto);
		 model.addAttribute("courses", courseService.getCourse());
		 
		return "edit_enq";
	}
}
