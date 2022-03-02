package com.generation.blogpessoal.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.Postagem;

@Repository  // essa interface será do tipo repository
public interface PostagemRepository extends JpaRepository <Postagem, Long> {  //criando uma herança, depoois do JpaReposiyory vamos identificar 
	
	List  <Postagem> findAllByTituloContainingIgnoreCase(String titulo);
									
	/*ignorando se é maiusculo ou minusculo
	procure tudo pelo titulo que contenha esta String ignorando se é maiuscula ou minuscula/*
	
	/* select(find) * from tb_postagens (Postagem) where (by) titulo like "%titulo%*/
}
