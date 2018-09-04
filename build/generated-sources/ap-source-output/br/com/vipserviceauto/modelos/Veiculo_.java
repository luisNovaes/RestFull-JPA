package br.com.vipserviceauto.modelos;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Veiculo.class)
public abstract class Veiculo_ {

	public static volatile SingularAttribute<Veiculo, String> observacao;
	public static volatile ListAttribute<Veiculo, Agendamento> agendamentos;
	public static volatile SingularAttribute<Veiculo, Pessoa> propietario;
	public static volatile SingularAttribute<Veiculo, Montadora> montadora;
	public static volatile SingularAttribute<Veiculo, TipoVeiculo> tipoveiculo;
	public static volatile SingularAttribute<Veiculo, Integer> anoFabricacao;
	public static volatile SingularAttribute<Veiculo, Long> idVeiculo;
	public static volatile SingularAttribute<Veiculo, String> modelo;
	public static volatile SingularAttribute<Veiculo, String> placa;
	public static volatile SingularAttribute<Veiculo, Agendamento> agendamento;

}

