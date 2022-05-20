package com.todo.list.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.todo.list.controller.dto.service.QuoteDTO;
import com.todo.list.service.image.BackGroundImageService;

/**
 * @author DongHyeon_kim
 * 
 *         이 문서는 관리자 페이지에서 모든 정보를 관리하기 위해 만들어진 컨트롤러 입니다.
 * 
 */

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private QuetoManagementService managementService;

	@Autowired
	private BackGroundImageService backGroundImageService;

	// Save Queto or Queto List
	@ResponseBody
	@PostMapping("/save/queto")
	public String saveQuetoList(@RequestBody QuoteDTO queto) {
		managementService.quetoSave(queto);
		
		return "saveSuccess";
	}

	// Save Image
	@PostMapping("/save/backGroundImage")
	public String saveBackGroundImages(@RequestParam MultipartFile multipartFile) {
		backGroundImageService.save(multipartFile);
		return "saveSuccess";
	}
	
}
