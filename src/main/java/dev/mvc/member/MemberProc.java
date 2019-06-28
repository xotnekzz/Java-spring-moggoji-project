package dev.mvc.member;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dev.mvc.member.MemberProc")    
public class MemberProc implements MemberProcInter{
    
    
    @Autowired
    @Qualifier("dev.mvc.member.MemberDAO")
    private MemberDAOInter memberDAO = null;
    
    public MemberProc() {
    }

    @Override // ȸ�� ���. ( ���� �� 1 , ���� �� 0 ��ȯ ) 
    public int create(MemberVO memberVO) {
        int count = memberDAO.create(memberVO);
        return count;
    }
    
    @Override // �̸��� �ߺ� üũ ( �ߺ��Ǵ� �̸��� ���� ��ȯ , �ߺ� �� 1, �ߺ� �ƴ� �� 0 ��ȯ )
    public int checkId(String email) {
      int count = memberDAO.checkId(email); 
      return count;
    }
    
    @Override   // ȸ�� ��ü�� �����.
    public List<MemberVO> list() {
      List<MemberVO> list = memberDAO.list();
        
      return list;
    }
    
    /**
     * ��� +�˻�(����)+ ����¡(���θ޴�)
     * <Xmp>
     * <select id="member_list_search" resultType="MemberVO" parameterType="HashMap">
     * </Xmp>
     * @param hashMap
     * @return
     */
    @Override
    public List<MemberVO> member_list_search(HashMap hashMap) {
      
      /* ���������� ����� ���� ���ڵ� ��ȣ ��� ���ذ�, nowPage�� 1���� ����
       1 ������: nowPage = 1, (1 - 1) * 10 --> 0 
       2 ������: nowPage = 2, (2 - 1) * 10 --> 10
       3 ������: nowPage = 3, (3 - 1) * 10 --> 20
       */
      int beginOfPage = ((Integer)hashMap.get("nowPage") - 1) * Member.RECORD_PER_PAGE;
      
       // ���� rownum, 1 ������: 1 / 2 ������: 11 / 3 ������: 21 
      int startNum = beginOfPage + 1; 
      //  ���� rownum, 1 ������: 10 / 2 ������: 20 / 3 ������: 30
      int endNum = beginOfPage + Member.RECORD_PER_PAGE;   
      
   /*    1 ������: WHERE r >= 1 AND r <= 10
       2 ������: WHERE r >= 11 AND r <= 20
       3 ������: WHERE r >= 21 AND r <= 30
       */
      hashMap.put("startNum", startNum);
      hashMap.put("endNum", endNum);
      
      List<MemberVO> list = memberDAO.member_list_search(hashMap); 
      
      return list;
    }
    
    
    @Override   // ȸ�� ��ü ����Ʈ �� �Ѹ��� ȸ�� ������ ���.
    public MemberVO read(int memberno) {
      MemberVO memberVO = memberDAO.read(memberno);
      return memberVO;
    }
    
    @Override   // ȸ�� ��ü ����Ʈ �� �Ѹ��� ȸ�� ������ ���.
    public MemberVO read(String email) {
      MemberVO memberVO = memberDAO.read(email);
      
      return memberVO;
    }
    
    @Override // login 
    public String login(String email) {
        String passwd = memberDAO.login(email);
        return passwd;
    }
    
    //ȸ�� ���� ��
    @Override
    public MemberVO member_update(int memberno){
      MemberVO memberVO = memberDAO.read(memberno);
      return memberVO;
    }
    
    //ȸ�� ���� ó��
    @Override
    public int member_update(MemberVO memberVO){
      int count = memberDAO.member_update(memberVO);
      return count;
    }
    
    //ȸ�� ���� ����
    public int member_delete(int memberno){
      int count = memberDAO.member_delete(memberno);
      return count;
    }
    
    //ȸ�� ���� ����
    public int member_act(MemberVO memberVO){
      int count = memberDAO.member_act(memberVO);
      return count;
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
      int cnt = memberDAO.search_count(hashMap);
      return cnt;
    }
    
    /** 
     * SPAN�±׸� �̿��� �ڽ� ���� ����, 1 ���������� ���� 
     * ���� ������: 11 / 22   [����] 11 12 13 14 15 16 17 18 19 20 [����] 
     *
     * @param search_count �˻�(��ü) ���ڵ�� 
     * @param nowPage     ���� ������
     * @param title �˻���
     * @return ����¡ ���� ���ڿ�
     */ 
    public String paging(int search_count, int nowPage, String word){ 
      int totalPage = (int)(Math.ceil((double)search_count/Member.RECORD_PER_PAGE)); // ��ü ������  
      int totalGrp = (int)(Math.ceil((double)totalPage/Member.PAGE_PER_BLOCK));// ��ü �׷� 
      int nowGrp = (int)(Math.ceil((double)nowPage/Member.PAGE_PER_BLOCK));    // ���� �׷� 
      int startPage = ((nowGrp - 1) * Member.PAGE_PER_BLOCK) + 1; // Ư�� �׷��� ������ ��� ����  
      int endPage = (nowGrp * Member.PAGE_PER_BLOCK);             // Ư�� �׷��� ������ ��� ����   
       
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
        str.append("<span class='span_box_1'><A href='./member_list_search.do?&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
      } 
   
      for(int i=startPage; i<=endPage; i++){ 
        if (i > totalPage){ 
          break; 
        } 
    
        if (nowPage == i){ 
          str.append("<span class='span_box_2'>"+i+"</span>"); // ���� ������, ���� 
        }else{
          // ���� �������� �ƴ� ������
          str.append("<span class='span_box_1'><A href='./member_list_search.do?word="+word+"&nowPage="+i+"'>"+i+"</A></span>");   
        } 
      } 
       
   // 10�� ���� �������� �̵� 
      //nowGrp : 1(1~10 page), nowGrp : 2(11~20 page), nowGrp : 3(21~30 page) 
      // ���� 1 �׷��� ��� : (1*10)+1 = 2 �׷��� 11
      // ���� 2 �׷��� ��� : (2*10)*+1 = 3 �׷��� 21
      _nowPage = (nowGrp * Member.PAGE_PER_BLOCK)+1; 
      if (nowGrp < totalGrp){ 
        str.append("<span class='span_box_1'><A href='./member_list_search.do?&word="+word+"&nowPage="+_nowPage+"'>����</A></span>"); 
      } 
      str.append("</DIV>"); 
       
      return str.toString(); 
    }
}
