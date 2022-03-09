package com.generation.blogpessoal.secutiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity //está sendo habilidade a segurança 
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter { //herda os métodos e atribtuos de WebSecutrityConfigureAdaptar
																		//herda o Authentication
																		//passwordEncoder
																		//configure (HttpSecurity http)
	@Autowired
	private UserDetailsService userDetailsService; //interface responsavel por checar se o meu usário e senha existem no meu banco de dados
	//UserDetailsService é a interface responsável por checar se o meu usuário e senha eixstem no meu banco de dados.
	
	@Override //autentique ultilizando meu banco de dados ou meu usário de memória
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService); //autentique ultilizando meu banco de dados
		//Todo usuário e senha que digitar, checa no meu banco de dados se existe, se bater o login e a senha, deixe acessar
		
		auth.inMemoryAuthentication() //autenticação em memória, não vai passar no meu banco em memóris, é apenas usar em teste.
		.withUser("root")
		.password(passwordEncoder().encode("root")) //essa é a forma de acessar o projeto
				.authorities("ROLE_USER");
	}
	
	@Bean //é a mesma coisa do autowired só que o @Bean pode chamar de qualquer outra clase dentro, como se fosse public
	public PasswordEncoder passwordEncoder( ) { //criptogra a senha
		return new BCryptPasswordEncoder();
	}
	
	@Override //sobrecarregue o método 
	protected void configure(HttpSecurity http) throws Exception { //
		http.authorizeRequests()
		.antMatchers("/usuarios/logar").permitAll()
		.antMatchers("usuarios/cadastrar").permitAll()
		.antMatchers(HttpMethod.OPTIONS).permitAll() //liberar as opçoes de cabeçalho das suas requisições 
		.anyRequest().authenticated() //se não colocar usuário e senha, não entra
		.and().httpBasic() //pacote básico do http
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Não guarda estado. Mandou, recebeu a resposta e acabou aquela requisição 	
		.and().cors() //crossorigin
		.and().csrf().disable(); //Impedir um ataque chamado cross site request forgery. Ele fica desabilitadado para não atrapalhar o put e o post.
	}
	//Para e-comerce é ncessário liberar o listar produtos.
	
	
}
