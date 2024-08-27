package telran.java53.security.model;

import java.security.Principal;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Getter
@AllArgsConstructor
@Builder
public class User implements Principal {
	private String name;
	@Singular
	private Set<String> roles;

}
