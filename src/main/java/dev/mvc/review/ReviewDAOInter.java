package dev.mvc.review;

import java.util.HashMap;
import java.util.List;


public interface ReviewDAOInter {
  
  /**
   *  ���� ���
   * <Xmp>
   *  <insert id="create" parameterType="ReviewVO">
   * </Xmp>
   * @param ReviewVO
   * @return ó���� ���ڵ� ����
   */ 
  public int create(ReviewVO reviewVO);
  
  /**
   *  ��� ��ȸ
   * <Xmp>
   *  <select id="list" resultType="ReviewVO">
   * </Xmp>
   * @return 
   */ 
  public List<ReviewVO> list(); 
  
 /**
  *  �� �� ��ȸ
  * <Xmp>
  * <select id="read" resultType="ReviewVO" parameterType="int">
  * </Xmp>
  * @param reviewno 
  * @return
  */
  public ReviewVO read(int reviewno);
  
 /**
  * ��ȸ�� ����
  * <Xmp>
  * <update id="cnt_increase" parameterType="ReviewVO">
  * </Xmp>
  * @param reviewVO
  * @return
  */
  public int cnt_increase(ReviewVO reviewVO);
  
  /**
   * ���� ������
   * <Xmp>
   * <select id="read" resultType="ReviewVO" parameterType="int">
   * </Xmp> 
   * @param reviewno
   * @return
   */
  public ReviewVO update(int reviewno);
  
  /**
   * ���� ����
   * <Xmp>
   * <update id="update" parameterType="ReviewVO">
   * </Xmp> 
   * @param reviewVO
   * @return
   */
  public int update(ReviewVO reviewVO);
  
  /**
   * ���� ����
   * <Xmp>
   * <delete id="delete" parameterType="int">
   * <Xmp>
   * @param reviewno
   * @return
   */
  public int delete(int reviewno);
  
  /**
   * �˻��� ���ڵ� ����
   * <Xmp>
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </Xmp>
   * @return
   */
  public int search_count(HashMap hashMap); 
  
  /**
   * ����Ʈ + �˻�(��������) + ����¡(���θ޴�)
   * <Xmp>
   * <select id="all_list_search" resultType="ReviewVO" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  public List<ReviewVO> all_list_search(HashMap hashMap);
  
  /**
   * �˻��� ���ڵ� ����(ȸ��)
   * <Xmp>
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </Xmp>
   * @return
   */
  public int msearch_count(HashMap hashMap); 
  
  /**
   * ����Ʈ + �˻�(��������) + ����¡(ȸ��)
   * <Xmp>
   * <select id="m_list_search" resultType="ReviewVO" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  public List<ReviewVO> m_list_search(HashMap hashMap);
  
  /**
   * ���並 �� ȸ�� �̸�
   * <Xmp>
   * <select id="mname" resultMap="MnameVO" parameterType="int">
   * </Xmp>
   * @param reviewno
   * @return
   */
  public MnameVO mname(int reviewno);
  
  /**
   * ���亰 ��� ����
   * <Xmp>
   * <select id="rcc" resultType="int" parameterType="int">
   * <Xmp>
   * @param reviewno
   * @return
   */
  public int rcc(int reviewno);
  
} 
