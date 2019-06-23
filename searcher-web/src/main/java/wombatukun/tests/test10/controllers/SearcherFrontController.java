package wombatukun.tests.test10.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wombatukun.tests.test10.dto.RequestDto;
import wombatukun.tests.test10.dto.ResponseDto;
import wombatukun.tests.test10.services.SearcherService;

import javax.validation.Valid;

@Slf4j
@Controller
public class SearcherFrontController {

	private static final String TMPL_THYMELEAF = "thymeleaf";
	private static final String TMPL_ANGULAR = "angular";

	private SearcherService searcherService;

	@Autowired
	public SearcherFrontController(SearcherService searcherService) {
		this.searcherService = searcherService;
	}

	@GetMapping(value = "angular")
	public String angularFront() {
		return TMPL_ANGULAR;
	}

	@GetMapping(value = "thymeleaf")
	public String thymeleafFront(Model model) {
		model.addAttribute("searchForm", new RequestDto(null, 1, 30));
		return TMPL_THYMELEAF;
	}

	@PostMapping(value = "thymeleaf")
	public String thymeleafSearch(
			@Valid @ModelAttribute("searchForm") RequestDto request,
			@RequestParam(value="action") String action,
			BindingResult result,
			Model model
	) {
		if (!result.hasErrors()) {
			switch(action) {
				case "prevPage": request.setPage(request.getPage()-1); break;
				case "nextPage": request.setPage(request.getPage()+1); break;
				default: request.setPage(1);
			}
			ResponseDto response = searcherService.searchQuestionsByTitle(request.getValue(), request.getPage(), request.getPageSize());
			model.addAttribute("errorMessage", response.getErrorMessage());
			model.addAttribute("items", response.getItems());
			model.addAttribute("hasMore", response.isHasMore());
			model.addAttribute("page", response.getPage());
			model.addAttribute("pageSize", response.getPageSize());
			model.addAttribute("total", response.getTotal());
			model.addAttribute("searchString", response.getSearchString());
			model.addAttribute("searchForm", new RequestDto(request.getValue(), request.getPage(), request.getPageSize()));
		}
		return TMPL_THYMELEAF;
	}

}
