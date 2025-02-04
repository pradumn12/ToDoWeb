package com.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class ToDoService {
	
	private static List<ToDo> todos = new ArrayList<>();
	private static int todoCount = 0;
	static {
		todos.add(new ToDo(++todoCount,"psahani","SpringBoot",LocalDate.now().plusYears(1),false));
		todos.add(new ToDo(++todoCount,"psahani","Java",LocalDate.now().plusYears(2),false));
		todos.add(new ToDo(++todoCount,"psahani","AWS",LocalDate.now().plusYears(3),true));
		todos.add(new ToDo(++todoCount,"psahani","JS",LocalDate.now().plusYears(4),false));
		todos.add(new ToDo(++todoCount,"psahani","Docker",LocalDate.now().plusYears(5),true));
		}
	
	public List<ToDo> findToDoByUsername(String username) {
		

		Predicate<? super ToDo> predicate = todo -> todo.getUserName().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}
	
	public int findId(String description) {
		int value = -1;
		for(ToDo todo: todos) {
			if(todo.getDescription().equalsIgnoreCase(description)) {
				value = todo.getId();
			}
		}
	    return value;
	}
	
	public void addToDo(String username,String description, LocalDate targetdate, boolean done){
		ToDo todo = new ToDo(++todoCount,username,description,targetdate,done);
		todos.add(todo);
	}
	
	public void deleteToDoById(int id) {
		
		Predicate<? super ToDo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
		
	}

	public ToDo findToDoById(int id) {
		
		Predicate<? super ToDo> predicate = todo -> todo.getId() == id;
		ToDo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
		
	}

	public void updateToDo(ToDo todo) {
		deleteToDoById(todo.getId());
		todos.add(todo);
		
	}


}
