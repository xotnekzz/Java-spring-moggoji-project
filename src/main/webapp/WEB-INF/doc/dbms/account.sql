�� /WEB-INF/doc/dbms/account.sql
-------------------------------------------------------------------------------------
1. ��ϵ� ����� ���
    SELECT username, user_id  
    FROM dba_users 
    ORDER BY username ASC;
 
       USERNAME                                USER_ID
       --------------------------------------- ----------
       OUTLN                                        9
       SOLDESK                                     48
       SYS                                             0
       SYSTEM                                       5
       XDB                                            34
       XS$NULL                                     2147483638
 
 
2. SOLDESK������ �⺻������ �����ϳ� �����ϸ� SOLDESK + IP�� �����Ͽ�
   ������ ����ϴٴ�. 1234�� �н������Դϴ�.
   ��) �Ʒñ���: SOLDESK
        �Ʒû�: SOLDESK1, SOLDESK2, SOLDESK3...
 
   CREATE USER mv IDENTIFIED BY 1234;
 
 
3. ���� �ο�
    GRANT CONNECT, RESOURCE to mv;
    GRANT CREATE VIEW TO mv;
 
 
4. ����� ���� 
   DROP USER mv CASCADE; 
 
 
 
[����] ���� ���� ����������� ����� ����
        �Ϲ� ������ �������� Ǫ�� ���: system���� �α����Ͽ� ����  
        ALTER USER kor1 ACCOUNT UNLOCK;  
 
 
 
[����] �������� system ������ Lock ��� ���
         - 'Run SQL Command Line' ����
        SQL> connect
        Enter user-name: sys as SYSDBA
        Enter password: 1234 or oracle
        Connected.
 
        SQL> ALTER USER system ACCOUNT UNLOCK;
        User altered.
 
 
-------------------------------------------------------------------------------------`