package dev.mvc.qna;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dev.mvc.member.Member;
import dev.mvc.member.MemberVO;

@Component("dev.mvc.qna.QnaProc")
public class QnaProc implements QnaProcInter{
  
  @Autowired
  @Qualifier("dev.mvc.qna.QnaDAO")
  QnaDAOInter qnaDAO = null;
  
  public QnaProc(){
    
  }
  //���� ���
  @Override
  public int create(QnaVO qnaVO) {
    int count = qnaDAO.create(qnaVO);
    return count;
  }
  
  //QnA ��ü ��� ��ȸ
  @Override
  public List<QnaVO> list() {
    List<QnaVO> list = qnaDAO.list();
    return list;
  }

  //QnA ��ü ��� ��ȸ + ����¡  + �˻�
  @Override
  public List<QnaVO> list(HashMap hashMap) {
    /* ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
    1 ������: nowPage = 1, (1 - 1) * 10 --> 0 
    2 ������: nowPage = 2, (2 - 1) * 10 --> 10
    3 ������: nowPage = 3, (3 - 1) * 10 --> 20
    */
   int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Qna.RECORD_PER_PAGE;
   
    // ���� rownum, 1 ������: 1 / 2 ������: 11 / 3 ������: 21 
   int startNum = beginOfPage + 1; 
   //  ���� rownum, 1 ������: 10 / 2 ������: 20 / 3 ������: 30
   int endNum = beginOfPage + Qna.RECORD_PER_PAGE;   
   
/*    1 ������: WHERE r >= 1 AND r <= 10
    2 ������: WHERE r >= 11 AND r <= 20
    3 ������: WHERE r >= 21 AND r <= 30
    */
   hashMap.put("startNum", startNum);
   hashMap.put("endNum", endNum);
   
   List<QnaVO> list = qnaDAO.list(hashMap); 
   
   return list;
  }

  //�˻��� ���ڵ� ����
  @Override
  public int search_count(HashMap hashMap) {
    int cnt = qnaDAO.search_count(hashMap);
    return cnt;
  }
  
  //����¡
  @Override
  public String paging(int search_count, int nowPage, String col, String word) {
    int totalPage = (int)(Math.ceil((double)search_count/Qna.RECORD_PER_PAGE)); // ��ü ������  
    int totalGrp = (int)(Math.ceil((double)totalPage/Qna.PAGE_PER_BLOCK));// ��ü �׷� 
    int nowGrp = (int)(Math.ceil((double)nowPage/Qna.PAGE_PER_BLOCK));    // ���� �׷� 
    int startPage = ((nowGrp - 1) * Qna.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
    int endPage = (nowGrp * Qna.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
     
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
    int _nowPage = (nowGrp-1) * Member.PAGE_PER_BLOCK; 
    if (nowGrp >= 2){ 
      str.append("<span class='span_box_1'><A href='./list.do?col="+col+"&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
 
    for(int i=startPage; i<=endPage; i++){ 
      if (i > totalPage){ 
        break; 
      } 
  
      if (nowPage == i){ 
        str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
      }else{
        // ���� �������� �ƴ� ������
        str.append("<span class='span_box_1'><A href='./list.do?col="+col+"&word="+word+"&nowPage="+i+"'>"+i+"</A></span>");   
      } 
    } 
     
    // 10�� ���� �������� �̵� 
    //nowGrp : 1(1~10 page), nowGrp : 2(11~20 page), nowGrp : 3(21~30 page) 
    // ���� 1 �׷��� ��� : (1*10)+1 = 2 �׷��� 11
    // ���� 2 �׷��� ��� : (2*10)*+1 = 3 �׷��� 21
    _nowPage = (nowGrp * Member.PAGE_PER_BLOCK)+1; 
    if (nowGrp < totalGrp){ 
      str.append("<span class='span_box_1'><A href='./list.do?col="+col+"&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
    } 
    str.append("</DIV>"); 
     
    return str.toString(); 
  }

  //�� �� ��ȸ
  @Override
  public QnaVO read(int qnano) {
    QnaVO qnaVO = qnaDAO.read(qnano);
    return qnaVO;
  }
  
  //��ȸ�� ����
  @Override
  public int cnt(int qnano) {
    int count = qnaDAO.cnt(qnano);
    return count;
  }
  
  //���� ����
  @Override
  public int update(QnaVO qnaVO) {
    int count = qnaDAO.update(qnaVO);
    return count;
  }
  //���� ����
  @Override
  public int delete(int qnano) {
    int count = qnaDAO.delete(qnano);
    return count;
  }
  
  //������ & ȸ�� ����
  @Override
  public String act(int memberno) {
    String act = qnaDAO.act(memberno);
    return act;
  }

  //�亯 ��� ( ���� �� 1 , ���� �� 0 ��ȯ ) 
  public int reply(QnaVO qnaVO){
    int count = qnaDAO.reply(qnaVO);
    return count;
  }
  
  //ó�� ����
  @Override
  public int update_statement(int qnano){
    int count = qnaDAO.update_statement(qnano);
    return count;
  }
}
