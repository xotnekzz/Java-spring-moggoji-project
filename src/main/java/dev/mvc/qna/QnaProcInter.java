package dev.mvc.qna;

import java.util.HashMap;
import java.util.List;

public interface QnaProcInter {

  //���� ��� ( ���� �� 1 , ���� �� 0 ��ȯ ) 
  public int create(QnaVO qnaVO);
  
  //QnA ��ü ��� ��ȸ
  public List<QnaVO> list();
  
  //QnA ��ü ��� ��ȸ + ����¡  + �˻�
  public List<QnaVO> list(HashMap hashMap);
  
  //�˻��� ���ڵ� ����
  public int search_count(HashMap hashMap); 
  
  //����¡
  public String paging(int search_count, int nowPage,String col, String word);
  
  //�� �� ��ȸ
  public QnaVO read(int qnano);
  
  //��ȸ�� ����
  public int cnt(int qnano);
  
  //���� ����
  public int update(QnaVO qnaVO);
  
  //���� ����
  public int delete(int qnano);
  
  //������ & ȸ�� ����
  public String act(int memberno);
  
  //�亯 ��� ( ���� �� 1 , ���� �� 0 ��ȯ ) 
  public int reply(QnaVO qnaVO);
  
  //ó�� ����
  public int update_statement(int qnano);
}
