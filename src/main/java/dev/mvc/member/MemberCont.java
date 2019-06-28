package dev.mvc.member;
 
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class MemberCont {
    @Autowired

    @Qualifier("dev.mvc.member.MemberProc") // �Ҵ�Ǵ� Class ��ü�� �̸�.
    private MemberProcInter memberProc;
    
    public MemberCont() {
        //System.out.println("MemberController ����Ǿ���.");
    }
    
    // ȸ������ ������ ȣ��.
    @RequestMapping(value = "/member/create.do", method = RequestMethod.GET)
    public ModelAndView create() {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/member/member_create"); // /webapp/member/member_create.jsp

      return mav;
    }
    
    
    // ȸ������ ���������� email�ߺ� Ȯ���� Ŭ���Ͽ����� AJAX������� email ��ȸ.
    @ResponseBody
    @RequestMapping(value = "/member/checkId.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String checkId(String memail) {
      
      
      JSONObject obj = new JSONObject();
      
      int cnt = memberProc.checkId(memail);
      obj.put("cnt", cnt);

      return obj.toJSONString();
    }
    
    // ȸ������ ������ -> DB�Է�.
    @RequestMapping(value="/member/create.do", method=RequestMethod.POST)
    public ModelAndView create(RedirectAttributes redirectAttributes,
                          HttpServletRequest request, MemberVO memberVO){
      ModelAndView mav = new ModelAndView();
      
      int count = memberProc.checkId(memberVO.getMemail());
      
      if (count == 1) { // ID �ߺ��� �޽��� ���
        redirectAttributes.addAttribute("email", memberVO.getMemail());
        // ex) 'james9957@naver.com �� �ߺ��� �̸����Դϴ�.' �� view�ʿ��� �޾Ƽ� ���.
         
      } else {
        
        // -------------------------------------------------------------------
        // ���� ���� �ڵ� ����
        // -------------------------------------------------------------------
        
        String upDir = Tool.getRealPath(request, "/member/storage"); //��μ���
        
        MultipartFile fileMF = memberVO.getFileMF(); // jsp���� name�̶� ���� �ؾ���.
        String file = ""; // �÷��� ������ ���ϸ�
        long imgsize = fileMF.getSize();

        if (imgsize > 0) { // ������ �ִٸ�
          file = Upload.saveFileSpring(fileMF, upDir); 
        }
        memberVO.setMfile(file);
        memberVO.setMsize(imgsize);    
        
        // -------------------------------------------------------------------
        // ���� ���� �ڵ� ����
        // -------------------------------------------------------------------

        // DBMS - ���
        
        count = memberProc.create(memberVO); // ���

        redirectAttributes.addAttribute("sw", "create");    // �ߺ��� ���� ������ ȸ������ ����/������ �����ϱ� ���� ����.
        redirectAttributes.addAttribute("count", count); // 0 �Ǵ� 1 ��ȯ���� 0�� ����/ 1�� ����.
      }
      System.out.println("�����");
      mav.setViewName("redirect:/index.jsp");
     
      return mav;
    }
    
   /* // (����) session���� ���Ͽ� �α��� �������� Ȯ�� �� ���.
    // (���� ����) Tool.java���� session ���� �̿��Ͽ� session id���� ������ ���� Ȯ�� �� ���.
    @RequestMapping(value="/member/member_list.do", method=RequestMethod.GET)
    public ModelAndView member_list(){
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/member/member_list"); // webapp/member/list.jsp
      
      List<MemberVO> list = memberProc.list();
      mav.addObject("list", list);
   
      return mav;
    }   */   
    
    
    /**
     * ��� + �˻� + ����¡ ����(���θ޴�)
     * @param word
     * @param nowPage
     * @return
     */
    @RequestMapping(value = "/member/member_list_search.do", method = RequestMethod.GET)
    public ModelAndView member_list_search(
        @RequestParam(value="col", defaultValue="") String col,
        @RequestParam(value="word", defaultValue="") String word,
        @RequestParam(value="nowPage", defaultValue="1") int nowPage) { 
      
      ModelAndView mav = new ModelAndView();
       
      // �˻� ��� �߰�
      mav.setViewName("/member/member_list"); 
      
      // ���ڿ� ���ڿ� Ÿ���� �����ؾ������� Obejct ���
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put("col", col);                  
      hashMap.put("word", word);                  
      hashMap.put("nowPage", nowPage);       
      
      // �˻� ���
      List<MemberVO> list = memberProc.member_list_search(hashMap);
      mav.addObject("list", list);
      
      // �˻��� ���ڵ� ����
      int search_count = memberProc.search_count(hashMap);
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
        
      String paging = memberProc.paging(search_count, nowPage, word);
      mav.addObject("paging", paging);
   
      mav.addObject("nowPage", nowPage);
      
      return mav;
    }
    
    // (����)ȸ�� ��� -> ȸ�� ��ȸ�� ��. ��� �������� ���ϴ� ������ list�� �� �ٸ� ���� ����.
    // (���� ����) ����, ���� ����� �߰��Ͽ� �����ڰ� ���� ������ ���� ��� �� �� �ֵ��� ����.
    @RequestMapping(value="/member/read.do", method=RequestMethod.GET)
    public ModelAndView read(int memberno){
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/member/read"); // webapp/member/read.jsp
      
      MemberVO memberVO = memberProc.read(memberno);
      mav.addObject("memberVO", memberVO);
      
      return mav;
    }  
    
    // ��й�ȣ ���� �������� �̵�.
    @RequestMapping(value="/member/passwd_update.do", method=RequestMethod.GET)
    public ModelAndView passwd_update(){
      
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/member/passwd_update"); // webapp/member/passwd_update.jsp
      
      return mav;
    }
    
    // �α��� �������� �̵�.
    @RequestMapping(value="/member/login_form.do", method=RequestMethod.GET)
    public ModelAndView login(){
      
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/member/login_form"); // webapp/member/passwd_update.jsp
      
      return mav;
    }
    
    //�α��� ó��
    @RequestMapping(value="/member/login_check.do", method=RequestMethod.POST)
    public ModelAndView login(RedirectAttributes redirectAttributes, HttpSession session, String email, String passwd, HttpServletRequest request){
     
      ModelAndView mav = new ModelAndView();
      
      int count = memberProc.checkId(email);
      
      if(count == 1){ //�α��� ����
        if(memberProc.login(email).equals(passwd)){ // �н����� üũ
          
          MemberVO memberVO = memberProc.read(email);
          
          String mname = memberVO.getMname();
          int memberno = memberVO.getMemberno();
          String mact = memberVO.getMact();
          
          /*���ǿ� ����*/
          session.setAttribute("memberno", memberno);
          session.setAttribute("email", email);
          session.setAttribute("passwd", passwd);
          session.setAttribute("mname", mname);
          session.setAttribute("mact", mact);
          
          session.setMaxInactiveInterval(500*60);
          mav.setViewName("redirect:/");
          
        }else{ //�α��� ����
          count = 0;
          mav.setViewName("redirect:./login_form.do");
        }
      }else{
        count = 0;
        mav.setViewName("redirect:./login_form.do");
      }
      mav.addObject("count", count); 
      return mav;
      
    }
    
  //�α׾ƿ�
    @RequestMapping(value="/member/logout.do", method=RequestMethod.GET)
    public ModelAndView logout(HttpSession session){
     
      ModelAndView mav = new ModelAndView();
      session.invalidate();
      mav.setViewName("redirect:/index.jsp");
      return mav;
    }
    
    //������ ȸ�� ���� ��
    @RequestMapping(value="/member/member_update.do", method = RequestMethod.GET)
    public ModelAndView update(int memberno) {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/member/member_update"); 
      
      MemberVO memberVO = memberProc.member_update(memberno);
      mav.addObject("memberVO", memberVO);

      return mav;
    }
    
    
    //ȸ�� ���� ó��
    @RequestMapping(value="/member/member_update.do", method=RequestMethod.POST)
    public ModelAndView update(RedirectAttributes redirectAttributes,
                          HttpServletRequest request, MemberVO memberVO, String col, String word, int nowPage){
      ModelAndView mav = new ModelAndView();
      
      
       // System.out.println("������������");
        // -------------------------------------------------------------------
        // ���� ���� �ڵ� ����
        // -------------------------------------------------------------------
        
        String upDir = Tool.getRealPath(request, "/member/storage"); //��μ���
        
        MultipartFile fileMF = memberVO.getFileMF(); // jsp���� name�̶� ���� �ؾ���.
        String file = ""; // �÷��� ������ ���ϸ�
        long imgsize = fileMF.getSize();

        //���� ��� ���� �о��.
        MemberVO memberVO_old = memberProc.read(memberVO.getMemberno());
        
        if (imgsize > 0) { // ������ ������ �ִٸ�
          
          Tool.deleteFile(memberVO_old.getMfile()); // �������� ����
          Tool.deleteFile(upDir, memberVO_old.getMfile());
          
          file = Upload.saveFileSpring(fileMF, upDir); //�ű� ���ε�
        }else{
          file = memberVO_old.getMfile();
          imgsize = memberVO_old.getMsize();
        }
        memberVO.setMfile(file);
        memberVO.setMsize(imgsize);    
        
        // -------------------------------------------------------------------
        // ���� ���� �ڵ� ����
        // -------------------------------------------------------------------

        // DBMS - ���
        int count = memberProc.member_update(memberVO); // ����

        redirectAttributes.addAttribute("sw", "update");    // �ߺ��� ���� ������ ȸ������ ����/������ �����ϱ� ���� ����.
        redirectAttributes.addAttribute("count", count); // 0 �Ǵ� 1 ��ȯ���� 0�� ����/ 1�� ����.
      
      System.out.println("�����");
      mav.setViewName("redirect:/member/member_list_search.do?col="+col+"&word="+word+"&nowPage="+nowPage);
     
      return mav;
    }
    
    //ȸ�� ���� ��(����������)
    @RequestMapping(value="/member/mupdate.do", method = RequestMethod.GET)
    public ModelAndView mupdate(HttpSession session) {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/member/myinfo"); 
      
      int memberno = (int) session.getAttribute("memberno"); 
      
      MemberVO memberVO = memberProc.member_update(memberno);
      mav.addObject("memberVO", memberVO);

      return mav;
    }
    
  //ȸ�� ���� ó��(����������)
    @RequestMapping(value="/member/mupdate.do", method=RequestMethod.POST)
    public ModelAndView mupdate(RedirectAttributes redirectAttributes,
                          HttpServletRequest request, MemberVO memberVO){
      ModelAndView mav = new ModelAndView();
      
      
       // System.out.println("������������");
        // -------------------------------------------------------------------
        // ���� ���� �ڵ� ����
        // -------------------------------------------------------------------
        
        String upDir = Tool.getRealPath(request, "/member/storage"); //��μ���
        
        MultipartFile fileMF = memberVO.getFileMF(); // jsp���� name�̶� ���� �ؾ���.
        String mfile = ""; // �÷��� ������ ���ϸ�
        long imgsize = fileMF.getSize();

        //���� ��� ���� �о��.
        MemberVO memberVO_old = memberProc.read(memberVO.getMemberno());
        
        if (imgsize > 0) { // ������ ������ �ִٸ�
          
          Tool.deleteFile(memberVO_old.getMfile()); // �������� ����
          Tool.deleteFile(upDir, memberVO_old.getMfile());
          
          mfile = Upload.saveFileSpring(fileMF, upDir); //�ű� ���ε�
          
        }else{ //������ �������� �ʴ� ���
          mfile = memberVO_old.getMfile();
          imgsize = memberVO_old.getMsize();
        }
        memberVO.setMfile(mfile);
        memberVO.setMsize(imgsize);    
        
        // -------------------------------------------------------------------
        // ���� ���� �ڵ� ����
        // -------------------------------------------------------------------

        // DBMS - ���
        memberProc.member_update(memberVO); // ����
        mav.setViewName("redirect:/member/mupdate.do");
     
      return mav;
    }
    
    
    //ȸ�� ���� ���� ��
    @RequestMapping(value = "/member/member_delete.do", method = RequestMethod.GET)
    public ModelAndView delete(int memberno) {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/member/delete"); // /webapp/member/delete.jsp

      MemberVO memberVO = memberProc.read(memberno);
      mav.addObject("memberVO", memberVO);
      
      return mav;
    }
    
    
    //ȸ�� ���� ���� ó��
    @RequestMapping(value="/member/member_delete.do", method=RequestMethod.POST)
    public ModelAndView delete(HttpServletRequest request, int memberno){
      ModelAndView mav = new ModelAndView();
      
      MemberVO memberVO = memberProc.read(memberno);
      
      // -----------------���� ����----------------------------------------------------------
      String upDir = Tool.getRealPath(request, "/member/storage");

      Tool.deleteFile(upDir, memberVO.getMfile());    // ���� ���� ����
        
      // ------------------���� ���� ��---------------------------------------------------------
      
      int count = memberProc.member_delete(memberno);
      System.out.println("count : " + count);
      
      mav.setViewName("redirect:/member/member_list_search.do");
      
      return mav;
    }
    
    //ȸ�� ���� ��
    @RequestMapping(value = "/member/act_form.do", method = RequestMethod.GET)
    public ModelAndView act(int memberno) {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("/member/act_form"); // /webapp/member/act_form.jsp

      MemberVO memberVO = memberProc.read(memberno);
      mav.addObject("memberVO", memberVO);
      
      return mav;
    }
    
  //ȸ�� ���� ó��
    @RequestMapping(value="/member/act_proc.do", method=RequestMethod.POST)
    public ModelAndView act(RedirectAttributes redirectAttributes,
                          HttpServletRequest request, MemberVO memberVO, String mact){
      ModelAndView mav = new ModelAndView();
      
        // DBMS - ���
        int count = memberProc.member_act(memberVO); // ����

        redirectAttributes.addAttribute("sw", "update");    // �ߺ��� ���� ������ ȸ������ ����/������ �����ϱ� ���� ����.
        redirectAttributes.addAttribute("count", count); // 0 �Ǵ� 1 ��ȯ���� 0�� ����/ 1�� ����.
      
      System.out.println("�����");
      mav.setViewName("redirect:/member/act_proc.jsp");
     
      return mav;
    }
    
    // //��й�ȣ ã��/ email�ߺ� Ȯ���� Ŭ���Ͽ����� AJAX������� email ��ȸ.
    @ResponseBody
    @RequestMapping(value = "/member/forgot_password.do", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String forgot_password(String memail){
      
      JSONObject obj = new JSONObject();
      
      int cnt = memberProc.checkId(memail);
      
      if(cnt == 1){
        
        /*���� �߼� ����*/
        class MyAuthentication extends Authenticator {
          PasswordAuthentication pa; 
          public MyAuthentication(){ 
            pa = new PasswordAuthentication("test@nulunggi.pe.kr", "test2016");
          } 
         
          public PasswordAuthentication getPasswordAuthentication() {
            return pa;
          }
        }
         
        String subject = "[moggoji] ȸ������ ��й�ȣ �����Դϴ�.";  // ����
        
        MemberVO memberVO = memberProc.read(memail);
        
        String content = "��й�ȣ : ";  // ����
        content += memberVO.getMpasswd()+"<br><br>";
         
         
        // mw-002.cafe24.com, Cafe24
        String host = "mw-002.cafe24.com";    // smtp mail server(����������)     
        String from = "moggoji@gmail.com";   // ������ �ּ�, ��α� ������ �ּ� 
        String to = memail;    // �޴� ���
         
        Properties props = new Properties();  // SMTP �������� ���, port 25
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth","true");
         
        Authenticator authenticator = new MyAuthentication();
        Session sess = Session.getInstance(props, authenticator);   // ���� ���� �˻�
         
        try { 
          Message msg = new MimeMessage(sess);   // ���� ���� ��ü ����
          msg.setFrom(new InternetAddress(from));   // ������ ��� ����
                 
          // �Ѹ��Ը� ���� 
          InternetAddress[] address = {new InternetAddress(to)}; // �޴� ��� ����
          
          msg.setRecipients(Message.RecipientType.TO, address); // ������ �ּ� ����
                
          msg.setSubject(subject);                  // ���� ���� 
          msg.setSentDate(new Date());          // ���� ��¥ ����
                
         
          // ������ �������� HTML �������� ���� ���
          msg.setContent(content, "text/html;charset=utf-8");
                
          Transport.send(msg);  // ���� ����
         
          
        } catch (MessagingException mex) { 
          System.out.println(mex.getMessage());
          // out.println(mex.getMessage()+"<br><br>");
          // out.println(to + "�Կ��� ���� �߼��� ���� �߽��ϴ�.");
        }
        /*���� �߼� ��*/
      }
      
      obj.put("cnt", cnt);

      return obj.toJSONString();
    }

}