package com.excilys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.excilys.DAO.DAOuser;
import com.excilys.dto.MyUserDTO;
import com.excilys.mapper.UserDTOMapper;
import com.excilys.model.MyUser;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	DAOuser daoUser;
	@Autowired
	UserDTOMapper userMapper;
	@Autowired
	BCryptPasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Optional<MyUser> optUser = daoUser.getUserByName(username);
		if (optUser.isPresent()) {
			MyUser user = optUser.get();
			List<GrantedAuthority> grantedList= new ArrayList<GrantedAuthority>();
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole());
			grantedList.add(authority);
			UserDetails userDetails = new User(user.getUsername(), user.getPassword(), grantedList);
			return userDetails;
		}
		else {
			throw new UsernameNotFoundException("username " + username + " not found !!");
		}
	}
	
	public void createUser(MyUserDTO user) {
		user.setPassword(encoder.encode(user.getPassword()));
		daoUser.createUser(userMapper.DtoToUser(user));
	}
}
