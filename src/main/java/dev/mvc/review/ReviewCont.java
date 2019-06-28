package dev.mvc.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dev.mvc.movie.MainBoxOfficeVO;
import dev.mvc.movie.MovielikeVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReviewCont {
  
  @Autowired  
  @Qualifier("dev.mvc.review.ReviewProc")
  ReviewProcInter reviewProc = null;

  public ReviewCont() {
    //System.out.println("--> ReviewCont Created... ");
  }
  
  // ���� ��� ��
  @RequestMapping(value="/review/create.do", method=RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/review/create");
    
    return mav;
  }
  
  // ���� ��� ó��
  @RequestMapping(value="/review/create.do", method=RequestMethod.POST)
  public ModelAndView create(HttpServletRequest request, ReviewVO reviewVO) {
    ModelAndView mav = new ModelAndView();
    
    //reviewProc.create(reviewVO);
    //mav.setViewName("redirect:/review/list");
    mav.setViewName("/review/message");
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
     
    // ---------------------���� ����----------------------------------------------
    String upDir = Tool.getRealPath(request, "/review/storage");
    MultipartFile file1MF = reviewVO.getFile1MF();
    String mainimg = ""; // �÷��� ������ ���ϸ�
    long imgsize = file1MF.getSize();

    if (imgsize > 0) {
      mainimg = Upload.saveFileSpring(file1MF, upDir);
    }
    reviewVO.setMainimg(mainimg);
    reviewVO.setImgsize(imgsize);
 // ---------------------���� ���� ��----------------------------------------------

    // DBMS - ���
    if (reviewProc.create(reviewVO) == 1) {
      //msgs.add("���並 ����߽��ϴ�.");
      mav.setViewName("redirect:/review/m_list_search.do?memberno="+reviewVO.getMemberno());
      
    } else {
      msgs.add("���� ��Ͽ� �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
      
      links.add("<button type='button' onclick=\"location.href='./m_list_search.do?memberno="+reviewVO.getMemberno()+"'\">���</button>");
      
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
    }
 
    return mav;
  }
  
  
  // ���� ��� 
  @RequestMapping(value="/review/list.do", method=RequestMethod.GET)
  public ModelAndView list() {
    ModelAndView mav = new ModelAndView();
    
    // DBMS - �����ȸ
    List<ReviewVO> list = reviewProc.list();
    mav.addObject("list", list);
    
    mav.setViewName("/review/list");
    return mav;
  }
  
  // �� �� ��ȸ
  @RequestMapping(value="/review/read.do", method=RequestMethod.GET)
  public ModelAndView read(int reviewno) {
    ModelAndView mav = new ModelAndView();
    
    // DBMS - �� �� ��ȸ 
    ReviewVO reviewVO = reviewProc.read(reviewno);
    mav.addObject("reviewVO", reviewVO);
    
    //���� �� ȸ�� ���� ��������
    MnameVO mnameVO = reviewProc.mname(reviewno);
    mav.addObject("mnameVO", mnameVO);
    
    //���亰 ��� ����
    int rcc = reviewProc.rcc(reviewno);
    mav.addObject("rcc", rcc);
    
    mav.setViewName("/review/read");
    return mav;
  }
  
  // ���� ��
  @RequestMapping(value="/review/update.do", method=RequestMethod.GET)
  public ModelAndView update(int reviewno) {
    ModelAndView mav = new ModelAndView();
    
    // DBMS - �� �� ��ȸ 
    ReviewVO reviewVO = reviewProc.update(reviewno);
    mav.addObject("reviewVO", reviewVO);
    
    mav.setViewName("/review/update");
    return mav;
   }
  
  //���� ó��
  @RequestMapping(value="/review/update.do", method=RequestMethod.POST)
  public ModelAndView update(RedirectAttributes redirectAttributes,
      HttpServletRequest request, ReviewVO reviewVO, int nowPage, String word, int memberno, HttpSession session) {
    ModelAndView mav = new ModelAndView();
   //mav.setViewName("/review/message"); 
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    int reviewno = reviewVO.getReviewno();
    
    // ------------------------���� ����---------------------------------------------------
    String upDir = Tool.getRealPath(request, "/review/storage");
    /*
    <input type="file" class="form-control input-lg" name='file1MF' id='file1MF' size='40' >
    ��
     name='file1MF'�� �ش��ϴ� �ʵ带 ã�Ƽ� File ��ü�� �ڵ����� �Ҵ�
    ��
    BlogVO.java: private MultipartFile file1MF;
    ��
     ���� ��ü ���: MultipartFile file1MF = blogVO.getFile1MF();          
     */
    MultipartFile file1MF = reviewVO.getFile1MF();
    String mainimg = "";                    // DBMS file1 �÷��� ��
    long imgsize = file1MF.getSize(); // ���� ũ��
    
    // ������ ��ϵ� �� ���� �ε�
    ReviewVO reviewVO_old = reviewProc.read(reviewno);
    
    if (imgsize > 0) { // ��ϵ� ������ �ִٸ�
      Tool.deleteFile(upDir, reviewVO_old.getMainimg());    // ���� ���� ����
      mainimg = Upload.saveFileSpring(file1MF, upDir); // �ű� ���� ���ε�

    } else {
      // ������ �������� �ʴ� ��� ���� ���� ���� ���
      mainimg = reviewVO_old.getMainimg();
      imgsize = reviewVO_old.getImgsize();
    }
    reviewVO.setMainimg(mainimg);
    reviewVO.setImgsize(imgsize);
    // ------------------------���� ���� ��---------------------------------------------------
    
    // ȸ�� ���� �� session ���κ���
    //int memberno = session.getAttribute("memberno");
    reviewVO.setMemberno(memberno);
    
    
    // DBMS - ���� 
    if (reviewProc.update(reviewVO) == 1) {
      
      //msgs.add("���並 �����߽��ϴ�.");
      //links.add("<button type='button' onclick=\"location.href='./read.do?reviewno="+reviewno+"'\">���� Ȯ��</button>");
/*      redirectAttributes.addAttribute("word", word);
      redirectAttributes.addAttribute("nowPage", nowPage);*/
     mav.setViewName("redirect:/review/m_list_search.do?memberno="+reviewVO.getMemberno()+"&word="+word+"&nowPage="+nowPage);
      //mav.setViewName("redirect:/review/read.do?reviewno="+reviewVO.getReviewno()+"&word="+word+"&nowPage="+nowPage); 
    } else {
      msgs.add("���� ������ �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
      
      links.add("<button type='button' onclick=\"location.href='./m_list_search.do?memberno="+reviewVO.getMemberno()+"'\">���</button>");
      
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
    }
    return mav;
  } 
  
  //������
  @RequestMapping(value = "/review/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int reviewno) {
    //System.out.println("--> delete() GET executed!!!!!!!");
    ModelAndView mav = new ModelAndView();

    ReviewVO reviewVO = reviewProc.read(reviewno);
    mav.addObject("reviewVO", reviewVO);
    
    mav.setViewName("/review/delete"); 
    return mav;
  }
   
 //���� ó��
  @RequestMapping(value="/review/delete.do", method=RequestMethod.POST)
  public ModelAndView delete(RedirectAttributes redirectAttributes,
                                         HttpServletRequest request, int reviewno, String word, int nowPage) {
    ModelAndView mav = new ModelAndView();
   
    //mav.setViewName("/review/message"); 
    
    ArrayList<String> msgs = new ArrayList<String>();
    ArrayList<String> links = new ArrayList<String>();
    
    ReviewVO reviewVO = reviewProc.read(reviewno);
    
    // -----------------���� ����----------------------------------------------------------
    String upDir = Tool.getRealPath(request, "/review/storage");

    Tool.deleteFile(upDir, reviewVO.getMainimg());    // ���� ���� ����
      
    // ------------------���� ���� ��---------------------------------------------------------
    
   // int count = reviewProc.delete(reviewno);
    
    if ((reviewProc.delete(reviewno)) == 1) {
      
      // 4���� ���ڵ尡 �ϳ��� �������ΰ�� 5��° ���ڵ尡 �����Ǹ� ����������
      // 2���������� 1 �������� �ٿ����մϴ�. 
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put("word", word);                 
      if (reviewProc.search_count(hashMap) % Review.RECORD_PER_PAGE == 0){ 
        nowPage = nowPage - 1;
        if (nowPage < 1){ 
          nowPage = 1; 
        }
      }
 /*     redirectAttributes.addAttribute("count", count); // 1 or 0
      redirectAttributes.addAttribute("word", word);
      System.out.println("nowPage : "+nowPage);
      redirectAttributes.addAttribute("nowPage", nowPage);*/
      mav.setViewName("redirect:/review/m_list_search.do?memberno="+reviewVO.getMemberno()+"&nowPage="+nowPage+"&word="+word);

    } else { 
      msgs.add("��������� �����߽��ϴ�.");
      msgs.add("�˼������� �ٽ��ѹ� �õ����ּ���.");
      links.add("<button type='button' onclick=\"history.back()\">�ٽ� �õ�</button>");
      links.add("<button type='button' onclick=\"location.href='./m_list_search.do?memberno="+reviewVO.getMemberno()+"'\">���</button>");
      
      mav.addObject("msgs", msgs);
      mav.addObject("links", links);
    }
    return mav;  
  } 
  
  /**
   * ��� + �˻� + ����¡ ����(���θ޴�)
   * @param word
   * @param nowPage
   * @return
   */
  @RequestMapping(value = "/review/all_list_search.do", method = RequestMethod.GET)
  public ModelAndView all_list_search(
      @RequestParam(value="word", defaultValue="") String word,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage
      ) { 
    //System.out.println("--> list_search.do GET called.");
    //System.out.println("--> nowPage: " + nowPage);
    
    ModelAndView mav = new ModelAndView();
     
    // �˻� ��� �߰�
    mav.setViewName("/review/all_list_search");   
    
    // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", word);                  
    hashMap.put("nowPage", nowPage);       
    
    // �˻� ���
    List<ReviewVO> list = reviewProc.all_list_search(hashMap);
    mav.addObject("list", list);
    
    
    // �˻��� ���ڵ� ����
    int search_count = reviewProc.search_count(hashMap);
    mav.addObject("search_count", search_count);
    
    /* 
     * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
     * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
     *
     * @param categoryno ī�װ���ȣ 
     * @param search_count �˻�(��ü) ���ڵ�� 
     * @param nowPage     ���� ������
     * @param word �˻���
     * @return ����¡ ���� ���ڿ�
     */ 
    String paging = reviewProc.paging(search_count, nowPage, word);
    mav.addObject("paging", paging);
 
    mav.addObject("nowPage", nowPage);
    
    return mav;
  }
  
  /**
   * ��� + �˻� + ����¡ ����(ȸ��)
   * @param word
   * @param nowPage
   * @return
   */
  @RequestMapping(value = "/review/m_list_search.do", method = RequestMethod.GET)
  public ModelAndView m_list_search(
      @RequestParam(value="word", defaultValue="") String word,
      @RequestParam(value="nowPage", defaultValue="1") int nowPage,
      HttpSession session
      ) { 
    //System.out.println("--> list_search.do GET called.");
    //System.out.println("--> nowPage: " + nowPage);
    
    int memberno = (int) session.getAttribute("memberno");
    ModelAndView mav = new ModelAndView();
     
    // �˻� ��� �߰�
    mav.setViewName("/review/m_list_search");   
    
    // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("word", word);                  
    hashMap.put("nowPage", nowPage);       
    hashMap.put("memberno", memberno);       
    
    // �˻� ���
    List<ReviewVO> list = reviewProc.m_list_search(hashMap);
    mav.addObject("list", list);
    
    // �˻��� ���ڵ� ����
    int msearch_count = reviewProc.msearch_count(hashMap);
    mav.addObject("msearch_count", msearch_count);
    //System.out.println("nowPage : "+nowPage);

     
    /*
     * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� (ȸ��)
     * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
     *
     * @param categoryno ī�װ���ȣ 
     * @param search_count �˻�(��ü) ���ڵ�� 
     * @param nowPage     ���� ������
     * @param word �˻���
     * @return ����¡ ���� ���ڿ�
     */ 
    String paging = reviewProc.mpaging(msearch_count, nowPage, word, memberno);
    mav.addObject("paging", paging);
    mav.addObject("nowPage", nowPage);
    
    return mav;
  }
  
  
  //���� ����
  @ResponseBody
  @RequestMapping(value="/review/review_count.do", produces="application/json; charset=utf8", method=RequestMethod.GET)
  public String review_count(){
    
    JSONObject obj = new JSONObject();
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    int count = reviewProc.search_count(hashMap);
    
    obj.put("count", count);
    
    return obj.toString();
   }
}
