package com.spring.start.controller;

import com.spring.start.annotations.AdminController;
import com.spring.start.entity.Changelog;
import com.spring.start.service.CarService;
import com.spring.start.service.ChangelogService;
import lombok.extern.log4j.Log4j;
import org.bouncycastle.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Log4j
@AdminController
public class ChangelogController extends BaseController{

	private static final String ADD_CHANGELOG = "add-notification";
	private static final String SAVE_CHANGELOG = "save-notification";

	@Autowired
	private ChangelogService changelogService;

	@RequestMapping(value = ADD_CHANGELOG, method = RequestMethod.GET)
	public String showAddChangelogPage(Model model) {
		model.addAttribute("changelog", changelogService.getLatestVersionChangelog());
		return PAGES + SLASH + ADMIN + SLASH + ADD_CHANGELOG;
	}

	@PostMapping(value = SAVE_CHANGELOG)
	public String saveNewChangelog(@Valid @RequestParam("ver") String version,
								   @Valid @RequestParam("description") String description,
								   Model model,
								   RedirectAttributes redirectAttributes) {
		log.info("cos");

		try {
			changelogService.saveChangelog(version, description);
			log.info("DODANO VERSJE");
		} catch (Exception e){
			log.error("COS SIE ZJEBALO: " + e);
		}

		return "redirect:/admin/" + ADD_CHANGELOG;
	}


}
