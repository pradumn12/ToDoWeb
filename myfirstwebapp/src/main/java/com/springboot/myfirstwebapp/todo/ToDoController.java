package com.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class ToDoController {
	
	@Autowired
	private ToDoService todoservice;
	

	@RequestMapping(value="todo-list")
	public String listAllTodos(ModelMap model) {
		String username = getLoggedInUsername(model);
		 List<ToDo> todos = todoservice.findToDoByUsername(username);
		 model.addAttribute("todos",todos);
		return "listTodos";
		
	}
	
	@RequestMapping(value="add-todo", method=RequestMethod.GET)
	public String showNewToDoPage(ModelMap model) {
		
		String username = getLoggedInUsername(model);
		ToDo newtodo = new ToDo(0,username,"Default description",LocalDate.now().plusYears(1),false);
		model.put("todo",newtodo);
		return "todo";
	}
	
	@RequestMapping(value="add-todo", method=RequestMethod.POST)
	public String addNewToDo(ModelMap model, ToDo todo) {
	
		String username = getLoggedInUsername(model);
		todoservice.addToDo(username,todo.getDescription(), todo.getFinalDate(), false);
		return "redirect:todo-list";

	}
	@RequestMapping(value="delete-todo")
	public String deleteDoTo(@RequestParam int id) {
		
		todoservice.deleteToDoById(id);
		return "redirect:todo-list";
	}
	@RequestMapping(value="update-todo", method=RequestMethod.GET)
	public String NavigateToToDo(@RequestParam int id,ModelMap model) {
		ToDo todo = todoservice.findToDoById(id);
		model.addAttribute("todo",todo);
		return "todo";
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.POST)
	public String updateToDo(ModelMap model, ToDo todo, BindingResult result) {
	
		String username = getLoggedInUsername(model);
		todo.setUserName(username);
		todoservice.updateToDo(todo);
		return "redirect:todo-list";

	}
	
	private String getLoggedInUsername(ModelMap model) {
		return (String)model.get("name");
	}
	

	
}
