package br.com.vipserviceauto.modelos;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Fatura.class)
public abstract class Fatura_ {

	public static volatile SingularAttribute<Fatura, Long> codDocumento;
	public static volatile SingularAttribute<Fatura, StatusDoc> StatusDoc;
	public static volatile SingularAttribute<Fatura, BigDecimal> ValorTotDoc;
	public static volatile SingularAttribute<Fatura, Agendamento> agendamento;

}

