package dev.mvc.qna;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class QnaCont {
  
  @Autowired
  @Qualifier("dev.mvc.qna.QnaProc")
  QnaProcInter qnaProc = null;
  
  public QnaCont(){
    
  }

  //QnA ��� ��
  @RequestMapping(value="/qna/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/create");
    return mav;
  }
  
  //QnA ��� ó��
  @RequestMapping(value="/qna/create.do", method=RequestMethod.POST)
  public ModelAndView create(RedirectAttributes redirectAttributes,
                        HttpServletRequest request, QnaVO qnaVO){
    ModelAndView mav = new ModelAndView();
   
    //���������� ȸ������ ����
    
    String act = qnaProc.act(qnaVO.getMemberno());
    
    if(act.equals("M")){
      qnaVO.setSeqno(2);
    }else{
      qnaVO.setSeqno(1);
    }
    
    //�� ����̴� �亯 ����
    qnaVO.setAnsnum(0);
    
    qnaProc.create(qnaVO); // ���

    mav.setViewName("redirect:/qna/list.do");
   
    return mav;
  }
  
  //QnA ��� 
  @RequestMapping(value="/qna/list.do", method=RequestMethod.GET)
  public ModelAndView list(
      @RequestParam(value="col", defaultValue="") String col,
      @RequestParam(value="word", defaultValue="") String word,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage) {
   ModelAndView mav = new ModelAndView();
   
   // �˻� ��� �߰�
   mav.setViewName("/qna/list"); 
   
   // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
   HashMap<String, Object> hashMap = new HashMap<String, Object>();
   hashMap.put("col", col);                  
   hashMap.put("word", word);                  
   hashMap.put("nowPage", nowPage);    
   
   // DBMS - �����ȸ
   List<QnaVO> list = qnaProc.list(hashMap);
   mav.addObject("list", list);
   
   // �˻��� ���ڵ� ����
   int search_count = qnaProc.search_count(hashMap);
   mav.addObject("search_count", search_count);
   
   /*
    * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
    * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
    *
    * @param categoryno ī�װ���ȣ 
    * @param search_count �˻�(��ü) ���ڵ�� 
    * @param nowPage     ���� ������
    * @param title �˻���
    * @return ����¡ ���� ���ڿ�*/
     
   String paging = qnaProc.paging(search_count, nowPage, col, word);
   mav.addObject("paging", paging);

   mav.addObject("nowPage", nowPage);
   return mav;
 }
  
  //�� �� ��ȸ
  @RequestMapping(value="/qna/read.do", method=RequestMethod.GET)
  public ModelAndView read(int qnano) {
    ModelAndView mav = new ModelAndView();
    
    // ��ȸ�� ����
    qnaProc.cnt(qnano);
    
    // DBMS - �� �� ��ȸ 
    QnaVO qnaVO = qnaProc.read(qnano);
    mav.addObject("qnaVO", qnaVO);
    
    
    mav.setViewName("/qna/read");
    return mav;
  }
  
  //���� ���� ��
  @RequestMapping(value="/qna/update.do", method=RequestMethod.GET)
  public ModelAndView update(int qnano){
    ModelAndView mav = new ModelAndView();
    
    // DBMS - �� �� ��ȸ 
    QnaVO qnaVO = qnaProc.read(qnano);
    mav.addObject("qnaVO", qnaVO);
    
    mav.setViewName("/qna/update");
    
    return mav;
  }
  
  //���� ���� ó��
  @RequestMapping(value="/qna/update.do", method=RequestMethod.POST)
  public ModelAndView update(QnaVO qnaVO){
    ModelAndView mav = new ModelAndView();
    
    //���� ���� �н����� Ÿ��
    
    qnaProc.update(qnaVO); // ����

    mav.setViewName("redirect:/qna/list.do");
    return mav;
  }
  
  //���� ���� ��
  @RequestMapping(value="/qna/delete.do", method=RequestMethod.GET)
  public ModelAndView delete(int qnano){
    ModelAndView mav = new ModelAndView();
    
    // DBMS - �� �� ��ȸ 
    QnaVO qnaVO = qnaProc.read(qnano);
    mav.addObject("qnaVO", qnaVO);
    
    mav.setViewName("/qna/delete");
    return mav;
  }
  
  //���� ���� ó��
  @RequestMapping(value="/qna/delete.do", method=RequestMethod.POST)
  public ModelAndView delete_1(int qnano, String qpasswd){
    ModelAndView mav = new ModelAndView();
    
    QnaVO qnaVO = qnaProc.read(qnano);
    
    if(qnaVO.getQpasswd().equals(qpasswd)){
      qnaProc.delete(qnano);
    } 
    mav.setViewName("redirect:/qna/list.do");
    return mav;
  }
  
  
//������) ���� ���� ó��
  @RequestMapping(value="/qna/admin_delete.do", method=RequestMethod.POST)
  public ModelAndView admin_delete_1(String mact, int qnano){
    ModelAndView mav = new ModelAndView();
    
    qnaProc.delete(qnano);
    
    mav.setViewName("redirect:/qna/list.do");
    return mav;
  }
  
//QnA ��� ��
  @RequestMapping(value="/qna/reply.do", method=RequestMethod.GET)
  public ModelAndView reply() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/qna/reply");
    return mav;
  }
  
  //QnA ��� ó��
  @RequestMapping(value="/qna/reply.do", method=RequestMethod.POST)
  public ModelAndView reply(RedirectAttributes redirectAttributes,
                        HttpServletRequest request, QnaVO qnaVO, int qnano){
    ModelAndView mav = new ModelAndView();
   
    
    //�亯 ����̴�
    qnaVO.setQnatype("���");
    qnaVO.setIndent(qnano);
    qnaVO.setAnsnum(1);
    qnaVO.setSeqno(1);
    
    qnaProc.reply(qnaVO); // ���
    
    
    //statement ����
    qnaProc.update_statement(qnano);
    

    mav.setViewName("redirect:/qna/list.do");
    return mav;
  }
}