package dev.dubrovsky.dao.bonus;

import dev.dubrovsky.exception.DbException;
import dev.dubrovsky.model.bonus.UserBonus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserBonusDao implements IUserBonusDao {

    private final EntityManagerFactory entityManagerFactory;

    public UserBonusDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void create(UserBonus userBonus) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            try {
                em.getTransaction().begin();

                em.persist(userBonus);

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
    public List<UserBonus> getAll() {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            return em.createQuery("SELECT ub FROM UserBonus ub", UserBonus.class).getResultList();
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer userId, Integer bonusId) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            try {
                em.getTransaction().begin();

                Query query = em.createNativeQuery("DELETE FROM user_bonuses WHERE user_id = :userId AND bonus_id = :bonusId");
                query.setParameter("userId", userId);
                query.setParameter("bonusId", bonusId);
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
