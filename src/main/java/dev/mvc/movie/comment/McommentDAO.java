package dev.mvc.movie.comment;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.movie.comment.McommentDAO")
public class McommentDAO implements McommentDAOInter {
  
  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public McommentDAO(){
    if(sqlSessionTemplate != null){
    }
  }
  
  /**
   * ��� ���
   * <Xmp>
   * <insert id="create" parameterType="McommentVO">
   * </Xmp>
   *  @param RcommentVO
   *  @return ó���� ���ڵ� ����
   */
  @Override
  public int create(McommentVO mcommentVO){
    int count = sqlSessionTemplate.insert("mcomment.create", mcommentVO);
    return count;
  }
  
  /**
   * ��� ���
   * <Xmp>
   * <select id="list" resultType="McommentVO" parameterType="String">
   * </Xmp>
   * @return
   */
  @Override
  public List<McommentVO> list(String movieCd){
    List<McommentVO> list = sqlSessionTemplate.selectList("mcomment.list", movieCd);
    return list;
  }
  
  /**
   * ��ȭ�� ��� ����
   * <Xmp>
   *  <select id="mcc" resultType="int" parameterType="MovieVO">
   *  </Xmp>
   * @param movieVO
   * @return
   */
  @Override
  public int mcc(String movieCd){
    int cnt = sqlSessionTemplate.selectOne("mcomment.mcc", movieCd);
    return cnt;
  }
  
  /**
   * ȸ���� ��ȭ ��� ����
   * <Xmp>
   *  <select id="m_mccount" resultType="int" parameterType="HashMap">
   *  </Xmp>
   * @param movieVO
   * @return
   */
  @Override
  public int m_mccount(HashMap hashMap){
    int cnt = sqlSessionTemplate.selectOne("mcomment.m_mccount", hashMap);
    return cnt;
  }
  
  /**
   * ȸ���� ��ȭ ��� ����Ʈ + �˻� + ����¡
   * <select id="m_mclist" resultType="MovieNmVO" parameterType="HashMap">
   * @param memberno
   * @return
   */
  @Override
  public List<MovieNmVO> m_mclist(HashMap hashMap){
    List<MovieNmVO> list = sqlSessionTemplate.selectList("mcomment.m_mclist", hashMap);
    return list;
  }
  
  
  /**
   * ȸ���� ��ȭ ��� ����
   * <Xmp>
   * <delete id="delete" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  @Override
  public int delete (HashMap hashMap){
    int count = sqlSessionTemplate.delete("mcomment.delete", hashMap);
    return count;
  }

  /**
   * ȸ���� ��ȭ ��� �� �� ��ȸ
   * <Xmp>
   * <select id="read" resultType="MovieNmVO" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  public MovieNmVO read(HashMap hashMap){
    MovieNmVO movieNmVO = sqlSessionTemplate.selectOne("mcomment.read", hashMap);
    return movieNmVO;
  }
  
  /**
   * ȸ���� ��ȭ ��� ����
   *  <Xmp>
   *  <update id="update" parameterType="movieNmVO">
   *  </Xmp>
   * @param movieNmVO
   * @return
   */
  @Override
  public int update(HashMap hashMap) {
   int count = sqlSessionTemplate.update("mcomment.update", hashMap);
   return count;
  }
  
  /*���� �� ����*/
  @Override
  public int star_all_count(){
    int count = sqlSessionTemplate.selectOne("mcomment.star_all_count");
    return count;
  }
}
