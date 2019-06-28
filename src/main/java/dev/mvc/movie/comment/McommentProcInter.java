package dev.mvc.movie.comment;

import java.util.HashMap;
import java.util.List;

public interface McommentProcInter {
  
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
   * ��ȭ �� ��� ����
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
   * <select id="m_mclist" resultType="MovieNmVO" parameterType="int">
   * @param memberno
   * @return
   */
  public List<MovieNmVO> m_mclist(HashMap hashMap);
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param m_mccount ��� ����
   * @param nowPage     ���� ������
   * @param word �˻���
   * @param memberno ȸ����ȣ
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int m_mccount, int nowPage, String word, int memberno); 
  
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
