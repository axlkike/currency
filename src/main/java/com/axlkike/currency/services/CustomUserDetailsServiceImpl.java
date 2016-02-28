package com.axlkike.currency.services;
/**
 * Created by kikejf on 22/02/2016.
 */
import com.axlkike.currency.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	private final String ROLE_PREFIX = "ROLE_";

	@Inject
	private IuserService userService;

	/**
	 * service for load a user
	 * @param userId
	 * @return
	 * @throws UsernameNotFoundException
     */
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		User user = userService.getUserByUserId(userId);
		System.out.println("User : "+user);
		if(user==null){
			System.out.println("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		//users only have one  rol
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole()));
		// not using the rest of the fields from userdetails
		// user is always enabled and non expiring.
		return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(),
			 true, true, true, true, authorities);
	}
}
