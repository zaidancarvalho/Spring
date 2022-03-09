package com.generation.blogpessoal.secutiry;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository userRepository; //injetar repository
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Optional<Usuario> usuario = userRepository.findByUsuario(userName); //procurar no banco de dados para saber se o usuario existe
		
		usuario.orElseThrow(() -> new UsernameNotFoundException(userName + " not found.")); //se não existir, vai enviar um not found
		
		return usuario.map(UserDetailsImpl::new).get(); //caso exista, vai criar um objeto da classe UserDetailsImpl
												
	}

}
