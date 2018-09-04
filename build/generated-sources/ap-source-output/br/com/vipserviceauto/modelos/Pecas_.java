package br.com.vipserviceauto.modelos;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pecas.class)
public abstract class Pecas_ {

	public static volatile SingularAttribute<Pecas, String> observacao;
	public static volatile SingularAttribute<Pecas, BigDecimal> valorPeca;
	public static volatile SingularAttribute<Pecas, String> nome;
	public static volatile SingularAttribute<Pecas, String> fabricante;
	public static volatile SingularAttribute<Pecas, Long> idPeca;
	public static volatile SingularAttribute<Pecas, String> modelo;
	public static volatile SingularAttribute<Pecas, Agendamento> agendamento;

}

