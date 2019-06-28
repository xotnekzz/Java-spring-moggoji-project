package dev.mvc.review.comment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.movie.MovieVO;
import dev.mvc.movie.Movies;
import dev.mvc.tool.Tool;


@Component("dev.mvc.review.comment.RcommentProc")
public class RcommentProc implements RcommentProcInter {
  
  @Autowired
  @Qualifier("dev.mvc.review.comment.RcommentDAO")
  RcommentDAOInter rcommentDAO = null;
 
  public RcommentProc() {
    //System.out.println("--> ReviewProc Created...");
  }
  
  /**
   * �ڸ�Ʈ ���
   * <Xmp>
   * <insert id="create" parameterType="RcommentVO">
   * </Xmp>
   *  @param RcommentVO
   *  @return ó���� ���ڵ� ����
   */
  @Override 
  public int create(RcommentVO rcommentVO){
    int count = rcommentDAO.create(rcommentVO);
    return count;
  }

  /**
   * �ڸ�Ʈ ���
   * <Xmp>
   * <select id="list" resultType="RcommentVO" parameterType="int">
   * </Xmp>
   * @return
   */
 @Override
  public List<RcommentVO> list(int reviewno){
    List<RcommentVO> list = rcommentDAO.list(reviewno);
    return list;
  }
 
 /**
  * ��� ����
  * <Xmp>
  * <select id="delete" parameterType="hashMap">
  * </Xmp>
  * @param hashMap
  * @return
  */
 @Override
 public int delete(HashMap hashMap){
   int count = rcommentDAO.delete(hashMap);
   return count;
 }
  
 /**
  * ��� ī��Ʈ
  * @param reviewno
  * @return
  */
 @Override
 public int comment_count(int reviewno){
   int count = rcommentDAO.comment_count(reviewno);
   return count;
 }
 
 /**
  * �ڸ�Ʈ ��� + ����¡
  * <Xmp>
  * <select id="list_paging" resultType="RcommentVO" parameterType="int">
  * </Xmp>
  * @param reviewno
  * @return
  */
 public List<RcommentVO> list_paging(HashMap hashMap){
   /* 
   ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
   1 ������: nowPage = 1, (1 - 1) * 10 --> 0 
   2 ������: nowPage = 2, (2 - 1) * 10 --> 10
   3 ������: nowPage = 3, (3 - 1) * 10 --> 20
   */
  int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Rcomment.RECORD_PER_PAGE;
  
   // ���� rownum, 1 ������: 1 / 2 ������: 11 / 3 ������: 21 
  int startNum = beginOfPage + 1; 
  //  ���� rownum, 1 ������: 10 / 2 ������: 20 / 3 ������: 30
  int endNum = beginOfPage + Rcomment.RECORD_PER_PAGE;   
  /*
   1 ������: WHERE r >= 1 AND r <= 10
   2 ������: WHERE r >= 11 AND r <= 20
   3 ������: WHERE r >= 21 AND r <= 30
   */
  hashMap.put("startNum", startNum);
  hashMap.put("endNum", endNum);
  
  List<RcommentVO> list = rcommentDAO.list_paging(hashMap);
  
  return list;
 }
 
 /** 
  * ��� + �˻�(��ȭ����) + ����¡
  * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
  * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
  *
  * @param comment_count ��ü ���ڵ�� 
  * @param nowPage     ���� ������
  * @return ����¡ ���� ���ڿ�
  */ 
@Override
 public String paging(int comment_count, int nowPage){ 
   int totalPage = (int)(Math.ceil((double)comment_count/Rcomment.RECORD_PER_PAGE)); // ��ü ������  
   int totalGrp = (int)(Math.ceil((double)totalPage/Rcomment.PAGE_PER_BLOCK));// ��ü �׷� 
   int nowGrp = (int)(Math.ceil((double)nowPage/Rcomment.PAGE_PER_BLOCK));    // ���� �׷� 
   int startPage = ((nowGrp - 1) * Rcomment.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
   int endPage = (nowGrp * Rcomment.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
    
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
   int _nowPage = (nowGrp-1) * Rcomment.PAGE_PER_BLOCK; 
   if (nowGrp >= 2){ 
     str.append("<span class='span_box_1'><A href='./comment/list_search.do?nowPage="+_nowPage+"'>����</A></span>"); 
   } 

   for(int i=startPage; i<=endPage; i++){ 
     if (i > totalPage){ 
       break; 
     } 
 
     if (nowPage == i){ 
       str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
     }else{
       // ���� �������� �ƴ� ������
       str.append("<span class='span_box_1'><A href='./comment/list_search.do?nowPage="+i+"'>"+i+"</A></span>");   
     } 
   } 
    
// 10�� ���� �������� �̵� 
   //nowGrp : 1(1~10 page), nowGrp : 2(11~20 page), nowGrp : 3(21~30 page) 
   // ���� 1 �׷��� ��� : (1*10)+1 = 2 �׷��� 11
   // ���� 2 �׷��� ��� : (2*10)*+1 = 3 �׷��� 21
   _nowPage = (nowGrp * Rcomment.PAGE_PER_BLOCK)+1; 
   if (nowGrp < totalGrp){ 
     str.append("<span class='span_box_1'><A href='./comment/list_search.do?nowPage="+_nowPage+"'>����</A></span>"); 
   } 
   str.append("</DIV>"); 
    
   return str.toString(); 
 }


}
