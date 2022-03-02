package com.generation.Farmacia.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.Farmacia.model.Produto;
import com.generation.Farmacia.repository.CategoriaRepository;
import com.generation.Farmacia.repository.ProdutoRepository;

@RestController //terá métodos implemetadores e irão executados os métodos que foram criados no repository. Repository são criado e aqui na controller implementa os métodos
@RequestMapping("/produtos") //caminho padrão dessa controller
@CrossOrigin(origins = "*", allowedHeaders = "*") //aceito requisições que vem de outros servidores 
public class ProdutoController {
	
	@Autowired //injeção de dependencia. Inversão de controle, transfere a responsabilidade de criar e instanciar um objeto para o spring. Fazendo atráves a interface repository porque ela tem acesso a classe model
	private ProdutoRepository produtoRepository;
	
	@Autowired 
	private CategoriaRepository categoriaRepository;
	//cada injeção de dependecia criar o prórpio @Autowired
	
	
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll() { //mostra tudo que tem na tabela. usando list para puxar todos os itens da tabela
		return ResponseEntity.ok(produtoRepository.findAll());  //return ResponseEntity porque precisamos de uma resposta http. Que é o status ok
	}
	
	@GetMapping("/{id}") //está entre chaves porque é uma váriavel 
	public ResponseEntity<Produto> getbyId(@PathVariable Long id) { //PathVariable pegue o que está em entre chaves e coloque na minha váriavel id
		return produtoRepository.findById(id) //executo meu método findById, padrao porque toda tabela precisa ter um id
				.map(resposta -> ResponseEntity.ok(resposta)) //o resultado da execução da linha 30 fica guardado no resposta. resposta vai receber o que está no meu findbyId 
				.orElse(ResponseEntity.notFound().build()); //lambda, caso for nulo orelse
	}
	
	@GetMapping("/nome/{nome}") //precisa colocar nome para diferenciar do id, mesmo as váriaveis tendo nome diferentes o caminho é o mesmo
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome)); //recebe o parâmetro do "nome"
	}
	
	@PostMapping //persistir meu método no meu banco
	public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto produto) { //apenas receber o objeto Produto e dentro do parâmetro passa o objeto inteiro(id,nome,descrição,quantidade,preço,foto e categoria)
		if (categoriaRepository.existsById(produto.getCategoria().getId())) //pegando o id do objeto categoria que está dentro do objeto produto
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto)); //o CREATED só devolve no post
		return ResponseEntity.badRequest().build(); //"digitei errado"
	}

	@PutMapping
	public ResponseEntity<Produto> putProduto(@Valid @RequestBody Produto produto) {
		
		if (produtoRepository.existsById(produto.getId())) {  //primeiro checar se o produto existe
			
			if (categoriaRepository.existsById(produto.getCategoria().getId())) //checar categoria, ai atualiza
			return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto)); //se existe, eu persisto
			else
				return ResponseEntity.badRequest().build(); //digite novamente, digitou uma categoria que não existe
		}	
		return ResponseEntity.notFound().build();  //se não existe, ir direto para o notfound
	
}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable long id) {
		
		return produtoRepository.findById(id)
				.map(resposta -> {
					produtoRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	//Consultar por nome ou laboratório
	
	@GetMapping("/nome/{nome}/oulaboratio/{laboratorio}")
	public ResponseEntity<List<Produto>> getByNomeOuLaboratorio(@PathVariable String nome, @PathVariable String laboratorio){
		return ResponseEntity.ok(produtoRepository.findByNomeOrLaboratorio(nome,  laboratorio));
	}
	
	//Consulta por nome e laboratório
	
	@GetMapping("/nome/{nome}/elaboratorio/{laboratorio}")
	public ResponseEntity<List<Produto>> getByNomeELaboratorio(@PathVariable String nome, @PathVariable String laboratorio){
		return ResponseEntity.ok(produtoRepository.findByNomeAndLaboratorio(nome, laboratorio));
	}
	
	//Consulta por preço entre dois valores (Between com Native Query)
	
	@GetMapping("/preco_inicial/{inicio}/preco_final/{fim}")
	public ResponseEntity<List<Produto>> getByPrecoEntreNatve(@PathVariable BigDecimal inicio, @PathVariable BigDecimal fim) {
		return ResponseEntity.ok(produtoRepository.buscarProdutosEntre(inicio, fim));
	}
}
