package dev.mvc.movie.comment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.review.Review;
import dev.mvc.review.ReviewVO;
import dev.mvc.tool.Tool;


@Component("dev.mvc.movie.comment.McommentProc")
public class McommentProc implements McommentProcInter{

  @Autowired
  @Qualifier("dev.mvc.movie.comment.McommentDAO")
  McommentDAOInter mcommentDAO = null;
 
  public McommentProc() {
    //System.out.println("--> McommentProc Created...");
  }
  
  /**
   * ��� ���
   * <Xmp>
   * <insert id="create" parameterType="McommentVO">
   * </Xmp>
   *  @param RcommentVO
   *  @return ó���� ���ڵ� ����
   */
  @Override
  public int create(McommentVO mcommentVO){
    int count = mcommentDAO.create(mcommentVO);
    return count;
  }
  
  /**
   * ��� ���
   * <Xmp>
   * <select id="list" resultType="McommentVO" parameterType="String">
   * </Xmp>
   * @return
   */
  @Override
  public List<McommentVO> list(String movieCd){
    List<McommentVO> list = mcommentDAO.list(movieCd);
    return list;
  }
  
  /**
   * ��ȭ�� ��� ����
   * <Xmp>
   *  <select id="mcc" resultType="int" parameterType="String">
   *  </Xmp>
   * @param movieVO
   * @return
   */
  @Override
  public int mcc(String movieCd){
    int cnt = mcommentDAO.mcc(movieCd);
    return cnt;
  }

  /**
   * ȸ���� ��ȭ ��� ����
   * <Xmp>
   *  <select id="m_mccount" resultType="int" parameterType="int">
   *  </Xmp>
   * @param movieVO
   * @return
   */
  @Override
  public int m_mccount(HashMap hashMap) {
    int cnt = mcommentDAO.m_mccount(hashMap);
    return cnt;
  }

  /**
   * ȸ���� ��ȭ ��� ����Ʈ
   * <select id="m_mclist" resultType="McommentVO" parameterType="HashMap">
   * @param memberno
   * @return
   */
  @Override 
  public List<MovieNmVO> m_mclist(HashMap hashMap) {
     
  /*  ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
    1 ������: nowPage = 1, (1 - 1) * 10 --> 0 
    2 ������: nowPage = 2, (2 - 1) * 10 --> 10
    3 ������: nowPage = 3, (3 - 1) * 10 --> 20*/
    
   int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Mcomment.RECORD_PER_PAGE;
   
    // ���� rownum, 1 ������: 1 / 2 ������: 11 / 3 ������: 21 
   int startNum = beginOfPage + 1; 
   //  ���� rownum, 1 ������: 10 / 2 ������: 20 / 3 ������: 30
   int endNum = beginOfPage + Mcomment.RECORD_PER_PAGE;   
   
   /* 1 ������: WHERE r >= 1 AND r <= 10
    2 ������: WHERE r >= 11 AND r <= 20
    3 ������: WHERE r >= 21 AND r <= 30*/
    
   hashMap.put("startNum", startNum);
   hashMap.put("endNum", endNum);
   
   List<MovieNmVO> list = mcommentDAO.m_mclist(hashMap);
   Iterator<MovieNmVO> iter = list.iterator();
    
   while(iter.hasNext() == true) {
     MovieNmVO movieNmVO = iter.next();
     String content = Tool.textLength(movieNmVO.getContent(), 80);
     content = Tool.convertChar(content); // �±� ó��
     movieNmVO.setContent(content);
    }
    return list;
  }

  
  /** 
   * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
   * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
   *
   * @param m_mccount ��� ����
   * @param nowPage     ���� ������
   * @param word �˻���
   * @return ����¡ ���� ���ڿ�
   */ 
  @Override
  public String paging(int m_mccount, int nowPage, String word, int memberno) {
    int totalPage = (int)(Math.ceil((double)m_mccount/Mcomment.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Mcomment.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Mcomment.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Mcomment.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Mcomment.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
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
    int _nowPage = (nowGrp-1) * Mcomment.PAGE_PER_BLOCK; 
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./m_mclist.do?memberno="+memberno+"&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
  
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='./m_mclist.do?memberno="+memberno+"&word="+word+"&nowPage="+i+"'>"+i+"</A></span>");   
      } 
    } 
     
 // 10�� ���� �������� �̵� 
    //nowGrp : 1(1~10 page), nowGrp : 2(11~20 page), nowGrp : 3(21~30 page) 
    // ���� 1 �׷��� ��� : (1*10)+1 = 2 �׷��� 11
    // ���� 2 �׷��� ��� : (2*10)*+1 = 3 �׷��� 21
    _nowPage = (nowGrp * Mcomment.PAGE_PER_BLOCK)+1; 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='.m_mclist.do?memberno="+memberno+"&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }
  
  /**
   * ȸ���� ��ȭ ��� ����
   * <Xmp>
   * <delete id="delete" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  @Override
  public int delete (HashMap hashMap){
    int count = mcommentDAO.delete(hashMap);
    return count;
  }
  
  /**
   * ȸ���� ��ȭ ��� �� �� ��ȸ
   * <Xmp>
   * <select id="read" resultType="MovieNmVO" parameterType="HashMap">
   * </Xmp>
   * @param hashMap
   * @return
   */
  public MovieNmVO read(HashMap hashMap){
    MovieNmVO movieNmVO = mcommentDAO.read(hashMap);
    return movieNmVO;
  }

  /**
   * ȸ���� ��ȭ ��� ����
   *  <Xmp>
   *  <update id="update" parameterType="movieNmVO">
   *  </Xmp>
   * @param movieNmVO
   * @return
   */
  @Override
  public int update(HashMap hashMap) {
   int count = mcommentDAO.update(hashMap);
   return count;
  }
  
  /*���� �� ����*/
  @Override
  public int star_all_count(){
    int count = mcommentDAO.star_all_count();
    return count;
  }
}
