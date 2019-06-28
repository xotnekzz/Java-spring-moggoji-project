package dev.mvc.movie.comment;

import java.util.HashMap;
import java.util.List;


public interface McommentDAOInter {

  /**
   * ��� ���
   * <Xmp>
   * <insert id="create" parameterType="McommentVO">
   * </Xmp>
   *  @param RcommentVO
   *  @return ó���� ���ڵ� ����
   */
  public int create(McommentVO mcommentVO);
  
  /**
   * ��� ���
   * <Xmp>
   * <select id="list" resultType="McommentVO" parameterType="String">
   * </Xmp>
   * @return
   */
  public List<McommentVO> list(String movieCd);
  
  /**
   * ��ȭ�� ��� ����
   * <Xmp>
   *  <select id="mcc" resultType="int" parameterType="String">
   *  </Xmp>
   * @param movieVO
   * @return
   */
  public int mcc(String movieCd);
  
  /**
   * ȸ���� ��ȭ ��� ����
   * <Xmp>
   *  <select id="m_mccount" resultType="int" parameterType="HashMap">
   *  </Xmp>
   * @param movieVO
   * @return
   */
  public int m_mccount(HashMap hashMap);
  
  
  /**
   * ȸ���� ��ȭ ��� ����Ʈ + �˻� + ����¡
   * <Xmp>
   * <select id="m_mclist" resultType="MovieNmVO" parameterType="HashMap">
   * </Xmp>
   * @param memberno
   * @return
   */
  public List<MovieNmVO> m_mclist(HashMap hashMap);
  
  /**
   * ȸ���� ��ȭ ��� ����
   * <Xmp>
   * <delete id="delete" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  public int delete (HashMap hashMap);
  
  /**
   * ȸ���� ��ȭ ��� �� �� ��ȸ
   * <Xmp>
   * <select id="read" resultType="MovieNmVO" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  public MovieNmVO read(HashMap hashMap);
  
  /**
   * ȸ���� ��ȭ ��� ����
   *  <Xmp>
   *  <update id="update" parameterType="movieNmVO">
   *  </Xmp>
   * @param movieNmVO
   * @return
   */
  public int update(HashMap hashMap);  
 
  /*���� �� ����*/
  public int star_all_count();
}
