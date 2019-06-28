package dev.mvc.review.comment;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.review.comment.RcommentDAO")
public class RcommentDAO implements RcommentDAOInter {
  
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public RcommentDAO(){
    if(sqlSessionTemplate != null){
      
    }
  }
  
  /**
   * ��� ���
   * <Xmp>
   * <insert id="create" parameterType="RcommentVO">
   * </Xmp>
   *  @param RcommentVO
   *  @return ó���� ���ڵ� ����
   */
  public int create(RcommentVO rcommentVO){
    int count = sqlSessionTemplate.insert("rcomment.create", rcommentVO);
    return count;
  }
  
  /**
   * ��� ���
   * <Xmp>
   * <select id="list" resultType="RcommentVO" parameterType="int">
   * </Xmp>
   * @return
   */
  public List<RcommentVO> list(int reviewno){
    List<RcommentVO> list = sqlSessionTemplate.selectList("rcomment.list", reviewno);
    return list;
  }
  
  /**
   * ��� ����
   * <Xmp>
   * <select id="delete" parameterType="hashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  public int delete(HashMap hashMap){
    // System.out.println("sqlSessionTemplate: " + sqlSessionTemplate);
    // return 0;
    int count = sqlSessionTemplate.delete("rcomment.delete", hashMap);
    return count;
  }
  
  /**
   * ��� ī��Ʈ
   */
  public int comment_count(int reviewno){
    int count = sqlSessionTemplate.selectOne("rcomment.comment_count", reviewno);
    return count;
  }
  
  /**
   * ��� ��� + ����¡
   * <Xmp>
   * <select id="list_paging" resultType="RcommentVO" parameterType="int">
   * </Xmp>
   * @param reviewno
   * @return
   */
  public List<RcommentVO> list_paging(HashMap hashMap){
    List<RcommentVO> list = sqlSessionTemplate.selectList("rcomment.list_paging", hashMap);
    return list;
  }
}
