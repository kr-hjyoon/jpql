package jpql;

import javax.persistence.*;
import java.util.List;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf  = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx  = em.getTransaction();

        tx.begin();

        try{

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            List<Member> result = em.createQuery("select m from Member m left join Team t on m.username = t.name" , Member.class)
                    .getResultList();

            System.out.println("result=" + result.size());

            for ( Member eachMember: result) {
                System.out.println("member="+eachMember.toString()+",team="+eachMember.getTeam().toString());
            }

            tx.commit();

        }catch (Exception e){
            System.out.println("EXCEPTION OCCURED");
            System.out.println(e.getMessage());
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

        System.out.println("SUCCESSFULLY FINISHED");
    }

}



