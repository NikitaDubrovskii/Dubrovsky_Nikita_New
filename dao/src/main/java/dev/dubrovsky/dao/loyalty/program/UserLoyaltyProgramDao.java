package dev.dubrovsky.dao.loyalty.program;

import dev.dubrovsky.dao.connection.ConnectionDataBase;
import dev.dubrovsky.exception.DbException;
import dev.dubrovsky.model.loyalty.program.UserLoyaltyProgram;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import java.util.List;

public class UserLoyaltyProgramDao implements IUserLoyaltyProgramDao {

    private final EntityManagerFactory entityManagerFactory = ConnectionDataBase.getEntityManagerFactory();

    @Override
    public void create(UserLoyaltyProgram userLoyaltyProgram) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            try {
                em.getTransaction().begin();

                em.persist(userLoyaltyProgram);

                em.getTransaction().commit();
            } catch (Exception ex) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                throw ex;
            }
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    //getByUserId
    //getByBonusId

    @Override
    public List<UserLoyaltyProgram> getAll() {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            return em.createQuery("SELECT ulp FROM UserLoyaltyProgram ulp", UserLoyaltyProgram.class).getResultList();
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer userId, Integer programId) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            try {
                em.getTransaction().begin();

                Query query = em.createNativeQuery("DELETE FROM user_loyalty_programs WHERE user_id = :userId AND program_id = :programId");
                query.setParameter("userId", userId);
                query.setParameter("programId", programId);
                query.executeUpdate();

                em.getTransaction().commit();
            } catch (Exception ex) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                throw ex;
            }
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

}
