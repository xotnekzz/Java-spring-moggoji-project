package dev.mvc.review;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;


public interface ReviewProcInter {
  
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
    * </Xmp>
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
    * ��� +�˻�(����)+ ����¡(���θ޴�)
    * <Xmp>
    * <select id="all_list_search" resultType="ReviewVO" parameterType="HashMap">
    * </Xmp>
    * @param hashMap
    * @return
    */
   public List<ReviewVO> all_list_search(HashMap hashMap);
   
   /** 
    * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� (��ü)
    * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
    *
    * @param search_count �˻� ����
    * @param nowPage  ���� ������
    * @param word �˻���
    * @return ����¡ ���� ���ڿ�
    */ 
   public String paging(int search_count, int nowPage, String word); 
   
   
   
   /**
    * �˻��� ���ڵ� ����
    * <Xmp>
    * <select id="msearch_count" resultType="int" parameterType="HashMap">
    * </Xmp>
    * @return
    */
   public int msearch_count(HashMap hashMap); 
   
   /**
    * ��� +�˻�(����)+ ����¡(ȸ��)
    * <Xmp>
    * <select id="m_list_search" resultType="ReviewVO" parameterType="HashMap">
    * </Xmp>
    * @param hashMap
    * @return
    */
   public List<ReviewVO> m_list_search(HashMap hashMap);
   
   /** 
    * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� (ȸ����)
    * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
    *
    *@param msearch_count �˻� ����
    * @param nowPage  ���� ������
    * @param word �˻���
    * @param memberno ȸ����ȣ
    * @return ����¡ ���� ���ڿ�
    */ 
   public String mpaging(int msearch_count, int nowPage, String word, int memberno); 
/*   public String mpaging(int msearch_count, int nowPage, String word, HttpSession session); */

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
