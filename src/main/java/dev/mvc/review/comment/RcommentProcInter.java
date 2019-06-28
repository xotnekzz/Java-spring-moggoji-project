package dev.mvc.review.comment;

import java.util.HashMap;
import java.util.List;

public interface RcommentProcInter {
  
  /**
   * ��� ���
   * <Xmp>
   * <insert id="create" parameterType="RcommentVO">
   * </Xmp>
   *  @param RcommentVO
   *  @return ó���� ���ڵ� ����
   */
  public int create(RcommentVO rcommentVO);
  
  /**
   * ��� ���
   * <Xmp>
   * <select id="list" resultType="RcommentVO" parameterType="int">
   * </Xmp>
   * @return
   */
  public List<RcommentVO> list(int reviewno);
  
  /**
   * ��� ����
   * <Xmp>
   * <select id="delete" parameterType="hashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  public int delete(HashMap hashMap);
  
  /**
   * ��� ī��Ʈ
   * @param reviewno
   * @return
   */
  public int comment_count(int reviewno);
  
  /**
   * ��� ��� + ����¡
   * <Xmp>
   * <select id="list_paging" resultType="RcommentVO" parameterType="hashMap">
   * </Xmp>
   * @param reviewno
   * @return
   */
  public List<RcommentVO> list_paging(HashMap hashMap);
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param nowPage     ���� ������
   * @return ����¡ ���� ���ڿ�
   */ 
  public String paging(int comment_count, int nowPage); 

}
