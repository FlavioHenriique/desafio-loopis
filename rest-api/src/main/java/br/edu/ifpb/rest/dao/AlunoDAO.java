package br.edu.ifpb.rest.dao;

import br.edu.ifpb.rest.domain.Aluno;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class AlunoDAO {

    private EntityManager manager;

    public AlunoDAO() {
        manager = Persistence
                .createEntityManagerFactory("Loopis")
                .createEntityManager();
    }

    public void salvar(Aluno a) {
        System.out.println(a.toString());
        manager.getTransaction().begin();
        manager.persist(a);
        manager.getTransaction().commit();
    }

    public void deletar(String matricula) {

        manager.getTransaction().begin();
        manager.remove(buscar(matricula));
        manager.getTransaction().commit();
    }

    public Aluno atualizar(String matricula) {

        return manager.merge(buscar(matricula));

    }

    public Aluno buscar(String matricula) {

        return manager.find(Aluno.class, matricula);
    }

    public List<Aluno> todos() {

        return manager
                .createQuery("SELECT a FROM Aluno a", Aluno.class)
                .getResultList();
    }
}
