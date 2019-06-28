package dev.mvc.movie.comment;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class McommentCont {
  
  @Autowired
  @Qualifier("dev.mvc.movie.comment.McommentProc")
  McommentProcInter mcommnetProc = null;

  public McommentCont(){
  }
  
  //��� ���
  @RequestMapping(value="/mcomment/create.do", method=RequestMethod.POST, produces = "text/json;charset=UTF-8")
  @ResponseBody
  public String create(McommentVO mcommentVO) {
    
    JSONObject obj = new JSONObject();
    JSONArray msgs = new JSONArray();
    
    if(mcommnetProc.create(mcommentVO) == 1){
      msgs.put("success");
    }else{
      msgs.put("fail");
    }
   obj.put("msgs", msgs);
   
    return obj.toString();
  
  }
  
//��� ���
  @ResponseBody
  @RequestMapping(value="/mcomment/list.do", produces="application/json; charset=utf8", method=RequestMethod.GET)
  public ResponseEntity list(String movieCd, HttpServletRequest request, HttpSession session) {
    //ResponseEntity : ���� + ���� �ڵ�
    
   // System.out.println("/mcomment/list.do ȣ��");
    
    HttpHeaders responseHeaders = new HttpHeaders();
    
    List<McommentVO> list = mcommnetProc.list(movieCd);
    
    JSONArray json = new JSONArray(list);
    
    return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
  }
  
  
  //ȸ���� ��ȭ ��� ����Ʈ
  @RequestMapping(value = "/mcomment/m_mclist.do", method = RequestMethod.GET)
  public ModelAndView m_mclist(
      @RequestParam(value="word", defaultValue="") String word,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage, 
      HttpSession session
      ) { 
    
    //System.out.println("--> m_mclist.do GET called.");  
    //System.out.println("--> nowPage: " + nowPage);
    int memberno = (int) session.getAttribute("memberno");
    
    ModelAndView mav = new ModelAndView();
     
    // ���
    mav.setViewName("/mcomment/m_mclist");   
    
 // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", word);          
    hashMap.put("nowPage", nowPage);       
    hashMap.put("memberno", memberno); 
    
    //���
    List<MovieNmVO> list = mcommnetProc.m_mclist(hashMap);
    mav.addObject("list", list);
    //System.out.println(list); 
    
    // �˻��� ���ڵ� ����
    int m_mccount = mcommnetProc.m_mccount(hashMap);
    mav.addObject("m_mccount", m_mccount);
    //System.out.println("m_mccount : "+m_mccount);  
    
     
/*     * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
     * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
     *
     * @param m_mccount ��ü ���ڵ�� 
     * @param nowPage     ���� ������
     * @param word �˻���
     * @return ����¡ ���� ���ڿ�*/
      
    String paging = mcommnetProc.paging(m_mccount, nowPage, word, memberno);
    mav.addObject("paging", paging);
    mav.addObject("nowPage", nowPage);
    
    return mav;
  }
  
  //��Ͽ��� ��� ����
  @RequestMapping(value="/mcomment/delete.do", method=RequestMethod.POST)
  public ModelAndView delete(int memberno, String movieCd, int mcno){
    ModelAndView mav = new ModelAndView();
    
    //System.out.println(memberno);
   // System.out.println(movieCd);
    //System.out.println("delete.do ����");  

    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("memberno", memberno);
    hashMap.put("movieCd", movieCd);
    hashMap.put("mcno", mcno);
    
   // int count = mcommnetProc.delete(hashMap);
    //System.out.println("count : "+count); 
     
    if(mcommnetProc.delete(hashMap) ==1){      
      mav.setViewName("redirect:/mcomment/m_mclist.do?memberno="+memberno);
    }
    return mav;
  }
  
/*  //��� ���� json
  @ResponseBody
  @RequestMapping(value="/mcomment/deleteco.do", method=RequestMethod.POST, produces="application/json; charset=utf8")
  public String delete_json(int movieCd, int memberno) {

    JSONObject obj = new JSONObject();
    JSONArray msgs = new JSONArray();
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("movieCd", movieCd);
    hashMap.put("memberno", memberno);
     
    //System.out.println(hashMap.toString()); 
    
    int count = mcommnetProc.delete(hashMap); 
    //System.out.println("count : "+count);
    
    if(count == 1){
      msgs.put("success");
    }else{
      msgs.put("fail");
    }
   obj.put("msgs", msgs);
   
    return obj.toString();

  }*/
  
  //ȸ���� ��ȭ ��� �� �� ��ȸ
  @ResponseBody 
  @RequestMapping(value="/mcomment/read.do", method=RequestMethod.GET, produces="text/plain; charset=UTF-8")
  public String read(String movieCd, int memberno){
    //System.out.println("read.do ����");
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("movieCd", movieCd);
    hashMap.put("memberno", memberno);
    
    MovieNmVO movieNmVO = mcommnetProc.read(hashMap);
    
    JSONObject obj = new JSONObject();
    obj.put("grade", movieNmVO.getGrade());
    obj.put("content", movieNmVO.getContent());
    obj.put("movieNm", movieNmVO.getMovieNm());
    
    return obj.toString();
  }
  
  
  //��Ͽ��� ��� ����
  @RequestMapping(value="/mcomment/update.do", method=RequestMethod.POST)
  public ModelAndView update(String movieCd, int memberno, int grade, String content){
    ModelAndView mav = new ModelAndView();
    
 /*   System.out.println("movieCd : "+movieCd);
    System.out.println("memberno : "+memberno);
    System.out.println("grade : "+grade);
    System.out.println("content : "+content); */
    
    //System.out.println("update.do ����");
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("movieCd", movieCd);
    hashMap.put("memberno", memberno);
    hashMap.put("grade", grade);
    hashMap.put("content", content); 
    
    if(mcommnetProc.update(hashMap) ==1){      
      System.out.println("��� ������");
      mav.setViewName("redirect:/mcomment/m_mclist.do?memberno="+memberno);
    } 
    return mav; 
  }
  
  
  //�� ����
  @ResponseBody
  @RequestMapping(value="/mcomment/star_all_count.do", produces="application/json; charset=utf8", method=RequestMethod.GET)
  public String star_all_count(){
    JSONObject obj = new JSONObject();
    
    int count = mcommnetProc.star_all_count();
    obj.put("count", count);
    
    return obj.toString();
   }
}
