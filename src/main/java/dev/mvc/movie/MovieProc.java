package dev.mvc.movie;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import dev.mvc.movie.comment.MovieNmVO;
import dev.mvc.review.Review;
import dev.mvc.tool.Tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("dev.mvc.movie.MovieProc")
public class MovieProc implements MovieProcInter {
  @Autowired
  @Qualifier("dev.mvc.movie.MovieDAO")
  private MovieDAOInter movieDAO = null;
  
  public MovieProc() { 
    //System.out.println("--> MoiveProc Created.");
  }
   
  //��ȭ�ֱ�(open api)
  @Override
  public int create(MovieVO movieVO) {
    int count = movieDAO.create(movieVO);
    return count;
  }

  //��ȭ�ڵ� ���
  @Override
  public List<MovieVO> codeList() {
    List<MovieVO> codeList = movieDAO.codeList();
    return codeList;
  }

  //��ȭ ����(open api)
  @Override
  public int update(MovieVO movieVO) {
    int count = movieDAO.update(movieVO);
    return count;
  }

  //������ ��ȭ ���
  @Override
  public List<MovieVO> a_movielist() {
    List<MovieVO> list = movieDAO.a_movielist();
    return list;
  }  
  
  //������ ��ȭ �� �� ��ȸ
  @Override
  public MovieVO a_movieread(String movieCd){
    MovieVO movieVO = movieDAO.a_movieread(movieCd);
    return movieVO;
  }
  
  //������ ��ȭ ���� ��
  @Override
  public MovieVO a_movieupdate(String movieCd){
    MovieVO movieVO = movieDAO.a_movieread(movieCd);
    return movieVO;
  }
  
  // ������ ��ȭ ���� ó��
  @Override
  public int a_movieupdate(MovieVO movieVO){
    int count = movieDAO.a_movieupdate(movieVO);
    return count;
  }
  
  //������) ��ȭ ���
  @Override
  public List<MovieVO> a_list_search(HashMap hashMap) {
    /* 
     ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
     1 ������: nowPage = 1, (1 - 1) * 10 --> 0 
     2 ������: nowPage = 2, (2 - 1) * 10 --> 10
     3 ������: nowPage = 3, (3 - 1) * 10 --> 20
     */
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Movies.RECORD_PER_PAGE;
    
     // ���� rownum, 1 ������: 1 / 2 ������: 11 / 3 ������: 21 
    int startNum = beginOfPage + 1; 
    //  ���� rownum, 1 ������: 10 / 2 ������: 20 / 3 ������: 30
    int endNum = beginOfPage + Movies.RECORD_PER_PAGE;   
    /*
     1 ������: WHERE r >= 1 AND r <= 10
     2 ������: WHERE r >= 11 AND r <= 20
     3 ������: WHERE r >= 21 AND r <= 30
     */
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    List<MovieVO> list = movieDAO.a_list_search(hashMap); 
    Iterator<MovieVO> iter = list.iterator();
    
    while(iter.hasNext() == true) {
      MovieVO movieVO = iter.next();
      String movieNm = Tool.textLength(movieVO.getMovieNm(), 90);
      movieNm = Tool.convertChar(movieNm); // �±� ó��
      movieVO.setMovieNm(movieNm);
    }
    
    return list;
  }
  
  //������) �˻��� ���ڵ� ����
  @Override
  public int asearch_count(HashMap hashMap) {
    int cnt = movieDAO.asearch_count(hashMap);
    return cnt;
  }
  
