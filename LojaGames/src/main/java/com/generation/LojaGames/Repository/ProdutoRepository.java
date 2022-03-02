package com.generation.LojaGames.Repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.LojaGames.model.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long> {
	
	public List<Produto> findAllByNomeContainingIgnoreCase(String nome);
	
	/*
	 * Método Personalizado - Buscar todos os Produtos cujo o preço seja maior
	 * do que um valor digitado ordenado pelo preço em ordem crescente
	 * 
	 * MYSQL: select * from tb_produtos where preco > preco order by preco;*/

	public List <Produto> findByPrecoGreaterThanOrderByPreco(BigDecimal preco);
	
	public List <Produto> findByPrecoLessThanOrderByPrecoDesc(BigDecimal preco);
}
