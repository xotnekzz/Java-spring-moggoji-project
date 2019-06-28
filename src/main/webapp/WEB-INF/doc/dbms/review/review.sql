1) ���̺� ����

DROP TABLE review cascade constraints;
/**********************************/
/* Table Name: ��ȭ���� */
/**********************************/
CREATE TABLE review(
    reviewno                          NUMBER    	 NOT NULL,
    title                             VARCHAR(30)    NOT NULL,
    content                           CLOB    		 NOT NULL,
    rdate                             DATE     		 NOT NULL,
    mainimg                        	  VARCHAR(300)    NULL,
    imgsize                           NUMBER(8)  	 DEFAULT 0   NULL,
    youtube                           VARCHAR(500)   NULL,    
    cnt                               NUMBER 		 DEFAULT 0   NOT NULL,
    visible                           CHAR(1)    	 DEFAULT 'Y'  NULL,
    review_rating                     NUMBER    	 NULL,
    memberno                          NUMBER   		 NOT NULL,
    mname                             VARCHAR2(20)   NOT NULL,
    PRIMARY KEY (reviewno),
    FOREIGN KEY (memberno) REFERENCES member(memberno)
);

alter table review MODIFY(mainimg VARCHAR(300));

COMMENT ON TABLE review is '����';
COMMENT ON COLUMN review.reviewno is '���� ��ȣ';
COMMENT ON COLUMN review.title is '���� ����';
COMMENT ON COLUMN review.content is '���� ����';
COMMENT ON COLUMN review.rdate is '���� �����';
COMMENT ON COLUMN review.mainimg is '��ǥ����';
COMMENT ON COLUMN review.youtube is '��Ʃ�� ����';
COMMENT ON COLUMN review.cnt is '��ȸ��';
COMMENT ON COLUMN review.visible is '��¿���';
COMMENT ON COLUMN review.review_rating is '���� ����';
COMMENT ON COLUMN review.memberno is 'ȸ�� ��ȣ';
COMMENT ON COLUMN review.mname is 'ȸ�� �̸�';

2) ���̺� ����

DROP TABLE review;

3) ���� ��� 

INSERT INTO review(reviewno, title, content, rdate, mainimg, imgsize, youtube, cnt, visible, review_rating, memberno)
VALUES((SELECT NVL(MAX(reviewno), 0)+1 as reviewno FROM review), 
'�����׽�Ʈ2', '�׽�Ʈ2', sysdate, 'img.jpg', 1000, '<iframe width="854" height="480" src="https://www.youtube.com/embed/j6jZIfwioCA" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>', 0 , 'Y', 5, 2);


   
4) ��ȸ
select * from review;

4_1) 1�� ��ȸ
SELECT reviewno, title, content, rdate, mainimg, imgsize, youtube, cnt, visible, review_rating, memberno
FROM review
WHERE reviewno = 7;

4_2) ��� ��ȸ
SELECT reviewno, title, content, rdate, mainimg, imgsize, youtube, cnt, visible 
FROM review  
ORDER BY reviewno desc;

5) ����
UPDATE review
SET title = '���� �׽�Ʈ ����', content= '��������Դϴ�.', youtube='�����̴�'
WHERE reviewno = 2;

6) ����

6_1) ��� ���ڵ� ����
DELETE FROM review;

6_2) 1�� ����
DELETE FROM review
WHERE reviewno = 2;
 
7) ��ȸ�� ����

UPDATE review
SET cnt = nvl(cnt, 0) + 1
WHERE reviewno = 1;  
 
UPDATE review
SET cnt = cnt + 1
WHERE reviewno = 1; 

-- VISIBLE SHOW
UPDATE review
SET visible='Y'
WHERE reviewno = 3;

-- HIDE
UPDATE review
SET visible='N'
WHERE reviewno = 3;

-- ����¡
SELECT reviewno, title, content, rdate, mainimg, imgsize, youtube, cnt, visible, review_rating, memberno, r
FROM (
          SELECT reviewno, title, content, rdate, mainimg, imgsize, youtube, cnt, visible, review_rating, memberno, rownum as r
          FROM (
                    SELECT reviewno, title, content, rdate, mainimg, imgsize, youtube, cnt, visible, review_rating, memberno
                     FROM review
                     WHERE title LIKE '%��%' AND visible == 'Y'
                     ORDER BY reviewno DESC
          )
)
WHERE r >=1 AND R<=10;

-- ȸ���� ���� ���
SELECT reviewno, title, content, rdate, mainimg, imgsize, youtube, cnt, visible, review_rating, memberno, r
FROM (
          SELECT reviewno, title, content, rdate, mainimg, imgsize, youtube, cnt, visible, review_rating, memberno, rownum as r
          FROM (
                    SELECT reviewno, title, content, rdate, mainimg, imgsize, youtube, cnt, visible, review_rating, memberno
                     FROM review
                     WHERE memberno = 1 AND title LIKE '%1%'
                     ORDER BY reviewno DESC
          )
)
WHERE r >=1 AND R<=10;

-- ���並 �� ȸ�� �̸�, ����
select mname, memail
from member
where memberno = (select memberno from review where reviewno = 1);

-- ���亰 ��� ����
 SELECT count(*) as cnt
 FROM rcomment
 WHERE reviewno=(select reviewno from review where reviewno = 1)

-- �� ���� ����
SELECT count(*)
FROM review;