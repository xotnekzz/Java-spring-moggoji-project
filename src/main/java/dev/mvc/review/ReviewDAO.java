package dev.mvc.review;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("dev.mvc.review.ReviewDAO")
public class ReviewDAO implements ReviewDAOInter {
  
  @Autowired 
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public ReviewDAO() {
    //System.out.println("--> ReviewDAO Created...");
    
    if(sqlSessionTemplate != null) {
      //System.out.println("--> sqlSessionTemplate �Ҵ��.");
    }
  }

  /**
   *  ���� ���
   * <Xmp>
   *  <insert id="create" parameterType="ReviewVO">
   * </Xmp>
   * @param ReviewVO
   * @return ó���� ���ڵ� ����
   */
  @Override
  public int create(ReviewVO reviewVO) {
    int count = sqlSessionTemplate.insert("review.create", reviewVO);
    return count;
  }

  /**
   *  ��� ��ȸ
   * <Xmp>
   *  <select id="list" resultType="ReviewVO">
   *  </Xmp>
   * @return 
   */
  @Override
  public List<ReviewVO> list() { 
    List<ReviewVO> list = sqlSessionTemplate.selectList("review.list");
    return list;  
  }

  /** 
   *  1�� ��ȸ
   * <Xmp>
   * <select id="read" resultType="ReviewVO" parameterType="int">
   * </Xmp>
   * @param reviewno 
   * @return
   */
  @Override
  public ReviewVO read(int reviewno) {
    ReviewVO reviewVO = sqlSessionTemplate.selectOne("review.read", reviewno);
    return reviewVO;
  }

  /**
  * ��ȸ�� ����
  * <Xmp>
  * <update id="cnt_increase" parameterType="ReviewVO">
  * </Xmp>
  * @param reviewVO
  * @return
  */
  @Override
  public int cnt_increase(ReviewVO reviewVO) {
    int count = sqlSessionTemplate.update("review.cnt_increase", reviewVO);
    return count;
  }
  
  /**
   * ���� ������
   * <Xmp>
   * <select id="read" resultType="ReviewVO" parameterType="int">
   * </Xmp> 
   * @param reviewno
   * @return
   */
  public ReviewVO update(int reviewno){
    ReviewVO reviewVO = sqlSessionTemplate.selectOne("review.read", reviewno);
    return reviewVO;
  }
  /**
   * ���� ����
   * <Xmp>
   * <update id="update" parameterType="ReviewVO">
   * </Xmp>
   * @param reviewVO
   * @return
   */
  @Override
  public int update(ReviewVO reviewVO) {
    int count = sqlSessionTemplate.update("review.update", reviewVO);   
    return count;
  }

  /**
   * ���� ����
   * <Xmp>
   * <delete id="delete" parameterType="int">
   * </Xmp>
   * @param reviewno
   * @return
   */
  @Override
  public int delete(int reviewno) {
    int count = sqlSessionTemplate.delete("review.delete", reviewno);
    return count;
  }
  
  /**
   * �˻��� ���ڵ� ����
   * <Xmp>
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </Xmp>
   * @return
   */
  @Override
  public int search_count(HashMap hashMap) {
    int cnt = sqlSessionTemplate.selectOne("review.search_count", hashMap);
    return cnt;
  }
  
  /**
   * ��� + �˻�(����) + ����¡(���θ޴�)
   * <Xmp>
   * <select id="all_list_search" resultType="ReviewVO" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  public List<ReviewVO> all_list_search(HashMap hashMap){
    List<ReviewVO> list = null;
    list = sqlSessionTemplate.selectList("review.all_list_search", hashMap);
    return list;
  }
  
  /**
   * �˻��� ���ڵ� ����(ȸ��)
   * <Xmp>
   * <select id="msearch_count" resultType="int" parameterType="HashMap">
   * </Xmp>
   * @return
   */
  @Override
  public int msearch_count(HashMap hashMap) {
    int cnt = sqlSessionTemplate.selectOne("review.msearch_count", hashMap);
    return cnt;
  }
  
  /**
   * ��� + �˻�(����) + ����¡(ȸ��)
   * <Xmp>
   * <select id="m_list_search" resultType="ReviewVO" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  public List<ReviewVO> m_list_search(HashMap hashMap){
    List<ReviewVO> list = null;
    list = sqlSessionTemplate.selectList("review.m_list_search", hashMap);
    return list;
  }
  
  /**
   * ���並 �� ȸ�� �̸�
   * <Xmp>
   * <select id="mname" resultMap="MemberVO" parameterType="int">
   * </Xmp>
   * @param reviewno
   * @return
   */
  public MnameVO mname(int reviewno){
    MnameVO mnameVO = sqlSessionTemplate.selectOne("review.mname", reviewno);
    return mnameVO;
  }

  /**
   * ���亰 ��� ����
   * <Xmp>
   * <select id="rcc" resultType="int" parameterType="int">
   * <Xmp>
   * @param reviewno
   * @return
   */
  @Override
  public int rcc(int reviewno) {
    int cnt = sqlSessionTemplate.selectOne("review.rcc", reviewno);
    return cnt;
  }
    
}
