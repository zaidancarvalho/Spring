package com.generation.Farmacia.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.Farmacia.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	public List <Produto> findAllByNomeContainingIgnoreCase(String nome);

	/*
	 * Método Personalzido - Buscar por nome do Produto e pelo Nome do laboratório
	 * 
	 * MySQL: select * from tb_produtos where nome = "produto" and laboratorio = "laboratorio";
	 */
	
	public List<Produto> findByNomeAndLaboratorio(String nome, String laboratorio);
	
	/*
	 * Método Personalizado - Buscar por Nome do Produto ou pelo Nome do laboratório
	 * 
	 * MySQL: select * from tb_produtos where nome = "produto" or laboratio = "laboratorio";
	 */
	
	public List<Produto> findByNomeOrLaboratorio(String nome, String laboratorio);
	
	/*
	 * Método buscarProdutosEntre: Retorna todos os produtos cujo preço esteja entre dosi valores
	 * informados nos parâmetros (valor inicial, valor final)
	 * 
	 * Anottaation @Query -> Permite executar uma consulta no  Spring utilizando código SQL nativo
	 * 
	 * ínicio -> Parâmetro da consulta SQL (Valor inicial)
	 * 
	 * final -> Parâmetro da consulta SQL (Valor final)
	 * 
	 * nativeQuery = true -> Indica que o código da consulta é o SQL nativo que
	 * é compátivel com qualquer SGBD Relacional
	 * 
	 * @Param ("inicio") -> Mapeia o parâmetro: inicio da consulta na variável BigDecimal inicio
	 * que é o primeiro parâemtro do método buscarProdutoEntre
	 * 
	 * Method Query equivalente: public List<Produto> findByPrecoBetween(BigDecimal inicio, BigDecimal fim);
	 */
	
	@Query(value = "select * from tb_produtos where preco between :inicio and :fim", nativeQuery = true)
	public List <Produto> buscarProdutosEntre(@Param("inicio") BigDecimal inicio, @Param("fim")BigDecimal fim);
}
