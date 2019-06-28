package dev.mvc.review;

import java.util.HashMap;
import java.util.List;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;

@Component("dev.mvc.review.ReviewProc")
public class ReviewProc implements ReviewProcInter {
  
  @Autowired
  @Qualifier("dev.mvc.review.ReviewDAO")
  ReviewDAOInter reviewDAO = null;
 
  public ReviewProc() {
    //System.out.println("--> ReviewProc Created...");
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
    int count = reviewDAO.create(reviewVO);
    return count;
  }
  
  /**
   *  ��� ��ȸ
   * <Xmp>
   *  <select id="list" resultType="ReviewVO">
   * </Xmp>
   * @return 
   */
  @Override
  public List<ReviewVO> list() {
    List<ReviewVO> list = reviewDAO.list();
    return list; 
  }

  /**
   *  �� �� ��ȸ
   * <Xmp>
   * <select id="read" resultType="ReviewVO" parameterType="int">
   * </Xmp>
   * @param reviewno 
   * @return
   */
  @Override
  public ReviewVO read(int reviewno) {
    ReviewVO reviewVO = reviewDAO.read(reviewno);
    if(reviewVO != null) { // ���� ��ȸ�ߴٸ�
      int count = reviewDAO.cnt_increase(reviewVO); // ��ȸ�� ����
    }
    return reviewVO;
  }

  //������
  @Override
  public ReviewVO update(int reviewno){
    ReviewVO reviewVO = reviewDAO.update(reviewno);
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
    int count = reviewDAO.update(reviewVO);
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
    int count = reviewDAO.delete(reviewno);
    return count;
  } 
  
  /**
   * ��� +�˻�(����)+ ����¡(���θ޴�)
   * <Xmp>
   * <select id="all_list_search" resultType="ReviewVO" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  @Override
  public List<ReviewVO> all_list_search(HashMap hashMap) {
    
    /*
     ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
     1 ������: nowPage = 1, (1 - 1) * 10 --> 0 
     2 ������: nowPage = 2, (2 - 1) * 10 --> 10
     3 ������: nowPage = 3, (3 - 1) * 10 --> 20
     */
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Review.RECORD_PER_PAGE;
    
     // ���� rownum, 1 ������: 1 / 2 ������: 11 / 3 ������: 21 
    int startNum = beginOfPage + 1; 
    //  ���� rownum, 1 ������: 10 / 2 ������: 20 / 3 ������: 30
    int endNum = beginOfPage + Review.RECORD_PER_PAGE;   
    /*
     1 ������: WHERE r >= 1 AND r <= 10
     2 ������: WHERE r >= 11 AND r <= 20
     3 ������: WHERE r >= 21 AND r <= 30
     */
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    List<ReviewVO> list = reviewDAO.all_list_search(hashMap); 
    Iterator<ReviewVO> iter = list.iterator();
    
    while(iter.hasNext() == true) {
      ReviewVO reviewVO = iter.next();
      String title = Tool.textLength(reviewVO.getTitle(), 90);
      title = Tool.convertChar(title); // �±� ó��
      reviewVO.setTitle(title);
    }
    
    return list;
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
    int cnt = reviewDAO.search_count(hashMap);
    return cnt;
  }
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� (��ü)
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  @Override
  public String paging(int search_count, int nowPage, String word){ 
    int totalPage = (int)(Math.ceil((double)search_count/Review.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Review.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Review.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Review.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Review.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
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
    _nowPage = (nowGrp * Review.PAGE_PER_BLOCK)+1; 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./all_search.do?&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }
  
  
  /**
   * ��� +�˻�(����)+ ����¡(ȸ��)
   * <Xmp>
   * <select id="m_list_search" resultType="ReviewVO" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  @Override
  public List<ReviewVO> m_list_search(HashMap hashMap) {
    /* 
     ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
     1 ������: nowPage = 1, (1 - 1) * 10 --> 0 
     2 ������: nowPage = 2, (2 - 1) * 10 --> 10
     3 ������: nowPage = 3, (3 - 1) * 10 --> 20
     */
    int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Review.RECORD_PER_PAGE;
    
     // ���� rownum, 1 ������: 1 / 2 ������: 11 / 3 ������: 21 
    int startNum = beginOfPage + 1; 
    //  ���� rownum, 1 ������: 10 / 2 ������: 20 / 3 ������: 30
    int endNum = beginOfPage + Review.RECORD_PER_PAGE;   
    /*
     1 ������: WHERE r >= 1 AND r <= 10
     2 ������: WHERE r >= 11 AND r <= 20
     3 ������: WHERE r >= 21 AND r <= 30
     */
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);
    
    List<ReviewVO> list = reviewDAO.m_list_search(hashMap); 
    Iterator<ReviewVO> iter = list.iterator();
    
    while(iter.hasNext() == true) {
      ReviewVO reviewVO = iter.next();
      String title = Tool.textLength(reviewVO.getTitle(), 90);
      title = Tool.convertChar(title); // �±� ó��
      reviewVO.setTitle(title);
    }
    
    return list;
  }
  
  /**
   * �˻��� ���ڵ� ����
   * <Xmp>
   * <select id="msearch_count" resultType="int" parameterType="HashMap">
   * </Xmp>
   * @return
   */
  @Override
  public int msearch_count(HashMap hashMap) {
    int cnt = reviewDAO.msearch_count(hashMap);
    return cnt;
  }
  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� (ȸ��)
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param search_count �˻�(��ü) ���ڵ�� 
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  @Override
  public String mpaging(int msearch_count, int nowPage, String word, int memberno){ 
    /*int memberno = (int) session.getAttribute("memberno");*/
    
    int totalPage = (int)(Math.ceil((double)msearch_count/Review.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Review.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Review.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Review.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Review.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
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
      str.append("<span class='span_box_1'><A href='./m_list_search.do?memberno="+memberno+"&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
 
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='./m_list_search.do?memberno="+memberno+"&word="+word+"&nowPage="+i+"'>"+i+"</A></span>");   
      } 
    } 
     
 // 10�� ���� �������� �̵� 
    //nowGrp : 1(1~10 page), nowGrp : 2(11~20 page), nowGrp : 3(21~30 page) 
    // ���� 1 �׷��� ��� : (1*10)+1 = 2 �׷��� 11
    // ���� 2 �׷��� ��� : (2*10)*+1 = 3 �׷��� 21
    _nowPage = (nowGrp * Review.PAGE_PER_BLOCK)+1; 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./m_list_search.do?memberno="+memberno+"&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }
  
  /**
   * ���並 �� ȸ�� �̸�
   * <Xmp>
   * <select id="mname" resultMap="MnameVO" parameterType="int">
   * </Xmp>
   * @param reviewno
   * @return
   */
  public MnameVO mname(int reviewno){
    MnameVO mnameVO = reviewDAO.mname(reviewno);
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
    int cnt = reviewDAO.rcc(reviewno);
    return cnt;
  }
  
}
