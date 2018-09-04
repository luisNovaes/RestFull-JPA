package br.com.vipserviceauto.modelos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pessoa.class)
public abstract class Pessoa_ {

	public static volatile SingularAttribute<Pessoa, TipoPessoa> tipoPessoa;
	public static volatile SingularAttribute<Pessoa, String> telefone;
	public static volatile SingularAttribute<Pessoa, Estado> estado;
	public static volatile SingularAttribute<Pessoa, String> observacao;
	public static volatile SingularAttribute<Pessoa, Date> databascimento;
	public static volatile SingularAttribute<Pessoa, String> nome;
	public static volatile SingularAttribute<Pessoa, String> cpf_cnpj;
	public static volatile SingularAttribute<Pessoa, String> Endereco;
	public static volatile SingularAttribute<Pessoa, String> Cidade;
	public static volatile SingularAttribute<Pessoa, Long> idPessoa;
	public static volatile SingularAttribute<Pessoa, String> senha;
	public static volatile SingularAttribute<Pessoa, String> rg;
	public static volatile SingularAttribute<Pessoa, String> email;

}

