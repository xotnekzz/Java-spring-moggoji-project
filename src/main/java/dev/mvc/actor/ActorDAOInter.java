package dev.mvc.actor;

import dev.mvc.actor.ActorVO;

public interface ActorDAOInter {
 
  /**
   * <Xmp>
   *  ������� �Է�
   *  <insert id="create" parameterType="ActorVO">
   * </Xmp>
   * @param ActorVO
   * @return ó���� ���ڵ� ����
   */
  public int create(ActorVO actorVO);
   
  
}
