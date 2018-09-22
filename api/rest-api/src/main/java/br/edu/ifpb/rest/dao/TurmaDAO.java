package br.edu.ifpb.rest.dao;

import br.edu.ifpb.rest.domain.Turma;
import br.edu.ifpb.rest.domain.Professor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class TurmaDAO {

    private EntityManager manager;

    public TurmaDAO() {
        manager = Persistence
                .createEntityManagerFactory("Loopis")
                .createEntityManager();
    }

    public void salvar(Turma d) {
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

    public Turma buscar(int id) {

        return manager.find(Turma.class, id);
    }

    public Turma atualizar(Turma d) {
        manager.getTransaction().begin();
        manager.merge(d);
        manager.getTransaction().commit();
        return buscar(d.getId());
    }

    public List<Turma> todas() {

        return manager
                .createQuery("SELECT d FROM Disciplina d")
                .getResultList();
    }
}