  /** 
   * ������)��� + �˻�(��ȭ����) + ����¡
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
 @Override
  public String apaging(int asearch_count, int nowPage, String word){ 
    int totalPage = (int)(Math.ceil((double)asearch_count/Movies.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Movies.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Movies.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Movies.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Movies.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
    StringBuffer str = new StringBuffer(); 
     
    str.append("<style type='text/css'>"); 
    str.append("  #paging {text-align: center; margin-top: 5px; margin-bottom:5px; font-size: 1.1em;}"); 
    str.append("  #paging A:link {text-decoration:none; color:#aaaaaa; font-size: 1.1em;}"); 
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color: #aaaaaa; font-size: 1.1em;}"); 
    str.append("  #paging A:visited {text-decoration:none;color: #aaaaaa; font-size: 1.1em;}"); 
    str.append("  .span_box_1{");  
    str.append("    text-align: center;");    
    str.append("    font-size: 1.1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #aaaaaa;"); 
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("  .span_box_2{"); //����
    str.append("    text-align: center;");     
    str.append("    background-color: #31106D;"); 
    str.append("    color: #FFFFFF;"); 
    str.append("    font-size: 1.2em;"); 
    str.append("    border: 1px;");  
    str.append("    border-style: solid;");  
    str.append("    border-color: #31106D;");  
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>"); 
   //str.append("���� ������: " + nowPage + " / " + totalPage + "  "); 
 
    // ���� 10�� �������� �̵� 
    //nowGrp : 1(1~10 page), nowGrp : 2(11~20 page), nowGrp : 3(21~30 page) 
    // ���� 2 �׷��� ��� : (2-1)*10 = 1�׷��� 10
    // ���� 3 �׷��� ��� : (3-1)*10 = 2 �׷��� 10
    int _nowPage = (nowGrp-1) * Movies.PAGE_PER_BLOCK; 
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./a_movielist_search.do?word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
 
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='./a_movielist_search.do?word="+word+"&nowPage="+i+"'>"+i+"</A></span>");   
      } 
    } 
     
 // 10�� ���� �������� �̵� 
    //nowGrp : 1(1~10 page), nowGrp : 2(11~20 page), nowGrp : 3(21~30 page) 
    // ���� 1 �׷��� ��� : (1*10)+1 = 2 �׷��� 11
    // ���� 2 �׷��� ��� : (2*10)*+1 = 3 �׷��� 21
    _nowPage = (nowGrp * Movies.PAGE_PER_BLOCK)+1; 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./a_movielist_search.do.do?word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }

 /**
  * ���θ޴� )��� + �˻�(��ȭ����) + ����¡
  * <Xmp>
  * <select id="main_movie" resultType="MovieVO" parameterType="HashMap">
  * </Xmp>
  * @param hashMap
  * @return
  */
 public List<MovieVO> main_movie(HashMap hashMap){
   /* 
   ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
   1 ������: nowPage = 1, (1 - 1) * 10 --> 0 
   2 ������: nowPage = 2, (2 - 1) * 10 --> 10
   3 ������: nowPage = 3, (3 - 1) * 10 --> 20
   */
  int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Movies.RECORD_PER_PAGE;
  
   // ���� rownum, 1 ������: 1 / 2 ������: 11 / 3 ������: 21 
  int startNum = beginOfPage + 1; 
  //  ���� rownum, 1 ������: 10 / 2 ������: 20 / 3 ������: 30
  int endNum = beginOfPage + Movies.RECORD_PER_PAGE;   
  /*
   1 ������: WHERE r >= 1 AND r <= 10
   2 ������: WHERE r >= 11 AND r <= 20
   3 ������: WHERE r >= 21 AND r <= 30
   */
  hashMap.put("startNum", startNum);
  hashMap.put("endNum", endNum);
  
  List<MovieVO> list = movieDAO.main_movie(hashMap); 
  Iterator<MovieVO> iter = list.iterator();
  
  while(iter.hasNext() == true) {
    MovieVO movieVO = iter.next();
    String movieNm = Tool.textLength(movieVO.getMovieNm(), 90);
    movieNm = Tool.convertChar(movieNm); // �±� ó��
    movieVO.setMovieNm(movieNm);
    } 
  
  return list;
 }
 
 //���θ޴�) �˻��� ���ڵ� ����
 @Override
 public int search_count(HashMap hashMap) {
   int cnt = movieDAO.search_count(hashMap);
   return cnt;
 }
 
