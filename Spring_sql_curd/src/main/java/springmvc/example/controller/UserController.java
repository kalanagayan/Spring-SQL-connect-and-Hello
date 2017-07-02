package springmvc.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import springmvc.example.model.User;
import springmvc.example.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	UserService userservice;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET )
	public ModelAndView list(){
		ModelAndView modelandview = new ModelAndView("user/user_page");
		
		List<User> list = userservice.listAllUsers();
		modelandview.addObject("listobject",list);
		return modelandview;
		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(){
		ModelAndView modelandview = new ModelAndView("user/user_form");
		User user = new User();
		modelandview.addObject("userform",user);
		return modelandview;
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView update(@PathVariable("id") int id){
		ModelAndView modelandview = new ModelAndView("user/user_form");
		User user = userservice.findUserById(id);
		modelandview.addObject("userform",user);
		return modelandview;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@PathVariable("userform") User user){
		
		if(user != null){
			userservice.updateUser(user);
		}else{
			userservice.addUser(user);
		}
		return new ModelAndView("redirect:/list");
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("id") int id){
		userservice.deleteUser(id);

		return new ModelAndView("redirect:/list");
	}
	
}
