package br.edu.ifpb.rest.dao;

import br.edu.ifpb.rest.domain.Disciplina;
import br.edu.ifpb.rest.domain.Professor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DisciplinaDAO {

    private EntityManager manager;

    public DisciplinaDAO() {
        manager = Persistence
                .createEntityManagerFactory("Loopis")
                .createEntityManager();
    }

    public void salvar(Disciplina d) {
        manager.getTransaction().begin();
        d.setProfessor(new ProfessorDAO()
                .buscar(d.getProfessor().getMatricula()));
        manager.persist(d);
        manager.getTransaction().commit();
    }

    public void deletar(int id) {
        manager.getTransaction().begin();
        manager.remove(buscar(id));
        manager.getTransaction().commit();
    }

    public Disciplina buscar(int id) {

        return manager.find(Disciplina.class, id);
    }

    public Disciplina atualizar(Disciplina d) {
        manager.getTransaction().begin();
        manager.merge(d);
        manager.getTransaction().commit();
        return buscar(d.getId());
    }

    public List<Disciplina> todas() {

        return manager
                .createQuery("SELECT d FROM Disciplina d")
                .getResultList();
    }
}
