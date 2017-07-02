package springmvc.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springmvc.example.dao.UserDao;
import springmvc.example.model.User;

@Service
public class UserServiceimpl implements UserService{

	UserDao userdao;
	
	
	@Autowired
	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}
	
	public List<User> listAllUsers() {
		return userdao.listAllUsers();
	}

	public void addUser(User user) {
		userdao.addUser(user);
	}

	public void updateUser(User user) {
		userdao.updateUser(user);
	}

	public void deleteUser(int id) {
		userdao.deleteUser(id);
	}

	public User findUserById(int id) {
		return userdao.findUserById(id);
	}

}
