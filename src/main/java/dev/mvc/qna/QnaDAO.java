package dev.mvc.qna;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.mvc.member.MemberVO;

@Repository("dev.mvc.qna.QnaDAO")
public class QnaDAO implements QnaDAOInter{

  @Autowired
  private SqlSessionTemplate sqlSessionTemplate = null;
  
  public QnaDAO(){
    
    if(sqlSessionTemplate != null){
      
    }
  }

  //���� ���
  @Override
  public int create(QnaVO qnaVO) {
    int count = sqlSessionTemplate.insert("qna.create", qnaVO);
    return count;
  }
  
  //QnA ��ü ��� ��ȸ
  @Override
  public List<QnaVO> list() {
    List<QnaVO> list = sqlSessionTemplate.selectList("qna.list");
    return list;
  }
  
  //QnA ��ü ��� ��ȸ + ����¡  + �˻�
  @Override
  public List<QnaVO> list(HashMap hashMap) {
    List<QnaVO> list = null;
    list = sqlSessionTemplate.selectList("qna.list2", hashMap);
    return list;
  }

  //�˻��� ���ڵ� ����
  @Override
  public int search_count(HashMap hashMap) {
    int cnt = sqlSessionTemplate.selectOne("qna.search_count", hashMap);
    return cnt;
  }

  //�� �� ��ȸ
  @Override
  public QnaVO read(int qnano) {
    QnaVO qnaVO = sqlSessionTemplate.selectOne("qna.read", qnano);
    return qnaVO;
  }

  //��ȸ�� ����
  @Override
  public int cnt(int qnano) {
    int count = sqlSessionTemplate.update("qna.cnt", qnano);
    return count;
  }

  //���� ����
  @Override
  public int update(QnaVO qnaVO) {
    int count = sqlSessionTemplate.update("qna.update", qnaVO);
    return count;
  }

  //���� ����
  @Override
  public int delete(int qnano) {
    int count = sqlSessionTemplate.delete("qna.delete", qnano);
    return count;
  }

  //������ & ȸ�� ����
  @Override
  public String act(int memberno) {
    String act = sqlSessionTemplate.selectOne("qna.act", memberno);
    return act;
  }
  
  //���� ���
  @Override
  public int reply(QnaVO qnaVO) {
    int count = sqlSessionTemplate.insert("qna.reply", qnaVO);
    return count;
  }
  
  //ó�� ����
  @Override
  public int update_statement(int qnano){
    int count = sqlSessionTemplate.update("qna.update_statement", qnano);
    return count;
  }
}
