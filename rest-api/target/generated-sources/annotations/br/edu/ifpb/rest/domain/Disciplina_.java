package br.edu.ifpb.rest.domain;

import br.edu.ifpb.rest.domain.Aluno;
import br.edu.ifpb.rest.domain.Professor;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-10T07:53:30")
@StaticMetamodel(Disciplina.class)
public class Disciplina_ { 

    public static volatile SingularAttribute<Disciplina, Professor> professor;
    public static volatile ListAttribute<Disciplina, Aluno> alunos;
    public static volatile SingularAttribute<Disciplina, String> nome;
    public static volatile SingularAttribute<Disciplina, Integer> id;
    public static volatile SingularAttribute<Disciplina, Integer> cargaHoraria;

}