 /** 
  * ��� + �˻�(��ȭ����) + ����¡(���θ޴�)
  * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
  * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
  *
  * @param search_count �˻�(��ü) ���ڵ�� 
  * @param nowPage     ���� ������
  * @param word �˻���
  * @return ����¡ ���� ���ڿ�
  */ 
@Override
 public String paging(int search_count, int nowPage, String col, String word){ 
   int totalPage = (int)(Math.ceil((double)search_count/Movies.RECORD_PER_PAGE)); // ��ü ������  
   int totalGrp = (int)(Math.ceil((double)totalPage/Movies.PAGE_PER_BLOCK));// ��ü �׷� 
   int nowGrp = (int)(Math.ceil((double)nowPage/Movies.PAGE_PER_BLOCK));    // ���� �׷� 
   int startPage = ((nowGrp - 1) * Movies.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
   int endPage = (nowGrp * Movies.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
    
   StringBuffer str = new StringBuffer(); 
    
   str.append("<style type='text/css'>"); 
   str.append("  #paging {text-align: center; margin-top: 5px; margin-bottom:5px; font-size: 1.1em;}"); 
   str.append("  #paging A:link {text-decoration:none; color:#aaaaaa; font-size: 1.1em;}"); 
   str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color: #aaaaaa; font-size: 1.1em;}"); 
   str.append("  #paging A:visited {text-decoration:none;color: #aaaaaa; font-size: 1.1em;}"); 
   str.append("  .span_box_1{");  
   str.append("    text-align: center;");    
   str.append("    font-size: 1.1em;"); 
   str.append("    border: 1px;"); 
   str.append("    border-style: solid;"); 
   str.append("    border-color: #aaaaaa;"); 
   str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
   str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
   str.append("  }"); 
   str.append("  .span_box_2{"); //����
   str.append("    text-align: center;");     
   str.append("    background-color: #31106D;"); 
   str.append("    color: #FFFFFF;"); 
   str.append("    font-size: 1.2em;"); 
   str.append("    border: 1px;");  
   str.append("    border-style: solid;");  
   str.append("    border-color: #31106D;");  
   str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
   str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
   str.append("  }"); 
   str.append("</style>"); 
   str.append("<DIV id='paging'>"); 
  //str.append("���� ������: " + nowPage + " / " + totalPage + "  "); 

   // ���� 10�� �������� �̵� 
   //nowGrp : 1(1~10 page), nowGrp : 2(11~20 page), nowGrp : 3(21~30 page) 
   // ���� 2 �׷��� ��� : (2-1)*10 = 1�׷��� 10
   // ���� 3 �׷��� ��� : (3-1)*10 = 2 �׷��� 10
   int _nowPage = (nowGrp-1) * Movies.PAGE_PER_BLOCK; 
   if (nowGrp >= 2){ 
     str.append("<span class='span_box_1'><A href='./main_movie.do?col="+col+"&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
   } 

   for(int i=startPage; i<=endPage; i++){ 
     if (i > totalPage){ 
       break; 
     } 
 
     if (nowPage == i){ 
       str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
     }else{
       // ���� �������� �ƴ� ������
       str.append("<span class='span_box_1'><A href='./main_movie.do?col="+col+"&word="+word+"&nowPage="+i+"'>"+i+"</A></span>");   
     } 
   } 
    
// 10�� ���� �������� �̵� 
   //nowGrp : 1(1~10 page), nowGrp : 2(11~20 page), nowGrp : 3(21~30 page) 
   // ���� 1 �׷��� ��� : (1*10)+1 = 2 �׷��� 11
   // ���� 2 �׷��� ��� : (2*10)*+1 = 3 �׷��� 21
   _nowPage = (nowGrp * Movies.PAGE_PER_BLOCK)+1; 
   if (nowGrp < totalGrp){ 
     str.append("<span class='span_box_1'><A href='./main_movie.do?col="+col+"&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
   } 
   str.append("</DIV>"); 
    
   return str.toString(); 
 }

//�帣) �˻��� ���ڵ� ����
@Override
public int search_count_genre(HashMap hashMap) {
int cnt = movieDAO.search_count_genre(hashMap);
return cnt;
}

/**
 * �帣 )�帣��� + �˻�(��ȭ����) + ����¡
 * <Xmp>
 * <select id="main_movie_genre" resultType="MovieVO" parameterType="HashMap">
 * </Xmp>
 * @param hashMap
 * @return
 */
public List<MovieVO> list_search_genre(HashMap hashMap){
  /* 
  ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
  1 ������: nowPage = 1, (1 - 1) * 10 --> 0 
  2 ������: nowPage = 2, (2 - 1) * 10 --> 10
  3 ������: nowPage = 3, (3 - 1) * 10 --> 20
  */
 int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Movies.RECORD_PER_PAGE;
 
  // ���� rownum, 1 ������: 1 / 2 ������: 11 / 3 ������: 21 
 int startNum = beginOfPage + 1; 
 //  ���� rownum, 1 ������: 10 / 2 ������: 20 / 3 ������: 30
 int endNum = beginOfPage + Movies.RECORD_PER_PAGE;   
 /*
  1 ������: WHERE r >= 1 AND r <= 10
  2 ������: WHERE r >= 11 AND r <= 20
  3 ������: WHERE r >= 21 AND r <= 30
  */
 hashMap.put("startNum", startNum);
 hashMap.put("endNum", endNum);
 
 List<MovieVO> list = movieDAO.list_search_genre(hashMap); 
 Iterator<MovieVO> iter = list.iterator();
 
 while(iter.hasNext() == true) {
   MovieVO movieVO = iter.next();
   String movieNm = Tool.textLength(movieVO.getMovieNm(), 90);
   movieNm = Tool.convertChar(movieNm); // �±� ó��
   movieVO.setMovieNm(movieNm);
   } 
 
 return list;
}

/** 
 * ��� + �˻�(��ȭ�帣) + ����¡(���θ޴�)
 * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
 * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
 *
 * @param search_count �˻�(��ü) ���ڵ�� 
 * @param nowPage     ���� ������
 * @param genre �˻���
 * @return ����¡ ���� ���ڿ�
 */ 
@Override
public String paging_genre(int search_count, int nowPage, String genre){ 
  int totalPage = (int)(Math.ceil((double)search_count/Movies.RECORD_PER_PAGE)); // ��ü ������  
  int totalGrp = (int)(Math.ceil((double)totalPage/Movies.PAGE_PER_BLOCK));// ��ü �׷� 
  int nowGrp = (int)(Math.ceil((double)nowPage/Movies.PAGE_PER_BLOCK));    // ���� �׷� 
  int startPage = ((nowGrp - 1) * Movies.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
  int endPage = (nowGrp * Movies.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
   
  StringBuffer str = new StringBuffer(); 
   
  str.append("<style type='text/css'>"); 
  str.append("  #paging {text-align: center; margin-top: 5px; margin-bottom:5px; font-size: 1.1em;}"); 
  str.append("  #paging A:link {text-decoration:none; color:#aaaaaa; font-size: 1.1em;}"); 
  str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color: #aaaaaa; font-size: 1.1em;}"); 
  str.append("  #paging A:visited {text-decoration:none;color: #aaaaaa; font-size: 1.1em;}"); 
  str.append("  .span_box_1{");  
  str.append("    text-align: center;");    
  str.append("    font-size: 1.1em;"); 
  str.append("    border: 1px;"); 
  str.append("    border-style: solid;"); 
  str.append("    border-color: #aaaaaa;"); 
  str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
  str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
  str.append("  }"); 
  str.append("  .span_box_2{"); //����
  str.append("    text-align: center;");     
  str.append("    background-color: #31106D;"); 
  str.append("    color: #FFFFFF;"); 
  str.append("    font-size: 1.2em;"); 
  str.append("    border: 1px;");  
  str.append("    border-style: solid;");  
  str.append("    border-color: #31106D;");  
  str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
  str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
  str.append("  }"); 
  str.append("</style>"); 
  str.append("<DIV id='paging'>"); 
 //str.append("���� ������: " + nowPage + " / " + totalPage + "  "); 

  // ���� 10�� �������� �̵� 
  //nowGrp : 1(1~10 page), nowGrp : 2(11~20 page), nowGrp : 3(21~30 page) 
  // ���� 2 �׷��� ��� : (2-1)*10 = 1�׷��� 10
  // ���� 3 �׷��� ��� : (3-1)*10 = 2 �׷��� 10
  int _nowPage = (nowGrp-1) * Movies.PAGE_PER_BLOCK; 
  if (nowGrp >= 2){ 
    str.append("<span class='span_box_1'><A href='./main_movie_genre.do?genre="+genre+"&nowPage="+_nowPage+"'>����</A></span>"); 
  } 

  for(int i=startPage; i<=endPage; i++){ 
    if (i > totalPage){ 
      break; 
    } 

    if (nowPage == i){ 
      str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
    }else{
      // ���� �������� �ƴ� ������
      str.append("<span class='span_box_1'><A href='./main_movie_genre.do?genre="+genre+"&nowPage="+i+"'>"+i+"</A></span>");   
    } 
  } 
   
//10�� ���� �������� �̵� 
  //nowGrp : 1(1~10 page), nowGrp : 2(11~20 page), nowGrp : 3(21~30 page) 
  // ���� 1 �׷��� ��� : (1*10)+1 = 2 �׷��� 11
  // ���� 2 �׷��� ��� : (2*10)*+1 = 3 �׷��� 21
  _nowPage = (nowGrp * Movies.PAGE_PER_BLOCK)+1; 
  if (nowGrp < totalGrp){ 
    str.append("<span class='span_box_1'><A href='./main_movie_genre.do?genre="+genre+"&nowPage="+_nowPage+"'>����</A></span>"); 
  } 
  str.append("</DIV>"); 
   
  return str.toString(); 
}


  //��ȭ�� ��� ����
  public int mcc(String movieCd){
    int cnt = movieDAO.mcc(movieCd);
    return cnt;
  }
  
  
  //�ڽ����ǽ� ����Ʈ
  @Override
  public List<BoxOfficeVO> boxOffice_List() {
    List<BoxOfficeVO> boxOffice_List = movieDAO.boxOffice_List();
    return boxOffice_List;
  }
  
  //�ڽ����ǽ� ��ȭ �� �� ��ȸ
  @Override
  public BoxOfficeVO boxOffice_Read(int rank){
    BoxOfficeVO boxOfficeVO = movieDAO.boxOffice_Read(rank);
    return boxOfficeVO;
  }
  
  //�ڽ����ǽ� ������Ʈ
  @Override
  public int boxOffice_update(BoxOfficeVO boxOfficeVO) {
    int count = movieDAO.boxOffice_update(boxOfficeVO);
    return count;
  }
  
 // ���� �ڽ����ǽ� ���
  public List<MainBoxOfficeVO> mainbo_list(){
    List<MainBoxOfficeVO> list = movieDAO.mainbo_list();
    
    Iterator<MainBoxOfficeVO> iter = list.iterator();
    
    while(iter.hasNext() == true) {
      MainBoxOfficeVO mainBoxOfficeVO = iter.next();
      String actors = Tool.textLength(mainBoxOfficeVO.getActors(), 40); //30�� ���� 
      actors = Tool.convertChar(actors); // �±� ó��
      mainBoxOfficeVO.setActors(actors);
     }
    
    return list;
  }

 //���ƿ� ����
  public int like_create(HashMap hashMap){
   int count = movieDAO.like_create(hashMap);
   return count;
  }
  
 // ���ƿ� ����
  public int like_check(HashMap hashMap){
    int count = movieDAO.like_check(hashMap);
    return count;
  }
  
// ���ƿ� ���
  public int like_check_cancel(HashMap hashMap){
    int count = movieDAO.like_check_cancel(hashMap);
    return count;
  }
  
 // ��ȭ�� ���ƿ� ����
  public int like_count(String movieCd){
    int count = movieDAO.like_count(movieCd);
    return count;
  }
  
 //���ƿ� �� �� ��ȸ
  @Override
  public MovielikeVO like_read(HashMap hashMap){
    MovielikeVO movielikeVO = movieDAO.like_read(hashMap);
    return movielikeVO;
  }
  
 // ȸ���� ��ȭ�� ���ƿ� üũ Ȯ��
  public int a_like_count(HashMap hashMap){
    int count = movieDAO.a_like_count(hashMap);
    return count;
  }
  

  
  //ȸ���� ����;�� ī��Ʈ
  public int mbc(HashMap hashMap){
    int cnt = movieDAO.mbc(hashMap);
    return cnt;
  }

  //ȸ���� ����;�� ���
  public List<MoviebucketVO> moviebucket(HashMap hashMap){
    
    /*
    ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
    1 ������: nowPage = 1, (1 - 1) * 10 --> 0 
    2 ������: nowPage = 2, (2 - 1) * 10 --> 10
    3 ������: nowPage = 3, (3 - 1) * 10 --> 20
    */
   int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Movies.RECORD_PER_PAGE;
   
    // ���� rownum, 1 ������: 1 / 2 ������: 11 / 3 ������: 21 
   int startNum = beginOfPage + 1; 
   //  ���� rownum, 1 ������: 10 / 2 ������: 20 / 3 ������: 30
   int endNum = beginOfPage + Movies.RECORD_PER_PAGE;   
   /*
    1 ������: WHERE r >= 1 AND r <= 10
    2 ������: WHERE r >= 11 AND r <= 20
    3 ������: WHERE r >= 21 AND r <= 30
    */
   hashMap.put("startNum", startNum);
   hashMap.put("endNum", endNum);
   
    List<MoviebucketVO> list = movieDAO.moviebucket(hashMap);
    return list;
  }
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� (��ü)
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param mbc �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  @Override
  public String mpaging(int mbc, int nowPage, String word){ 
    int totalPage = (int)(Math.ceil((double)mbc/Movies.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Movies.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Movies.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Movies.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Movies.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
    StringBuffer str = new StringBuffer(); 
     
    str.append("<style type='text/css'>"); 
    str.append("  #paging {text-align: center; margin-top: 5px; margin-bottom:5px; font-size: 1.1em;}"); 
    str.append("  #paging A:link {text-decoration:none; color:#31106D; font-size: 1.1em;}"); 
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color: #31106D; font-size: 1.1em;}"); 
    str.append("  #paging A:visited {text-decoration:none;color: #31106D; font-size: 1.1em;}"); 
    str.append("  .span_box_1{");  
    str.append("    text-align: center;");    
    str.append("    font-size: 1.1em;"); 
    str.append("    border: 1px;"); 
    str.append("    border-style: solid;"); 
    str.append("    border-color: #31106D;"); 
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("  .span_box_2{"); //����
    str.append("    text-align: center;");     
    str.append("    background-color: #31106D;"); 
    str.append("    color: #FFFFFF;"); 
    str.append("    font-size: 1.2em;"); 
    str.append("    border: 1px;");  
    str.append("    border-style: solid;"); 
    str.append("    border-color: #31106D;");  
    str.append("    padding:1px 6px 1px 6px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("    margin:1px 2px 1px 2px; /*��, ������, �Ʒ�, ����*/"); 
    str.append("  }"); 
    str.append("</style>"); 
    str.append("<DIV id='paging'>"); 
   //str.append("���� ������: " + nowPage + " / " + totalPage + "  "); 
 
    // ���� 10�� �������� �̵� 
    //nowGrp : 1(1~10 page), nowGrp : 2(11~20 page), nowGrp : 3(21~30 page) 
    // ���� 2 �׷��� ��� : (2-1)*10 = 1�׷��� 10
    // ���� 3 �׷��� ��� : (3-1)*10 = 2 �׷��� 10
    int _nowPage = (nowGrp-1) * Review.PAGE_PER_BLOCK; 
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./all_list_search.do?word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
 
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='./all_list_search.do?word="+word+"&nowPage="+i+"'>"+i+"</A></span>");   
      } 
    } 
     
 // 10�� ���� �������� �̵� 
    //nowGrp : 1(1~10 page), nowGrp : 2(11~20 page), nowGrp : 3(21~30 page) 
    // ���� 1 �׷��� ��� : (1*10)+1 = 2 �׷��� 11
    // ���� 2 �׷��� ��� : (2*10)*+1 = 3 �׷��� 21
    _nowPage = (nowGrp * Movies.PAGE_PER_BLOCK)+1; 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./all_search.do?&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
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
    int cnt = movieDAO.mc_check(hashMap);
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
    int sum = movieDAO.grade_sum(movieCd);
    return sum;
  }
  
}
