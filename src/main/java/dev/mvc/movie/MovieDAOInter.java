package dev.mvc.movie;

import java.util.HashMap;
import java.util.List;

public interface MovieDAOInter {

  /**
   *  ��ȭ���� �Է�
   * <Xmp>
   *  <insert id="create" parameterType="MovieVO">
   * </Xmp>
   * @param MovieVO
   * @return ó���� ���ڵ� ����
   */
  public int create(MovieVO movieVO);
  
  
  /**
   * ��ȭ �ڵ� ��ȸ(�����Ҷ� ���)
   * <Xmp>
   * <select id="list" resultType="MovieCdVO">
   * </Xmp>
   * @return
   */
  public List<MovieVO> codeList(); 
  
  /**
   * ��ȭ �߰� ���� ��� 
   * <Xmp>
   * <update id="update" parameterType="MovieVO">
   * </Xmp>
   * @param movieVO
   * @return
   */
  public int update(MovieVO movieVO);
  
  /**
   * ������ ��ȭ ����Ʈ
   * <Xmp>
   * <select id="a_movielist" resultType="MovieVO">
   * </Xmp>
   * @return
   */
  public List<MovieVO> a_movielist();
  
  
  /**
   * ������ ��ȭ �� �� ��ȸ
   * <Xmp>
   * <select id="a_movieread" resultType="MovieVO" parameterType="String">
   * </Xmp>
   * @param movieCd
   * @return 
   */
  public MovieVO a_movieread(String movieCd);
  
  
  /**
   * ������ ��ȭ ���� ó��
   * <Xmp>
   * <update id="a_movieupdate"  parameterType="MovieVO">
   * </Xmp>
   * @param movieVO
   * @return 1 or 0
   */
  public int a_movieupdate(MovieVO movieVO);


  /**
   * ������) �˻��� ���ڵ� ����
   * <Xmp>
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </Xmp>
   * @return
   */
  public int asearch_count(HashMap hashMap); 

  /**
   * ������ )��� + �˻�(��ȭ����) + ����¡
   * <Xmp>
   * <select id="a_list_search" resultType="MovieVO" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  public List<MovieVO> a_list_search(HashMap hashMap);

  /**
   * ���θ޴�) �˻��� ���ڵ� ����
   * <Xmp>
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </Xmp>
   * @return
   */
  public int search_count(HashMap hashMap); 
  
  /**
   * ���θ޴� )��� + �˻�(��ȭ����) + ����¡
   * <Xmp>
   * <select id="main_movie" resultType="MovieVO" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  public List<MovieVO> main_movie(HashMap hashMap);

  /**
   * �帣) �˻��� ���ڵ� ����
   * <Xmp>
   * <select id="search_count_genre" resultType="int" parameterType="HashMap">
   * </Xmp>
   * @return
   */
  public int search_count_genre(HashMap hashMap); 
  
  /**
   * �帣)��� + �˻�(��ȭ�帣) + ����¡
   * <Xmp>
   * <select id="list_search_genre" resultType="MovieVO" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  public List<MovieVO> list_search_genre(HashMap hashMap);
  
  /**
   * ��ȭ�� ��� ����
   * @param movieCd
   * @return
   */
  public int mcc(String movieCd);

  
  /**
   * �ڽ����ǽ� ��ȭ ����Ʈ
   * <Xmp>
   * <select id="boxOffice_List" resultType="BoxOfficeVO">
   * </Xmp>
   * @return
   */
  public List<BoxOfficeVO> boxOffice_List();
  
  /**
   * �ڽ����ǽ� ��ȭ �� �� ��ȸ
   * <Xmp>
   * <select id="boxOffice_read" resultType="BoxOfficeVO" parameterType="String">
   * </Xmp>
   * @param rank
   * @return
   */
  public BoxOfficeVO boxOffice_Read(int rank);
  
  /**
   * �ڽ����ǽ� ������Ʈ 
   * <Xmp>
   * <update id="boxOffice_update" parameterType="BoxOfficeVO">
   * </Xmp>
   * @param boxOfficeVO
   * @return
   */
  public int boxOffice_update(BoxOfficeVO boxOfficeVO);
  
  /**
   * ���� �ڽ����ǽ� ���
   * <Xmp>
   *  <select id="mainbo_list" resultType="MainBoxOfficeVO">
   *  </Xmp>
   * @return
   */
  public List<MainBoxOfficeVO> mainbo_list();
  
  
  /**
   * ���ƿ� ����
   * <Xmp>
   * <insert id="like_create" parameterType="MovielikeVO">
   * </Xmp>
   * @return
   */
  public int like_create(HashMap hashMap);
  
  /**
   * ���ƿ� ����
   * <Xmp>
   * <update id="like_check" parameterType="HashMap">
   * </Xmp>
   * @return
   */
  public int like_check(HashMap hashMap);
  
  /**
   * ���ƿ� ���
   * <Xmp>
   * update id="like_check_cancel" parameterType="HashMap">
   * </Xmp>
   * @return
   */
  public int like_check_cancel(HashMap hashMap);
  
  /**
   * ��ȭ�� ���ƿ� ����
   * <Xmp>
   * update id="like_count" parameterType="movieCd">
   * </Xmp>
   * @return
   */
  public int like_count(String movieCd);
  
  /**
   * ���ƿ� �� �� ��ȸ
   * <Xmp>
   * update id="like_read" parameterType="HashMap">
   * </Xmp>
   * @return
   */
  public MovielikeVO like_read(HashMap hashMap);
  
  /**
   * ȸ���� ��ȭ�� ���ƿ� üũ Ȯ��
   * <Xmp>
   * update id="a_like_count" parameterType="HashMap">
   * </Xmp>
   * @return
   */
  public int a_like_count(HashMap hashMap);
  
  /**
   * ȸ���� ����;�� ī��Ʈ
   * <Xmp>
   * <select id="mbc" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  public int mbc(HashMap hashMap);

  /**
   * ȸ���� ����;�� ���
   * <Xmp>
   * <select id="moviebucket" resultType="MoviebucketVO" parameterType="HashMap" >
   *  </Xmp>
   * @return
   */
  public List<MoviebucketVO> moviebucket(HashMap hashMap);
  
  /**
   * ȸ���� ��ȭ ��� ���� Ȯ��
   * <Xmp>
   * <select id="mc_check" resultType="int"  parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  public int mc_check(HashMap hashMap);
  
  
  /**
   * ��ȭ�� ��ȭ���� ��
   *  <Xmp>
   * <select id="grade_sum" resultType="int" parameterType="String">
   *  </Xmp>
   * @param movieCd
   * @return
   */
  public int grade_sum(String movieCd);
  
}
