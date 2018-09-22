package br.edu.ifpb.rest.domain;

import br.edu.ifpb.rest.domain.Aluno;
import br.edu.ifpb.rest.domain.Professor;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-21T23:18:05")
@StaticMetamodel(Turma.class)
public class Turma_ { 

    public static volatile SingularAttribute<Turma, Professor> professor;
    public static volatile ListAttribute<Turma, Aluno> alunos;
    public static volatile SingularAttribute<Turma, String> nome;
    public static volatile SingularAttribute<Turma, Integer> id;
    public static volatile SingularAttribute<Turma, Integer> cargaHoraria;

}