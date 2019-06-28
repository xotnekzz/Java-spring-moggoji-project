package dev.mvc.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.actor.ActorProc")
public class ActorProc implements ActorProcInter {
  @Autowired
  @Qualifier("dev.mvc.actor.ActorDAO")
  private ActorDAOInter actorDAO = null;
    
  public ActorProc() {
    //System.out.println("--> ActorProc Created.");
  }
    
  /**
   * <Xmp>
   *  ������� �Է�
   *  <insert id="create" parameterType="ActorVO">
   * </Xmp>
   * @param ActorVO
   * @return ó���� ���ڵ� ����
   */
  @Override
  public int create(ActorVO actorVO) {
    int count = actorDAO.create(actorVO);
    return count; 
  }

}
