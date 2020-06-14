package com.dathanwong.dojooverflow.controllers;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dathanwong.dojooverflow.models.Answer;
import com.dathanwong.dojooverflow.models.Question;
import com.dathanwong.dojooverflow.models.Tag;
import com.dathanwong.dojooverflow.services.AnswerService;
import com.dathanwong.dojooverflow.services.QuestionService;
import com.dathanwong.dojooverflow.services.TagService;

@Controller
public class MainController {

	private final TagService tagService;
	private final QuestionService questionService;
	private final AnswerService answerService;
	
	public MainController(TagService ts, QuestionService qs, AnswerService as) {
		this.tagService = ts;
		this.questionService = qs;
		this.answerService = as;
	}
	
	@RequestMapping("/questions")
	public String showQuestionDashboard(Model model) {
		model.addAttribute("questions", questionService.findAll());
		return "questionsDashboard.jsp";
	}
	
	@RequestMapping("/questions/{id}")
	public String showQuestion(@PathVariable("id") Long id, Model model, @ModelAttribute("answer") Answer answer) {
		model.addAttribute("question", questionService.findById(id));
		return "questionsPage.jsp";
	}
	
	@RequestMapping("/questions/new")
	public String showNewQuestion() {
		return "newQuestionPage.jsp";
	}
	
	@PostMapping("/answers/{id}/new")
	public String createAnswer(@PathVariable Long id, @Valid @ModelAttribute("answer") Answer answer, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("question", questionService.findById(id));
			return "questionsPage.jsp";
		}else {
			Question q = questionService.findById(id);
			answer.setQuestion(q);
			answerService.create(answer);
			return "redirect:/questions/"+id;
		}
	}
	
	@PostMapping("/questions/new")
	public String createQuestion(@RequestParam("question") String question, @RequestParam("tags") String tags, RedirectAttributes redirectAttributes) {
		//Check to make sure question isn't empty
		if(question.isEmpty()) {
			redirectAttributes.addFlashAttribute("error", "Question cannot be empty");
			return "redirect:/questions/new";
		}
		Question q = new Question(question);
		//Make sure tags are present
		if(!tags.isEmpty()) {
			String[] allTags = tags.split(",");
			System.out.println(Arrays.toString(allTags));
			//Check to make sure no more than 3 tags
			if(allTags.length > 3) {
				redirectAttributes.addFlashAttribute("error", "You can only add 3 tags at most");
				return "redirect:/questions/new";
			}
			for(String tag : allTags) {
				//Check if tag already exists
				Tag t = tagService.findBySubject(tag);
				if(t != null) {
					q.addTag(t);
				}else {
					Tag newTag = new Tag(tag.trim());
					tagService.create(newTag);
					q.addTag(newTag);
				}
			}
		}
		questionService.create(q);
		return "redirect:/questions";
	}
}
