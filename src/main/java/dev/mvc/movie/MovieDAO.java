package dev.mvc.movie;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dev.mvc.movie.MovieDAO")
public class MovieDAO implements MovieDAOInter{
  
  @Autowired 
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public MovieDAO() {
   //System.out.println("--> MovieDAO create.");
    
    if(sqlSessionTemplate != null) {
      //System.out.println("--> sqlSessionTemplate �Ҵ��.");
    }
  }
 
  /**
   *  ��ȭ���� �Է�
   * <Xmp>
   *  <insert id="create" parameterType="MovieVO">
   * </Xmp>
   * @param MovieVO
   * @return ó���� ���ڵ� ����
   */
  @Override
  public int create(MovieVO movieVO) {
    int count = sqlSessionTemplate.insert("movie.create", movieVO);
    return count; 
  }

  /**
   * ��ȭ �ڵ� ���
   * <Xmp>
   * <select id="list" resultType="MovieCdVO">
   * </Xmp>
   * @return
   */
  @Override
  public List<MovieVO> codeList() {
    List<MovieVO> codeList = sqlSessionTemplate.selectList("movie.list");
    return codeList;
  }
  
  /**
   * ��ȭ �߰� ���� ��� 
   * <Xmp>
   * <update id="update" parameterType="MovieVO">
   * </Xmp>
   * @param movieVO
   * @return
   */
  @Override
  public int update(MovieVO movieVO) {
    int count = sqlSessionTemplate.update("movie.update", movieVO);
    return count; 
  } 
  
  /**
   * ������ ��ȭ ����Ʈ
   * <Xmp>
   * <select id="a_movielist" resultType="MovieVO">
   * <Xmp>
   * @return
   */
  @Override
  public List<MovieVO> a_movielist(){
    List<MovieVO> list = sqlSessionTemplate.selectList("movie.a_movielist");
    return list;
  }

  /**
   * ������ ��ȭ �� �� ��ȸ
   * <Xmp>
   * <select id="a_movieread" resultType="movieVO" parameterType="String">
   * </Xmp>
   * @param movieCd
   * @return
   */
  @Override
  public MovieVO a_movieread(String movieCd){
    MovieVO movieVO = sqlSessionTemplate.selectOne("movie.a_movieread", movieCd);
    return movieVO;
  }
  
  /**
   * ������ ��ȭ ���� ó��
   * <Xmp>
   * <update id="a_movieupdate"  parameterType="MovieVO">
   * </Xmp>
   * @param movieVO
   * @return
   */
  @Override
  public int a_movieupdate(MovieVO movieVO){
    int count = sqlSessionTemplate.update("movie.a_movieupdate", movieVO);
    return count;
  }

  /**
   * ������) �˻��� ���ڵ� ����
   * <Xmp>
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </Xmp>
   * @return
   */
  @Override
  public int asearch_count(HashMap hashMap) {
    int cnt = sqlSessionTemplate.selectOne("movie.asearch_count", hashMap);
    return cnt;
  }
  
  /**
   * ������) ��� + �˻�(��ȭ����) + ����¡
   * <Xmp>
   * <select id="a_list_search" resultType="ReviewVO" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  @Override
  public List<MovieVO> a_list_search(HashMap hashMap){
    List<MovieVO> list = null;
    list = sqlSessionTemplate.selectList("movie.a_list_search", hashMap);
    return list;
  }
  
  /**
   * ���θ޴�) �˻��� ���ڵ� ����
   * <Xmp>
   * <select id="search_count" resultType="int" parameterType="HashMap">
   * </Xmp>
   * @return
   */
  @Override
  public int search_count(HashMap hashMap) {
    int cnt = sqlSessionTemplate.selectOne("movie.search_count", hashMap);
    return cnt;
  }
  
  /**
   * ���θ޴� )��� + �˻�(��ȭ����) + ����¡
   * <Xmp>
   * <select id="main_movie" resultType="MovieVO" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  @Override
  public List<MovieVO> main_movie(HashMap hashMap){
    List<MovieVO> list = null;
    list = sqlSessionTemplate.selectList("movie.main_movie", hashMap);
    return list;
  }
  
  /**
   * �帣) �˻��� ���ڵ� ����
   * <Xmp>
   * <select id="search_count_genre" resultType="int" parameterType="HashMap">
   * </Xmp>
   * @return
   */
  @Override
  public int search_count_genre(HashMap hashMap) {
    int cnt = sqlSessionTemplate.selectOne("movie.search_count_genre", hashMap);
    return cnt;
  }
  
  /**
   * �帣) ��� + �˻�(��ȭ����) + ����¡
   * <Xmp>
   * <select id="a_list_search" resultType="ReviewVO" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  @Override
  public List<MovieVO> list_search_genre(HashMap hashMap){
    List<MovieVO> list = null;
    list = sqlSessionTemplate.selectList("movie.list_search_genre", hashMap);
    return list;
  }

  /**
   * ��ȭ�� ��� ����
   * @param movieCd
   * @return
   */
  public int mcc(String movieCd){
    int cnt = sqlSessionTemplate.selectOne("movie.mcc", movieCd);
    return cnt;
  }
  
