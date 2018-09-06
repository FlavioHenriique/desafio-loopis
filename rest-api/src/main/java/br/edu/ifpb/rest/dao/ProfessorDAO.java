package br.edu.ifpb.rest.dao;

import br.edu.ifpb.rest.domain.Professor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ProfessorDAO {

    private EntityManager manager;

    public ProfessorDAO() {
        manager = Persistence
                .createEntityManagerFactory("Loopis")
                .createEntityManager();
    }

    public void salvar(Professor professor) {

        manager.getTransaction().begin();
        manager.persist(professor);
        manager.getTransaction().commit();
    }

    public void deletar(String matricula) {
        manager.getTransaction().begin();
        manager.remove(buscar(matricula));
        manager.getTransaction().commit();
    }

    public List<Professor> todos() {

        return manager
                .createQuery("SELECT p FROM Professor p")
                .getResultList();
    }

    public Professor atualizar(Professor professor) {
        manager.getTransaction().begin();
        manager.merge(professor);
        manager.getTransaction().commit();
        return buscar(professor.getMatricula());
    }

    public Professor buscar(String matricula) {

        return manager.find(Professor.class, matricula);
    }

}
