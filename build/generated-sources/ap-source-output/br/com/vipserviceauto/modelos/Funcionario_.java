package br.com.vipserviceauto.modelos;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Funcionario.class)
public abstract class Funcionario_ {

	public static volatile SingularAttribute<Funcionario, Funcao> funcao;
	public static volatile SingularAttribute<Funcionario, Date> dataDemissao;
	public static volatile SingularAttribute<Funcionario, Setor> setor;
	public static volatile SingularAttribute<Funcionario, Long> idfucionario;
	public static volatile SingularAttribute<Funcionario, Date> dataAdminissao;
	public static volatile SingularAttribute<Funcionario, Filial> filial;
	public static volatile SingularAttribute<Funcionario, Pessoa> pessoa;
	public static volatile SingularAttribute<Funcionario, Especialidade> especialidade;
	public static volatile SingularAttribute<Funcionario, Turno> turno;
	public static volatile SingularAttribute<Funcionario, Escolaridade> Escolaridade;
	public static volatile SingularAttribute<Funcionario, Agendamento> agendamento;

}

