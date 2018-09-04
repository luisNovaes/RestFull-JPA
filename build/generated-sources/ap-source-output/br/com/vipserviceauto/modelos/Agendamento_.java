package br.com.vipserviceauto.modelos;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Agendamento.class)
public abstract class Agendamento_ {

	public static volatile SingularAttribute<Agendamento, Veiculo> veiculo;
	public static volatile SingularAttribute<Agendamento, String> observacao;
	public static volatile SingularAttribute<Agendamento, TipoServico> tipoServico;
	public static volatile SingularAttribute<Agendamento, BigDecimal> valorTotalServico;
	public static volatile SingularAttribute<Agendamento, HoraAgenda> horaAgenda;
	public static volatile SingularAttribute<Agendamento, Pecas> pecas;
	public static volatile SingularAttribute<Agendamento, Funcionario> funcionarios;
	public static volatile SingularAttribute<Agendamento, Long> idOS;
	public static volatile SingularAttribute<Agendamento, Date> dataAgenda;

}