  /**
   * �ڽ����ǽ� ����Ʈ
   * <Xmp>
   * <select id="boxOffice_List" resultType="BoxOfficeVO">
   * <Xmp>
   * @return
   */
  @Override
  public List<BoxOfficeVO> boxOffice_List(){
    List<BoxOfficeVO> boxOffice_List  = sqlSessionTemplate.selectList("movie.boxOffice_List");
    return boxOffice_List;
  }
  
  /**
   * �ڽ����ǽ� ��ȭ �� �� ��ȸ
   * <Xmp>
   * <select id="boxOffice_Read" resultType="boxOfficeVO" parameterType="String">
   * </Xmp>
   * @param movieCd
   * @return
   */
  @Override
  public BoxOfficeVO boxOffice_Read(int rank){
    BoxOfficeVO boxOfficeVO = sqlSessionTemplate.selectOne("movie.boxOffice_Read", rank);
    return boxOfficeVO;
  }
  
  /**
   * �ڽ����ǽ� ��ȭ ���� ó��
   * <Xmp>
   * <update id="boxOffice_update"  parameterType="BoxOfficeVO">
   * </Xmp>
   * @param movieVO
   * @return
   */
  @Override
  public int boxOffice_update(BoxOfficeVO boxOfficeVO){
    int count = sqlSessionTemplate.update("movie.boxOffice_update", boxOfficeVO);
    return count;
  }
  
  /**
   * ���� �ڽ����ǽ� ���
   * <Xmp>
   *  <select id="mainbo_list" resultType="MainBoxOfficeVO">
   *  </Xmp>
   * @return
   */
  public List<MainBoxOfficeVO> mainbo_list(){
    List<MainBoxOfficeVO> list = sqlSessionTemplate.selectList("movie.mainbo_list");
    return list;
  }
  
  
  /**
   * ���ƿ� ����
   * <Xmp>
   * <insert id="like_create" parameterType="HashMap">
   * </Xmp>
   * @return
   */
  public int like_create(HashMap hashMap){
   int count = sqlSessionTemplate.insert("movie.like_create",hashMap);
   return count;
  }
  
  /**
   * ���ƿ� ����
   * <Xmp>
   * <update id="like_check" parameterType="HashMap">
   * </Xmp>
   * @return
   */
  public int like_check(HashMap hashMap){
    int count = sqlSessionTemplate.update("movie.like_check", hashMap);
    return count;
  }
  
  /**
   * ���ƿ� ���
   * <Xmp>
   * update id="like_check_cancel" parameterType="HashMap">
   * </Xmp>
   * @return
   */
  public int like_check_cancel(HashMap hashMap){
    int count = sqlSessionTemplate.update("movie.like_check_cancel", hashMap);
    return count;
  }
  
  /**
   * ��ȭ�� ���ƿ� ����
   * <Xmp>
   * update id="like_count" parameterType="movieCd">
   * </Xmp>
   * @return
   */
  public int like_count(String movieCd){
    int count = sqlSessionTemplate.selectOne("movie.like_count", movieCd);
    return count;
  }
  
  /**
   * ���ƿ� �� �� ��ȸ
   * <Xmp>
   * update id="like_read" parameterType="HashMap">
   * </Xmp>
   * @return
   */
  public MovielikeVO like_read(HashMap hashMap){
    MovielikeVO movielikeVO = sqlSessionTemplate.selectOne("movie.like_read", hashMap);
    return movielikeVO;
  }
  
  /**
   * ȸ���� ��ȭ�� ���ƿ� üũ Ȯ��
   * <Xmp>
   * update id="a_like_count" parameterType="HashMap">
   * </Xmp>
   * @return
   */
  public int a_like_count(HashMap hashMap){
    int count = sqlSessionTemplate.selectOne("movie.a_like_count", hashMap);
    return count;
  }
  
  /**
   * ȸ���� ����;�� ī��Ʈ
   * <Xmp>
   * <select id="mbc" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  public int mbc(HashMap hashMap){
    int cnt = sqlSessionTemplate.selectOne("movie.mbc", hashMap);
    return cnt;
  }
  
  /**
   * ȸ���� ����;�� ���
   * <Xmp>
   * <select id="moviebucket" resultType="MoviebucketVO" parameterType="HashMap" >
   *  </Xmp>
   * @return
   */
  public List<MoviebucketVO> moviebucket(HashMap hashMap){
    List<MoviebucketVO> list = sqlSessionTemplate.selectList("movie.moviebucket",hashMap);
    return list;
  }
  
  /**
   * ȸ���� ��ȭ ��� ���� Ȯ��
   * <Xmp>
   * <select id="mc_check" resultType="int"  parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  public int mc_check(HashMap hashMap){
    int cnt = sqlSessionTemplate.selectOne("movie.mc_check", hashMap);
    return cnt;
  }
  
  /**
   * ��ȭ�� ��ȭ���� ��
   *  <Xmp>
   * <select id="grade_sum" resultType="int" parameterType="String">
   *  </Xmp>
   * @param movieCd
   * @return
   */
  public int grade_sum(String movieCd){
    int sum = sqlSessionTemplate.selectOne("movie.grade_sum", movieCd);
    return sum;
  }
}
