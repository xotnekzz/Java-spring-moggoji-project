/**********************************/
/* Table Name: ��ȭ���� */
/**********************************/

1. ���̺� ����
CREATE TABLE review(
    reviewno INT NOT NULL IDENTITY,
    title VARCHAR2(30) NOT NULL,
    content CLOB NOT NULL,
    rdate DATETIME NOT NULL,
    main_image VARCHAR2(20),
    size VARCHAR2(20),
    thumb_image VARCHAR(20),
    youtube VARCHAR2(500),
    cnt INT NOT NULL,
    visible CHAR(1) DEFAULT Y,
    review_rating INT, 
    member INT,
  PRIMARY KEY (reviewno),
  FOREIGN KEY (reviewno) REFERENCES member(memberno)
);  
 
2) ���̺� ����
DROP TABLE review;
 
3) ���
INSERT INTO review(reviewno, title, content, rdate, main_image, youtube, cnt, visible, review_rating)
VALUES((SELECT NVL(MAX(reviewno), 0)+1 as reviewno FROM review),
             '���� �ı�', '������̶� ���� ��վ���', sysdate, '�̹���.jpg', '��Ʃ����' , 0, 'Y', 3);

4) ���
SELECT reviewno, title, rdate, main_image, cnt, visible, review_rating
FROM review
ORDER BY reviewno ASC;
           
5) 1���� �� ����(R:Read, PK ���) 
SELECT reviewno, title, content, rdate, main_image, youtube, cnt, visible, review_rating
FROM review
WHERE reviewno = 1;

6) ��ȸ�� ���� 
UPDATE review
SET cnt = cnt + 1
WHERE reviewno=2;

7) �� ����(U:Update), PK�� ���ڵ带 �����ϴ� ���� ����� ������ �������� 
   �����ϴ� ���� �������� ����.
UPDATE review
SET title='���� 2�� �� �ı�', content='�ι����� �ʹ���Դ���', rdate=sysdate
WHERE reviewno=1;

8) �� ����(D:Delete) 
DELETE FROM review
WHERE reviewno = 2;
 

 
7_1) �۸� ���� 
UPDATE review
SET title = '��α� �����̶�?', content= 'jsp�� �����  ��αװ���', rdate=sysdate
WHERE reviewno=1;
  
7_2) Visible ����
UPDATE review
SET visible = 'N'
WHERE reviewno=1;
 
7_3) ��Ʃ�� ����
UPDATE review
SET youtube = ''
WHERE reviewno=2;
 
7_4) ���ο� ��Ʃ�� ���
UPDATE review
SET youtube=''
WHERE reviewno=2;
 
7_5) ���� ��ü
UPDATE review
SET file='new.jpg', fstor1='new.jpg', thumb='new_m.jpg', size1=123
WHERE reviewno=2;
  
 
8) �� ����(D:Delete) 
DELETE FROM review
WHERE reviewno = 2;
 
 
8. �˻� �� ���(S:Search List) 
    - ����� ���۽� �˻��� ������� �����ϸ� ��ü ������
      ��ü �˻����� �����ϴ�.
    - rname, title, content �÷� ���
 
8_1) ����: 
    - WHERE rname LIKE '�մ���'
       rname �÷��� ���� '�մ���'�� ���ڵ� ���� ���
 
    - WHERE rname LIKE '%�մ���%'
      rname �÷��� ���� '�մ���'�� �� ���ڵ� ���� ���
 
    - WHERE rname LIKE '�մ���%'
      rname �÷��� ���� '�մ���'�� �����ϴ� ���ڵ� ���� ���
 
    - WHERE rname LIKE '%�մ���'
      rname �÷��� ���� '�մ���'�� �����ϴ� ���ڵ� ���� ���
   
 
8_2) �˻��� ���� �ʴ� ��� ��� ���ڵ� ��� 
SELECT devcateno, rname, email, title_main, title_sub, content, passwd, cnt, 
          SUBSTRING(rdate, 1, 10) as rdate, file, fstor1, thumb,
          size1, visible
FROM review
ORDER BY reviewno DESC;
 
8_3) �̸� �˻�
SELECT devcateno, rname, email, title_main, title_sub, content, passwd, cnt, 
          SUBSTRING(rdate, 1, 10) as rdate, file, fstor1, thumb,
          size1, visible
FROM review
WHERE rname LIKE '%�Ʒι�%'
ORDER BY reviewno DESC;
     
8_4) ���� �������� �˻�   
SELECT devcateno, rname, email, title_main, title_sub, content, passwd, cnt, 
          SUBSTRING(rdate, 1, 10) as rdate, file, fstor1, thumb,
          size1, visible
FROM review
WHERE title_main LIKE '%�н�����%'
ORDER BY reviewno DESC;

8_5) ���� �������� �˻�   
SELECT devcateno, rname, email, title_main, title_sub, content, passwd, cnt, 
          SUBSTRING(rdate, 1, 10) as rdate, file, fstor1, thumb,
          size1, visible
FROM review
WHERE title_sub LIKE '%�н�����%'
ORDER BY reviewno DESC;
    
 
9 ����¡
   - ����� ����¡ ������ �ʼ��� �մϴ�.
   
9_1) �˻��� ��ü ���ڵ� ��
SELECT COUNT(reviewno) as cnt 
FROM review
WHERE rname LIKE '%�մ���%';
 
 cnt
 ---
   1
   
9_2) ����¡
SELECT devcateno, rname, email, title_main, title_sub, content, passwd, cnt, SUBSTRING(rdate, 1, 10) as rdate, file, fstor1, thumb, size1, visible 
FROM review
WHERE rname LIKE '%�մ���%'
ORDER BY reviewno DESC
LIMIT 0, 5;
 
 pdsno rname email          title content passwd cnt rdate                 web                 file1     size1  thumb       visible
 ----- ----- -------------- ----- ------- ------ --- --------------------- ------------------- --------- ------ ----------- -------
     1 �մ���   mail1@mail.com ��     �� ����    123      2 2016-04-07 12:09:17.0 http://www.daum.net fun04.jpg 106346 fun04_t.jpg Y
 
     