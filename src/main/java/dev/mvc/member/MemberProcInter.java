package dev.mvc.member;

import java.util.HashMap;
import java.util.List;


public interface MemberProcInter {
    
   // ȸ�� ���. ( ���� �� 1 , ���� �� 0 ��ȯ ) 
   public int create(MemberVO memberVO);   
   
   // �̸��� �ߺ� üũ ( �ߺ��Ǵ� �̸��� ���� ��ȯ , �ߺ� �� 1, �ߺ� �ƴ� �� 0 ��ȯ )
   public int checkId(String email);
    
   // ȸ�� ��ü ��� ���.
   public List<MemberVO> list();
   
   // ȸ�� ��ü ��� ��� + ����¡ + �˻�
   public List<MemberVO> member_list_search(HashMap hashMap);
   
   //�˻��� ���ڵ� ����
   public int search_count(HashMap hashMap); 
   
   //����¡
   public String paging(int search_count, int nowPage, String title);
   
   // ȸ�� 1�� ��ȸ.
   public MemberVO read(int memberno);
   
   // email�� ȸ�� 1�� ��ȸ.
   public MemberVO read(String email);
   
   //login_passwd üũ
   public String login(String email);
   
   //ȸ�� ���� ���� ��
   public MemberVO member_update(int memberno);
   
   //ȸ�� ���� ���� ó��
   public int member_update(MemberVO memberVO);
   
   //ȸ�� ���� ����
   public int member_delete(int memberno);
   
   //ȸ�� ���� ����
   public int member_act(MemberVO memberVO);
   
   
}