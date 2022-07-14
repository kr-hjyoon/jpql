package jpql;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.*;
import java.util.List;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf  = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx  = em.getTransaction();

        tx.begin();

        try{

            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);


            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("member2");
            member3.setTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();

            //String query = "select m from Member m left join Team t on m.username = t.name";
            String query = " update Member m set m.age=20 ";

            int updatedRows  = em.createQuery(query).executeUpdate();;
            System.out.println("member1.getAge() = " + member1.getAge());
            //System.out.println("updatedRows="+updatedRows);


/*            for( Team team: result){
                System.out.println("Team="+ team.getName()+ ", members = " + team.getMembers().size());
            }*/

            //Gson gson = new GsonBuilder().setPrettyPrinting().create();
            //System.out.println(gson.toJson(result));
            //System.out.println(gson.toJson(result));
/*
            for(Object[] objects : result){
                System.out.println("object=" + objects[0]);
                System.out.println("object=" + objects[1]);
                System.out.println("object=" + objects[2]);
            }*/

/*            for ( Member eachMember: result) {
                System.out.println("member="+eachMember.toString()+",team="+eachMember.getTeam().toString());
            }*/

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



