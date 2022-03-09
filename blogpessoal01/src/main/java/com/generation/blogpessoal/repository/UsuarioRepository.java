package com.generation.blogpessoal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
	
	/*
	 * Método que busca um usário pelo seu usuario (email).
	 * 
	 * select * from tb_usuarios where usuario = "usuario procurado"*/

	public Optional<Usuario> findByUsuario(String usuario); //Pode encontrar usuário ou não encontrar. 
	//Se ele encontrar o usuario vai trzer os dados, se não encontrar, vai  trazer o objeto nulo
	
}
