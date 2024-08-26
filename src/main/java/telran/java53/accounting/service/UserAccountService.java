package telran.java53.accounting.service;

import telran.java53.accounting.dto.RolesDto;
import telran.java53.accounting.dto.UseRegisterDto;
import telran.java53.accounting.dto.UserDto;
import telran.java53.accounting.dto.UserEditDto;

public interface UserAccountService {
	UserDto register(UseRegisterDto useRegisterDto);
	
	UserDto getUser(String login);
	
	UserDto removeUser(String login);
	
	UserDto updateUser(String login, UserEditDto userEditDto);
	
	RolesDto changeRolesList(String login, String role, boolean isAddRoles);
	
	void changePassword(String login, String newPassword);
}
