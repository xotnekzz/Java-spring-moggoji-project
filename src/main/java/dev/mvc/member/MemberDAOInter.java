package dev.mvc.member;

import java.util.HashMap;
import java.util.List;

import dev.mvc.review.ReviewVO;

public interface MemberDAOInter {
   
    // ȸ�� ���. ( ���� �� 1, ���� �� 0 ��ȯ )
   public int create(MemberVO memberVO);   
   
   
   // �̸��� �ߺ� üũ ( �ߺ��Ǵ� �̸��� ���� ��ȯ , �ߺ� �� 1, �ߺ� �ƴ� �� 0 ��ȯ )
   public int checkId(String email);
   
   // ȸ�� ��ü ��� ���.
   public List<MemberVO> list();
   
   // ȸ�� ��ü ��� ��� + �˻� + ����¡
   public List<MemberVO> member_list_search(HashMap hashMap);

   //�˻��� ���ڵ� ����
   public int search_count(HashMap hashMap); 
   
   // ȸ�� 1�� ��ȸ.
   public MemberVO read(int memberno);
   
   // ȸ�� 1�� ��ȸ.
   public MemberVO read(String email);
   
   //login
   public String login(String email);
   
   //ȸ�� ���� ����
   public int member_update(MemberVO memberVO);
   
   //ȸ�� ���� ����
   public int member_delete(int memberno);
   
   //ȸ�� ���� ����
   public int member_act(MemberVO memberVO);
}
