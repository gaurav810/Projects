package com.dailyupdate.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dailyupdate.bean.TaskDetails;
import com.dailyupdate.bean.User;
import com.dailyupdate.bean.UserTaskTransformer;
import com.dailyupdate.dao.TaskDetailsDAO;
import com.dailyupdate.dao.UserDAO;
import com.dailyupdate.dto.TaskDetailsDTO;
import com.dailyupdate.utils.DateUtils;

@Controller
public class ViewController {

	@Autowired
	private TaskDetailsDAO dailyUpdateDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value = {"/", "/submit"}, method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request) {
		
		try {
			
			Date now = new Date();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
	        model.addAttribute("date", dateFormat.format(now));
			
	        System.out.println("IP ::::> " + request.getRemoteAddr());
	        
	        User user = userDAO.getUserDetailsByIp(request.getRemoteAddr());
	        
	        if(user != null) {
	        	if("Admin".equals(user.getRole())) {
	        		
	        		String filterdDate = request.getParameter("date");
	        		if(filterdDate != null && !(filterdDate.isEmpty())) {
	        			now = dateFormat1.parse(filterdDate);
	        		}
		        	
		        	model.addAttribute("userType", "admin");
		        	
		        	List<User> users = userDAO.listOfUserByRole("User");
		        	model.addAttribute("users", users);
		        	
		        	List<UserTaskTransformer> userTasks = dailyUpdateDAO.listOfTaskByDate(now);
		        	model.addAttribute("userTasks", userTasks);
		        	
		        	
		        } else {
		        	model.addAttribute("userType", "user");
		        	
		        	TaskDetailsDTO taskDetailsDTO = dailyUpdateDAO.getCurrentDateTaskDetailsByUser(request.getRemoteAddr(), now);
		        	if(taskDetailsDTO == null) {
		        		taskDetailsDTO = new TaskDetailsDTO();
		        	}
		        	model.addAttribute("taskDetailsDTO", taskDetailsDTO);
		        	model.addAttribute("name", user.getName());
		        	model.addAttribute("usreId", user.getId());
		        	
		        	List<TaskDetails> dailyUpdates = dailyUpdateDAO.listOfTask(request.getRemoteAddr()); 
			        model.addAttribute("tasks", dailyUpdates);
		        }
	        	model.addAttribute("dateFormmated", dateFormat1.format(now));
	        	
	        } else {
	        	model.addAttribute("errorMessage", "Your are not registerd, Please contact to admin");
	        }
	        
		} catch(Exception e) {
			model.addAttribute("errorMessage", "Something went wrong please contact to Admin.");
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String submitData(@ModelAttribute("taskDetailsDTO") TaskDetailsDTO taskDetailsDTO, HttpServletRequest request,
																RedirectAttributes redirectAttributes) {
		
		try {
			
			Date now = new Date();
			
			User user = userDAO.getUserDetailsByIp(request.getRemoteAddr());
			
			List<TaskDetails> listOfTaskDetails = dailyUpdateDAO.listOfTaskByUserId(user.getId(), DateUtils.getFromDateTime(now), DateUtils.getToDateTime(now));
			TaskDetails taskDetails = null;
			if(listOfTaskDetails == null || listOfTaskDetails.isEmpty()) {
				taskDetails = new TaskDetails();
			} else {
				taskDetails = listOfTaskDetails.get(0);
			}
			
			taskDetails.setAchievement(taskDetailsDTO.getAchievement());
			taskDetails.setClientTask(taskDetailsDTO.getClientTask());
			taskDetails.setLearnNewThings(taskDetailsDTO.getLearnNewThings());
			taskDetails.setTaskDate(now);
			taskDetails.setUser(user);
			
			dailyUpdateDAO.save(taskDetails);
			
			redirectAttributes.addFlashAttribute("successMessage", "Data Saved Successfully");
	        
		} catch(Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "Something went wrong please contact to Admin.");
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/download-report", method = RequestMethod.POST)
	public void downloadReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		int userId = Integer.valueOf(request.getParameter("userId"));
		
		String fromDateStr = request.getParameter("fromDate");
		String toDateStr = request.getParameter("toDate");
		
		Date fromDate = null;
		Date toDate = null;
		try {
			fromDate = dateFormat.parse(fromDateStr);
			toDate = dateFormat.parse(toDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		HSSFSheet sheet = workbook.createSheet("Task Report");
		sheet.addMergedRegion(new CellRangeAddress(1,1,1,3));
		
		HSSFRow title = sheet.createRow(1);
		title.createCell(1).setCellValue("Task Report (" + fromDateStr + " - " + toDateStr + ")");
		
		sheet.setColumnWidth(0, 5000);
		sheet.setColumnWidth(1, 5000);
		sheet.setColumnWidth(2, 7500);
		sheet.setColumnWidth(3, 7500);
		sheet.setColumnWidth(4, 7500);
		
		HSSFRow header = sheet.createRow(3);
		header.createCell(0).setCellValue("Name");
		header.createCell(1).setCellValue("Date");
		header.createCell(2).setCellValue("Client Task");
		header.createCell(3).setCellValue("Learn New Things");
		header.createCell(4).setCellValue("Achievement");
		
		List<TaskDetails> dailyUpdates = dailyUpdateDAO.listOfTaskByUserId(userId, fromDate, toDate); 
		
		int rowNum = 5;
		for (TaskDetails entry : dailyUpdates) {
			HSSFRow row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(entry.getUser().getName());
			row.createCell(1).setCellValue(entry.getTaskDateString());
			row.createCell(2).setCellValue(entry.getClientTask());
			row.createCell(3).setCellValue(entry.getLearnNewThings());
			row.createCell(4).setCellValue(entry.getAchievement());
        }
         
         response.setContentType("application/vnd.ms-excel");
         response.setHeader("Content-Disposition", "attachment; filename=\"Task_Report.xlsx");
         workbook.write(response.getOutputStream());
         
	}
}
